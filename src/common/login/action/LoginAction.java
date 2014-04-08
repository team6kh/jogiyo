package common.login.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import user.buyer.dto.BuyerDTO;
import user.seller.dto.SellerDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.Action;

import common.ConDAOAware;

public class LoginAction implements Action, ConDAOAware, SessionAware {
	
	private SqlMapClient sqlMapper;
	
	private String login_type;			// 로그인 타입.
	private String login_id;			// 로그인 아이디.
	private String login_pw;			// 로그인 비밀번호.
	private String actionName;			// 액션 이름.
	
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

	public String execute() throws Exception {
		
		// 로그인 타입이 "구매자"
		if (login_type.equals("buyer")){			
			
			BuyerDTO paramClass = new BuyerDTO();
			BuyerDTO resultClass = new BuyerDTO();
			
			paramClass.setBuyer_id(getLogin_id());
			paramClass.setBuyer_pw(getLogin_pw());

			// 사용자의 비밀번호 가져오기.
			resultClass = (BuyerDTO) sqlMapper.queryForObject("Buyer.selectWhereBuyerPw", paramClass);
			
			// 입력한 비밀번호가 맞으면 세션 설정 후 SUCCESS 리턴.
			if (resultClass != null) {				
				
				sessionMap.put("sessionType", getLogin_type());
				sessionMap.put("sessionId", resultClass.getBuyer_id());
				sessionMap.put("sessionPw", resultClass.getBuyer_pw());
				sessionMap.put("sessionName", resultClass.getBuyer_name());
				
				return SUCCESS;
			}
			
			return ERROR;

		} else if (login_type.equals("seller")){
			
			SellerDTO paramClass = new SellerDTO();
			SellerDTO resultClass = new SellerDTO();
			
			paramClass.setSeller_id(getLogin_id());
			paramClass.setSeller_pw(getLogin_pw());

			// 사용자의 비밀번호 가져오기.
			resultClass = (SellerDTO) sqlMapper.queryForObject("Seller.selectWhereSellerPw", paramClass);
			
			// 입력한 비밀번호가 맞으면 세션 설정 후 SUCCESS 리턴.
			if (resultClass != null) {				
				
				sessionMap.put("sessionType", getLogin_type());
				sessionMap.put("sessionId", resultClass.getSeller_id());
				sessionMap.put("sessionPw", resultClass.getSeller_pw());
				sessionMap.put("sessionName", resultClass.getSeller_name());
				
				return SUCCESS;
			}
			
			return ERROR;
		}
		
		return ERROR;
		
	}
	
	public String getLogin_type() {
		return login_type;
	}

	public void setLogin_type(String login_type) {
		this.login_type = login_type;
	}

	public String getLogin_id() {
		return login_id;
	}

	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}

	public String getLogin_pw() {
		return login_pw;
	}

	public void setLogin_pw(String login_pw) {
		this.login_pw = login_pw;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		if (actionName.equals("")) {
			this.actionName = "home";
		} else {
			this.actionName = actionName;
		}		
	}	

}