package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CRUD implements IUsuarioDAO{

    private Statement s;

    public CRUD(Statement s){
        this.s=s;
    }

//Cadastrar usuário

    public String InserirUsuario(String tabela, Usuario usuario){
        String SQL = "INSERT INTO usuario (CODIGOPESSOA, LOGIN, SENHA, EMAIL, INDICADORMASTER, INDICADORATIVO) " +
                "VALUES (NULL, '"+ usuario.getLogin()+ "','"+usuario.getSenha()+ "','"+usuario.getEmail()+ "', 0, 1)";
        int linhasafetadas = -1;

        try {
            linhasafetadas = s.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Usuário Cadastrado com Sucesso!";
    }

    //Verificar se usuário já não existe
    public boolean VerificarUsuarioExistente(String tabela, Usuario usuario){
        String SQL = "SELECT 1 FROM USUARIO WHERE LOGIN = '" + usuario.getLogin() + "'";

        try {
            ResultSet linhasafetadas = s.executeQuery(SQL);

            return (linhasafetadas.next());

        } catch (SQLException e) {
            e.printStackTrace();
        } return  false;
    }

}
