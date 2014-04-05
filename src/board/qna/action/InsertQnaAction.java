package board.qna.action;

import board.qna.dto.QnaDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import common.ConDAOAware;

public class InsertQnaAction implements Action, Preparable,ModelDriven,ConDAOAware{
	
	public static SqlmapClient sqlMapper;
	
	QnaDTO paramClass;
	QnaDTO resultClass;
	
	private int currentPage;
	private int Qna_num;
	

	public String execute() throws Exception {

		return SUCCESS;
	}

	
	public Object getModel() {
		
		return null;
	}


	public void prepare() throws Exception {
		
		
	}


	@Override
	public void setConDAO(SqlMapClient sqlMapper) {
		// TODO 자동 생성된 메소드 스텁
		
	}

}
