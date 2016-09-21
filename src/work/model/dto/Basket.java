package work.model.dto;

import java.io.Serializable;

public class Basket implements Serializable{

	/** ��ٱ��� ��ȣ */
	private int bkId;

	/** ȸ�� ���̵� */
	private String uId;

	/** ��ٱ��Ͽ� ��� �Խ��� ��ȣ */
	private String bId;

	/** ��ٱ��� ���� ��¥ */
	private String bkDate;
	
	/** �⺻ ������ */
	public Basket() {}
	
	/** ������  ������*/
	public Basket(int bkId, String uId, String bId, String bkDate) {
		this.bkId = bkId;
		this.uId = uId;
		this.bId = bId;
		this.bkDate = bkDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Basket [bkId=");
		builder.append(bkId);
		builder.append(", uId=");
		builder.append(uId);
		builder.append(", bId=");
		builder.append(bId);
		builder.append(", bkDate=");
		builder.append(bkDate);
		builder.append("]");
		return builder.toString();
	}

	public int getBkId() {
		return bkId;
	}

	public void setBkId(int bkId) {
		this.bkId = bkId;
	}

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public String getbId() {
		return bId;
	}

	public void setbId(String bId) {
		this.bId = bId;
	}

	public String getBkDate() {
		return bkDate;
	}

	public void setBkDate(String bkDate) {
		this.bkDate = bkDate;
	}
	
	
}
