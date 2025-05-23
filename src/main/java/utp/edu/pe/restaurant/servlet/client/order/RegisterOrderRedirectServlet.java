package utp.edu.pe.restaurant.servlet.client.order;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(name = "RegisterOrderRedirect", urlPatterns = {"/RegisterOrderRedirect"})
public class RegisterOrderRedirectServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        double amount = Double.parseDouble(req.getParameter("amount"));
        try {
            req.setAttribute("amount", amount);
            req.getRequestDispatcher("registerOrder.jsp").forward(req, resp);
        } catch (Exception e) {
            String msg = "Error al registrar la orden";
            req.setAttribute("message", msg + ". " + e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
