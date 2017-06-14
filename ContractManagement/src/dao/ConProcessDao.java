package dao;

import java.util.List;

import model.ConProcess;
import utils.AppException;

/**
 * ��ͬ���� - ���ݲ�
 */
public interface ConProcessDao {

	/**
	 * �ж��ض�ID�ĺ�ͬ�����Ƿ����
	 * 
	 * @param conId ��ͬID
	 * @return boolean �����򷵻�true
	 * @throws AppException
	 */
	public boolean isExist(int conId) throws AppException;
	
	/**
	 * ��Ӻ�ͬ������Ϣ
	 * 
	 * @param  conProcess ��ͬ����ʵ��
	 * @return boolean �ɹ��򷵻�true
	 * @throws AppException
	 */
	public boolean add(ConProcess conProcess) throws AppException;
	
	/**
	 * ��ѯ���������ĺ�ͬ����
	 * 
	 * @param Contract ��ͬ���̶���
	 * @return ��ͬid�б�
	 * @throws AppException
	 */
	public List<Integer> getConIds(ConProcess conProcess) throws AppException;
	
	/**
	 * ���º�ͬ��Ϣ
	 * 
	 * @param  userId �û�ID
	 * @param  conId ��ͬID
	 * @param  type  ��������
	 * @return boolean �ɹ��򷵻�true
	 * @throws AppException
	 */
	public boolean update(ConProcess conProcess) throws AppException;
	
	/**
	 * ��ѯ���������ĺ�ͬ����
	 * 
	 * @param con_id ��ͬID
	 * @param type ��������
	 * @param state �����Ľ���״̬
	 * @return ���������ĺ�ͬ����
	 * @throws AppException
	 */
	public int getTotalCount(ConProcess conProcess) throws AppException;
	
	/**
	 * ��ѯ���������ĺ�ͬID
	 * 
	 * @param conId ��ͬID
	 * @param type ��������
	 * @param state �����Ľ���״̬
	 * @return ���������ĺ�ͬID
	 * @throws AppException
	 */
	public List<Integer> getIds(int conId, int type, int state) throws AppException;
	
	/**
	 * ͨ��ID��ѯ��ͬ����
	 * 
	 * @param id ��ͬID
	 * @return ��ͬ���̶���
	 * @throws AppException
	 */
	public ConProcess getById(int id) throws AppException;
}
