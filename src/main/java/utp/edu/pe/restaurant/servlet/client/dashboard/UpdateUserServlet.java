package utp.edu.pe.restaurant.servlet.client.dashboard;

import utp.edu.pe.restaurant.dao.UserDao;
import utp.edu.pe.restaurant.model.User;
import utp.edu.pe.restaurant.model.enums.User_Type;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import utp.edu.pe.restaurant.util.ErrorLog;

import java.io.IOException;

@WebServlet(name = "updateUser", urlPatterns = {"/updateUser"})
public class UpdateUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String new_full_name = req.getParameter("full_name");
        String new_phone_number = req.getParameter("phone_number");
        String new_email = req.getParameter("email");

        HttpSession session = req.getSession();
        User client = (User) session.getAttribute("client");
        try{
            String email = client.getEmail();
            User clientUpdate = User.createUser(client.getUser_id(), new_full_name, new_email, new_phone_number, client.getPwd(), User_Type.client);
            UserDao clientDao = new UserDao();
            clientDao.updateUser(clientUpdate, client.getUser_id());

            session.removeAttribute("client");
            session.setAttribute("client", clientUpdate);
            req.getRequestDispatcher("profile.jsp").forward(req, resp);
        } catch (Exception e){
            ErrorLog.log("UpdateUserServlet: "+e.getMessage(), ErrorLog.Level.ERROR);
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
