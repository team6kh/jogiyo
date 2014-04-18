package user.seller.action;

import user.seller.dto.SellerDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.Action;

import common.ConDAOAware;

public class UpdateSellerAction implements Action, ConDAOAware
{
    private SqlMapClient sqlMapper;
    
    private SellerDTO sellerDTO = new SellerDTO();
    
    private String modalParam_key;
    
    // UPDATE에 쓰이는 파리미터들
    private String seller_id;
    private String seller_pw;
    private String seller_rest_name;
    private String seller_rest_address;
    private String seller_telnum;
    private String seller_mobilenum;
    private String seller_email;    

    public void setConDAO(SqlMapClient sqlMapper)
    {
        this.sqlMapper = sqlMapper;        
    }
    
    public String updateForm() throws Exception
    {
        System.out.println("UpdateSellerAction updateForm()");
        
        sellerDTO.setSeller_id(getModalParam_key());
        
        // 해당 유저의 정보를 가져온다.
        setSellerDTO((SellerDTO) sqlMapper.queryForObject("Seller.selectWhereSellerId", sellerDTO));
        
        return SUCCESS;
    }

    public String execute() throws Exception
    {
        // 수정할 항목 설정.
        sellerDTO.setSeller_id(getSeller_id());
        sellerDTO.setSeller_pw(getSeller_pw());
        sellerDTO.setSeller_rest_name(getSeller_rest_name());
        sellerDTO.setSeller_rest_address(getSeller_rest_address());
        sellerDTO.setSeller_telnum(getSeller_telnum());
        sellerDTO.setSeller_mobilenum(getSeller_mobilenum());
        sellerDTO.setSeller_email(getSeller_email());
        
        sqlMapper.update("Seller.updateSeller", sellerDTO);
        
        // 수정이 끝나면 view 페이지로 이동.
        sellerDTO = (SellerDTO) sqlMapper.queryForObject("Seller.selectWhereSellerId", sellerDTO);
        
        return SUCCESS;
    }
    
    public SellerDTO getSellerDTO()
    {
        return sellerDTO;
    }

    public void setSellerDTO(SellerDTO sellerDTO)
    {
        this.sellerDTO = sellerDTO;
    }

    public String getModalParam_key()
    {
        return modalParam_key;
    }

    public void setModalParam_key(String modalParam_key)
    {
        this.modalParam_key = modalParam_key;
    }

    // UPDATE에 쓰이는 getter & setter
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

    public String getSeller_telnum()
    {
        return seller_telnum;
    }

    public void setSeller_telnum(String seller_telnum)
    {
        this.seller_telnum = seller_telnum;
    }

    public String getSeller_mobilenum()
    {
        return seller_mobilenum;
    }

    public void setSeller_mobilenum(String seller_mobilenum)
    {
        this.seller_mobilenum = seller_mobilenum;
    }

    public String getSeller_email()
    {
        return seller_email;
    }

    public void setSeller_email(String seller_email)
    {
        this.seller_email = seller_email;
    }

}
