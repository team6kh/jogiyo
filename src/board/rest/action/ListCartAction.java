package board.rest.action;

import java.util.ArrayList;
import java.util.List;

import board.cart.dto.CartDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.Action;

import common.ConDAOAware;

public class ListCartAction implements Action, ConDAOAware
{
    private SqlMapClient sqlMapper;
    private List<CartDTO> list = new ArrayList<CartDTO>();
    private int rest_num;
    
    
    
    //ConDAOAware 인터페이스의 메서드(인터셉터에서 호출)
    public void setConDAO(SqlMapClient sqlMapper) { 
        this.sqlMapper = sqlMapper;
    }    
    
    public String execute() throws Exception {
        list = sqlMapper.queryForList("Rest.selectCartAll");
        
        rest_num = list.get(0).getCart_rest_num();
        
        return SUCCESS;
    }

    
    public List<CartDTO> getList(){
        return list;
    }
    public void setList(List<CartDTO> list){
        this.list = list;
    }
	public int getRest_num() {
		return rest_num;
	}
    
}
