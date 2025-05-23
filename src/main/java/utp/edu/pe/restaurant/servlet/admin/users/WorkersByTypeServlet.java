package utp.edu.pe.restaurant.servlet.admin.users;

import utp.edu.pe.restaurant.dao.UserDao;
import utp.edu.pe.restaurant.model.User;
import utp.edu.pe.restaurant.model.enums.User_Type;
import utp.edu.pe.restaurant.util.ErrorLog;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "admin/users/update", urlPatterns = {"/admin/users/update"})
public class WorkersByTypeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User_Type user_type = User_Type.valueOf(req.getParameter("user_type"));

        try{

            UserDao userDao = new UserDao();

            List<User> workers = userDao.getUsersByType(user_type);

            ErrorLog.log("WorkersByTypeServlet: La lista de trabajadores de tipo "+user_type.getDisplayName().toUpperCase()+" ha sido recuperada.", ErrorLog.Level.INFO);

            req.setAttribute("users", workers);
            req.getRequestDispatcher("users.jsp").forward(req, resp);

            ErrorLog.log("WorkersByTypeServlet: La tarea fue completada con éxito.", ErrorLog.Level.INFO);

        }catch(Exception e){
            ErrorLog.log("WorkersByTypeServlet: "+e.getMessage(), ErrorLog.Level.ERROR);
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
