package autoguide.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * this is interface to achieve the command pattern and loss coupling
 */

public interface Service {
	boolean execute(HttpServletRequest req,HttpServletResponse res) ;
}
