package model.dao;

public class Usuario {

    private String login;
    private String senha;
    private String email;
    private int IndicadorAtivo;

    public Usuario(String login, String email, String senha,int indicadorAtivo) {
        this.login = login;
        IndicadorAtivo = indicadorAtivo;
        this.email = email;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIndicadorAtivo() {
        return IndicadorAtivo;
    }

    public void setIndicadorAtivo(int indicadorAtivo) {
        IndicadorAtivo = indicadorAtivo;
    }

}
