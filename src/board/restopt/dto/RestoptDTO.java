package board.restopt.dto;

public class RestoptDTO {
	
	private int restopt_num; //옵션 시퀀스
	private int restopt_rest_num; // rest테이블의 시퀀스넘
	private String restopt_subject; //옵션명
	private int restopt_priceplus; //옵션가
	
	
	public int getRestopt_num() {
		return restopt_num;
	}
	public void setRestopt_num(int restopt_num) {
		this.restopt_num = restopt_num;
	}
	public int getRestopt_rest_num() {
		return restopt_rest_num;
	}
	public void setRestopt_rest_num(int restopt_rest_num) {
		this.restopt_rest_num = restopt_rest_num;
	}
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
}
