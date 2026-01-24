package autoguide.servlet;

import java.io.IOException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
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
 * 
 * this class is used for receiving signup,login,logout request 
 * validate the user credentials and forward to respected pages
 * 
 */

@WebServlet(urlPatterns ={"/controller"},initParams = {@WebInitParam(name="max", value="3")})
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//the variable for write the log
    private static final Logger logger=LogManager.getLogger(FrontController.class);
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
		//load the init param to validate user attempt
		ServletConfig servletConfig=getServletConfig();
		int max=Integer.parseInt(servletConfig.getInitParameter("max"));
		
	
		//session.setAttribute("loginCount","0" ); initially getAttribute is null
		int count=(session.getAttribute("loginCount")==null)?1:(int) session.getAttribute("loginCount");
		// input from jsp
		String input=request.getParameter("input");
		if(input==null) {
			System.out.println("input is null");
		}
		
		//login or signup object 
		//ServiceConfig confiq=ServiceFactory.map.get(input);
		Service service=ServiceFactory.getInstance(input);
		logger.debug("user try to "+ input);
		insert =service.execute(request,response);
		
		
		
		System.out.println("login object getted");
		
		
		//get success and failure in serviceConfiq object
		ServiceConfig confiq=ServiceFactory.map.get(input);
		
		
		if(insert) {
			System.out.println(count +"inside");
			if(input.equals("signup")) {
				//if user successfully signup
				System.out.println("signup successfully ");
				logger.debug("successfully signup forword to login page");
				String forward=confiq.getSuccess();
				
				request.getRequestDispatcher(forward).forward(request, response);
			}else if(input.equals("login")&&count<=max) {
				//if user enter correct credential within max count then forward to home page
				System.out.println("login successfully ");
				
				logger.debug("login succesfully at attempt :"+count + " email :"+request.getParameter("email"));
				logger.debug("Forward to home page");
				String forward=confiq.getSuccess();
				request.getRequestDispatcher(forward).forward(request, response);
			}else if(input.equals("login")) {
				//if user reached maximum attempts then give correct details it will return blocked
				logger.debug("user reached the maximum attempts");
				request.setAttribute("error","you reached max attempts and your are blocked");
				String forward=confiq.getFailure();
				request.getRequestDispatcher(forward).forward(request, response);
			}
		}else {
			
			if(input.equals("login")) {
				//if user give wrong credential count the attempt
				logger.debug("login fail attempt:"+count +" email :"+request.getParameter("email"));
				session.setAttribute("loginCount", count+1);			
				System.out.println(count);
			}else if(input.equals("signup")) {
				//if email already exist
				request.setAttribute("error","email already exist");
				String forward=confiq.getFailure();
				request.getRequestDispatcher(forward).forward(request, response);
			}
			if(input.equals("login")&&count>=max) {
				//if user reach maximum attempts
				logger.debug("user reached the maximum attempts");
				request.setAttribute("error","you reached max attempts and your are blocked");
				String forward=confiq.getFailure();
				request.getRequestDispatcher(forward).forward(request, response);
				
			}else {
				//if invalid user
				logger.debug("invalid user forward to the login page, again to login ");
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
