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
 * ��ͬ���� - ����ʵ�ֲ�
 */
public class ConProcessDaoImpl implements ConProcessDao{
	
	/**
	 * �ж��ض�ID�ĺ�ͬ�����Ƿ����
	 * 
	 * @param conId ��ͬID
	 * @return boolean �����򷵻�true
	 * @throws AppException
	 */
	public boolean isExist(int conId) throws AppException{
		boolean flag = false;// �Ƿ�ɹ�_flag
		
		//�������Ӷ������ͽ����
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			// �������ݿ�����
			conn = DBUtil.getConnection();
			// ��ѯ���
			String sql = "select count(id) as n from t_contract_process where con_id = ? and del = 0";
				
			psmt = conn.prepareStatement(sql);// Ԥ�������
			// ��ֵ
			psmt.setInt(1, conId);
			// ִ�в�ѯ���
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
			// �ر����ݿ���������ͷ���Դ
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}
	
	/**
	 * ��Ӻ�ͬ������Ϣ
	 * 
	 * @param  conProcess ��ͬ����ʵ��
	 * @return boolean �ɹ��򷵻�true
	 * @throws AppException
	 */
	public boolean add(ConProcess conProcess)  throws AppException{	
		boolean flag = false;// �Ƿ�ɹ�_flag
		//�������Ӷ������ͽ����
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			// �������ݿ�����
			conn = DBUtil.getConnection();
			// ��ѯ���
			String sql = "insert into t_contract_process(con_id,user_id,type,state,content) values(?,?,?,?,?)";
				
			psmt = conn.prepareStatement(sql);// Ԥ�������
			// ��ֵ
			psmt.setInt(1, conProcess.getConId());
			psmt.setInt(2, conProcess.getUserId());
			psmt.setInt(3, conProcess.getType());
			psmt.setInt(4, conProcess.getState());
			psmt.setString(5, conProcess.getContent());
		
			int result = psmt.executeUpdate();// ִ�����
			
			if(result > 0){
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
			"dao.impl.ConProcessDaoImpl.add error");
		} finally {
			// �ر����ݿ���������ͷ���Դ
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}
	
	/**
	 * ��ѯ���������ĺ�ͬ����
	 * 
	 * @param Contract ��ͬ���̶���
	 * @return ��ͬid�б�
	 * @throws AppException
	 */
	public List<Integer> getConIds(ConProcess conProcess) throws AppException {
		// ��ʼ��
		List<Integer> conIds = new ArrayList<Integer>();
		
		//�������Ӷ������ͽ����
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			// �������ݿ�����
			conn = DBUtil.getConnection();
			// ��ѯ���
			String sql = "select con_id from t_contract_process " +
					"where user_id= ? and type = ? and state = ? and del=0";
				
			psmt = conn.prepareStatement(sql);// Ԥ�������
			// ��ֵ
			psmt.setInt(1, conProcess.getUserId());
			psmt.setInt(2, conProcess.getType());
			psmt.setInt(3, conProcess.getState());
			
			// ִ�в�ѯ���
			rs = psmt.executeQuery();
			
			// ��ý����������list
			while (rs.next()) {
				conIds.add(rs.getInt("con_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
			"dao.impl.ConProcessDaoImpl.getConIds error");
		} finally {
			// �ر����ݿ���������ͷ���Դ
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return conIds;
	}
	
	/**
	 * ���º�ͬ��Ϣ
	 * 
	 * @param  userId �û�ID
	 * @param  conId ��ͬID
	 * @param  type  ��������
	 * @return boolean �ɹ��򷵻�true
	 * @throws AppException
	 */
	public boolean update(ConProcess conProcess) throws AppException {
		boolean flag = false;// �Ƿ�ɹ�_flag
		//�������Ӷ������ͽ����
		Connection conn = null;
		PreparedStatement psmt = null;
		try {
			// �������ݿ�����
			conn = DBUtil.getConnection();
			// ��ѯ���
			String sql = "update t_contract_process set state = ?, content = ?, time = ? " 
					+"where user_id = ? and con_id = ? and type = ?";

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, conProcess.getState());
			psmt.setString(2, conProcess.getContent());

			// ���ڸ�ʽ�� yyyy-MM-dd hh:mm:ss
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
			// �ر����ݿ���������ͷ���Դ
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}
	
	/**
	 * ��ѯ���������ĺ�ͬ����
	 * 
	 * @param con_id ��ͬID
	 * @param type ��������
	 * @param state �����Ľ���״̬
	 * @return ���������ĺ�ͬ����
	 * @throws AppException
	 */
	public int getTotalCount(ConProcess conProcess) throws AppException{
		int totalCount = 0;
		
		//�������Ӷ������ͽ����
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			// �������ݿ�����
			conn = DBUtil.getConnection();
			// ��ѯ���
			String sql = "select count(id) as n from t_contract_process "
				 +"where con_id = ? and type = ? and state = ?";
				
			psmt = conn.prepareStatement(sql);// Ԥ�������
			// ��ֵ 
			psmt.setInt(1, conProcess.getConId());
			psmt.setInt(2, conProcess.getType());
			psmt.setInt(3, conProcess.getState());
			// ִ�в�ѯ��� 
			rs = psmt.executeQuery();
			rs.next();
			totalCount =  rs.getInt("n");  
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
			"dao.impl.ConProcessDaoImpl.getTotalCount error");
		} finally {
			// �ر����ݿ���������ͷ���Դ
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return totalCount;
	}
	/**
	 * ��ѯ���������ĺ�ͬID
	 * 
	 * @param conId ��ͬID
	 * @param type ��������
	 * @param state �����Ľ���״̬
	 * @return ���������ĺ�ͬID
	 * @throws AppException
	 */
	public List<Integer> getIds(int conId, int type, int state) throws AppException {
		List<Integer> ids = new ArrayList<Integer>();
		
		//�������Ӷ������ͽ����
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			// �������ݿ�����
			conn = DBUtil.getConnection();
			// ��ѯ���
			String sql = "select id from t_contract_process " +
					"where con_id= ? and type = ? and state = ? and del=0";
				
			psmt = conn.prepareStatement(sql);// Ԥ�������
			// ��ֵ 
			psmt.setInt(1, conId);
			psmt.setInt(2, type);
			psmt.setInt(3, state);
			
			// ִ�в�ѯ��� 
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				ids.add(rs.getInt("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
			"dao.impl.ConProcessDaoImpl.getIds");
		} finally {
			// �ر����ݿ���������ͷ���Դ
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return ids;
	}
	
	/**
	 * ͨ��ID��ѯ��ͬ����
	 * 
	 * @param id ��ͬID
	 * @return ��ͬ���̶���
	 * @throws AppException
	 */
	public ConProcess getById(int id) throws AppException {
		ConProcess conProcess = null;
		
		//�������Ӷ������ͽ����
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			// �������ݿ�����
			conn = DBUtil.getConnection();
			// ��ѯ���
			String sql = "select id,con_id,user_id,type,state,content,time "
					+"from t_contract_process "
					+"where id = ? and del = 0";

			//Ԥ�������
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
			// �ر����ݿ���������ͷ���Դ
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return conProcess;
	}
}
