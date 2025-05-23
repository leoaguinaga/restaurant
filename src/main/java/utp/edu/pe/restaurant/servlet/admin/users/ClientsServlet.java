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

@WebServlet(name = "admin/users/clients", urlPatterns = {"/admin/users/clients"})
public class ClientsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{

            UserDao userDao = new UserDao();

            List<User> clients = userDao.getUsersByType(User_Type.client);

            ErrorLog.log("ClientsServlet: La lista de clientes ha sido recuperada.", ErrorLog.Level.INFO);

            req.setAttribute("users", clients);
            req.getRequestDispatcher("users.jsp").forward(req, resp);

            ErrorLog.log("ClientsServlet: La tarea fue completada con éxito.", ErrorLog.Level.INFO);

        }catch(Exception e){
            ErrorLog.log("ClientsServlet: "+e.getMessage(), ErrorLog.Level.ERROR);
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
