package board.rest.action;

import java.io.Reader;
import java.util.List;

import board.cart.dto.CartDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.ActionSupport;

import common.ConDAOAware;

public class InsertCartAction extends ActionSupport implements ConDAOAware{
	
	public static Reader reader;
	public static SqlMapClient sqlMapper;
	
	private CartDTO paramClass = new CartDTO();
	private CartDTO resultClass = new CartDTO();
	
	private int cart_rest_num;
	private String cart_rest_subject;
	
	private String cart_restopt_destFile1;
	private String cart_restopt_subject;
	private String cart_restopt_priceplus;
	private String session_id;
	
	

	public void setConDAO(SqlMapClient sqlMapper) { 
	    this.sqlMapper = sqlMapper;
	}
	
	
	public String execute() throws Exception {
		System.out.println("getCart_rest_num() 값 : "+getCart_rest_num());
		System.out.println("getCart_rest_subject() 값 : "+getCart_rest_subject());
		System.out.println("getCart_restopt_destFile1() 값 : "+getCart_restopt_destFile1());
		System.out.println("getCart_restopt_subject() 값 : "+getCart_restopt_subject());
		System.out.println("getCart_restopt_priceplus() 값 : "+getCart_restopt_priceplus());
		System.out.println("getSession_id() 값 : "+getSession_id());
		
		
		return SUCCESS;
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
	public String getCart_restopt_priceplus() {
		return cart_restopt_priceplus;
	}
	public void setCart_restopt_priceplus(String cart_restopt_priceplus) {
		this.cart_restopt_priceplus = cart_restopt_priceplus;
	}
	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}

}