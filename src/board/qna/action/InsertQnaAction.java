package board.qna.action;

import java.util.Calendar;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import board.qna.dto.QnaDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import common.ConDAOAware;

public class InsertQnaAction implements Action, Preparable, ModelDriven, ConDAOAware, SessionAware {

	public static SqlMapClient sqlMapper; 

	QnaDTO paramClass;    
	QnaDTO resultClass;

	private int currentPage;
	private int qna_num;
	Calendar today = Calendar.getInstance();

	Map sessionMap;

	public void setConDAO(SqlMapClient sqlMapper) {
		this.sqlMapper = sqlMapper;
	}

	public void setSession(Map sessionMap) {
		this.sessionMap = sessionMap;
	}


	/**
	 * 등록화면으로 이동
	 * @return
	 * @throws Exception
	 */
	public String insertForm() throws Exception {
		if (sessionMap != null) {
			resultClass = new QnaDTO();			
		//resultClass.setQna_id((String) sessionMap.get("sessionName"));	// 세션이름을 작성자로 설정.
		//resultClass.setQna_pw((String) sessionMap.get("sessionPw"));		// 세션비번을 글비번으로 설정..
		}
		return SUCCESS;
	}


	public void prepare() throws Exception {
		paramClass = new QnaDTO();
	}

	public Object getModel() {
		return paramClass;
	}


	/**
	 * Insert 실행
	 */
	public String execute() throws Exception {
	

		sqlMapper.insert("Qna.insertQnaBoard", paramClass);

		return SUCCESS;
	}

	/**
	 * update 실행
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception{


		sqlMapper.update("Qna.updateQna", paramClass);

		return SUCCESS;
	}
	/**
	 *delete 실행
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception{

		sqlMapper.delete("Qna.deleteQna", paramClass);

		return SUCCESS;
	}

	public QnaDTO getResultClass() {
		return resultClass;
	}

	public void setResultClass(QnaDTO resultClass) {
		this.resultClass = resultClass;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}	

}