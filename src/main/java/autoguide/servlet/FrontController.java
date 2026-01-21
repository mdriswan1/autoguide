package autoguide.servlet;

import java.io.IOException;

import org.apache.catalina.Session;

import autoguide.service.Service;
import autoguide.service.ServiceConfig;
import autoguide.service.ServiceFactory;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class FrontController
 */
@WebServlet(urlPatterns ={"/controller"},initParams = {@WebInitParam(name="max", value="3")})
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean insert=false;
		
		HttpSession session= request.getSession();
		ServletConfig servletConfig=getServletConfig();
		int max=Integer.parseInt(servletConfig.getInitParameter("max"));
		
	
		//session.setAttribute("loginCount","0" );
		int count=(session.getAttribute("loginCount")==null)?0:(int) session.getAttribute("loginCount");
		// input from jsp
		String input=request.getParameter("input");
		if(input==null) {
			System.out.println("input is null");
		}
		
		//login or signup object 
//		ServiceConfig confiq=ServiceFactory.map.get(input);
		Service service=ServiceFactory.getInstance(input);
		insert =service.execute(request,response);
		
		
		
		System.out.println("login object getted");
		
		
		//get success and failure in serviceConfiq object
		ServiceConfig confiq=ServiceFactory.map.get(input);
		
		
		if(insert) {
			System.out.println("login successfully ");
			String forward=confiq.getSuccess();
			request.getRequestDispatcher(forward).forward(request, response);
		}else {
			
			if(input.equals("login")) {
				
				session.setAttribute("loginCount", count+1);
				System.out.println(count);
			}
			if(input.equals("login")&&count>=max) {
				
				request.setAttribute("error","you reached max attempts");
				String forward=confiq.getFailure();
				request.getRequestDispatcher(forward).forward(request, response);
				
			}else {
				request.setAttribute("error","invalid user");
				String forward=confiq.getFailure();
				request.getRequestDispatcher(forward).forward(request, response);
			}
			
		}
	
}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
