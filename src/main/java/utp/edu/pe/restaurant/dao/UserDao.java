package utp.edu.pe.restaurant.dao;

import utp.edu.pe.restaurant.interfaces.User_Methods;
import utp.edu.pe.restaurant.model.User;
import utp.edu.pe.restaurant.model.enums.User_Type;
import utp.edu.pe.restaurant.util.AppConfig;
import utp.edu.pe.restaurant.util.DataAccessMariaDB;

import javax.naming.NamingException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements User_Methods {

        @Override
        public User getUserByEmail(String email) throws SQLException {
            String query = "SELECT * FROM user WHERE email = ?";
            User user = null;
            try (Connection cnn = DataAccessMariaDB.getConnection(DataAccessMariaDB.TipoDA.valueOf(AppConfig.getSourceType()), AppConfig.getDatasource());
                    PreparedStatement ps = cnn.prepareStatement(query)) {
                ps.setString(1, email);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        user = User.createUser(
                                rs.getLong("user_id"),
                                rs.getString("full_name"),
                                rs.getString("email"),
                                rs.getString("phone_number"),
                                rs.getString("pwd"),
                                User_Type.valueOf(rs.getString("type"))
                        );
                    } else {
                        throw new SQLException("No se pudo encontrar el usuario en la base de datos.");
                    }
                }
            } catch (NamingException e) {
                throw new RuntimeException(e);
            }
            return user;
        }

        @Override
        public void registerUser(User user) throws SQLException {
            String query = "INSERT INTO user (full_name, email, phone_number, pwd, type) VALUES (?, ?, ?, ?, ?)";
            try (Connection cnn = DataAccessMariaDB.getConnection(DataAccessMariaDB.TipoDA.valueOf(AppConfig.getSourceType()), AppConfig.getDatasource());
                    PreparedStatement ps = cnn.prepareStatement(query)) {
                ps.setString(1, user.getFull_name());
                ps.setString(2, user.getEmail());
                ps.setString(3, user.getPhone_number());
                ps.setString(4, user.getPwd());
                ps.setString(5, user.getUser_type().toString());
                ps.execute();
            } catch (SQLException e) {
                throw new SQLException("No se pudo registrar el usuario en la base de datos. "+ e.getMessage());
            } catch (NamingException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public List<User> getAllUsers() throws SQLException {
            List<User> users = new ArrayList<>();
            String query = "SELECT * FROM user";
            try (Connection cnn = DataAccessMariaDB.getConnection(DataAccessMariaDB.TipoDA.valueOf(AppConfig.getSourceType()), AppConfig.getDatasource());
                    PreparedStatement ps = cnn.prepareStatement(query);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    users.add(User.createUser(
                            rs.getLong("user_id"),
                            rs.getString("full_name"),
                            rs.getString("email"),
                            rs.getString("phone_number"),
                            rs.getString("pwd"),
                            User_Type.valueOf(rs.getString("type"))
                    ));
                }
                if (users.isEmpty()) {
                    throw new SQLException("No se encontraron usuarios en la base de datos.");
                }
            } catch (NamingException e) {
                throw new RuntimeException(e);
            }
            return users;
        }

        @Override
        public User getUserById(long client_id) throws SQLException {
            String query = "SELECT * FROM user WHERE client_id = ?";
            User user = null;
            try ( Connection cnn = DataAccessMariaDB.getConnection(DataAccessMariaDB.TipoDA.valueOf(AppConfig.getSourceType()), AppConfig.getDatasource());
                    PreparedStatement ps = cnn.prepareStatement(query)) {
                ps.setLong(1, client_id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        user = User.createUser(
                                rs.getLong("user_id"),
                                rs.getString("full_name"),
                                rs.getString("email"),
                                rs.getString("phone_number"),
                                rs.getString("pwd"),
                                User_Type.valueOf(rs.getString("type"))
                        );
                    } else {
                        throw new SQLException(String.format("No se encontró un usuario con el ID %d en la base de datos.", client_id));
                    }
                }
            } catch (NamingException e) {
                throw new RuntimeException(e);
            }
            return user;
        }

        @Override
        public void updateUser(User user, long user_id) throws SQLException {
            String query = "UPDATE user SET full_name = ?, email = ?, phone_number = ?, pwd = ? WHERE user_id";
            try ( Connection cnn = DataAccessMariaDB.getConnection(DataAccessMariaDB.TipoDA.valueOf(AppConfig.getSourceType()), AppConfig.getDatasource());
                    PreparedStatement ps = cnn.prepareStatement(query)) {
                ps.setString(1, user.getFull_name());
                ps.setString(2, user.getEmail());
                ps.setString(3, user.getPhone_number());
                ps.setString(4, user.getPwd());
                ps.setLong(5, user_id);
                ps.execute();
            } catch (NamingException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void deleteUser(long user_id) throws SQLException {
            String query = "DELETE FROM user WHERE user_id = ?";
            try ( Connection cnn = DataAccessMariaDB.getConnection(DataAccessMariaDB.TipoDA.valueOf(AppConfig.getSourceType()), AppConfig.getDatasource());
                    PreparedStatement ps = cnn.prepareStatement(query)) {
                ps.setLong(1, user_id);
                ps.execute();
            } catch (NamingException e) {
                throw new RuntimeException(e);
            }
        }


    public List<User> getUsersByType(User_Type userType) throws SQLException {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM user WHERE type = ?";
        try (Connection cnn = DataAccessMariaDB.getConnection(
                DataAccessMariaDB.TipoDA.valueOf(AppConfig.getSourceType()),
                AppConfig.getDatasource());
             PreparedStatement ps = cnn.prepareStatement(query)) {

            ps.setString(1, userType.toString()); // Establece el tipo de usuario en la consulta

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    users.add(User.createUser(
                            rs.getLong("user_id"),
                            rs.getString("full_name"),
                            rs.getString("email"),
                            rs.getString("phone_number"),
                            rs.getString("pwd"),
                            User_Type.valueOf(rs.getString("type"))
                    ));
                }
                if (users.isEmpty()) {
                    throw new SQLException("No se encontraron usuarios de tipo " + userType + " en la base de datos.");
                }
            }
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public List<User> getWorkers() throws SQLException {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM user WHERE type != ?";
        try (Connection cnn = DataAccessMariaDB.getConnection(
                DataAccessMariaDB.TipoDA.valueOf(AppConfig.getSourceType()),
                AppConfig.getDatasource());
             PreparedStatement ps = cnn.prepareStatement(query)) {

            ps.setString(1, User_Type.client.toString());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    users.add(User.createUser(
                            rs.getLong("user_id"),
                            rs.getString("full_name"),
                            rs.getString("email"),
                            rs.getString("phone_number"),
                            rs.getString("pwd"),
                            User_Type.valueOf(rs.getString("type"))
                    ));
                }
                if (users.isEmpty()) {
                    throw new SQLException("No se encontraron usuarios que no sean de tipo CLIENT en la base de datos.");
                }
            }
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

}
