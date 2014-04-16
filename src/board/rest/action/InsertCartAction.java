package board.rest.action;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import board.cart.dto.CartDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.ActionSupport;

import common.ConDAOAware;

public class InsertCartAction extends ActionSupport implements ConDAOAware {

	public static Reader reader;
	public static SqlMapClient sqlMapper;

	private CartDTO paramClass = new CartDTO();
	private CartDTO resultClass = new CartDTO();

	private List<CartDTO> list = new ArrayList<CartDTO>();

	// readRest.jsp 에서 a링크로 넘어오는 파라미터들
	private int cart_rest_num; // 상품
	private String cart_rest_subject;
	private String cart_restopt_num; // 여기서부터 옵션
	private String cart_restopt_destFile1;
	private String cart_restopt_subject;
	private int cart_restopt_priceplus;
	private String session_id;

	public void setConDAO(SqlMapClient sqlMapper) {
		this.sqlMapper = sqlMapper;
	}

	public String execute() throws Exception {

		paramClass.setCart_rest_num(getCart_rest_num());
		paramClass.setCart_rest_subject(getCart_rest_subject());
		paramClass.setCart_restopt_num(getCart_restopt_num());
		paramClass.setCart_restopt_destFile1(getCart_restopt_destFile1());
		paramClass.setCart_restopt_subject(getCart_restopt_subject());
		paramClass.setCart_restopt_priceplus(getCart_restopt_priceplus());
		paramClass.setSession_id(getSession_id());

		sqlMapper.insert("Rest.insertCart_board", paramClass);

		list = sqlMapper.queryForList("Rest.selectCartAll");

		return SUCCESS;
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

	public CartDTO getResultClass() {
		return resultClass;
	}

	public void setResultClass(CartDTO resultClass) {
		this.resultClass = resultClass;
	}

	public int getCart_rest_num() {
		return cart_rest_num;
	}

	public void setCart_rest_num(int cart_rest_num) {
		this.cart_rest_num = cart_rest_num;
	}

	public String getCart_rest_subject() {
		return cart_rest_subject;
	}

	public void setCart_rest_subject(String cart_rest_subject) {
		this.cart_rest_subject = cart_rest_subject;
	}

	public String getCart_restopt_destFile1() {
		return cart_restopt_destFile1;
	}

	public void setCart_restopt_destFile1(String cart_restopt_destFile1) {
		this.cart_restopt_destFile1 = cart_restopt_destFile1;
	}

	public String getCart_restopt_subject() {
		return cart_restopt_subject;
	}

	public void setCart_restopt_subject(String cart_restopt_subject) {
		this.cart_restopt_subject = cart_restopt_subject;
	}

	public int getCart_restopt_priceplus() {
		return cart_restopt_priceplus;
	}

	public void setCart_restopt_priceplus(int cart_restopt_priceplus) {
		this.cart_restopt_priceplus = cart_restopt_priceplus;
	}

	public String getSession_id() {
		return session_id;
	}

	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}

	public String getCart_restopt_num() {
		return cart_restopt_num;
	}

	public void setCart_restopt_num(String cart_restopt_num) {
		this.cart_restopt_num = cart_restopt_num;
	}

}