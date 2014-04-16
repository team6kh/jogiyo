package user.seller.action;

import user.seller.dto.SellerDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.Action;
import common.ConDAOAware;

public class ReadSellerAction implements Action, ConDAOAware
{
    private SqlMapClient sqlMapper;
    private SellerDTO sellerDTO = new SellerDTO();
    private String user_id; // jsp에서 넘어오는 user_id
    
    public void setConDAO(SqlMapClient sqlMapper)
    {
        this.sqlMapper = sqlMapper;
    }
    
    public String execute() throws Exception
    {
        sellerDTO.setSeller_id(getUser_id());
        
        // 해당 유저의 정보를 가져온다.
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

    public String getUser_id()
    {
        return user_id;
    }

    public void setUser_id(String user_id)
    {
        this.user_id = user_id;
    }

}
