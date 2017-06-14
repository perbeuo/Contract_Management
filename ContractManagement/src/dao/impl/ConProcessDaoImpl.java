package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import dao.ConProcessDao;
import model.ConProcess;
import utils.AppException;
import utils.DBUtil;

/**
 * 合同进程 - 数据实现层
 */
public class ConProcessDaoImpl implements ConProcessDao{
	
	/**
	 * 判断特定ID的合同进程是否存在
	 * 
	 * @param conId 合同ID
	 * @return boolean 存在则返回true
	 * @throws AppException
	 */
	public boolean isExist(int conId) throws AppException{
		boolean flag = false;// 是否成功_flag
		
		//声明连接对象、语句和结果集
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			// 建立数据库连接
			conn = DBUtil.getConnection();
			// 查询语句
			String sql = "select count(id) as n from t_contract_process where con_id = ? and del = 0";
				
			psmt = conn.prepareStatement(sql);// 预编译语句
			// 赋值
			psmt.setInt(1, conId);
			// 执行查询语句
			rs = psmt.executeQuery();
			rs.next();
			int n =  rs.getInt("n"); 
			if (n > 0) {
				flag = true; 
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
			"dao.impl.ConProcessDaoImpl.isExist error");
		} finally {
			// 关闭数据库操作对象，释放资源
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}
	
	/**
	 * 添加合同进程信息
	 * 
	 * @param  conProcess 合同进程实体
	 * @return boolean 成功则返回true
	 * @throws AppException
	 */
	public boolean add(ConProcess conProcess)  throws AppException{	
		boolean flag = false;// 是否成功_flag
		//声明连接对象、语句和结果集
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			// 建立数据库连接
			conn = DBUtil.getConnection();
			// 查询语句
			String sql = "insert into t_contract_process(con_id,user_id,type,state,content) values(?,?,?,?,?)";
				
			psmt = conn.prepareStatement(sql);// 预编译语句
			// 赋值
			psmt.setInt(1, conProcess.getConId());
			psmt.setInt(2, conProcess.getUserId());
			psmt.setInt(3, conProcess.getType());
			psmt.setInt(4, conProcess.getState());
			psmt.setString(5, conProcess.getContent());
		
			int result = psmt.executeUpdate();// 执行语句
			
			if(result > 0){
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
			"dao.impl.ConProcessDaoImpl.add error");
		} finally {
			// 关闭数据库操作对象，释放资源
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}
	
	/**
	 * 查询符合条件的合同进程
	 * 
	 * @param Contract 合同进程对象
	 * @return 合同id列表
	 * @throws AppException
	 */
	public List<Integer> getConIds(ConProcess conProcess) throws AppException {
		// 初始化
		List<Integer> conIds = new ArrayList<Integer>();
		
		//声明连接对象、语句和结果集
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			// 建立数据库连接
			conn = DBUtil.getConnection();
			// 查询语句
			String sql = "select con_id from t_contract_process " +
					"where user_id= ? and type = ? and state = ? and del=0";
				
			psmt = conn.prepareStatement(sql);// 预编译语句
			// 赋值
			psmt.setInt(1, conProcess.getUserId());
			psmt.setInt(2, conProcess.getType());
			psmt.setInt(3, conProcess.getState());
			
			// 执行查询语句
			rs = psmt.executeQuery();
			
			// 获得结果集，放入list
			while (rs.next()) {
				conIds.add(rs.getInt("con_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
			"dao.impl.ConProcessDaoImpl.getConIds error");
		} finally {
			// 关闭数据库操作对象，释放资源
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return conIds;
	}
	
	/**
	 * 更新合同信息
	 * 
	 * @param  userId 用户ID
	 * @param  conId 合同ID
	 * @param  type  操作类型
	 * @return boolean 成功则返回true
	 * @throws AppException
	 */
	public boolean update(ConProcess conProcess) throws AppException {
		boolean flag = false;// 是否成功_flag
		//声明连接对象、语句和结果集
		Connection conn = null;
		PreparedStatement psmt = null;
		try {
			// 建立数据库连接
			conn = DBUtil.getConnection();
			// 查询语句
			String sql = "update t_contract_process set state = ?, content = ?, time = ? " 
					+"where user_id = ? and con_id = ? and type = ?";

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, conProcess.getState());
			psmt.setString(2, conProcess.getContent());

			// 日期格式： yyyy-MM-dd hh:mm:ss
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			java.sql.Timestamp time = new java.sql.Timestamp(conProcess.getTime().getTime());
			df.format(time);
			psmt.setTimestamp(3, time);
			psmt.setInt(4, conProcess.getUserId());
			psmt.setInt(5, conProcess.getConId());
			psmt.setInt(6, conProcess.getType());
			int count = psmt.executeUpdate();
			
			if (count > 0) {
				flag = true;
			}
		}catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.impl.ConProcessDaoImpl.update error");
		} finally {
			// 关闭数据库操作对象，释放资源
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}
	
	/**
	 * 查询符合条件的合同数量
	 * 
	 * @param con_id 合同ID
	 * @param type 操作类型
	 * @param state 操作的进程状态
	 * @return 符合条件的合同数量
	 * @throws AppException
	 */
	public int getTotalCount(ConProcess conProcess) throws AppException{
		int totalCount = 0;
		
		//声明连接对象、语句和结果集
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			// 建立数据库连接
			conn = DBUtil.getConnection();
			// 查询语句
			String sql = "select count(id) as n from t_contract_process "
				 +"where con_id = ? and type = ? and state = ?";
				
			psmt = conn.prepareStatement(sql);// 预编译语句
			// 赋值 
			psmt.setInt(1, conProcess.getConId());
			psmt.setInt(2, conProcess.getType());
			psmt.setInt(3, conProcess.getState());
			// 执行查询语句 
			rs = psmt.executeQuery();
			rs.next();
			totalCount =  rs.getInt("n");  
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
			"dao.impl.ConProcessDaoImpl.getTotalCount error");
		} finally {
			// 关闭数据库操作对象，释放资源
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return totalCount;
	}
	/**
	 * 查询符合条件的合同ID
	 * 
	 * @param conId 合同ID
	 * @param type 操作类型
	 * @param state 操作的进程状态
	 * @return 符合条件的合同ID
	 * @throws AppException
	 */
	public List<Integer> getIds(int conId, int type, int state) throws AppException {
		List<Integer> ids = new ArrayList<Integer>();
		
		//声明连接对象、语句和结果集
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			// 建立数据库连接
			conn = DBUtil.getConnection();
			// 查询语句
			String sql = "select id from t_contract_process " +
					"where con_id= ? and type = ? and state = ? and del=0";
				
			psmt = conn.prepareStatement(sql);// 预编译语句
			// 赋值 
			psmt.setInt(1, conId);
			psmt.setInt(2, type);
			psmt.setInt(3, state);
			
			// 执行查询语句 
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				ids.add(rs.getInt("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
			"dao.impl.ConProcessDaoImpl.getIds");
		} finally {
			// 关闭数据库操作对象，释放资源
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return ids;
	}
	
	/**
	 * 通过ID查询合同进程
	 * 
	 * @param id 合同ID
	 * @return 合同进程对象
	 * @throws AppException
	 */
	public ConProcess getById(int id) throws AppException {
		ConProcess conProcess = null;
		
		//声明连接对象、语句和结果集
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			// 建立数据库连接
			conn = DBUtil.getConnection();
			// 查询语句
			String sql = "select id,con_id,user_id,type,state,content,time "
					+"from t_contract_process "
					+"where id = ? and del = 0";

			//预编译语句
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, id); //Set contract id
			
			rs = psmt.executeQuery();

			if(rs.next()) {
				conProcess = new ConProcess();
				conProcess.setId(rs.getInt("id"));
				conProcess.setConId(rs.getInt("con_id"));
				conProcess.setUserId(rs.getInt("user_id"));
				conProcess.setType(rs.getInt("type"));
				conProcess.setState(rs.getInt("state"));
				conProcess.setContent(rs.getString("content"));
				conProcess.setTime(rs.getTimestamp("time"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"dao.impl.ConProcessDaoImpl.getById");
		} finally {
			// 关闭数据库操作对象，释放资源
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return conProcess;
	}
}
