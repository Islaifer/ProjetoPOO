package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import config.DatabaseConnection;
import models.Space;


public class SpaceDao {
	
	private static String nameTable = "spaces";

	public static synchronized void createTableSpaces() throws Exception {
		try {
			// pega conex�o com o banco de dados
			Connection connection = DatabaseConnection.getConnection();
			// PreparedStatement � o metodo na sua tradu��o prepara a declara��o que ir� ser
			// introduzida no SQL, no contexto � o m�todo de cria��o de tabela.
			PreparedStatement create = connection.prepareStatement("CREATE TABLE IF NOT EXISTS " + nameTable
					+ "(id INT NOT NULL AUTO_INCREMENT," + "name VARCHAR(255) NOT NULL,"
					+ "capacity INT NOT NULL," + "PRIMARY KEY(id))");
			// aqui ele executa o m�todo o qual criei acima no banco de dados.
			create.executeUpdate();
			seedTable();
			connection.close();
		} catch (Exception error) {
			System.out.println(error);
		} finally {
			System.out.println("Function completed");
		}
	}
	private static void seedTable() throws Exception {
		Space quadra = new Space("Quadra", 30);
		Space piscina = new Space("Piscina", 50);
		Space campofut = new Space ("Campo de futebol", 30);
		insert(quadra);
		insert(piscina);
		insert(campofut);
	}
	public static synchronized List<Space> get() throws Exception {
		try {
			Connection connection = DatabaseConnection.getConnection();
			// M�TODO PARA BUSCAR TODOS OS ITENS DENTRO DO BANCO DE DADOS DA TABELA
			PreparedStatement statement = connection.prepareStatement("SELECT * from " + nameTable);

			// DENTRO DO RESULT ESTAR� OS DADOS QUE EU QUERO CASO TENHA DADO SUCESSO
			ResultSet result = statement.executeQuery();

			List<Space> spaces = new LinkedList<Space>();

			// AQUI FARA UM WHILE QUE ENQUANTO EXISTIR "PR�XIMO" DADO ELE CONTINUARA NO
			// WHILE.
			while (result.next()) {
				// CADA GET DESSES AQUI EMBAIXO S�O OS NOMES DA COLUNA QUE EU QUERO O RESULTADO
				Space space = new Space (result.getInt("id"), result.getString("name"),	result.getInt("capacity"));
				spaces.add(space);
			}
			connection.close();

			return spaces;
		} catch (Exception error) {
			System.out.println(error);
			return null;
		}
	}

	public static synchronized Space getById(int id) throws Exception {
		try {
			Connection connection = DatabaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * from " + nameTable + " WHERE id = " + id);
			
			ResultSet result = statement.executeQuery();
			
			Space spaceCatched = new Space();
			
			while(result.next()) {
				Space space = new Space (result.getInt("id"), result.getString("name"),	result.getInt("capacity"));
				spaceCatched = space;
			}
			connection.close();
			 
			return spaceCatched;
		} catch (Exception error){
			System.out.println(error);
			return null;
		}
	}
	
	private static void insert(Space space) throws Exception {
		try {
			Connection connection = DatabaseConnection.getConnection();
			PreparedStatement posted = connection.prepareStatement("INSERT INTO " + nameTable + "(name, capacity)"
					+ " VALUES (?,?)");
			posted.setString(1, space.getName());
			posted.setInt(2, space.getCapacity());
			posted.executeUpdate();
			connection.close();
			
		} catch (Exception error) {
			System.out.println("ta aqui?");
			System.out.println(error);
		}
	}
	
}
