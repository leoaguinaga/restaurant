package utp.edu.pe.restaurant.servlet.admin.extras;

import utp.edu.pe.restaurant.dao.ExtraDao;
import utp.edu.pe.restaurant.model.Extra;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "admin/updateExtraRedirect", urlPatterns = {"/admin/updateExtraRedirect"})
public class UpdateExtraRedirectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        try {
            ExtraDao extraDao = new ExtraDao();
            Extra extra = extraDao.getExtraById(id);

            req.setAttribute("extra", extra);
            req.getRequestDispatcher("updateExtra.jsp").forward(req, resp);
        } catch (Exception e) {
            String msg = "No se pudo registrar el extra";
            req.setAttribute("message", msg + ". " + e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
