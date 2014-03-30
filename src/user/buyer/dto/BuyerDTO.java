package user.buyer.dto;

import java.sql.Timestamp;

public class BuyerDTO {
	private String buyer_id;
	private String buyer_pw;
	private String buyer_name;
	private Timestamp regDate;
	private String buyer_email;
	private String buyer_phonenumber;
	private String buyer_gender;
	
	public String getBuyer_id() {
		return buyer_id;
	}
	public void setBuyer_id(String buyer_id) {
		this.buyer_id = buyer_id;
	}
	public String getBuyer_pw() {
		return buyer_pw;
	}
	public void setBuyer_pw(String buyer_pw) {
		this.buyer_pw = buyer_pw;
	}
	public String getBuyer_name() {
		return buyer_name;
	}
	public void setBuyer_name(String buyer_name) {
		this.buyer_name = buyer_name;
	}
	public Timestamp getRegDate() {
		return regDate;
	}
	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
	public String getBuyer_email() {
		return buyer_email;
	}
	public void setBuyer_email(String buyer_email) {
		this.buyer_email = buyer_email;
	}
	public String getBuyer_phonenumber() {
		return buyer_phonenumber;
	}
	public void setBuyer_phonenumber(String buyer_phonenumber) {
		this.buyer_phonenumber = buyer_phonenumber;
	}
	public String getBuyer_gender() {
		return buyer_gender;
	}
	public void setBuyer_gender(String buyer_gender) {
		this.buyer_gender = buyer_gender;
	}
	
}