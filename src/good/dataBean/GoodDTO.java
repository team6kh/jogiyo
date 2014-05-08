package good.dataBean;

public class GoodDTO
{
	/*﻿ 자바빈의 프로퍼티(멤버변수) 선언 */
	
	// 1. 상품관련 변수 선언
	
	private	int	good_seq;      //상품시퀀스
	private	int	good_num;      //상품글번호
	private	int	good_code;      //상품코드
	private	String	good_seller;      //상품 판매자
	private	String	good_ctgy;      //상품분류
	private	String	good_name;      //상품명
	private	int	sell_price;      //판매가
	private	String	mnft;      //제조사
	private	String	pbnc;      //원산지
	private	int	reg_amount;      //등록수량
	private	int	sell_amount;      //판매수량
	private	int	stock_amount;      //재고수량
	private	String	good_content;      //상품내용
	private	String	good_option;      //상품옵션
	private	String	good_shp_info_content;      //상품 배송정보 내용
	private	String	good_keep_type;      //상품 보관 유형
	private	int	shp_fee;      //배송비
	private	String	shp_fee_type;      //배송비 유형
	private	int	good_reg_date;      //상품 등록일
	
	// 2. 게시판관련 변수 선언
	
	/* 자바빈의 메소드 선언 (getter & setter) */		
	public int getGood_seq()
	{
		return good_seq;
	}
	public void setGood_seq(int good_seq)
	{
		this.good_seq = good_seq;
	}
	public int getGood_num()
	{
		return good_num;
	}
	public void setGood_num(int good_num)
	{
		this.good_num = good_num;
	}
	public int getGood_code()
	{
		return good_code;
	}
	public void setGood_code(int good_code)
	{
		this.good_code = good_code;
	}
	public String getGood_seller()
	{
		return good_seller;
	}
	public void setGood_seller(String good_seller)
	{
		this.good_seller = good_seller;
	}
	public String getGood_ctgy()
	{
		return good_ctgy;
	}
	public void setGood_ctgy(String good_ctgy)
	{
		this.good_ctgy = good_ctgy;
	}
	public String getGood_name()
	{
		return good_name;
	}
	public void setGood_name(String good_name)
	{
		this.good_name = good_name;
	}
	public int getSell_price()
	{
		return sell_price;
	}
	public void setSell_price(int sell_price)
	{
		this.sell_price = sell_price;
	}
	public String getMnft()
	{
		return mnft;
	}
	public void setMnft(String mnft)
	{
		this.mnft = mnft;
	}
	public String getPbnc()
	{
		return pbnc;
	}
	public void setPbnc(String pbnc)
	{
		this.pbnc = pbnc;
	}
	public int getReg_amount()
	{
		return reg_amount;
	}
	public void setReg_amount(int reg_amount)
	{
		this.reg_amount = reg_amount;
	}
	public int getSell_amount()
	{
		return sell_amount;
	}
	public void setSell_amount(int sell_amount)
	{
		this.sell_amount = sell_amount;
	}
	public int getStock_amount()
	{
		return stock_amount;
	}
	public void setStock_amount(int stock_amount)
	{
		this.stock_amount = stock_amount;
	}
	public String getGood_content()
	{
		return good_content;
	}
	public void setGood_content(String good_content)
	{
		this.good_content = good_content;
	}
	public String getGood_option()
	{
		return good_option;
	}
	public void setGood_option(String good_option)
	{
		this.good_option = good_option;
	}
	public String getGood_shp_info_content()
	{
		return good_shp_info_content;
	}
	public void setGood_shp_info_content(String good_shp_info_content)
	{
		this.good_shp_info_content = good_shp_info_content;
	}
	public String getGood_keep_type()
	{
		return good_keep_type;
	}
	public void setGood_keep_type(String good_keep_type)
	{
		this.good_keep_type = good_keep_type;
	}
	public int getShp_fee()
	{
		return shp_fee;
	}
	public void setShp_fee(int shp_fee)
	{
		this.shp_fee = shp_fee;
	}
	public String getShp_fee_type()
	{
		return shp_fee_type;
	}
	public void setShp_fee_type(String shp_fee_type)
	{
		this.shp_fee_type = shp_fee_type;
	}
	public int getGood_reg_date()
	{
		return good_reg_date;
	}
	public void setGood_reg_date(int good_reg_date)
	{
		this.good_reg_date = good_reg_date;
	}
	
}
