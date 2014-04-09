package dashboard.admin.action;

import java.util.ArrayList;
import java.util.List;

import user.buyer.dto.BuyerDTO;
import user.seller.dto.SellerDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.Action;

import common.ConDAOAware;

public class DashAdminAction implements Action, ConDAOAware {
	
	private String actionName = "dashAdmin"; // 페이징액션과 로그인액션에서 쓰인다.

	private SqlMapClient sqlMapper;

	private List<BuyerDTO> listBuyer = new ArrayList<BuyerDTO>(); // 구매자 리스트
	private List<SellerDTO>	listSeller = new ArrayList<SellerDTO>(); // 판매자 리스트
	
	private int countRest;
	private int countQna;	

	public void setConDAO(SqlMapClient sqlMapper) {
		this.sqlMapper = sqlMapper;
	}	

	// 액션 실행.
	public String execute() throws Exception {

		// 모든 구매자 정보를 가져와 listBuyer에 넣는다.
		setListBuyer(sqlMapper.queryForList("Buyer.selectAll"));
		
		// 모든 판매자 정보를 가져와 listSeller에 넣는다.
		setListSeller(sqlMapper.queryForList("Seller.selectAll"));
		
		// REST 글 개수를 구한다.
		setCountRest(sqlMapper.queryForList("Rest.selectAll").size());
		
		// QnA 글 개수를 구한다.
		setCountQna(sqlMapper.queryForList("Qna.qnaList").size());

		return SUCCESS;
	}

	// getter & setter
	public String getActionName() {
		return actionName;
	}	
	public List<BuyerDTO> getListBuyer() {
		return listBuyer;
	}
	public void setListBuyer(List<BuyerDTO> listBuyer) {
		this.listBuyer = listBuyer;
	}	
	public List<SellerDTO> getListSeller() {
		return listSeller;
	}
	public void setListSeller(List<SellerDTO> listSeller) {
		this.listSeller = listSeller;
	}
	public int getCountRest() {
		return countRest;
	}
	public void setCountRest(int countRest) {
		this.countRest = countRest;
	}
	public int getCountQna() {
		return countQna;
	}
	public void setCountQna(int countQna) {
		this.countQna = countQna;
	}
	
}