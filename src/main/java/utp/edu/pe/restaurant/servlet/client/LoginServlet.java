package utp.edu.pe.restaurant.servlet.client;

import utp.edu.pe.restaurant.dao.UserDao;
import utp.edu.pe.restaurant.model.Product;
import utp.edu.pe.restaurant.model.User;
import utp.edu.pe.restaurant.service.Auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import utp.edu.pe.restaurant.util.ErrorLog;

import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "login", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        try {
            Auth auth = new Auth();
            User user = auth.isValidUser(email, password);

            if(user == null){
                req.setAttribute("isError", true);
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            } else {
                HttpSession session = req.getSession();
                session.setAttribute("email", email);
                session.setAttribute("userType", user.getUser_type().toString());
                UserDao workerDao = new UserDao();
                User worker;
                switch (user.getUser_type()) {
                    case client:
                        HashMap<Product, Integer> saleCar = new HashMap<>();
                        UserDao clientDao = new UserDao();
                        User client = clientDao.getUserByEmail(email);
                        session.setAttribute("saleCar", saleCar);
                        session.setAttribute("client", client);
                        session.setAttribute("name", client.getFull_name());
                        resp.sendRedirect("menu");
                        break;

                    case admin:
                        worker = workerDao.getUserByEmail(email);
                        session.setAttribute("worker", worker);
                        resp.sendRedirect("admin/ordersHistory");
                        break;

                    case driver:
                        worker = workerDao.getUserByEmail(email);
                        session.setAttribute("worker", worker);
                        resp.sendRedirect("admin/delivery");
                        break;

                    case chef:
                        worker = workerDao.getUserByEmail(email);
                        session.setAttribute("worker", worker);
                        resp.sendRedirect("admin/chef");
                        break;

                    default:
                        req.setAttribute("isError", true);
                        req.getRequestDispatcher("index.jsp").forward(req, resp);
                        break;
                }
            }
        } catch (Exception e) {
            ErrorLog.log("LoginServlet: "+ e.getMessage(), ErrorLog.Level.ERROR);
            String credentials = String.format("E:%s  P:%s", email, password);
            req.setAttribute("message", e.getMessage() + credentials);
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
