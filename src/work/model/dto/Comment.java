package work.model.dto;

import java.io.Serializable;

public class Comment implements Serializable {

	/** 댓글 번호 */
	private int cId;

	/** 댓글다는 게시판 번호 */
	private int bId;

	/** 회원 아이디 */
	private String uId;
	
	/** 댓글 내용 */
	private String cContent;

	/** 댓글 쓴 날짜 */
	private String cDate;

	/** 기본 생성자 */
	public Comment() {}
	
	/** 생성자  재정의*/
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
