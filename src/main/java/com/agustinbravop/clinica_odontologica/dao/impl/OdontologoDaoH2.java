package com.agustinbravop.clinica_odontologica.dao.impl;

import com.agustinbravop.clinica_odontologica.dao.IDao;
import com.agustinbravop.clinica_odontologica.dao.config.ConfiguracionJDBC;
import com.agustinbravop.clinica_odontologica.model.Odontologo;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OdontologoDaoH2 implements IDao<Odontologo> {
    private final ConfiguracionJDBC configuracionJDBC;
    final static Logger logger = Logger.getLogger(OdontologoDaoH2.class);

    public OdontologoDaoH2(ConfiguracionJDBC configuracionJDBC) {
        this.configuracionJDBC = configuracionJDBC;
    }

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        String query = String.format(
                "INSERT INTO odontologos(nombre,apellido,matricula) VALUES('%s','%s','%s')",
                odontologo.getNombre(),
                odontologo.getApellido(),
                odontologo.getMatricula()
        );
        Connection conn = configuracionJDBC.conectarConDB();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next())
                odontologo.setId(rs.getLong(1));

            rs.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException throwables) {
            logger.debug("SQLException: ", throwables);
        }

        return odontologo;
    }

    @Override
    public Optional<Odontologo> buscar(Integer id) {
        String query = String.format("SELECT id,nombre,apellido,matricula FROM odontologos where id = '%s'", id);
        Odontologo odontologo = null;
        Connection conn = configuracionJDBC.conectarConDB();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                odontologo = getOdontologoFromResultSet(rs);
            }
            rs.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException throwables) {
            logger.debug("SQLException: ", throwables);
        }
        return Optional.ofNullable(odontologo);
    }

    @Override
    public List<Odontologo> buscarTodos() {
        Connection connection = configuracionJDBC.conectarConDB();
        String query = "SELECT *  FROM odontologos";
        List<Odontologo> odontologos = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                odontologos.add(getOdontologoFromResultSet(rs));
            }
            rs.close();
            stmt.close();
            connection.close();
        }
        catch (SQLException throwables) {
            logger.debug("SQLException: ", throwables);
        }

        return odontologos;
    }

    @Override
    public Odontologo actualizar(Odontologo odontologo) {
        String query = String.format(
                "UPDATE odontologos SET nombre = '%s', apellido = '%s',matricula = '%s'  WHERE id = '%s'",
                odontologo.getNombre(),
                odontologo.getApellido(),
                odontologo.getMatricula(),
                odontologo.getId()
        );
        ejecutarQuery(query);
        return odontologo;
    }

    @Override
    public void eliminar(Integer id) {
        String query = String.format("DELETE FROM odontologos where id = %s", id);
        ejecutarQuery(query);
    }

    private Odontologo getOdontologoFromResultSet(ResultSet rs) throws SQLException {
        return new Odontologo(
                rs.getLong("id"),
                rs.getString("nombre"),
                rs.getString("apellido"),
                rs.getInt("matricula")
        );
    }

    private void ejecutarQuery(String query) {
        try {
            Connection conn = configuracionJDBC.conectarConDB();
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
            statement.close();
            conn.close();
        }
        catch (SQLException throwables) {
            logger.debug("SQLException: ", throwables);
        }
    }
}
