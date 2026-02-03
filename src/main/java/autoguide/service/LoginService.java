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
		String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
		String passwordRegex = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*]).{8,}$";
		// check the email is empty or not
		if ("".equals(email)) {
			req.setAttribute("error", "Enter the Email");
			return false;
		}
		// check the email is in correct format and not be blank or empty
		if (email != null) {
			if (email.isEmpty() || email.isBlank() || !email.matches(emailRegex)) {
				req.setAttribute("error", "Enter the valid email");
				return false;
			}
		} else {
			// null means denote the error
			logger.error("parameter of email is null");
			req.setAttribute("error", "Enter the valid email");
			return false;
		}
		/**
		 * validate the password if the password is empty or not
		 */
		if ("".equals(password)) {
			req.setAttribute("error", "Enter the Password");
			return false;
		}
		/**
		 * If not empty then validate the password
		 */
		if (password != null) {
			// check the password length
			if (password.length() < 8) {
				req.setAttribute("error", "Password must be more than 8 character");
				return false;
			}
			// check the password contain space or not
			if (password.matches("(?=.*[ ])")) {
				req.setAttribute("error", "Password not conatin space");
				return false;
			}
			// finally check the password match with the pattern
			if (!password.matches(passwordRegex)) {
				req.setAttribute("error", "Password must contain one uppercase letter and special character");
				return false;
			}
		} else {

			logger.error("parameter of password is null");
			req.setAttribute("error", "Enter the valid Password");
			return false;
		}
		// if all the validation is correct then return true
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
