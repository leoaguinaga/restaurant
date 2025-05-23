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

@WebServlet(name = "admin/users/update", urlPatterns = {"/admin/users/update"})
public class UpdateUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long id = Long.parseLong(req.getParameter("id"));
        String full_name = req.getParameter("full_name");
        String email = req.getParameter("email");
        String phone_number = req.getParameter("phone_number");
        String password = req.getParameter("password");
        User_Type type = User_Type.valueOf(req.getParameter("type"));

        try{

            User user = User.createUser(id, full_name, email, phone_number, password, type);

            UserDao userDao = new UserDao();

            userDao.updateUser(user, id);

            ErrorLog.log("UpdateUserServlet: El usuario ha sido actualizado.", ErrorLog.Level.INFO);

            resp.sendRedirect("/all");

            ErrorLog.log("La tarea ha sido completada con éxito.", ErrorLog.Level.INFO);

        }catch(Exception e){
            ErrorLog.log("UpdateUserServlet: "+e.getMessage(), ErrorLog.Level.ERROR);
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
