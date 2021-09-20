package com.agustinbravop.clinica_odontologica.dao.config;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfiguracionJDBC {
    private final String jdbcDriver;
    private final String dbUrl;
    private final String user;
    private final String password;

    public ConfiguracionJDBC() {
        this.jdbcDriver = "org.h2.Driver";
        this.dbUrl = "jdbc:h2:~/ctd_back1_clinica_odontologica;INIT=RUNSCRIPT FROM 'create.sql'";
        this.user = "sa";
        this.password = null;
    }

    public ConfiguracionJDBC(String jdbcDriver, String dbUrl, String user, String password) {
        this.jdbcDriver = jdbcDriver;
        this.dbUrl = dbUrl;
        this.user = user;
        this.password = password;
    }

    public Connection conectarConDB() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbUrl, user, password);
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(ConfiguracionJDBC.class);
            logger.debug(e);
        }

        return connection;
    }
}
