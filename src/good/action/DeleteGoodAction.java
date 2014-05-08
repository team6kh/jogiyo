package good.action;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import common.ConDAOAware;

public class DeleteGoodAction implements Action, Preparable, ModelDriven, ConDAOAware
{

	@Override
	public void setConDAO(SqlMapClient sqlMapper)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getModel()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void prepare() throws Exception
	{
		// TODO Auto-generated method stub
		
	}

	public String execute() throws Exception
	{
		
		return SUCCESS;
	}
	
}
