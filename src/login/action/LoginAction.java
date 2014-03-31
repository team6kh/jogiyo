package login.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import user.buyer.dto.BuyerDTO;
import board.test.action.ConDAO;
import board.test.action.ConDAOAware;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class LoginAction implements Action, Preparable, ModelDriven, ConDAOAware, SessionAware {
	
	BuyerDTO buyerDto;
	public static SqlMapClient sqlMapper;
	private ConDAO conDao;
	
	private String buyer_id;
	private String buyer_pw;
	
	private BuyerDTO paramClass = new BuyerDTO(); // 파라미터를 저장할 객체
	private BuyerDTO resultClass = new BuyerDTO(); // 쿼리 결과 값을 저장할 객체
	
	public void setConDAO(ConDAO conDao){
		this.conDao = conDao;
	}
	
	private String currentActionName; // 호출한 액션이름을 받아와 다시 그 액션으로 보내기 위한 스트링.
	
	Map sessionMap;
	
	// 로그인 폼 메소드
	public String loginForm() throws Exception {
		
		return SUCCESS;
	}
	
	// Preparable 인터페이스의 prepare
	public void prepare() throws Exception {
		buyerDto = new BuyerDTO();		
	}
	
	// ModelDriven 인터페이스의 getModel 구현
	public Object getModel() {
		return buyerDto;		
	}
	
	public String execute() throws Exception {
		sqlMapper = conDao.getCon();
		
		// 사용자의 비밀번호 가져오기.
		resultClass = (BuyerDTO) sqlMapper.queryForObject("Buyer.selectBuyerPassword", buyerDto);

		// 입력한 비밀번호가 틀리면 ERROR 리턴.
		if (resultClass == null) {
			return ERROR;
		}
				
		sessionMap.put("sessionBuyerId", resultClass.getBuyer_id());
		sessionMap.put("sessionBuyerPw", resultClass.getBuyer_pw());
		sessionMap.put("sessionBuyerName", resultClass.getBuyer_name());	
		
		return SUCCESS;
	}	
	
	public BuyerDTO getResultClass() {
		return resultClass;
	}

	public void setResultClass(BuyerDTO resultClass) {
		this.resultClass = resultClass;
	}

	public BuyerDTO getParamClass() {
		return paramClass;
	}

	public void setParamClass(BuyerDTO paramClass) {
		this.paramClass = paramClass;
	}

	
	public String getBuyer_id() {
		return buyer_id;
	}

	public void setBuyer_id(String buyer_id) {
		this.buyer_id = buyer_id;
	}

	public String getBuyer_pw() {
		return buyer_pw;
	}

	public void setBuyer_pw(String buyer_pw) {
		this.buyer_pw = buyer_pw;
	}

	public String getCurrentActionName() {
		return currentActionName;
	}
	
	public void setCurrentActionName(String currentActionName){
		this.currentActionName = currentActionName;
	}

	public void setSession(Map sessionMap) {
		this.sessionMap = sessionMap;		
	}
	
}