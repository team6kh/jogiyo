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
	public static SqlMapClient sqlMapper;

	// 해당 글을 추출해내기 위해 받아야 할 파라미터값(임시값 test_Rest)
	private String review_rest;

	// 추출해낸 리뷰글들을 저장할 변수
	private List<ReviewDTO> reviewRes = new ArrayList<ReviewDTO>();

	// 첨부파일을 위한 변수
	String[] fileName;
	private List<String> file_Path_List = new ArrayList<String>();

	// PagingAction 관련 변수
	private int currentPage = 1; // 현재 페이지
	private int totalCount; // 총 게시물의 수
	private int blockCount = 5; // 한 페이지의 게시물의 수
	private int blockPage = 5; // 한 화면에 보여줄 페이지 수
	private String pagingHtml; // 페이징을 구현한 HTML
	private PagingAction page; // 페이징 클래스
	private String actionName = "listReview";

	public String execute() throws Exception {

		// review_Rest 임시값 설정(나중에 변경)
		review_rest = "test_Rest";

		// 해당식당에 관한 리뷰 글만 가져와 List 타입 인스턴스에 저장
		reviewRes = sqlMapper.queryForList("Review.selectReviewList",
				review_rest);

		// String으로 연결해놓은 파일 경로를 분리하여 List 타입으로 저장하기 위한 코드
		if (reviewRes != null) {
			for (int i = 0; i < reviewRes.size(); i++) {
				// 반복문을 이용하여 순차적으로 review_file 값을 꺼내어 저장
				if (reviewRes.get(i).getReview_file() != null) {
					String saveFileName = reviewRes.get(i).getReview_file();
					// 저장한 첨부파일명을 구분자를 "공백"으로 나누어 배열로 저장
					fileName = saveFileName.split(" ");
					// 배열 타입으로 저장한 파일명을 순차적으로 뽑아 경로와 연결
			
				}
			}
		}

		// 페이징 관련 코드
		totalCount = reviewRes.size();
		page = new PagingAction(actionName, currentPage, totalCount,
				blockCount, blockPage); // pagingAction 객체 생성.
		pagingHtml = page.getPagingHtml().toString(); // 페이지 HTML 생성.

		// 현재 페이지에서 보여줄 마지막 글의 번호 설정.
		int lastCount = totalCount;

		// 현재 페이지의 마지막 글의 번호가 전체의 마지막 글 번호보다 작으면 lastCount를 +1 번호로 설정.
		if (page.getEndCount() < totalCount)
			lastCount = page.getEndCount() + 1;

		// 전체 리스트에서 현재 페이지만큼의 리스트만 가져온다.
		reviewRes = reviewRes.subList(page.getStartCount(), lastCount);

		return SUCCESS;
	}

	// 추출해낸 리뷰글들(List) getter & setter
	public List<ReviewDTO> getReviewRes() {
		return reviewRes;
	}

	public void setReviewRes(List<ReviewDTO> reviewRes) {
		this.reviewRes = reviewRes;
	}

	// review_rest 변수의 getter & setter
	public String getReview_rest() {
		return review_rest;
	}

	public void setReview_rest(String review_rest) {
		this.review_rest = review_rest;
	}

	// 파일 경로 getter & setter

	public List<String> getFile_Path_List() {
		return file_Path_List;
	}

	public void setFile_Path_List(List<String> file_Path_List) {
		this.file_Path_List = file_Path_List;
	}

	// pagingAction 관련 변수 getter & setter

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getBlockCount() {
		return blockCount;
	}

	public void setBlockCount(int blockCount) {
		this.blockCount = blockCount;
	}

	public int getBlockPage() {
		return blockPage;
	}

	public void setBlockPage(int blockPage) {
		this.blockPage = blockPage;
	}

	public String getPagingHtml() {
		return pagingHtml;
	}

	public void setPagingHtml(String pagingHtml) {
		this.pagingHtml = pagingHtml;
	}

	public PagingAction getPage() {
		return page;
	}

	public void setPage(PagingAction page) {
		this.page = page;
	}

	public String getActionName() {
		return actionName;
	}

	// ConDAOAware 인터페이스
	public void setConDAO(SqlMapClient sqlMapper) {
		this.sqlMapper = sqlMapper;
	}

}
