package board.recipe.action;

import java.util.ArrayList;
import java.util.List;

import board.qna.dto.QnaDTO;
import board.recipe.dto.RecipeDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import common.ConDAOAware;
import common.action.PagingAction;

public class MyAllListPageAction extends ActionSupport implements ConDAOAware {
	public static SqlMapClient sqlMapper;
	private List<RecipeDTO> recipelist = new ArrayList<RecipeDTO>();
	private List<QnaDTO> qnalist = new ArrayList<QnaDTO>();
	
	private int currentPage = 1; // 현재 페이지
	private int recipetotalCount; // 총 게시물의 수
	private int qnatotalCount;
	private int blockCount = 10; // 한 페이지의 게시물의 수
	private int blockPage = 5; // 한 화면에 보여줄 페이지
	private String recipepagingHtml; // 페이지를 구현할 HTML
	private String qnapagingHtml;
	private PagingAction recipepage; // 페이징 클래스
	private PagingAction qnapage;
	private String actionName = "myListRecipe";
	private String session_id;
	
	
	public void setConDAO(SqlMapClient sqlMapper) {
		this.sqlMapper = sqlMapper;

	}

	public String execute() throws Exception {
		
		recipelist = sqlMapper.queryForList("Recipe.myListRecipe", session_id);
		qnalist = sqlMapper.queryForList("Qna.myListQna", session_id);

		recipetotalCount = recipelist.size(); // recipe 전체 글 갯수를 구한다.
		qnatotalCount = qnalist.size(); //qna 전체 글 갯수를 구한다.
		recipepage = new PagingAction(actionName, currentPage, recipetotalCount, blockCount, blockPage); // PagingAction 객체 생성
		qnapage = new PagingAction(actionName, currentPage, qnatotalCount, blockCount, blockPage);
		recipepagingHtml = recipepage.getPagingHtml().toString(); // 페이지 HTML 생성.
		qnapagingHtml = qnapage.getPagingHtml().toString();

		// 현재 페이지에서 보여줄 마지막 글의 번호 설정.
		int recipelastCount = recipetotalCount;
		int qnalastCount = qnatotalCount;

		// 현재 페이지의 마지막 글의 번호가 전체의 마지막 글 번호보다 작으면 lastCount를 +1 번호로 설정.
		if (recipepage.getEndCount() < recipetotalCount)
			recipelastCount = recipepage.getEndCount() + 1;
		
		if(qnapage.getEndCount() < qnatotalCount)
			qnalastCount = qnapage.getEndCount() + 1;

		// 전체 리스트에서 현재 페이지만큼의 리스트만 가져온다.
		recipelist = recipelist.subList(recipepage.getStartCount(), recipelastCount);
		qnalist = qnalist.subList(recipepage.getStartCount(), qnalastCount);

		return SUCCESS;

	}
	

	public String getSession_id() {
		return session_id;
	}

	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}

	
	
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	
	public int getBlockCount() {
		return blockCount;
	}

	public void setBlockCount(int blockCount) {
		this.blockCount = blockCount;
	}

	public int getBlockPage() {
		return blockPage;
	}

	public void setBlockPage(int blockPage) {
		this.blockPage = blockPage;
	}

	
	public List<RecipeDTO> getRecipelist() {
		return recipelist;
	}

	public void setRecipelist(List<RecipeDTO> recipelist) {
		this.recipelist = recipelist;
	}

	public List<QnaDTO> getQnalist() {
		return qnalist;
	}

	public void setQnalist(List<QnaDTO> qnalist) {
		this.qnalist = qnalist;
	}

	public int getRecipetotalCount() {
		return recipetotalCount;
	}

	public void setRecipetotalCount(int recipetotalCount) {
		this.recipetotalCount = recipetotalCount;
	}

	public int getQnatotalCount() {
		return qnatotalCount;
	}

	public void setQnatotalCount(int qnatotalCount) {
		this.qnatotalCount = qnatotalCount;
	}

	public String getRecipepagingHtml() {
		return recipepagingHtml;
	}

	public void setRecipepagingHtml(String recipepagingHtml) {
		this.recipepagingHtml = recipepagingHtml;
	}

	public String getQnapagingHtml() {
		return qnapagingHtml;
	}

	public void setQnapagingHtml(String qnapagingHtml) {
		this.qnapagingHtml = qnapagingHtml;
	}

	public PagingAction getRecipepage() {
		return recipepage;
	}

	public void setRecipepage(PagingAction recipepage) {
		this.recipepage = recipepage;
	}

	public PagingAction getQnapage() {
		return qnapage;
	}

	public void setQnapage(PagingAction qnapage) {
		this.qnapage = qnapage;
	}


}
