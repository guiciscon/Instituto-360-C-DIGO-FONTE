package inst360.services;

import inst360.dao.UsuarioDAO;
import inst360.model.Usuario;

public class LoginService{

    private UsuarioDAO dao = new UsuarioDAO();

    public String autenticar(String matricula, String senha){

        Usuario usuario = dao.buscarPorMatricula(matricula);

        if (usuario == null){
            return "Matrícula não existente!";
        }

        if (!usuario.getSenha().equals(senha)){
            return "Senha incorreta!";
        }

        if (!usuario.isUsuarioValido()){
        return "Usuário aguardando validação...";
        }

        return "Login realizado com sucesso!";
    }
}
