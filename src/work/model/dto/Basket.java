package work.model.dto;

import java.io.Serializable;

public class Basket implements Serializable{

	/** 장바구니 번호 */
	private int bkId;

	/** 회원 아이디 */
	private String uId;

	/** 장바구니에 담길 게시판 번호 */
	private String bId;

	/** 장바구니 담은 날짜 */
	private String bkDate;
	
	/** 기본 생성자 */
	public Basket() {}
	
	/** 생성자  재정의*/
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
