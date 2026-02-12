package com.crud.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class H2Conexao implements IConexao {
    private final String url;
    private final String user;
    private final String pass;

    public H2Conexao() {
        this.url = "jdbc:h2:mem:meubanco;DB_CLOSE_DELAY=-1"; // memória H2
        this.user = "sa";
        this.pass = "";
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }
}
