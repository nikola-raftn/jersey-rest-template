package filters;

import java.util.List;

import beans.Entity;
import dao.EntityDAO;

/** Abstract parent class for data filters */
public abstract class BaseFilter<T extends Entity> {

	private List<T> entities;
	
	/** Instantiates a new filter object with all the entities which the DAO argument object provides with getAll() */
	public BaseFilter(EntityDAO<T> dao) {
		this.entities = dao.getAll();
	}
	
	/** Returns the current state of the filtered entities */
	public List<T> getResults() {
		return entities;
	}
}
