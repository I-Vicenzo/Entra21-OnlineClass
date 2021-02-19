package entidade;

/**
 * Classe contendo metodos e atributos para o aluno
 * Herda metodos e atributos da classe Usuario.
 * @see Usuario
 * @author Andre
 */
public class Aluno {
    
	private int idAluno;
    private String ra;
    private String matricula;
    private boolean deficiencia;
    private String nomeMae;
    private String nomePai;
    private String nomeResponsavel;
    private boolean situacaoAnoLetivo;
    private int fk_usuario;
    private int fk_turma;
    
    /**
     * Construtor padrao
     */
    public Aluno() {
    	//Nenhum atributo inicializado
    }
    
    /**
     * Metodo construtor que preenche todos os atributos da classe aluno
     * @param idAluno
     * @param ra
     * @param matricula
     * @param deficiencia
     * @param nomeMae
     * @param nomePai
     * @param nomeResponsavel
     * @param situacaoAnoLetivo
     * @param fk_usuario
     * @param fk_turma
     */
    public Aluno(int idAluno, String ra, String matricula, boolean deficiencia, String nomeMae, String nomePai,
				 String nomeResponsavel, boolean situacaoAnoLetivo, int fk_usuario, int fk_turma) {

    	setIdAluno(idAluno);
    	setRa(ra);
    	setMatricula(matricula);
    	setDeficiencia(deficiencia);
    	setNomeMae(nomeMae);
    	setNomePai(nomePai);
    	setNomeResponsavel(nomeResponsavel);
    	setSituacaoAnoLetivo(situacaoAnoLetivo);
    	setFk_usuario(fk_usuario);
    	setFk_turma(fk_turma);
	}

	/** 
     * Metodo para retorno do ID do aluno
     * @return int idAluno
     */
    public int getIdAluno() {
        return idAluno;
    }

    /**
     * Metodo para inserção do ID do aluno 
     * @param int idAluno
     */
    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    /** 
     * Metodo para retorno do RA do aluno
     * @return String ra
     */
    public String getRa() {
        return ra;
    }

    /**
     * Metodo para inserção do RA
     * @param String ra
     */
    public void setRa(String ra) {
        this.ra = ra;
    }

    /** 
     * Metodo para retorno da matricula do aluno
     * @return String matricula
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     * Metodo para inserção de matricula 
     * @param String matricula
     */
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    /** 
     * Metodo para retornar se o aluno possui alguma deficiencia fisica
     * @return boolean deficiencia
     */
    public boolean getDeficiencia() {
        return deficiencia;
    }

    /**
     * Metodo para insercao da deficiencia 
     * @param boolean deficiencia
     */
    public void setDeficiencia(boolean deficiencia) {
        this.deficiencia = deficiencia;
    }

    /** 
     * Metodo para retorno do nome da mae do aluno
     * @return String nomeMae
     */
    public String getNomeMae() {
        return nomeMae;
    }

    /**
     * Metodo para insercao do nome da mae 
     * @param String nomeMae
     */
    public void setNomeMae(String nomeMae) {
        this.nomeMae =  nomeMae;
    }

    /** 
     * Metodo para retorno do nome do pai do aluno
     * @return String nomePai
     */
    public String getNomePai() {
        return nomePai;
    }

    /**
     * Metodo para insercao do nome do pai 
     * @param String nomePai
     */
    public void setNomePai(String nomePai) {
    	this.nomePai = nomePai;
    }

    /** 
     * Metodo para retorno do nome do responsavel pelo aluno.
     * @return String nomeResponsavel
     */
    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    /**
     * Metodo de inserção do nome do responsavel 
     * @param String nomeResponsavel
     */
    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    /** 
     * Metodo para retorno da situacao do ano letivo do aluno
     * @return boolean situacaoAnoLetivo
     */
    public boolean getSituacaoAnoLetivo() {
        return situacaoAnoLetivo;
    }

    /**
     * Metodo de insercao da situacao do ano letivo 
     * @param boolean situacaoAnoLetivo
     */
    public void setSituacaoAnoLetivo(boolean situacaoAnoLetivo) {
        this.situacaoAnoLetivo = situacaoAnoLetivo;
    }

    /**
     * Metodo para retorno do FK do usuario
     * @return int fk_usuario
     */
    public int getFk_usuario() {
		return fk_usuario;
	}

    /**
     * Metodo para inserção FK do usuario
     * @param int fk_usuario
     */
	public void setFk_usuario(int fk_usuario) {
		this.fk_usuario = fk_usuario;
	}

	/**
     * Metodo de retorno da turma 
	 * @return int fk_turma
	 */
	public int getFk_turma() {
		return fk_turma;
	}

	/**
	 * Metodo de insercao da turma 
	 * @param int fk_turma
	 */
	public void setFk_turma(int fk_turma) {
		this.fk_turma = fk_turma;
	}
}