package good.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import common.ConDAOAware;

public class ListGoodAction  implements Action, Preparable, ModelDriven, ConDAOAware
{

	public void setConDAO(SqlMapClient sqlMapper)
	{
		
	}

	public Object getModel()
	{
		return null;
	}

	public void prepare() throws Exception
	{
		
	}

	public String execute() throws Exception
	{
		return SUCCESS;
	}

	
}




