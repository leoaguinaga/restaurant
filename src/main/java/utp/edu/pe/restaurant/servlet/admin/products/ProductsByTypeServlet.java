package utp.edu.pe.restaurant.servlet.admin.products;

import utp.edu.pe.restaurant.dao.ProductDao;
import utp.edu.pe.restaurant.model.Product;
import utp.edu.pe.restaurant.model.enums.Product_Type;
import utp.edu.pe.restaurant.util.ErrorLog;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "admin/products/type", urlPatterns = {"/admin/products/type"})
public class ProductsByTypeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product_Type type = Product_Type.valueOf(req.getParameter("type"));


        try{
            ProductDao productDao = new ProductDao();

            List<Product> products = productDao.getProductsByType(type);

            if(products.isEmpty()){
                String message = "No hay productos del tipo " + type.getDisplayName().toUpperCase() +" para mostrar.";
                ErrorLog.log("ProductsByTypeServlet: " + message, ErrorLog.Level.INFO);
                req.setAttribute("message", message);
                req.getRequestDispatcher("error.jsp").forward(req, resp);
            }

            ErrorLog.log("ProductsByTypeServlet: Los productos fueron recuperados con éxito.", ErrorLog.Level.INFO);

            req.setAttribute("products", products);
            req.getRequestDispatcher("products.jsp").forward(req, resp);

            ErrorLog.log("ProductsByTypeServlet: La tarea fue completada con éxito.", ErrorLog.Level.INFO);

        }catch (Exception e) {
            ErrorLog.log("ProductsByTypeServlet: " + e.getMessage(), ErrorLog.Level.ERROR);
            req.setAttribute("message", e. getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
