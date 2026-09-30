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

    public String InserirUsuario(String tabela, Usuario usuario, Pessoa pessoa){
        String SQLPessoa = "INSERT INTO pessoa (NOME, EMAIL, CPF, ENDERECO, CEP, TELEFONE, CIDADE, INDICADORATIVO) "+
                "VALUES ('"+pessoa.getNome()+"', '"+pessoa.getEmail() +"', '"+ pessoa.getCpf()+"','"+pessoa.getEndereco()+"','"+pessoa.getCep()+"','"+pessoa.getTelefone()+"','"+pessoa.getCidade()+"', 1)" +
                "RETURNING Codigo";

        try {
            ResultSet linhasafetadas = s.executeQuery(SQLPessoa);

            if(linhasafetadas.next()){
                int codigoPessoa = linhasafetadas.getInt("Codigo");

                String SQLUsuario =
                        "INSERT INTO Usuario " +
                                "(CodigoPessoa, Login, Senha, IndicadorAtivo) " +
                                "VALUES (" + codigoPessoa + ", '" + usuario.getLogin() + "', '" + usuario.getSenha() + "', 1)";

                s.executeUpdate(SQLUsuario);

                return "Usuário cadastrado com sucesso!";
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Não foi possível cadastrar o usuário.";
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

    //Login

    public boolean VerificarLogin (String tabela, Usuario usuario) {
        String SQL = "SELECT * FROM USUARIO WHERE LOGIN = '" + usuario.getLogin() + "' AND SENHA = '" + usuario.getSenha() + "'"+ " AND INDICADORATIVO = 1 ";

        try {
            ResultSet linhasafetadasLogin = s.executeQuery(SQL);
            return linhasafetadasLogin.next();


        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
    }

    //Usuario Master

    public boolean Usuariomaster (Usuario usuario) {
        String SQL = "SELECT * FROM USUARIO WHERE LOGIN = '" + usuario.getLogin() + "' AND SENHA = '" + usuario.getSenha() + "'" + "RETURNING IndicadorMaster";

        try {
            ResultSet linhasafetadas = s.executeQuery(SQL);

            if (linhasafetadas.next()) {
                int IndicadorMaster = linhasafetadas.getInt("IndicadorMaster");

                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
