package board.review.action;

import java.io.File;
import java.util.*;

import org.apache.commons.io.FileUtils;

import board.review.dto.ReviewDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import common.ConDAOAware;
import common.Constants;

public class InsertReviewAction implements Action, Preparable,
		ModelDriven<ReviewDTO>, ConDAOAware {

	// DAO 관련 변수
	SqlMapClient sqlMapper;

	// DTO 관련 변수
	private ReviewDTO reviewDTO;
	private Calendar today = Calendar.getInstance();

	// 첨부파일 업로드 관련 변수
	private String fileUploadPath = Constants.COMMON_FILE_PATH
			+ Constants.REVIEW_FILE_PATH;
	private List<File> review_files = new ArrayList<File>();
	private List<String> review_filesFileName = new ArrayList<String>();
	private List<String> review_filesContentType = new ArrayList<String>();
	private String saveFileName = "";

	// 리뷰 글쓰기 폼
	public String form() throws Exception {
		
		
		// 해당회원이 해당 식당에서 구매한 내역이 있는지 없는지 논리값
		
		return SUCCESS;
	}

	// 리뷰 글쓰기 등록
	public String execute() throws Exception {

		// 작성일에 현재 시간 설정
		reviewDTO.setReview_reg_date(today.getTime());

		// DB 입력처리
		sqlMapper.insert("Review.insertReview", reviewDTO);

		// 첨부파일이 있는 경우
		if(review_files != null) {
			
		}
	

		return SUCCESS;
	}
	
	
	

	// 첨부 이미지파일을 위한 getter & setter
	public List<File> getReview_files() {
		return review_files;
	}

	public void setReview_files(List<File> review_files) {
		this.review_files = review_files;
	}

	public List<String> getReview_filesFileName() {
		return review_filesFileName;
	}

	public void setReview_filesFileName(List<String> review_filesFileName) {
		this.review_filesFileName = review_filesFileName;
	}

	public List<String> getReview_filesContentType() {
		return review_filesContentType;
	}

	public void setReview_filesContentType(List<String> review_filesContentType) {
		this.review_filesContentType = review_filesContentType;
	}

	
	
	
	// ConDAOAware 인터페이스
	public void setConDAO(SqlMapClient sqlMapper) {
		this.sqlMapper = sqlMapper;
	}

	// ModelDriven 인터페이스
	public ReviewDTO getModel() {
		return reviewDTO;
	}

	// Preparable 인터페이스
	public void prepare() throws Exception {
		reviewDTO = new ReviewDTO();
	}

}
