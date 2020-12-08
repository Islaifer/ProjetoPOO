package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import config.DatabaseConnection;
import models.Plan;
import models.User;

public class UserDao {
	private static String nameTable = "users";
	
	public static synchronized void createTableUser() throws Exception {
		try {
			//pega conex�o com o banco de dados
			Connection connection = DatabaseConnection.getConnection();
			//PreparedStatement � o metodo na sua tradu��o prepara a declara��o que ir� ser introduzida no SQL, no contexto � o m�todo de cria��o de tabela.
			PreparedStatement create = connection.prepareStatement("CREATE TABLE IF NOT EXISTS "
					+ nameTable +"(id INT NOT NULL AUTO_INCREMENT,"
					+ "first_name VARCHAR(255) NOT NULL,"
					+ "last_name VARCHAR(255) NOT NULL,"
					+ "rg INT NOT NULL,"
					+ "cpf INT NOT NULL,"
					+ "birthdate DATE,"
					+ "phone_number INT NOT NULL,"
					+ "address VARCHAR(255) NOT NULL,"
					+ "address_number VARCHAR(255) NOT NULL,"
					+ "state VARCHAR(255) NOT NULL,"
					+ "city VARCHAR(255) NOT NULL,"
					+ "plan_id INT,"
					+ "PRIMARY KEY(id),"
					+ "FOREIGN KEY (plan_id) REFERENCES plans(id)"
					+ ");"
					);
			// aqui ele executa o m�todo o qual criei acima no banco de dados.
			create.executeUpdate();
			connection.close();
		} catch (Exception error) {
			System.out.println(error);
		}
		finally {
			System.out.println("Function completed");
		}
	}
	
	public static synchronized List<User> get() throws Exception {
		try {
			Connection connection = DatabaseConnection.getConnection();
			//M�TODO PARA BUSCAR TODOS OS ITENS DENTRO DO BANCO DE DADOS DA TABELA
			PreparedStatement statement = connection.prepareStatement("SELECT * from " + nameTable);
			
			//DENTRO DO RESULT ESTAR� OS DADOS QUE EU QUERO CASO TENHA DADO SUCESSO
			ResultSet result = statement.executeQuery();
			
			List<User> users = new LinkedList<User>();
			
			//AQUI FARA UM WHILE QUE ENQUANTO EXISTIR "PR�XIMO" DADO ELE CONTINUARA NO WHILE.
			while(result.next()) {
				Plan plan = PlanDao.getById(result.getInt("plan_id"));
				//CADA GET DESSES AQUI EMBAIXO S�O OS NOMES DA COLUNA QUE EU QUERO O RESULTADO
				User user = new User(result.getInt("id"),
						result.getString("first_name"), 
						result.getString("last_name"), 
						result.getInt("rg"),
						result.getInt("cpf"),
						result.getDate("birthdate"),
						result.getInt("phoneNumber"), 
						result.getString("address"), 
						result.getString("address_number"), 
						result.getString("state") , 
						result.getString("city"),
						plan
						); 
				users.add(user);
			}
			connection.close();
			
			return users;
		} catch (Exception error){
			System.out.println(error);
			return null;
		}
	}
	
	public static synchronized User getById(int id) throws Exception {
		try {
			Connection connection = DatabaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * from " + nameTable + " WHERE id = " + id);
			
			ResultSet result = statement.executeQuery();
			
			User userCatched = new User();
			
			while(result.next()) {
				Plan plan = PlanDao.getById(result.getInt("plan_id"));
				User user = new User(result.getInt("id"),
						result.getString("first_name"), 
						result.getString("last_name"), 
						result.getInt("rg"),
						result.getInt("cpf"),
						result.getDate("birthdate"),
						result.getInt("phoneNumber"), 
						result.getString("address"), 
						result.getString("address_number"), 
						result.getString("state") , 
						result.getString("city"),
						plan
						); 
				userCatched = user;
			}
			connection.close();
			 
			return userCatched;
			
		} catch (Exception error){
			System.out.println(error);
			return null;
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
	
	public static void insert(User user) throws Exception {
		try {
			//pega conex�o com o banco de dados
			Connection connection = DatabaseConnection.getConnection();
			//PreparedStatement � o metodo na sua tradu��o prepara a declara��o que ir� ser introduzida no SQL, no contexto � o m�todo de inser��o de dados.
			PreparedStatement posted = connection.prepareStatement("INSERT INTO "+ nameTable +"("
					+ "	first_name,"
					+ " last_name,"
					+ " rg,"
					+ " cpf,"
					+ " birthdate,"
					+ " phone_number,"
					+ " address,"
					+ " address_number,"
					+ " state,"
					+ " city,"
					+ " plan_id)"
					+ " VALUES (?,?,?,?,?,?,?,?,?,?,?)");
			// para cada interroga��o respectiva estou preenchendo de acordo com as informa��es abaixo.
			posted.setString(1, user.getFirstName());
			posted.setString(2, user.getLastName());
			posted.setInt(3, user.getRg());
			posted.setInt(4, user.getCpf());
			posted.setDate(5, java.sql.Date.valueOf(user.getBirthdate().toString()));
			posted.setInt(6, user.getPhoneNumber());
			posted.setString(7, user.getAddress());
			posted.setString(8, user.getAddressNumber());
			posted.setString(9, user.getState());
			posted.setString(10, user.getCity());
			posted.setInt(11, user.getPlan().getId());
			// aqui ele executa o m�todo o qual criei acima no banco de dados.
			posted.executeUpdate();
		} catch (Exception error) {
			System.out.println(error);
		}
	}
	
	
}
