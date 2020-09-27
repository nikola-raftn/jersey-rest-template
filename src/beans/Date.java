package beans;

import java.util.Calendar;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import util.DateDeserializer;

@JsonDeserialize(using = DateDeserializer.class)
public class Date {
	
	public Calendar calendar;
	
	public Date(Long ticks) {
		calendar = Calendar.getInstance();
		calendar.setTimeInMillis(ticks);
	}
	
	public Date(Calendar calendar) {
		this.calendar = calendar;
	}
	
	public Date(Date date) {
		this.calendar = Calendar.getInstance();
		this.calendar.setTimeInMillis(date.calendar.getTimeInMillis());
	}
	
	public static Date now() {
		return new Date(Calendar.getInstance());
	}
	
	/** Check whether this date is in the future 
	 * @return true if date is in the future, false if it's in the past
	 */
	public boolean isFuture() {
		return (this.calendar.compareTo(Calendar.getInstance()) > 0);
	}
	
	public boolean isPast() {
		return (this.calendar.compareTo(Calendar.getInstance()) < 0);
	}
	
	public Long getTicks() {
		return this.calendar.getTimeInMillis();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj.getClass() != Date.class)
			return false;
		
		Date otherDate = (Date)obj;
		return this.calendar.get(Calendar.YEAR) == otherDate.calendar.get(Calendar.YEAR)  
					&& this.calendar.get(Calendar.DAY_OF_YEAR) == otherDate.calendar.get(Calendar.DAY_OF_YEAR);
	}
}
