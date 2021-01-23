package persistencia.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidade.Coordenador;

/**
 * Metodo para consulta do administrador no banco de dados 
 * @author André 
 *
 */
public class CoordenadorDAO {
	
	private Connection conexao = ConexaoFactory.getConnection();
	
	/**
	 * Metodo para selecionar do banco de dados todos os coordenadores cadastrados
	 * @return lista de coordenadores registrados no banco 
	 * @author André
	 * @throws SQLException
	 */
	public List<Coordenador> buscarTodos() throws SQLException {
		Coordenador coordenador = new Coordenador();
		List<Coordenador> lista =  new ArrayList<Coordenador>();
		String sql = "select * from usuario where tipoUsuario = 3";
		
		PreparedStatement comandoSql = conexao.prepareStatement(sql);
		ResultSet resultSet = comandoSql.executeQuery();
		
		while (resultSet.next()) {
			coordenador.setIdUsuario(resultSet.getInt(1));
			coordenador.setNome(resultSet.getString(2));
			coordenador.setSobrenome(resultSet.getString(3));
			coordenador.setCpf(resultSet.getString(4));
			coordenador.setTelefone(resultSet.getString(5));
			coordenador.setCelular(resultSet.getString(6));
			coordenador.setTipoUsuario(resultSet.getInt(7));
			coordenador.setEmail(resultSet.getString(8));
			coordenador.setSenha(resultSet.getString(9));
			coordenador.setHorarioFinalExpediente(resultSet.getTime(10));
			coordenador.setHorarioInicioExpediente(resultSet.getTime(11));
			coordenador.setFotoUsuario(resultSet.getString(12));
			coordenador.setFk_endereco(resultSet.getInt(13));
			coordenador.setFk_escola(resultSet.getInt(14));
			
			lista.add(coordenador);
		}
		comandoSql.close();
		return lista;
	}
}