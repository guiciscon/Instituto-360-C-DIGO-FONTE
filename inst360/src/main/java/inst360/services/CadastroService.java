package inst360.services;

import java.time.LocalDate;
import java.time.Period;

import inst360.dao.UsuarioDAO;
import inst360.model.Usuario;

public class CadastroService {

    private UsuarioDAO dao = new UsuarioDAO();

    public String cadastrarUsuario(Usuario usuario) {

        // verifica se o usuário tem idade o suficiente para cadastrar
        if (usuario.getDataNasc() != null) {

            LocalDate dataNasc = usuario.getDataNasc().toLocalDate();

            int idade = Period.between(dataNasc, LocalDate.now()).getYears();

            if (idade < 12) {
                return "Usuário deve ter pelo menos 12 anos!";
            }
        } else {
            return "Data de nascimento é obrigatória!";
        }

        // verifica se a matricula ja existe no banco
        if (dao.matriculaExiste(usuario.getMatricula())) {
            return "Matrícula já cadastrada!";
        }

        // se tudo estiver certo, segue com o cadastro
        boolean sucesso = dao.cadastrar(usuario);

        if (sucesso) {
            return "Cadastro realizado com sucesso!";
        } else {
            return "Erro ao cadastrar usuário!";
        }
    }
}
