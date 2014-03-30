package login.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import user.dto.UserDTO;
import board.test.action.ConDAO;
import board.test.action.ConDAOAware;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class LoginAction implements Action, Preparable, ModelDriven, ConDAOAware, SessionAware {
	
	UserDTO userDto;
	public static SqlMapClient sqlMapper;
	private ConDAO conDao;
	
	private String userId;
	private String password;
	
	private UserDTO paramClass = new UserDTO(); // 파라미터를 저장할 객체
	private UserDTO resultClass = new UserDTO(); // 쿼리 결과 값을 저장할 객체
	
	public void setConDAO(ConDAO conDao){
		this.conDao = conDao;
	}
	
	private String currentActionName; // 호출한 액션이름을 받아와 다시 그 액션으로 보내기 위한 스트링.
	
	Map sessionMap;
	
	// 폼
	public String loginForm() throws Exception {
		
		return SUCCESS;
	}
	
	public String execute() throws Exception {
		sqlMapper = conDao.getCon();
		
		// 현재 글의 비밀번호 가져오기.
		resultClass = (UserDTO) sqlMapper.queryForObject("User.selectPassword", userDto);

		// 입력한 비밀번호가 틀리면 ERROR 리턴.
		if (resultClass == null) {
			return ERROR;
		}
		
		sessionMap.put("sessionId", userDto.getUserId());
		sessionMap.put("sessionPw", userDto.getPassword());		
		
		return SUCCESS;
	}	
	
	public UserDTO getParamClass() {
		return paramClass;
	}

	public void setParamClass(UserDTO paramClass) {
		this.paramClass = paramClass;
	}

	// Preparable 인터페이스의 prepare
	public void prepare() throws Exception {
		userDto = new UserDTO();		
	}
	
	// ModelDriven 인터페이스의 getModel 구현
	public Object getModel() {
		return userDto;		
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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