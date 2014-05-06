package common.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import board.restopt.dto.RestoptDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.Action;

import common.ConDAOAware;

public class HomeAction implements Action, ConDAOAware {
	
    private String actionName = "home"; // 페이징액션과 로그인액션에서 쓰인다.
    
    private SqlMapClient sqlMapper;
    
    // 상품 관련
    private int countRest;
    private int countRestopt;
    private int countPaid;
    private int countReview;
    // .상품 관련
    
    private int countRecipe;
    private int countNotice;
    private int countQna;

	// 회원 관련
    private int countBuyer;
    private int countSeller;
    // .회원 관련
    
    // masonry
    private List<RestoptDTO> listRestopt = new ArrayList<RestoptDTO>();
    // .masonry
    
    public void setConDAO(SqlMapClient sqlMapper) {
        this.sqlMapper = sqlMapper;
    }

    // 환영 페이지
    public String welcome() throws Exception {
        return SUCCESS;
    }

    public String execute() throws Exception {
    	// 상품 개수를 구한다.
    	setCountRest((Integer) sqlMapper.queryForObject("Common.selectRestCount"));
    	
    	// 메뉴 개수를 구한다.
    	setCountRestopt((Integer) sqlMapper.queryForObject("Common.selectRestOptCount"));
    	
    	// 결재 개수를 구한다.
    	setCountPaid((Integer) sqlMapper.queryForObject("Common.selectPaidCount"));
    	
    	// 리뷰 개수를 구한다.
    	setCountReview((Integer) sqlMapper.queryForObject("Common.selectReviewCount"));
    	
    	// 레시피 개수를 구한다.
    	setCountRecipe((Integer) sqlMapper.queryForObject("Common.selectRecipeCount"));
    	
    	// 공지사항 개수를 구한다.
    	setCountNotice((Integer) sqlMapper.queryForObject("Common.selectNoticeCount"));
    	
    	// 문의하기 개수를 구한다.
    	setCountQna((Integer) sqlMapper.queryForObject("Common.selectQnACount"));
    	
    	// 구매자 수를 구한다.
    	setCountBuyer((Integer) sqlMapper.queryForObject("Buyer.selectBuyerCount"));
    	
    	// 판매자 수를 구한다.
    	setCountSeller((Integer) sqlMapper.queryForObject("Seller.selectSellerCount"));
    	
    	/* Masonry 리스트 */
    	listRestopt = (List<RestoptDTO>) sqlMapper.queryForList("Common.selectRestOptAll");
    	// 리스트를 받아와 섞는다(shuffle)
    	Collections.shuffle(listRestopt);
    	// 40개만 뽑아온다.
    	listRestopt = listRestopt.subList(0, 80);
    	setListRestopt(listRestopt);

        return SUCCESS;
    }

    // getter & setter
	public String getActionName() {
        return actionName;
    }

	public int getCountRest() {
		return countRest;
	}

	public void setCountRest(int countRest) {
		this.countRest = countRest;
	}

	public int getCountRestopt() {
		return countRestopt;
	}

	public void setCountRestopt(int countRestopt) {
		this.countRestopt = countRestopt;
	}

	public int getCountPaid() {
		return countPaid;
	}

	public void setCountPaid(int countPaid) {
		this.countPaid = countPaid;
	}

	public int getCountReview() {
		return countReview;
	}

	public void setCountReview(int countReview) {
		this.countReview = countReview;
	}

    public int getCountRecipe() {
		return countRecipe;
	}

	public void setCountRecipe(int countRecipe) {
		this.countRecipe = countRecipe;
	}

	public int getCountNotice() {
		return countNotice;
	}

	public void setCountNotice(int countNotice) {
		this.countNotice = countNotice;
	}

	public int getCountQna() {
		return countQna;
	}

	public void setCountQna(int countQna) {
		this.countQna = countQna;
	}

    public int getCountBuyer() {
		return countBuyer;
	}

	public void setCountBuyer(int countBuyer) {
		this.countBuyer = countBuyer;
	}

	public int getCountSeller() {
		return countSeller;
	}

	public void setCountSeller(int countSeller) {
		this.countSeller = countSeller;
	}
	
	public List<RestoptDTO> getListRestopt() {
		return listRestopt;
	}
	
	public void setListRestopt(List<RestoptDTO> listRestopt) {
		this.listRestopt = listRestopt;
	}
    
}