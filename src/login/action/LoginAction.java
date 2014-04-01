package login.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import user.buyer.dto.BuyerDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import common.ConDAOAware;

public class LoginAction implements Action, Preparable, ModelDriven,
		ConDAOAware, SessionAware {
	
	public static SqlMapClient sqlMapper;
	
	private BuyerDTO paramClass;	// 파라미터를 저장할 객체
	private BuyerDTO resultClass;	// 쿼리 결과 값을 저장할 객체
	
	private String actionName;
	
	Map sessionMap;
	
	public void setConDAO(SqlMapClient sqlMapper) {
		this.sqlMapper = sqlMapper;
	}
	
	public void setSession(Map sessionMap) {
		this.sessionMap = sessionMap;
	}	

	// 로그인 폼 메소드
	public String loginForm() throws Exception {

		return SUCCESS;
	}

	// Preparable 인터페이스의 prepare
	public void prepare() throws Exception {
		paramClass = new BuyerDTO();
	}

	// ModelDriven 인터페이스의 getModel 구현
	public Object getModel() {
		return paramClass;
	}

	public String execute() throws Exception {
		
		resultClass = new BuyerDTO();

		// 사용자의 비밀번호 가져오기.
		resultClass = (BuyerDTO) sqlMapper.queryForObject(
				"Buyer.selectBuyerPw", paramClass);

		// 입력한 비밀번호가 틀리면 ERROR 리턴.
		if (resultClass == null) {
			return ERROR;
		}

		sessionMap.put("sessionBuyerId", resultClass.getBuyer_id());
		sessionMap.put("sessionBuyerPw", resultClass.getBuyer_pw());
		sessionMap.put("sessionBuyerName", resultClass.getBuyer_name());

		return SUCCESS;
		
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

}