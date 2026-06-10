package inst360;

import java.io.IOException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/sessao")
public class SessaoServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        HttpSession session =
                request.getSession(false);

        if (session == null) {

            response.setStatus(401);
            response.getWriter().write("SEM_SESSAO");

            return;
        }

        String perfil =
                (String) session.getAttribute("perfil");

        response.getWriter().write(perfil);
    }
}