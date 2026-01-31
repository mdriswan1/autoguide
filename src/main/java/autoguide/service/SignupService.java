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
		String fullname = req.getParameter("fullname").trim();
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
		if (fullname == null || email == null || password == null || confirmpassword == null) {
			req.setAttribute("error", "Please fill the mandotary fields");
			return false;
		}
		fullname = fullname.trim();
		if (fullname == null || fullname.isEmpty()) {
			req.setAttribute("error", "Enter valid user name");
			return false;
		}
		if (email.contains(" ") || !email.contains("@") || !email.contains(".com")) {
			req.setAttribute("error", "Enter valid email");
			return false;
		}
		if (password.length() < 6) {
			req.setAttribute("error", "password should be more than 6 character");
			return false;
		}
		if (!password.equals(confirmpassword)) {
			req.setAttribute("error", "password and confirm password should be same");
			return false;
		}
		return true;
	}
}
