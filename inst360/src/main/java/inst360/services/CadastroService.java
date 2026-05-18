package inst360.services;

import inst360.dao.UsuarioDAO;
import inst360.model.Usuario;

public class CadastroService {

    private UsuarioDAO dao = new UsuarioDAO();

    public String cadastrarUsuario(Usuario usuario) {

        // 🔍 verifica se matrícula já existe
        if (dao.matriculaExiste(usuario.getMatricula())) {
            return "Matrícula já cadastrada!";
        }

        // tenta cadastrar
        boolean sucesso = dao.cadastrar(usuario);

        if (sucesso) {
            return "Cadastro realizado com sucesso!";
        } else {
            return "Erro ao cadastrar usuário!";
        }
    }
}
