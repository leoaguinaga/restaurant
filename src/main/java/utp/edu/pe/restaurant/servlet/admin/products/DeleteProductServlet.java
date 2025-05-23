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

@WebServlet(name = "admin/products/delete", urlPatterns = {"/admin/products/delete"})
public class DeleteProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long id = Long.parseLong(req.getParameter("id"));

        try{
            ProductDao productDao = new ProductDao();

            Product product = productDao.getProductById(id);

            String name = product.getName();

            productDao.deleteProduct(id);

            ErrorLog.log("DeleteProductServlet: El producto "+name.toUpperCase()+" ha sido eliminado.", ErrorLog.Level.ERROR);

            resp.sendRedirect("/all");
            ErrorLog.log("DeleteProductServlet: La tarea ha sido completada con éxito.", ErrorLog.Level.ERROR);

        }catch (Exception e) {
            ErrorLog.log("DeleteProductServlet: " + e.getMessage(), ErrorLog.Level.ERROR);
            req.setAttribute("message", e. getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
