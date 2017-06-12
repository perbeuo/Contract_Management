package dao;

import java.util.List;

import model.ConProcess;
import utils.AppException;

/**
 * Contract Process Data Access Layer Interface
 */
public interface ConProcessDao {

	/**
	 * Determine whether the record of specified contract id is exist in the contract process table
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
	
	/**
	 * Query all contract ids that meet the conditions from contract process table
	 * 
	 * @param Contract process object
	 * @return Contract id set
	 * @throws AppException
	 */
	public List<Integer> getConIds(ConProcess conProcess) throws AppException;
	
	/**
	 * Update status,content and time of contract process according to userId, userId and operation type
	 * 
	 * @param  userId User id
	 * @param  conId Contract id
	 * @param  type  Operation type
	 * @return boolean Return true if successful , otherwise false 
	 * @throws AppException
	 */
	public boolean update(ConProcess conProcess) throws AppException;
	
	/**
	 * Query total number of eligible records from contract process table according to contract id, operation type and its processing state
	 * 
	 * @param con_id Contract id
	 * @param type Operation type
	 * @param state Processing state of Operation type
	 * @return Total number of eligible records
	 * @throws AppException
	 */
	public int getTotalCount(ConProcess conProcess) throws AppException;
	
}
