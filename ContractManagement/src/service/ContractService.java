package service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import dao.ConStateDao;
import dao.ContractDao;
import dao.impl.ConStateDaoImpl;
import dao.impl.ContractDaoImpl;
import model.ConState;
import model.Contract;
import utils.AppException;
/*import utils.Constant;*/
import utils.Constant;

/**
 *	Contract business logic class
 */
public class ContractService {
	
	private ContractDao contractDao = null;// Define a contractDao interface object
	private ConStateDao conStateDao = null;// Define a conStateDao interface object

	/**
	 * No-arg constructor method is used to initialize instance in data access layer
	 */
	public ContractService() {
		contractDao = new ContractDaoImpl();
		conStateDao = new ConStateDaoImpl();
	}
	
	/**
	 * Draft contract
	 * 
	 * @param contract 
	 * @return boolean  Return true if successful , otherwise false
	 * @throws AppException
	 */
	public boolean draft(Contract contract) throws AppException {
		boolean flag = false;// Define flag
		
		/*
		 * 1.Call generateConNum() to generate contract number ,and set the number to contract object
		 */ 
		contract.setNum(generateConNum());
		
		try {
			/*
			 * 2.If the contract successfully saved, save the draft contract state in the database
			 */
			if (contractDao.add(contract)) {
				// Instantiate conState object
				ConState conState = new ConState();
				conState.setConId(contract.getId());  // Get contract ID, and set it into conState object
				// Set type of contract status to "STATE_DRAFTED"
				conState.setType(Constant.STATE_DRAFTED);
				// Save contract status information, the operating result is assigned to flag
				flag = conStateDao.add(conState);
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException(
					"service.ContractService.draft");
		}
		return flag;
	}
	
	/**
	 * Generated contract number, the rule is: year month day hour minute second+5 random numbers when drafting contract,
	 * Will generate a unique number stored in the database, but the contract number is not the primary key in the table.
	 */
	private String generateConNum() {
		// Initialize date
		Date date = new Date();
		// Define date format
		SimpleDateFormat sft = new SimpleDateFormat("yyyyMMddhhmmss");
		
		// Generate a number make up by 5 random numbers
		int rd = new Random().nextInt(99999);
		String rand = "00000" + rd;
		rand = rand.substring(rand.length() - 5);
		
		// Generate contract number is current date and time + 5 random numbers
		String contractNum = sft.format(date) + rand;
		return contractNum;
	}

}
