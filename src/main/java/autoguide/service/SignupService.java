package autoguide.service;

import org.mindrot.jbcrypt.BCrypt;

import autoguide.dao.SignupDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SignupService implements Service{
	

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
