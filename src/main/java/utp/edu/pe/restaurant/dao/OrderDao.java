package utp.edu.pe.restaurant.dao;

import utp.edu.pe.restaurant.interfaces.Order_Methods;
import utp.edu.pe.restaurant.model.Order;
import utp.edu.pe.restaurant.model.User;
import utp.edu.pe.restaurant.model.enums.State;
import utp.edu.pe.restaurant.util.AppConfig;
import utp.edu.pe.restaurant.util.DataAccessMariaDB;

import javax.naming.NamingException;
import java.sql.*;
import java.util.Stack;

public class OrderDao implements Order_Methods {
    @Override
    public void registerOrder(Order order) throws SQLException {
        String query = "INSERT INTO order (user_id, date, address, amount, state, evidence) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection cnn = DataAccessMariaDB.getConnection(DataAccessMariaDB.TipoDA.valueOf(AppConfig.getSourceType()), AppConfig.getDatasource());
             CallableStatement cs = cnn.prepareCall(query)) {

            long user_id = (order.getUser() != null) ? order.getUser().getUser_id() : order.getUser_id();
            cs.setLong(1, user_id);
            cs.setTimestamp(2, Timestamp.valueOf(order.getOrder_date()));
            cs.setString(3, order.getAddress());
            cs.setDouble(4, order.getAmount());
            cs.setString(5, String.valueOf(order.getState()));
            cs.setString(6, order.getEvidence_image());
            cs.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al registrar la orden", e);
        } catch (NamingException e) {
            throw new RuntimeException("Error al establecer conexión para registrar la orden", e);
        }
    }

    @Override
    public Stack<Order> getAllOrders() throws SQLException, NamingException {
        Stack<Order> orders = new Stack<>();
        String query = "SELECT * FROM order";
        try (Connection cnn = DataAccessMariaDB.getConnection(DataAccessMariaDB.TipoDA.valueOf(AppConfig.getSourceType()), AppConfig.getDatasource());
             CallableStatement cs = cnn.prepareCall(query);
             ResultSet rs = cs.executeQuery()) {
            while (rs.next()) {
                orders.push(
                        Order.createOrder(
                                rs.getLong("order_id"),
                                rs.getLong("user_id"),
                                rs.getTimestamp("date").toLocalDateTime(),
                                rs.getString("address"),
                                rs.getDouble("amount"),
                                State.valueOf(rs.getString("state")),
                                rs.getString("evidence")));

            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener todas las órdenes", e);
        }
        return orders;
    }

    @Override
    public Order getOrderById(long order_id) throws SQLException, NamingException {
        String query = "SELECT * FROM order WHERE order_id = ?";
        Order order = null;
        try (Connection cnn = DataAccessMariaDB.getConnection(DataAccessMariaDB.TipoDA.valueOf(AppConfig.getSourceType()), AppConfig.getDatasource());
             PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setLong(1, order_id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    order = Order.createOrder(
                            rs.getLong("order_id"),
                            rs.getLong("user_id"),
                            rs.getTimestamp("date").toLocalDateTime(),
                            rs.getString("address"),
                            rs.getDouble("amount"),
                            State.valueOf(rs.getString("state")),
                            rs.getString("evidence"));
                } else {
                    throw new SQLException(String.format("No se encontró una orden con el ID %d en la base de datos.", order_id));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener la orden por ID", e);
        }
        return order;
    }

    @Override
    public void updateOrder(Order order, long order_id) throws SQLException {
        String query = "UPDATE order SET user_id = ?, date = ?, address = ?, amount = ?, state = ?, evidence = ? WHERE order_id = ?";
        try (Connection cnn = DataAccessMariaDB.getConnection(DataAccessMariaDB.TipoDA.valueOf(AppConfig.getSourceType()), AppConfig.getDatasource());
             PreparedStatement ps = cnn.prepareStatement(query)) {

            long user_id = (order.getUser() != null) ? order.getUser().getUser_id() : order.getUser_id();
            ps.setLong(1, user_id);
            ps.setTimestamp(2, Timestamp.valueOf(order.getOrder_date()));
            ps.setString(3, order.getAddress());
            ps.setDouble(4, order.getAmount());
            ps.setString(5, String.valueOf(order.getState()));
            ps.setString(6, order.getEvidence_image());
            ps.setLong(7, order_id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar la orden: " + e.getMessage());
            throw new RuntimeException("Error al actualizar la orden", e);
        } catch (NamingException e) {
            throw new RuntimeException("Error al establecer conexión para actualizar la orden", e);
        }
    }

    @Override
    public void deleteOrder(long order) throws SQLException {
        String query = "DELETE FROM order WHERE order_id = ?";
        try (Connection cnn = DataAccessMariaDB.getConnection(DataAccessMariaDB.TipoDA.valueOf(AppConfig.getSourceType()), AppConfig.getDatasource());
             PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setLong(1, order);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar la orden", e);
        } catch (NamingException e) {
            throw new RuntimeException("Error al establecer conexión para eliminar la orden", e);
        }
    }

    @Override
    public Order getLastOrder() throws SQLException, NamingException {
        String query = "SELECT * FROM order ORDER BY date DESC LIMIT 1";
        Order order = null;
        try (Connection cnn = DataAccessMariaDB.getConnection(DataAccessMariaDB.TipoDA.valueOf(AppConfig.getSourceType()), AppConfig.getDatasource());
             PreparedStatement ps = cnn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                order = Order.createOrder(
                        rs.getLong("order_id"),
                        rs.getLong("user_id"),
                        rs.getTimestamp("date").toLocalDateTime(),
                        rs.getString("address"),
                        rs.getDouble("amount"),
                        State.valueOf(rs.getString("state")),
                        rs.getString("evidence"));
            } else {
                throw new SQLException("No se encontró el último pedido en la base de datos.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener el último pedido", e);
        }
        return order;
    }

    @Override
    public Order getLastOrderByUser(User user) throws SQLException, NamingException {
        String query = "SELECT * FROM order WHERE user_id = ? ORDER BY date DESC LIMIT 1";
        Order order = null;
        try (Connection cnn = DataAccessMariaDB.getConnection(DataAccessMariaDB.TipoDA.valueOf(AppConfig.getSourceType()), AppConfig.getDatasource());
             PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setLong(1, user.getUser_id());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    order = Order.createOrder(
                            rs.getLong("order_id"),
                            rs.getLong("user_id"),
                            rs.getTimestamp("date").toLocalDateTime(),
                            rs.getString("address"),
                            rs.getDouble("amount"),
                            State.valueOf(rs.getString("state")),
                            rs.getString("evidence"));
                } else {
                    throw new SQLException("No se encontró el último pedido para el cliente con ID: " + user.getUser_id());
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener el último pedido por usuario", e);
        }
        return order;
    }

    @Override
    public Stack<Order> getOrdersByState(State state) throws SQLException, NamingException {
        Stack<Order> orders = new Stack<>();
        String query = "SELECT * FROM order WHERE state = ?";
        try (Connection cnn = DataAccessMariaDB.getConnection(DataAccessMariaDB.TipoDA.valueOf(AppConfig.getSourceType()), AppConfig.getDatasource());
             PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setString(1, String.valueOf(state));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    orders.push(
                            Order.createOrder(
                                    rs.getLong("order_id"),
                                    rs.getLong("user_id"),
                                    rs.getTimestamp("date").toLocalDateTime(),
                                    rs.getString("address"),
                                    rs.getDouble("amount"),
                                    State.valueOf(rs.getString("state")),
                                    rs.getString("evidence")));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener las órdenes por estado", e);
        }
        return orders;
    }
}
