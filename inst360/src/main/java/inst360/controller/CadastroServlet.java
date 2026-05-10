package inst360.controller;

import java.io.IOException;
import java.sql.Date;

import inst360.dao.UsuarioDAO;
import inst360.model.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cadastro")
public class CadastroServlet extends HttpServlet {

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        Usuario usuario = new Usuario();

        usuario.setNome(
                request.getParameter("nome"));

        usuario.setMatricula(
                request.getParameter("matricula"));

        usuario.setEndereco(
                request.getParameter("endereco"));

        usuario.setDataNasc(
                Date.valueOf(
                request.getParameter("dataNasc")));

        usuario.setEmail(
                request.getParameter("email"));

        usuario.setSenha(
                request.getParameter("senha"));

        UsuarioDAO dao = new UsuarioDAO();

        dao.cadastrar(usuario);

        response.sendRedirect("sucesso.jsp");
    }
}
