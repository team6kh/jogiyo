package board.rest.action;

import java.util.ArrayList;
import java.util.List;

import board.cart.dto.CartDTO;
import board.restopt.dto.RestoptDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.ActionSupport;

import common.ConDAOAware;

public class PayRestAction extends ActionSupport implements ConDAOAware{

	public static SqlMapClient sqlMapper;
	private List<CartDTO> list = new ArrayList<CartDTO>();
	private CartDTO paramClass = new CartDTO();
	
	private int rest_num;//cart_rest_num
	private String session_id;//cart_restopt_priceplus
	
	
	public void setConDAO(SqlMapClient sqlMapper) { 
	    this.sqlMapper = sqlMapper;
	}
	
	
	public String execute() throws Exception {
		
		//rest_num 이 1이고
		//session_id 가 water 인 글들을 cart보드 에서 select함   -> 리스트에 담음   
		//-> readyPayment로 리스트넘김  -> 리스트를 pay로 forward 시킴 -> pay에서 리스트를 뿌려줌.
		
		paramClass.setCart_rest_num(getRest_num());
		paramClass.setSession_id(getSession_id());
		
		//현재 카트에 담긴 레코드를 리스트에 담음 (상품넘버and세션아이디)
		list = sqlMapper.queryForList("Rest.selectForPayment",paramClass);
		
		
		return SUCCESS; // readyPayment.jsp
	}


	public int getRest_num() {
		return rest_num;
	}
	public void setRest_num(int rest_num) {
		this.rest_num = rest_num;
	}
	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}


	public List<CartDTO> getList() {
		return list;
	}
	public void setList(List<CartDTO> list) {
		this.list = list;
	}
	public CartDTO getParamClass() {
		return paramClass;
	}
	public void setParamClass(CartDTO paramClass) {
		this.paramClass = paramClass;
	}
	

}