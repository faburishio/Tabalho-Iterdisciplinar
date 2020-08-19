package util;

import java.sql.Connection;
import java.sql.SQLException;
import com.mysql.cj.jdbc.MysqlDataSource;

public class ConectaBanco {

    private Connection conexao = null;
    private final String BANCO = "dbteste";
    private final String LOGIN = "root";
    private final String SENHA = "";
    private final String HOST = "localhost";

    public Connection getConnection() {
        try {
            MysqlDataSource ds = new MysqlDataSource();
            ds.setServerName(HOST);
            ds.setDatabaseName(BANCO);
            ds.setUser(LOGIN);
            ds.setPassword(SENHA);
            ds.setConnectTimeout(8000);
            ds.setServerTimezone("UTC");

            conexao = ds.getConnection();
            System.out.println("CONECTADO AO BANCO DE DADOS.");
        } catch (SQLException sqlerror) {
            System.out.println("FALHA NA CONEXÃO: " + sqlerror.getMessage());
        }

        return conexao;
    }

}