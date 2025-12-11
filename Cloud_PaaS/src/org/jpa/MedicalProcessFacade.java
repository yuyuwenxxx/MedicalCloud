package org.jpa;

import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class MedicalProcessFacade implements MedicalProcessFacadeLocal {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(MedicalProcess entity) {
        LogUtil.log("saving MedicalProcess instance", Level.INFO, null);
        try {
            entityManager.persist(entity);
            LogUtil.log("save successful", Level.INFO, null);
        } catch (RuntimeException re) {
            LogUtil.log("save failed", Level.SEVERE, re);
            throw re;
        }
    }

    @Override
    public void delete(MedicalProcess entity) {
        LogUtil.log("deleting MedicalProcess instance", Level.INFO, null);
        try {
            entity = entityManager.getReference(MedicalProcess.class, entity.getProcessId());
            entityManager.remove(entity);
            LogUtil.log("delete successful", Level.INFO, null);
        } catch (RuntimeException re) {
            LogUtil.log("delete failed", Level.SEVERE, re);
            throw re;
        }
    }

    @Override
    public MedicalProcess update(MedicalProcess entity) {
        LogUtil.log("updating MedicalProcess instance", Level.INFO, null);
        try {
            MedicalProcess result = entityManager.merge(entity);
            LogUtil.log("update successful", Level.INFO, null);
            return result;
        } catch (RuntimeException re) {
            LogUtil.log("update failed", Level.SEVERE, re);
            throw re;
        }
    }

    @Override
    public MedicalProcess findById(Long processId) {
        LogUtil.log("finding MedicalProcess with id: " + processId, Level.INFO, null);
        try {
            MedicalProcess instance = entityManager.find(MedicalProcess.class, processId);
            return instance;
        } catch (RuntimeException re) {
            LogUtil.log("find failed", Level.SEVERE, re);
            throw re;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<MedicalProcess> findByPatientId(Long patientId) {
        LogUtil.log("finding MedicalProcess by patientId: " + patientId, Level.INFO, null);
        try {
            String queryString = "SELECT m FROM MedicalProcess m WHERE m.patientId = :patientId ORDER BY m.createdAt DESC";
            Query query = entityManager.createQuery(queryString);
            query.setParameter("patientId", patientId);
            return query.getResultList();
        } catch (RuntimeException re) {
            LogUtil.log("find by patientId failed", Level.SEVERE, re);
            throw re;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<MedicalProcess> findByDoctorId(Long doctorId) {
        LogUtil.log("finding MedicalProcess by doctorId: " + doctorId, Level.INFO, null);
        try {
            String queryString = "SELECT m FROM MedicalProcess m WHERE m.doctorId = :doctorId ORDER BY m.createdAt DESC";
            Query query = entityManager.createQuery(queryString);
            query.setParameter("doctorId", doctorId);
            return query.getResultList();
        } catch (RuntimeException re) {
            LogUtil.log("find by doctorId failed", Level.SEVERE, re);
            throw re;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<MedicalProcess> findByStatus(String status) {
        LogUtil.log("finding MedicalProcess by status: " + status, Level.INFO, null);
        try {
            String queryString = "SELECT m FROM MedicalProcess m WHERE m.processStatus = :status ORDER BY m.createdAt ASC";
            Query query = entityManager.createQuery(queryString);
            query.setParameter("status", status);
            return query.getResultList();
        } catch (RuntimeException re) {
            LogUtil.log("find by status failed", Level.SEVERE, re);
            throw re;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<MedicalProcess> findRegisteredByDoctor(Long doctorId) {
        LogUtil.log("finding registered patients for doctorId: " + doctorId, Level.INFO, null);
        try {
            String queryString = "SELECT m FROM MedicalProcess m " +
                                "WHERE m.doctorId = :doctorId AND m.processStatus = :status " +
                                "ORDER BY m.createdAt ASC";
            Query query = entityManager.createQuery(queryString);
            query.setParameter("doctorId", doctorId);
            query.setParameter("status", MedicalProcess.STATUS_REGISTERED);
            return query.getResultList();
        } catch (RuntimeException re) {
            LogUtil.log("find registered by doctor failed", Level.SEVERE, re);
            throw re;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<MedicalProcess> findAll() {
        LogUtil.log("finding all MedicalProcess instances", Level.INFO, null);
        try {
            final String queryString = "SELECT m FROM MedicalProcess m ORDER BY m.createdAt DESC";
            Query query = entityManager.createQuery(queryString);
            return query.getResultList();
        } catch (RuntimeException re) {
            LogUtil.log("find all failed", Level.SEVERE, re);
            throw re;
        }
    }

    @Override
    public Long getMaxProcessId() {
        LogUtil.log("getting max processId", Level.INFO, null);
        try {
            String queryString = "SELECT MAX(m.processId) FROM MedicalProcess m";
            Query query = entityManager.createQuery(queryString);
            Long maxId = (Long) query.getSingleResult();
            return maxId != null ? maxId : 0L;
        } catch (RuntimeException re) {
            LogUtil.log("get max processId failed", Level.SEVERE, re);
            throw re;
        }
    }
}