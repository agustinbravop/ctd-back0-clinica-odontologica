package com.agustinbravop.clinica_odontologica.dao.impl;

import com.agustinbravop.clinica_odontologica.dao.IDao;
import com.agustinbravop.clinica_odontologica.dao.config.ConfiguracionJDBC;
import com.agustinbravop.clinica_odontologica.model.Domicilio;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class DomicilioDaoH2 implements IDao<Domicilio> {
    private final ConfiguracionJDBC configuracionJDBC;

    public DomicilioDaoH2(ConfiguracionJDBC configuracionJDBC) {
        this.configuracionJDBC = configuracionJDBC;
    }

    @Override
    public Domicilio guardar(Domicilio domicilio) {
        Connection conn = configuracionJDBC.conectarConDB();
        String query = String.format(
                "INSERT INTO domicilios(calle,numero,localidad,provincia) VALUES('%s','%s','%s','%s')",
                domicilio.getCalle(),
                domicilio.getNumero(),
                domicilio.getLocalidad(),
                domicilio.getProvincia()
        );
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();

            if(rs.next())
                domicilio.setId(rs.getInt(1));

            rs.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return domicilio;
    }

    @Override
    public Optional<Domicilio> buscar(Integer id) {
        Connection conn = configuracionJDBC.conectarConDB();
        String query = String.format("SELECT id,calle,numero,localidad,provincia FROM domicilios where id = '%s'", id);
        Domicilio domicilio = null;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                domicilio = getDomicilioFromResultSet(rs);
            }
            rs.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.ofNullable(domicilio);
    }

    @Override
    public void eliminar(Integer id) {
        String query = String.format("DELETE FROM domicilios where id = %s", id);
        ejecutarQuery(query);
    }

    @Override
    public Collection<Domicilio> buscarTodos() {
        String query = "SELECT * FROM domicilios";
        List<Domicilio> domicilios = new ArrayList<>();
        Connection conn = configuracionJDBC.conectarConDB();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                domicilios.add(getDomicilioFromResultSet(rs));
            }

            rs.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return domicilios;
    }

    @Override
    public Domicilio actualizar(Domicilio domicilio) {
        String query = String.format(
                "UPDATE domicilios SET calle = '%s', numero = '%s',localidad = '%s',provincia = '%s' WHERE id = '%s'",
                domicilio.getCalle(),
                domicilio.getNumero(),
                domicilio.getLocalidad(),
                domicilio.getProvincia(),
                domicilio.getId()
        );
        ejecutarQuery(query);
        return domicilio;
    }

    private Domicilio getDomicilioFromResultSet(ResultSet result) throws SQLException {
        Integer id = result.getInt("id");
        String calle = result.getString("calle");
        String numero = result.getString("numero");
        String localidad = result.getString("localidad");
        String provincia = result.getString("provincia");
        return new Domicilio(id, calle, numero, localidad, provincia);
    }

    private void ejecutarQuery(String query) {
        try {
            Connection conn = configuracionJDBC.conectarConDB();
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
            statement.close();
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
