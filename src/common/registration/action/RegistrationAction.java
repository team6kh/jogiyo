package common.registration.action;

import java.util.Calendar;
import java.util.Map;

import user.buyer.dto.BuyerDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import common.ConDAOAware;
import common.registration.dto.RegistrationDTO;

public class RegistrationAction implements Action, Preparable, ModelDriven, ConDAOAware {
	
	RegistrationDTO regDTO;	// 회원가입 폼 에서 넘어오는 파라미터를 받고 담기 위한 임시객체.
	Calendar today = Calendar.getInstance(); // 오늘 날짜 구하기.
	private SqlMapClient sqlMapper;
	
	public void setConDAO(SqlMapClient sqlMapper) {
		this.sqlMapper = sqlMapper;		
	}
	
	public void prepare() throws Exception {
		regDTO = new RegistrationDTO();
	}	
	
	public Object getModel() {
		return regDTO;
	}
	
	public String execute() throws Exception {
		
		if (regDTO.getReg_type().equals("buyer")){			
			BuyerDTO buyerDTO = new BuyerDTO();			
			buyerDTO.setBuyer_id(regDTO.getReg_id());
			buyerDTO.setBuyer_pw(regDTO.getReg_pw());
			buyerDTO.setBuyer_name(regDTO.getReg_name());
			buyerDTO.setBuyer_reg_date(today.getTime());
			buyerDTO.setBuyer_email(regDTO.getReg_email());
			buyerDTO.setBuyer_phonenumber(regDTO.getReg_phonenumber());
			buyerDTO.setBuyer_gender(regDTO.getReg_gender());
			
			sqlMapper.insert("Buyer.insertBuyer", buyerDTO);
			
			return SUCCESS;
		} else if (regDTO.getReg_type().equals("seller")){
			SellerDTO sellerDTO = new SellerDTO();			
			sellerDTO.setSeller_id(regDTO.getReg_id());
			sellerDTO.setSeller_pw(regDTO.getReg_pw());
			sellerDTO.setSeller_name(regDTO.getReg_name());
			sellerDTO.setSeller_reg_date(today.getTime());
			sellerDTO.setSeller_email(regDTO.getReg_email());
			sellerDTO.setSeller_phonenumber(regDTO.getReg_phonenumber());
			sellerDTO.setSeller_gender(regDTO.getReg_gender());
			
			sqlMapper.insert("Seller.insert", sellerDTO);
		}
		
		return ERROR;
		
	}	
	
}
