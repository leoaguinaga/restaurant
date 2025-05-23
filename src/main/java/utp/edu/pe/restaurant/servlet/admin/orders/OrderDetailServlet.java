package utp.edu.pe.restaurant.servlet.admin.orders;

import utp.edu.pe.restaurant.dao.OrderDao;
import utp.edu.pe.restaurant.dao.Order_DetailDao;
import utp.edu.pe.restaurant.model.Order;
import utp.edu.pe.restaurant.model.Order_Detail;
import utp.edu.pe.restaurant.util.ErrorLog;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "admin/orders/detail", urlPatterns = {"/admin/orders/detail"})
public class OrderDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));

        try{
            Order_DetailDao order_detailDao= new Order_DetailDao();
            OrderDao orderDao= new OrderDao();

            Order order = orderDao.getOrderById(id);
            ErrorLog.log("OrderDetailServlet: Se obtuvo el pedido con éxito.", ErrorLog.Level.INFO);

            List<Order_Detail> order_detailList = order_detailDao.getOrderDetailsByOrderId(id);
            ErrorLog.log("OrderDetailServlet: Se obtuvo el detalle del pedido con éxito.", ErrorLog.Level.INFO);

            req.setAttribute("order", order);
            req.setAttribute("order_detail", order_detailList);
            req.getRequestDispatcher("order_detail.jsp").forward(req, resp);
            ErrorLog.log("OrderDetailServlet: La tarea fue completada con éxito.", ErrorLog.Level.INFO);

        }catch(Exception e){
            ErrorLog.log("OrderDetailServlet: "+e.getMessage(), ErrorLog.Level.ERROR);
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }

}
