package user.seller.dto;

import java.util.Date;

public class SellerDTO
{
    private int user_seq;
    private String seller_id;
    private String seller_pw;
    private String seller_name;
    private Date seller_reg_date;
    private String seller_rest_name;
    private String seller_rest_address;
    private String seller_rest_email;
    private String seller_rest_mobilenum;
    private String seller_rest_telnum;
    private String seller_verification;
    
    public int getUser_seq()
    {
        return user_seq;
    }

    public void setUser_seq(int user_seq)
    {
        this.user_seq = user_seq;
    }

    public String getSeller_id()
    {
        return seller_id;
    }

    public void setSeller_id(String seller_id)
    {
        this.seller_id = seller_id;
    }

    public String getSeller_pw()
    {
        return seller_pw;
    }

    public void setSeller_pw(String seller_pw)
    {
        this.seller_pw = seller_pw;
    }

    public String getSeller_name()
    {
        return seller_name;
    }

    public void setSeller_name(String seller_name)
    {
        this.seller_name = seller_name;
    }

    public Date getSeller_reg_date()
    {
        return seller_reg_date;
    }

    public void setSeller_reg_date(Date seller_reg_date)
    {
        this.seller_reg_date = seller_reg_date;
    }

    public String getSeller_rest_name()
    {
        return seller_rest_name;
    }

    public void setSeller_rest_name(String seller_rest_name)
    {
        this.seller_rest_name = seller_rest_name;
    }

    public String getSeller_rest_address()
    {
        return seller_rest_address;
    }

    public void setSeller_rest_address(String seller_rest_address)
    {
        this.seller_rest_address = seller_rest_address;
    }

    public String getSeller_rest_email()
    {
        return seller_rest_email;
    }

    public void setSeller_rest_email(String seller_rest_email)
    {
        this.seller_rest_email = seller_rest_email;
    }

    public String getSeller_rest_mobilenum()
    {
        return seller_rest_mobilenum;
    }

    public void setSeller_rest_mobilenum(String seller_rest_mobilenum)
    {
        this.seller_rest_mobilenum = seller_rest_mobilenum;
    }

    public String getSeller_rest_telnum()
    {
        return seller_rest_telnum;
    }

    public void setSeller_rest_telnum(String seller_rest_telnum)
    {
        this.seller_rest_telnum = seller_rest_telnum;
    }

    public String getSeller_verification()
    {
        return seller_verification;
    }

    public void setSeller_verification(String seller_verification)
    {
        this.seller_verification = seller_verification;
    }
}