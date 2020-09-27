package services.validation;

import java.util.Collection;

import beans.Entity;
import dao.EntityDAO;
import exceptions.EntityNotFoundException;
import exceptions.EntityValidationException;
import exceptions.FailedDatabaseOperationException;

public class DataAccessService<T extends Entity> {
	
	protected EntityDAO<T> dao;
	
	public DataAccessService(EntityDAO<T> dao) {
		this.dao = dao;
	}
	
	/** Creates an Entity, while validating its value (non-null and valid fields) beforehand
	 * 
	 * @param obj
	 * @return created entity
	 * @throws EntityValidationException if entity is null, or fails validation
	 * @throws FailedDatabaseOperationException if the creation fails because the object already exists
	 */
	public T create(T obj) throws EntityValidationException, FailedDatabaseOperationException {
		if (obj == null)
			throw new EntityValidationException("Object must be non-null");
		obj.validate();
		
		T createdObj = dao.create(obj);
		if (createdObj == null)
			throw new FailedDatabaseOperationException();
		
		return createdObj;
	}
	
	/** Updates an existing Entity, while validating its value (non-null and valid fields) beforehand
	 * 
	 * @param obj
	 * @return updated entity
	 * @throws EntityValidationException if entity is null, or fails validation
	 * @throws FailedDatabaseOperationException if the update fails because the object doesn't exist
	 */
	public T update(T obj) throws EntityValidationException, FailedDatabaseOperationException {
		if (obj == null)
			throw new EntityValidationException("Object must be non-null");
		obj.validate();
		
		T updatedObj = dao.update(obj);
		if (updatedObj == null)
			throw new FailedDatabaseOperationException();
		
		return updatedObj;
	}
	
	/** Deletes an existing Entity with the given key.
	 * 
	 * @param obj
	 * @throws EntityNotFoundException If entity being deleted is not found
	 */
	public void delete(String key) throws EntityNotFoundException {
		if (key == null)
			throw new IllegalArgumentException();
		
		if (dao.deleteByID(key) == null)
			throw new EntityNotFoundException();
	}
	
	public T getByKey(String key) throws EntityNotFoundException{
		if (key == null)
			throw new EntityNotFoundException();
		
		T obj = dao.getByKey(key);
		if (obj == null)
			throw new EntityNotFoundException();
		
		return obj;
	}
	
	public Collection<T> getAll() {
		return dao.getAll();
	}

}
