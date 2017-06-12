package dao;

import model.ConState;
import utils.AppException;

/**
 * Contract State Data Access Layer Interface
 */
public interface ConStateDao {

	/**
	 * Add contract operation state information
	 * 
	 * @param  conState Contract state object
	 * @return Return true if successful , otherwise false
	 * @throws AppException
	 */
	public boolean add(ConState conState) throws AppException;
}
