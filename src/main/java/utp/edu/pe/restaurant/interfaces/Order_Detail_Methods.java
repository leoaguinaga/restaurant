package utp.edu.pe.restaurant.interfaces;

import utp.edu.pe.restaurant.model.Order_Detail;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public interface Order_Detail_Methods {
    public void registerOrder_detail(Order_Detail orderDetail) throws SQLException, NamingException;
    List<Order_Detail> getOrderDetailsByOrderId(long order_id) throws SQLException, NamingException;
}