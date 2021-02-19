package persistencia.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import backend.api.controller.form.TurmaAtividadeForm;
import entidade.Atividade;

/**
 * Metodo para consulta da atividade no banco de dados
 * @author Andre
 *
 */
public class AtividadeDAO {
	
	private Connection conexao = ConexaoFactory.getConnection();
	
	/**
	 * Metodo para inserir atividade no banco de dados
	 * @param Atividade atividade
	 * @author Andre
	 * @throws SQLException 
	 */
	public void insert(Atividade atividade) throws SQLException {
		String sql = "insert into atividade (descricao, inicioatividade, finalatividade, tipoatividade, pesonota, fk_usuario_disciplina, titulo, fk_arquivo) values (?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement comandoSql = conexao.prepareStatement(sql);
		
		comandoSql.setString(1, atividade.getDescricao());
		comandoSql.setTimestamp(2, atividade.getInicioAtividade());
		comandoSql.setTimestamp(3, atividade.getFinalAtividade());
		comandoSql.setInt(4, atividade.getTipoAtividade());
		comandoSql.setDouble(5, atividade.getPesoNota());
		comandoSql.setInt(6, atividade.getFk_usuarioDisciplina());
		comandoSql.setString(7, atividade.getTitulo());
		comandoSql.setInt(8, atividade.getFk_arquivo());
		
		comandoSql.execute();
		comandoSql.close();
	}
	
	/**
	 * Metodo para atualizar uma atividade no banco de dados.
	 * O <code>idAtividade</code> deve ser igual ao da atividade que deseja atualizar
	 * @param Atividade atividade
	 * @author Andre
	 * @throws SQLException 
	 */ 
	public void update(Atividade atividade) throws SQLException {
		String sql = "update atividade set descricao = ?, inicioatividade = ?, finalatividade = ?, tipoatividade = ?, "
				+ "pesonota = ?, fk_usuario_disciplina, titulo = ?, fk_arquivo = ?  where idatividade = ?";
		PreparedStatement comandoSql = conexao.prepareStatement(sql);
		
		comandoSql.setString(1, atividade.getDescricao());
		comandoSql.setTimestamp(2, atividade.getInicioAtividade());
		comandoSql.setTimestamp(3, atividade.getFinalAtividade());
		comandoSql.setInt(4, atividade.getTipoAtividade());
		comandoSql.setDouble(5, atividade.getPesoNota());
		comandoSql.setInt(6, atividade.getFk_usuarioDisciplina());
		comandoSql.setString(7, atividade.getTitulo());
		comandoSql.setInt(8, atividade.getFk_arquivo());
		comandoSql.setInt(9, atividade.getIdAtividade());
		
		comandoSql.execute();
		comandoSql.close();
	}
	
	/**
	 *  Metodo para deletar do banco de dados uma atividade.
	 *  O <code>idAtividade</code> deve ser igual ao da atividade que deseja deletar
	 * @param int idAtividade
	 * @author Andre
	 * @throws SQLException 
	 */
	public void deleteId(int idAtividade) throws SQLException {
		String sql = "delete from atividade where idatividade = ?";
		PreparedStatement comandoSql = conexao.prepareStatement(sql);
		
		comandoSql.setInt(1, idAtividade);
		
		comandoSql.execute();
		comandoSql.close();
	}
	
	/**
	 * Metodo para selecionar uma atividade do banco de dados.
	 * O <code>idAtividade</code> deve ser igual ao da atividade que deseja buscar
	 * @param int idAtividade
	 * @author Andre
	 * @throws SQLException 
	 */
	public Atividade buscarId(int idAtividade) throws SQLException {
		Atividade atividade = new Atividade();
		String sql = "select * from atividade where idatividade = ?";
		PreparedStatement comandoSql = conexao.prepareStatement(sql);
		
		comandoSql.setInt(1, idAtividade);
		ResultSet resultSet = comandoSql.executeQuery();
		
		if (resultSet.next()) {
			atividade.setIdAtividade(resultSet.getInt(1));
			atividade.setDescricao(resultSet.getString(2));
			atividade.setInicioAtividade(resultSet.getTimestamp(3));
			atividade.setFinalAtividade(resultSet.getTimestamp(4));
			atividade.setTipoAtividade(resultSet.getInt(5));
			atividade.setPesoNota(resultSet.getDouble(6));
			atividade.setFk_usuarioDisciplina(resultSet.getInt(7));
			atividade.setTitulo(resultSet.getString(8));
			atividade.setFk_arquivo(resultSet.getInt(9));
		}
		comandoSql.close();
		return atividade;
	}
	
	/**
	 * Metodo para selecionar todas as atividades do banco de dados
	 * @author Andre
	 * @return lista de atividades resgistradas no banco 
	 * @throws SQLException 
	 */
	public List<Atividade> buscarTodos() throws SQLException {
		List<Atividade> lista =  new ArrayList<Atividade>();
		String sql = "select * from atividade";
		
		PreparedStatement comandoSql = conexao.prepareStatement(sql);
		ResultSet resultSet = comandoSql.executeQuery();
		
		while (resultSet.next()) {
			Atividade atividade = new Atividade();
			atividade.setIdAtividade(resultSet.getInt(1));
			atividade.setDescricao(resultSet.getString(2));
			atividade.setInicioAtividade(resultSet.getTimestamp(3));
			atividade.setFinalAtividade(resultSet.getTimestamp(4));
			atividade.setTipoAtividade(resultSet.getInt(5));
			atividade.setPesoNota(resultSet.getDouble(6));
			atividade.setFk_usuarioDisciplina(resultSet.getInt(7));
			atividade.setTitulo(resultSet.getString(8));
			atividade.setFk_arquivo(resultSet.getInt(9));
			lista.add(atividade);
		}
		comandoSql.close();
		return lista;
	}

	//------------------------------------------------------------------
	//Método Extras - Fora dos 5 principais 
	//------------------------------------------------------------------
	
	/**
	 * Metodo para selecionar uma atividade do banco de dados.
	 * O <code>idAtividade</code> deve ser igual ao da atividade que deseja buscar
	 * @param int idAtividade
	 * @author Andre
	 * @throws SQLException 
	 */
	public List<TurmaAtividadeForm> buscarTurmaAtividade(int idTurma) throws SQLException {
		List<TurmaAtividadeForm> turma_atividade =	new ArrayList<TurmaAtividadeForm>();
		String sql = "select atividade.idatividade, atividade.titulo, atividade.tipoatividade, atividade.finalatividade, atividade.fk_arquivo, disciplina.iddisciplina, disciplina.nome\r\n"
				+ "from "
				+ "	atividade, "
				+ " arquivo, "
				+ "    usuario_disciplina, "
				+ "    disciplina, "
				+ "    turma_atividade, "
				+ "    turma "
				+ "where "
				+ "    atividade.fk_arquivo = arquivo.idarquivo"
				+ "	   and atividade.fk_usuario_disciplina = usuario_disciplina.id_usuario_disciplina "
				+ "    and usuario_disciplina.fk_disciplina = disciplina.iddisciplina "
				+ "    and atividade.idatividade = turma_atividade.fk_atividade "
				+ "    and turma_atividade.fk_turma =  turma.idturma "
				+ "    and turma.idturma = ? "
				+ "order by atividade.finalatividade";
		PreparedStatement comandoSql = conexao.prepareStatement(sql);
		
		comandoSql.setInt(1, idTurma);
		ResultSet resultSet = comandoSql.executeQuery();
		
		while (resultSet.next()) {
			TurmaAtividadeForm turmaAtividadeForm = new TurmaAtividadeForm();
			turmaAtividadeForm.setIdAtividade(resultSet.getInt(1));
			turmaAtividadeForm.setTituloAtividade(resultSet.getString(2));
			turmaAtividadeForm.setTipoAtividade(resultSet.getInt(3));
			turmaAtividadeForm.setFinalAtividade(resultSet.getTimestamp(4));
			turmaAtividadeForm.setFk_arquivo(resultSet.getInt(5));
			turmaAtividadeForm.setIdDisciplina(resultSet.getInt(6));
			turmaAtividadeForm.setDisciplinaNome(resultSet.getString(7));
			
			turma_atividade.add(turmaAtividadeForm);
		
		}
		comandoSql.close();
		return turma_atividade;
	}

	/**
	 * Metodo para inserir atividade no banco de dados
	 * e Retornar o idAtividade gerado
	 * @param Atividade atividade
	 * @author Andre
	 * @throws SQLException 
	 * @return int idAtividade
	 */
	public int insertReturnID(Atividade atividade) throws SQLException {
		String sql = "insert into atividade (descricao, inicioatividade, finalatividade, tipoatividade, pesonota, fk_usuario_disciplina, titulo, fk_arquivo) values (?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement comandoSql = conexao.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		
		comandoSql.setString(1, atividade.getDescricao());
		comandoSql.setTimestamp(2, atividade.getInicioAtividade());
		comandoSql.setTimestamp(3, atividade.getFinalAtividade());
		comandoSql.setInt(4, atividade.getTipoAtividade());
		comandoSql.setDouble(5, atividade.getPesoNota());
		comandoSql.setInt(6, atividade.getFk_usuarioDisciplina());
		comandoSql.setString(7, atividade.getTitulo());
		comandoSql.setInt(8, atividade.getFk_arquivo());
		
		comandoSql.execute();
		ResultSet rs = comandoSql.getGeneratedKeys();
        rs.next();
        int idEndereco = rs.getInt(1);
		comandoSql.close();
		return idEndereco;
	}
}
