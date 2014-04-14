package user.buyer.action;

import user.buyer.dto.BuyerDTO;
import board.test.dto.TestDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.Action;

import common.ConDAOAware;

public class UpdateBuyerAction implements Action, ConDAOAware
{
    private SqlMapClient sqlMapper;

    private BuyerDTO buyerDTO = new BuyerDTO();

    private String modalParam_key;
    private String buyer_id;

    // UPDATE에 쓰이는 파라미터들
    private String buyer_pw;
    private String buyer_phonenumber;
    private String buyer_email;

    public void setConDAO(SqlMapClient sqlMapper)
    {
        this.sqlMapper = sqlMapper;
    }

    public String updateForm() throws Exception
    {
        buyerDTO.setBuyer_id(getModalParam_key());

        // 해당 유저의의 정보를 가져온다.
        setBuyerDTO((BuyerDTO) sqlMapper.queryForObject("Buyer.selectWhereBuyerId", buyerDTO));

        return SUCCESS;
    }

    public String execute() throws Exception
    {
        // 수정할 항목 설정.
        buyerDTO.setBuyer_id(getBuyer_id());
        buyerDTO.setBuyer_pw(getBuyer_pw());
        buyerDTO.setBuyer_phonenumber(getBuyer_phonenumber());
        buyerDTO.setBuyer_email(getBuyer_email());

        sqlMapper.update("Buyer.updateBuyer", buyerDTO);

        // 수정이 끝나면 view 페이지로 이동.
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

    public String getModalParam_key()
    {
        return modalParam_key;
    }

    public void setModalParam_key(String modalParam_key)
    {
        this.modalParam_key = modalParam_key;
    }

    public String getBuyer_id()
    {
        return buyer_id;
    }

    public void setBuyer_id(String buyer_id)
    {
        this.buyer_id = buyer_id;
    }

    // UPDATE에 쓰이는 getter & setter
    public String getBuyer_pw()
    {
        return buyer_pw;
    }

    public void setBuyer_pw(String buyer_pw)
    {
        this.buyer_pw = buyer_pw;
    }

    public String getBuyer_phonenumber()
    {
        return buyer_phonenumber;
    }

    public void setBuyer_phonenumber(String buyer_phonenumber)
    {
        this.buyer_phonenumber = buyer_phonenumber;
    }

    public String getBuyer_email()
    {
        return buyer_email;
    }

    public void setBuyer_email(String buyer_email)
    {
        this.buyer_email = buyer_email;
    }
}