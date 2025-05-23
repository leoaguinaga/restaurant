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

@WebServlet(name = "admin/products/updateForm", urlPatterns = {"/admin/products/updateForm"})
public class UpdateProductFormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));

        try{
            ProductDao productDao = new ProductDao();

            Product product = productDao.getProductById(id);

            if(product == null){
                String message = "No se ha podido encontrar el producto seleccionado.";
                ErrorLog.log("UpdateProductFormServlet: " + message, ErrorLog.Level.ERROR);
                req.setAttribute("message", message);
                req.getRequestDispatcher("error.jsp").forward(req, resp);
            }

            ErrorLog.log("UpdateProductFormServlet: El producto fue recuperado con éxito.", ErrorLog.Level.ERROR);

            req.setAttribute("product", product);
            req.getRequestDispatcher("updateForm.jsp").forward(req, resp);
            ErrorLog.log("UpdateProductFormServlet: La tarea fue completado con éxito.", ErrorLog.Level.ERROR);

        }catch (Exception e) {
            ErrorLog.log("UpdateProductFormServlet: " + e.getMessage(), ErrorLog.Level.ERROR);
            req.setAttribute("message", e. getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
