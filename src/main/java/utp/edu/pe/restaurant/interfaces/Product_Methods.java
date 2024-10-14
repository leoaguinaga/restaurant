package utp.edu.pe.restaurant.interfaces;

import utp.edu.pe.restaurant.model.Product;
import utp.edu.pe.restaurant.model.enums.Product_Type;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public interface Product_Methods {
    void registerProduct(Product product) throws SQLException, NamingException;
    List<Product> getAllProducts() throws SQLException, NamingException;
    List<Product> getProductsByType(Product_Type productType) throws SQLException, NamingException;
    Product getProductById(long product_id) throws SQLException, NamingException;
    Product getProductByName(String name) throws SQLException, NamingException;
    void updateProduct(Product product, long product_id) throws SQLException, NamingException;
    void deleteProduct(long product_id) throws SQLException, NamingException;
    Product getLastProduct() throws SQLException;
}
