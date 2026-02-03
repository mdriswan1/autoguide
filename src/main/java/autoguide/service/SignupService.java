package autoguide.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import autoguide.dao.SignupDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * this class is used to insert the data in the database by using the DAO class(SignupDao)
 */
public class SignupService implements Service {
	private static final Logger logger = LogManager.getLogger(SignupService.class);

	/**
	 * get the user details and convert the password in to hash(BCrpt third party jar), and send the details to the SignupDAO class
	 * createUser() method
	 */
	@Override
	public boolean execute(HttpServletRequest req, HttpServletResponse res) {

		// TODO Auto-generated method stub
		logger.debug("controll transefer signup execute method");
		String fullname = req.getParameter("fullname");
		if (fullname != null) {
			fullname = fullname.trim();
		}
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String city = req.getParameter("city");
		boolean flag = false;
		boolean validationFlag = validation(req);
		if (validationFlag) {
			password = BCrypt.hashpw(password, BCrypt.gensalt(10));
			flag = SignupDao.createUser(fullname, email, password, city);
		} else {
			return false;
		}
		if (!flag) {
			req.setAttribute("error", "Email is already exist");
		}
		return flag;
	}

	/**
	 * validate the user name, email, password and confirm password
	 * 
	 * @param req
	 * @return
	 */
	private static boolean validation(HttpServletRequest req) {
		String fullname = req.getParameter("fullname");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String confirmpassword = req.getParameter("confirmpassword");
		String fullnameRegex = "^[A-Za-z]+(?:\\s+[A-Za-z]+)*$";
		String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
		String passwordRegex = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*]).{8,}$";
		if ("".equals(fullname)) {
			req.setAttribute("error", "Enter the name");
			return false;
		}
		if (fullname != null) {
			if (fullname.isEmpty() || fullname.isBlank() || !fullname.matches(fullnameRegex)) {
				req.setAttribute("error", "Enter the valid name");
				return false;
			}
		}
		if ("".equals(email)) {
			req.setAttribute("error", "Enter the Email");
			return false;
		}
		if (email != null) {
			if (email.isEmpty() || email.isBlank() || !email.matches(emailRegex)) {
				req.setAttribute("error", "Enter the valid email");
				return false;
			}
		} else {
			req.setAttribute("error", "Enter the valid email");
			return false;
		}
		if ("".equals(password)) {
			req.setAttribute("error", "Enter the Password");
			return false;
		}
		if (password != null) {
			if (password.length() < 8) {
				req.setAttribute("error", "Password must be more than 8 character");
				return false;
			}
			if (password.matches("(?=.*[ ])")) {
				req.setAttribute("error", "Password not conatin space");
				return false;
			}
			if (!password.matches(passwordRegex)) {
				req.setAttribute("error", "Password must contain one uppercase letter and special character");
				return false;
			}
		} else {
			req.setAttribute("error", "Enter the valid Password");
			return false;
		}
		if ("".equals(confirmpassword)) {
			req.setAttribute("error", "Enter the Confirm Password");
			return false;
		}
		if (confirmpassword != null) {
			if (!confirmpassword.equals(password)) {
				req.setAttribute("error", "Password and Confirm Password must be same");
				return false;
			}
		} else {
			req.setAttribute("error", "Enter the valid Confirm Password");
			return false;
		}
		return true;
	}
}
