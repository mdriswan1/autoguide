package autoguide.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ServiceConfig {
	private String className;
	private String success;
	private String failure;
	
	
	
	
	public ServiceConfig(String className, String success, String failure) {
		super();
		this.className = className;
		this.success = success;
		this.failure = failure;
	}




	public String getClassName() {
		return className;
	}




	public void setClassName(String className) {
		this.className = className;
	}




	public String getSuccess() {
		return success;
	}




	public void setSuccess(String success) {
		this.success = success;
	}




	public String getFailure() {
		return failure;
	}




	public void setFailure(String failure) {
		this.failure = failure;
	}




	public static Map<String,ServiceConfig> getService(Properties pro){
		
		Map<String, ServiceConfig> confiqservice=new HashMap<String, ServiceConfig>();
		
		if(pro!=null) {
			for(String key:pro.stringPropertyNames()) {
				String value=pro.getProperty(key);
				String names[]=value.split(",");
				String classname=names[0];
				String success="";
				String failure="";
				for(int i=0;i<names.length;i++) {
					String []pairs=names[i].split("=");
					if("success".equals(pairs[0])) {
						success=pairs[1];
					}else if("failure".equals(pairs[0])){
						failure=pairs[1];
					}
				}
				
				ServiceConfig config=new ServiceConfig(classname,success,failure);
				confiqservice.put(key, config);
				
			}
		}
		return confiqservice;
	}

}
