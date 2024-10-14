package utp.edu.pe.restaurant.interfaces;

import utp.edu.pe.restaurant.model.Order;
import utp.edu.pe.restaurant.model.User;
import utp.edu.pe.restaurant.model.enums.State;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.Stack;

public interface Order_Methods {
    void registerOrder(Order order) throws SQLException;
    Stack<Order> getAllOrders() throws SQLException, NamingException;
    Order getOrderById(long order_id) throws SQLException, NamingException;
    void updateOrder(Order order, long order_id) throws SQLException;
    void deleteOrder(long order) throws SQLException;
    Order getLastOrder() throws SQLException, NamingException;
    Order getLastOrderByUser(User user) throws SQLException, NamingException;
    Stack<Order> getOrdersByState(State state) throws SQLException, NamingException;
}
