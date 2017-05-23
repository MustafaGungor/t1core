package com.mebitech.persistence.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.mebitech.persistence.entities.UserImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mebitech.core.api.persistence.PropertyOrder;
import com.mebitech.core.api.persistence.dao.IUserLevelDao;
import com.mebitech.core.api.persistence.entities.IUserLevel;
import com.mebitech.core.api.persistence.enums.OrderType;
import com.mebitech.persistence.entities.UserLevelImpl;

@SuppressWarnings("unchecked")
public class UserLevelDaoImpl extends ABaseDao<UserLevelImpl> implements IUserLevelDao {

    public UserLevelDaoImpl(){
        super(UserLevelImpl.class);
    }

//    private static Logger logger = LoggerFactory.getLogger(UserLevelDaoImpl.class);
//    private EntityManager entityManager;
//
//    public void init() {
//        logger.info("Initializing userlevel DAO.");
//    }
//
//    public void destroy() {
//        logger.info("Destroying userlevel DAO.");
//    }
//
//    @Override
//    public IUserLevel save(IUserLevel userlevel) {
//        UserLevelImpl userlevelImpl = new UserLevelImpl((IUserLevel) userlevel);
//        entityManager.persist(userlevelImpl);
//        logger.debug("IUserLevel object persisted: {}", userlevelImpl.toString());
//        return userlevelImpl;
//    }
//
//    @Override
//    public UserLevelImpl update(IUserLevel userlevel) {
//        UserLevelImpl userlevelImpl = new UserLevelImpl(userlevel);
//        userlevelImpl = entityManager.merge(userlevelImpl);
//        logger.debug("IUserLevel object merged: {}", userlevelImpl.toString());
//        return userlevelImpl;
//    }
//
//    @Override
//    public void delete(Long userlevelId) {
//        UserLevelImpl userlevelImpl = entityManager.find(UserLevelImpl.class, userlevelId);
//// Never truly delete, just mark as deleted!
//        userlevelImpl.setDeleted(true);
//        userlevelImpl = entityManager.merge(userlevelImpl);
//        logger.debug("IUserLevel object marked as deleted: {}", userlevelImpl.toString());
//    }
//
//    @Override
//    public UserLevelImpl find(Long userlevelId) {
//        UserLevelImpl UserLevelImpl = entityManager.find(UserLevelImpl.class, userlevelId);
//        logger.debug("IUserLevel object found: {}", UserLevelImpl.toString());
//        return UserLevelImpl;
//    }
//
//    @Override
//    public List<? extends IUserLevel> findAll(Class<? extends IUserLevel> obj, Integer maxResults) {
//        List<UserLevelImpl> userlevelList = entityManager
//                .createQuery("select t from " + UserLevelImpl.class.getSimpleName() + " t", UserLevelImpl.class)
//                .getResultList();
//        logger.debug("IUserLevel objects found: {}", userlevelList);
//        return userlevelList;
//    }
//
//    @Override
//    public List<? extends IUserLevel> findByProperty(Class<? extends IUserLevel> obj, String propertyName, Object propertyValue,
//                                                     Integer maxResults) {
//        TypedQuery<UserLevelImpl> query = entityManager.createQuery(
//                "select t from " + UserLevelImpl.class.getSimpleName() + " t where t." + propertyName + "= :propertyValue",
//                UserLevelImpl.class).setParameter("propertyValue", propertyValue);
//        if (maxResults > 0) {
//            query = query.setMaxResults(maxResults);
//        }
//        List<UserLevelImpl> userlevelList = query.getResultList();
//        logger.debug("IUserLevel objects found: {}", userlevelList);
//        return userlevelList;
//    }
//
//    @Override
//    public List<? extends IUserLevel> findByProperties(Class<? extends IUserLevel> obj, Map<String, Object> propertiesMap,
//                                                       List<PropertyOrder> orders, Integer maxResults) {
//        orders = new ArrayList<PropertyOrder>();
//        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<UserLevelImpl> criteria = (CriteriaQuery<UserLevelImpl>) builder.createQuery(UserLevelImpl.class);
//        Root<UserLevelImpl> from = (Root<UserLevelImpl>) criteria.from(UserLevelImpl.class);
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
//        List<UserLevelImpl> list = null;
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
