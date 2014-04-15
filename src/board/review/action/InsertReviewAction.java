package board.review.action;

import java.io.File;
import java.util.*;

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
	private List<File> review_files = new ArrayList<File>();
	private List<String> review_filesFileName = new ArrayList<String>();
	private List<String> review_filesContentType = new ArrayList<String>();



	// 리뷰 글쓰기 등록
	public String execute() throws Exception {
	    // 필요한 param : 식당코드, 세션아이디값
	    // 두 param을 이용해 해당회원이 해당 식당에서 구매한 내역이 있는지 없는지 논리값을 구할 것
        

		// 작성일에 현재 시간 설정
		reviewDTO.setReview_reg_date(today.getTime());

		// DB 입력처리
		sqlMapper.insert("Review.insertReview", reviewDTO);

		// 첨부파일이 있는 경우
		if (review_files != null) {

			// 파일 업로드 경로 설정
			String fileUploadPath = Constants.COMMON_FILE_PATH
					+ Constants.REVIEW_FILE_PATH;

			// 파일 이름 변경을 위해, review_num 값을 가져온다.
			reviewDTO = (ReviewDTO) sqlMapper
					.queryForObject("Review.selectLastNum");

			// 파일명 변경 시 공통으로 붙여줄 이름 설정
			String fileRename = "review_" + reviewDTO.getReview_num();

			// 파일업로드를 위해 FileUpload 클래스의 인스턴스 생성
			FileUpload fileUpload = new FileUpload();
			// 파일업로드 메서드 실행
			String saveFileName = fileUpload.uploadFiles(review_files,
					review_filesFileName, fileUploadPath, fileRename);



			// setReview_file 메서드로 값 설정
			reviewDTO.setReview_file(saveFileName);
			// DB update 진행
			sqlMapper.update("Review.updateReviewFile", reviewDTO);
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