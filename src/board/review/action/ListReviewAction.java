package board.review.action;

import board.review.dto.ReviewDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import common.ConDAOAware;

public class ListReviewAction implements Action, Preparable,
		ModelDriven<ReviewDTO>, ConDAOAware {

	// DAO 관련 변수
	SqlMapClient sqlMapper;
	
	// DTO 관련 변수
	private ReviewDTO reviewDTO;

	
	
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	

	public ReviewDTO getModel() {
		return reviewDTO;
	}

	public void prepare() throws Exception {
		reviewDTO = new ReviewDTO();
	}




	public void setConDAO(SqlMapClient sqlMapper) {
		this.sqlMapper = sqlMapper;	
	}

}
