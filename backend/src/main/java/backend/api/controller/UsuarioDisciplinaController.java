package backend.api.controller;

import java.sql.SQLException;
import java.util.List;

import com.google.gson.Gson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import entidade.UsuarioDisciplina;
import persistencia.jdbc.UsuarioDisciplinaDAO;

/**
 * Metodo controller do usuarioDisciplina para consulta no banco de dados através da API Rest
 * @author Breno
 *
 */
@RestController
@RequestMapping("usuariodisciplinas")
public class UsuarioDisciplinaController {
	
	public static final Logger LOGGER = LoggerFactory.getLogger("backend.api");
	
	/**
	 * Retorna o usuarioDisciplina que corresponde ao id indicado {GET}
	 * @param int codigo
	 * @return String json
	 * @author Breno
	 */
	@CrossOrigin
	@GetMapping("/{codigo}")
	public String consultar(@PathVariable("codigo") int codigo) {
		LOGGER.info("Requisição UsuarioDisciplina codigo {} iniciada", codigo);
		UsuarioDisciplinaDAO usuarioDisciplinaDao = new UsuarioDisciplinaDAO();
		UsuarioDisciplina usuarioDisciplina;
		try {
			usuarioDisciplina = usuarioDisciplinaDao.buscarId(codigo);
			Gson gson = new Gson();
			String json = gson.toJson(usuarioDisciplina);
			LOGGER.info("Requisição UsuarioDisciplina codigo {} bem sucedida",codigo);
			return json;
		} catch (SQLException e) {
			e.printStackTrace();
			LOGGER.error("Requisição para Consultar UsuarioDisciplina Mal Sucedida - UsuarioDisciplina {} - erro - {}",codigo,e.toString());
			return null;
		}
	}
	
	/**
	 * Retorna a lista dos usuariosDisciplinas registrados no sistema {GET}
	 * @return lista de usuariosDisciplinas registrados no banco
	 * @author Breno
	 */
	@CrossOrigin
	@GetMapping()
	public List<UsuarioDisciplina> consultar(){
		LOGGER.info("Requisição List<UsuarioDisciplina>");
		List<UsuarioDisciplina> lista;
		UsuarioDisciplinaDAO usuarioDisciplinaDao = new UsuarioDisciplinaDAO();
		try {
			lista = usuarioDisciplinaDao.buscarTodos();
			LOGGER.info("Requisição List<UsuarioDisciplina> bem sucedida");
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
			LOGGER.error("Requisição para Consultar todos UsuarioDisciplina Mal Sucedida - erro - {}",e.toString());
			return null;
		}
	}
	
	/**
	 * Insere um novo usuarioDisciplina no banco de dados {POST}
	 * @param String json
	 * @author Breno
	 * @return boolean situacao da operacao
	 */
	@PostMapping(path = "api/usuarioDisciplina/inserir/{json}")
	public boolean inserir(@PathVariable("json") String json) {
		LOGGER.info("Requisição Inserir UsuarioDisciplina - {}",json);
		Gson gson = new Gson();
		UsuarioDisciplina usuarioDisciplina = gson.fromJson(json, UsuarioDisciplina.class);
		UsuarioDisciplinaDAO usuarioDisciplinaDAO = new UsuarioDisciplinaDAO();
		try {
			usuarioDisciplinaDAO.insert(usuarioDisciplina);
			LOGGER.info("Requisição Inserir UsuarioDisciplina - {} - Bem Sucedida",json);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			LOGGER.error("Requisição para Inserir UsuarioDisciplina Mal Sucedida - UsuarioDisciplina {} - erro - {}",json,e.toString());
			return false;
		}
	}

	/**
	 * Metodo para alteração do usuarioDisciplina que corresponde ao codigo informado {PUT}
	 * @param int codigo
	 * @param String json
	 * @author Breno
	 * @return boolean situacao da operacao
	 */
	@PutMapping(path = "api/usuarioDisciplina/alterar/{codigo}/{json}")
	public boolean alterar(@PathVariable("codigo") int codigo, @PathVariable("json") String json) {
		LOGGER.info("Requisição Atualizar UsuarioDisciplina - {}",json);
		Gson gson = new Gson();
		UsuarioDisciplina usuarioDisciplina = gson.fromJson(json, UsuarioDisciplina.class);
		UsuarioDisciplinaDAO usuarioDisciplinaDAO = new UsuarioDisciplinaDAO();
		try {
			usuarioDisciplinaDAO.update(usuarioDisciplina);
			LOGGER.info("Requisição Atualizar UsuarioDisciplina - {} - Bem Sucedida",json);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			LOGGER.error("Requisição para Atualizar UsuarioDisciplina Mal Sucedida - UsuarioDisciplina {} - erro - {}",json,e.toString());
			return false;
		}
	}
	
	/**
	 * Método de exclusão do usuarioDisciplina que corresponde ao codigo informado {DELETE}
	 * @param int codigo
	 * @author Breno
	 * @return boolean situacao da operacao
	 */
	@CrossOrigin
	@DeleteMapping("/{codigo}")
	public boolean deletar(@PathVariable("codigo") int codigo) {
		LOGGER.info("Requisição para Deletar UsuarioDisciplina id - {}",codigo);
		UsuarioDisciplinaDAO usuarioDisciplinaDAO = new UsuarioDisciplinaDAO();
		try {
			usuarioDisciplinaDAO.deleteId(codigo);
			LOGGER.info("Requisição para Deletar UsuarioDisciplina id - {} - Bem Sucedida",codigo);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Requisição para Deletar UsuarioDisciplina Mal Sucedida - UsuarioDisciplina {} - erro - {}",codigo,e.toString());
			return false;
		}
	}
	
	/**
	 * Verifica se existe alguem endereco identico no
	 * banco e Insere um novo endereco no banco de dados
	 * caso nao tenha um igual ou retorna o id do enderco
	 * ja existente
	 * 
	 * @param String json
	 * @author Andre
	 * @return boolean situacao da operacao
	 */	
	@PostMapping(path = "/api/usuarioDisciplina/inserirAlterar/{json}")
	public int inserirDisciplinaTurma(@PathVariable("json") String json) {
		LOGGER.info("Requisição Inserir UsuarioDisciplina - {}",json);
		Gson gson = new Gson();
		UsuarioDisciplina usuarioDisciplinas = gson.fromJson(json, UsuarioDisciplina.class);
		UsuarioDisciplinaDAO usuarioDisciplinaDAO = new UsuarioDisciplinaDAO();
		try {
			int idUserDisc = usuarioDisciplinaDAO.buscarIgual(usuarioDisciplinas);
			if (idUserDisc==0) {
				idUserDisc = usuarioDisciplinaDAO.insertReturn(usuarioDisciplinas);
			}
			
			LOGGER.info("Requisição Inserir UsuarioDisciplina - Bem Sucedida - {}",json);
			return idUserDisc;
		} catch (SQLException e) {
			e.printStackTrace();
			LOGGER.error("Requisição para Inserir UsuarioDisciplina Mal Sucedida - Usuario {} - erro - {}",json,e.toString());
			return 0;
		}
	}
}
