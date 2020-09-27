# Small library for creating web apps using Jersey JAX-RS implementation. 

### Master branch is the template itself. 
### Example with a demo Account class is on the Example branch. Should be tested with Postman or a similar service.
___
## How-to for a simple Account Rest Service (as shown in the Example branch)
* Create a new class ```Account``` that extends ```Entity```
    * Optionally implement the ```validate()``` method, while throwing an ```EntityValidationException``` where needed. (Note that ```validate()``` method is used by the ```DataAccessService``` to check whether the object is valid.)
* Create a new class ```AccountService ```
    * Annotate with ```@Path("/something")```
    * Extend DatabaseRestService
    * Implement CrudRestInterface (to avoid handwriting all the CRUD methods)
    * Annotate the onCreate() method with ```@PostConstruct```
    * Add the following code to ```onCreate()```
        * ```
            @PostConstruct
	        @Override
	        public void onCreate() {
		    super.setDatabaseAttributeString("accounts");
		    if (super.getDAO() == null)
			super.setDAO(new DataAccessService<Account>(
					new EntityDAO<Account>( 
							new BasicStorage<Account>(Account.class, "Please enter a valid path to the /project/data/accounts.txt file here"))));
	        }```
    * Provide annotations for the CRUD methods (@GET, @POST, @PUT, @Consumes, @Produces, etc.)
    * Optionally, you can implement ```HttpResponseHandler``` in order to use the shorthand ```Response``` methods such as
        * ```OK(Object object)```
        * ```BadRequest()```
        * ```ForbiddenRequest()```
        * ```NotFound()```

        * You can wrap objects into the ```OK()``` and ```BadRequest()``` methods, if you wish to return them back to the client (you can wrap any object, be it a String, or an Account).
        ```
            @GET
            @Consumes(MediaType.APPLICATION_JSON)
            @Produces(MediaType.APPLICATION_JSON)
            @Override
            public Response create(Account account) {
                try {
                    Account createdAccount = super.getDAO().create(account);
                    return OK(createdAccount);
                }
                catch (EntityValidationException | FailedDatabaseOperation e) {
                    return BadRequest("Failed to create new account");
                }
            }
        ```
