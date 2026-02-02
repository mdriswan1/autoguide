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
 * This the service class to call the LoginDao to validate the user details is vslid or not
 */

public class LoginService implements Service {
	private static final Logger logger = LogManager.getLogger(LoginService.class);
	private static final int loginCount = 3;

	/**
	 * get the data of user email and the password and validate the details from the data base based on the email validate email and
	 * password from the dao class
	 */
	@Override
	public boolean execute(HttpServletRequest req, HttpServletResponse res) {
		// int loginCount=Integer.parseInt((String) req.getSession().getAttribute("loginCount"));

		HttpSession session = req.getSession();

		// TODO Auto-generated method stub
		logger.debug("controll transefer login execute method");

		boolean flag = false;
		boolean credentialValidationFlag = credentialValidation(req);
		// if the attempts less than max then we will validate the email and password
		if (validateAttempts(req) == true && credentialValidationFlag) {
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			flag = LoginDao.userValidate(email, password);
			if (flag == true) {
				logger.debug("User Credentials is correct forward to home page");
				String name = LoginService.setName(email);
				session.setAttribute("name", name);

				// set the cookie if the user click the remember me option
				String remember = req.getParameter("remember");
				if (remember != null) {
					logger.debug("set the user name in cookies");
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

	/**
	 * validate the email and password
	 * 
	 * @param req
	 * @return
	 */
	private static boolean credentialValidation(HttpServletRequest req) {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		if (email != null) {
			email = email.trim();
		}
		if (email == null || email.isEmpty()) {
			req.setAttribute("error", "Space not allowed in the email");
			return false;
		}
		if (password != null) {
			password = password.trim();
		}
		if (password == null || password.isEmpty() || password.isBlank()) {
			req.setAttribute("error", "Space not allowed in the password");
			return false;
		}
		if (!email.contains("@") || !email.contains(".com")) {
			req.setAttribute("error", "Enter Valid Email");
			return false;
		}
		if (password.length() < 6) {
			req.setAttribute("error", "Password should be more than 6 character");
			return false;
		}
		return true;
	}

	/**
	 * this method is check the user attempts is less than 3(max attempts)
	 * 
	 * @param req
	 * @return true means less than max attempts, false means greater than max attempts
	 */
	private static boolean validateAttempts(HttpServletRequest req) {
		HttpSession session = req.getSession();
		int numberOfAttempts = (session.getAttribute("numberOfAttempts") == null) ? 1 : (int) session.getAttribute("numberOfAttempts");
		if (numberOfAttempts < loginCount) {
			logger.debug("User Login Attempts" + numberOfAttempts);
			session.setAttribute("numberOfAttempts", ++numberOfAttempts);
			return true;
		} else {
			logger.debug("User reaches the maximun Attempts");
			req.setAttribute("error", "You reached the maximum attempts dont try");
			return false;
		}
	}

	/**
	 * this methods used to get the name as per email for set in the session to display the user name
	 * 
	 * @param email
	 * @return user name
	 */
	public static String setName(String email) {
		return GetUserName.getUserName(email);
	}

}
