package utp.edu.pe.restaurant.servlet.admin.extras;

import utp.edu.pe.restaurant.dao.ExtraDao;
import utp.edu.pe.restaurant.model.Extra;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name ="admin/extras", urlPatterns = {"/admin/extras"})
public class ExtrasServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Extra> extras = new ArrayList<>();
        try {
            ExtraDao extraDao = new ExtraDao();
            extras = extraDao.getAllExtras();

            req.setAttribute("extras", extras);
            req.getRequestDispatcher("extras.jsp").forward(req, resp);
        } catch (Exception e) {
            if (extras != null && !extras.isEmpty()) {
                req.setAttribute("message", e.getMessage());
                req.getRequestDispatcher("error.jsp").forward(req, resp);
            } else {
                req.setAttribute("extras", extras);
                req.getRequestDispatcher("extras.jsp").forward(req, resp);
            }
        }
    }
}
