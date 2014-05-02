package board.recipe.action;

import java.util.*;

import board.recipe.dto.RecipeDTO;
import board.recipe.dto.RecipeSearchDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import common.ConDAOAware;
import common.action.PagingAction;

public class SearchRecipeAction extends ActionSupport implements ConDAOAware,Preparable, ModelDriven{
    
    RecipeSearchDTO paramClass;

    public static SqlMapClient sqlMapper;
    
    private List<RecipeDTO> list = new ArrayList<RecipeDTO>();
    private int currentPage = 1; //현재 페이지
    private int totalCount;      //총 게시물의 수
    private int blockCount = 10; //한 페이지의 게시물의 수
    private int blockPage = 5; //한 화면에 보여줄 페이지
    private String pagingHtml; //페이지를 구현할 HTML
    private PagingAction page;  //페이징 클래스
    
    private String actionName = "searchRecipe" ; //페이지 액션과 로그인 액션에서 쓰인다.
    private String myListactionName = "listMyRecipe" ;
    private String session_id;


   
    
    public void setConDAO(SqlMapClient sqlMapper){
        this.sqlMapper = sqlMapper;
             
    }
    
    public Object getModel()
    {

        return paramClass;
    }

    
    public void prepare() throws Exception
    {
        paramClass = new RecipeSearchDTO();
        
    }
    
    public String myListSearch() throws Exception
    {
    	 if(paramClass.getRecipe_priceinput1()==0 && paramClass.getRecipe_priceinput2()==0 && paramClass.getRecipe_timeinput1()==0 && paramClass.getRecipe_timeinput2()==0){
         	list = sqlMapper.queryForList("Recipe.detailSearchRecipeEmpty", paramClass);
         }else if(paramClass.getRecipe_timeinput1()==0 && paramClass.getRecipe_timeinput2()==0){
         	list = sqlMapper.queryForList("Recipe.detailSearchRecipePrice", paramClass);
         }else if(paramClass.getRecipe_priceinput1()==0 && paramClass.getRecipe_priceinput2()==0){
         	list = sqlMapper.queryForList("Recipe.detailSearchRecipeTime", paramClass);
         }else{//모두 기입했을때
         	list = sqlMapper.queryForList("Recipe.detailSearchRecipeAll", paramClass);
         }
         
         totalCount = list.size(); //전체 글 갯수를 구한다.
         page = new PagingAction(myListactionName, currentPage, totalCount, blockCount, blockPage, session_id); //PagingAction 객체 생성
         pagingHtml = page.getPagingHtml().toString();  //페이지 HTML 생성.
         
         // 현재 페이지에서 보여줄 마지막 글의 번호 설정.
                 int lastCount = totalCount;

                 // 현재 페이지의 마지막 글의 번호가 전체의 마지막 글 번호보다 작으면 lastCount를 +1 번호로 설정.
                 if (page.getEndCount() < totalCount)
                     lastCount = page.getEndCount() + 1;

                 // 전체 리스트에서 현재 페이지만큼의 리스트만 가져온다.
                 list = list.subList(page.getStartCount(), lastCount);
                
         return SUCCESS;

    	
    }


    public String execute() throws Exception
    {
        if(paramClass.getRecipe_priceinput1()==0 && paramClass.getRecipe_priceinput2()==0 && paramClass.getRecipe_timeinput1()==0 && paramClass.getRecipe_timeinput2()==0){
        	list = sqlMapper.queryForList("Recipe.detailSearchRecipeEmpty", paramClass);
        }else if(paramClass.getRecipe_timeinput1()==0 && paramClass.getRecipe_timeinput2()==0){
        	list = sqlMapper.queryForList("Recipe.detailSearchRecipePrice", paramClass);
        }else if(paramClass.getRecipe_priceinput1()==0 && paramClass.getRecipe_priceinput2()==0){
        	list = sqlMapper.queryForList("Recipe.detailSearchRecipeTime", paramClass);
        }else{//모두 기입했을때
        	list = sqlMapper.queryForList("Recipe.detailSearchRecipeAll", paramClass);
        }
        
        totalCount = list.size(); //전체 글 갯수를 구한다.
        page = new PagingAction(actionName, currentPage, totalCount, blockCount, blockPage); //PagingAction 객체 생성
        pagingHtml = page.getPagingHtml().toString();  //페이지 HTML 생성.
        
        // 현재 페이지에서 보여줄 마지막 글의 번호 설정.
                int lastCount = totalCount;

                // 현재 페이지의 마지막 글의 번호가 전체의 마지막 글 번호보다 작으면 lastCount를 +1 번호로 설정.
                if (page.getEndCount() < totalCount)
                    lastCount = page.getEndCount() + 1;

                // 전체 리스트에서 현재 페이지만큼의 리스트만 가져온다.
                list = list.subList(page.getStartCount(), lastCount);
               
        return SUCCESS;
    }

  
	public String getActionName() {
		return actionName;
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

	public List<RecipeDTO> getList() {
		return list;
	}

	public void setList(List<RecipeDTO> list) {
		this.list = list;
	}

	public String getSession_id() {
		return session_id;
	}

	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	
	
   
}
