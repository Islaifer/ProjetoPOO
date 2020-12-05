package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import config.DatabaseConnection;
import models.Payment;
import models.User;

public class PaymentDao {
private static String nameTable = "payments";
	
	public static synchronized void createTablePayments() throws Exception {
		try {
			//pega conex�o com o banco de dados
			Connection connection = DatabaseConnection.getConnection();
			//PreparedStatement � o metodo na sua tradu��o prepara a declara��o que ir� ser introduzida no SQL, no contexto � o m�todo de cria��o de tabela.
			PreparedStatement create = connection.prepareStatement("CREATE TABLE IF NOT EXISTS "
					+ nameTable +"(id INT NOT NULL AUTO_INCREMENT,"
					+ "id_user INT NOT NULL,"
					+ "date DATE NOT NULL,"
					+ "price DOUBLE NOT NULL,"
					+ "PRIMARY KEY(id),"
					+ "FOREIGN KEY (id_user) REFERENCES users(id))");
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
	
	public static synchronized List<Payment> get() throws Exception {
		try {
			Connection connection = DatabaseConnection.getConnection();
			//M�TODO PARA BUSCAR TODOS OS ITENS DENTRO DO BANCO DE DADOS DA TABELA
			PreparedStatement statement = connection.prepareStatement("SELECT * from " + nameTable);
			
			//DENTRO DO RESULT ESTAR� OS DADOS QUE EU QUERO CASO TENHA DADO SUCESSO
			ResultSet result = statement.executeQuery();
			
			List<Payment> payments = new LinkedList<Payment>();
			
			//AQUI FARA UM WHILE QUE ENQUANTO EXISTIR "PR�XIMO" DADO ELE CONTINUARA NO WHILE.
			while(result.next()) {
				//CADA GET DESSES AQUI EMBAIXO S�O OS NOMES DA COLUNA QUE EU QUERO O RESULTADO
				User user = UserDao.getById(result.getInt("id_user"));
				Payment payment = new Payment(result.getInt("id"),
						result.getDate("date"),
						user,
						result.getDouble("price")
						); 
				payments.add(payment);
			}
			connection.close();
			
			return payments;
		} catch (Exception error){
			System.out.println(error);
			return null;
		}
	}
	
	public static synchronized Payment getById(int id) throws Exception {
		try {
			Connection connection = DatabaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * from " + nameTable + " WHERE id = " + id);
			
			ResultSet result = statement.executeQuery();
			
			Payment paymentCatched = new Payment();
			
			while(result.next()) {
				User user = UserDao.getById(result.getInt("id_user"));
				Payment payment = new Payment (result.getInt("id"),
						result.getDate("date"),
						user,
						result.getDouble("price")
						);
				paymentCatched = payment;
			}
			connection.close();
			 
			return paymentCatched;
			
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
	
	public static void insert(Payment payment) throws Exception {
		try {
			//pega conex�o com o banco de dados
			Connection connection = DatabaseConnection.getConnection();
			//PreparedStatement � o metodo na sua tradu��o prepara a declara��o que ir� ser introduzida no SQL, no contexto � o m�todo de inser��o de dados.
			PreparedStatement posted = connection.prepareStatement("INSERT INTO "+ nameTable +"("
					+ "id_user,"
					+ "date DATE,"
					+ "price)"
					+ " VALUES (?,?,?)");
			// para cada interroga��o respectiva estou preenchendo de acordo com as informa��es abaixo.
			posted.setInt(1, payment.getUser().getId());
			posted.setDate(2, java.sql.Date.valueOf(payment.getDate().toString()));
			posted.setDouble(3, payment.getPrice());
			// aqui ele executa o m�todo o qual criei acima no banco de dados.
			posted.executeUpdate();
		} catch (Exception error) {
			System.out.println(error);
		}
	}
}
