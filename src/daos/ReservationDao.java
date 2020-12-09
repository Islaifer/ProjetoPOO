package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import config.DatabaseConnection;
import models.Reservation;
import models.Space;
import models.User;

public class ReservationDao {
	private static String nameTable = "reservations";
	
	public static synchronized void createTableReservations() throws Exception {
		try {
			//pega conexão com o banco de dados
			Connection connection = DatabaseConnection.getConnection();
			//PreparedStatement é o metodo na sua tradução prepara a declaração que irá ser introduzida no SQL, no contexto é o método de criação de tabela.
			PreparedStatement create = connection.prepareStatement("CREATE TABLE IF NOT EXISTS "
					+ nameTable +"(id INT NOT NULL AUTO_INCREMENT,"
					+ "id_user INT NOT NULL,"
					+ "id_space INT NOT NULL,"
					+ "date DATE NOT NULL,"
					+ "people_qntd INT NOT NULL,"
					+ "PRIMARY KEY(id),"
					+ "FOREIGN KEY (id_user) REFERENCES users(id),"
					+ "FOREIGN KEY (id_space) REFERENCES spaces(id)"
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
	
	public static synchronized List<Reservation> get() throws Exception {
		try {
			Connection connection = DatabaseConnection.getConnection();
			//MÉTODO PARA BUSCAR TODOS OS ITENS DENTRO DO BANCO DE DADOS DA TABELA
			PreparedStatement statement = connection.prepareStatement("SELECT * from " + nameTable);
			
			//DENTRO DO RESULT ESTARÁ OS DADOS QUE EU QUERO CASO TENHA DADO SUCESSO
			ResultSet result = statement.executeQuery();
			
			List<Reservation> reservations = new LinkedList<Reservation>();
			
			//AQUI FARA UM WHILE QUE ENQUANTO EXISTIR "PRÓXIMO" DADO ELE CONTINUARA NO WHILE.
			while(result.next()) {
				//CADA GET DESSES AQUI EMBAIXO SÃO OS NOMES DA COLUNA QUE EU QUERO O RESULTADO
				User user = UserDao.getById(result.getInt("id_user"));
				Space space = SpaceDao.getById(result.getInt("id_space"));
				Reservation reservation = new Reservation(result.getInt("id"),
						user,
						result.getDate("date"),
						space,
						result.getInt("people_qntd")
						); 
				reservations.add(reservation);
			}
			connection.close();
			
			return reservations;
		} catch (Exception error){
			System.out.println(error);
			return null;
		}
	}
	
	public static synchronized Reservation getById(int id) throws Exception {
		try {
			Connection connection = DatabaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * from " + nameTable + " WHERE id = " + id);
			
			ResultSet result = statement.executeQuery();
			
			Reservation reservationCatched = new Reservation();
			
			while(result.next()) {
				User user = UserDao.getById(result.getInt("id_user"));
				Space space = SpaceDao.getById(result.getInt("id_space"));
				Reservation reservation = new Reservation(result.getInt("id"),
						user,
						result.getDate("date"),
						space,
						result.getInt("people_qntd")
						);
				reservationCatched = reservation;
			}
			connection.close();
			 
			return reservationCatched;
			
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
	
	public static void insert(Reservation reservation) throws Exception {
		try {
			//pega conexão com o banco de dados
			Connection connection = DatabaseConnection.getConnection();
			//PreparedStatement é o metodo na sua tradução prepara a declaração que irá ser introduzida no SQL, no contexto é o método de inserção de dados.
			PreparedStatement posted = connection.prepareStatement("INSERT INTO "+ nameTable +"("
					+ "id_user,"
					+ "id_space,"
					+ "date,"
					+ "people_qntd)"
					+ " VALUES (?,?,?,?)");
			// para cada interrogação respectiva estou preenchendo de acordo com as informações abaixo.
			posted.setInt(1, reservation.getUser().getId());
			posted.setInt(2, reservation.getSpace().getId());
			posted.setDate(3, new java.sql.Date(reservation.getDate().getTime()));
			posted.setInt(4, reservation.getPeopleqnt());
			// aqui ele executa o método o qual criei acima no banco de dados.
			posted.executeUpdate();
		} catch (Exception error) {
			System.out.println(error);
		}
	}
	
	public static Reservation getByDateAndByPlace(int id, Date date, int spaceId) {
		try {

		Connection connection = DatabaseConnection.getConnection();
		PreparedStatement statement;
		if(id != 0) {
			statement = connection.prepareStatement("SELECT * FROM " + nameTable + " WHERE date = " + "'"+ new java.sql.Date(date.getTime())  + "'" + " AND id_space = " + spaceId + " AND id <> " + id + " LIMIT 1");
		}else {
			statement = connection.prepareStatement("SELECT * FROM " + nameTable + " WHERE date = " + "'"+ new java.sql.Date(date.getTime())  + "'" + " AND id_space = " + spaceId + " LIMIT 1");
		}
		
		ResultSet result = statement.executeQuery();
				
		Reservation reservationCatched = new Reservation();
		
		while(result.next()) {
			User user = UserDao.getById(result.getInt("id_user"));
			Space space = SpaceDao.getById(result.getInt("id_space"));
			Reservation reservation = new Reservation(result.getInt("id"),
					user,
					result.getDate("date"),
					space,
					result.getInt("people_qntd")
					);
			reservationCatched = reservation;
		}
		connection.close();
		
		return reservationCatched;
		
		} catch (Exception error){
			System.out.println(error);
			return null;
		}
	}
	
	public static void update(Reservation reservation) throws Exception {
		try {
			//pega conexão com o banco de dados
			Connection connection = DatabaseConnection.getConnection();
			//PreparedStatement é o metodo na sua tradução prepara a declaração que irá ser introduzida no SQL, no contexto é o método de inserção de dados.
			PreparedStatement posted = connection.prepareStatement("UPDATE "+ nameTable +" set"
					+ " id_space = ?,"
					+ " date = ?,"
					+ " people_qntd = ?"
					+ " WHERE id = ?");
			// para cada interrogação respectiva estou preenchendo de acordo com as informações abaixo.
			posted.setInt(1, reservation.getSpace().getId());
			posted.setDate(2, new java.sql.Date(reservation.getDate().getTime()));
			posted.setInt(3, reservation.getPeopleqnt());
			posted.setInt(4, reservation.getId());
			// aqui ele executa o método o qual criei acima no banco de dados.
			posted.executeUpdate();
			connection.close();
		} catch (Exception error) {
			System.out.println(error);
		}
	}
}
