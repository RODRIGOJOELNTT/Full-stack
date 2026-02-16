package com.example.rechargesbatch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.AbstractDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * DataSource "dummy" (NO conecta a nada).
 * Algunas autoconfiguraciones esperan un bean llamado 'dataSource'.
 *
 * Importante:
 * - No hay BD.
 * - No se debe usar para JDBC. Si alguien intenta usarlo, falla explícitamente.
 */
@Configuration
public class DummyDataSourceConfig {

    @Bean
    public DataSource dataSource() {
        return new AbstractDataSource() {
            @Override
            public Connection getConnection() throws SQLException {
                throw new SQLException("Dummy DataSource: no hay BD configurada.");
            }

            @Override
            public Connection getConnection(String username, String password) throws SQLException {
                throw new SQLException("Dummy DataSource: no hay BD configurada.");
            }
        };
    }
}
