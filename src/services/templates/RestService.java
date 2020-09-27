package services.templates;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;

/** Abstract parent for a simple REST service class */
public abstract class RestService {

	@Context
	protected ServletContext ctx;
}
