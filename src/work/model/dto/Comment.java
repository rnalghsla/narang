package work.model.dto;

import java.io.Serializable;

public class Comment implements Serializable {

	/** ��� ��ȣ */
	private int cId;

	/** ��۴ٴ� �Խ��� ��ȣ */
	private int bId;

	/** ȸ�� ���̵� */
	private String uId;
	
	/** ��� ���� */
	private String cContent;

	/** ��� �� ��¥ */
	private String cDate;

	/** �⺻ ������ */
	public Comment() {}
	
	/** ������  ������*/
	public Comment(int cId, int bId, String uId, String cContent, String cDate) {
		this.cId = cId;
		this.bId = bId;
		this.uId = uId;
		this.cContent = cContent;
		this.cDate = cDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Comment [cId=");
		builder.append(cId);
		builder.append(", bId=");
		builder.append(bId);
		builder.append(", uId=");
		builder.append(uId);
		builder.append(", cContent=");
		builder.append(cContent);
		builder.append(", cDate=");
		builder.append(cDate);
		builder.append("]");
		return builder.toString();
	}

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public int getbId() {
		return bId;
	}

	public void setbId(int bId) {
		this.bId = bId;
	}

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public String getcContent() {
		return cContent;
	}

	public void setcContent(String cContent) {
		this.cContent = cContent;
	}

	public String getcDate() {
		return cDate;
	}

	public void setcDate(String cDate) {
		this.cDate = cDate;
	}
	
}
