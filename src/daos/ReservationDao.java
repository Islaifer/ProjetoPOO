package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
			//pega conex�o com o banco de dados
			Connection connection = DatabaseConnection.getConnection();
			//PreparedStatement � o metodo na sua tradu��o prepara a declara��o que ir� ser introduzida no SQL, no contexto � o m�todo de cria��o de tabela.
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
	
	public static synchronized List<Reservation> get() throws Exception {
		try {
			Connection connection = DatabaseConnection.getConnection();
			//M�TODO PARA BUSCAR TODOS OS ITENS DENTRO DO BANCO DE DADOS DA TABELA
			PreparedStatement statement = connection.prepareStatement("SELECT * from " + nameTable);
			
			//DENTRO DO RESULT ESTAR� OS DADOS QUE EU QUERO CASO TENHA DADO SUCESSO
			ResultSet result = statement.executeQuery();
			
			List<Reservation> reservations = new LinkedList<Reservation>();
			
			//AQUI FARA UM WHILE QUE ENQUANTO EXISTIR "PR�XIMO" DADO ELE CONTINUARA NO WHILE.
			while(result.next()) {
				//CADA GET DESSES AQUI EMBAIXO S�O OS NOMES DA COLUNA QUE EU QUERO O RESULTADO
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
			//pega conex�o com o banco de dados
			Connection connection = DatabaseConnection.getConnection();
			//PreparedStatement � o metodo na sua tradu��o prepara a declara��o que ir� ser introduzida no SQL, no contexto � o m�todo de inser��o de dados.
			PreparedStatement posted = connection.prepareStatement("INSERT INTO "+ nameTable +"("
					+ "id_user,"
					+ "id_space,"
					+ "date DATE,"
					+ "people_qntd)"
					+ " VALUES (?,?,?,?)");
			// para cada interroga��o respectiva estou preenchendo de acordo com as informa��es abaixo.
			posted.setInt(1, reservation.getUser().getId());
			posted.setInt(2, reservation.getSpace().getId());
			posted.setDate(3, java.sql.Date.valueOf(reservation.getDate().toString()));
			posted.setInt(4, reservation.getPeopleqnt());
			// aqui ele executa o m�todo o qual criei acima no banco de dados.
			posted.executeUpdate();
		} catch (Exception error) {
			System.out.println(error);
		}
	}
}
