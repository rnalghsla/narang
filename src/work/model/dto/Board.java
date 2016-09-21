package work.model.dto;

import java.io.Serializable;

public class Board implements Serializable{
	
	/** 게시판 번호 */
	private int bId;

	/** 게시판 탭 이름 1.밥, 2.공연, 3.게임, 4.운동, 5.영화*/
	private int bTab;

	/** 게시판 제목 */
	private String bTitle;

	/** 게시판 작성자 이름  */
	private String uId;

	/** 게시판 내용 */
	private String bContent;

	/** 게시판 모이는 장소  */
	private String bPlace;

	/** 게시판 인원 제한 */
	private int bLimit;
	
	/** 게시판 매칭 시작 시간 */
	private String bStartMatch;
	
	/** 게시판 매칭 끝나는 시간 */
	private String bEndMatch;
	
	/** 게시판 이미지1 */
	private String bImg1;
	
	/** 게시판 이미지2 */
	private String bImg2;
	
	/** 게시판 이미지3 */
	private String bImg3;
	
	/** 게시판 날짜 */
	private String bDate;
	
	/** 게시판 조회수 */
	private int bHit;
	
	/** 기본 생성자 */
	public Board() {}
	
	/** 생성자  재정의*/
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
