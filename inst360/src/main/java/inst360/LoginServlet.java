package inst360;

import java.io.IOException;

import inst360.services.LoginService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        try {

            String matricula = request.getParameter("matricula");
            String senha = request.getParameter("senha");

            LoginService service = new LoginService();

            String resultado = service.autenticar(matricula, senha);

            if (resultado.equals("Login realizado com sucesso!")) {
                HttpSession session = request.getSession();

                session.setAttribute("usuario", matricula);
                
                response.setStatus(200);
            } else {
                response.setStatus(401);
            }

            response.getWriter().write(resultado);

        } catch (Exception e) {

            e.printStackTrace();

            response.setStatus(500);
            response.getWriter().write("Erro interno no servidor!");
        }
    }
}