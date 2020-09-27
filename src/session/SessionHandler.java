package session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/** Interface to provide full range Session handling. Includes
 *  creating, deleting and reading sessions.
 * @author Nikola
 */
public interface SessionHandler extends SessionTracker {
	
	/** Delete the current session */
	public default void deleteSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null)
			session.removeAttribute("session");
	}
	
	/** Attach the SessionToken object to the current session */
	public default void createSession(Session session, HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("session", session);
	}
}

