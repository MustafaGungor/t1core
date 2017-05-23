package com.mebitech.persistence.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.persistence.EntityManager;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import com.mebitech.persistence.dao.util.PropertyObj;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mebitech.core.api.persistence.PropertyOrder;
import com.mebitech.core.api.persistence.dao.ILevelDao;
import com.mebitech.core.api.persistence.entities.ILevel;
import com.mebitech.core.api.persistence.enums.OrderType;
import com.mebitech.persistence.entities.LevelImpl;

@SuppressWarnings("unchecked")
public class LevelDaoImpl extends ABaseDao<LevelImpl> implements ILevelDao{

    public LevelDaoImpl() {
        super(LevelImpl.class);
    }


//    private static Logger logger = LoggerFactory.getLogger(LevelDaoImpl.class);
//    private EntityManager entityManager;
//
//    public void init() {
//        logger.info("Initializing level DAO.");
//    }
//
//    public void destroy() {
//        logger.info("Destroying level DAO.");
//    }
//
//    @Override
//    public ILevel save(ILevel level) {
//        LevelImpl levelImpl = new LevelImpl((ILevel) level);
//        entityManager.persist(levelImpl);
//        logger.debug("ILevel object persisted: {}", levelImpl.toString());
//        return levelImpl;
//    }
//
//    @Override
//    public LevelImpl update(ILevel level) {
//        LevelImpl levelImpl = new LevelImpl(level);
//        levelImpl = entityManager.merge(levelImpl);
//        logger.debug("ILevel object merged: {}", levelImpl.toString());
//        return levelImpl;
//    }
//
//    @Override
//    public void delete(Long levelId) {
//        LevelImpl levelImpl = entityManager.find(LevelImpl.class, levelId);
//// Never truly delete, just mark as deleted!
//        levelImpl.setDeleted(true);
//        levelImpl = entityManager.merge(levelImpl);
//        logger.debug("ILevel object marked as deleted: {}", levelImpl.toString());
//    }
//
//    @Override
//    public LevelImpl find(Long levelId) {
//        LevelImpl LevelImpl = entityManager.find(LevelImpl.class, levelId);
//        logger.debug("ILevel object found: {}", LevelImpl.toString());
//        return LevelImpl;
//    }
//
//    @Override
//    public List<? extends ILevel> findAll(Class<? extends ILevel> obj, Integer maxResults) {
//        List<LevelImpl> levelList = entityManager
//                .createQuery("select t from " + LevelImpl.class.getSimpleName() + " t", LevelImpl.class)
//                .getResultList();
//        logger.debug("ILevel objects found: {}", levelList);
//        return levelList;
//    }
//
//    @Override
//    public List<? extends ILevel> findByProperty(Class<? extends ILevel> obj, String propertyName, Object propertyValue,
//                                                 Integer maxResults) {
//        TypedQuery<LevelImpl> query = entityManager.createQuery(
//                "select t from " + LevelImpl.class.getSimpleName() + " t where t." + propertyName + "= :propertyValue",
//                LevelImpl.class).setParameter("propertyValue", propertyValue);
//        if (maxResults > 0) {
//            query = query.setMaxResults(maxResults);
//        }
//        List<LevelImpl> levelList = query.getResultList();
//        logger.debug("ILevel objects found: {}", levelList);
//        return levelList;
//    }
//
//
//    public List getProperties() {
//        Metamodel meta = entityManager.getMetamodel();
//        EntityType<LevelImpl> entityType = meta.entity(LevelImpl.class);
//
//        // Check whether @Table annotation is present on the class.
//        Table t = LevelImpl.class.getAnnotation(Table.class);
//
//        String tableName = (t == null) ? entityType.getName().toUpperCase() : t.name();
//        logger.debug("Table name found: {}", tableName);
//        List<PropertyObj> propertyList = new ArrayList<PropertyObj>();
//        for(Attribute<? super LevelImpl, ?> a : entityType.getAttributes()){
//            PropertyObj obj = new PropertyObj(a.getName(),a.getJavaType().getName());
//            propertyList.add(obj);
//        }
//        return propertyList;
//    }
//
//    @Override
//    public List<? extends ILevel> findByProperties(Class<? extends ILevel> obj, Map<String, Object> propertiesMap,
//                                                   List<PropertyOrder> orders, Integer maxResults) {
//        orders = new ArrayList<PropertyOrder>();
//        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<LevelImpl> criteria = (CriteriaQuery<LevelImpl>) builder.createQuery(LevelImpl.class);
//        Root<LevelImpl> from = (Root<LevelImpl>) criteria.from(LevelImpl.class);
//        criteria.select(from);
//        Predicate predicate = null;
//        if (propertiesMap != null) {
//            Predicate pred = null;
//            for (Entry<String, Object> entry : propertiesMap.entrySet()) {
//                if (entry.getValue() != null && !entry.getValue().toString().isEmpty()) {
//                    String[] key = entry.getKey().split(".");
//                    if (key.length > 1) {
//                        Join<Object, Object> join = null;
//                        for (int i = 0; i < key.length - 1; i++) {
//                            join = join != null ? join.join(key[i]) : from.join(key[i]);
//                        }
//                        pred = builder.equal(join.get(key[key.length - 1]), entry.getValue());
//                    } else {
//                        pred = builder.equal(from.get(entry.getKey()), entry.getValue());
//                    }
//                    predicate = predicate == null ? pred : builder.and(predicate, pred);
//                }
//            }
//            if (predicate != null) {
//                criteria.where(predicate);
//            }
//        }
//        if (orders != null && !orders.isEmpty()) {
//            List<Order> orderList = new ArrayList<Order>();
//            for (PropertyOrder order : orders) {
//                orderList.add(order.getOrderType() == OrderType.ASC ? builder.asc(from.get(order.getPropertyName()))
//                        : builder.desc(from.get(order.getPropertyName())));
//            }
//            criteria.orderBy(orderList);
//        }
//        List<LevelImpl> list = null;
//        if (null != maxResults) {
//            list = entityManager.createQuery(criteria).setMaxResults(maxResults).getResultList();
//        } else {
//            list = entityManager.createQuery(criteria).getResultList();
//        }
//        return list;
//    }
//
//    public void setEntityManager(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }
}
