package autoguide.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Service {
	boolean execute(HttpServletRequest req,HttpServletResponse res) ;
}
