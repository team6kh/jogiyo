package user.seller.action;

import user.seller.dto.SellerDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.Action;

import common.ConDAOAware;

public class DeleteSellerAction implements Action, ConDAOAware
{
    private SqlMapClient sqlMapper; // SqlMapClient API를 사용하기 위한 sqlMapper 객체
    
    private SellerDTO sellerDTO =  new SellerDTO();
    
    private String modalParam_key;
    
    public void setConDAO(SqlMapClient sqlMapper)
    {
        this.sqlMapper = sqlMapper;        
    }
    
    public String execute() throws Exception
    {
        // 삭제할 항목 설정
        sellerDTO.setSeller_id(getModalParam_key());
        
        // 삭제 쿼리 수행
        sqlMapper.update("Seller.deleteSeller", sellerDTO);
        
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
    
}
