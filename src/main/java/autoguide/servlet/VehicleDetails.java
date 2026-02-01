package autoguide.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import autoguide.service.GetColumnService;
import autoguide.service.VehicleDetailsService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class VehicleDetails this servlet class is used for load data from the database sends json as a response
 * 
 */
@WebServlet("/api/vehicledetails/*")
public class VehicleDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(VehicleDetails.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VehicleDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// in this class we only retuen the response as json so we declared it as
		// application/json to inform
		response.setContentType("application/json");
		PrintWriter print = response.getWriter();
		String path = request.getPathInfo();
		String[] paths = {};
		if (path != null) {
			// if the path is not null then we divide the path depends the path give the
			// results
			paths = path.split("/");
			System.out.println(Arrays.toString(paths));
		}
		System.out.println(Arrays.toString(paths));
		/**
		 * first if is used to load the all vehicle type if the path is empty or / then load the all vehicle details
		 */

		if ("".equals(path) || "/".equals(path) || path.equals(null)) {
			logger.debug("load all vehicle details ");
			// if the path is empty or null to load the entire vehicle details
			String json = VehicleDetailsService.vehicleDetails();
			// return the details as a json(response)
			print.write(json);
		}

		/**
		 * used to load the drop down data, vehicletype->load the vehicle type (two_wheeler,four_wheeler), manufacturer->load the
		 * manufacturer name based on vehicle type(maruti suzuki), model_name->load the model based on the manufacturer(swift VXI).
		 */

		else if ("vehicletype".equals(paths[1])) {
			logger.debug("load all vehicle types ");
			// if the path contain vehicletype as a value we will return the vehicle type as
			// String as a json format
			print.write(GetColumnService.getVehicleType());
		} else if ("manufacturer".equals(paths[1])) {
			String type = paths[2];
			logger.debug("load all vehicle manufacturer depends on vehicle type " + type);
			// if the path contain manufacturer as a value we will return the manufacturer details as
			// String as a json format

			System.out.println(type);
			print.write(GetColumnService.getVehicleManufacturer(type));
		} else if ("model_name".equals(paths[1])) {
			String type = paths[2];
			logger.debug("load all vehicle  model_name depends on manufacturer  " + type);
			// if the path contain model_name as a value we will return the models as
			// String as a json format

			System.out.println(type);
			print.write(GetColumnService.getVehicleModel(type));
		}

		/**
		 * this are used to load the vehicle details, welcome->load the 3 vehicle details, vehicletypedata->load the vehicle details based
		 * on the vehicle type, manufacturerdata->load the vehicle details based on the manufacturer, getvehicle->load the the vehicle
		 * details based on the manufacturer and model.
		 */
		else if ("welcome".equals(paths[1])) {
			PrintWriter pw = response.getWriter();
			// this mehtod is used for get vehicle details for guest user
			logger.debug("load some vehicle to display for guest users");
			String json = VehicleDetailsService.getWelcomeDetails();
			pw.write(json);
		} else if ("vehicletypedata".equals(paths[1])) {
			PrintWriter pw = response.getWriter();
			String type = paths[2];
			// this method is used for load vehicle based o vehicle type eg.two_wheeler
			logger.debug("load vehicle based on vehicle type " + type);
			String json = VehicleDetailsService.vehicleTypeDetails(type);
			pw.write(json);
		} else if ("manufacturerdata".equals(paths[1])) {
			PrintWriter pw = response.getWriter();
			String type = paths[2];
			// this method is used for load vehicle based on manufacturer eg.tvs
			logger.debug("load vehicle based on manufacturer " + type);
			String json = VehicleDetailsService.manufacturerDetails(type);
			pw.write(json);
		} else if ("getvehicle".equals(paths[1])) {
			// if the path contain getvehicle as a value we will return the details of vehicle as
			// String as a json format
			String manu = paths[2];
			String model = paths[3];

			logger.debug("load all vehicle   depends on model_name and manufacturer " + model + ", " + manu);
			System.out.println(model);
			String json = VehicleDetailsService.vehicleDetails(manu, model);
			print.write(json);
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
