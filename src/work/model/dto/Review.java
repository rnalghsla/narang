package work.model.dto;

import java.io.Serializable;

public class Review implements Serializable{
	
	/** �ı� ��ȣ */
	private int rId;

	/** �ı� Ÿ��  0. ��������, 1.��, 2.����, 3.����, 4.�, 5.��ȭ, 6.���Ǳ�*/
	private int rType;

	/** �ı� �ۼ��� ���̵� */
	private String uId;
	
	/** �ı� ����*/
	private String rTitle;

	/** �ı� ���� */
	private String rContent;
	
	/** �ı� �� ��¥*/
	private String rDate;

	/** �ı� �̹��� */
	private String rImg;
	
	/** �ı� ��ȸ�� */
	private int rHit;

	/** �⺻ ������ */
	public Review() {}
	
	/** ������  ������*/
	public Review(int rId, int rType, String uId, String rTitle, String rContent, String rDate, String rImg, int rHit) {
		this.rId = rId;
		this.rType = rType;
		this.uId = uId;
		this.rTitle = rTitle;
		this.rContent = rContent;
		this.rDate = rDate;
		this.rImg = rImg;
		this.rHit = rHit;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Review [rId=");
		builder.append(rId);
		builder.append(", rType=");
		builder.append(rType);
		builder.append(", uId=");
		builder.append(uId);
		builder.append(", rTitle=");
		builder.append(rTitle);
		builder.append(", rContent=");
		builder.append(rContent);
		builder.append(", rDate=");
		builder.append(rDate);
		builder.append(", rImg=");
		builder.append(rImg);
		builder.append(", rHit=");
		builder.append(rHit);
		builder.append("]");
		return builder.toString();
	}

	public int getrId() {
		return rId;
	}

	public void setrId(int rId) {
		this.rId = rId;
	}

	public int getrType() {
		return rType;
	}

	public void setrType(int rType) {
		this.rType = rType;
	}

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public String getrTitle() {
		return rTitle;
	}

	public void setrTitle(String rTitle) {
		this.rTitle = rTitle;
	}

	public String getrContent() {
		return rContent;
	}

	public void setrContent(String rContent) {
		this.rContent = rContent;
	}

	public String getrDate() {
		return rDate;
	}

	public void setrDate(String rDate) {
		this.rDate = rDate;
	}

	public String getrImg() {
		return rImg;
	}

	public void setrImg(String rImg) {
		this.rImg = rImg;
	}

	public int getrHit() {
		return rHit;
	}

	public void setrHit(int rHit) {
		this.rHit = rHit;
	}
	
	
}
