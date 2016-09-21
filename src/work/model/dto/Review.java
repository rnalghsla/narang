package work.model.dto;

import java.io.Serializable;

public class Review implements Serializable{
	
	/** 후기 번호 */
	private int rId;

	/** 후기 타입  0. 공지사항, 1.밥, 2.공연, 3.게임, 4.운동, 5.영화, 6.문의글*/
	private int rType;

	/** 후기 작성자 아이디 */
	private String uId;
	
	/** 후기 제목*/
	private String rTitle;

	/** 후기 내용 */
	private String rContent;
	
	/** 후기 쓴 날짜*/
	private String rDate;

	/** 후기 이미지 */
	private String rImg;
	
	/** 후기 조회수 */
	private int rHit;

	/** 기본 생성자 */
	public Review() {}
	
	/** 생성자  재정의*/
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
