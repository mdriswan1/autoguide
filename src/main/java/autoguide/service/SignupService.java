package autoguide.service;

import org.mindrot.jbcrypt.BCrypt;

import autoguide.dao.SignupDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * this class is used to insert the data in the database by using the DAO class(SignupDao) 
 */
public class SignupService implements Service{
	
	/**
	 * get the user details and convert the password in to hash(BCrpt third party jar),
	 *  and send the details to the SignupDAO class createUser() method
	 */
	@Override
	public boolean execute(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		String fullname=req.getParameter("fullname");
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		String city=req.getParameter("city");
		
		password=BCrypt.hashpw(password,BCrypt.gensalt(10));
		return SignupDao.createUser(fullname, email, password, city);
		
	}
}
