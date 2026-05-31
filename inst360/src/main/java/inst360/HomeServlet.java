package inst360;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // ❌ se não estiver logado
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("loginUsuario.html");
            return;
        }

        // ✔️ se estiver logado → libera acesso
        response.sendRedirect("home.html");
    }
}