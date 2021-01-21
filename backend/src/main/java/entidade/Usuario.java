package entidade;

import java.sql.Time;

/**
 * Classe contendo metodos e atributos para o usuario do sistema.
 * Os atributos e metodos dessa classe sao herdados pelas classes Administrador, Aluno, Coordenador, Diretor e Professor
 * @see Administrador
 * @see Aluno
 * @see Coordenador
 * @see Diretor
 * @see Professor
 * @author Breno
 */
public class Usuario {
	
    private int idUsuario;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String telefone;
    private String celular;
    private int tipoUsuario;
    private String email;
    private String senha;
    private Time horarioInicioExpediente;
    private Time horarioFinalExpediente;
    private String fotoUsuario;
    private int fk_endereco;
    private int fk_escola;
    
    /**
     * Construtor padrao
     */
    public Usuario() {
    	//Nenhum atributo inicializado
    }

    /**
     * Metodo construtor que preenche todos os atributos da classe 
     * @param idUsuario
     * @param nome
     * @param sobrenome
     * @param cpf
     * @param telefone
     * @param celular
     * @param tipoUsuario
     * @param email
     * @param senha
     * @param horarioInicioExpediente
     * @param horarioFinalExpediente
     * @param fotoUsuario
     * @param fk_endereco
     * @param fk_escola
     */
    public Usuario(int idUsuario, String nome, String sobrenome, String cpf, String telefone, String celular,
				   int tipoUsuario, String email, String senha, Time horarioInicioExpediente, Time horarioFinalExpediente,
				   String fotoUsuario, int fk_endereco, int fk_escola) {
    	
    	setIdUsuario(idUsuario);
		setNome(nome);
		setSobrenome(sobrenome);
		setCpf(cpf);
		setTelefone(telefone);
		setCelular(celular);
		setTipoUsuario(tipoUsuario);
		setEmail(email);
		setSenha(senha);
		setHorarioInicioExpediente(horarioInicioExpediente);
		setHorarioFinalExpediente(horarioFinalExpediente);
		setFotoUsuario(fotoUsuario);
		setFk_endereco(fk_endereco);
		setFk_escola(fk_escola);
	}
    
    /**
     * Metodo construtor que preenche todos os atributos da classe menos a escola 
     * @param idUsuario
     * @param nome
     * @param sobrenome
     * @param cpf
     * @param telefone
     * @param celular
     * @param tipoUsuario
     * @param email
     * @param senha
     * @param horarioInicioExpediente
     * @param horarioFinalExpediente
     * @param fotoUsuario
     * @param endereco
     */
    public Usuario(int idUsuario, String nome, String sobrenome, String cpf, String telefone, String celular,
			   	   int tipoUsuario, String email, String senha, Time horarioInicioExpediente, Time horarioFinalExpediente,
			       String fotoUsuario, int fk_endereco) {
	
		setIdUsuario(idUsuario);
		setNome(nome);
		setSobrenome(sobrenome);
		setCpf(cpf);
		setTelefone(telefone);
		setCelular(celular);
		setTipoUsuario(tipoUsuario);
		setEmail(email);
		setSenha(senha);
		setHorarioInicioExpediente(horarioInicioExpediente);
		setHorarioFinalExpediente(horarioFinalExpediente);
		setFotoUsuario(fotoUsuario);
		setFk_endereco(fk_endereco);
}

	/**
	 * Metodo para retorno do ID do usuario
     * @return int idUsuario
     */
    public int getIdUsuario() {
    	 return idUsuario;
    }

    /**
     * Metodo de insercao do id usuario 
     * @param int idUsuario
     */
    public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	/** 
	 * Metodo para retorno do nome do usuario
     * @return String nome
     */
    public String getNome() {
        return nome;
    }
    
    /**
     * Metodo de insercao do nome
     * @param String nome
     */
    public void setNome(String nome) {
		this.nome = nome;
	}

    /** 
     * Metodo para retorno do sobrenome do usuario.
     * @return String sobrenome
     */
    public String getSobrenome() {
        return sobrenome;
    }

    /**
     * Metodo de insercao do sobrenome do usuario
     * @param String sobrenome
     */
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

    /** 
     * Metodo para retorno do cpf do usuario.
     * @return String cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Metodo de insercao do cpf do usuario 
     * @param String cpf
     */
    public void setCpf(String cpf) {
		this.cpf = cpf;
	}
    
    /** 
     * Metodo para retorno do telefone do usuario.
     * @return String telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * Metodo para insercaoo do telefone do usuario 
     * @param String telefone
     */
    public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

    /** 
     * Metodo para retorno do numero de celular do usuario.
     * @return String celular
     */
    public String getCelular() {
        return celular;
    }

    /**
     * Metodo para insercao do celular do usuario 
     * @param String celular
     */
    public void setCelular(String celular) {
		this.celular = celular;
	}

    /** 
     * 1 - Administrador,
	 * 2 - Diretor,
	 * 3 - Coordenador,
	 * 4 - Professor,
	 * 5 - Aluno.
     * 
     * Metodo para retorno do tipo de conta do usuario.
     * @return int tipoUsuario
	 * 							
     */
    public int getTipoUsuario() {
        return tipoUsuario;
    }

    /** 
     * Metodo para insercao do tipo do usuario
     * @param int tipoUsuario
     */
    public void setTipoUsuario(int tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

    /** 
     * Metodo para retorno do email do usuario
     * @return String email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Metodo de insercao do email do usuario
     * @param String email
     */
    public void setEmail(String email) {
		this.email = email;
	}

    /**
     * Metodo de insercao de senha 
     * @param String senha
     */
    public void setSenha(String senha) {
		this.senha = senha;
	}
    
    /**
     * Metodo de retorno da senha do usuario
     * @return String senha 
     */
	public String getSenha() {
		return senha;
	}

	/** 
	 * Metodo para retorno do horario inicial do expediente do usuario.
     * @return Time horarioInicioExpediente
     */
    public Time getHorarioInicioExpediente() {
        return horarioInicioExpediente;
    }

    /**
     * Metodo para insercao do horarioInicioExpediente do usuario
     * @param Time horarioInicioExpediente
     */
    public void setHorarioInicioExpediente(Time horarioInicioExpediente) {
		this.horarioInicioExpediente = horarioInicioExpediente;
	}

    /** 
     * Metodo para retorno do horario final do expediente do usuario.
     * @return Time horarioFinalExpediente
     */
    public Time getHorarioFinalExpediente() {
        return horarioFinalExpediente;
    }

    /**
     * Metodo para insercao do horarioFinalExpediente do usuario 
     * @param Time horarioFinalExpediente
     */
    public void setHorarioFinalExpediente(Time horarioFinalExpediente) {
		this.horarioFinalExpediente = horarioFinalExpediente;
	}
    
    /**
     * Metodo para pegar o caminho da foto do usuario
     * @return String fotoUsuario
     */
    public String getFotoUsuario() {
		return fotoUsuario;
	}

    /**
     * Metodo de insercao do caminho da foto do usuario
     * @param String fotoUsuario
     */
	public void setFotoUsuario(String fotoUsuario) {
		this.fotoUsuario = fotoUsuario;
	}

	/**
	 * Metodo para retorno do FK do endereco
	 * @return int fk_endereco
	 */
	public int getFk_endereco() {
		return fk_endereco;
	}

	/**
	 * Metodo para insercao do FK do endereco
	 * @param int fk_endereco
	 */
	public void setFk_endereco(int fk_endereco) {
		this.fk_endereco = fk_endereco;
	}

	/**
	 * Metodo para retorno do FK da escola 
	 * @return int fk_escola 
	 */
	public int getFk_escola() {
		return fk_escola;
	}

	/**
	 * Metodo de insercao do FK da escola 
	 * @param int fk_escola
	 */
	public void setFk_escola(int fk_escola) {
		this.fk_escola = fk_escola;
	}
	
	
	//-------------
	

//	/** 
//	 * Metodo para a verificacao de cpf.
//     * Recebe uma String e converte para inteiro, realizando o c�lculo para a verifica��o do d�gito ver�ficador.
//     * @param String - CPF a ser verificado. 
//     * @return Boolean - True para CPF v�lido / False para inv�lido.
//     */
//    public boolean verificarCpf(String cpf) {
//        return false;
//    }
//
//    /** 
//     * Metodo para verificacao do login.
//     * Recebe o email e senha e verifica no banco de dados se os dados coincidem com os cadastrados pelo usu�rio.
//     * @param String - Email a ser verificado.
//     * @param String - Senha a ser verificada.
//     * @return Boolean - True para login correto / False para incorreto.
//     */
//    public boolean verificarLogin(String email, String senha) {
//        return false;
//    }
//
//    /** 
//     * Metodo para pesquisar um usuario especifico a partir do ID.
//     * @param Int - ID do usuario.
//     * @return Usuario - Retorna o usu�rio.
//     */
//    public void pesquisarUsuario(int id) {
//    }
//
//    /** 
//     * Metodo para envio de um relatorio. 
//     * @param -
//     */
//    public void mandarRelatorio() {
//        // TODO implement here
//    }
//
//    /** 
//     * Metodo para visualizar os relatorios enviados, a partir do banco de dados.
//     * @param -
//     * @return Relatorio - Retorna os relat�rios enviados.
//     */
//    public Relatorio verRelatoriosEnviados() {
//        return null;
//    }
//
//    /** 
//     * Metodo para buscar um relatorio enviado, a partir do banco de dados.
//     * @param -
//     * @return Relatorio - Retorna um relat�rio.
//     */
//    public void buscarRelatorioEnviado() {
//        // TODO implement here
//    }
//
//    /** 
//     * Metodo para visualizar os relatorios recebidos, a partir do banco de dados.
//     * @param -
//     * @return Relatorio - Retorna os relat�rios recebidos.
//     */
//    public void verRelatoriosRecebidos() {
//        // TODO implement here
//    }
//
//    /** 
//     * Metodo para buscar um relatorio recebido, a partir do banco de dados.
//     * @param -
//     * @return Relatorio - Retorna um relat�rio.
//     */
//    public void buscarRelatorioRecebido() {
//        // TODO implement here
//    }
//
//    /** 
//     * Metodo para a remocao de um relatorio.
//     * @param Relatorio - Relat�rio a ser removido.
//     */
//    public void removerRelatorio(Relatorio relatorio) {
//        // TODO implement here
//    }
//
//    /** 
//     * Metodo para o envio do convite para acessar uma reuni�o.
//     * @param -
//     */
//    public void mandarConvite() {
//        // TODO implement here
//    }
//
//    /** 
//     * Metodo para visualizar os convites enviados, a partir do banco de dados.
//     * @param -
//     * @return Convite - Retorna os convites enviados.
//     */
//    public void verConvitesEnviados() {
//        // TODO implement here
//    }
//
//    /** 
//     * Metodo para buscar um convite enviado, a partir do banco de dados.
//     * @param -
//     * @return Convite - Retorna um convite.
//     */
//    public void buscarConviteEnviado() {
//        // TODO implement here
//    }
//
//    /** 
//     * Metodo para buscar os convites recebidos, a partir do banco de dados.
//     * @param -
//     * @return Convite - Retorna os convites recebidos.
//     */
//    public void verConvitesRecebidos() {
//        // TODO implement here
//    }
//
//    /** 
//     * Metodo para buscar um convite recebido, a partir do banco de dados.
//     * @param -
//     * @return Convite - Retorna um convite.
//     */
//    public void buscarConviteRecebido() {
//        // TODO implement here
//    }
//
//    /** 
//     * Metodo para a remocao de um convite.
//     * @param Convite - Convite a ser removido.
//     */
//    public void removerConvite() {
//        // TODO implement here
//    }
//
//    /** 
//     * Metodo para a aceitacao de um convite.
//     * @param Boolean - Resposta do convite.
//     * @return Boolean - True.
//     */
//    public boolean aceitarConvite(boolean convite) {
//        return true;
//    }
//
//    /** 
//     * Metodo para recusar um convite.
//     * @param Boolean - Resposta do convite.
//     * @return Boolean - False.
//     */
//    public boolean recusarConvite(boolean convite) {
//        return false;
//    }
//
//    /** 
//     * Metodo para o envio de um arquivo.
//     * @param -
//     */
//    public void mandarArquivo() {
//        // TODO implement here
//    }
//
//    /** 
//     * Metodo para visualizar os arquivos enviados, a partir do banco de dados.
//     * @param -
//     * @return Arquivo - Retorna os arquivos enviados.
//     */
//    public Arquivo verArquivosEnviados() {
//        return null;
//    }
//
//    /** 
//     * Metodo para visualizar um arquivo especifico enviado, a partir do banco de dados.
//     * @param -
//     * @return Arquivo - Retorna o arquivo.
//     */
//    public void buscarArquivoEnviado() {
//        // TODO implement here
//    }
//
//    /** 
//     * Metodo para visualizar os arquivos recebidos, a partir do banco de dados.
//     * @param -
//     * @return Arquivo - Retorna os arquivos recebidos.
//     */
//    public void verArquivosRecebidos() {
//        // TODO implement here
//    }
//
//    /** 
//     * Metodo para visualizar arquivo especifico recebido, a partir do banco de dados.
//     * @param -
//     * @return Arquivo - Retorna o arquivo.
//     */
//    public void buscarArquivoRecebido() {
//        // TODO implement here
//    }
//
//    /** 
//     * Metodo para a remo��o de um arquivo.
//     * @param -
//     */
//    public void removerArquivo() {
//        // TODO implement here
//    }
//
//    /** 
//     * Metodo para visualizar uma sala personalizada.
//     * @param -
//     * @return SalaPersonalizada.
//     */
//    public SalaPersonalizada getSalaPersonalizada() {
//        return null;
//    }
//
//    /** 
//     * Metodo para a cria��o de uma sala personalizada.
//     * @param -
//     */
//    public void criarSalaPersonalizada() {
//        // TODO implement here
//    }
//
//    /** 
//     * Metodo para atualizar uma sala personalizada.
//     * @param SalaPersonalizadaDAO - Sala a ser atualizada.
//     */
//    public void atualizarSalaPersonalizada(SalaPersonalizada salaP) {
//        // TODO implement here
//    }
//
//    /** 
//     * Metodo para remo��o de uma sala personalizada.
//     * @param SalaPersonalizadaDAO - Sala a ser removida.
//     */
//    public void removerSalaPersonalizada(SalaPersonalizada salaP) {
//        // TODO implement here
//    }
//
//    /** 
//     * Metodo para visualizar uma reuni�o.
//     * @return Reuniao.
//     */
//    public Reuniao getReuniao() {
//        return null;
//    }
//
//    /** 
//     * Metodo para a cria��o de uma reuni�o.
//     * @param -
//     */
//    public void criarReuniao() {
//        // TODO implement here
//    }
//
//    /** 
//     * Metodo para a atualiza��o de uma reuni�o.
//     * @param Reuniao - Reuni�o a ser atualizada.
//     */
//    public void atualizarReuniao(Reuniao reuniao) {
//        // TODO implement here
//    }
//
//    /** 
//     * Metodo para a remo��o de uma reuni�o.
//     * @param Reuniao - Reuni�o a ser removida.
//     */
//    public void removerReuniao(Reuniao reuniao) {
//        // TODO implement here
//    }
//
//    /** 
//     * Metodo para entrar em uma reuni�o.
//     * @param - 
//     */
//    public void entrarReuniao() {
//        // TODO implement here
//    }
//
//    /** 
//     * Metodo para sair de uma reuni�o.
//     * @param -
//     */
//    public void sairReuniao() {
//        // TODO implement here
//    }
//
//    /** 
//     * Metodo para visualizar uma chamada.
//     * @param - 
//     * @return Chamada.
//     */
//    public Chamada getChamada() {
//        return null;
//    }
//
//    /** 
//     * Metodo para a realiza��o da chamada.
//     * @param -
//     */
//    public void fazerChamada() {
//        // TODO implement here
//    }
//
//    /** 
//     * Metodo a remo��o de uma chamada.
//     *  @param Chamada - Chamada a ser removida.
//     */
//    public void removerChamada(Chamada chamada) {
//        // TODO implement here
//    }
}