package board.review.action;

import java.util.ArrayList;
import java.util.List;

import board.review.dto.ReviewDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.Action;

import common.ConDAOAware;
import common.PagingAction;

public class ListReviewAction implements Action, ConDAOAware {

	// DAO 관련 변수
	private SqlMapClient sqlMapper;

	// 해당 글을 추출해내기 위해 받아야 할 파라미터값(임시값 test_Rest)
	private String review_rest;

	// 추출해낸 리뷰글들을 저장할 변수
	private List<ReviewDTO> reviewRes = new ArrayList<ReviewDTO>();

	// 첨부파일을 위한 변수

	private String reviewFile_Path = common.Constants.COMMON_FILE_PATH
			+ common.Constants.REVIEW_FILE_PATH;

	// 페이징 관련 변수
	private int review_currentPage = 1; // 현재 페이지
	private int review_totalCount; // 총 게시물의 수
	private int review_blockCount = 5; // 한 페이지의 게시물의 수
	private int review_blockPage = 5; // 한 화면에 보여줄 페이지 수
	private String review_pagingHtml; // 페이징을 구현한 HTML
	private PagingAction review_page; // 페이징 클래스

	private String actionName = "listReview"; // 페이징액션과 로그인액션에서 쓰인다...

	public String execute() throws Exception {

		// 파일경로 역슬러시 변경
		reviewFile_Path.replace("\\", "/");

		// review_Rest 임시값 설정(나중에 변경)
		review_rest = "test_Rest";

		// 해당식당에 관한 리뷰 글만 가져와 List 타입 인스턴스에 저장
		reviewRes = sqlMapper.queryForList("Review.selectReviewList",
				review_rest);

	
		// 페이징 관련 코드
		review_totalCount = reviewRes.size();
		review_page = new PagingAction(actionName, review_currentPage,
				review_totalCount, review_blockCount, review_blockPage);
		review_pagingHtml = review_page.getPagingHtml().toString();

		// 현재 페이지에서 보여줄 마지막 글의 번호 설정
		int review_lastCount = review_totalCount;

		// 현재 페이지의 마지막 글의 번호가 전체의 마지막 글 번호보다 작으면 lastCount를 +1 번호로 설정.

		if (review_page.getEndCount() < review_totalCount)
			review_lastCount = review_page.getEndCount() + 1;

		// 전체 리스트에서 현재 페이지만큼의 리스트만 가져온다.
		reviewRes = reviewRes.subList(review_page.getStartCount(),
				review_lastCount);

		return SUCCESS;
	}

	// 추출해낸 리뷰글들(List) getter & setter
	public List<ReviewDTO> getReviewRes() {
		return reviewRes;
	}

	public void setReviewRes(List<ReviewDTO> reviewRes) {
		this.reviewRes = reviewRes;
	}

	// 히든값 : review_rest 변수의 getter & setter
	public String getReview_rest() {
		return review_rest;
	}

	public void setReview_rest(String review_rest) {
		this.review_rest = review_rest;
	}

	// 파일 경로 getter
	public String getReviewFile_Path() {
		return reviewFile_Path;
	}

	// 페이징 관련 getter & setter
	public int getReview_currentPage() {
		return review_currentPage;
	}

	public void setReview_currentPage(int review_currentPage) {
		this.review_currentPage = review_currentPage;
	}

	public int getReview_totalCount() {
		return review_totalCount;
	}

	public void setReview_totalCount(int review_totalCount) {
		this.review_totalCount = review_totalCount;
	}

	public int getReview_blockCount() {
		return review_blockCount;
	}

	public void setReview_blockCount(int review_blockCount) {
		this.review_blockCount = review_blockCount;
	}

	public int getReview_blockPage() {
		return review_blockPage;
	}

	public void setReview_blockPage(int review_blockPage) {
		this.review_blockPage = review_blockPage;
	}

	public String getReview_pagingHtml() {
		return review_pagingHtml;
	}

	public void setReview_pagingHtml(String review_pagingHtml) {
		this.review_pagingHtml = review_pagingHtml;
	}

	public PagingAction getReview_page() {
		return review_page;
	}

	public void setReview_page(PagingAction review_page) {
		this.review_page = review_page;
	}

	public String getActionName() {
		return actionName;
	}

	// ConDAOAware 인터페이스
	public void setConDAO(SqlMapClient sqlMapper) {
		this.sqlMapper = sqlMapper;
	}

}
