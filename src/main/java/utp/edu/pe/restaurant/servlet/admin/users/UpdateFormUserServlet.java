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

@WebServlet(name = "admin/users/updateForm", urlPatterns = {"/admin/users/updateForm"})
public class UpdateFormUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long id = Long.parseLong(req.getParameter("id"));

        try{

            UserDao userDao = new UserDao();

            User user = userDao.getUserById(id);

            ErrorLog.log("UpdateFormUserServlet: El usuario ha sido recuperado.", ErrorLog.Level.INFO);

            req.setAttribute("user", user);
            req.getRequestDispatcher("updateForm.jsp").forward(req, resp);

            ErrorLog.log("UpdateFormUserServlet: La tarea fue completada con éxito.", ErrorLog.Level.INFO);

        }catch(Exception e){
            ErrorLog.log("UpdateFormUserServlet"+e.getMessage(), ErrorLog.Level.ERROR);
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
