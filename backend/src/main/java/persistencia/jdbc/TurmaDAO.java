package persistencia.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidade.Turma;

/**
 * Metodo para consulta da turma no banco de dados 
 * @author Breno
 *
 */
public class TurmaDAO {
	
	private Connection conexao = ConexaoFactory.getConnection();

	/**
	 * Realiza o registro de uma turma no banco de dados
	 * @param Turma turma
	 * @author Breno
	 * @throws SQLException 
	 */
	public void insert(Turma turma) throws SQLException {
		String sql = "insert into turma (ano, qtdAluno, horaInicioAula, horaFinalAula, fk_sala, fk_escola) values (?,?,?,?,?,?)"; 
		PreparedStatement comandoSql = conexao.prepareStatement(sql);
		     
		comandoSql.setString(1, turma.getAno());
		comandoSql.setInt(2, turma.getQtdAluno());
		comandoSql.setTimestamp(3, turma.getHorarioInicioAula());
		comandoSql.setTimestamp(4, turma.getHorarioFinalAula());
		comandoSql.setInt(5, turma.getFk_sala());
		comandoSql.setInt(6, turma.getFk_escola());
		
		comandoSql.execute(); 
		comandoSql.close(); 
	}
	
	/**
	 * Realiza atualizacao dos dados da turma no banco de dados.
	 * O <code>idTurma</code> deve ser igual ao da turma que deseja atualizar
	 * @param Turma turma
	 * @author Breno
	 * @throws SQLException 
	 */
	public void update(Turma turma) throws SQLException {
		String sql = "Update turma set ano = ?, qtdAluno = ?, horaInicioAula = ?, "
				   + "horaFinalAula = ?, fk_sala = ?, fk_escola = ? where idTurma = ?"; 
		PreparedStatement comandoSql = conexao.prepareStatement(sql);
		    
		comandoSql.setString(1, turma.getAno());
		comandoSql.setInt(2, turma.getQtdAluno());
		comandoSql.setTimestamp(3, turma.getHorarioInicioAula());
		comandoSql.setTimestamp(4, turma.getHorarioFinalAula());
		comandoSql.setInt(5, turma.getFk_sala());
		comandoSql.setInt(6, turma.getFk_escola());
		comandoSql.setInt(7, turma.getIdTurma());

		comandoSql.execute(); 
		comandoSql.close(); 
	}
	
	/**
	 * Realiza a exclusao dos dados de uma linha da tabela turma.
	 * O <code>idTurma</code> deve ser igual ao da turma que deseja delete
	 * @param int idTurma
	 * @author Breno
	 * @throws SQLException 
	 */
	public void deleteId(int idTurma) throws SQLException {
		String sql = "delete from turma where idTurma = ?"; 
		PreparedStatement comandoSql = conexao.prepareStatement(sql);
			
		comandoSql.setInt(1, idTurma);
		
		comandoSql.execute(); 
		comandoSql.close(); 
	}
	
	/**
	 * Metodo de busca de todas as informacoes de uma linha
	 * da tabela Turma do banco de dados.
	 * O <code>idTurma</code> deve ser igual ao da turma que deseja buscar
	 * @param int idTurma
	 * @return Turma turma 
	 * @author Breno
	 * @throws SQLException 
	 */
	public Turma buscarId(int idTurma) throws SQLException {
		Turma turma = new Turma(); 
		String sql = "select * from turma where idTurma = ?"; 
		PreparedStatement comandoSql = conexao.prepareStatement(sql);
			
		comandoSql.setInt(1, idTurma);
		ResultSet resultSet = comandoSql.executeQuery();
		
		if (resultSet.next()) {
			turma.setIdTurma(resultSet.getInt(1));
			turma.setAno(resultSet.getString(2));
			turma.setQtdAluno(resultSet.getInt(3));
			turma.setHorarioInicioAula(resultSet.getTimestamp(4));
			turma.setHorarioFinalAula(resultSet.getTimestamp(5));
			turma.setFk_sala(resultSet.getInt(6));
		}
		comandoSql.close(); 
		return turma;
	}
	
	/**
	 * Retorna todos os dados listados da tabela turma do banco de dados 
	 * @return lista de turmas registradas no banco
	 * @author Breno
	 * @throws SQLException 
	 */
	public List<Turma> buscarTodos() throws SQLException {
		List<Turma> lista = new ArrayList<Turma>(); 
		String sql = "select * from turma"; 
			
		PreparedStatement comandoSql = conexao.prepareStatement(sql);
		ResultSet resultSet = comandoSql.executeQuery(); 
			
		while (resultSet.next()) {
			Turma turma = new Turma(); 
			turma.setIdTurma(resultSet.getInt(1));
			turma.setAno(resultSet.getString(2));
			turma.setQtdAluno(resultSet.getInt(3));
			turma.setHorarioInicioAula(resultSet.getTimestamp(4));
			turma.setHorarioFinalAula(resultSet.getTimestamp(5));
			turma.setFk_sala(resultSet.getInt(6));
			turma.setFk_escola(resultSet.getInt(7));
			lista.add(turma); 
		}
		comandoSql.close();
		return lista;
	}	
	
	//------------------------------------------------------------------
	//Método Extras - Fora dos 5 principais 
	//------------------------------------------------------------------
	
	/**
	 * Metodo de busca de todas as informacoes de uma linha
	 * da tabela Turma do banco de dados.
	 * O <code>fk_sala</code> deve ser igual ao da turma que deseja buscar
	 * @param int fk_sala
	 * @return Turma turma 
	 * @author Breno
	 * @throws SQLException 
	 */
	public Turma buscarIdSala(int fk_sala) throws SQLException {
		Turma turma = new Turma(); 
		String sql = "select * from turma where fk_sala = ?"; 
		PreparedStatement comandoSql = conexao.prepareStatement(sql);
			
		comandoSql.setInt(1, fk_sala);
		ResultSet resultSet = comandoSql.executeQuery();
		
		if (resultSet.next()) {
			turma.setIdTurma(resultSet.getInt(1));
			turma.setAno(resultSet.getString(2));
			turma.setQtdAluno(resultSet.getInt(3));
			turma.setHorarioInicioAula(resultSet.getTimestamp(4));
			turma.setHorarioFinalAula(resultSet.getTimestamp(5));
			turma.setFk_sala(resultSet.getInt(6));
		}
		comandoSql.close(); 
		return turma;
	}
	
	/**
	 * Metodo de busca de todas as informacoes de uma linha
	 * da tabela Turma do banco de dados.
	 * O <code>fk_escola</code> deve ser igual ao da turma que deseja buscar
	 * @param int fk_escola
	 * @return Turma turma 
	 * @author Andrey
	 * @throws SQLException 
	 */
	public List<Turma> buscarIdEscola(int fk_escola) throws SQLException {
		List<Turma> lista = new ArrayList<Turma>(); 
		String sql = "select * from turma where fk_escola = ?"; 
			
		PreparedStatement comandoSql = conexao.prepareStatement(sql);
		
		comandoSql.setInt(1, fk_escola);
		
		ResultSet resultSet = comandoSql.executeQuery(); 
			
		while (resultSet.next()) {
			Turma turma = new Turma(); 
			turma.setIdTurma(resultSet.getInt(1));
			turma.setAno(resultSet.getString(2));
			turma.setQtdAluno(resultSet.getInt(3));
			turma.setHorarioInicioAula(resultSet.getTimestamp(4));
			turma.setHorarioFinalAula(resultSet.getTimestamp(5));
			turma.setFk_sala(resultSet.getInt(6));
			turma.setFk_escola(resultSet.getInt(7));
			lista.add(turma); 
		}
		comandoSql.close();
		return lista;
	}	
	
	/**
	 * Metodo de busca de todas as informacoes de uma linha
	 * da tabela Turma do banco de dados onde a disciplina ocorre.
	 * O <code>idDisciplina</code> deve ser igual ao da turma que deseja buscar
	 * @param int idDisciplina
	 * @return Turma turma 
	 * @author Breno
	 * @throws SQLException 
	 */
	public List<Turma> buscarDisciplina(int idDisciplina) throws SQLException {
		List<Turma> lista = new ArrayList<Turma>(); 
		String sql = "select * "
				+ "   from turma,"
				+ "        disciplina,"
				+ "        usuario_disciplina,"
				+ "        usuario_disciplina_turma"
				+ "    where turma.idturma = usuario_disciplina_turma.fk_turma"
				+ "    and   usuario_disciplina_turma.fk_usuario_disciplina = usuario_disciplina.id_usuario_disciplina"
				+ "    and   usuario_disciplina.fk_disciplina = disciplina.iddisciplina"
				+ "    and disciplina.iddisciplina = ?"; 
			
		PreparedStatement comandoSql = conexao.prepareStatement(sql);
		
		comandoSql.setInt(1, idDisciplina);
		
		ResultSet resultSet = comandoSql.executeQuery(); 
			
		while (resultSet.next()) {
			Turma turma = new Turma(); 
			turma.setIdTurma(resultSet.getInt(1));
			turma.setAno(resultSet.getString(2));
			turma.setQtdAluno(resultSet.getInt(3));
			turma.setHorarioInicioAula(resultSet.getTimestamp(4));
			turma.setHorarioFinalAula(resultSet.getTimestamp(5));
			turma.setFk_sala(resultSet.getInt(6));
			turma.setFk_escola(resultSet.getInt(7));
			lista.add(turma); 
		}
		comandoSql.close();
		return lista;
	}	

	/**
	 * Metodo de busca de todas as informacoes de uma linha
	 * da tabela Turma do banco de dados onde o professor da aula ocorre.
	 * O <code>idUsuario</code> deve ser igual ao da turma que deseja buscar
	 * @param int idUsuario
	 * @return Turma turma 
	 * @author Breno
	 * @throws SQLException 
	 */
	public List<Turma> buscarTurmaIdUsuario(int idUsuario) throws SQLException {
		List<Turma> lista = new ArrayList<Turma>(); 
		String sql = "select * "
				+ "   from turma,"
				+ "        usuario_disciplina,"
				+ "        usuario_disciplina_turma"
				+ "    where turma.idturma = usuario_disciplina_turma.fk_turma"
				+ "    and   usuario_disciplina_turma.fk_usuario_disciplina = usuario_disciplina.id_usuario_disciplina"
				+ "    and   usuario_disciplina.fk_usuario = ?";
			
		PreparedStatement comandoSql = conexao.prepareStatement(sql);
		
		comandoSql.setInt(1, idUsuario);
		
		ResultSet resultSet = comandoSql.executeQuery(); 
			
		while (resultSet.next()) {
			Turma turma = new Turma(); 
			turma.setIdTurma(resultSet.getInt(1));
			turma.setAno(resultSet.getString(2));
			turma.setQtdAluno(resultSet.getInt(3));
			turma.setHorarioInicioAula(resultSet.getTimestamp(4));
			turma.setHorarioFinalAula(resultSet.getTimestamp(5));
			turma.setFk_sala(resultSet.getInt(6));
			turma.setFk_escola(resultSet.getInt(7));
			lista.add(turma); 
		}
		comandoSql.close();
		return lista;
	}
}
