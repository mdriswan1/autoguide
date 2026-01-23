 package autoguide.service;

import autoguide.dao.GetUserName;
import autoguide.dao.LoginDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginService implements Service {

	@Override
	public boolean execute(HttpServletRequest req, HttpServletResponse res) {
//		int loginCount=Integer.parseInt((String) req.getSession().getAttribute("loginCount"));
		
		
		// TODO Auto-generated method stub
		
		String email=req.getParameter("email");
		String password=req.getParameter("password");
//		if(login)
		boolean flag= LoginDao.userValidate(email, password);
		if(flag) {
			req.getSession().setAttribute("name", GetUserName.getUserName(email));
		}
		return flag;
	}
}
