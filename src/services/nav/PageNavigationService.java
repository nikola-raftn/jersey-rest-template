package services.nav;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import services.file.HtmlService;
import session.HttpResponseHandler;
import session.SessionTracker;


@Path("/")
public class PageNavigationService implements SessionTracker, HttpResponseHandler {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response getLandingPage(@Context HttpServletRequest request) {
		return OK(HtmlService.getInstance().get("index.html"));
	}
	
	@GET
	@Path("/{pagePath}")
	@Produces(MediaType.TEXT_HTML)
	public Response getSubpage(@PathParam("pagePath") String pagePath, @Context HttpServletRequest request) {
		return OK("Page missing.");
	}
}
