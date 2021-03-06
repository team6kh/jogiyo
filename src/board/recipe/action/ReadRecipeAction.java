package board.recipe.action;

import java.io.InputStream;
import java.net.URLEncoder;

import board.recipe.dto.RecipeCommandDTO;
import board.recipe.dto.RecipeDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.ActionSupport;

import common.ConDAOAware;

public class ReadRecipeAction extends ActionSupport implements ConDAOAware {

	public static SqlMapClient sqlMapper;

	private int isRecommend = 1;
	private RecipeDTO paramClass = new RecipeDTO(); // 파라미터를 저장할 객체
	private RecipeDTO resultClass = new RecipeDTO(); // 쿼리 결과 값을 저장할 객체
	private RecipeCommandDTO paramRC = new RecipeCommandDTO();
	private RecipeCommandDTO resultRC = new RecipeCommandDTO();
	private String session_id;
	private int currentPage;
	private int recipe_num;
	private String recipe_password;
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
		
		if (getCurrentPage() == 0)
		{
			setCurrentPage(1);
		}

		paramClass.setRecipe_num(getRecipe_num());
		paramRC.setRecipeCommand_num(getRecipe_num());
		paramRC.setRecipeReadCount_writer(getSession_id());
		
        
		resultRC = (RecipeCommandDTO) sqlMapper.queryForObject("Recipe.selectReadcountId", paramRC);
		

		if (!(session_id.equals("")) && resultRC == null) {
			// 해당 글의 조회수 +1.
			sqlMapper.insert("Recipe.insertRecipeReadCount", paramRC);
			sqlMapper.update("Recipe.updateReadcount", paramClass);

		} else {

		}

		// 해당 번호의 글을 가져온다.
		resultClass = (RecipeDTO) sqlMapper.queryForObject("Recipe.selectOne", getRecipe_num());

		pagingHtml.append(resultClass.getRecipe_content());

		return SUCCESS;
	}

	// recommendAction
	public String recommendAction() throws Exception {

		// 해당글에 추천 유무 확인
		paramClass.setRecipe_num(getRecipe_num());
		paramRC.setRecipeCommand_num(getRecipe_num());
		paramRC.setRecipeCommand_writer(getSession_id());

		resultRC = (RecipeCommandDTO) sqlMapper.queryForObject("Recipe.selectCommandId", paramRC);
		if (resultRC == null) {
			
			// 해당 글의 추천수 +1.
			sqlMapper.insert("Recipe.insertRecipeCommand", paramRC);
			sqlMapper.update("Recipe.updateRecommend", paramClass);

			// 해당 번호의 글을 가져온다.
			resultClass = (RecipeDTO) sqlMapper.queryForObject("Recipe.selectOne", getRecipe_num());
			isRecommend = 0;
		} else {
			isRecommend = 1;
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

	public int getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(int isRecommend) {
		this.isRecommend = isRecommend;
	}

}
