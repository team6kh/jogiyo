package board.recipe.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;


import board.recipe.dto.RecipeCommandDTO;
import board.recipe.dto.RecipeDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.ActionSupport;

import common.ConDAOAware;

public class ReadRecipeAction extends ActionSupport implements ConDAOAware {

	public static SqlMapClient sqlMapper;

	private int isrecommand =1;
	private RecipeDTO paramClass = new RecipeDTO(); // 파라미터를 저장할 객체
	private RecipeDTO resultClass = new RecipeDTO(); // 쿼리 결과 값을 저장할 객체
	private RecipeCommandDTO paramRC = new RecipeCommandDTO();
	private RecipeCommandDTO resultRC = new RecipeCommandDTO(); 
	private String session_id;
	private int currentPage;
	private int recipe_num;
	private String recipe_password;
	private String fileUploadPath = "D:\\김경남\\Java\\upload\\";
	private InputStream inputStream;
	private String contentDisposition;
	private long contentLength;
	// 추가한것.
	private StringBuffer pagingHtml = new StringBuffer();

	public void setConDAO(SqlMapClient sqlMapper) {
		this.sqlMapper = sqlMapper;
	}

	// 게시판 상세보기 액션.
	public String execute() throws Exception {

		// 해당 글의 조회수 +1.
		paramClass.setRecipe_num(getRecipe_num());
		sqlMapper.update("Recipe.updateReadcount", paramClass);

		// 해당 번호의 글을 가져온다.
		resultClass = (RecipeDTO) sqlMapper.queryForObject("Recipe.selectOne",
				getRecipe_num());
		pagingHtml.append(resultClass.getRecipe_content());

		return SUCCESS;
	}

	// 첨부 파일 다운로드
	public String download() throws Exception {

		// 해당 번호의 파일 정보를 가져온다.
		resultClass = (RecipeDTO) sqlMapper.queryForObject("selectOne",
				getRecipe_num());

		// 파일 경로와 파일명을 file 객체에 넣는다.
		File fileInfo = new File(fileUploadPath + resultClass.getRecipe_file());

		// 다운로드 파일 정보 설정.
		setContentLength(fileInfo.length());
		setContentDisposition("attachment;filename="
				+ URLEncoder.encode(resultClass.getRecipe_orgfile(), "UTF-8"));
		setInputStream(new FileInputStream(fileUploadPath
				+ resultClass.getRecipe_file()));

		return SUCCESS;
	}

	// recommandAction
	public String recommandAction() throws Exception {
        
		//해당글에 추천 유무 확인
        paramClass.setRecipe_num(getRecipe_num());
		paramRC.setRecipeCommand_num(getRecipe_num());
		paramRC.setRecipeCommand_writer(getSession_id());
		
		resultRC = (RecipeCommandDTO) sqlMapper.queryForObject("Recipe.commandID", paramRC);
		if(resultRC == null){
			System.out.println("rdDTO is null!");
			// 해당 글의 추천수 +1.
			sqlMapper.insert("Recipe.insertRecipeCommand", paramRC);
			sqlMapper.update("Recipe.updateRecommand", paramClass);

			// 해당 번호의 글을 가져온다.
			resultClass = (RecipeDTO) sqlMapper.queryForObject("Recipe.selectOne",
					getRecipe_num());
			isrecommand = 0;
		}else{
			isrecommand =1;
		}
		
		return SUCCESS;

	}

	// 비밀번호 체크 폼
	public String checkForm() throws Exception {

		return SUCCESS;
	}

	// 비밀번호 체크 액션
	public String checkAction() throws Exception {

		// 비밀번호 입력값 파라미터 설정.
		paramClass.setRecipe_num(getRecipe_num());
		paramClass.setRecipe_password(getRecipe_password());

		// 현재 글의 비밀번호 가져오기.
		resultClass = (RecipeDTO) sqlMapper.queryForObject(
				"Recipe.selectRecipe_password", paramClass);

		// 입력한 비밀번호가 틀리면 ERROR 리턴.
		if (resultClass == null)
			return ERROR;

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

	public String getRecipe_password() {
		return recipe_password;
	}

	public void setRecipe_password(String recipe_password) {
		this.recipe_password = recipe_password;
	}

	public StringBuffer getPagingHtml() {
		return pagingHtml;
	}

	public void setPagingHtml(StringBuffer pagingHtml) {
		this.pagingHtml = pagingHtml;
	}

	
	public String getSession_id() {
		return session_id;
	}

	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}

	public int getIsrecommand() {
		return isrecommand;
	}

	public void setIsrecommand(int isrecommand) {
		this.isrecommand = isrecommand;
	}
	
	

	
	
	

}
