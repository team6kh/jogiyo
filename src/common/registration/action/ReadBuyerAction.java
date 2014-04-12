package common.registration.action;

import user.buyer.dto.BuyerDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.Action;
import common.ConDAOAware;

public class ReadBuyerAction implements Action, ConDAOAware
{
    private SqlMapClient sqlMapper;
    private BuyerDTO buyerDTO = new BuyerDTO();
    private String userId;

    public void setConDAO(SqlMapClient sqlMapper)
    {
        this.sqlMapper = sqlMapper;
    }

    public String execute() throws Exception
    {
        buyerDTO.setBuyer_id(getUserId());

        // 해당 유저의 정보를 가져온다.
        buyerDTO = (BuyerDTO) sqlMapper.queryForObject("Buyer.selectWhereBuyerId", buyerDTO);

        return SUCCESS;
    }

    public BuyerDTO getBuyerDTO()
    {
        return buyerDTO;
    }

    public void setBuyerDTO(BuyerDTO buyerDTO)
    {
        this.buyerDTO = buyerDTO;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }
}