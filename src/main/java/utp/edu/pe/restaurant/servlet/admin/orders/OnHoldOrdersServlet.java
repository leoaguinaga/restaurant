package utp.edu.pe.restaurant.servlet.admin.orders;

import utp.edu.pe.restaurant.dao.OrderDao;
import utp.edu.pe.restaurant.model.Order;
import utp.edu.pe.restaurant.model.enums.State;
import utp.edu.pe.restaurant.util.ErrorLog;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Stack;

@WebServlet(name = "admin/orders/on_hold", urlPatterns = {"/admin/orders/on_hold"})
public class OnHoldOrdersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try{
            OrderDao orderDao = new OrderDao();

            Stack<Order> on_hold_orders = orderDao.getOrdersByState(State.on_hold);

            if(on_hold_orders.isEmpty()){
                String message = "No hay órdenes pendientes para mostrar.";
                ErrorLog.log("OnHoldOrdersServlet: No hay órdenes pendientes.", ErrorLog.Level.ERROR);
                req.setAttribute("message", message);
                req.getRequestDispatcher("error.jsp").forward(req, resp);
            }

            ErrorLog.log("OnHoldOrdersServlet: Se recuperaron las órdenes con éxito.", ErrorLog.Level.INFO);

            req.setAttribute("orders", on_hold_orders);
            req.getRequestDispatcher("orders.jsp").forward(req, resp);

            ErrorLog.log("OnHoldOrdersServlet: Se finalizó la tarea con éxito.", ErrorLog.Level.INFO);

        } catch (Exception e) {
            ErrorLog.log("OnHoldOrdersServlet: " + e.getMessage(), ErrorLog.Level.ERROR);
            req.setAttribute("message", e. getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
