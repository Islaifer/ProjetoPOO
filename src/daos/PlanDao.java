package daos;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import config.DatabaseConnection;
import models.Plan;

public class PlanDao {
	
	private static String nameTable = "plans";
	
	public static synchronized void createTablePlans() throws Exception {
		try {
			Connection connection = DatabaseConnection.getConnection();
			DatabaseMetaData dbm = connection.getMetaData();
			ResultSet tables = dbm.getTables(null, null, nameTable, null);
			if (!tables.next()) {		
				PreparedStatement create = connection.prepareStatement("CREATE TABLE IF NOT EXISTS "
						+ nameTable + "(id INT NOT NULL AUTO_INCREMENT,"
						+ "name VARCHAR(255) NOT NULL,"
						+ "price DOUBLE NOT NULL,"
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
		Plan simplePlan = new Plan("Simples", 10.0);
		Plan goldPlan = new Plan("Gold", 19.0);
		Plan platinumPlan = new Plan("Platinum", 29.0);
		insert(simplePlan);
		insert(goldPlan);
		insert(platinumPlan);
	}
	
	public static synchronized List<Plan> get() throws Exception {
		try {
			Connection connection = DatabaseConnection.getConnection();
			//MÉTODO PARA BUSCAR TODOS OS ITENS DENTRO DO BANCO DE DADOS DA TABELA
			PreparedStatement statement = connection.prepareStatement("SELECT * from " + nameTable);
			
			//DENTRO DO RESULT ESTARÁ OS DADOS QUE EU QUERO CASO TENHA DADO SUCESSO
			ResultSet result = statement.executeQuery();
			
			List<Plan> plans = new LinkedList<Plan>();
			
			//AQUI FARA UM WHILE QUE ENQUANTO EXISTIR "PRÓXIMO" DADO ELE CONTINUARA NO WHILE.
			while(result.next()) {
				//CADA GET DESSES AQUI EMBAIXO SÃO OS NOMES DA COLUNA QUE EU QUERO O RESULTADO
				Plan plan = new Plan(result.getInt("id"), result.getString("name"), result.getDouble("price")); 
				plans.add(plan);
			}
			connection.close();
			
			return plans;
		} catch (Exception error){
			System.out.println(error);
			return null;
		}
	}
	
	public static synchronized Plan getById(int id) throws Exception {
		try {
			Connection connection = DatabaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * from " + nameTable + " WHERE id = " + id);
			
			ResultSet result = statement.executeQuery();
			
			Plan planCatched = new Plan();
			
			while(result.next()) {
				Plan plan = new Plan(result.getInt("id"), result.getString("name"), result.getDouble("price")); 
				planCatched = plan;
			}
			connection.close();
			 
			return planCatched;
		} catch (Exception error){
			System.out.println(error);
			return null;
		}
	}
	
	public static void insert(Plan plan) throws Exception {
		try {
			Connection connection = DatabaseConnection.getConnection();
			PreparedStatement posted = connection.prepareStatement("INSERT INTO plans(name, price)"
					+ " VALUES (?,?)");
			posted.setString(1, plan.getName());
			posted.setDouble(2, plan.getPrice());
			posted.executeUpdate();
			connection.close();
			
		} catch (Exception error) {
			System.out.println("ta aqui?");
			System.out.println(error);
		}
	}
	
	public static synchronized void deleteById(int id) throws Exception {
		try {
			Connection connection = DatabaseConnection.getConnection();
			PreparedStatement delete = connection.prepareStatement("DELETE from " + nameTable + " WHERE id = " + id);
			
			delete.executeUpdate();
			
			connection.close();		
		} catch (Exception error){
			System.out.println(error);
		}
	}
	
}
