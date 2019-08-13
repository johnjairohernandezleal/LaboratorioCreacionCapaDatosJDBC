
package usuarios.jdbc;
import java.sql.*;
import java.util.*;
import usuarios.dto.UsuarioDTO;

/**
 * esta clase implementa la clase UsuarioDao, es una implementacion con la tecnologia JDBC podria 
 * haber otro tipo de implementaciones con tecnologias como Hibernate, iBAtis, SpringJDBC, etc
 * @author john hernandez
 */
public class UsuarioDaoJDBC implements UsuarioDao{
    
     private  Connection userConn;
    
    private final String SQL_INSERT ="insert into usuario (usuario,password) values (?,?)";
    
    private final String SQL_UPDATE ="UPDATE usuario SET usuario=?,password=? WHERE id_usuario=?";
    
    private final String SQL_DELETE ="DELETE FROM usuario where id_usuario =?";
    
    private final String SQL_SELECT ="SELECT id_usuario,usuario,password FROM usuario ORDER BY id_usuario";

    public UsuarioDaoJDBC() {
    }

    public UsuarioDaoJDBC(Connection conn) {
        this.userConn = conn;
    }
    
    @Override
    public int insert(UsuarioDTO usuarioDTO) throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            conn = (this.userConn!=null)? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            int index = 1;
            stmt.setString(index++, usuarioDTO.getUsuario());
            stmt.setString(index, usuarioDTO.getPassword());
            rows = stmt.executeUpdate();
            System.out.println("ejecutando query:" + SQL_INSERT);
            System.out.println("registros afectados: " + rows );
        } finally {
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
                
            }
        }
        return rows;
    }
    
    public  int update(UsuarioDTO usuarioDTO)throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = (this.userConn!=null)? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            int index = 1;
            
            stmt.setString(index++, usuarioDTO.getUsuario());
            stmt.setString(index++, usuarioDTO.getPassword());
            stmt.setInt(index, usuarioDTO.getId_usuario());
            rows = stmt.executeUpdate();
            System.out.println("registros afectados:" + rows);
            
        } finally {
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
                
            }
        }
        return rows;
    }
    
    public int delete(UsuarioDTO usuarioDTO) throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = (this.userConn!=null)? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, usuarioDTO.getId_usuario());
            rows = stmt.executeUpdate();
            System.out.println("registros eliminados :" + rows);
        } finally {
            Conexion.close(stmt);
            if (this.userConn==null) {
                Conexion.close(conn);
                
            }
        }
        return rows;
    }
    
    public List<UsuarioDTO> select() throws SQLException{
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        UsuarioDTO usuarioDTO1 = null;
        List<UsuarioDTO> usuario = new ArrayList<UsuarioDTO>();
        
        try {
            conn = (this.userConn!=null)? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
              int idusuarioTemp = rs.getInt(1);
              String usuarioTemp = rs.getString(2);
              String passwordTemp = rs.getString(3);
              
              usuarioDTO1 = new  UsuarioDTO();
              usuarioDTO1.setId_usuario(idusuarioTemp);
              usuarioDTO1.setUsuario(usuarioTemp);
              usuarioDTO1.setPassword(passwordTemp);
              usuario.add(usuarioDTO1);
              
                
            }
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            if (this.userConn ==null) {
                Conexion.close(conn);
                
            }
        }
        return usuario;
    }
    
    
    
    
}
