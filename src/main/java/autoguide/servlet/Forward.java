package autoguide.servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class Forward
 */
@WebServlet("/forward")
public class Forward extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Forward() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Cookie[] cookie=request.getCookies();
		
		if(cookie!=null) {
			for (Cookie cookie2 : cookie) {	
				if(cookie2.getName().equals("username")) {
					request.getSession().setAttribute("name", cookie2.getValue());
					cookie2.setMaxAge(7*60*60*24);
					response.addCookie(cookie2);
					System.out.println("--------------------------------------------------------------------------------forwardLogin");
					RequestDispatcher requestDispatcher=request.getRequestDispatcher("controller?input=forwardlogin");
					requestDispatcher.forward(request, response);
					return;
				}
			}
			
		}
		System.out.println("-------------------------------------------------------------------------------forward");
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("controller?input=forward");
		requestDispatcher.forward(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
