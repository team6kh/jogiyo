package common.registration.action;

import java.util.Calendar;

import user.seller.dto.SellerDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import common.ConDAOAware;

public class RegSellerAction implements Action, Preparable, ModelDriven, ConDAOAware
{
    private SqlMapClient sqlMapper;
    private SellerDTO sellerDTO;
    private Calendar today = Calendar.getInstance(); // 오늘 날짜 구하기

    public void setConDAO(SqlMapClient sqlMapper)
    {
        this.sqlMapper = sqlMapper;
    }

    public void prepare() throws Exception
    {
        sellerDTO = new SellerDTO();
    }

    public Object getModel()
    {
        return sellerDTO;
    }

    public String execute() throws Exception
    {
        sellerDTO.setSeller_reg_date(today.getTime());

        sqlMapper.insert("Seller.insertSeller", sellerDTO);

        return SUCCESS;
    }
}