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
	 * @return booleanReturn true if successful , otherwise false
	 * @throws AppException
	 */
	public boolean add(Contract contract) throws AppException;
	
}
