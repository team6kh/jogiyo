package common;

import user.buyer.dto.BuyerDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.Action;

public class CheckDuplicateAction implements Action, ConDAOAware {
	
	private SqlMapClient sqlMapper;
	
	private String reg_id;
	private StringBuffer sb;
	private int isDup;
	private int check;
	
	public void setConDAO(SqlMapClient sqlMapper) {
		this.sqlMapper = sqlMapper;
	}
	
	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}
	
	public int getIsDup() {
		return isDup;
	}
	
	public int getCheck() {
		return check;
	}

	public String execute() throws Exception {
		
		BuyerDTO buyerDTO = new BuyerDTO();
		
		System.out.println("reg_id:"+reg_id);
		buyerDTO.setBuyer_id(reg_id);
		
		buyerDTO = (BuyerDTO) sqlMapper.queryForObject("Buyer.selectWhereBuyerId", buyerDTO);
		
		System.out.println("aaa");
		System.out.println("bb"+buyerDTO);
		
		sb = new StringBuffer();
		sb.append("<?xml version='1.0' encoding='euc-kr'?>");
		sb.append("<root>");
		if (!(reg_id.equals("adminadmin") || reg_id.equals("test"))) {
			sb.append("true");
			isDup = 0;
			check = 1;
		} else {
			sb.append("false");
			isDup = 1;
			check = 0;
		}
		
		sb.append("<id>" + reg_id + "</id>");
		sb.append("</root>");

		// response.setContentType("text/xml;charset=euc-kr");
		// response.getWriter().write(sb.toString());
		
		
		System.out.println("isDup:"+isDup);
		System.out.println("check:"+check);
		
		return SUCCESS;
		
	}
	
}
