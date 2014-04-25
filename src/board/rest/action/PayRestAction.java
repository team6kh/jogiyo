package board.rest.action;

import java.util.ArrayList;
import java.util.List;

import user.buyer.dto.BuyerDTO;
import user.seller.dto.SellerDTO;
import board.cart.dto.CartDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.ActionSupport;

import common.ConDAOAware;

public class PayRestAction extends ActionSupport implements ConDAOAware{

	public static SqlMapClient sqlMapper;
	private List<CartDTO> list = new ArrayList<CartDTO>();
	private CartDTO paramClass = new CartDTO();
	private BuyerDTO buyerDTO = new BuyerDTO();
	
	private int rest_num;
	private String rest_subject;
	private String session_id;//cart_restopt_priceplus
	
	//pay용 파라미터 변수
	private int pay_num; //완료
	private String pay_rest_subject; //완료
	private String pay_restopt_subject =""; //완료
	private int pay_pricetotal; //완료
	
	
	public void setConDAO(SqlMapClient sqlMapper) { 
	    this.sqlMapper = sqlMapper;
	}
	
	
	public String execute() throws Exception {
		
		paramClass.setCart_rest_num(getRest_num());
		paramClass.setSession_id(getSession_id());
		
		
		//현재 카트에 담긴 레코드를 리스트에 담음 (상품넘버and세션아이디)
		list = sqlMapper.queryForList("Rest.selectForPayment",paramClass);
		
		//결제 필수 4종 파라미터 생성, and get() to AGS_pay.jsp
		pay_num = getRest_num();
		pay_rest_subject = getRest_subject();
		
		for(int i=0; i<list.size(); i++){
			pay_restopt_subject += list.get(i).getCart_restopt_subject()+", ";
			pay_pricetotal += list.get(i).getCart_restopt_priceplus();
		}
		
		
		//회원정보
		String session_id = getSession_id();
		buyerDTO = (BuyerDTO) sqlMapper.queryForObject("Buyer.selectBuyerOne", session_id);
		
		
		
		return SUCCESS; // AGS_pay.jsp
	}

	//list 글 get용
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

	//AGS_pay 에서 쓰일 주문한 장바구니 list
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
	
	
	//AGS_pay.jsp로 보내질 파라미터 (=결제모듈 필수 파라미터)
	public int getPay_num() {
		return pay_num;
	}
	public String getPay_rest_subject() {
		return pay_rest_subject;
	}
	public String getPay_restopt_subject() {
		return pay_restopt_subject;
	}
	public int getPay_pricetotal() {
		return pay_pricetotal;
	}
	public String getRest_subject() {
		return rest_subject;
	}
	public void setRest_subject(String rest_subject) {
		this.rest_subject = rest_subject;
	}


	public BuyerDTO getBuyerDTO() {
		return buyerDTO;
	}
	public void setBuyerDTO(BuyerDTO buyerDTO) {
		this.buyerDTO = buyerDTO;
	}
	

}