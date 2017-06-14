package dao;

import java.util.List;

import model.ConProcess;
import utils.AppException;

/**
 * 合同进程 - 数据层
 */
public interface ConProcessDao {

	/**
	 * 判断特定ID的合同进程是否存在
	 * 
	 * @param conId 合同ID
	 * @return boolean 存在则返回true
	 * @throws AppException
	 */
	public boolean isExist(int conId) throws AppException;
	
	/**
	 * 添加合同进程信息
	 * 
	 * @param  conProcess 合同进程实体
	 * @return boolean 成功则返回true
	 * @throws AppException
	 */
	public boolean add(ConProcess conProcess) throws AppException;
	
	/**
	 * 查询符合条件的合同进程
	 * 
	 * @param Contract 合同进程对象
	 * @return 合同id列表
	 * @throws AppException
	 */
	public List<Integer> getConIds(ConProcess conProcess) throws AppException;
	
	/**
	 * 更新合同信息
	 * 
	 * @param  userId 用户ID
	 * @param  conId 合同ID
	 * @param  type  操作类型
	 * @return boolean 成功则返回true
	 * @throws AppException
	 */
	public boolean update(ConProcess conProcess) throws AppException;
	
	/**
	 * 查询符合条件的合同数量
	 * 
	 * @param con_id 合同ID
	 * @param type 操作类型
	 * @param state 操作的进程状态
	 * @return 符合条件的合同数量
	 * @throws AppException
	 */
	public int getTotalCount(ConProcess conProcess) throws AppException;
	
	/**
	 * 查询符合条件的合同ID
	 * 
	 * @param conId 合同ID
	 * @param type 操作类型
	 * @param state 操作的进程状态
	 * @return 符合条件的合同ID
	 * @throws AppException
	 */
	public List<Integer> getIds(int conId, int type, int state) throws AppException;
	
	/**
	 * 通过ID查询合同进程
	 * 
	 * @param id 合同ID
	 * @return 合同进程对象
	 * @throws AppException
	 */
	public ConProcess getById(int id) throws AppException;
}
