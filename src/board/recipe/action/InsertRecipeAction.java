package board.recipe.action;

import java.io.File;

import org.apache.commons.io.FileUtils;

import board.recipe.dto.RecipeDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import common.ConDAOAware;

public class InsertRecipeAction implements Action, Preparable, ModelDriven, ConDAOAware {

	SqlMapClient sqlMapper; // SqlMapClient API를 사용하기 위한 SqlMapper
											// 객체

	RecipeDTO paramClass;
	RecipeDTO resultClass;

	// private RecipeDTO paramClass; // 파라미터를 저장할 객체
	// private RecipeDTO resultClass; //쿼리 결과 값을 저장할 객체

	private int currentPage; // 현재 페이지
	private int recipe_num; // 현재 글 고유 번호

	private File recipe_file; // 파일 객체
	private String recipe_fileContentType; // 컨텐츠 타입
	private String recipe_fileFileName; // 파일 이름

	// Calendar today = Calendar.getInstance(); // 오늘 날짜 구하기.

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
		System.out.println("sqlMapper:"+sqlMapper);
		System.out.println("paramClass:"+paramClass);
		
		// sqlMapper=conDAO.getCon();
		// //파라미터와 리절트 객체 생성.
		// paramClass = new RecipeDTO();
		// resultClass = new RecipeDTO();
		//
		// // 등록할 항목 설정.
		// paramClass.setRecipe_foodkind(getRecipe_foodkind());
		// paramClass.setRecipe_subject(getRecipe_subject());
		// paramClass.setRecipe_writer(getRecipe_writer());
		// paramClass.setRecipe_password(getRecipe_password());
		// paramClass.setRecipe_method(getRecipe_method());
		// paramClass.setRecipe_time(getRecipe_time());
		// paramClass.setRecipe_price(getRecipe_price());
		// paramClass.setRecipe_content(getRecipe_content());
		// paramClass.setRegdate(today.getTime());

		// 등록 쿼리 수행.
		sqlMapper.insert("Recipe.insertRecipe", paramClass);

		// 첨부파일을 선택했다면 파일을 업로드한다.
		if (getRecipe_file() != null) {

			// 등록한 글 번호 가져오기.
			paramClass = (RecipeDTO) sqlMapper.queryForObject("Recipe.selectLastRecipe_num");

			// 실제 서버에 저장될 파일 이름과 확장자 설정.
			String file_name = "file_" + paramClass.getRecipe_num();
			String file_ext = getRecipe_fileFileName().substring(
					getRecipe_fileFileName().lastIndexOf('.') + 1,
					getRecipe_fileFileName().length());

			// 서버에 파일 저장.
			File destFile = new File(fileUploadPath + file_name + "."
					+ file_ext);
			FileUtils.copyFile(getRecipe_file(), destFile);

			// 파일 정보 파라미터 설정.
			paramClass.setRecipe_num(paramClass.getRecipe_num()); // resultClass는
																	// 결과가 하나일때
			paramClass.setRecipe_orgfile(getRecipe_fileFileName()); // 원래 파일 이름
			paramClass.setRecipe_file(file_name + "." + file_ext); // 서버에 저장한
																	// 파일 이름

			// 파일 정보 업데이트.
			sqlMapper.update("Recipe.updateFile", paramClass);
		}

		return SUCCESS;
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

	private String fileUploadPath = "D:\\김경남\\Java\\upload\\"; // 업로드 경로.


	public void setConDAO(SqlMapClient sqlMapper) {
		this.sqlMapper = sqlMapper;
		
	}

}
