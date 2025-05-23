package utp.edu.pe.restaurant.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import utp.edu.pe.restaurant.interfaces.Order_Detail_Methods;
import utp.edu.pe.restaurant.model.Extra;
import utp.edu.pe.restaurant.model.Order_Detail;
import utp.edu.pe.restaurant.util.AppConfig;
import utp.edu.pe.restaurant.util.DataAccessMariaDB;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Order_DetailDao implements Order_Detail_Methods {

    @Override
    public void registerOrder_detail(Order_Detail orderDetail) throws SQLException, NamingException {
        String query = "INSERT INTO order_detail (order_id, product_id, quantity, extras) VALUES (?, ?, ?, ?)";
        try (Connection cnn = DataAccessMariaDB.getConnection(DataAccessMariaDB.TipoDA.valueOf(AppConfig.getSourceType()), AppConfig.getDatasource());
             PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setLong(1, orderDetail.getOrder_id());
            ps.setLong(2, orderDetail.getProduct().getProduct_id());
            ps.setInt(3, orderDetail.getQuantity());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al registrar el detalle de la orden", e);
        }
    }

    @Override
    public List<Order_Detail> getOrderDetailsByOrderId(long order_id) throws SQLException, NamingException {
        String query = "SELECT * FROM order_detail WHERE order_id = ?";
        List<Order_Detail> order_details = new ArrayList<>();
        try (Connection cnn = DataAccessMariaDB.getConnection(DataAccessMariaDB.TipoDA.DATASOURCE, AppConfig.getDatasource());
             PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setLong(1, order_id);
            try (ResultSet rs = ps.executeQuery()) {
                ProductDao productDAO = new ProductDao();
                Gson gson = new Gson();
                while (rs.next()) {
                    String extrasJson = rs.getString("extras");
                    List<Extra> extras = gson.fromJson(extrasJson, new TypeToken<List<Extra>>(){}.getType());
                    order_details.add(Order_Detail.createOrderDetail(
                            (rs.getLong("order_id")),
                            productDAO.getProductById(rs.getLong("product_id")),
                            rs.getInt("quantity")
                    ));
                }
                if (order_details.isEmpty()) {
                    throw new SQLException("No se encontraron los detalles del pedido en la base de datos.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener los detalles del pedido por ID", e);
        } catch (NamingException e) {
             new RuntimeException("Error al establecer conexión para obtener los detalles del pedido por ID", e);
        }
        return order_details;
    }
}
