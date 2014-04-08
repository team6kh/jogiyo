package common;

import user.buyer.dto.BuyerDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.Action;

public class CheckDuplicateAction implements Action, ConDAOAware {
	
	private SqlMapClient sqlMapper;
	
	private String reg_id;
	private int isDup;
	
	public void setConDAO(SqlMapClient sqlMapper) {
		this.sqlMapper = sqlMapper;
	}
	
	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}
	
	public int getIsDup() {
		return isDup;
	}
	
	public String execute() throws Exception {
		
		BuyerDTO buyerDTO = new BuyerDTO();
		
		buyerDTO.setBuyer_id(reg_id);
		
		buyerDTO = (BuyerDTO) sqlMapper.queryForObject("Buyer.selectWhereBuyerId", buyerDTO);
		
		if(buyerDTO != null) {
			isDup = 1;
		} else {
			isDup = 0;
		}
		
		return SUCCESS;
		
	}
	
}
