package inst360.filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter({
    "/validarCadastros",
    "/validarCadastros.html"
})
public class AutenticadorAdm extends HttpFilter{

    @Override
    public void doFilter(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpSession session =
                request.getSession(false);

        if (session == null) {

            response.sendRedirect(
                    request.getContextPath() +
                    "/loginUsuario.html");

            return;
        }

        String perfil =
                (String) session.getAttribute("perfil");

        if (!"ADMIN".equals(perfil)) {

            response.sendRedirect(
                    request.getContextPath() +
                    "/home");

            return;
        }

        chain.doFilter(request, response);
    }
}