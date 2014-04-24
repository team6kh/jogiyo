package board.recipe.action;


import java.util.*;



import board.recipe.dto.RecipeDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import common.ConDAOAware;

public class InsertRecipeAction implements Action, Preparable, ModelDriven,
		ConDAOAware {

	SqlMapClient sqlMapper; // SqlMapClient API를 사용하기 위한 SqlMapper
							// 객체

	RecipeDTO paramClass;

	// private RecipeDTO paramClass; // 파라미터를 저장할 객체
	// private RecipeDTO resultClass; //쿼리 결과 값을 저장할 객체

	
	private int currentPage; // 현재 페이지
	private int recipe_num; // 현재 글 고유 번호

	

	Calendar today = Calendar.getInstance(); // 오늘 날짜 구하기.

	public void setConDAO(SqlMapClient sqlMapper) {
		this.sqlMapper = sqlMapper;

	}

	public String form() throws Exception {
		// 등록 폼
		return SUCCESS;
	}

	public void prepare() throws Exception { // Preparable 인터페이스의 prepare
		paramClass = new RecipeDTO();

	}

	public Object getModel() { // ModelDriven 인터페이스의 getModel 구현

		return paramClass;
	}

	// insertRecipe 액션
	public String execute() throws Exception {

		paramClass.setRecipe_reg_date(today.getTime());

		// 등록 쿼리 수행.
		sqlMapper.insert("Recipe.insertRecipe", paramClass);
		System.out.println("작성자:" + paramClass.getRecipe_writer());
		System.out.println("내용:" + paramClass.getRecipe_content());
		System.out.println("요리명:" + paramClass.getRecipe_foodsubject());
	     

		return SUCCESS;
	}


	public Calendar getToday() {
		return today;
	}

	public void setToday(Calendar today) {
		this.today = today;
	}

	public RecipeDTO getParamClass() {
		return paramClass;
	}

	public void setParamClass(RecipeDTO paramClass) {
		this.paramClass = paramClass;
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

}
