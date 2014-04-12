package board.recipe.action;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

import board.recipe.dto.RecipeDTO;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.opensymphony.xwork2.ActionSupport;
import common.ConDAOAware;

public class DeleteRecipeAction extends ActionSupport implements ConDAOAware {

	public static SqlMapClient sqlMapper;

	private RecipeDTO paramClass = new RecipeDTO(); // 파라미터를 저장할 객체
	private RecipeDTO resultClass = new RecipeDTO(); // 쿼리 결과 값을 저장할 객체

	private int currentPage; // 현재 페이지
	private int recipe_num;

	private String fileUploadPath = "D:\\김경남\\Java\\upload\\"; // 업로드 경로.

	public void setConDAO(SqlMapClient sqlMapper) {
		this.sqlMapper = sqlMapper;

	}

	// 게시글 글 삭제
	public String execute() throws Exception {
		
		// 해당 번호의 글을 가져온다.
		resultClass = (RecipeDTO) sqlMapper.queryForObject("Recipe.selectOne", getRecipe_num());

		// 삭제할 항목 설정.
		paramClass.setRecipe_num(getRecipe_num());

		// 삭제 쿼리 수행.
		sqlMapper.update("Recipe.deleteRecipe", paramClass);

		// 서버 파일 삭제
		File deleteFile = new File(fileUploadPath
				+ resultClass.getRecipe_file());
		deleteFile.delete();

		return SUCCESS;
	}

	public String getFileUploadPath() {
		return fileUploadPath;
	}

	public void setFileUploadPath(String fileUploadPath) {
		this.fileUploadPath = fileUploadPath;
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

}
