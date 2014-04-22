package user.admin.action;

import java.util.ArrayList;
import java.util.List;

import user.buyer.dto.BuyerDTO;
import user.seller.dto.SellerDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.Action;

import common.ConDAOAware;

public class DashAdminAction implements Action, ConDAOAware
{
    private String actionName = "dashAdmin"; // 페이징액션과 로그인액션에서 쓰인다.

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

    private List<BuyerDTO> listBuyer = new ArrayList<BuyerDTO>(); // 구매자 리스트
    private List<SellerDTO> listSeller = new ArrayList<SellerDTO>(); // 판매자 리스트
    // .회원 관련

    public void setConDAO(SqlMapClient sqlMapper)
    {
        this.sqlMapper = sqlMapper;
    }

    // 액션 실행.
    public String execute() throws Exception
    {
       	// 상품 개수를 구한다.
    	setCountRest((Integer) sqlMapper.queryForObject("Common.selectRestCount"));
    	
    	// 메뉴 개수를 구한다.
    	setCountRestopt((Integer) sqlMapper.queryForObject("Common.selectRestoptCount"));
    	
    	// 결재 개수를 구한다.
    	setCountPaid((Integer) sqlMapper.queryForObject("Common.selectPaidCount"));
    	
    	// 리뷰 개수를 구한다.
    	setCountReview((Integer) sqlMapper.queryForObject("Common.selectReviewCount"));
    	
    	// 레시피 개수를 구한다.
    	setCountRecipe((Integer) sqlMapper.queryForObject("Common.selectRecipeCount"));
    	
    	// 공지사항 개수를 구한다.
    	setCountNotice((Integer) sqlMapper.queryForObject("Common.selectNoticeCount"));
    	
    	// 문의하기 개수를 구한다.
    	setCountQna((Integer) sqlMapper.queryForObject("Common.selectQnaCount"));
    	
    	// 구매자 수를 구한다.
    	setCountBuyer((Integer) sqlMapper.queryForObject("Buyer.selectBuyerCount"));
    	
    	// 판매자 수를 구한다.
    	setCountSeller((Integer) sqlMapper.queryForObject("Seller.selectSellerCount"));
    	
        // 모든 구매자 정보를 가져와 listBuyer에 넣는다.
        setListBuyer(sqlMapper.queryForList("Buyer.selectAll"));

        // 모든 판매자 정보를 가져와 listSeller에 넣는다.
        setListSeller(sqlMapper.queryForList("Seller.selectAll"));

        return SUCCESS;
    }

    // getter & setter
    public String getActionName()
    {
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

    public List<BuyerDTO> getListBuyer()
    {
        return listBuyer;
    }

    public void setListBuyer(List<BuyerDTO> listBuyer)
    {
        this.listBuyer = listBuyer;
    }

    public List<SellerDTO> getListSeller()
    {
        return listSeller;
    }

    public void setListSeller(List<SellerDTO> listSeller)
    {
        this.listSeller = listSeller;
    }

}
