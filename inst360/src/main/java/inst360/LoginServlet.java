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
            throws ServletException, IOException{

        request.setCharacterEncoding("UTF-8");

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        try {

            String matricula = request.getParameter("matricula");
            String senha = request.getParameter("senha");

            LoginService service = new LoginService();

            String resultado = service.autenticar(matricula, senha);
        
            //alimenta os atributos da sessão se for administrador
            if (resultado.equals("ADMIN")) {
                HttpSession session = request.getSession();

                session.setAttribute("usuario", matricula);
                session.setAttribute("perfil", "ADMIN");

                response.setStatus(200);
                response.getWriter().write("ADMIN");
                return;
            }

            //alimenta os atributos da sessão se for usuario
            else if (resultado.equals("USUARIO")) {
                HttpSession session = request.getSession();

                session.setAttribute("usuario", matricula);
                session.setAttribute("perfil","USUARIO");
                
                response.setStatus(200);
                response.getWriter().write("USUARIO");
                return;
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