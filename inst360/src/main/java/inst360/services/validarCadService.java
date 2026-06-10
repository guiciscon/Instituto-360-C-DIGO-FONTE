package inst360.services;
import java.util.List;

import inst360.dao.UsuarioDAO;
import inst360.model.Usuario;

public class validarCadService {
    private UsuarioDAO dao = new UsuarioDAO();

    public List<Usuario> listarUsuarios(){
        return dao.listarUsuarios();
    }

    public boolean validarUsuario(int id){
        return dao.validarUsuario(id);
    }
    
    public boolean invalidarUsuario(int id){
        return dao.invalidarUsuario(id);
    }
}
