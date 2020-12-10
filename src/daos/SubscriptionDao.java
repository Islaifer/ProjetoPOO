package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import config.DatabaseConnection;
import models.Subscription;
import models.SubscriptionStatus;
import models.User;

public class SubscriptionDao {
	private static String nameTable = "subscriptions";
	
	public static synchronized void createTableSubscriptions() throws Exception {
		try {
			//pega conex�o com o banco de dados
			Connection connection = DatabaseConnection.getConnection();
			//PreparedStatement � o metodo na sua tradu��o prepara a declara��o que ir� ser introduzida no SQL, no contexto � o m�todo de cria��o de tabela.
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
			// aqui ele executa o m�todo o qual criei acima no banco de dados.
			create.executeUpdate();
			connection.close();
		} catch (Exception error) {
			System.out.println(error);
		}
		finally {
			System.out.println("Table " + nameTable + " created");
		}
	}
	
	public static synchronized List<Subscription> getAll() throws Exception {
		try {
			Connection connection = DatabaseConnection.getConnection();
			//M�TODO PARA BUSCAR TODOS OS ITENS DENTRO DO BANCO DE DADOS DA TABELA
			PreparedStatement statement = connection.prepareStatement("SELECT * from " + nameTable);
			
			//DENTRO DO RESULT ESTAR� OS DADOS QUE EU QUERO CASO TENHA DADO SUCESSO
			ResultSet result = statement.executeQuery();
			
			List<Subscription> subscriptions = new LinkedList<Subscription>();
			
			//AQUI FARA UM WHILE QUE ENQUANTO EXISTIR "PR�XIMO" DADO ELE CONTINUARA NO WHILE.
			while(result.next()) {
				//CADA GET DESSES AQUI EMBAIXO S�O OS NOMES DA COLUNA QUE EU QUERO O RESULTADO
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
	
	public static synchronized List<Subscription> filterByStatus(int statusId) throws Exception {
		try {
			Connection connection = DatabaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * from " + nameTable + " WHERE id_status = " + statusId + " ORDER BY date ASC");
			
			ResultSet result = statement.executeQuery();
			

			List<Subscription> subscriptions = new LinkedList<Subscription>();
			
			//AQUI FARA UM WHILE QUE ENQUANTO EXISTIR "PR�XIMO" DADO ELE CONTINUARA NO WHILE.
			while(result.next()) {
				//CADA GET DESSES AQUI EMBAIXO S�O OS NOMES DA COLUNA QUE EU QUERO O RESULTADO
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
			Date currentDate = new Date();
			Calendar nextMonthCalendar = Calendar.getInstance();
			nextMonthCalendar.setTime(currentDate);
			nextMonthCalendar.set(Calendar.MONTH, nextMonthCalendar.get(Calendar.MONTH) + 1);
			//pega conex�o com o banco de dados
			Connection connection = DatabaseConnection.getConnection();
			//PreparedStatement � o metodo na sua tradu��o prepara a declara��o que ir� ser introduzida no SQL, no contexto � o m�todo de inser��o de dados.
			PreparedStatement posted = connection.prepareStatement("INSERT INTO "+ nameTable +"("
					+ "id_user,"
					+ "id_status,"
					+ "date,"
					+ "amount)"
					+ " VALUES (?,?,?,?)");
			// para cada interroga��o respectiva estou preenchendo de acordo com as informa��es abaixo.
			posted.setInt(1, subscription.getUser().getId());
			posted.setInt(2, subscription.getStatus().getId());
			posted.setDate(3, new java.sql.Date(nextMonthCalendar.getTime().getTime()));
			posted.setDouble(4, subscription.getUser().getPlan().getPrice());
			// aqui ele executa o m�todo o qual criei acima no banco de dados.
			posted.executeUpdate();
		} catch (Exception error) {
			System.out.println(error);
		}
	}
	
	public static void updateStatus(Subscription subscription, int statusId) throws Exception {
		try {
			//pega conex�o com o banco de dados
			Connection connection = DatabaseConnection.getConnection();
			//PreparedStatement � o metodo na sua tradu��o prepara a declara��o que ir� ser introduzida no SQL, no contexto � o m�todo de inser��o de dados.
			PreparedStatement posted = connection.prepareStatement("UPDATE "+ nameTable +" set"
					+ "	id_status = ?"
					+ " WHERE id = ?");
			// para cada interroga��o respectiva estou preenchendo de acordo com as informa��es abaixo.
			posted.setInt(1, statusId);
			posted.setInt(2, subscription.getId());
			// aqui ele executa o m�todo o qual criei acima no banco de dados.
			posted.executeUpdate();
			connection.close();
		} catch (Exception error) {
			System.out.println(error);
		}
	}
	
	public static synchronized Subscription checkIfUserHasSubscriptionOnMonth(Date initialMonth, Date finalMonth, int userId) throws Exception {
		try {
			Connection connection = DatabaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * from " + nameTable + " WHERE date >= " + "'" +new java.sql.Date(initialMonth.getTime()) + "'"  + " AND date <= "  +  "'" +new java.sql.Date(finalMonth.getTime()) + "'" + " AND id_user = "+ userId + " LIMIT 1");
			
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
}
