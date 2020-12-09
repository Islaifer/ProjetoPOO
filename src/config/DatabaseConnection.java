package config;

import java.sql.Connection;
import java.sql.DriverManager;

import daos.PaymentDao;
import daos.PlanDao;
import daos.ReservationDao;
import daos.SpaceDao;
import daos.SubscriptionDao;
import daos.SubscriptionStatusDao;
import daos.UserDao;

public class DatabaseConnection {
	public static Connection getConnection() throws Exception {
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/clubebolinha?useTimezone=true&serverTimezone=UTC";
			String username = "fallguys";
			String password = "1234@qwer";
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(url,username,password);
			return connection;
		}catch (Exception error) {
			System.out.println(error);
	
		}
	
		return null;
	}
	
	public static void createTables() throws Exception {
		PlanDao.createTablePlans();
		UserDao.createTableUser();
		SpaceDao.createTableSpaces();
		ReservationDao.createTableReservations();
		SubscriptionStatusDao.createTablePlans();
		SubscriptionDao.createTableSubscriptions();
		PaymentDao.createTablePayments();
	}
}
