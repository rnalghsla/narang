package work.model.dto;

import java.io.Serializable;

public class Matching implements Serializable{

	/** 매칭 번호 */
	private int mId;

	/** 매칭하는 테이블 번호 */
	private int bId;

	/** 매칭한 회원1 */
	private String mUser1;
	
	/** 매칭한 회원2 */
	private String mUser2;
	
	/** 매칭한 회원3 */
	private String mUser3;
	
	/** 매칭한 회원4 */
	private String mUser4;
	
	/** 매칭한 회원5 */
	private String mUser5;
	
	/** 매칭 시작 시간 */
	private String bStartMatch;
	
	/** 매칭 끝나는 시간 */
	private String bEndMatch;

	/** 기본 생성자 */
	public Matching() {}
	
	/** 생성자  재정의*/
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
