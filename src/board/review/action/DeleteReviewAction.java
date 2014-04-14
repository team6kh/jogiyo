package board.review.action;

import board.review.dto.ReviewDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.Action;

import common.ConDAOAware;
import common.Constants;

public class DeleteReviewAction implements Action, ConDAOAware {

	// DAO 관련 변수
	private SqlMapClient sqlMapper;

	// DTO 관련 변수
	private ReviewDTO reviewDTO;

	// param
	private int ccp;
	private int review_num;

	public String form() throws Exception {

		return SUCCESS;
	}

	public String execute() throws Exception {

		// 첨부파일 삭제를 위해 DB에서 해당 글을 가져옴
		reviewDTO = (ReviewDTO) sqlMapper.queryForObject(
				"Review.selectReviewOne", review_num);

		// 첨부파일명 값을 꺼냄
		String filesName = reviewDTO.getReview_file();

		// 첨부파일이 있으면 첨부파일 삭제 진행
		if (filesName != null) {
			
			// 첨부파일 저장된 경로
			String fileUploadPath = Constants.COMMON_FILE_PATH
					+ Constants.REVIEW_FILE_PATH;
			// 파일업로드, 파일삭제 메서드를 이용하기 위해 객체 생성
			FileUpload fileUpload = new FileUpload();
			// 첨부파일 삭제 메서드 호출
			fileUpload.deleteFiles(filesName, fileUploadPath);

		}
	

		// DB에서 레코드 삭제
		sqlMapper.delete("Review.deleteReview", reviewDTO);

		return SUCCESS;
	}

	// parameter getter & setter

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

}
