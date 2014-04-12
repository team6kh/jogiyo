package common.registration.action;

import user.buyer.dto.BuyerDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.Action;

import common.ConDAOAware;

public class DeleteBuyerAction implements Action, ConDAOAware
{

	private SqlMapClient sqlMapper; // SqlMapClient API를 사용하기 위한 sqlMapper 객체

	private BuyerDTO buyerDTO = new BuyerDTO();

	private String modalParam_key;

	public void setConDAO(SqlMapClient sqlMapper)
	{
		this.sqlMapper = sqlMapper;
	}

	public String execute() throws Exception
	{

		// 삭제할 항목 설정
		buyerDTO.setBuyer_id(getModalParam_key());

		// 삭제 쿼리 수행
		sqlMapper.update("Buyer.deleteBuyer", buyerDTO);

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

}
