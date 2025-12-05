package org.jpa;

import java.util.List;
import javax.ejb.Local;

/**
 * Local interface for PatientFacade.
 * 
 * @author MyEclipse Persistence Tools
 */
@Local

public interface PatientFacadeLocal {
	/**
	 * Perform an initial save of a previously unsaved Patient entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method.
	 * 
	 * @param entity
	 *            Patient entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Patient entity);

	/**
	 * Delete a persistent Patient entity.
	 * 
	 * @param entity
	 *            Patient entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Patient entity);

	/**
	 * Persist a previously saved Patient entity and return it or a copy of it
	 * to the sender. A copy of the Patient entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity.
	 * 
	 * @param entity
	 *            Patient entity to update
	 * @return Patient the persisted Patient entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Patient update(Patient entity);

	public Patient findById(Integer id);

	/**
	 * Find all Patient entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Patient property to query
	 * @param value
	 *            the property value to match
	 * @return List<Patient> found by query
	 */
	public List<Patient> findByProperty(String propertyName, Object value);

	public List<Patient> findByName(Object name);

	/**
	 * Find all Patient entities.
	 * 
	 * @return List<Patient> all Patient entities
	 */
	public List<Patient> findAll();
}