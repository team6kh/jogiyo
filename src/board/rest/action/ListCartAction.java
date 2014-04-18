package board.rest.action;

import java.util.ArrayList;
import java.util.List;
import board.cart.dto.CartDTO;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.Action;

import common.ConDAOAware;

public class ListCartAction implements Action, ConDAOAware {
	private SqlMapClient sqlMapper;
	private List<CartDTO> list = new ArrayList<CartDTO>();
	private CartDTO paramClass = new CartDTO();

	private int rest_num;
	private String rest_subject;
	private String session_id;

	// ConDAOAware 인터페이스의 메서드(인터셉터에서 호출)
	public void setConDAO(SqlMapClient sqlMapper) {
		this.sqlMapper = sqlMapper;
	}

	
	public String execute() throws Exception {
		paramClass.setCart_rest_num(getRest_num());
		paramClass.setSession_id(getSession_id());
		//레코드들 = cartboard // where 상품번호 and 세션아뒤 //
		list = sqlMapper.queryForList("Rest.selectCartAll", paramClass);

		return SUCCESS;
	}
	
	
	public List<CartDTO> getList() {
		return list;
	}
	public void setList(List<CartDTO> list) {
		this.list = list;
	}
	public void setRest_num(int rest_num) {
		this.rest_num = rest_num;
	}
	public int getRest_num() {
		return rest_num;
	}
	public String getRest_subject() {
		return rest_subject;
	}
	public void setRest_subject(String rest_subject) {
		this.rest_subject = rest_subject;
	}
	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}

}