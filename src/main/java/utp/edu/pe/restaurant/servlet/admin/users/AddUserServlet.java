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

@WebServlet(name = "admin/users/add", urlPatterns = {"/admin/users/add"})
public class AddUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String full_name = req.getParameter("full_name");
        String email = req.getParameter("email");
        String phone_number = req.getParameter("phone_number");
        String password = req.getParameter("password");
        String type = req.getParameter("type");

        try{
            //Validación de parámetros nulos

            if(full_name == null || email == null || phone_number == null || password == null || type == null){
                String message = "Uno de los parámetros ingresados es nulo.";
                ErrorLog.log("AddUserServlet: "+message, ErrorLog.Level.ERROR);
                req.setAttribute("message", message);
                req.getRequestDispatcher("error.jsp").forward(req, resp);
            }

            User user = User.createUserWithoutId(full_name, email, phone_number, password, User_Type.valueOf(type));

            UserDao userDao = new UserDao();
            userDao.registerUser(user);

            ErrorLog.log("AddUserServlet: El usuario ha sido registrado con éxito.", ErrorLog.Level.INFO);

            resp.sendRedirect("/workers");
            ErrorLog.log("La tarea ha sido completada con éxito.", ErrorLog.Level.INFO);

        }catch(Exception e){
            ErrorLog.log("AddUserServlet"+e.getMessage(), ErrorLog.Level.ERROR);
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
