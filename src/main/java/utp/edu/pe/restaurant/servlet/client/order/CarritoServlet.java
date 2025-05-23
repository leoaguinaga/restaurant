package utp.edu.pe.restaurant.servlet.client.order;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "procesarCarrito", urlPatterns = {"/procesarCarrito"})
public class CarritoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Leer el cuerpo de la solicitud (que contiene el JSON)
            BufferedReader reader = req.getReader();
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            System.out.println("JSON recibido: " + stringBuilder.toString());

            // Deserializar el JSON en un Map usando ObjectMapper de Jackson
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object>[] jsonMap = objectMapper.readValue(stringBuilder.toString(), Map[].class);

            // Aquí puedes procesar el carrito (almacenar, validar, etc.)
            HttpSession session = req.getSession();
            session.setAttribute("carritoProcesado", jsonMap);
            // ...

            resp.getWriter().write("{\\\"status\\\":\\\"success\\\"}");
        } catch (Exception e) {
            // Manejar errores
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}
