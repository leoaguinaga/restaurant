package utp.edu.pe.restaurant.servlet.client.order;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "getOrders", urlPatterns = {"/getOrders"})
public class GetOrdersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = req.getReader();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        String body = sb.toString();
        req.setAttribute("message", body);
        req.getRequestDispatcher("message.jsp").forward(req, resp);
    }
}