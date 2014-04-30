package user.buyer.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import board.paid.dto.PaidDTO;
import board.paid.dto.SearchConditionDTO;
import board.paid.dto.SearchTimeDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.ActionSupport;

import common.ConDAOAware;
import common.action.PagingAction;

public class DashBuyerAction extends ActionSupport implements ConDAOAware {
	public static SqlMapClient sqlMapper;
	private List<PaidDTO> list = new ArrayList<PaidDTO>();
	private List<PaidDTO> timelist= new ArrayList<PaidDTO>();

	private int currentPage = 1; // 현재 페이지
	private int totalCount; // 총 게시물의 수

	private int blockCount = 10; // 한 페이지의 게시물의 수
	private int blockPage = 5; // 한 화면에 보여줄 페이지
	private String pagingHtml; // 페이지를 구현할 HTML
	private PagingAction page; // 페이징 클래스
	private String actionName = "dashBuyer";
	private String session_id;
	private String startDate;
	private String endDate;
	private int paid_num;
	private SearchConditionDTO searchDTO = new SearchConditionDTO();	
	

	public void setConDAO(SqlMapClient sqlMapper) {
		this.sqlMapper = sqlMapper;

	}
	
	public String time() throws Exception{
		searchDTO.setSession_id(getSession_id());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		searchDTO.setStartDate(sdf.format(sdf.parse(getStartDate())));
		searchDTO.setEndDate(sdf.format(sdf.parse(getEndDate())));	
		
		list = sqlMapper.queryForList("Rest.myListTime", searchDTO);
		
		return SUCCESS;
		
	}
	
	//사용요청
	public String requestCPN() throws Exception{
		sqlMapper.update("Rest.requestCPN", getPaid_num());
		return SUCCESS;
	}
	

	public String execute() throws Exception {
		
		list = sqlMapper.queryForList("Rest.myListCoupon", session_id);
		totalCount = list.size(); // 전체 글 갯수를 구한다.
		page = new PagingAction(actionName, currentPage, totalCount, blockCount, blockPage, session_id); // PagingAction 객체 생성
		pagingHtml = page.getPagingHtml().toString(); // 페이지 HTML 생성.

		// 현재 페이지에서 보여줄 마지막 글의 번호 설정.
		int lastCount = totalCount;

		// 현재 페이지의 마지막 글의 번호가 전체의 마지막 글 번호보다 작으면 lastCount를 +1 번호로 설정.
		if (page.getEndCount() < totalCount)
			lastCount = page.getEndCount() + 1;

		// 전체 리스트에서 현재 페이지만큼의 리스트만 가져온다.
		list = list.subList(page.getStartCount(), lastCount);

		return SUCCESS;
	}

	
	
	
	
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public SearchConditionDTO getSearchDTO() {
		return searchDTO;
	}

	public List<PaidDTO> getList() {
		return list;
	}

	public void setList(List<PaidDTO> list) {
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

	public String getSession_id() {
		return session_id;
	}

	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}

	public List<PaidDTO> getTimelist() {
		return timelist;
	}

	public int getPaid_num() {
		return paid_num;
	}

	public void setPaid_num(int paid_num) {
		this.paid_num = paid_num;
	}
	
	

}
