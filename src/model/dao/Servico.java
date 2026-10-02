package model.dao;

public class Servico {

    private String nome;
    private String Descricao;
    private String duracao;
    private String valor;

    public Servico(String nome, String descricao, String duracao, String valor) {
        this.nome = nome;
        Descricao = descricao;
        this.duracao = duracao;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
