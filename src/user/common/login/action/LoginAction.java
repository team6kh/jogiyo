package user.common.login.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import user.buyer.dto.BuyerDTO;
import user.seller.dto.SellerDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.Action;

import common.ConDAOAware;

public class LoginAction implements Action, ConDAOAware, SessionAware
{
    private SqlMapClient sqlMapper;

    private String login_type; // 로그인 타입
    private String login_id; // 로그인 아이디
    private String login_pw; // 로그인 비밀번호
    private String actionName; // 액션 이름
    private String queryString; // queryString

    private Map sessionMap;

    public void setConDAO(SqlMapClient sqlMapper)
    {
        this.sqlMapper = sqlMapper;
    }

    public void setSession(Map sessionMap)
    {
        this.sessionMap = sessionMap;
    }

    // 로그인 폼 메소드
    public String loginForm() throws Exception
    {
        return SUCCESS;
    }

    public String execute() throws Exception
    {
        //System.out.println("LoginAction");
        //System.out.println("getActionName:"+getActionName());
    	//System.out.println("getQueryString:"+getQueryString());

        // 로그인을 실행한 액션의 actionName(로그인 후 리턴할 주소)이 설정되어 있지 않을 경우 home으로 설정(리턴)한다.
        /* 이 방법 밖에는 없는건가? */
        if (getActionName() == null || getActionName().equals(""))
        {
            // System.out.println("no actionName");
            setActionName("home");
        }        
        
        /*
         * 이 액션에서는 paramClass와 resultClass로 나누어 구현하였다.
         */

        // 로그인 타입이 "구매자"
        if (getLogin_type().equals("buyer"))
        {
            BuyerDTO paramClass = new BuyerDTO();
            BuyerDTO resultClass = new BuyerDTO();

            paramClass.setBuyer_id(getLogin_id());
            paramClass.setBuyer_pw(getLogin_pw());

            // 사용자의 비밀번호 가져오기
            resultClass = (BuyerDTO) sqlMapper.queryForObject("Buyer.selectBuyerPw", paramClass);

            // 입력한 비밀번호가 맞으면 세션 설정 후 SUCCESS 리턴
            if (resultClass != null)
            {
                sessionMap.put("session_type", getLogin_type());
                sessionMap.put("session_id", resultClass.getBuyer_id());
                sessionMap.put("session_name", resultClass.getBuyer_name());
                //sessionMap.put("session_pw", resultClass.getBuyer_pw());                
                //sessionMap.put("session_mobilenum", resultClass.getBuyer_mobilenum());
                //sessionMap.put("session_email", resultClass.getBuyer_email());
                //sessionMap.put("session_verification", resultClass.getBuyer_verification());              

                return SUCCESS;
            }
            return ERROR;
        // 로그인 타입이 "판매자"
        } else if (getLogin_type().equals("seller"))
        {
            SellerDTO paramClass = new SellerDTO();
            SellerDTO resultClass = new SellerDTO();

            paramClass.setSeller_id(getLogin_id());
            paramClass.setSeller_pw(getLogin_pw());

            // 사용자의 비밀번호 가져오기
            resultClass = (SellerDTO) sqlMapper.queryForObject("Seller.selectSellerPw", paramClass);

            // 입력한 비밀번호가 맞으면 세션 설정 후 SUCCESS 리턴
            if (resultClass != null)
            {
                sessionMap.put("session_type", getLogin_type());
                sessionMap.put("session_id", resultClass.getSeller_id());
                sessionMap.put("session_name", resultClass.getSeller_name());
                //sessionMap.put("session_pw", resultClass.getSeller_pw());
                //sessionMap.put("session_mobilenum", resultClass.getSeller_mobilenum());                
                //sessionMap.put("session_email", resultClass.getSeller_email());
                //sessionMap.put("session_verification", resultClass.getSeller_verification());

                return SUCCESS;
            }
            return ERROR;
        // 로그인 타입이 "관리자"
        } else if (getLogin_type().equals("admin"))
        {
            if (getLogin_id().equals("admin") && getLogin_pw().equals("1234"))
            {
                sessionMap.put("session_type", getLogin_type());
                sessionMap.put("session_id", "admin");
                sessionMap.put("session_name", "관리자");
                
                return SUCCESS;
            }
            return ERROR;
        }
        return ERROR;
    }

    // getter & setter
    public String getLogin_type()
    {
        return login_type;
    }

    public void setLogin_type(String login_type)
    {
        this.login_type = login_type;
    }

    public String getLogin_id()
    {
        return login_id;
    }

    public void setLogin_id(String login_id)
    {
        this.login_id = login_id;
    }

    public String getLogin_pw()
    {
        return login_pw;
    }

    public void setLogin_pw(String login_pw)
    {
        this.login_pw = login_pw;
    }

    public String getActionName()
    {
        return actionName;
    }

    public void setActionName(String actionName)
    {
        this.actionName = actionName;
    }

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}
    
}
