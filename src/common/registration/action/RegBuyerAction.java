package common.registration.action;

import java.util.Calendar;

import user.buyer.dto.BuyerDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import common.ConDAOAware;

public class RegBuyerAction implements Action, Preparable, ModelDriven, ConDAOAware {
	
	BuyerDTO buyerDTO;
	Calendar today = Calendar.getInstance(); // 오늘 날짜 구하기.
	private SqlMapClient sqlMapper;
	
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
		
		sqlMapper.insert("Buyer.insertBuyer", buyerDTO);
		
		return SUCCESS;		
		
	}	
	
}
