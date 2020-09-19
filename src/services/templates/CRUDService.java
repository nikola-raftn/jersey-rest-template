package services.templates;

import beans.Entity;
import dao.EntityDAO;

/** Template service to provide basic CRUD operations with Entity inheriting objects */
public class CRUDService<T extends Entity, DAO extends EntityDAO<T>> extends BaseRESTService {

	@Override
	protected void onCreate() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setDatabaseAttributeString() {
		// TODO Auto-generated method stub

	}

}
