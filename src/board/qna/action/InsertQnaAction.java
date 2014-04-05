package board.qna.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class InsertQnaAction implements Action, Preparable,ModelDriven{

	public String execute() throws Exception {

		return SUCCESS;
	}

	
	public Object getModel() {
		
		return null;
	}


	public void prepare() throws Exception {
		
		
	}

}
