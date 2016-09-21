package work.model.dto;

import java.io.Serializable;

public class Notify implements Serializable{
	
	/** 신고 번호 */
	private int nId;

	/** 신고 한 회원 아이디 */
	private String sendUId;

	/** 신고 당한 회원 아이디 */
	private String recUId;
	
	/** 신고된 테이블 이름 */
	private String tableName;
	
	/** 신고된 테이블 번호 */
	private int tableId;
	
	/** 기본 생성자 */
	public Notify() {}
	
	/** 생성자  재정의*/
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
