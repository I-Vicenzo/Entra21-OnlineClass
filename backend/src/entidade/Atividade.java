package entidade;

import java.util.*;

/**
 * Classe contendo m�todos e atributos para a cria��o de atividades.
 * As atividades s�o em formato de arquivo e podem ser criadas pelo Professor.
 * @author 
 */
public class Atividade {
    private int idAtividade;
    private String descricao;
    private Date inicioAtividade;
    private Date finalAtividade;
    private int tipoAtividade;
    private double pesoNota;

    /**
     * Construtor usado ao instanciar a classe Atividade.
     * @param
     */
    public Atividade() {
    }


    /** M�todo para retorno do ID da atividade.
     * @return Int - ID da atividade
     */
    public int getIdAtividade() {
        return idAtividade;
    }

    /**
     * 
     */
    public void setIdAtividade() {
        // TODO implement here
    }

    /** M�todo para retorno da descri��o da atividade.
     * @return String - Descri��o da atividade
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * 
     */
    public void setDescricao() {
        // TODO implement here
    }

    /** M�todo para retorno da data de in�cio da atividade.
     * @return Date - Data de in�cio da atividade
     */
    public Date getInicioAtividade() {
        return inicioAtividade;
    }

    /**
     * 
     */
    public void setInicioAtividade() {
        // TODO implement here
    }

    /** M�todo para retorno da data final da atividade.
     * @return Date - Data final da atividade
     */
    public Date getFinalAtividadel() {
        return finalAtividade;
    }

    /**
     * 
     */
    public void setFinalAtividade() {
        // TODO implement here
    }

    /** M�todo para retorno do tipo de atividade.
     * @return Int - Tipo da atividade
     */
    public int getTipoAtividade() {
        return tipoAtividade;
    }

    /**
     * 
     */
    public void setTipoAtividade() {
        // TODO implement here
    }

    /** M�todo para retorno do peso da nota.
     * @return Double - Peso da nota
     */
    public double getPesoNota() {
        return pesoNota;
    }

    /**
     * 
     */
    public void setPesoNota() {
        // TODO implement here
    }

}