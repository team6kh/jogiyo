package board.cart.action;

import java.util.ArrayList;
import java.util.List;
import board.cart.dto.CartDTO;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.Action;
import common.ConDAOAware;

public class DeleteCartAction implements Action, ConDAOAware {
	private SqlMapClient sqlMapper;
	private List<CartDTO> list = new ArrayList<CartDTO>();
	private CartDTO paramClass = new CartDTO();
	private int rest_num;
	private String rest_subject;
	private String session_id;
	
	//인터셉터
	public void setConDAO(SqlMapClient sqlMapper) {
		this.sqlMapper = sqlMapper;
	}

	public String execute() throws Exception {
		paramClass.setCart_rest_num(getRest_num());
		paramClass.setSession_id(getSession_id());
		//장바구니 레코드 삭제
		sqlMapper.delete("Cart.deleteCartForPaid", paramClass);
		//장바구니 레코드 가져오기.
		list = sqlMapper.queryForList("Cart.selectCartAll", paramClass);

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
