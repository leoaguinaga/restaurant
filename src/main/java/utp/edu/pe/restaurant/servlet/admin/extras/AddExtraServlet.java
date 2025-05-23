package utp.edu.pe.restaurant.servlet.admin.extras;

import utp.edu.pe.restaurant.dao.ExtraDao;
import utp.edu.pe.restaurant.model.Extra;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name ="admin/addExtra", urlPatterns = {"/admin/addExtra"})
public class AddExtraServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));

        try {
            ExtraDao extraDAO = new ExtraDao();
            Extra extra = Extra.createExtraWithoutId(name, price);
            extraDAO.registerExtra(extra);

            resp.sendRedirect("extras");
        } catch (Exception e) {
            String msg = "No se pudo agregar el extra" +
                    "";
            req.setAttribute("message", msg + ". " + e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
