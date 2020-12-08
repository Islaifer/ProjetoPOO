package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import config.DatabaseConnection;
import models.Reservation;
import models.Space;
import models.Subscription;
import models.SubscriptionStatus;
import models.User;

public class SubscriptionDao {
	private static String nameTable = "subscriptions";
	
	public static synchronized void createTableSubscriptions() throws Exception {
		try {
			//pega conexão com o banco de dados
			Connection connection = DatabaseConnection.getConnection();
			//PreparedStatement é o metodo na sua tradução prepara a declaração que irá ser introduzida no SQL, no contexto é o método de criação de tabela.
			PreparedStatement create = connection.prepareStatement("CREATE TABLE IF NOT EXISTS "
					+ nameTable +"(id INT NOT NULL AUTO_INCREMENT,"
					+ "id_user INT NOT NULL,"
					+ "id_status INT NOT NULL,"
					+ "date DATE NOT NULL,"
					+ "amount DOUBLE NOT NULL,"
					+ "PRIMARY KEY(id),"
					+ "FOREIGN KEY (id_user) REFERENCES users(id),"
					+ "FOREIGN KEY (id_status) REFERENCES subscription_status(id)"
					+ ");"
					);
			// aqui ele executa o método o qual criei acima no banco de dados.
			create.executeUpdate();
			connection.close();
		} catch (Exception error) {
			System.out.println(error);
		}
		finally {
			System.out.println("Function completed");
		}
	}
	
	public static synchronized List<Subscription> getAll() throws Exception {
		try {
			Connection connection = DatabaseConnection.getConnection();
			//MÉTODO PARA BUSCAR TODOS OS ITENS DENTRO DO BANCO DE DADOS DA TABELA
			PreparedStatement statement = connection.prepareStatement("SELECT * from " + nameTable);
			
			//DENTRO DO RESULT ESTARÁ OS DADOS QUE EU QUERO CASO TENHA DADO SUCESSO
			ResultSet result = statement.executeQuery();
			
			List<Subscription> subscriptions = new LinkedList<Subscription>();
			
			//AQUI FARA UM WHILE QUE ENQUANTO EXISTIR "PRÓXIMO" DADO ELE CONTINUARA NO WHILE.
			while(result.next()) {
				//CADA GET DESSES AQUI EMBAIXO SÃO OS NOMES DA COLUNA QUE EU QUERO O RESULTADO
				User user = UserDao.getById(result.getInt("id_user"));
				SubscriptionStatus status = SubscriptionStatusDao.getById(result.getInt("id_status"));
				Subscription subscription = new Subscription(result.getInt("id"),
						result.getDate("date"),
						status,
						user,
						result.getDouble("amount")
						); 
				subscriptions.add(subscription);
			}
			connection.close();
			
			return subscriptions;
		} catch (Exception error){
			System.out.println(error);
			return null;
		}
	}
	
	public static synchronized Subscription getById(int id) throws Exception {
		try {
			Connection connection = DatabaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * from " + nameTable + " WHERE id = " + id);
			
			ResultSet result = statement.executeQuery();
			
			Subscription subscriptionCatched = new Subscription();
			
			while(result.next()) {
				User user = UserDao.getById(result.getInt("id_user"));
				SubscriptionStatus status = SubscriptionStatusDao.getById(result.getInt("id_status"));
				Subscription subscription = new Subscription(result.getInt("id"),
						result.getDate("date"),
						status,
						user,
						result.getInt("amount")
						);
				subscriptionCatched = subscription;
			}
			connection.close();
			 
			return subscriptionCatched;
			
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
	
	public static void insert(Subscription subscription) throws Exception {
		try {
			//pega conexão com o banco de dados
			Connection connection = DatabaseConnection.getConnection();
			//PreparedStatement é o metodo na sua tradução prepara a declaração que irá ser introduzida no SQL, no contexto é o método de inserção de dados.
			PreparedStatement posted = connection.prepareStatement("INSERT INTO "+ nameTable +"("
					+ "id_user,"
					+ "id_status,"
					+ "date DATE,"
					+ "amount)"
					+ " VALUES (?,?,?,?)");
			// para cada interrogação respectiva estou preenchendo de acordo com as informações abaixo.
			posted.setInt(1, subscription.getUser().getId());
			posted.setInt(2, subscription.getStatus().getId());
			posted.setDate(3, java.sql.Date.valueOf(subscription.getDueDate().toString()));
			posted.setDouble(4, subscription.getAmount());
			// aqui ele executa o método o qual criei acima no banco de dados.
			posted.executeUpdate();
		} catch (Exception error) {
			System.out.println(error);
		}
	}
}
