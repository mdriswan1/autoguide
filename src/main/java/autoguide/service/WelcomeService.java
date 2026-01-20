package autoguide.service;

import autoguide.dao.WelcomeDao;

public class WelcomeService {
	public static String getWelcomeDetails() {
		return WelcomeDao.getsomeDetails();
	}
}
