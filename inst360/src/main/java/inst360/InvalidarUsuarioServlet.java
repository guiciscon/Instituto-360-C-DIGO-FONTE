package inst360;

import java.io.IOException;

import inst360.services.validarCadService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/invalidarUsuario")
public class InvalidarUsuarioServlet
        extends HttpServlet {

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        int id =
            Integer.parseInt(
                request.getParameter("id"));

        validarCadService service =
            new validarCadService();

        boolean sucesso =
            service.invalidarUsuario(id);

        if (sucesso) {

            response.setStatus(200);

            response.getWriter().write(
                "Cadastro recusado com sucesso!");

        } else {

            response.setStatus(400);

            response.getWriter().write(
                "Erro ao excluir cadastro.");
        }
    }
}