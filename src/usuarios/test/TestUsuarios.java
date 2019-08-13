
package usuarios.test;

import java.sql.*;
import java.util.*;
import usuarios.dto.*;
import usuarios.jdbc.*;


public class TestUsuarios {

   
    public static void main(String[] args) {
        // utilizamos el tipo interfaz como referencia a una clase concreta
        
        UsuarioDao usuarioDao = new UsuarioDaoJDBC();
       /**
        * creamos un registro
        * hacemos uso de la clase UsuarioDto la cual se usa para transferir informacion entre capas
        * no es necesario especificar la llave primaria
        * ya que es un campo de autoincremento y la base de datos se encarga de asignarle el valor
        */
       
       UsuarioDTO usuario = new UsuarioDTO();
       usuario.setUsuario("johnja");
       usuario.setPassword("123456");
       /**
        * usamos la capa Dao para persistir el objeto DTO
        */
        try {
            //usuarioDao.insert(usuario);
           // usuarioDao.delete(new UsuarioDTO(4));
              /**
               * actualizamos un registro
               */
              UsuarioDTO usuarioTmp = new  UsuarioDTO();
              usuarioTmp.setId_usuario(2);
              usuarioTmp.setUsuario("lukas");
              usuarioTmp.setPassword("1234");
              usuarioDao.update(usuarioTmp);
              
              /**
               * seleccionar los registros 
               */
              List<UsuarioDTO> usuarios = usuarioDao.select();
              for(UsuarioDTO usuarioDTO: usuarios){
                  System.out.println(usuarios);
                  System.out.println();
              }
              
              
        } catch (SQLException e) {
            
            System.out.println("error en la capa de prueba");
            e.printStackTrace();
        }
    }
    
}
