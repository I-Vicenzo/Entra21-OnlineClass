package persistencia.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Estabelece a conexão com o banco de dados 
 * @author Breno
 *
 */
public class ConexaoFactory {
	
	private static Connection conexao = null;
	
	public static Connection getConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			
			if (conexao == null) {
				conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/OnlineClassBancoDB", "postgres","adm123");
			}
			return conexao;
		} catch (Exception e) {
			System.err.println(e);
		}
		return null;
	}
}
