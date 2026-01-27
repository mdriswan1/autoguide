 package autoguide.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import autoguide.dao.GetUserName;
import autoguide.dao.LoginDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * This the service class to call the LoginDao to validate the user details is vslid or not
 */

public class LoginService implements Service {
	  private static final Logger logger=LogManager.getLogger(LoginService.class);
/**
 * get the data of user email and the password and validate the details from the data base based on the email 
 * validate email and password from the dao class
 */
	@Override
	public boolean execute(HttpServletRequest req, HttpServletResponse res) {
//		int loginCount=Integer.parseInt((String) req.getSession().getAttribute("loginCount"));
		
		
		// TODO Auto-generated method stub
		logger.debug("controll transefer login execute method");
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		
//		if(login)
		
		//
		boolean flag= LoginDao.userValidate(email, password);
		if(flag) {
			req.getSession().setAttribute("name", GetUserName.getUserName(email));
		}
		return flag;
	}
}
