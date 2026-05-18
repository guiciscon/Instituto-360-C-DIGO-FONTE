package inst360.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import inst360.connection.ConnectionFactory;
import inst360.model.Usuario;

public class UsuarioDAO {

    public boolean cadastrar(Usuario usuario) {

        String sql = "INSERT INTO cadastros " +
                "(nome, matricula, endereco, data_nasc, email, senha) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getMatricula());
            stmt.setString(3, usuario.getEndereco());

            // DATA (pode ser null)
            if (usuario.getDataNasc() != null) {
                stmt.setDate(4, usuario.getDataNasc());
            } else {
                stmt.setNull(4, java.sql.Types.DATE);
            }

            stmt.setString(5, usuario.getEmail());
            stmt.setString(6, usuario.getSenha());

            int linhasAfetadas = stmt.executeUpdate();

            return linhasAfetadas > 0;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    public boolean matriculaExiste(String matricula) {

        String sql = "SELECT 1 FROM cadastros WHERE matricula = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, matricula);

            ResultSet rs = stmt.executeQuery();

            return rs.next(); // true = já existe

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}