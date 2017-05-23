package com.mebitech.persistence.dao;

import com.mebitech.core.api.persistence.IABaseDao;
import com.mebitech.core.api.persistence.PropertyOrder;
import com.mebitech.core.api.persistence.entities.IEntity;
import com.mebitech.core.api.persistence.enums.Converter;
import com.mebitech.core.api.persistence.enums.CriteriaOperator;
import com.mebitech.core.api.persistence.filter.IFilter;
import com.mebitech.core.api.persistence.filter.IFilterAndPager;
import com.mebitech.persistence.QueryCriteriaImpl;
import com.mebitech.persistence.QueryImpl;
import com.mebitech.persistence.dao.util.PredGenerator;
import com.mebitech.persistence.dao.util.PropertyObj;
import com.mebitech.persistence.filter.FilterAndPagerImpl;
import com.mebitech.persistence.filter.FilterImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.Table;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.util.*;

/**
 * Created by tayipdemircan on 14.11.2016.
 */
public abstract class ABaseDao<T> implements IABaseDao {

    private Map<String, String[]> selectionMap;
    private String fullQueryString = "";
    private Long userLevelId;

    private Long levelId;

    private Long userId;

    private boolean innerQuery = false;

    private static Logger logger = LoggerFactory.getLogger(ABaseDao.class);
    private EntityManager entityManager;

    public void init() {
         logger.info("Initializing "+ typedClass.getName() +" DAO.");
    }

    public void destroy() {
        logger.info("Destroying DAO.");
    }

    final Class<T> typedClass;

    public ABaseDao(Class<T> typedClass) {
        this.typedClass = typedClass;
    }

    public T save(Object o) throws Exception {
        T obj = (T) o;
        ((IEntity) obj).setDeleted(false);
        ((IEntity) obj).setCreateDate(new Date());
        entityManager.persist(obj);
        logger.debug("Object persisted: {}", obj.toString());
        return obj;
    }

    public T update(Object t) throws Exception {
        if(t instanceof Map)
            return mapUpdate((Map)t);
        T obj = (T) t;
        obj = entityManager.merge(obj);
        logger.debug("object merged: {}", obj.toString());
        return obj;
    }

    public T mapUpdate(Map changedMap) throws Exception {
        Long id = ((Integer) changedMap.get("id")).longValue();
        T obj = (T) find(id);
        Class objClass = obj.getClass();
        for (int i = 0; i < changedMap.keySet().toArray().length ; i++) {
            String key = (String)changedMap.keySet().toArray()[i];
            if(!key.equals("id")) {
                Object value = changedMap.get(key);
                Class propertyClass = getPropertyClass(key);
                if(value instanceof String && value.toString().matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}"))
                    value = Converter.tryParseDate(value.toString());
                else if(value instanceof Integer && getPropertyClass(key).getName().indexOf("Long") > -1)
                    value = ((Integer) value).longValue();
                else if(value instanceof HashMap && propertyClass.getName().indexOf("com.mebitech") > -1){
                    Object joinObject = Class.forName(propertyClass.getName()).newInstance();
                    joinObject.getClass().getDeclaredMethod("setId", Long.class).invoke(joinObject, ((Integer) ((HashMap) value).get("id")).longValue());
                    value = joinObject;
                }

                String setMethod = "set" + key.substring(0, 1).toUpperCase() + key.substring(1, key.length());
                setMethod = setMethod.replace("İ", "I");
                objClass.getDeclaredMethod(setMethod, propertyClass).invoke(obj, value);
            }
        }

        entityManager.persist(obj);
        logger.debug("object merged: {}", obj.toString());
        return (T) find(id); //kayıttan sonra satırın son halini dönüyor
    }

    public void delete(Long id) throws Exception {
        T obj = (T) find(id);
        ((IEntity) obj).setDeleted(true);
        update(obj);
    }


    public Object find(Long id) {
        Map<String, Object> propertyMap = new HashMap<String, Object>();
        propertyMap.put("deleted", false);
        propertyMap.put("id", id);
        if (getLevelId() != null)
            propertyMap.put("levelId", getLevelId());

        List<Object> list = findByProperties(propertyMap, null, 0);
        logger.debug("IUser objects found: {}", list);
        return list.get(0);
    }

    @Override
    public List<Object> findAll(Integer maxResults) {

        Map<String, Object> propertyMap = new HashMap<String, Object>();
        propertyMap.put("deleted", false);
        if (getLevelId() != null)
            propertyMap.put("levelId", getLevelId());
        List<Object> list = findByProperties(propertyMap, null, 0);
        logger.debug("IUser objects found: {}", list);
        return list;
    }

    @Override
    public List<Object> findByProperty(String propertyName, Object value, Integer maxResults) {

        Map<String, Object> propertyMap = new HashMap<String, Object>();
        propertyMap.put("deleted", false);
        propertyMap.put(propertyName, value);
        List<Object> list = findByProperties(propertyMap, null, 0);
        return list;
    }

    @Override
    public List<Object> findByProperties(Map<String, Object> propertiesMap, List<PropertyOrder> orders, Integer maxResults) {
        innerQuery = true;
        FilterAndPagerImpl filterAndPager = new FilterAndPagerImpl();
        filterAndPager.setFilters(new ArrayList<IFilter>());
        for (String key : propertiesMap.keySet()) {
            FilterImpl filter = new FilterImpl();
            filter.setProperty(key);
            filter.setValue(propertiesMap.get(key));
            filter.setOperator(propertiesMap.get(key) instanceof List ? "IN" : "=");
            filterAndPager.getFilters().add(filter);
        }
        Map<String, Object> retMap = findByFilters(filterAndPager);
        return (List<Object>) retMap.get("data");
    }

    public List getProperties() {
        return getProperties(typedClass);
    }

    public List getProperties(Class propertyClass) {
        Metamodel meta = entityManager.getMetamodel();
        EntityType<T> entityType = meta.entity(propertyClass);

        // Check whether @Table annotation is present on the class.
        Table t = propertyClass.getClass().getAnnotation(Table.class); //typedClass.getAnnotation(Table.class);

        String tableName = (t == null) ? entityType.getName().toUpperCase() : t.name();
        logger.debug("Table name found: {}", tableName);
        List<PropertyObj> propertyList = new ArrayList<PropertyObj>();
        for (Attribute<? super T, ?> a : entityType.getAttributes()) {
            if (!a.getName().equals("deleted") && !a.getName().equals("entityAttributes") && !a.getName().equals("crudTypes")) {
                PropertyObj obj = new PropertyObj(a.getName(), a.getJavaType().getName());
                propertyList.add(obj);
            }
        }
        return propertyList;
    }

    private Class getPropertyClass(String propertyName){
        Metamodel meta = entityManager.getMetamodel();
        EntityType<T> entityType = meta.entity(typedClass);

        // Check whether @Table annotation is present on the class.
        Table t = typedClass.getAnnotation(Table.class);

        String tableName = (t == null) ? entityType.getName().toUpperCase() : t.name();
        logger.debug("Table name found: {}", tableName);

        for (Attribute<? super T, ?> a : entityType.getAttributes()) {
            if (a.getName().equals(propertyName)) {
                return a.getJavaType();
            }
        }
        return null;
    }

    @Override
    public Map<String, Object> findByFilters(IFilterAndPager filterAndPager) {
        if (selectionMap == null)
            selectionMap = filterAndPager.getSelectionMap();
        if(filterAndPager.getFullQueryString() != null && !filterAndPager.getFullQueryString().equals(""))
            fullQueryString = filterAndPager.getFullQueryString();

        return getResults(getQueryObject(filterAndPager));
    }

    public Map<String, Object> getResults(QueryImpl query) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = selectionMap != null ? builder.createTupleQuery() : builder.createQuery(typedClass);
        Root<T> from = criteriaQuery.from(typedClass);
        if (selectionMap != null) {
            criteriaQuery.multiselect(getSelections(selectionMap, from));
        }
        Predicate predicate = null;
        if (query.getCriteria() != null && query.getCriteria().length > 0) {
            Predicate pred = null;
            for (QueryCriteriaImpl entry : query.getCriteria()) {
                if (entry.getValues() != null && !entry.getValues().toString().isEmpty()) {
                    String[] key = entry.getField().split("\\.");
                    if (key.length > 1) {
                        Join<Object, Object> join = null;
                        for (int i = 0; i < key.length - 1; i++) {
                            join = join != null ? join.join(key[i]) : from.join(key[i]);
                        }

                        pred = getPredicate(key[key.length - 1], entry.getValues()[0], entry.getOperator(), from, builder, join);

//                        if (entry.getOperator().equals(CriteriaOperator.EQ)) {
//                            pred = builder.equal(join.get(), entry.getValues()[0]);
//                        } else if (entry.getOperator().equals(CriteriaOperator.LIKE)) {
//                            Expression<String> path = join.get(key[key.length - 1]);
//                            pred = builder.like(path, entry.getValues()[0].toString());
//                        }
                    } else {
                        pred = getPredicate(entry, from, builder, null);
                    }
                    predicate = predicate == null ? pred : builder.and(predicate, pred);
                }
            }

            if(fullQueryString != null && !fullQueryString.equals("")){
                Predicate fullQueryPred = getFullQueryStringCriteria(builder, from);
                predicate = predicate == null ? fullQueryPred : builder.and(predicate, fullQueryPred);
                fullQueryString = "";
            }

            if (predicate != null) {
                criteriaQuery.where(predicate);
            }
        }

        Map<String, Object> retMap = new HashMap<String, Object>();

        if(!innerQuery) {//iç sorgularda boş yere count hesaplamasın
            CriteriaQuery<Long> criteriaQueryCount = builder.createQuery(Long.class);
            criteriaQueryCount.select(builder.count(criteriaQueryCount.from(typedClass)));
            criteriaQueryCount.where(criteriaQuery.getRestriction());

            retMap.put("count", entityManager.createQuery(criteriaQueryCount).getSingleResult());
        }

        TypedQuery typedQuery = entityManager.createQuery(criteriaQuery);
        if (query.getOffset() > 0) {
            typedQuery.setFirstResult(query.getOffset());
        }
        if (query.getMaxResults() > 0) {
            typedQuery.setMaxResults(query.getMaxResults());
        }

        innerQuery = false;
        if (selectionMap != null) {
            List retList = new ArrayList();
            List<Tuple> set = typedQuery.getResultList();
            for (Tuple tuple : set) {
                Map<String, Object> map = new HashMap<String, Object>();
                for (String key : selectionMap.keySet()) {
                    if (key.equals("this")) {
                        for (String select : selectionMap.get(key)) {
                            map.put(select, tuple.get(select));
                        }
                    } else {
                        Map<String, Object> innerMap = new HashMap<String, Object>();
                        for (String select : selectionMap.get(key)) {
                            innerMap.put(select, tuple.get(key + "_" + select));
                        }
                        map.put(key, innerMap);
                    }
                }
                retList.add(map);
            }
            retMap.put("data", retList);
            selectionMap = null;
            return retMap;
        }

        retMap.put("data", typedQuery.getResultList());

        return retMap;
    }

    @Override
    public Map<String, Object> findByInnerFilters(IFilterAndPager filterAndPager, Class innerClass) {
        return findByInnerFilters(filterAndPager, innerClass, "");
    }

    public Map<String, Object> findByInnerFilters(IFilterAndPager filterAndPager, Class innerClass, String entityFieldName) {
        return getResults(getQueryObject(filterAndPager), innerClass, entityFieldName);
    }

    public Map<String, Object> getResults(QueryImpl query, Class<? extends T> innerClass, String entityFieldName) {
        Map<String, Object> retMap = new HashMap<String, Object>();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery((Class<T>) innerClass);
        Root<T> root = criteriaQuery.from(typedClass);
        String innerListName = "";
        //master table criterias
        Predicate predicate = null;
        Integer id = 0;
        for (QueryCriteriaImpl entry : query.getCriteria()) {
            Predicate pred = null;
            if (entry.getValues() != null && !entry.getValues().toString().isEmpty()) {
                if (entry.getField().split("\\.").length <= 1) {

                    if (entry.getField().equals("id"))
                        id = (Integer) entry.getValues()[0];
                    pred = getPredicate(entry, root, criteriaBuilder, null);

                    predicate = predicate == null ? pred : criteriaBuilder.and(predicate, pred);
                } else {
                    innerListName = entry.getField().split("\\.")[0];
                    entityFieldName = innerListName;
                }

            }
        }
        criteriaQuery.where(predicate);

        Join<Object, T> join = root.join(!innerListName.equals("") ? innerListName : entityFieldName);
        CriteriaQuery<T> cq = criteriaQuery.select(join);

        for (QueryCriteriaImpl entry : query.getCriteria()) {
            Predicate pred = null;
            if (entry.getValues() != null && !entry.getValues().toString().isEmpty()) {
                String key[] = entry.getField().split("\\.");
                if (key.length > 1) {
                    pred = getPredicate(key[key.length - 1], entry.getValues()[0], entry.getOperator(), root, criteriaBuilder, join);
                    predicate = predicate == null ? pred : criteriaBuilder.and(predicate, pred);
                }
            }
        }


        cq.where(predicate);
        TypedQuery<T> typedQuery = entityManager.createQuery(cq);

        CriteriaQuery<Long> criteriaQueryCount = criteriaBuilder.createQuery(Long.class);
        criteriaQueryCount.select(criteriaBuilder.count(criteriaQueryCount.from(typedClass))).distinct(true);
        criteriaQueryCount.where(cq.getRestriction());

        if (!innerListName.equals(""))
            retMap.put("count", entityManager.createQuery(criteriaQueryCount).getSingleResult());
        else
            retMap.put("count", ((Integer) typedQuery.getResultList().size()).longValue());

        if (query.getOffset() > 0) {
            typedQuery.setFirstResult(query.getOffset());
        }
        if (query.getMaxResults() > 0) {
            typedQuery.setMaxResults(query.getMaxResults());
        }

        Set result = new HashSet(typedQuery.getResultList());

        Map<String, Object> resultMap = new HashMap<String, Object>();

        resultMap.put("id", id.longValue());
        resultMap.put(entityFieldName, result);

        retMap.put("data", resultMap);

        return retMap;
    }

    private QueryImpl getQueryObject(IFilterAndPager filterAndPager) {
        QueryImpl query = new QueryImpl();
        if (filterAndPager.getLimit() != null)
            query.setMaxResults(filterAndPager.getLimit());
        if (filterAndPager.getStart() != null)
            query.setOffset(filterAndPager.getStart());
        List<QueryCriteriaImpl> criterias = new ArrayList<QueryCriteriaImpl>();
        QueryCriteriaImpl queryCriteria = new QueryCriteriaImpl();
        queryCriteria.setField("deleted");
        queryCriteria.setValues(new Object[]{false});
        queryCriteria.setOperator(CriteriaOperator.EQ);
        criterias.add(queryCriteria);

        if (getLevelId() != null) {
            QueryCriteriaImpl queryCriteriaLevel = new QueryCriteriaImpl();
            queryCriteriaLevel.setField("levelId");
            queryCriteriaLevel.setValues(new Object[]{getLevelId()});
            queryCriteriaLevel.setOperator(CriteriaOperator.EQ);
            criterias.add(queryCriteriaLevel);
        }
        if (filterAndPager.getFilters() != null && filterAndPager.getFilters().size() > 0) {
            for (IFilter filter : filterAndPager.getFilters()) {
                queryCriteria = new QueryCriteriaImpl();
                queryCriteria.setField(filter.getProperty());
                queryCriteria.setValues(new Object[]{filter.getValue()});
                queryCriteria.setOperator(Converter.convertCriteriaOperator(filter.getOperator()));
                queryCriteria.setType(filter.getType());
                criterias.add(queryCriteria);
            }
        }
        query.setCriteria(criterias.toArray(new QueryCriteriaImpl[criterias.size()]));

        return query;
    }

    private Predicate getPredicate(String field, Object value, CriteriaOperator operator, Root<T> from, CriteriaBuilder builder, Join join) {
        QueryCriteriaImpl entry = new QueryCriteriaImpl();
        entry.setOperator(operator);
        entry.setValues(new Object[]{value});
        entry.setField(field);

        return getPredicate(entry, from, builder, join);
    }

    private Predicate getPredicate(QueryCriteriaImpl entry, Root<T> from, CriteriaBuilder builder, Join join) {

        PredGenerator generator = new PredGenerator(entry.getOperator(), entry.getField(), entry.getValues()[0], from, join, builder, entry.getType());
        Predicate pred = generator.getPredicate();
        generator = null;

//        if (entry.getOperator().equals(CriteriaOperator.EQ)) {
//            pred = builder.equal(join != null ? join.get(entry.getField()) : from.get(entry.getField()), entry.getValues()[0]);
//        } else if (entry.getOperator().equals(CriteriaOperator.NE)) {
//            pred = builder.notEqual(join != null ? join.get(entry.getField()) : from.get(entry.getField()), entry.getValues()[0]);
//        } else if (entry.getOperator().equals(CriteriaOperator.LIKE)) {
//            Expression<String> path = join != null ? join.get(entry.getField()) : from.get(entry.getField());
//            pred = builder.like(path, entry.getValues()[0].toString());
//        } else if (entry.getOperator().equals(CriteriaOperator.GT)) {
//            if (entry.getType().equals("date")) {
//                Expression<Date> path = join != null ? join.get(entry.getField()) : from.get(entry.getField());
//                pred = builder.greaterThan(path, (Date) entry.getValues()[0]);
//            } else if (entry.getType().equals("int")) {
//                Expression<Integer> path = join != null ? join.get(entry.getField()) : from.get(entry.getField());
//                pred = builder.greaterThan(path, (Integer) entry.getValues()[0]);
//            } else {
//                Expression<String> path = join != null ? join.get(entry.getField()) : from.get(entry.getField());
//                pred = builder.greaterThan(path, (String) entry.getValues()[0]);
//            }
//        } else if (entry.getOperator().equals(CriteriaOperator.GE)) {
//            if (entry.getType().equals("date")) {
//                Expression<Date> path = join != null ? join.get(entry.getField()) : from.get(entry.getField());
//                pred = builder.greaterThanOrEqualTo(path, (Date) entry.getValues()[0]);
//            } else if (entry.getType().equals("int")) {
//                Expression<Integer> path = join != null ? join.get(entry.getField()) : from.get(entry.getField());
//                pred = builder.greaterThanOrEqualTo(path, (Integer) entry.getValues()[0]);
//            } else {
//                Expression<String> path = join != null ? join.get(entry.getField()) : from.get(entry.getField());
//                pred = builder.greaterThanOrEqualTo(path, (String) entry.getValues()[0]);
//            }
//        } else if (entry.getOperator().equals(CriteriaOperator.LE)) {
//            if (entry.getType().equals("date")) {
//                Expression<Date> path = join != null ? join.get(entry.getField()) : from.get(entry.getField());
//                pred = builder.lessThanOrEqualTo(path, (Date) entry.getValues()[0]);
//            } else if (entry.getType().equals("int")) {
//                Expression<Integer> path = join != null ? join.get(entry.getField()) : from.get(entry.getField());
//                pred = builder.lessThanOrEqualTo(path, (Integer) entry.getValues()[0]);
//            } else {
//                Expression<String> path = join != null ? join.get(entry.getField()) : from.get(entry.getField());
//                pred = builder.lessThanOrEqualTo(path, (String) entry.getValues()[0]);
//            }
//        } else if (entry.getOperator().equals(CriteriaOperator.LT)) {
//            if (entry.getType().equals("date")) {
//                Expression<Date> path = join != null ? join.get(entry.getField()) : from.get(entry.getField());
//                pred = builder.lessThan(path, (Date) entry.getValues()[0]);
//            } else if (entry.getType().equals("int")) {
//                Expression<Integer> path = join != null ? join.get(entry.getField()) : from.get(entry.getField());
//                pred = builder.lessThan(path, (Integer) entry.getValues()[0]);
//            } else {
//                Expression<String> path = join != null ? join.get(entry.getField()) : from.get(entry.getField());
//                pred = builder.lessThan(path, (String) entry.getValues()[0]);
//            }
//        } else if (entry.getOperator().equals(CriteriaOperator.BT)) {
//
//        } else if (entry.getOperator().equals(CriteriaOperator.IN)) {
//            Expression<Object> exp = join != null ? join.get(entry.getField()) : from.get(entry.getField());
//            pred = exp.in(entry.getValues()[0]);
//        } else if (entry.getOperator().equals(CriteriaOperator.NOT_IN)) {
//            //TODO
//        } else if (entry.getOperator().equals(CriteriaOperator.NULL)) {
//            Expression<Object> exp = join != null ? join.get(entry.getField()) : from.get(entry.getField());
//            pred = exp.isNull();
//        } else if (entry.getOperator().equals(CriteriaOperator.NOT_NULL)) {
//            Expression<Object> exp = join != null ? join.get(entry.getField()) : from.get(entry.getField());
//            pred = exp.isNotNull();
//        }
        return pred;
    }

    private List<Selection<?>> getSelections(Map<String, String[]> selectionStrings, Root from) {
        List<Selection<?>> selections = new ArrayList<Selection<?>>();

        for (String key : selectionStrings.keySet()) {
            String[] selectionList = selectionStrings.get(key);
            Join<Object, Object> join = null;
            if (!key.equals("this")) {
                join = from.join(key, JoinType.LEFT);
            }

            for (String select : selectionList) {
                if (key.equals("this")) {
                    Selection<?> s = from.get(select).alias(select);
                    selections.add(s);
                } else {
                    Selection<?> s = join.get(select).alias(key + "_" + select);
                    selections.add(s);
                }
            }
        }
        return selections;
    }

    private Predicate getFullQueryStringCriteria(CriteriaBuilder builder, Root from){
        Predicate predicate = null;
        List<PropertyObj> propertyList = (List<PropertyObj>)getProperties();
        for(PropertyObj obj : propertyList){
            Predicate pred = null;
            if(obj.getType().equals("string")){
                Expression<String> path = from.get(obj.getField());
                pred = builder.like(path, "%" + fullQueryString + "%");
                predicate = predicate == null ? pred : builder.or(predicate,pred);
            }
        }

        return predicate;
    }

    public void truncate(String tableName) throws Exception {
        entityManager.createNativeQuery("truncate table " + tableName).executeUpdate();
        logger.debug("table truncated: {}", tableName);
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public void setLevelId(Long aLong) {
        this.levelId = aLong;
    }

    @Override
    public Long getLevelId() {
        return this.levelId;
    }

    @Override
    public void setUserId(Long aLong) {
        this.userId = aLong;
    }

    @Override
    public Long getUserId() {
        return this.userId;
    }

    @Override
    public void setUserLevelId(Long aLong) {
        this.userLevelId = aLong;
    }

    @Override
    public Long getUserLevelId() {
        return this.userLevelId;
    }

    public void setSelectionMap(Map<String, String[]> selectionMap) {
        this.selectionMap = selectionMap;
    }
}
