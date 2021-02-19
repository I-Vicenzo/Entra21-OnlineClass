package persistencia.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import backend.api.controller.form.UsuarioDisciplinaForm;
import backend.api.controller.form.UsuarioDisciplinaTurmaForm;
import entidade.UsuarioDisciplinaTurma;

/**
 * Metodo para consulta do usuarioDisciplinaTurma no banco de dados 
 * @author Breno
 *
 */
public class UsuarioDisciplinaTurmaDAO {
	
	private Connection conexao = ConexaoFactory.getConnection();

	/**
	 * Realiza o registro de uma usuarioDisciplinaTurma no banco de dados
	 * @param UsuarioDisciplinaTurma usuarioDisciplinaTurma
	 * @author Breno
	 * @throws SQLException 
	 */
	public void insert(UsuarioDisciplinaTurma usuarioDisciplinaTurma) throws SQLException {
		String sql = "insert into usuario_Disciplina_Turma (fk_usuario_Disciplina, fk_Turma) values (?,?)"; 
		PreparedStatement comandoSql = conexao.prepareStatement(sql);
		     
		comandoSql.setInt(1, usuarioDisciplinaTurma.getFk_usuariorDisciplina());
		comandoSql.setInt(2, usuarioDisciplinaTurma.getFk_turma());
		
		comandoSql.execute(); 
		comandoSql.close(); 
	}
	
	/**
	 * Metodo para atualizar um usuarioDisciplinaTurma no banco de dados.
	 * O <code>idUsuarioDisciplinaTurma</code> deve ser igual ao do usuarioDisciplinaTurma que deseja atualizar
	 * @param UsuarioDisciplinaTurma usuarioDisciplinaTurma 
	 * @author Breno
	 * @throws SQLException 
	 */ 
	public void update(UsuarioDisciplinaTurma usuarioDisciplinaTurma) throws SQLException {
		String sql = "update usuario_Disciplina_Turma set fk_usuario = ?, fk_disciplina = ? where id_usuario_Disciplina_Turma = ?";
		PreparedStatement comandoSql = conexao.prepareStatement(sql);
			
		comandoSql.setInt(1, usuarioDisciplinaTurma.getFk_usuariorDisciplina());
		comandoSql.setInt(2, usuarioDisciplinaTurma.getFk_turma());
		comandoSql.setInt(3, usuarioDisciplinaTurma.getIdUsuarioDisciplinaTurma());
		
		comandoSql.execute();
		comandoSql.close();
	}
	
	/**
	 * Realiza a exclusao dos dados de uma linha da tabela usuarioDisciplinaTurma.
	 *  O <code>idUsuarioDisciplinaTurma</code> deve ser igual ao do usuarioDisciplinaTurma que deseja deletar
	 * @param int idUsuarioDisciplinaTurma
	 * @author Breno
	 * @throws SQLException 
	 */
	public void deleteId(int idUsuarioDisciplinaTurma) throws SQLException {
		String sql = "delete from usuario_Disciplina_Turma where idUsuario_Disciplina_Turma = ?"; 
		PreparedStatement comandoSql = conexao.prepareStatement(sql);
			
		comandoSql.setInt(1, idUsuarioDisciplinaTurma);
		
		comandoSql.execute(); 
		comandoSql.close(); 
	}
	
	/**
	 * Metodo de busca de todas as informacoes de uma linha
	 * da tabela usuarioDisciplinaTurma do banco de dados.
	 *  O <code>idTurmaUsuarioDisciplina</code> deve ser igual ao da usuarioDisciplinaTurma que deseja buscar
	 * @param int idUsuarioDisciplinaTurma
	 * @author Breno
	 * @throws SQLException 
	 */
	public UsuarioDisciplinaTurma buscarId(int idUsuarioDisciplinaTurma) throws SQLException {
		UsuarioDisciplinaTurma usuarioDisciplinaTurma = new UsuarioDisciplinaTurma(); 
		String sql = "select * from sala where idSala = ?"; 
		PreparedStatement comandoSql = conexao.prepareStatement(sql);
			
		comandoSql.setInt(1, idUsuarioDisciplinaTurma);
		ResultSet resultSet = comandoSql.executeQuery();
			
		if (resultSet.next()) {
			usuarioDisciplinaTurma.setIdUsuarioDisciplinaTurma(resultSet.getInt(1));
			usuarioDisciplinaTurma.setFk_usuariorDisciplina(resultSet.getInt(2));
			usuarioDisciplinaTurma.setFk_turma(resultSet.getInt(3));
		}
		comandoSql.close(); 
		return usuarioDisciplinaTurma;
	}
	
	/**
	 * Retorna todos os dados listados da tabela Sala do banco de dados 
	 * @return lista de usuariosDisciplinasTurmas registradas no banco
	 * @author Breno
	 * @throws SQLException 
	 */
	public List<UsuarioDisciplinaTurma> buscarTodos() throws SQLException {
		List<UsuarioDisciplinaTurma> lista = new ArrayList<UsuarioDisciplinaTurma>(); 
		String sql = "select * from usuario_Disciplina_Turma"; 
		
		PreparedStatement comandoSql = conexao.prepareStatement(sql);
		ResultSet resultSet = comandoSql.executeQuery();
		
		while (resultSet.next()) {
			UsuarioDisciplinaTurma usuarioDisciplinaTurma = new UsuarioDisciplinaTurma(); 
			usuarioDisciplinaTurma.setIdUsuarioDisciplinaTurma(resultSet.getInt(1));
			usuarioDisciplinaTurma.setFk_usuariorDisciplina(resultSet.getInt(2));
			usuarioDisciplinaTurma.setFk_turma(resultSet.getInt(3));
			
			lista.add(usuarioDisciplinaTurma); 
		}
		comandoSql.close(); 
		return lista;
	}

	//------------------------------------------------------------------
	//Método Extras - Fora dos 5 principais 
	//------------------------------------------------------------------

	/**
	 * Metodo para selecionar o idUsuarioDisciplinaTurma no banco de dados.
	 * O objeto usuarioDisciplinaTurma ter os atributos iguais aos do banco de dados
	 * caso nao tenha nenhum usuarioDisciplina igual sera retornado 0
	 * 
	 * @param UsuarioDisciplina usuarioDisciplina
	 * @return int idUsuarioDisciplina
	 * @author Andre
	 * @throws SQLException
	 */
	public int buscarIgual(UsuarioDisciplinaTurma usuarioDisciplinaTurma) throws SQLException {
		int idUsuarioDisciplina = 0;
		String sql = "select idusuario_Disciplina_turma from usuario_disciplina_turma where fk_usuario_disciplina=? and fk_turma=?";
		PreparedStatement comandoSql = conexao.prepareStatement(sql);
			
		comandoSql.setInt(1, usuarioDisciplinaTurma.getFk_usuariorDisciplina());
		comandoSql.setInt(2, usuarioDisciplinaTurma.getFk_turma());
		ResultSet resultSet = comandoSql.executeQuery();
		
		if (resultSet.next()) {
			idUsuarioDisciplina = resultSet.getInt(1);
		}
		comandoSql.close();
		return idUsuarioDisciplina;
	}

	public int insertReturn(UsuarioDisciplinaTurma usuarioDisciplinaTurma) throws SQLException {
		String sql = "insert into usuario_disciplina_turma (fk_usuario_disciplina, fk_turma) values (?,?)";
		PreparedStatement comandoSql = conexao.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		
		comandoSql.setInt(1, usuarioDisciplinaTurma.getFk_usuariorDisciplina());
		comandoSql.setInt(2, usuarioDisciplinaTurma.getFk_turma());
		
		comandoSql.execute();
        ResultSet rs = comandoSql.getGeneratedKeys();
        rs.next();
        int idusuarioDisciplina = rs.getInt(1);
		comandoSql.close();
		return idusuarioDisciplina; 
	}
	
	/**
	 * Metodo para selecionar as disciplinas
	 * que um professor idProfessor da na turma
	 * idTurma especifica
	 * @author Andre
	 * @throws SQLException 
	 * @param idTurma
	 * @param idProfessor
	 */
	public List<UsuarioDisciplinaTurma> buscarUsuarioDisciplinaTurmaIdTurmaIdProfessor(int idTurma, int idProfessor) throws SQLException {
		List<UsuarioDisciplinaTurma> lista = new ArrayList<UsuarioDisciplinaTurma>(); 
		String sql = "SELECT\r\n"
				+ "	usuario_disciplina_turma.*\r\n"
				+ "from\r\n"
				+ "	disciplina,\r\n"
				+ "    turma,\r\n"
				+ "    usuario_disciplina,\r\n"
				+ "    usuario_disciplina_turma,\r\n"
				+ "    usuario\r\n"
				+ "where\r\n"
				+ "		usuario.idusuario = usuario_disciplina.fk_usuario\r\n"
				+ "    and	usuario_disciplina.fk_disciplina = disciplina.iddisciplina\r\n"
				+ "    and	usuario_disciplina.id_usuario_disciplina = usuario_disciplina_turma.fk_usuario_disciplina\r\n"
				+ "    and	usuario_disciplina_turma.fk_turma = turma.idturma\r\n"
				+ "    and turma.idturma = ?\r\n"
				+ "    and usuario.idusuario = ?"; 
		
		PreparedStatement comandoSql = conexao.prepareStatement(sql);
		comandoSql.setInt(1, idTurma);
		comandoSql.setInt(2, idProfessor);
		ResultSet resultSet = comandoSql.executeQuery();
		
		while (resultSet.next()) {
			UsuarioDisciplinaTurma usuarioDisciplinaTurma = new UsuarioDisciplinaTurma(); 
			usuarioDisciplinaTurma.setIdUsuarioDisciplinaTurma(resultSet.getInt(1));
			usuarioDisciplinaTurma.setFk_usuariorDisciplina(resultSet.getInt(2));
			usuarioDisciplinaTurma.setFk_turma(resultSet.getInt(3));
			
			lista.add(usuarioDisciplinaTurma); 
		}
		comandoSql.close(); 
		return lista;
	}
	
	/**
	 * Metodo para selecionar as disciplinas do aluno.
	 * O <code>idUsuario</code> deve ser igual ao do usuario que deseja buscar
	 * @param int idAluno
	 * @author Andrey
	 * @throws SQLException 
	 */
	public List<UsuarioDisciplinaForm> buscarDisciplinasAluno(int idUsuario) throws SQLException {
		List<UsuarioDisciplinaForm> disciplinas = new ArrayList<UsuarioDisciplinaForm>();
		String sql = "select distinct disciplina.nome, disciplina.iddisciplina "
				+ "from "
				+ "	disciplina, "
				+ "    usuario, "
				+ "    aluno, "
				+ "    usuario_disciplina "
				+ "where "
				+ "    usuario.idusuario = usuario_disciplina.fk_usuario "
				+ "    and usuario_disciplina.fk_disciplina = disciplina.iddisciplina "
				+ "    and usuario.idusuario = ? "
				+ "";
		PreparedStatement comandoSql = conexao.prepareStatement(sql);
		
		comandoSql.setInt(1, idUsuario);
		ResultSet resultSet = comandoSql.executeQuery();
		
		while (resultSet.next()) {
			UsuarioDisciplinaForm usuarioDisciplinaForm = new UsuarioDisciplinaForm();
			usuarioDisciplinaForm.setNome(resultSet.getString(1));
			usuarioDisciplinaForm.setIdDisciplina(resultSet.getInt(2));

			disciplinas.add(usuarioDisciplinaForm);
		}
		comandoSql.close();
		return disciplinas;
	}
	
	/**
	 * Metodo de busca de todas as informacoes de uma linha
	 * da tabela Turma do banco de dados onde o professor da aula ocorre.
	 * O <code>idUsuario</code> deve ser igual ao da turma que deseja buscar
	 * @param int idUsuario
	 * @return turmas 
	 * @author Andrey
	 * @throws SQLException 
	 */
	public List<UsuarioDisciplinaTurmaForm> buscarTurmasIdUsuario(int idUsuario) throws SQLException {
		List<UsuarioDisciplinaTurmaForm> lista = new ArrayList<UsuarioDisciplinaTurmaForm>(); 
		String sql = "select distinct turma.idturma, turma.ano "
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
			UsuarioDisciplinaTurmaForm usuarioDisciplinaTurmaForm = new UsuarioDisciplinaTurmaForm(); 
			usuarioDisciplinaTurmaForm.setIdTurma(resultSet.getInt(1));
			usuarioDisciplinaTurmaForm.setAno(resultSet.getString(2));
			lista.add(usuarioDisciplinaTurmaForm); 
		}
		comandoSql.close();
		return lista;
	}	
}
