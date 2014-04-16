package board.review.dto;

import java.util.Date;

public class ReviewDTO {
	
	private int review_num;  // 글번호(PK)
	private int review_rest; // 식당코드 (FK - 식당테이블의 식당 정보) : 임시값 설정 test_Rest
	private String review_writer;  //작성자 (FK - 결제테이블의 주문자 정보) : 임시값 설정 test_Customer 
	private String review_content;  // 글내용
	private String review_file; // 첨부 사진
	private Date review_reg_date; // 작성일
	private String review_rating; // 별점
	
	
	// getter & setter
	public int getReview_num() {
		return review_num;
	}
	public void setReview_num(int review_num) {
		this.review_num = review_num;
	}
	public int getReview_rest() {
		return review_rest;
	}
	public void setReview_rest(int review_rest) {
		this.review_rest = review_rest;
	}
	public String getReview_writer() {
		return review_writer;
	}
	public void setReview_writer(String review_writer) {
		this.review_writer = review_writer;
	}
	public String getReview_content() {
		return review_content;
	}
	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}
	public String getReview_file() {
		return review_file;
	}
	public void setReview_file(String review_file) {
		this.review_file = review_file;
	}
	public Date getReview_reg_date() {
		return review_reg_date;
	}
	public void setReview_reg_date(Date review_reg_date) {
		this.review_reg_date = review_reg_date;
	}
	public String getReview_rating() {
		return review_rating;
	}
	public void setReview_rating(String review_rating) {
		this.review_rating = review_rating;
	}
	
	
	
	

}
