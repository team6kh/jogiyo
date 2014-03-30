package board.test.action;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class ConDAOInterceptor implements Interceptor{
	ConDAO conDAO;

	public void init() {
		conDAO = new ConDAO();
	}

	public void destroy() {	
		conDAO = null;
	}

	public String intercept(ActionInvocation invocation) throws Exception {
		Object action = invocation.getAction(); // 액션의 객체 생성
		
		if (action instanceof ConDAOAware) { // 액션의 메서드가 Aware과 인터페이스 구현관계에 있으면
			ConDAOAware conDAOAware = (ConDAOAware) action;
			conDAOAware.setConDAO(conDAO); // 다시 액션메서드로 가서 conDAO호출함
		}
		return invocation.invoke();
	}
}
