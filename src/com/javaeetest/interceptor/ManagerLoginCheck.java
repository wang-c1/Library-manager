package com.javaeetest.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class ManagerLoginCheck extends AbstractInterceptor{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation ai) throws Exception {
		Map session = ai.getInvocationContext().getSession();
		String managerName = (String)session.get("managerLoginName");
		
		System.out.println("managerLoginName:"+managerName);
		if(managerName!=null && managerName.length()!=0){
			return ai.invoke();
		}else{
			((ActionSupport)ai.getAction()).addActionError("您还没有登陆");
			return Action.LOGIN;
		}
		
		/*HttpServletRequest request = ServletActionContext.getRequest();
		String currentURL = request.getRequestURI();
		if(currentURL.contains("manage")){
			if(!(managerName == null || "".equals(managerName.trim()))){
				return "managerIsLogin";
			}else{
				return "managerShouldLogin";
			}
		}else{
			return ai.invoke();
		}*/
	}

}
