package utp.edu.pe.restaurant.dao;

import utp.edu.pe.restaurant.interfaces.Product_Methods;
import utp.edu.pe.restaurant.model.Product;
import utp.edu.pe.restaurant.model.enums.Product_Type;
import utp.edu.pe.restaurant.util.AppConfig;
import utp.edu.pe.restaurant.util.DataAccessMariaDB;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao implements Product_Methods {
    @Override
    public void registerProduct(Product product) throws SQLException, NamingException {
        String query = "INSERT INTO product (name, image, price, decription, type) VALUES (?, ?, ?, ?, ?)";
        try (Connection cnn = DataAccessMariaDB.getConnection(DataAccessMariaDB.TipoDA.valueOf(AppConfig.getSourceType()), "java:/MariaDB");
             PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setString(1, product.getName());
            ps.setString(2, product.getImage());
            ps.setDouble(3, product.getPrice());
            ps.setString(4, product.getDescription());
            ps.setString(5, product.getProduct_type().toString());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No se pudo registrar el producto en la base de datos.");
            }
        }
    }

    @Override
    public List<Product> getAllProducts() throws SQLException, NamingException {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM product";
        try (Connection cnn = DataAccessMariaDB.getConnection(DataAccessMariaDB.TipoDA.valueOf(AppConfig.getSourceType()), AppConfig.getDatasource());
             PreparedStatement ps = cnn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                products.add(Product.createProduct(
                        rs.getLong("product_id"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        Product_Type.valueOf(rs.getString("type"))
                ));
            }
            if (products.isEmpty()) {
                throw new SQLException("No se encontraron productos en la base de datos.");
            }
        }
        return products;
    }

    @Override
    public List<Product> getProductsByType(Product_Type productType) throws SQLException, NamingException {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM product WHERE type = ?";
        try (Connection cnn = DataAccessMariaDB.getConnection(DataAccessMariaDB.TipoDA.valueOf(AppConfig.getSourceType()), AppConfig.getDatasource());
             PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setString(1, productType.toString());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    products.add(Product.createProduct(
                            rs.getLong("product_id"),
                            rs.getString("name"),
                            rs.getString("image"),
                            rs.getDouble("price"),
                            rs.getString("description"),
                            Product_Type.valueOf(rs.getString("type"))
                    ));
                }
            }
        }
        return products;
    }

    @Override
    public Product getProductById(long product_id) throws SQLException, NamingException {
        String query = "SELECT * FROM product WHERE product_id = ?";
        Product product = null;
        try (Connection cnn = DataAccessMariaDB.getConnection(DataAccessMariaDB.TipoDA.valueOf(AppConfig.getSourceType()), AppConfig.getDatasource());
             PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setLong(1, product_id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    product = Product.createProduct(
                            rs.getLong("product_id"),
                            rs.getString("name"),
                            rs.getString("image"),
                            rs.getDouble("price"),
                            rs.getString("description"),
                            Product_Type.valueOf(rs.getString("type"))
                    );
                } else {
                    throw new SQLException(String.format("No se encontró un producto con el ID %d en la base de datos.", product_id));
                }
            }
        }
        return product;
    }

    @Override
    public Product getProductByName(String name) throws SQLException, NamingException {
        String query = "SELECT * FROM product WHERE name = ?";
        Product product = null;
        try (Connection cnn = DataAccessMariaDB.getConnection(DataAccessMariaDB.TipoDA.valueOf(AppConfig.getSourceType()), AppConfig.getDatasource());
             PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    product = Product.createProduct(
                            rs.getLong("product_id"),
                            rs.getString("name"),
                            rs.getString("image"),
                            rs.getDouble("price"),
                            rs.getString("description"),
                            Product_Type.valueOf(rs.getString("type"))
                    );
                } else {
                    throw new SQLException(String.format("No se encontró un producto con el nombre %s en la base de datos.", name));
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
        }
        return product;
    }

    @Override
    public void updateProduct(Product product, long product_id) throws SQLException, NamingException {
        String query = "UPDATE product SET name = ?, image = ?, price = ?, description = ?,  type = ? WHERE product_id = ?";
        try (Connection cnn = DataAccessMariaDB.getConnection(DataAccessMariaDB.TipoDA.valueOf(AppConfig.getSourceType()), AppConfig.getDatasource());
             PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setString(1, product.getName());
            ps.setString(2, product.getImage());
            ps.setDouble(3, product.getPrice());
            ps.setString(4, product.getDescription());
            ps.setString(5, product.getProduct_type().toString());
            ps.setLong(6, product_id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No se pudo actualizar el producto en la base de datos.");
            }
        }
    }

    @Override
    public void deleteProduct(long product_id) throws SQLException, NamingException {
        String query = "DELETE FROM product WHERE product_id = ?";
        try (Connection cnn = DataAccessMariaDB.getConnection(DataAccessMariaDB.TipoDA.valueOf(AppConfig.getSourceType()), AppConfig.getDatasource());
             PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setLong(1, product_id);
            ps.executeUpdate();
        }
    }

    @Override
    public Product getLastProduct() throws SQLException {
        String query = "SELECT * FROM product ORDER BY product_id DESC LIMIT 1";
        Product product = null;
        try (Connection cnn = DataAccessMariaDB.getConnection(DataAccessMariaDB.TipoDA.valueOf(AppConfig.getSourceType()), AppConfig.getDatasource());
             PreparedStatement ps = cnn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                product = Product.createProduct(
                        rs.getLong("product_id"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        Product_Type.valueOf(rs.getString("type"))
                );
            } else {
                throw new SQLException("No se encontró el último producto en la base de datos.");
            }
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
        return product;
    }
}