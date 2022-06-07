package domain;

import java.io.Serializable;

public class Relatorio implements Serializable {

    private Integer convenio;
    private String nome;
    private Integer quantidade;

    public Relatorio() {
    }

    public Relatorio(Integer convenio, String nome, Integer quantidade) {
        this.convenio = convenio;
        this.nome = nome;
        this.quantidade = quantidade;
    }

    public Integer getConvenio() {
        return convenio;
    }

    public void setConvenio(Integer convenio) {
        this.convenio = convenio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Relatorio{" +
                "convenio=" + convenio +
                ", nome='" + nome + '\'' +
                ", quantidade=" + quantidade +
                '}';
    }
}
