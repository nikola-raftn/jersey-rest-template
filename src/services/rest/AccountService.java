package services.rest;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.Account;
import dao.EntityDAO;
import exceptions.EntityNotFoundException;
import exceptions.EntityValidationException;
import exceptions.FailedDatabaseOperationException;
import services.templates.CrudRestInterface;
import services.templates.DatabaseRestService;
import services.validation.DataAccessService;
import session.HttpResponseHandler;
import session.SessionTracker;
import storage.BasicStorage;


@Path("/data/accounts")
public class AccountService extends DatabaseRestService<Account> implements CrudRestInterface<Account>, HttpResponseHandler, SessionTracker {

	@PostConstruct
	@Override
	public void onCreate() {
		super.setDatabaseAttributeString("myClass");
		if (super.getDAO() == null)
			super.setDAO(new DataAccessService<Account>(
					new EntityDAO<Account>( 
							new BasicStorage<Account>(Account.class, "Please enter a valid path to the /project/data/accounts.txt file here"))));
																// TODO Provide a custom path to the /project/data/accounts.txt file here
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response create(Account obj, @Context HttpServletRequest request) {
		DataAccessService<Account> dao = super.getDAO();
		
		try {
			obj.setCountable(false); // Default countable value for an Entity is true, so we have to change it here.
			Account createdAccount = dao.create(obj);
			
			return OK(createdAccount);	// HttpResponseHandler provides these shorthand Response methods (OK, ForbiddenRequest, BadRequest)
		} 
		catch (EntityValidationException  e) {
			System.out.println("Account object is not well formed.");
			return BadRequest(e.message);
		}
		catch (FailedDatabaseOperationException e) {
			return BadRequest("Account with this username already exists.");
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response update(Account obj, @Context HttpServletRequest request) {
		DataAccessService<Account> dao = super.getDAO();
		
		try {
			obj.setCountable(false); 
			Account updatedAccount = dao.update(obj);
			
			return OK(updatedAccount);
		}
		catch (EntityValidationException  e) {
			System.out.println("Account object is not well formed.");
			return BadRequest(e.message);
		}
		catch (FailedDatabaseOperationException e) {
			return BadRequest("Account with this username already exists.");
		}
	}

	@DELETE
	@Path("/{accountID}")
	@Override
	public Response delete(@PathParam("accountID") String key, @Context HttpServletRequest request) {
		return ForbiddenRequest();	// We do not allow accounts to be deleted
	}

	@GET
	@Path("/{accountID}")
	@Override
	public Response getByKey(@PathParam("accountID") String key, @Context HttpServletRequest request) {
		DataAccessService<Account> dao = super.getDAO();
		
		try {
			return OK(dao.getByKey(key));
		} 
		catch (EntityNotFoundException e) {
			e.printStackTrace();
			return NotFound(key + " not found");
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response getAll(@Context HttpServletRequest request) {
		DataAccessService<Account> dao = super.getDAO();
		return OK(dao.getAll());
	}
	
	

}
