package work.model.dto;

import java.io.Serializable;

public class Notify implements Serializable{
	
	/** �Ű� ��ȣ */
	private int nId;

	/** �Ű� �� ȸ�� ���̵� */
	private String sendUId;

	/** �Ű� ���� ȸ�� ���̵� */
	private String recUId;
	
	/** �Ű�� ���̺� �̸� */
	private String tableName;
	
	/** �Ű�� ���̺� ��ȣ */
	private int tableId;
	
	/** �⺻ ������ */
	public Notify() {}
	
	/** ������  ������*/
	public Notify(int nId, String sendUId, String recUId, String tableName, int tableId) {
		this.nId = nId;
		this.sendUId = sendUId;
		this.recUId = recUId;
		this.tableName = tableName;
		this.tableId = tableId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Notify [nId=");
		builder.append(nId);
		builder.append(", sendUId=");
		builder.append(sendUId);
		builder.append(", recUId=");
		builder.append(recUId);
		builder.append(", tableName=");
		builder.append(tableName);
		builder.append(", tableId=");
		builder.append(tableId);
		builder.append("]");
		return builder.toString();
	}

	public int getnId() {
		return nId;
	}

	public void setnId(int nId) {
		this.nId = nId;
	}

	public String getSendUId() {
		return sendUId;
	}

	public void setSendUId(String sendUId) {
		this.sendUId = sendUId;
	}

	public String getRecUId() {
		return recUId;
	}

	public void setRecUId(String recUId) {
		this.recUId = recUId;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public int getTableId() {
		return tableId;
	}

	public void setTableId(int tableId) {
		this.tableId = tableId;
	}
	
	
}
