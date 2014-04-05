package board.recipe.action;

import java.io.InputStream;

import board.recipe.dto.RecipeDTO;
import board.test.dto.TestDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.ActionSupport;
import common.ConDAOAware;

public class ReadRecipeAction extends ActionSupport implements ConDAOAware{
	
	public static SqlMapClient sqlMapper;
	
	private RecipeDTO paramClass = new RecipeDTO();   //파라미터를 저장할 객체
	private RecipeDTO resultClass = new RecipeDTO();  //쿼리 결과 값을 저장할 객체
	
	private int currentPage;
	private int recipe_num;
	
	private InputStream inputStream;
	private String contentDisposition;
	private long contentLength;
	
	public void setConDAO(SqlMapClient sqlMapper){
		this.sqlMapper = sqlMapper;
	}

	// 게시판 상세보기 액션.
	public String execute() throws Exception {
		System.out.println(getRecipe_num());
		System.out.println(sqlMapper);
		// 해당 글의 조회수 +1.
		paramClass.setRecipe_num(getRecipe_num());
		sqlMapper.update("Recipe.updateReadcount", paramClass);

		// 해당 번호의 글을 가져온다.
		resultClass = (RecipeDTO) sqlMapper.queryForObject("Recipe.selectOne", getRecipe_num());

		return SUCCESS;
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

	public int getRecipe_num() {
		return recipe_num;
	}

	public void setRecipe_num(int recipe_num) {
		this.recipe_num = recipe_num;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getContentDisposition() {
		return contentDisposition;
	}

	public void setContentDisposition(String contentDisposition) {
		this.contentDisposition = contentDisposition;
	}

	public long getContentLength() {
		return contentLength;
	}

	public void setContentLength(long contentLength) {
		this.contentLength = contentLength;
	}
	
	


}
