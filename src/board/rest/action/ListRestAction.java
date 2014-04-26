package board.rest.action;

import com.opensymphony.xwork2.ActionSupport;
import com.ibatis.sqlmap.client.SqlMapClient;
import common.ConDAOAware;
import common.action.PagingAction;
import java.util.*;
import board.rest.dto.RestDTO;

public class ListRestAction extends ActionSupport implements ConDAOAware {

	public static SqlMapClient sqlMapper; 

	private List<RestDTO> list = new ArrayList<RestDTO>();
	private RestDTO paramClass = new RestDTO();
	
	private int currentPage = 1;			// 현재 페이지
	private int totalCount;					// 총 게시물의 수
	private int blockCount = 12;			// 한 페이지의 게시물의 수
	private int blockPage = 5;				// 한 화면에 보여줄 페이지 수
	private String pagingHtml;				// 페이징을 구현한 HTML
	private PagingAction page;				// 페이징 클래스
	private String actionName = "listRest";	// 페이징액션과 로그인액션에서 쓰인다
	
	//카테고리 판단
	private String rest_localcategory;
	private String rest_typecategory;

	public void setConDAO(SqlMapClient sqlMapper) {
		this.sqlMapper = sqlMapper;
	}	

	public String execute() throws Exception {
		//카테고리별 분기
		if(rest_localcategory==null&&rest_typecategory==null){
			list = sqlMapper.queryForList("Rest.selectAll");
		}else if(rest_localcategory.equals("1")&&rest_typecategory.equals("1")){//지역카테고리 전체글
			list = sqlMapper.queryForList("Rest.selectLocalAll");
		}else if(rest_localcategory.equals("2")&&rest_typecategory.equals("2")){//종류카테고리 전체글
			list = sqlMapper.queryForList("Rest.selectTypeAll");
		}else if(rest_typecategory.equals("0")){//지역카테고리에 속하면
			paramClass.setRest_localcategory(getRest_localcategory());
			list = sqlMapper.queryForList("Rest.selectLocal", paramClass);
		}else  if(rest_localcategory.equals("0")){//종류카테고리에 속하면
			paramClass.setRest_typecategory(getRest_typecategory());
			list = sqlMapper.queryForList("Rest.selectType", paramClass);
		}
		
		totalCount = list.size(); // 전체 글 갯수
		page = new PagingAction(actionName, currentPage, totalCount, blockCount, blockPage); // pagingAction 객체 생성.
		pagingHtml = page.getPagingHtml().toString(); // 페이지 HTML 생성.

		int lastCount = totalCount;
		// 현재 페이지의 마지막 글의 번호가 전체의 마지막 글 번호보다 작으면 lastCount를 +1 번호로 설정.
		if (page.getEndCount() < totalCount)
			lastCount = page.getEndCount() + 1;
		// 전체 리스트에서 현재 페이지만큼의 리스트만 가져온다.
		list = list.subList(page.getStartCount(), lastCount);

		return SUCCESS;
	}

	public List<RestDTO> getList() {
		return list;
	}
	public void setList(List<RestDTO> list) {
		this.list = list;
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
	public String getActionName() { 
		return actionName;
	}
	public String getRest_localcategory() {
		return rest_localcategory;
	}
	public void setRest_localcategory(String rest_localcategory) {
		this.rest_localcategory = rest_localcategory;
	}
	public String getRest_typecategory() {
		return rest_typecategory;
	}
	public void setRest_typecategory(String rest_typecategory) {
		this.rest_typecategory = rest_typecategory;
	}
}
