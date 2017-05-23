package com.mebitech.persistence.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

import com.mebitech.persistence.entities.GroupImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mebitech.core.api.persistence.PropertyOrder;
import com.mebitech.core.api.persistence.dao.IPermissionDao;
import com.mebitech.core.api.persistence.entities.IPermission;
import com.mebitech.core.api.persistence.enums.OrderType;
import com.mebitech.persistence.entities.PermissionImpl;

@SuppressWarnings("unchecked")
public class PermissionDaoImpl extends ABaseDao<PermissionImpl> implements IPermissionDao {

    public PermissionDaoImpl(){
        super(PermissionImpl.class);
    }
//    private static Logger logger = LoggerFactory.getLogger(PermissionDaoImpl.class);
//    private EntityManager entityManager;
//
//    public void init() {
//        logger.info("Initializing permission DAO.");
//    }
//
//    public void destroy() {
//        logger.info("Destroying permission DAO.");
//    }
//
//    @Override
//    public IPermission save(IPermission permission) {
//        PermissionImpl permissionImpl = new PermissionImpl((IPermission) permission);
//        entityManager.persist(permissionImpl);
//        logger.debug("IPermission object persisted: {}", permissionImpl.toString());
//        return permissionImpl;
//    }
//
//    @Override
//    public PermissionImpl update(IPermission permission) {
//        PermissionImpl permissionImpl = new PermissionImpl(permission);
//        permissionImpl = entityManager.merge(permissionImpl);
//        logger.debug("IPermission object merged: {}", permissionImpl.toString());
//        return permissionImpl;
//    }
//
//    @Override
//    public void delete(Long permissionId) {
//        PermissionImpl permissionImpl = entityManager.find(PermissionImpl.class, permissionId);
//// Never truly delete, just mark as deleted!
//        permissionImpl.setDeleted(true);
//        permissionImpl = entityManager.merge(permissionImpl);
//        logger.debug("IPermission object marked as deleted: {}", permissionImpl.toString());
//    }
//
//    @Override
//    public PermissionImpl find(Long permissionId) {
//        PermissionImpl PermissionImpl = entityManager.find(PermissionImpl.class, permissionId);
//        logger.debug("IPermission object found: {}", PermissionImpl.toString());
//        return PermissionImpl;
//    }
//
//    @Override
//    public List<? extends IPermission> findAll(Class<? extends IPermission> obj, Integer maxResults) {
//        List<PermissionImpl> permissionList = entityManager
//                .createQuery("select t from " + PermissionImpl.class.getSimpleName() + " t", PermissionImpl.class)
//                .getResultList();
//        logger.debug("IPermission objects found: {}", permissionList);
//        return permissionList;
//    }
//
//    @Override
//    public List<? extends IPermission> findByProperty(Class<? extends IPermission> obj, String propertyName, Object propertyValue,
//                                                      Integer maxResults) {
//        TypedQuery<PermissionImpl> query = entityManager.createQuery(
//                "select t from " + PermissionImpl.class.getSimpleName() + " t where t." + propertyName + "= :propertyValue",
//                PermissionImpl.class).setParameter("propertyValue", propertyValue);
//        if (maxResults > 0) {
//            query = query.setMaxResults(maxResults);
//        }
//        List<PermissionImpl> permissionList = query.getResultList();
//        logger.debug("IPermission objects found: {}", permissionList);
//        return permissionList;
//    }
//
//    @Override
//    public List<? extends IPermission> findByProperties(Class<? extends IPermission> obj, Map<String, Object> propertiesMap,
//                                                        List<PropertyOrder> orders, Integer maxResults) {
//        orders = new ArrayList<PropertyOrder>();
//        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<PermissionImpl> criteria = (CriteriaQuery<PermissionImpl>) builder.createQuery(PermissionImpl.class);
//        Root<PermissionImpl> from = (Root<PermissionImpl>) criteria.from(PermissionImpl.class);
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
//                        if(entry.getValue() instanceof List) {
//                            Expression<Object> exp = from.get(entry.getKey());
//                            pred = exp.in(entry.getValue());
//                        }else
//                            pred = builder.equal(from.get(entry.getKey()), entry.getValue());
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
//        List<PermissionImpl> list = null;
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
