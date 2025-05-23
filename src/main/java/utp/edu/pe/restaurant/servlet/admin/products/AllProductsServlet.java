package utp.edu.pe.restaurant.servlet.admin.products;

import utp.edu.pe.restaurant.dao.ProductDao;
import utp.edu.pe.restaurant.model.Product;
import utp.edu.pe.restaurant.util.ErrorLog;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "admin/products/all", urlPatterns = {"/admin/products/all"})
public class AllProductsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            ProductDao productDao = new ProductDao();

            List<Product> products = productDao.getAllProducts();

            if(products.isEmpty()){
                String message = "No hay productos para mostrar.";
                ErrorLog.log("AllProductsServlet: " + message, ErrorLog.Level.INFO);
                req.setAttribute("message", message);
                req.getRequestDispatcher("error.jsp").forward(req, resp);
            }

            ErrorLog.log("AllProductsServlet: Los productos fueron recuperados con éxito.", ErrorLog.Level.INFO);

            req.setAttribute("products", products);
            req.getRequestDispatcher("products.jsp").forward(req, resp);

            ErrorLog.log("AllProductsServlet: La tarea fue completada con éxito.", ErrorLog.Level.INFO);

        }catch (Exception e) {
            ErrorLog.log("AllProductsServlet: " + e.getMessage(), ErrorLog.Level.ERROR);
            req.setAttribute("message", e. getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
