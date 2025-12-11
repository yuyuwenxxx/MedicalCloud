package org.jpa;

import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class ProcessNodeFacade implements ProcessNodeFacadeLocal {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(ProcessNode entity) {
        LogUtil.log("saving ProcessNode instance", Level.INFO, null);
        try {
            entityManager.persist(entity);
            LogUtil.log("save successful", Level.INFO, null);
        } catch (RuntimeException re) {
            LogUtil.log("save failed", Level.SEVERE, re);
            throw re;
        }
    }

    @Override
    public void delete(ProcessNode entity) {
        LogUtil.log("deleting ProcessNode instance", Level.INFO, null);
        try {
            entity = entityManager.getReference(ProcessNode.class, 
                new ProcessNodeId(entity.getNodeId(), entity.getProcessId()));
            entityManager.remove(entity);
            LogUtil.log("delete successful", Level.INFO, null);
        } catch (RuntimeException re) {
            LogUtil.log("delete failed", Level.SEVERE, re);
            throw re;
        }
    }

    @Override
    public ProcessNode update(ProcessNode entity) {
        LogUtil.log("updating ProcessNode instance", Level.INFO, null);
        try {
            ProcessNode result = entityManager.merge(entity);
            LogUtil.log("update successful", Level.INFO, null);
            return result;
        } catch (RuntimeException re) {
            LogUtil.log("update failed", Level.SEVERE, re);
            throw re;
        }
    }

    @Override
    public ProcessNode findById(Long nodeId, Long processId) {
        LogUtil.log("finding ProcessNode with nodeId: " + nodeId + ", processId: " + processId, Level.INFO, null);
        try {
            ProcessNodeId id = new ProcessNodeId(nodeId, processId);
            ProcessNode instance = entityManager.find(ProcessNode.class, id);
            return instance;
        } catch (RuntimeException re) {
            LogUtil.log("find failed", Level.SEVERE, re);
            throw re;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ProcessNode> findByProcessId(Long processId) {
        LogUtil.log("finding ProcessNode by processId: " + processId, Level.INFO, null);
        try {
            String queryString = "SELECT p FROM ProcessNode p WHERE p.processId = :processId ORDER BY p.nodeId";
            Query query = entityManager.createQuery(queryString);
            query.setParameter("processId", processId);
            return query.getResultList();
        } catch (RuntimeException re) {
            LogUtil.log("find by processId failed", Level.SEVERE, re);
            throw re;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ProcessNode> findAll() {
        LogUtil.log("finding all ProcessNode instances", Level.INFO, null);
        try {
            final String queryString = "SELECT p FROM ProcessNode p ORDER BY p.createAt DESC";
            Query query = entityManager.createQuery(queryString);
            return query.getResultList();
        } catch (RuntimeException re) {
            LogUtil.log("find all failed", Level.SEVERE, re);
            throw re;
        }
    }

    @Override
    public Long getMaxNodeId(Long processId) {
        LogUtil.log("getting max nodeId for processId: " + processId, Level.INFO, null);
        try {
            String queryString = "SELECT MAX(p.nodeId) FROM ProcessNode p WHERE p.processId = :processId";
            Query query = entityManager.createQuery(queryString);
            query.setParameter("processId", processId);
            Long maxId = (Long) query.getSingleResult();
            return maxId != null ? maxId : 0L;
        } catch (RuntimeException re) {
            LogUtil.log("get max nodeId failed", Level.SEVERE, re);
            throw re;
        }
    }

    @Override
    public ProcessNode findCurrentNode(Long processId) {
        LogUtil.log("finding current node for processId: " + processId, Level.INFO, null);
        try {
            String queryString = "SELECT p FROM ProcessNode p WHERE p.processId = :processId AND p.isCurrent = true";
            Query query = entityManager.createQuery(queryString);
            query.setParameter("processId", processId);
            List<?> results = query.getResultList();
            return results.isEmpty() ? null : (ProcessNode) results.get(0);
        } catch (RuntimeException re) {
            LogUtil.log("find current node failed", Level.SEVERE, re);
            throw re;
        }
    }

    @Override
    public ProcessNode findLatestNode(Long processId) {
        LogUtil.log("finding latest node for processId: " + processId, Level.INFO, null);
        try {
            String queryString = "SELECT p FROM ProcessNode p WHERE p.processId = :processId AND p.isLatest = true";
            Query query = entityManager.createQuery(queryString);
            query.setParameter("processId", processId);
            List<?> results = query.getResultList();
            return results.isEmpty() ? null : (ProcessNode) results.get(0);
        } catch (RuntimeException re) {
            LogUtil.log("find latest node failed", Level.SEVERE, re);
            throw re;
        }
    }
}