package utp.edu.pe.restaurant.servlet.client;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import utp.edu.pe.restaurant.util.ErrorLog;

import java.io.IOException;

@WebServlet(name = "logout", urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String usertype = (String) session.getAttribute("userType");

        try {
            session.invalidate();

            if ("admin".equals(usertype) || "chef".equals(usertype) || "delivery".equals(usertype)) {
                resp.sendRedirect("./index.jsp");
            } else if ("client".equals(usertype)) {
                resp.sendRedirect("index.jsp");
            }

        }catch (Exception e){
            ErrorLog.log("LogoutServlet: "+e.getMessage(), ErrorLog.Level.ERROR);
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
