package com.mebitech.persistence.dao;

import com.mebitech.core.api.persistence.dao.IRoleDao;
import com.mebitech.persistence.entities.RoleImpl;

/**
 * Created by tayipdemircan on 26.10.2016.
 */
public class RoleDaoImpl extends ABaseDao<RoleImpl> implements IRoleDao {
    public RoleDaoImpl() {
        super(RoleImpl.class);
    }

    //    private static Logger logger = LoggerFactory.getLogger(RoleDaoImpl.class);
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
//    public void setEntityManager(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }
//
//    @Override
//    public IRole save(IRole role) {
//        RoleImpl roleI = new RoleImpl(role);
//        entityManager.persist(roleI);
//        return roleI;
//    }
//
//    @Override
//    public IRole update(Object role) {
//        RoleImpl roleI = new RoleImpl(role);
//        entityManager.merge(roleI);
//        return roleI;
//    }
//
//    @Override
//    public IRole delete(IRole role) {
//        return null;
//    }
//
//    @Override
//    public void delete(Long id) {
//        RoleImpl role = (RoleImpl) find(id);
//        role.setDeleted(true);
//        entityManager.merge(role);
//    }
//
//    @Override
//    public IRole find(Long id) {
//        RoleImpl role= entityManager.find(RoleImpl.class,id);
//        return role;
//    }
//
//    @Override
//    public List<? extends IRole> findAll(Class<? extends IRole> obj, Integer maxResults) {
//        Map<String, Object> propertyMap = new HashMap<String, Object>();
//        propertyMap.put("deleted",false);
//        List<RoleImpl> userList = (List<RoleImpl>)findByProperties(RoleImpl.class,propertyMap,null,0);
//        logger.debug("IRole objects found: {}", userList);
//        return userList;
//    }
//
//    @Override
//    public List<? extends IRole> findByProperty(Class<? extends IRole> obj, String propertyName, Object value, Integer maxResults) {
//        return null;
//    }
//
//    @Override
//    public List<? extends IRole> findByProperties(Class<? extends IRole> obj, Map<String, Object> propertiesMap, List<PropertyOrder> orders, Integer maxResults) {
//        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<RoleImpl> criteriaQuery = builder.createQuery(RoleImpl.class);
//        Root<RoleImpl> from = criteriaQuery.from(RoleImpl.class);
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

}
