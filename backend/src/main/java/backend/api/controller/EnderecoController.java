package backend.api.controller;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import entidade.Endereco;
import persistencia.jdbc.EnderecoDAO;

/**
 * Metodo controller do endereco para consulta no banco de dados através da API Rest
 * @author Breno
 *
 */
@RestController
public class EnderecoController {
	
	/**
	 * Retorna o endereco que corresponde ao id indicado {GET}
	 * @param int codigo
	 * @return String json
	 * @author Andre
	 * @return boolean situacao da operacao
	 */
	@GetMapping(path = "/api/endereco/{codigo}")
	public String consultar(@PathVariable("codigo") int codigo) {
		EnderecoDAO enderecoDao = new EnderecoDAO();
		Endereco endereco;
		try {
			endereco = enderecoDao.buscarId(codigo);
		} catch (SQLException e) {
			endereco = null;
			e.printStackTrace();
		}
		Gson gson = new Gson();
		String json = gson.toJson(endereco);
		return json;
	}
	
	/**
	 * Retorna a lista de enderecos registrados no sistema {GET}
	 * 
	 * @return lista de enderecos registrados no banco
	 * @author Andre
	 */
	@GetMapping(path = "/api/enderecos")
	public List<Endereco> consultar() {
		List<Endereco> lista;
		EnderecoDAO enderecoDao = new EnderecoDAO();
		try {
			lista = enderecoDao.buscarTodos();
		} catch (SQLException e) {
			lista = null;
			e.printStackTrace();
		}
		return lista;
	}

	/**
	 * Insere um novo endereco no banco de dados {POST}
	 * @param String json
	 * @author Andre
	 * @return boolean situacao da operacao
	 */
	@PostMapping(path = "api/endereco/inserir/{json}")
	public boolean inserir(@PathVariable("json") String json) {
		Gson gson = new Gson();
		Endereco endereco = gson.fromJson(json, Endereco.class);
		EnderecoDAO enderecoDAO = new EnderecoDAO();
		try {
			enderecoDAO.insert(endereco);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Metodo para alteração do endereco que corresponde ao codigo informado {PUT}
	 * @param int codigo
	 * @param String json
	 * @author Andre
	 * @return boolean situacao da operacao
	 */
	@PutMapping(path = "api/endereco/alterar/{json}")
	public boolean alterar(@PathVariable("json") String json) {
		Gson gson = new Gson();
		Endereco endereco = gson.fromJson(json, Endereco.class);
		EnderecoDAO enderecoDAO = new EnderecoDAO();
		try {
			enderecoDAO.update(endereco);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Método de exclusão do endereco que corresponde ao codigo informado {DELETE}
	 * @param int codigo
	 * @author Andre
	 * @return boolean situacao da operacao
	 */
	@DeleteMapping(path = "/api/endereco/deletar/{codigo}")
	public boolean deletar(@PathVariable("codigo") int codigo) {
		EnderecoDAO enderecoDAO = new EnderecoDAO();
		try {
			enderecoDAO.deleteId(codigo);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}