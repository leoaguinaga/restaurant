package utp.edu.pe.restaurant.servlet.admin.users;

import utp.edu.pe.restaurant.dao.UserDao;
import utp.edu.pe.restaurant.model.User;
import utp.edu.pe.restaurant.util.ErrorLog;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "admin/users/delete", urlPatterns = {"/admin/users/delete"})
public class DeleteUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));

        try{

            UserDao userDao = new UserDao();

            String username = req.getParameter("username");

            User user = userDao.getUserById(id);
            String full_name = user.getFull_name();

            userDao.deleteUser(id);

            ErrorLog.log("DeleteUserServlet: El usuario "+full_name+" ha sido eliminado.", ErrorLog.Level.INFO);

            resp.sendRedirect("/all");

            ErrorLog.log("DeleteUserServlet: La tarea fue completada con éxito.", ErrorLog.Level.INFO);

        }catch(Exception e){
            ErrorLog.log("DeleteUserServlet"+e.getMessage(), ErrorLog.Level.ERROR);
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
