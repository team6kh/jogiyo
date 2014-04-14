package user.common.registration.action;

import user.buyer.dto.BuyerDTO;
import user.seller.dto.SellerDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.Action;

import common.ConDAOAware;

public class CheckDuplicateAction implements Action, ConDAOAware
{
    private SqlMapClient sqlMapper;

    private String reg_type; // 받아오는 가입타입에 따라 다른 DB에서 중복체크를 한다.
    private String reg_id;
    private int isDup;

    public void setConDAO(SqlMapClient sqlMapper)
    {
        this.sqlMapper = sqlMapper;
    }
    
    public String getReg_type()
    {
        return reg_type;
    }
   
    public void setReg_type(String reg_type)
    {
        this.reg_type = reg_type;
    }
    
    public String getReg_id()
    {
        return reg_id;
    }

    public void setReg_id(String reg_id)
    {
        this.reg_id = reg_id;
    }

    public int getIsDup()
    {
        return isDup;
    }

//    public void setIsDup(int isDup)
//    {
//        this.isDup = isDup;
//    }

    public String execute() throws Exception
    {
        BuyerDTO buyerDTO = new BuyerDTO();
        SellerDTO sellerDTO = new SellerDTO();

        buyerDTO.setBuyer_id(getReg_id());
        sellerDTO.setSeller_id(getReg_id());

        buyerDTO = (BuyerDTO) sqlMapper.queryForObject("Buyer.selectWhereBuyerId", buyerDTO);
        sellerDTO = (SellerDTO) sqlMapper.queryForObject("Seller.selectWhereSellerId", sellerDTO);

        if (buyerDTO != null || sellerDTO != null)
        {
            isDup = 1;
        } else
        {
            isDup = 0;
        }

        return SUCCESS;
    }
}