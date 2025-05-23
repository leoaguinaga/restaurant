package utp.edu.pe.restaurant.service;

import utp.edu.pe.restaurant.dao.UserDao;
import utp.edu.pe.restaurant.model.User;
import utp.edu.pe.restaurant.model.enums.User_Type;
import utp.edu.pe.restaurant.util.AppConfig;
import utp.edu.pe.restaurant.util.DataAccessMariaDB;
import utp.edu.pe.restaurant.util.ErrorLog;

import javax.naming.NamingException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Auth {
    public void close() throws SQLException {
    }

    public static String md5(String data) throws IOException {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(data.getBytes());
            return byteArrayToHex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new IOException(e);
        }
    }

    public static String byteArrayToHex(byte[] a) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        for (byte b : a) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public User isValidUser(String email, String password) throws SQLException, IOException {
        String query = "SELECT * FROM user WHERE email = ? AND pwd = ?";
        User user = null;
        try (Connection cnn = DataAccessMariaDB.getConnection(DataAccessMariaDB.TipoDA.valueOf(AppConfig.getSourceType()), AppConfig.getDatasource());
             PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setString(1, email);
            ps.setString(2, md5(password));
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
}
