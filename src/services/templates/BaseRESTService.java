package services.templates;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;

/** Abstract parent for a simple REST service class */
public abstract class BaseRESTService {

	@Context
	protected ServletContext ctx;
	
	/** This string is used to identify unique database names accross the server. */
	protected String databaseAttributeString;
	
	/** Should be used with a PostConstruct annotation, to initialize a Service with neccessary attributes. */
	protected abstract void onCreate();
	/** Overriding functions should define the databaseAttributeString here.*/
	protected abstract void setDatabaseAttributeString();
}
