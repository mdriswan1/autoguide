package autoguide.servlet;

import java.io.IOException;

import java.util.Map;

import autoguide.service.Service;
import autoguide.service.ServiceConfig;
import autoguide.service.ServiceFactory;
import autoguide.service.SignupService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Signup
 */
@WebServlet("/Signup")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		boolean insert=false;

			String input=request.getParameter("input");
			if(input==null) {
				System.out.println("input is null");
			}
			Service service=ServiceFactory.getInstance(input);
			
			insert =service.execute(request,response);
			System.out.println("login object getted");
			ServiceConfig confiq=ServiceFactory.map.get(input);
			
			
			if(insert) {
				System.out.println("login successfully ");
				String forward=confiq.getSuccess();
				request.getRequestDispatcher(forward).forward(request, response);
			}else {
				String forward=confiq.getFailure();
				request.getRequestDispatcher(forward).forward(request, response);
			}
		
	}

}
