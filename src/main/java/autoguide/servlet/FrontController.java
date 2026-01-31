package autoguide.servlet;

import java.io.IOException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import autoguide.service.LoginService;
import autoguide.service.Service;
import autoguide.service.ServiceConfig;
import autoguide.service.ServiceFactory;
import autoguide.service.VehicleDetailsService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
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

@WebServlet(urlPatterns ={"/controller"})
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
		
		HttpSession session= request.getSession(false);	
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
			String forward=confiq.getSuccess();
			request.getRequestDispatcher(forward).forward(request, response);
		}else {
			String forward=confiq.getFailure();
			request.getRequestDispatcher(forward).forward(request, response);
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
