package common.registration.action;

import java.util.Calendar;

import user.buyer.dto.BuyerDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import common.ConDAOAware;

public class RegBuyerAction implements Action, Preparable, ModelDriven, ConDAOAware {
	
	private SqlMapClient sqlMapper;
	private BuyerDTO buyerDTO;
	private Calendar today = Calendar.getInstance(); // 오늘 날짜 구하기
	
	// 가입 후 바로 로그인을 위한 변수
	private String reg_type;	// 넘어온 가입 타입
	
	private String login_type;	// 넘길 타입
	private String login_id;	// 넘길 아이디
	private String login_pw;	// 넘길 패스워드
	
	private String actionName;	// 가입 후 보여질 환영 페이지
	
	public void setConDAO(SqlMapClient sqlMapper) {
		this.sqlMapper = sqlMapper;		
	}
	
	public void prepare() throws Exception {
		buyerDTO = new BuyerDTO();
	}	
	
	public Object getModel() {
		return buyerDTO;
	}
	
	public String execute() throws Exception {		
		
		buyerDTO.setBuyer_reg_date(today.getTime());
		buyerDTO.setBuyer_verification("no"); // 가입시 인증값은 no
		
		sqlMapper.insert("Buyer.insertBuyer", buyerDTO);
		
		setLogin_type(getReg_type());
		setLogin_id(buyerDTO.getBuyer_id());
		setLogin_pw(buyerDTO.getBuyer_pw());
		
		//System.out.println("getLogin_type():"+getLogin_type());
		//System.out.println("getLogin_id():"+getLogin_id());
		//System.out.println("getLogin_pw():"+getLogin_pw());
		
		setActionName("welcome"); // 가입 후 보여질 환영 페이지
		
		return SUCCESS;		
	}	

	// 가입 후 바로 로그인을 위한 getter & setter	
	public String getReg_type() {
		return reg_type;
	}

	public void setReg_type(String reg_type) {
		this.reg_type = reg_type;
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
	
	// 가입 후 보여질 환영 페이지
	public String getActionName() {
		return actionName;
	}
	
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

}
