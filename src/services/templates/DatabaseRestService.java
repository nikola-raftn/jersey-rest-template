package services.templates;

import beans.Entity;
import services.validation.DataAccessService;

public abstract class DatabaseRestService<T extends Entity> extends RestService {

	private String databaseAttributeString;
	
	/** Should be used alongside a PostConstruct annotation in order to initialize
	 *  neccessary data for the specific extending servlet.
	 */
	public abstract void onCreate();
	/** Sets the attribute string which is used for accessing data */
	public void setDatabaseAttributeString(String databaseAttributeString) {
		this.databaseAttributeString = databaseAttributeString;
	}
	
	/** Gets the specific DAO object tied to the given database attribute string
	 *  in the ServletContext object 
	 * @return DAO or null, if the DAO has not been set yet
	 */
	@SuppressWarnings("unchecked")
	protected DataAccessService<T> getDAO() {
		return (DataAccessService<T>) ctx.getAttribute(databaseAttributeString);
	}
	
	/** Sets the context attribute related to the specific database attribute string 
	 *  to be the given DAO object.
	 * @param dao
	 */
	protected void setDAO(DataAccessService<T> dao) {
		ctx.setAttribute(databaseAttributeString, dao);
	}
}