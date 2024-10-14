package utp.edu.pe.restaurant.dao;

import utp.edu.pe.restaurant.interfaces.Extra_Methods;
import utp.edu.pe.restaurant.model.Extra;
import utp.edu.pe.restaurant.util.AppConfig;
import utp.edu.pe.restaurant.util.DataAccessMariaDB;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExtraDao implements Extra_Methods {
    @Override
    public void registerExtra(Extra extra) throws SQLException {
        String query = "INSERT INTO extra (name, price) VALUES (?, ?)";
        try (Connection cnn = DataAccessMariaDB.getConnection(DataAccessMariaDB.TipoDA.valueOf(AppConfig.getSourceType()), AppConfig.getDatasource());
             CallableStatement cs = cnn.prepareCall(query)) {
            cs.setString(1, extra.getName());
            cs.setDouble(2, extra.getPrice());
            int rowsAffected = cs.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No se pudo insertar el extra en la base de datos.");
            }
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Extra> getAllExtras() throws SQLException {
        List<Extra> extras = new ArrayList<>();
        String query = "SELECT * FROM extra";
        try ( Connection cnn = DataAccessMariaDB.getConnection(DataAccessMariaDB.TipoDA.valueOf(AppConfig.getSourceType()), AppConfig.getDatasource());
              CallableStatement cs = cnn.prepareCall(query);
              ResultSet rs = cs.executeQuery()) {
            while (rs.next()) {
                extras.add(Extra.createExtra(
                        rs.getLong("extra_id"),
                        rs.getString("name"),
                        rs.getDouble("price")
                ));
            }
            if (extras.isEmpty()) {
                throw new SQLException("No se encontraron extras en la base de datos.");
            }
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
        return extras;
    }

    @Override
    public Extra getExtraById(long extra_id) throws SQLException {
        String query = "SELECT * FROM extra WHERE extra_id = ?";
        Extra extra = null;
        try ( Connection cnn = DataAccessMariaDB.getConnection(DataAccessMariaDB.TipoDA.valueOf(AppConfig.getSourceType()), AppConfig.getDatasource());
                CallableStatement cs = cnn.prepareCall(query)) {
            cs.setLong(1, extra_id);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    extra = Extra.createExtra(
                            rs.getLong("extra_id"),
                            rs.getString("name"),
                            rs.getDouble("price")
                    );
                } else {
                    throw new SQLException(String.format("No se encontró un extra con el ID %d en la base de datos.", extra_id));
                }
            }
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
        return extra;
    }

    @Override
    public void updateExtra(Extra extra, long extra_id) throws SQLException {
        String query = "UPDATE extra SET name = ?, price = ? WHERE extra_id = ?";
        try (Connection cnn = DataAccessMariaDB.getConnection(DataAccessMariaDB.TipoDA.valueOf(AppConfig.getSourceType()), AppConfig.getDatasource());
                CallableStatement cs = cnn.prepareCall(query)) {
            cs.setString(1, extra.getName());
            cs.setDouble(2, extra.getPrice());
            cs.setLong(3, extra_id);
            cs.executeUpdate();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteExtra(long extra_id) throws SQLException {
        String query = "DELETE FROM extra WHERE extra_id = ?";
        try (Connection cnn = DataAccessMariaDB.getConnection(DataAccessMariaDB.TipoDA.valueOf(AppConfig.getSourceType()), AppConfig.getDatasource());
                CallableStatement cs = cnn.prepareCall(query)) {
            cs.setLong(1, extra_id);
            cs.executeUpdate();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getExtraNameById(long extra_id) throws SQLException {
        String query = "SELECT name FROM extra WHERE extra_id = ?";
        try (Connection cnn = DataAccessMariaDB.getConnection(DataAccessMariaDB.TipoDA.valueOf(AppConfig.getSourceType()), AppConfig.getDatasource());
             PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setLong(1, extra_id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("name");
                } else {
                    throw new SQLException(String.format("No se encontró un extra con el ID %d en la base de datos.", extra_id));
                }
            }
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
}
