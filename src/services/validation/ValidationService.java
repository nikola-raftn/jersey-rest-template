package services.validation;

import java.util.Collection;

import beans.Entity;
import dao.EntityDAO;
import exceptions.EntityNotFoundException;
import exceptions.EntityValidationException;
import exceptions.FailedDatabaseOperationException;
import wrappers.RequestWrapper;

public class ValidationService<T extends Entity> {
	
	private EntityDAO<T> dao;
	
	public ValidationService(EntityDAO<T> dao) {
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
			throw new FailedDatabaseOperationException("Failed to create object.");
		
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
			throw new FailedDatabaseOperationException("Failed to update object.");
		
		return updatedObj;
	}
	
	/** Deletes an existing Entity.
	 * 
	 * @param obj
	 * @throws EntityValidationException if wrapper is invalid, or if the object with the given key doesn't exist
	 * @throws EntityNotFoundException If no entity being deleted is not found
	 */
	public void delete(RequestWrapper wrapper) throws EntityValidationException, EntityNotFoundException {
		if (wrapper == null)
			throw new EntityValidationException("Wrapper must be non-null.");
		if (wrapper.stringKey == null)
			throw new EntityValidationException("Wrapper string key must be non-null");
		if (wrapper.stringKey.isEmpty())
			throw new EntityValidationException("Wrapper string key must be of non-zero length.");
		
		if (dao.deleteByID(wrapper.stringKey) == null)
			throw new EntityNotFoundException();
	}
	
	/** Returns the object with the key value equal to [stringKey] field from the RequestWrapper
	 * 
	 * @param wrapper
	 * @return
	 * @throws EntityValidationException If the RequestWrapper is invalid
	 * @throws EntityNotFoundException If the entity being searched for is not found
	 */
	public T getByKey(RequestWrapper wrapper) throws EntityValidationException, EntityNotFoundException{
		if (wrapper == null)
			throw new EntityValidationException("Wrapper must be non-null.");
		if (wrapper.stringKey == null)
			throw new EntityValidationException("Wrapper string key must be non-null");
		if (wrapper.stringKey.isEmpty())
			throw new EntityValidationException("Wrapper string key must be of non-zero length.");
		
		T obj = dao.getByKey(wrapper.stringKey);
		if (obj == null)
			throw new EntityNotFoundException();
		else 
			return obj;
	}
	
	public Collection<T> getAll() {
		return dao.getAll();
	}

}
