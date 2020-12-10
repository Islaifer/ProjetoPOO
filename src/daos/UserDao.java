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
			//pega conexão com o banco de dados
			Connection connection = DatabaseConnection.getConnection();
			//PreparedStatement é o metodo na sua tradução prepara a declaração que irá ser introduzida no SQL, no contexto é o método de criação de tabela.
			PreparedStatement create = connection.prepareStatement("CREATE TABLE IF NOT EXISTS "
					+ nameTable +"(id INT NOT NULL AUTO_INCREMENT,"
					+ "first_name VARCHAR(255) NOT NULL,"
					+ "last_name VARCHAR(255) NOT NULL,"
					+ "rg BIGINT NOT NULL,"
					+ "cpf BIGINT NOT NULL,"
					+ "birthdate DATE,"
					+ "phone_number BIGINT NOT NULL,"
					+ "address VARCHAR(255) NOT NULL,"
					+ "plan_id INT NOT NULL,"
					+ "PRIMARY KEY(id),"
					+ "FOREIGN KEY (plan_id) REFERENCES plans(id)"
					+ ");"
					);
			// aqui ele executa o método o qual criei acima no banco de dados.
			create.executeUpdate();
			connection.close();
		} catch (Exception error) {
			System.out.println(error);
		}
		finally {
			System.out.println("Table " + nameTable + " created");
		}
	}
	
	public static synchronized List<User> get() throws Exception {
		try {
			Connection connection = DatabaseConnection.getConnection();
			//MÉTODO PARA BUSCAR TODOS OS ITENS DENTRO DO BANCO DE DADOS DA TABELA
			PreparedStatement statement = connection.prepareStatement("SELECT * from " + nameTable);
			
			//DENTRO DO RESULT ESTARÁ OS DADOS QUE EU QUERO CASO TENHA DADO SUCESSO
			ResultSet result = statement.executeQuery();
			
			List<User> users = new LinkedList<User>();
			
			//AQUI FARA UM WHILE QUE ENQUANTO EXISTIR "PRÓXIMO" DADO ELE CONTINUARA NO WHILE.
			while(result.next()) {
				Plan plan = PlanDao.getById(result.getInt("plan_id"));
				//CADA GET DESSES AQUI EMBAIXO SÃO OS NOMES DA COLUNA QUE EU QUERO O RESULTADO
				User user = new User(result.getInt("id"),
						result.getString("first_name"), 
						result.getString("last_name"), 
						result.getLong("rg"),
						result.getLong("cpf"),
						result.getDate("birthdate"),
						result.getLong("phone_number"), 
						result.getString("address"), 
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
						result.getLong("rg"),
						result.getLong("cpf"),
						result.getDate("birthdate"),
						result.getLong("phone_number"), 
						result.getString("address"), 
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
	
	public static synchronized User getByCPF(long cpf) throws Exception {
		try {
			Connection connection = DatabaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * from " + nameTable + " WHERE cpf = " + cpf);
			
			ResultSet result = statement.executeQuery();
			
			User userCatched = new User();
			
			while(result.next()) {
				Plan plan = PlanDao.getById(result.getInt("plan_id"));
				User user = new User(result.getInt("id"),
						result.getString("first_name"), 
						result.getString("last_name"), 
						result.getLong("rg"),
						result.getLong("cpf"),
						result.getDate("birthdate"),
						result.getLong("phone_number"), 
						result.getString("address"), 
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
			PreparedStatement deleteR = connection.prepareStatement("DELETE FROM reservations WHERE id_user = " + id);
			PreparedStatement deleteP = connection.prepareStatement("DELETE FROM payments WHERE id_user = " + id);
			PreparedStatement deleteS = connection.prepareStatement("DELETE FROM subscriptions WHERE id_user = " + id); 
			PreparedStatement delete = connection.prepareStatement("DELETE from " + nameTable + " WHERE id = " + id);
			
			deleteR.executeUpdate();
			deleteP.executeUpdate();
			deleteS.executeUpdate();
			delete.executeUpdate();
			
			connection.close();		
		} catch (Exception error){
			System.out.println(error);
		}
	}
	
	public static void insert(User user) throws Exception {
		try {
			//pega conexão com o banco de dados
			Connection connection = DatabaseConnection.getConnection();
			//PreparedStatement é o metodo na sua tradução prepara a declaração que irá ser introduzida no SQL, no contexto é o método de inserção de dados.
			PreparedStatement posted = connection.prepareStatement("INSERT INTO "+ nameTable +"("
					+ "	first_name,"
					+ " last_name,"
					+ " rg,"
					+ " cpf,"
					+ " birthdate,"
					+ " phone_number,"
					+ " address,"
					+ " plan_id)"
					+ " VALUES (?,?,?,?,?,?,?,?)");
			// para cada interrogação respectiva estou preenchendo de acordo com as informações abaixo.
			posted.setString(1, user.getFirstName());
			posted.setString(2, user.getLastName());
			posted.setLong(3, user.getRg());
			posted.setLong(4, user.getCpf());
			posted.setDate(5, new java.sql.Date(user.getBirthdate().getTime()));
			posted.setLong(6, user.getPhoneNumber());
			posted.setString(7, user.getAddress());
			posted.setInt(8, user.getPlan().getId());
			// aqui ele executa o método o qual criei acima no banco de dados.
			posted.executeUpdate();
		} catch (Exception error) {
			System.out.println(error);
		}
	}
	
	public static void update(User user) throws Exception {
		try {
			//pega conexão com o banco de dados
			Connection connection = DatabaseConnection.getConnection();
			//PreparedStatement é o metodo na sua tradução prepara a declaração que irá ser introduzida no SQL, no contexto é o método de inserção de dados.
			PreparedStatement posted = connection.prepareStatement("UPDATE "+ nameTable +" set"
					+ "	first_name = ?,"
					+ " last_name = ?,"
					+ " phone_number = ?,"
					+ " address = ?,"
					+ " plan_id = ?"
					+ " WHERE id = ?");
			// para cada interrogação respectiva estou preenchendo de acordo com as informações abaixo.
			posted.setString(1, user.getFirstName());
			posted.setString(2, user.getLastName());
			posted.setLong(3, user.getPhoneNumber());
			posted.setString(4, user.getAddress());
			posted.setInt(5, user.getPlan().getId());
			posted.setInt(6, user.getId());
			// aqui ele executa o método o qual criei acima no banco de dados.
			posted.executeUpdate();
			connection.close();
		} catch (Exception error) {
			System.out.println(error);
		}
	}
}
