package daos;

import java.sql.*;
import config.DatabaseConnection;
import models.SubscriptionStatus;

public class SubscriptionStatusDao {
	
	private static String nameTable = "subscription_status";
	
	public static synchronized void createTablePlans() throws Exception {
		try {
			Connection connection = DatabaseConnection.getConnection();
			DatabaseMetaData dbm = connection.getMetaData();
			ResultSet tables = dbm.getTables(null, null, nameTable, null);
			if (!tables.next()) {		
				PreparedStatement create = connection.prepareStatement("CREATE TABLE IF NOT EXISTS "
						+ nameTable + "(id INT NOT NULL AUTO_INCREMENT,"
						+ "name VARCHAR(255) NOT NULL,"
						+ "PRIMARY KEY(id))"
						);
				create.executeUpdate();
				seedTable();
			}
			connection.close();
		} catch (Exception error) {
			System.out.println(error);
			
		}
		finally {
			System.out.println("Function completed");
		}
	}
	
	private static void seedTable() throws Exception {
		SubscriptionStatus delayedStatus = new SubscriptionStatus("Atrasado");
		SubscriptionStatus pendentStatus = new SubscriptionStatus("Pendente");
		SubscriptionStatus paidStatus = new SubscriptionStatus("Pago");
		insert(delayedStatus);
		insert(pendentStatus);
		insert(paidStatus);
	}
	
	
	public static synchronized SubscriptionStatus getById(int id) throws Exception {
		try {
			Connection connection = DatabaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * from " + nameTable + " WHERE id = " + id);
			
			ResultSet result = statement.executeQuery();
			
			SubscriptionStatus subscriptionCatched = new SubscriptionStatus();
			
			while(result.next()) {
				SubscriptionStatus subscription = new SubscriptionStatus(result.getInt("id"), result.getString("name")); 
				subscriptionCatched = subscription;
			}
			connection.close();
			 
			return subscriptionCatched;
		} catch (Exception error){
			System.out.println(error);
			return null;
		}
	}
	
	public static void insert(SubscriptionStatus subscriptionStatus) throws Exception {
		try {
			Connection connection = DatabaseConnection.getConnection();
			PreparedStatement posted = connection.prepareStatement("INSERT INTO " + nameTable + "(name)"
					+ " VALUES (?)");
			posted.setString(1, subscriptionStatus.getName());
			posted.executeUpdate();
			connection.close();
			
		} catch (Exception error) {
			System.out.println(error);
		}
	}
	
}
