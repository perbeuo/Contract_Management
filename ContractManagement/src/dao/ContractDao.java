package dao;

import model.Contract;
import utils.AppException;

/**
 *	Contract Data Access Layer Interface
 */
public interface ContractDao {

	/**
	 * Add contract information
	 * 
	 * @param contract Contract object
	 * @return boolean  Return true if successful , otherwise false
	 * @throws AppException
	 */
	public boolean add(Contract contract) throws AppException;
	
	/**
	 * Query contract information according to contract id
	 * 
	 * @param id Contract id
	 * @return Contract object
	 * @throws AppException
	 */
	public Contract getById(int id) throws AppException;
	
}
