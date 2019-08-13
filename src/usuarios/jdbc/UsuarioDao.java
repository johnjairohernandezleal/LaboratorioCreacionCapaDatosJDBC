
package usuarios.jdbc;

import java.sql.*;
import java.util.*;
import usuarios.dto.UsuarioDTO;
public interface UsuarioDao {
    
    public int insert(UsuarioDTO usuarioDTO) throws SQLException;
    
    public int update(UsuarioDTO usuarioDTO) throws SQLException;
    
    public int delete(UsuarioDTO usuarioDTO) throws SQLException;
    
    public List<UsuarioDTO> select() throws SQLException;
    
}
