package board.recipe.action;

import java.io.File;

import org.apache.commons.io.FileUtils;

import board.recipe.dto.RecipeDTO;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import common.ConDAOAware;

public class UpdateRecipeAction extends ActionSupport implements  Preparable, ModelDriven,ConDAOAware {	
	public static SqlMapClient sqlMapper;

	private RecipeDTO paramClass ;		// 파라미터를 저장할 객체
	private RecipeDTO resultClass = new RecipeDTO() ;	// 쿼리 결과 값을 저장할 객체

	private int currentPage;	// 현재 페이지	
	
	private int recipe_num;
	
	private String old_file;
	private String fileUploadPath = "D:\\김경남\\Java\\upload\\"; // 업로드 경로.
	private File recipe_file; // 파일 객체
	private String recipe_fileContentType; // 컨텐츠 타입
	private String recipe_fileFileName; // 파일 이름
		
	public void setConDAO(SqlMapClient sqlMapper){
		this.sqlMapper = sqlMapper;
	}
	
	public Object getModel() {
		return paramClass;
	}

	
	public void prepare() throws Exception {
		paramClass = new RecipeDTO();
		
	}
	//updateRecipeForm 액션
	public String updateRecipeForm() throws Exception{
		System.out.println("recipe_num :" + recipe_num);
		resultClass = (RecipeDTO)sqlMapper.queryForObject("Recipe.selectOne", recipe_num);
		
		return SUCCESS;
		
	}

	// 게시글 UPDATE 액션.
	public String execute() throws Exception {

		System.out.println("parmaClass:"+paramClass);

		// 일단 항목만 수정한다.
		sqlMapper.update("Recipe.updateRecipe", paramClass);
		
		// 첨부파일을 선택했다면 파일을 업로드한다.
				if (getRecipe_file() != null) {

					
					// 실제 서버에 저장될 파일 이름과 확장자 설정.
					String file_name = "file_" + paramClass.getRecipe_num();
					String file_ext = getRecipe_fileFileName().substring(getRecipe_fileFileName().lastIndexOf('.')+1,getRecipe_fileFileName().length());

					//이전 파잉 삭제
					File deleteFile = new File(fileUploadPath + getOld_file());
					deleteFile.delete();
					
					// 새 파일 업로드
					File destFile = new File(fileUploadPath + file_name + "."+ file_ext);
					FileUtils.copyFile(getRecipe_file(), destFile);

					// 파일 정보 파라미터 설정.
					
					paramClass.setRecipe_orgfile(getRecipe_fileFileName()); // 원래 파일 이름
					paramClass.setRecipe_file(file_name + "." + file_ext); // 서버에 저장한
																			// 파일 이름

					// 파일 정보 업데이트.
					sqlMapper.update("Recipe.updateFile", paramClass);
				}


		// 수정이 끝나면 view 페이지로 이동.
		resultClass = (RecipeDTO) sqlMapper.queryForObject("Recipe.selectOne", paramClass.getRecipe_num());

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

	public String getFileUploadPath() {
		return fileUploadPath;
	}

	public void setFileUploadPath(String fileUploadPath) {
		this.fileUploadPath = fileUploadPath;
	}

	public String getOld_file() {
		return old_file;
	}

	public void setOld_file(String old_file) {
		this.old_file = old_file;
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
	
	public File getRecipe_file() {
		return recipe_file;
	}

	public void setRecipe_file(File recipe_file) {
		this.recipe_file = recipe_file;
	}

	public String getRecipe_fileContentType() {
		return recipe_fileContentType;
	}

	public void setRecipe_fileContentType(String recipe_fileContentType) {
		this.recipe_fileContentType = recipe_fileContentType;
	}

	public String getRecipe_fileFileName() {
		return recipe_fileFileName;
	}

	public void setRecipe_fileFileName(String recipe_fileFileName) {
		this.recipe_fileFileName = recipe_fileFileName;
	}
	
	
}
