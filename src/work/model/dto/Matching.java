package work.model.dto;

import java.io.Serializable;

public class Matching implements Serializable{

	/** ��Ī ��ȣ */
	private int mId;

	/** ��Ī�ϴ� ���̺� ��ȣ */
	private int bId;

	/** ��Ī�� ȸ��1 */
	private String mUser1;
	
	/** ��Ī�� ȸ��2 */
	private String mUser2;
	
	/** ��Ī�� ȸ��3 */
	private String mUser3;
	
	/** ��Ī�� ȸ��4 */
	private String mUser4;
	
	/** ��Ī�� ȸ��5 */
	private String mUser5;
	
	/** ��Ī ���� �ð� */
	private String bStartMatch;
	
	/** ��Ī ������ �ð� */
	private String bEndMatch;

	/** �⺻ ������ */
	public Matching() {}
	
	/** ������  ������*/
	public Matching(int mId, int bId, String mUser1, String mUser2, String mUser3, String mUser4, String mUser5, String bStartMatch, String bEndMatch) {
		this.mId = mId;
		this.bId = bId;
		this.mUser1 = mUser1;
		this.mUser2 = mUser2;
		this.mUser3 = mUser3;
		this.mUser4 = mUser4;
		this.mUser5 = mUser5;
		this.bStartMatch = bStartMatch;
		this.bEndMatch = bEndMatch;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Matching [mId=");
		builder.append(mId);
		builder.append(", bId=");
		builder.append(bId);
		builder.append(", mUser1=");
		builder.append(mUser1);
		builder.append(", mUser2=");
		builder.append(mUser2);
		builder.append(", mUser3=");
		builder.append(mUser3);
		builder.append(", mUser4=");
		builder.append(mUser4);
		builder.append(", mUser5=");
		builder.append(mUser5);
		builder.append(", bStartMatch=");
		builder.append(bStartMatch);
		builder.append(", bEndMatch=");
		builder.append(bEndMatch);
		builder.append("]");
		return builder.toString();
	}

	public int getmId() {
		return mId;
	}

	public void setmId(int mId) {
		this.mId = mId;
	}

	public int getbId() {
		return bId;
	}

	public void setbId(int bId) {
		this.bId = bId;
	}

	public String getmUser1() {
		return mUser1;
	}

	public void setmUser1(String mUser1) {
		this.mUser1 = mUser1;
	}

	public String getmUser2() {
		return mUser2;
	}

	public void setmUser2(String mUser2) {
		this.mUser2 = mUser2;
	}

	public String getmUser3() {
		return mUser3;
	}

	public void setmUser3(String mUser3) {
		this.mUser3 = mUser3;
	}

	public String getmUser4() {
		return mUser4;
	}

	public void setmUser4(String mUser4) {
		this.mUser4 = mUser4;
	}

	public String getmUser5() {
		return mUser5;
	}

	public void setmUser5(String mUser5) {
		this.mUser5 = mUser5;
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
	
	
}
