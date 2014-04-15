package board.rest.action;

import java.io.File;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import common.ConDAOAware;
import board.rest.dto.RestDTO;
import board.restopt.dto.RestoptDTO;
import board.review.action.FileUpload;
import board.review.action.PagingReviewAction;
import board.review.dto.ReviewDTO;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.opensymphony.xwork2.ActionSupport;

import common.Constants;

public class ReadRestAction extends ActionSupport implements ConDAOAware{
	
	//listRest.jsp에서 보낸 히든값
	private int currentPage;
	private int rest_num;
	
	public static Reader reader;
	public static SqlMapClient sqlMapper;
	private RestDTO paramClass = new RestDTO();
	private RestDTO resultClass = new RestDTO();
	private RestoptDTO paramClass1 = new RestoptDTO();
	private RestoptDTO resultClass1 = new RestoptDTO();
	private List<RestoptDTO> list = new ArrayList<RestoptDTO>();

	
	//수정시(파일관련)
	private String fileUploadPath1 = Constants.COMMON_FILE_PATH + Constants.REST_MAIN_FILE_PATH;
	private String fileUploadPath2 = Constants.COMMON_FILE_PATH + Constants.REST_CONTENT_FILE_PATH;
	
	
	//후기리스트
	private int review_rest;
    private List<ReviewDTO> reviewRes = new ArrayList<ReviewDTO>();
    private String reviewFile_Path = Constants.COMMON_FILE_PATH+ Constants.REVIEW_FILE_PATH;
    // 페이징 관련 변수
    private int ccp = 1; // 현재 페이지
    private int totalCount; // 총 게시물의 수
    private int blockCount = 5; // 한 페이지의 게시물의 수
    private int blockPage = 5; // 한 화면에 보여줄 페이지 수
    private String pagingHtml; // 페이징을 구현한 HTML
    private PagingReviewAction page; // 페이징 클래스
    private String actionName = "listReview"; // 페이징액션과 로그인액션에서 쓰인다...

	
	
	
	public void setConDAO(SqlMapClient sqlMapper) { 
	    this.sqlMapper = sqlMapper;
	}
	
	
	public String execute() throws Exception {
		//글 조회수 +1
		paramClass.setRest_num(getRest_num());
		sqlMapper.update("Rest.updateRest_readcount", paramClass);
		
		//해당글번호의 레코드를 가져옴(상품테이블, 옵션테이블)
		resultClass = (RestDTO)sqlMapper.queryForObject("Rest.selectRestOne", getRest_num());
		//옵션들
		list = (List<RestoptDTO>) sqlMapper.queryForList("Rest.selectRestoptOne", getRest_num());
		
		
		
		//후기리스트
		reviewFile_Path = reviewFile_Path.replace("\\", "/").substring(27);
        review_rest = getRest_num();

        // 해당식당에 관한 리뷰 글만 가져와 List 타입 인스턴스에 저장
        reviewRes = sqlMapper.queryForList("Review.selectReviewList", review_rest);

        // 페이징 관련 코드
        totalCount = reviewRes.size();
        page = new PagingReviewAction(actionName, ccp, totalCount, blockCount, blockPage);
        pagingHtml = page.getPagingHtml().toString();
        int lastCount = totalCount; // 현재 페이지에서 보여줄 마지막 글의 번호 설정

        // 현재 페이지의 마지막 글의 번호가 전체의 마지막 글 번호보다 작으면 lastCount를 +1 번호로 설정.
        if (page.getEndCount() < totalCount)
            lastCount = page.getEndCount() + 1;

        // 전체 리스트에서 현재 페이지만큼의 리스트만 가져온다.
        reviewRes = reviewRes.subList(page.getStartCount(), lastCount);
		
		return SUCCESS;
	}
	
	
	//옵션 테이블 list
	public List<RestoptDTO> getList() {
		return list;
	}
	public void setList(List<RestoptDTO> list) {
		this.list = list;
	}


	//listRest.jsp에서 보낸 히든값
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getRest_num() {
		return rest_num;
	}
	public void setRest_num(int rest_num) {
		this.rest_num = rest_num;
	}


	//paramClass,1 resultClass,1
	public RestDTO getParamClass() {
		return paramClass;
	}
	public void setParamClass(RestDTO paramClass) {
		this.paramClass = paramClass;
	}
	public RestDTO getResultClass() {
		return resultClass;
	}
	public void setResultClass(RestDTO resultClass) {
		this.resultClass = resultClass;
	}
	public RestoptDTO getParamClass1() {
		return paramClass1;
	}
	public void setParamClass1(RestoptDTO paramClass1) {
		this.paramClass1 = paramClass1;
	}
	public RestoptDTO getResultClass1() {
		return resultClass1;
	}
	public void setResultClass1(RestoptDTO resultClass1) {
		this.resultClass1 = resultClass1;
	}
	
	
	
	//후기 리스트
    public List<ReviewDTO> getReviewRes() {
        return reviewRes;
    }
    public void setReviewRes(List<ReviewDTO> reviewRes) {
        this.reviewRes = reviewRes;
    }
    // 히든값 : review_rest 변수의 getter & setter
    public int getReview_rest() {
        return review_rest;
    }
    public void setReview_rest(int review_rest) {
        this.review_rest = review_rest;
    }
    // 파일 경로 getter
    public String getReviewFile_Path() {
        return reviewFile_Path;
    }
    // 페이징 관련 getter & setter
    public int getCcp() {
        return ccp;
    }
    public void setCcp(int ccp) {
        this.ccp = ccp;
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
    public PagingReviewAction getPage() {
        return page;
    }
    public void setPage(PagingReviewAction page) {
        this.page = page;
    }
    public String getActionName() {
        return actionName;
    }
	
}