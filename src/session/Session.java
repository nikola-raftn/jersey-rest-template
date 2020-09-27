package session;

import java.util.Calendar;

import beans.Date;
import exceptions.SessionExpiredException;
import exceptions.SessionInvalidException;

/** Session class which identifies a client by their userID */
public class Session {

	public String userID;
	public Date expirationDate;
	private static int session_length_in_minutes = 90;

	public Session() {}
	
	public Session(String userID, Date expirationDate) {
		this.userID = userID;
		this.expirationDate = expirationDate;
	}

	/** Creates a new session for the user with the given ID */
	public Session(String userID) {
		this.userID = userID;
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, session_length_in_minutes);
		this.expirationDate = new Date(calendar);
	}

	/** Return the user ID tied to this session */
	public String getUserID() {
		return this.userID;
	}
	
	/** Returns the expiration date for this session in milliseconds */
	public Long getExpirationDateInTicks() {
		return this.expirationDate.getTicks();
	}
	
	/** Checks whether this session is valid, and whether it has expired
	 * @throws SessionExpiredException If the session expiration date has passed
	 * @throws SessionInvalidException If the session format is invalid
	 */
	public void validate() throws SessionExpiredException, SessionInvalidException {
		if (userID == null)
			throw new SessionInvalidException();
		if (userID.isEmpty())
			throw new SessionInvalidException();
		if (expirationDate == null)
			throw new SessionInvalidException();
		if (expirationDate.isPast())
			throw new SessionExpiredException();
	}
	
	@Override
	public String toString() {
		Calendar c = expirationDate.calendar;
		return userID + ":  " + c.get(Calendar.DATE) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.YEAR) + "  " + c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE);
	}
}
