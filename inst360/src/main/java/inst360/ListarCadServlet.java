package inst360;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import inst360.model.Usuario;
import inst360.services.validarCadService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/usuariosPendentes")
public class ListarCadServlet
        extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        validarCadService service =
            new validarCadService();

        List<Usuario> usuarios =
            service.listarUsuarios();

        Gson gson = new Gson();

        response.setContentType(
            "application/json");

        response.getWriter().write(
            gson.toJson(usuarios));
    }
}
