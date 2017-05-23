package com.mebitech.persistence.dao;

import com.mebitech.core.api.persistence.PropertyOrder;
import com.mebitech.core.api.persistence.dao.IUserDao;
import com.mebitech.core.api.persistence.entities.IUser;
import com.mebitech.core.api.persistence.enums.CriteriaOperator;
import com.mebitech.core.api.persistence.enums.OrderType;
import com.mebitech.core.api.persistence.filter.IFilter;
import com.mebitech.core.api.persistence.filter.IFilterAndPager;
import com.mebitech.persistence.QueryCriteriaImpl;
import com.mebitech.persistence.QueryImpl;
import com.mebitech.persistence.dao.util.PropertyObj;
import com.mebitech.persistence.entities.UserImpl;
import com.mebitech.persistence.filter.FilterImpl;
import org.osgi.service.useradmin.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.util.*;

/**
 * Created by tayipdemircan on 24.10.2016.
 */
public class UserDaoImpl extends ABaseDao<UserImpl> implements IUserDao {

    public UserDaoImpl(){
        super(UserImpl.class);
    }
//    private static Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
//
//    private EntityManager entityManager;
//
//    public void init() {
//        logger.info("Initializing user DAO.");
//    }
//
//    public void destroy() {
//        logger.info("Destroying user DAO.");
//    }
//
//    @Override
//    public IUser save(IUser json){
//
//        UserImpl userImpl = new UserImpl(json);
//        try {
////            if(userImpl.getPassword() == null || (userImpl.getPassword() != null && userImpl.getPassword().equals("")))
////                userImpl.setPassword(UUID.randomUUID().toString());
//            entityManager.persist(userImpl);
//        } catch (Exception ex){
//            ex.printStackTrace();
//        }
//
//        return userImpl;
//    }
//
//    @Override
//    public IUser update(IUser user) {
//        UserImpl userImpl = new UserImpl(user);
//        entityManager.merge(userImpl);
//        return userImpl;
//    }
//
//    @Override
//    public IUser delete(IUser user) {
//        return null;
//    }
//
//    @Override
//    public void delete(Long id) {
//        UserImpl userImpl = (UserImpl) find(id);
//        userImpl.setDeleted(true);
//        entityManager.merge(userImpl);
//    }
//
//    @Override
//    public IUser find(Long id) {
//        UserImpl user = entityManager.find(UserImpl.class,id);
//        return user;
//    }
//
//    @Override
//    public List<? extends IUser> findAll(Class<? extends IUser> obj, Integer maxResults) {
//        Map<String, Object> propertyMap = new HashMap<String, Object>();
//        propertyMap.put("deleted",false);
//        List<UserImpl> userList = (List<UserImpl>)findByProperties(UserImpl.class,propertyMap,null,0);
//        logger.debug("IUser objects found: {}", userList);
//        return userList;
//    }
//
//    @Override
//    public List<? extends IUser> findByProperty(Class<? extends IUser> obj, String propertyName, Object value, Integer maxResults) {
//        return null;
//    }
//
//    @Override
//    public List<? extends IUser> findByProperties(Class<? extends IUser> obj, Map<String, Object> propertiesMap, List<PropertyOrder> orders, Integer maxResults) {
//        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<UserImpl> criteriaQuery = builder.createQuery(UserImpl.class);
//        Root<UserImpl> from = criteriaQuery.from(UserImpl.class);
//        Predicate predicate = null;
//        if (propertiesMap != null) {
//            Predicate pred = null;
//            for (Map.Entry<String, Object> entry : propertiesMap.entrySet()) {
//                if (entry.getValue() != null && !entry.getValue().toString().isEmpty()) {
//                    pred = builder.equal(from.get(entry.getKey()),entry.getValue());
//                }
//                if(pred != null){
//                    predicate = predicate == null ? pred : builder.and(predicate,pred);
//                }
//            }
//            if(predicate!= null) {
//                criteriaQuery.where(predicate);
//            }
//        }
//        return entityManager.createQuery(criteriaQuery).getResultList();
//    }
//
//    public List getProperties() {
//        Metamodel meta = entityManager.getMetamodel();
//        EntityType<UserImpl> entityType = meta.entity(UserImpl.class);
//
//        // Check whether @Table annotation is present on the class.
//        Table t = UserImpl.class.getAnnotation(Table.class);
//
//        String tableName = (t == null) ? entityType.getName().toUpperCase() : t.name();
//        logger.debug("Table name found: {}", tableName);
//        List<PropertyObj> propertyList = new ArrayList<PropertyObj>();
//        for(Attribute<? super UserImpl, ?> a : entityType.getAttributes()){
//            PropertyObj obj = new PropertyObj(a.getName(),a.getJavaType().getName());
//            propertyList.add(obj);
//        }
//        return propertyList;
//    }
//
//    @Override
//    public Map<String,Object> findByFilters(IFilterAndPager filterAndPager) {
//        QueryImpl query = new QueryImpl();
//        if (filterAndPager.getLimit() != null)
//            query.setMaxResults(filterAndPager.getLimit());
//        if (filterAndPager.getStart() != null)
//            query.setOffset(filterAndPager.getStart());
//        List<QueryCriteriaImpl> criterias = new ArrayList<QueryCriteriaImpl>();
//        QueryCriteriaImpl queryCriteria = new QueryCriteriaImpl();
//        queryCriteria.setField("deleted");
//        queryCriteria.setValues(new Object[]{false});
//        queryCriteria.setOperator(CriteriaOperator.EQ);
//        criterias.add(queryCriteria);
//        if(filterAndPager.getFilters() != null && filterAndPager.getFilters().size() > 0) {
//            for (IFilter filter : filterAndPager.getFilters()) {
//                queryCriteria = new QueryCriteriaImpl();
//                queryCriteria.setField(filter.getProperty());
//                queryCriteria.setValues(new Object[]{filter.getValue()});
//                queryCriteria.setOperator(CriteriaOperator.LIKE);
//                criterias.add(queryCriteria);
//            }
//        }
//        query.setCriteria(criterias.toArray(new QueryCriteriaImpl[criterias.size()]));
//
//        return getResults(query);
//    }
//
//    public Map<String,Object> getResults(QueryImpl query) {
//        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<UserImpl> criteriaQuery = builder.createQuery(UserImpl.class);
//        Root<UserImpl> from = criteriaQuery.from(UserImpl.class);
//        Predicate predicate = null;
//        if (query.getCriteria() != null && query.getCriteria().length > 0) {
//            Predicate pred = null;
//            for (QueryCriteriaImpl entry : query.getCriteria()) {
//                if (entry.getValues() != null && !entry.getValues().toString().isEmpty()) {
//                    String[] key = entry.getField().split(".");
//                    if (key.length > 1) {
//                        Join<Object, Object> join = null;
//                        for (int i = 0; i < key.length - 1; i++) {
//                            join = join != null ? join.join(key[i]) : from.join(key[i]);
//                        }
//                        pred = builder.equal(join.get(key[key.length - 1]), entry.getValues());
//                    } else {
//                        if(entry.getOperator().equals(CriteriaOperator.EQ))
//                            pred = builder.equal(from.get(entry.getField()), entry.getValues()[0]);
//                        else if(entry.getOperator().equals(CriteriaOperator.LIKE)) {
//                            Expression<String> path = from.get(entry.getField());
//                            pred = builder.like(path, entry.getValues()[0].toString());
//                        }
//                    }
//                    predicate = predicate == null ? pred : builder.and(predicate, pred);
//                }
//            }
//            if (predicate != null) {
//                criteriaQuery.where(predicate);
//            }
//        }
//
//        CriteriaQuery<Long> criteriaQueryCount = builder.createQuery(Long.class);
//        criteriaQueryCount.select(builder.count(criteriaQueryCount.from(UserImpl.class)));
//        criteriaQueryCount.where(criteriaQuery.getRestriction());
//
//
//        TypedQuery typedQuery = entityManager.createQuery(criteriaQuery);
//
//        Map<String,Object> retMap = new HashMap<String, Object>();
//
//        retMap.put("count",entityManager.createQuery(criteriaQueryCount).getSingleResult());
//
//        if(query.getOffset() > 0){
//            typedQuery.setFirstResult(query.getOffset());
//        }
//        if(query.getMaxResults() > 0){
//            typedQuery.setMaxResults(query.getMaxResults());
//        }
//
//        retMap.put("data",typedQuery.getResultList());
//
//        return retMap;
//    }
//
//    @Override
//    public IUser addGroup(Long id, List<Integer> groupIds) {
//        return null;
//    }
//
//    public void setEntityManager(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }

}
