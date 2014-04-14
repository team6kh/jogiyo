package board.rest.action;

import com.opensymphony.xwork2.ActionSupport;

public class PayRestAction extends ActionSupport {

	private int rest_num;
	private String rest_subject;
	private String restopt_set;
	private String restopt_subject;
	private int restopt_priceplus;
	
	
	public String execute() throws Exception {
		//restopt_set에서 받은걸 옵션명과 옵션가격을 분리 시킴.
		restopt_subject = getRestopt_set().substring(0, getRestopt_set().indexOf('a'));
		restopt_priceplus = Integer.parseInt(getRestopt_set().substring(getRestopt_set().indexOf('a')+1));
			
		//readRest.jsp 상품정보를 결제페이지로 넘김.
		return SUCCESS;
	}
	
	
	//분리된 옵션명과 옵션가
	public String getRestopt_subject() {
		return restopt_subject;
	}
	public void setRestopt_subject(String restopt_subject) {
		this.restopt_subject = restopt_subject;
	}
	public int getRestopt_priceplus() {
		return restopt_priceplus;
	}
	public void setRestopt_priceplus(int restopt_priceplus) {
		this.restopt_priceplus = restopt_priceplus;
	}
	//readRest.jsp에서 들어온 상품정보
	public String getRestopt_set() {
		return restopt_set;
	}
	public void setRestopt_set(String restopt_set) {
		this.restopt_set = restopt_set;
	}
	public int getRest_num() {
		return rest_num;
	}
	public void setRest_num(int rest_num) {
		this.rest_num = rest_num;
	}
	public String getRest_subject() {
		return rest_subject;
	}
	public void setRest_subject(String rest_subject) {
		this.rest_subject = rest_subject;
	}
}
