package utp.edu.pe.restaurant.servlet.client.dashboard;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import utp.edu.pe.restaurant.util.ErrorLog;

import java.io.IOException;

@WebServlet(name = "profile", urlPatterns = {"/profile"})
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            req.getRequestDispatcher("profile.jsp").forward(req, resp);
        }catch (Exception e){
            ErrorLog.log("ProfileServlet: "+e.getMessage(), ErrorLog.Level.ERROR);
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
