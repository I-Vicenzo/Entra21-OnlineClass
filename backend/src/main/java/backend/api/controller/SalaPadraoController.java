package backend.api.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import backend.api.controller.DTO.UsuarioDTO;
import entidade.Aluno;
import entidade.Sala;
import entidade.SalaPadrao;
import entidade.Turma;
import entidade.Usuario;
import persistencia.jdbc.AlunoDAO;
import persistencia.jdbc.SalaDAO;
import persistencia.jdbc.SalaPadraoDAO;
import persistencia.jdbc.TurmaDAO;
import persistencia.jdbc.UsuarioDAO;

/**
 * Metodo controller da salaPadrao para consulta no banco de dados através da API Rest
 * @author Breno
 *
 */
@RestController
@RequestMapping("salapadroes")
public class SalaPadraoController {
	
	public static final Logger LOGGER = LoggerFactory.getLogger("backend.api");
	
	/**
	 * Retorna a lista das salasPadroes registrados no sistema {GET}
	 * @return lista de salasPadroes registradas no banco
	 * @author Breno
	 */
	@CrossOrigin
	@GetMapping
	public List<SalaPadrao> consultar(){
		LOGGER.info("Requisição List<SalaPadrao>");
		List<SalaPadrao> lista;
		SalaPadraoDAO salaPadraoDao = new SalaPadraoDAO();
		try {
			lista = salaPadraoDao.buscarTodos();
			LOGGER.info("Requisição List<SalaPadrao> bem sucedida");
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
			LOGGER.error("Requisição para Consultar todos SalaPadrao Mal Sucedida - erro - {}",e.toString());
			return null;
		}
	}
	
	//------------------------------------------------------------------
	//Método Extras - Fora dos 5 principais 
	//------------------------------------------------------------------
	
	/**
	 * Retorna a lista das salasPadroes registrados no sistema pelo codigo do usuario {GET}
	 * @return lista de salasPadroes registradas no banco com o codigo do usuario
	 * @param int codigo
	 * @author Breno
	 */
	@CrossOrigin
	@GetMapping("/usuario/{codigo}")
	public String consultarIdUsuario(@PathVariable("codigo") int codigo) {
		AlunoDAO alunoDAO = new AlunoDAO();
		TurmaDAO turmaDAO = new TurmaDAO();
		SalaDAO salaDAO = new SalaDAO();
		Gson gson = new Gson();
		try {
			Aluno aluno = alunoDAO.buscarIdUsuario(codigo);
			Turma turma = turmaDAO.buscarId(aluno.getFk_turma());
			Sala sala = salaDAO.buscarId(turma.getFk_sala());
			return gson.toJson(sala);
		} catch (SQLException e) {
			LOGGER.error("Requisição para Consultar Turma Mal Sucedida - Turma {} - erro - {}",codigo,e.toString());
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Retorna a lista de Usuarios registrados no sistema {GET}
	 * que participaram dessa salaPadrao
	 * @return lista de salasPadroes registradas no banco
	 * @author Breno
	 */
	@CrossOrigin
	@GetMapping("/participantes/{codigo}")
	public List<UsuarioDTO> consultarIdSala(@PathVariable("codigo") int codigo){
		LOGGER.info("Requisição List<Usuario> da sala {}",codigo);
		List<Usuario> usuarios = new ArrayList<Usuario>();
		try {
			AlunoDAO alunoDAO = new AlunoDAO();
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			List<Aluno> alunos = alunoDAO.buscarTodosTurma(codigo);
			for (Aluno aluno : alunos) {
				usuarios.add(usuarioDAO.buscarId(aluno.getFk_usuario()));
			}
			List<UsuarioDTO> usuarioDTOs;
			usuarioDTOs = UsuarioDTO.converter(usuarios);
			LOGGER.info("Requisição List<Usuario> da Sala {} bem sucedida",codigo);
			return usuarioDTOs;
		} catch (SQLException e) {
			e.printStackTrace();
			LOGGER.error("Requisição para Consultar todos Usuario da Sala {} Mal Sucedida - erro - {}",codigo,e.toString());
			return null;
		}
	}
}
