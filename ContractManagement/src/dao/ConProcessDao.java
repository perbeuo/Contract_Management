package dao;

import model.ConProcess;
import utils.AppException;

/**
 * Contract Process Data Access Layer Interface
 */
public interface ConProcessDao {

	/**
	 * Judgment whether the record of specified contract id is exist in the contract process table
	 * 
	 * @param conId Contract id
	 * @return boolean Return true if exist,otherwise return false
	 * @throws AppException
	 */
	public boolean isExist(int conId) throws AppException;
	
	/**
	 * Add contract operation process information
	 * 
	 * @param  conProcess Contract process object
	 * @return boolean Return true if successful , otherwise false 
	 * @throws AppException
	 */
	public boolean add(ConProcess conProcess) throws AppException;

}
