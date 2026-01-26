package autoguide.service;

import java.io.IOException;


import java.io.InputStream;
import java.util.Map;
import java.util.Properties;


/**
 * this class is used to create the object for the service class
 */
public class ServiceFactory {

	public static  Map<String , ServiceConfig> map=null;
	static Properties properties=new Properties();
	
	
	static {
		try (
			InputStream fr=ServiceFactory.class.getClassLoader().getResourceAsStream("/autoguide/resource/resource.properties")){
			properties.load(fr);
			
			if(properties!=null) {
				map=ServiceConfig.getService(properties);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException("fail to load the class name ",e);
		}
	}
	
	/**
	 * get the input, depends on the input get the serviceConfig object 
	 * get the class_name from serviceConfig object and create the object and return it back
	 * @param input
	 * @return service Object
	 */
	public static Service getInstance(String input) {//login
		try {
			if(input==null) {
				System.out.println("input is null");
				return null;
			}
			
			String serviceClassName=map.get(input).getClassName();
			System.out.println("class name "+serviceClassName);
			if(serviceClassName==null) {
				return null;
			}
			System.out.println("get command");
			Class<?> clazz=Class.forName(serviceClassName);
			return (Service) clazz.getDeclaredConstructor().newInstance();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException("unable to create command action "+ input ,e);
		}
		
 	}
	
}
