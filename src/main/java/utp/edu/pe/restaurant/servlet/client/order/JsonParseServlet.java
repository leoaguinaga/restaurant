package utp.edu.pe.restaurant.servlet.client.order;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "jsonParse", urlPatterns = {"/jsonParse"})
public class JsonParseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Redirigir a POST si llega una solicitud GET
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Leer el cuerpo de la solicitud (que contiene el JSON)
            BufferedReader reader = req.getReader();

            // Registrar el cuerpo de la solicitud en los logs para depuración
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            System.out.println("JSON recibido: " + stringBuilder.toString());

            // Deserializar el JSON en un Map usando ObjectMapper de Jackson
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object>[] jsonMap = objectMapper.readValue(stringBuilder.toString(), Map[].class);

            // Pasar los datos deserializados a la JSP
            req.setAttribute("jsonData", jsonMap);

            // Redirigir a la página JSP para mostrar los datos
            req.getRequestDispatcher("message.jsp").forward(req, resp);

        } catch (Exception e) {
            // Capturar cualquier excepción y mostrar el error
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}
