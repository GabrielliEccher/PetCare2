package model.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Conexao {
    private Statement s;

    public Statement getS() {
        return s;
    }

    public void conectaBD(String database){

        String URL = "jdbc:postgresql://localhost:5432/"+database;
        String usuario = "postgres";
        String senha = "unifebe";
        String JDBC = "org.postgresql.Driver";
        try{
            /*Conectando ao Servidor de Banco de Dados*/
            Class.forName(JDBC);
            Connection Conn = DriverManager.getConnection(URL,usuario,senha);//cria conex�o com o servidor
            this.s = Conn.createStatement();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
