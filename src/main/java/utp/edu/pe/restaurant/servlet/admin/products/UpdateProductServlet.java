package utp.edu.pe.restaurant.servlet.admin.products;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;
import utp.edu.pe.restaurant.dao.ProductDao;
import utp.edu.pe.restaurant.model.Product;
import utp.edu.pe.restaurant.model.enums.Product_Type;
import utp.edu.pe.restaurant.util.AppConfig;
import utp.edu.pe.restaurant.util.ErrorLog;
import utp.edu.pe.restaurant.util.TextUTP;
import utp.edu.pe.restaurant.util.UTPBinary;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@MultipartConfig
@WebServlet(name = "admin/products/update", urlPatterns = {"/admin/products/update"})
public class UpdateProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        String name = req.getParameter("name");
        Part filePart = req.getPart("image");
        String price = req.getParameter("price");
        String description = req.getParameter("description");
        String type = req.getParameter("type");
        String image;

        try{

            //Validación de parámetros nulos
            if(id == null || name == null || price == null || description == null || type == null){
                String message = "Uno de los parámetros ingresados es nulo.";
                ErrorLog.log("UpdateProductServlet: " + message, ErrorLog.Level.ERROR);
                req.setAttribute("message", message);
                req.getRequestDispatcher("error.jsp").forward(req, resp);
                return;
            }

            ProductDao productDao = new ProductDao();
            String imgDir = AppConfig.getImageDir();
            String old_product_image = productDao.getProductById(Long.parseLong(id)).getImage();

            if(filePart != null){

                image = name + LocalDateTime.now();

                TextUTP.deleteFile(imgDir+"product_"+old_product_image);

                ErrorLog.log("UpdateProductServlet: La imagen anterior del producto ha sido borrada.", ErrorLog.Level.INFO);

                byte[] fileContent = filePart.getInputStream().readAllBytes();
                UTPBinary.echobin(fileContent, imgDir+"product_"+image);

                ErrorLog.log("UpdateProductServlet: La nueva imagen del producto ha sido almacenada.", ErrorLog.Level.INFO);

            }else {
                image = old_product_image;
            }

            Product product = Product.createProduct(Long.parseLong(id), name, image, Double.parseDouble(price), description, Product_Type.valueOf(type));

            productDao.updateProduct(product, Long.parseLong(id));
            ErrorLog.log("UpdateProductServlet: El producto ha sido actualizado.", ErrorLog.Level.INFO);

            resp.sendRedirect("/all");

            ErrorLog.log("UpdateProductServlet: La tarea ha sido completada con éxito.", ErrorLog.Level.INFO);

        }catch (Exception e) {
            ErrorLog.log("UpdateProductServlet: " + e.getMessage(), ErrorLog.Level.ERROR);
            req.setAttribute("message", e. getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
