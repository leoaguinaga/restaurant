package utp.edu.pe.restaurant.servlet.client.dashboard;

import utp.edu.pe.restaurant.dao.ProductDao;
import utp.edu.pe.restaurant.model.Product;
import utp.edu.pe.restaurant.model.enums.Product_Type;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "menu", urlPatterns = {"/menu"})
public class MenuServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String strProductType = req.getParameter("type");
        Product_Type productType = (strProductType == null || strProductType.isEmpty() || strProductType.isBlank()) ? Product_Type.burger : Product_Type.valueOf(strProductType);

        try {
            ProductDao productDao = new ProductDao();
            List<Product> products = productDao.getProductsByType(productType);


            req.setAttribute("products", products);
            req.setAttribute("productType", productType);
            req.getRequestDispatcher("/menu.jsp").forward(req, resp);

        } catch (Exception e) {
            String msg = "No se puede ver el menú en este momento. Inténtalo más tarde";
            req.setAttribute("message", msg + ". " + e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
