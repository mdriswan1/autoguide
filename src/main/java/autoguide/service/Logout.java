package autoguide.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Logout implements Service {
	private static final Logger logger = LogManager.getLogger(Logout.class);

	@Override
	public boolean execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		logger.debug("user logout the session and forward to the login page" + request.getAttribute("email"));

		if (session != null) {
			logger.debug("session Invalid");
			session.invalidate();
		}
		Cookie cookie = new Cookie("username", "");
		logger.debug("remove the cookie details");
		// cookie.setPath("/");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		return true;

	}

}
