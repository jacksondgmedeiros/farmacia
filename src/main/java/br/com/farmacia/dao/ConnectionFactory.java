package br.com.farmacia.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {
    //    conexão usada para o ORACLE
    private static final String URL = "jdbc:oracle:thin:@localhost:1521/XE";
    private static final String USER = "system";
    private static final String PASS = "12345";

    public Connection recuperarConexao() {
        try {
            return createDataSource().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Contract(" -> new")
    private @NotNull HikariDataSource createDataSource(){
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(URL);
        config.setUsername(USER);
        config.setPassword(PASS);
        config.setMaximumPoolSize(5);

        return new HikariDataSource(config);
    }
}
