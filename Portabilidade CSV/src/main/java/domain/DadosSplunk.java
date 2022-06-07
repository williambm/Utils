package domain;

public class DadosSplunk {
    private Integer convenio;
    private Integer quantidade;

    public DadosSplunk() {
    }

    public DadosSplunk(Integer convenio, Integer quantidade) {
        this.convenio = convenio;
        this.quantidade = quantidade;
    }

    public Integer getConvenio() {
        return convenio;
    }

    public void setConvenio(Integer convenio) {
        this.convenio = convenio;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
