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
import java.util.List;

@WebServlet(name = "admin/users/workers", urlPatterns = {"/admin/users/workers"})
public class WorkersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{

            UserDao userDao = new UserDao();

            List<User> workers = userDao.getWorkers();

            ErrorLog.log("WorkersServlet: La lista de trabajadores ha sido recuperada.", ErrorLog.Level.INFO);

            req.setAttribute("users", workers);
            req.getRequestDispatcher("users.jsp").forward(req, resp);

            ErrorLog.log("WorkersServlet: La tarea fue completada con éxito.", ErrorLog.Level.INFO);

        }catch(Exception e){
            ErrorLog.log("WorkersServlet"+e.getMessage(), ErrorLog.Level.ERROR);
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
