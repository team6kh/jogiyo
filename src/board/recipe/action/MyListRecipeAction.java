package board.recipe.action;

import java.util.ArrayList;
import java.util.List;

import board.recipe.dto.RecipeDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import common.ConDAOAware;
import common.action.PagingAction;

public class MyListRecipeAction extends ActionSupport implements ConDAOAware {
	public static SqlMapClient sqlMapper;
	private List<RecipeDTO> list = new ArrayList<RecipeDTO>();
	private RecipeDTO paramClass = new RecipeDTO(); // 파라미터를 저장할 객체
	private RecipeDTO resultClass = new RecipeDTO(); // 쿼리 결과 값을 저장할 객체
	private int currentPage = 1; // 현재 페이지
	private int totalCount; // 총 게시물의 수
	private int blockCount = 10; // 한 페이지의 게시물의 수
	private int blockPage = 5; // 한 화면에 보여줄 페이지
	private String pagingHtml; // 페이지를 구현할 HTML
	private PagingAction page; // 페이징 클래스
	private String actionName = "myListRecipe";
	private String recipe_memberwriter;
	
	
	public void setConDAO(SqlMapClient sqlMapper) {
		this.sqlMapper = sqlMapper;

	}

	public String execute() throws Exception {
		System.out.println("recipe_memberwriter : " + recipe_memberwriter);
		list = sqlMapper.queryForList("Recipe.myListRecipe", recipe_memberwriter);

		totalCount = list.size(); // 전체 글 갯수를 구한다.
		page = new PagingAction(actionName, currentPage, totalCount,
				blockCount, blockPage); // PagingAction 객체 생성
		pagingHtml = page.getPagingHtml().toString(); // 페이지 HTML 생성.

		// 현재 페이지에서 보여줄 마지막 글의 번호 설정.
		int lastCount = totalCount;

		// 현재 페이지의 마지막 글의 번호가 전체의 마지막 글 번호보다 작으면 lastCount를 +1 번호로 설정.
		if (page.getEndCount() < totalCount)
			lastCount = page.getEndCount() + 1;

		// 전체 리스트에서 현재 페이지만큼의 리스트만 가져온다.
		list = list.subList(page.getStartCount(), lastCount);

		return SUCCESS;

	}
	
	
	
	
	
	

	

	public String getRecipe_memberwriter() {
		return recipe_memberwriter;
	}

	public void setRecipe_memberwriter(String recipe_memberwriter) {
		this.recipe_memberwriter = recipe_memberwriter;
	}

	public List<RecipeDTO> getList() {
		return list;
	}

	public void setList(List<RecipeDTO> list) {
		this.list = list;
	}

	public RecipeDTO getParamClass() {
		return paramClass;
	}

	public void setParamClass(RecipeDTO paramClass) {
		this.paramClass = paramClass;
	}

	public RecipeDTO getResultClass() {
		return resultClass;
	}

	public void setResultClass(RecipeDTO resultClass) {
		this.resultClass = resultClass;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
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

	public String getPagingHtml() {
		return pagingHtml;
	}

	public void setPagingHtml(String pagingHtml) {
		this.pagingHtml = pagingHtml;
	}

	public PagingAction getPage() {
		return page;
	}

	public void setPage(PagingAction page) {
		this.page = page;
	}

}
