package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

    //SERVIÇO

    public ArrayList<Agenda> MostrarAgendamentosPorUsuario(Usuario usuario){
        String SQL = "SELECT * FROM AGENDA A "+
        "JOIN SERVICOAGENDA B ON A.CODIGO = B.CODIGOAGENDA "+
        "JOIN SERVICO C ON B.CODIGOSERVICO = C.CODIGO "+
        "JOIN PESSOA D ON D.CODIGO = A.CODIGOPESSOA "+
                "WHERE A.INDICADORATIVO = 1 "+
                "AND D.LOGIN = " + usuario.getLogin()+
                " AND D.SENHA = " + usuario.getSenha() +
                "ORDER BY DATAHORAINICIO";

        ArrayList<Agenda> agenda = new ArrayList<>();
        try {
            ResultSet rset = s.executeQuery(SQL);

            while (rset.next()){
                Agenda a = new Agenda();
                a.getDatahorafinal(rset.getString("DATAHORAINICIO"));
                a.getDatahorainicio(rset.getString("DATAHORAFINAL"));

                return  agenda;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null; //deu errado!


    }


}
