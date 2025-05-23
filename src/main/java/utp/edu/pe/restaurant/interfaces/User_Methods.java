package utp.edu.pe.restaurant.interfaces;

import utp.edu.pe.restaurant.model.User;

import java.sql.SQLException;
import java.util.List;

public interface User_Methods {
    void registerUser(User user) throws SQLException;
    List<User> getAllUsers() throws SQLException;
    void updateUser(User user, long user_id) throws SQLException;
    void deleteUser(long user_id) throws SQLException;
    User getUserByEmail(String email) throws SQLException;
    User getUserById(long user_id) throws SQLException;
}
