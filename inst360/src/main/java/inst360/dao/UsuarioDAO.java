package inst360.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import inst360.connection.ConnectionFactory;
import inst360.model.Usuario;

public class UsuarioDAO {

    public boolean cadastrar(Usuario usuario) {

        String sql = "INSERT INTO usuarios " +
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

        String sql = "SELECT 1 FROM usuarios WHERE matricula = ?";

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

    public Usuario buscarPorMatricula(String matricula) {

    String sql = "SELECT matricula, senha, usuario_valido, administrador FROM usuarios WHERE matricula = ?";

    try (Connection conn = ConnectionFactory.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, matricula);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {

            Usuario usuario = new Usuario();

            usuario.setMatricula(rs.getString("matricula"));
            usuario.setSenha(rs.getString("senha"));

            usuario.setUsuarioValido(rs.getBoolean("usuario_valido"));
            usuario.setAdministrador(rs.getBoolean("administrador"));

            return usuario;
        }

        return null;

    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}

public List<Usuario> listarUsuarios() {

    List<Usuario> usuarios = new ArrayList<>();

    String sql =
        "SELECT * FROM usuarios WHERE usuario_valido = false";

    try (
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery()
    ) {

        while (rs.next()) {

            Usuario usuario = new Usuario();

            usuario.setId(
                rs.getInt("id"));

            usuario.setNome(
                rs.getString("nome"));

            usuario.setMatricula(
                rs.getString("matricula"));

            usuario.setEmail(
                rs.getString("email"));

            usuarios.add(usuario);
        }

    } catch (Exception e) {

        e.printStackTrace();
    }

    return usuarios;
}

public boolean validarUsuario(int id){
    String sql = "UPDATE usuarios SET usuario_valido = true WHERE id = ?";

    try(
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
    ){
        stmt.setInt(1, id);
        return stmt.executeUpdate() > 0;
    }
    
    catch (Exception e){
        e.printStackTrace();
    }

    return false;
}

public boolean invalidarUsuario(int id){
    String sql = "DELETE FROM usuarios WHERE id = ?";

    try(
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
    ){
        stmt.setInt(1, id);
        return stmt.executeUpdate() > 0;
    }
    
    catch (Exception e){
        e.printStackTrace();
    }

    return false;
}

}