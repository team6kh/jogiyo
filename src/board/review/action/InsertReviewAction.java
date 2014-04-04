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

public class InsertReviewAction implements Action, Preparable,
		ModelDriven<ReviewDTO>, ConDAOAware {

	// DAO 관련 변수
	SqlMapClient sqlMapper;

	// DTO 관련 변수
	private ReviewDTO reviewDTO;
	private Calendar today = Calendar.getInstance();

	// 첨부파일 업로드 관련 변수
	private String fileUploadPath = common.Constants.COMMON_FILE_PATH
			+ common.Constants.REVIEW_FILE_PATH;
	private List<File> review_files = new ArrayList<File>();
	private List<String> review_filesFileName = new ArrayList<String>();
	private List<String> review_filesContentType = new ArrayList<String>();
	private String saveFileName = "";

	// 리뷰 글쓰기 폼
	public String form() throws Exception {
		// 식당, 주문자 정보 값 넘겨줄 필요..?
		return SUCCESS;
	}

	// 리뷰 글쓰기 등록
	public String execute() throws Exception {

		// 작성일에 현재 시간 설정
		reviewDTO.setReview_reg_date(today.getTime());

		// DB 입력처리
		sqlMapper.insert("Review.insertReview", reviewDTO);

		// 첨부파일이 있는 경우
		if (review_files != null) {
			// 반복문을 이용하여 첨부파일 이름 변경
			for (int i = 0; i < review_files.size(); i++) {
				String fileName = review_filesFileName.get(i);
				String fileExt = fileName.substring(
						fileName.lastIndexOf('.') + 1, fileName.length());

				// DB 수정 및 파일 이름 변경을 위해, 해당 레코드의 review_num 값을 가져온다.
				reviewDTO = (ReviewDTO) sqlMapper
						.queryForObject("Review.selectLastNum");
				fileName = "review_" + reviewDTO.getReview_num() + "_" + i; // ex.
																			// review_1(review_num값)_0(
																			// i
																			// 값
																			// )

				// 업로드된 파일들의 이름을 저장한 파일 List로부터 파일 이름들을 차례로 꺼내서 각각 파일을 생성한다.
				File destFile = new File(fileUploadPath + fileName + "."
						+ fileExt);
				// get(i)로 업로드한 파일을 순차적으로 가져와서 destFile객체에 저장한다.
				// 즉 destFile객체에 모든 정보를 복사해준다.
				FileUtils.copyFile(review_files.get(i), destFile);
				// DB에 저장될 파일이름 연결: 구분자 "공백"
				saveFileName += fileName + "." + fileExt + " ";
			}
		}
		// setReview_file 메서드로 값 설정
		reviewDTO.setReview_file(saveFileName);
		// DB update 진행
		sqlMapper.update("Review.updateReviewFile", reviewDTO);

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
