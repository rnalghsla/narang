package work.model.dto;

import java.io.Serializable;

public class Board implements Serializable{
	
	/** �Խ��� ��ȣ */
	private int bId;

	/** �Խ��� �� �̸� 1.��, 2.����, 3.����, 4.�, 5.��ȭ*/
	private int bTab;

	/** �Խ��� ���� */
	private String bTitle;

	/** �Խ��� �ۼ��� �̸�  */
	private String uId;

	/** �Խ��� ���� */
	private String bContent;

	/** �Խ��� ���̴� ���  */
	private String bPlace;

	/** �Խ��� �ο� ���� */
	private int bLimit;
	
	/** �Խ��� ��Ī ���� �ð� */
	private String bStartMatch;
	
	/** �Խ��� ��Ī ������ �ð� */
	private String bEndMatch;
	
	/** �Խ��� �̹���1 */
	private String bImg1;
	
	/** �Խ��� �̹���2 */
	private String bImg2;
	
	/** �Խ��� �̹���3 */
	private String bImg3;
	
	/** �Խ��� ��¥ */
	private String bDate;
	
	/** �Խ��� ��ȸ�� */
	private int bHit;
	
	/** �⺻ ������ */
	public Board() {}
	
	/** ������  ������*/
	public Board(int bId, int bTab, String bTitle, String uId, String bContent, String bPlace, int bLimit
			, String bStartMatch, String bEndMatch, String bImg1, String bImg2, String bImg3, String bDate, int bHit) {
		this.bId = bId;
		this.bTab = bTab;
		this.bTitle = bTitle;
		this.uId = uId;
		this.bContent = bContent;
		this.bPlace = bPlace;
		this.bLimit = bLimit;
		this.bStartMatch = bStartMatch;
		this.bEndMatch = bEndMatch;
		this.bImg1 = bImg1;
		this.bImg2 = bImg2;
		this.bImg3 = bImg3;
		this.bDate = bDate;
		this.bHit = bHit;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Board [bId=");
		builder.append(bId);
		builder.append(", bTab=");
		builder.append(bTab);
		builder.append(", bTitle=");
		builder.append(bTitle);
		builder.append(", uId=");
		builder.append(uId);
		builder.append(", bContent=");
		builder.append(bContent);
		builder.append(", bPlace=");
		builder.append(bPlace);
		builder.append(", bLimit=");
		builder.append(bLimit);
		builder.append(", bStartMatch=");
		builder.append(bStartMatch);
		builder.append(", bEndMatch=");
		builder.append(bEndMatch);
		builder.append(", bImg1=");
		builder.append(bImg1);
		builder.append(", bImg2=");
		builder.append(bImg2);
		builder.append(", bImg3=");
		builder.append(bImg3);
		builder.append(", bDate=");
		builder.append(bDate);
		builder.append(", bHit=");
		builder.append(bHit);
		builder.append("]");
		return builder.toString();
	}

	public int getbId() {
		return bId;
	}

	public void setbId(int bId) {
		this.bId = bId;
	}

	public int getbTab() {
		return bTab;
	}

	public void setbTab(int bTab) {
		this.bTab = bTab;
	}

	public String getbTitle() {
		return bTitle;
	}

	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public String getbContent() {
		return bContent;
	}

	public void setbContent(String bContent) {
		this.bContent = bContent;
	}

	public String getbPlace() {
		return bPlace;
	}

	public void setbPlace(String bPlace) {
		this.bPlace = bPlace;
	}

	public int getbLimit() {
		return bLimit;
	}

	public void setbLimit(int bLimit) {
		this.bLimit = bLimit;
	}

	public String getbStartMatch() {
		return bStartMatch;
	}

	public void setbStartMatch(String bStartMatch) {
		this.bStartMatch = bStartMatch;
	}

	public String getbEndMatch() {
		return bEndMatch;
	}

	public void setbEndMatch(String bEndMatch) {
		this.bEndMatch = bEndMatch;
	}

	public String getbImg1() {
		return bImg1;
	}

	public void setbImg1(String bImg1) {
		this.bImg1 = bImg1;
	}

	public String getbImg2() {
		return bImg2;
	}

	public void setbImg2(String bImg2) {
		this.bImg2 = bImg2;
	}

	public String getbImg3() {
		return bImg3;
	}

	public void setbImg3(String bImg3) {
		this.bImg3 = bImg3;
	}

	public String getbDate() {
		return bDate;
	}

	public void setbDate(String bDate) {
		this.bDate = bDate;
	}

	public int getbHit() {
		return bHit;
	}

	public void setbHit(int bHit) {
		this.bHit = bHit;
	}


	
}
