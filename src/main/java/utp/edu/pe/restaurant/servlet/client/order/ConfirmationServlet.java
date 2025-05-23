package utp.edu.pe.restaurant.servlet.client.order;

import utp.edu.pe.restaurant.dao.OrderDao;
import utp.edu.pe.restaurant.dao.Order_DetailDao;
import utp.edu.pe.restaurant.dao.UserDao;
import utp.edu.pe.restaurant.model.Order;
import utp.edu.pe.restaurant.model.Order_Detail;
import utp.edu.pe.restaurant.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "confirmation", urlPatterns = {"/confirmation"})
public class ConfirmationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User client = (User) session.getAttribute("client");
        UserDao clientDao;
        OrderDao orderDao;
        Order_DetailDao order_DetailDao;

        try {
            clientDao = new UserDao();
            orderDao = new OrderDao();
            order_DetailDao = new Order_DetailDao();

            // Obtenemos los datos necesarios de la base de datos
            Order order = orderDao.getLastOrderByUser(client);
            List<Order_Detail> order_Details;
            order_Details = order_DetailDao.getOrderDetailsByOrderId(order.getOrder_id());

            // Almacenamos los datos en el atributo del request
            req.setAttribute("order", order);
            req.setAttribute("order_Details", order_Details);

            // Redirigimos al jsp
            req.getRequestDispatcher("confirmation.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}
