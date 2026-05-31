package inst360;

import java.io.IOException;
import java.sql.Date;

import inst360.model.Usuario;
import inst360.services.CadastroService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/cadastro")
public class CadastroServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        try {

            System.out.println("NOME: " + request.getParameter("nome"));

            Usuario usuario = new Usuario();

            usuario.setNome(request.getParameter("nome"));
            usuario.setMatricula(request.getParameter("matricula"));
            usuario.setEndereco(request.getParameter("endereco"));

            String dataTexto = request.getParameter("dataNasc");

            if (dataTexto != null && !dataTexto.isEmpty()) {
                usuario.setDataNasc(Date.valueOf(dataTexto));
            }

            usuario.setEmail(request.getParameter("email"));
            usuario.setSenha(request.getParameter("senha"));

            CadastroService service = new CadastroService();

            String resultado = service.cadastrarUsuario(usuario);

            if (resultado.equals("Cadastro realizado com sucesso!")) {
                response.setStatus(200);
            } else {
                response.setStatus(400);
            }

            response.getWriter().write(resultado);

        } catch (Exception e) {

            e.printStackTrace();

            response.setStatus(500);
            response.getWriter().write("Erro interno no servidor!");
        }
    }
}