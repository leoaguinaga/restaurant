package utp.edu.pe.restaurant.servlet.client;

import utp.edu.pe.restaurant.dao.UserDao;
import utp.edu.pe.restaurant.model.Product;
import utp.edu.pe.restaurant.model.User;
import utp.edu.pe.restaurant.model.enums.User_Type;
import utp.edu.pe.restaurant.service.Auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "singin", urlPatterns = {"/singin"})
public class SinginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("s-email");
        String phone = req.getParameter("s-phone");
        String pwd = req.getParameter("s-password");

        try {
            User client = User.createUserWithoutId("User", email, phone, Auth.md5(pwd), User_Type.client);
            UserDao clientDao = new UserDao();
            clientDao.registerUser(client);

            HttpSession session = req.getSession();
            HashMap<Product, Integer> saleCar = new HashMap<>();
            session.setAttribute("saleCar", saleCar);
            session.setAttribute("client", client);
            session.setAttribute("name", client.getFull_name());
            session.setAttribute("userType", "client");
            resp.sendRedirect("index.jsp");

        } catch (Exception e) {
            String msg = "Error al registrarte";
            req.setAttribute("message", msg + ". " + e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
