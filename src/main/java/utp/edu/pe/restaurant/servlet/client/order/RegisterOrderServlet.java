package utp.edu.pe.restaurant.servlet.client.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import utp.edu.pe.restaurant.dao.OrderDao;
import utp.edu.pe.restaurant.dao.Order_DetailDao;
import utp.edu.pe.restaurant.dao.ProductDao;
import utp.edu.pe.restaurant.model.Order;
import utp.edu.pe.restaurant.model.Order_Detail;
import utp.edu.pe.restaurant.model.Product;
import utp.edu.pe.restaurant.model.User;
import utp.edu.pe.restaurant.model.enums.State;
import utp.edu.pe.restaurant.service.ApachePOI;
import utp.edu.pe.restaurant.util.UTPBinary;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "registerOrder", urlPatterns = {"/registerOrder"})
@MultipartConfig
public class RegisterOrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Manejar la información del formulario
        String address = req.getParameter("pac-input");
        Part filePart = req.getPart("evidence");
        String evidence = "evidence" + LocalDateTime.now();

        // Manejar el JSON Obtenido
        String cartData = req.getParameter("cart");
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object>[] jsonMap = objectMapper.readValue(cartData, Map[].class);
        Map<Product, Integer> cartItems = new HashMap<>();

        // Inicializar variable del total price
        double total = 0.0;

        // Obtener el cliente de la sesión
        HttpSession session = req.getSession();
        User client = (User) session.getAttribute("client");

        // Inicializamos la clase de ApachePOI
        ApachePOI apachePOI = new ApachePOI();

        try {
            // Inicializamos los DAO necesarios
            ProductDao productDAO = new ProductDao();
            OrderDao orderDao = new OrderDao();
            Order_DetailDao order_detailDAO = new Order_DetailDao();

            for (Map<String, Object> item : jsonMap) {
                String productName = item.get("name").toString();
                int quantity = Integer.parseInt(item.get("quantity").toString());
                Product product = productDAO.getProductByName(productName);
                total += Double.parseDouble(item.get("subtotal").toString());
                cartItems.put(product, quantity);
            }

            // Guardar la evidencia en el servidor
            byte[] fileContent = filePart.getInputStream().readAllBytes();
            UTPBinary.echobin(fileContent, "/tmp/" + evidence);

            // Creamos la orden y la almacenamos en la base de datos
            Order order = Order
                    .createOrderWithoutId(client, LocalDateTime.now(), address, total, evidence);

            orderDao.registerOrder(order);

            // Creamos las relaciones entre el pedido y los productos y las almacenamos en la base de datos
            for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
                Product product = entry.getKey();
                Integer quantity = entry.getValue();
                Order_Detail orderDetail = Order_Detail.createOrderDetail(order.getOrder_id(), product, quantity);
                order_detailDAO.registerOrder_detail(orderDetail);
            }

            // Enviamos el email de confirmación de pedido
            apachePOI.sendEmail(order.getUser().getEmail(),
                    "Pedido de Black Dog " + order.getOrderDateTime(),
                    State.on_hold.getDisplayName());

        } catch (Exception e) {
            String message = e.getMessage();
            req.setAttribute("message", message);
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
