package services.templates;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

/** Template service to provide basic CRUD operations with Entity inheriting objects */
public interface CrudRestInterface<T> {

	public Response create(T obj, HttpServletRequest request);
	public Response update(T obj, HttpServletRequest request);
	public Response delete(String key, HttpServletRequest request);
	public Response getByKey(String key, HttpServletRequest request);
	public Response getAll(HttpServletRequest request);
}
