package board.qna.action;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.Action;

import common.ConDAOAware;

public class ReadQnaAction implements Action, ConDAOAware{

	@Override
	public void setConDAO(SqlMapClient sqlMapper) {
		// TODO 자동 생성된 메소드 스텁
		
	}

	@Override
	public String execute() throws Exception {
		// TODO 자동 생성된 메소드 스텁
		return null;
	}

}
