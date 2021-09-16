package com.agustinbravop.clinica_odontologica.dao.impl;

import com.agustinbravop.clinica_odontologica.dao.IDao;
import com.agustinbravop.clinica_odontologica.dao.config.ConfiguracionJDBC;
import com.agustinbravop.clinica_odontologica.model.Paciente;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

public class PacienteDaoH2 implements IDao<Paciente> {

    private final ConfiguracionJDBC configuracionJDBC;
    private final DomicilioDaoH2 domicilioDaoH2;
    final static Logger logger = Logger.getLogger(PacienteDaoH2.class);

    public PacienteDaoH2(ConfiguracionJDBC configuracionJDBC, DomicilioDaoH2 domicilioDaoH2) {
        this.configuracionJDBC = configuracionJDBC;
        this.domicilioDaoH2 = domicilioDaoH2;
    }

    @Override
    public Paciente guardar(Paciente paciente) {
        paciente.setDomicilio( domicilioDaoH2.guardar(paciente.getDomicilio()) );
        String query = String.format(
                "INSERT INTO pacientes(nombre,apellido,dni,fecha_ingreso,domicilio_id) VALUES('%s','%s','%s','%s','%s')",
                paciente.getNombre(),
                paciente.getApellido(),
                paciente.getDni(),
                new SimpleDateFormat().format(paciente.getFechaIngreso()),
                paciente.getDomicilio().getId()
        );
        Connection conn = configuracionJDBC.conectarConDB();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next())
                paciente.setId(rs.getLong(1));

            rs.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException throwables) {
            logger.debug("SQLException: ", throwables);
        }
        return paciente;
    }

    @Override
    public Optional<Paciente> buscar(Integer id) {
        String query = String.format("SELECT id,nombre,apellido,dni,fecha_ingreso,domicilio_id FROM pacientes where id = '%s'", id);
        Paciente paciente = null;
        Connection conn = configuracionJDBC.conectarConDB();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {

                paciente = getPacienteFromResultSet(rs);
            }
            rs.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException throwables) {
            logger.debug("SQLException: ", throwables);
        }

        return Optional.ofNullable(paciente);
    }



    @Override
    public List<Paciente> buscarTodos() {
        String query = "SELECT *  FROM pacientes";
        List<Paciente> pacientes = new ArrayList<>();
        Connection conn = configuracionJDBC.conectarConDB();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {

                pacientes.add(getPacienteFromResultSet(rs));

            }
            rs.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException throwables) {
            logger.debug("SQLException: ", throwables);
        }

        return pacientes;
    }

    @Override
    public Paciente actualizar(Paciente paciente) {
        if (paciente.getDomicilio() != null && paciente.getId() != null)
            domicilioDaoH2.actualizar(paciente.getDomicilio());

        String query = String.format(
                "UPDATE pacientes SET nombre = '%s', apellido = '%s',dni = '%s' WHERE id = '%s'",
                paciente.getNombre(),
                paciente.getApellido(),
                paciente.getDni(),
                paciente.getId()
        );
        ejecutarQuery(query);
        return paciente;
    }

    @Override
    public void eliminar(Integer id) {
        String query = String.format("DELETE FROM pacientes where id = %s", id);
        ejecutarQuery(query);
    }

    private Paciente getPacienteFromResultSet(ResultSet rs) throws SQLException {
        return new Paciente(rs.getLong("id"),
                rs.getString("nombre"),
                rs.getString("apellido"),
                rs.getString("dni"),
                rs.getDate("fecha_ingreso"),
                domicilioDaoH2.buscar(rs.getInt("domicilio_id")).orElse(null)
        );
    }

    private void ejecutarQuery(String query) {
        try {
            Connection conn = configuracionJDBC.conectarConDB();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
            stmt.close();
            conn.close();
        }
        catch (SQLException throwables) {
            logger.debug("SQLException: ", throwables);
        }
    }
}
