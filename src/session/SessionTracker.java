package session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import exceptions.SessionExpiredException;
import exceptions.SessionInvalidException;

/** Interface to provide basic session tracking for users. 
 *  Allows identification of the current user via the HttpServletRequest argument. */
public interface SessionTracker {
	
	/** Return the SessionToken object from the current user session 
	 * @param request
	 * @return Current session if valid, or null if session is missing, or incorrect JWT format.
	 */
	public default Session getCurrentSession(HttpServletRequest request) {
		HttpSession httpSession = request.getSession(false);

		if (httpSession == null) {
			return null;
		}
		
		Session session = (Session)httpSession.getAttribute("session");
		if (session == null) {
			return null;
		}
		
		try {
			session.validate();
			return session;
		} 
		catch (SessionExpiredException e) {
			System.out.println("Previous session has expired");
			return null;
		}
		catch(SessionInvalidException e) {
			System.out.println("Session invalid");
			return null;
		}
		catch (Exception e) {
			return null;
		}
	}
}