package inst360.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import inst360.connection.ConnectionFactory;
import inst360.model.Usuario;

public class UsuarioDAO {

    public void cadastrar(Usuario usuario) {

        String sql =
            "INSERT INTO cadastros " +
            "(nome, matricula, endereco, data_nasc, email, senha) " +
            "VALUES (?, ?, ?, ?, ?, ?)";

        try (
            Connection conn =
                ConnectionFactory.getConnection();

            PreparedStatement stmt =
                conn.prepareStatement(sql)
        ) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getMatricula());
            stmt.setString(3, usuario.getEndereco());
            stmt.setDate(4, usuario.getDataNasc());
            stmt.setString(5, usuario.getEmail());
            stmt.setString(6, usuario.getSenha());

            stmt.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}