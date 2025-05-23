package utp.edu.pe.restaurant.servlet.admin.products;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;
import utp.edu.pe.restaurant.dao.ProductDao;
import utp.edu.pe.restaurant.model.Product;
import utp.edu.pe.restaurant.model.enums.Product_Type;
import utp.edu.pe.restaurant.util.AppConfig;
import utp.edu.pe.restaurant.util.ErrorLog;
import utp.edu.pe.restaurant.util.UTPBinary;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@MultipartConfig
@WebServlet(name = "admin/products/add", urlPatterns = {"/admin/products/add"})
public class AddProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        Part filePart = req.getPart("image");
        String price = req.getParameter("price");
        String description = req.getParameter("description");
        String type = req.getParameter("type");
        String image = name + LocalDateTime.now();

        try{

            // Validación de parámetros nulos
            if (name == null || image == null || price == null || description == null || type == null) {
                String message = "Uno de los parámetros ingresados es nulo.";
                ErrorLog.log("AddProductServlet: " + message, ErrorLog.Level.ERROR);
                req.setAttribute("message", message);
                req.getRequestDispatcher("error.jsp").forward(req, resp);
                return;
            }

            Product product = Product.createProductWithoutId(name, image, Double.parseDouble(price), description, Product_Type.valueOf(type));

            String imgDir = AppConfig.getImageDir();

            byte[] fileContent = filePart.getInputStream().readAllBytes();
            UTPBinary.echobin(fileContent, imgDir+"product_"+image);

            ErrorLog.log("AddProductServlet: La imagen del producto fue almacenada con éxito.", ErrorLog.Level.INFO);

            ProductDao productDao = new ProductDao();
            productDao.registerProduct(product);
            ErrorLog.log("AddProductServlet: Producto registrado correctamente.", ErrorLog.Level.INFO);

            resp.sendRedirect("/all");
            ErrorLog.log("AddProductServlet: La tarea fue completada con éxito.", ErrorLog.Level.INFO);

        }catch (Exception e) {
            ErrorLog.log("AddProductServlet: " + e.getMessage(), ErrorLog.Level.ERROR);
            req.setAttribute("message", e. getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
