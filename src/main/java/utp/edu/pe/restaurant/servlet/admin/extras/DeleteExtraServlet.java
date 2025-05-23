package utp.edu.pe.restaurant.servlet.admin.extras;

import utp.edu.pe.restaurant.dao.ExtraDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name ="admin/deleteExtra", urlPatterns = {"/admin/deleteExtra"})
public class DeleteExtraServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        try {
            ExtraDao extraDAO = new ExtraDao();
            extraDAO.deleteExtra(id);
            resp.sendRedirect("extras");
        } catch (Exception e) {
            String msg = "Error al eliminar el extra";
            req.setAttribute("message", msg + ". " + e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
