package board.review.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import board.review.dto.ReviewDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import common.ConDAOAware;
import common.Constants;

public class UpdateReviewAction implements Action, Preparable, ModelDriven<ReviewDTO>, ConDAOAware {

	// DAO 관련 변수
	SqlMapClient sqlMapper;
	
	private int rest_num;
    private int review_rest_currentPage; 

	// DTO 관련 변수
	private ReviewDTO reviewDTO;

	// 받아야 하는 파라미터
	private int ccp;
    private int review_num;

	// 첨부파일 업로드 관련 변수
	private List<File> review_files = new ArrayList<File>();
	private List<String> review_filesFileName = new ArrayList<String>();
	private List<String> review_filesContentType = new ArrayList<String>();

	
	// 리뷰 글 수정 폼
	public String form() throws Exception {
		reviewDTO = (ReviewDTO) sqlMapper.queryForObject("Review.selectReviewOne", review_num);
		return SUCCESS;
	}

	// 리뷰글 수정 update 처리
	public String execute() throws Exception {
		// 다른 항목 업데이트 처리
		sqlMapper.update("Review.updateReview", reviewDTO);

		// 첨부파일이 있는 경우
		if (!review_files.isEmpty()) {
			// 첨부파일 저장된 경로
			String fileUploadPath = Constants.COMMON_FILE_PATH	+ Constants.REVIEW_FILE_PATH;
			// 파일업로드, 파일삭제 메서드를 이용하기 위해 객체 생성
			FileUpload fileUpload = new FileUpload();
		
			// 기존 업로드된 첨부파일 삭제 시작

			// 첨부파일 삭제를 위해 DB에서 해당 글을 가져옴
			reviewDTO = (ReviewDTO) sqlMapper.queryForObject("Review.selectReviewOne", reviewDTO);
			// 첨부파일명 값을 꺼냄
			String filesName = reviewDTO.getReview_file();
			// 첨부파일 삭제 메서드 호출
			fileUpload.deleteFiles(filesName, fileUploadPath);
			// 기존 업로드된 첨부파일 삭제 종료
			
			
			// 새로 첨부된 파일 업로드 시작
			// 파일명 변경시 공통으로 붙여줄 이름
			String fileRename = "review_" + reviewDTO.getReview_num();
			// 첨부파일 업로드 메서드 호출
			String saveFileName = fileUpload.uploadFiles(review_files, review_filesFileName, fileUploadPath, fileRename);
			System.out.println("saveFileName :" + saveFileName);
			
			
			// setReview_file 메서드로 값 설정
			reviewDTO.setReview_file(saveFileName);
			// DB file update 진행
			sqlMapper.update("Review.updateReviewFile", reviewDTO);

		}

		return SUCCESS;
	}

	// reviewDTO getter & setter
	public ReviewDTO getReviewDTO() {
		return reviewDTO;
	}

	public void setReviewDTO(ReviewDTO reviewDTO) {
		this.reviewDTO = reviewDTO;
	}

	// 첨부파일 관련 getter & setter
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

	// 파라미터 getter & setter
	public int getCcp() {
		return ccp;
	}

	public void setCcp(int ccp) {
		this.ccp = ccp;
	}

	public int getReview_num() {
		return review_num;
	}

	public void setReview_num(int review_num) {
		this.review_num = review_num;
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
	
	//페이지 돌아가기
	public int getRest_num()
    {
        return rest_num;
    }

    public void setRest_num(int rest_num)
    {
        this.rest_num = rest_num;
    }

    public int getReview_rest_currentPage()
    {
        return review_rest_currentPage;
    }

    public void setReview_rest_currentPage(int review_rest_currentPage)
    {
        this.review_rest_currentPage = review_rest_currentPage;
    }

	
}
