package util;

import java.net.URI;

import javax.ws.rs.core.Response;

/** Interface with provided default methods for generating Http Response objects.  */
public interface HttpResponseHandler {
	
	/** Returns an empty HTTP 200 response */
	public default Response OK() {
		return Response.ok().build();
	}
	
	/** Returns a status 200 response with the given object as body */
	public default Response OK(Object object) {
		return Response.ok(object).build();
	}
	
	/** Returns a status code 400 response, with an empty body */
	public default Response BadRequest() {
		return Response.status(400).build();
	}
	
	/** Returns a status code 400 response, with the given object as body */
	public default Response BadRequest(Object object) {
		return Response.status(400).entity(object).build();
	}
	
	/** Returns a status code 403 response, with an empty body */
	public default Response ForbiddenRequest() { 
		return Response.status(403).build();
	}
	
	/** Returns a status 412 response with the given object as body */
	public default Response AuthFailed(Object object) {
		return Response.status(412).entity(object).build();
	}
	
	public default Response NotAllowed() {
		return Response.status(405).build();
	}
	
	/** Returns a status 404 response with the given object as body */
	public default Response NotFound(Object object) {
		return Response.status(404).entity(object).build();
	}
	
	/** Redirects user to the given link */
	public default Response Redirect(String link) {
		URI url;
		try {
			url = new URI(link);
			return Response.seeOther(url).build();
		} 
		catch (Exception ex) {
			return Response.status(404).build();
		}
	}
}
