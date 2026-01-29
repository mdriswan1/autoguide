package autoguide.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import autoguide.dao.GetUserName;
import autoguide.dao.LoginDao;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * This the service class to call the LoginDao to validate the user details is
 * vslid or not
 */

public class LoginService implements Service {
	private static final Logger logger = LogManager.getLogger(LoginService.class);
	private static final int loginCount = 3;

	/**
	 * get the data of user email and the password and validate the details from the
	 * data base based on the email validate email and password from the dao class
	 */
	@Override
	public boolean execute(HttpServletRequest req, HttpServletResponse res) {
//		int loginCount=Integer.parseInt((String) req.getSession().getAttribute("loginCount"));

HttpSession session = req.getSession(false);

		// TODO Auto-generated method stub
		logger.debug("controll transefer login execute method");

		boolean flag = false;
		if (validateAttempts(req) == true) {
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			flag = LoginDao.userValidate(email, password);
			if (flag == true) {
				String name = LoginService.setName(email);
				session.setAttribute("name", name);
				
				//set the cookie 
				String remember = req.getParameter("remember");
				if (remember != null) {
					System.out.println("username:" + name);
					Cookie cookie = new Cookie("username", name);
					cookie.setMaxAge(7 * 60 * 60 * 24);
					res.addCookie(cookie);
				}

				return flag;
			} else {
				req.setAttribute("error", "Invalid Credentials!");
				return flag;
			}
		} else {
			return false;

		}

	}

	private static boolean validateAttempts(HttpServletRequest req) {
		HttpSession session = req.getSession();
		int numberOfAttempts = (session.getAttribute("numberOfAttempts") == null) ? 1
				: (int) session.getAttribute("numberOfAttempts");
		if (numberOfAttempts < loginCount) {
			session.setAttribute("numberOfAttempts", ++numberOfAttempts);
			return true;
		} else {
			req.setAttribute("error", "You reached the maximum attempts dont try");
			return false;
		}

	}

	public static String setName(String email) {
		return GetUserName.getUserName(email);
	}

}
