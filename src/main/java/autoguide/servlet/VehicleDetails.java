package autoguide.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

import autoguide.service.GetColumnService;
import autoguide.service.VehicleDetailsService;

/**
 * Servlet implementation class VehicleDetails
 */
@WebServlet("/api/vehicledetails/*")
public class VehicleDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VehicleDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		PrintWriter print = response.getWriter();
		String path = request.getPathInfo();
		String[] paths= {};
		if(path!=null)
			paths = path.split("/");
		System.out.println(Arrays.toString(paths));
		if ("".equals(path) || "/".equals(path) || path.equals(null)) {
			String json = VehicleDetailsService.vehicleDetails();
			print.write(json);
		} else if ("vehicletype".equals(paths[1])) {
			print.write(GetColumnService.getVehicleType());
		} else if ("manufacturer".equals(paths[1])) {
			String type = paths[2];
			System.out.println(type);
			print.write(GetColumnService.getVehicleManufacturer(type));
		} else if ("model_name".equals(paths[1])) {
			String type = paths[2];
			System.out.println(type);
			print.write(GetColumnService.getVehicleModel(type));
		}else if("getvehicle".equals(paths[1])) {
			String type = paths[2];
			System.out.println(type);
			String json = VehicleDetailsService.vehicleDetails(type);
			print.write(json);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
