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
    private String rest_subject;
    
    
    //ConDAOAware 인터페이스의 메서드(인터셉터에서 호출)
    public void setConDAO(SqlMapClient sqlMapper) { 
        this.sqlMapper = sqlMapper;
    }    
    
    public String execute() throws Exception {
        list = sqlMapper.queryForList("Rest.selectCartAll");
        
        return SUCCESS;
    }

    

	public List<CartDTO> getList(){
        return list;
    }
    public void setList(List<CartDTO> list){
        this.list = list;
    }
    public void setRest_num(int rest_num) {
		this.rest_num = rest_num;
	}
	public int getRest_num() {
		return rest_num;
	}
	public String getRest_subject() {
		return rest_subject;
	}
	public void setRest_subject(String rest_subject) {
		this.rest_subject = rest_subject;
	}
    
}
