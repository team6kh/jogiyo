package board.rest.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import table.coopon.dto.CooponDTO;
import board.cart.dto.CartDTO;
import board.pay.dto.payDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.ActionSupport;

import common.ConDAOAware;

public class PayRestResultAction  extends ActionSupport implements ConDAOAware{
	public static SqlMapClient sqlMapper;
	Calendar today = Calendar.getInstance();
	
	private CooponDTO paramClass = new CooponDTO();
	private CartDTO paramClass1 = new CartDTO();
	private payDTO paramClass2 = new payDTO();
	
	private List<CooponDTO> list = new ArrayList<CooponDTO>();
	private List<CartDTO> list1 = new ArrayList<CartDTO>();
	
	
	
	Integer count;
	
	private int rest_num;
	private String session_id;
	
	private String cooResult;
	
	
	
	public void setConDAO(SqlMapClient sqlMapper) { 
	    this.sqlMapper = sqlMapper;
	}
	
	
	public String execute() throws Exception {
		
		//장바구니에 담은 레코드들을 get
		paramClass1.setCart_rest_num(getRest_num());
		paramClass1.setSession_id(getSession_id());
		list1 = sqlMapper.queryForList("Rest.selectCartAll", paramClass1);
		
		//장바구니 테이블 -> 결과테이블로 insert
		for(int i=0; i<list1.size(); i++){
			paramClass2.setPaid_rest_num(list1.get(i).getCart_rest_num());
			paramClass2.setPaid_rest_subject(list1.get(i).getCart_rest_subject());
			paramClass2.setPaid_restopt_num(list1.get(i).getCart_restopt_num()); //CartDTO int로 수정
			paramClass2.setPaid_restopt_subject(list1.get(i).getCart_restopt_subject());
			paramClass2.setPaid_restopt_priceplus(list1.get(i).getCart_restopt_priceplus());
			paramClass2.setPaid_restopt_destFile1(list1.get(i).getCart_restopt_destFile1());
			
			//쿠폰생성
			cooResult = Integer.toString((int)(Math.random() * 999999))+ "-" + Integer.toString((int)(Math.random() * 99999999))+ "-" + Integer.toString((int)(Math.random() * 777777));
			count = (Integer)sqlMapper.queryForObject("Rest.selectCheckcpn", cooResult);
			
			while(count != 0){ //값이 중복이 안될때까지 다시 생성
				cooResult = Integer.toString((int)(Math.random() * 777777))+ "-" + Integer.toString((int)(Math.random() * 99999999))+ "-" + Integer.toString((int)(Math.random() * 999999));
				count = (Integer)sqlMapper.queryForObject("Rest.selectCheckcpn", cooResult);
			}
			
			//중복되지 않은 쿠폰번호 cooResult를 쿠폰테이블로 insert
			paramClass.setCpn_num(cooResult);
			sqlMapper.insert("Rest.insertCpn", paramClass);
			
			paramClass2.setPaid_cpn(cooResult); //쿠폰번호
			paramClass2.setPaid_cpn_used(0); // 0발행완료, 1사용함, 2유효기간초과 되면서 처리되는 논리값
			paramClass2.setSession_id(getSession_id()); //주문한 세션아이디
			paramClass2.setPaid_reg_date(today.getTime()); // 쿠폰 발행일자
			
			sqlMapper.insert("Rest.insertPaidBoard", paramClass2);
		}
	    
		
		//최종 장바구니 레코드 삭제
		sqlMapper.delete("Rest.deleteCartforpaid", paramClass1);
		
		
		return SUCCESS; //payRestResult.jsp
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

	//paramClass2 를 jsp로 보냄
	public payDTO getParamClass2() {
		return paramClass2;
	}
	
}
