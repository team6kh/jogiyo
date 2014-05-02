package board.recipe.action;

import board.recipe.dto.RecipeDTO;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import common.ConDAOAware;

public class UpdateRecipeAction extends ActionSupport implements Preparable,
		ModelDriven, ConDAOAware {
	public static SqlMapClient sqlMapper;

	private RecipeDTO paramClass; // 파라미터를 저장할 객체
	private RecipeDTO resultClass = new RecipeDTO(); // 쿼리 결과 값을 저장할 객체

	private int currentPage; // 현재 페이지

	private int recipe_num;

	public void setConDAO(SqlMapClient sqlMapper) {
		this.sqlMapper = sqlMapper;
	}

	public Object getModel() {
		return paramClass;
	}

	public void prepare() throws Exception {
		paramClass = new RecipeDTO();

	}

	// updateRecipeForm 액션
	public String updateRecipeForm() throws Exception {
		System.out.println("recipe_num :" + recipe_num);
		resultClass = (RecipeDTO) sqlMapper.queryForObject("Recipe.selectOne", recipe_num);

		return SUCCESS;

	}

	// 게시글 UPDATE 액션.
	public String execute() throws Exception {

		System.out.println("parmaClass:" + paramClass);

		// 일단 항목만 수정한다.
		sqlMapper.update("Recipe.updateRecipe", paramClass);

		// 수정이 끝나면 view 페이지로 이동.
		resultClass = (RecipeDTO) sqlMapper.queryForObject("Recipe.selectOne",
				paramClass.getRecipe_num());

		return SUCCESS;
	}

	public int getRecipe_num() {
		return recipe_num;
	}

	public void setRecipe_num(int recipe_num) {
		this.recipe_num = recipe_num;
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

}
