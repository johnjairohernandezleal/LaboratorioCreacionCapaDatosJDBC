/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuarios.jdbc;

import java.sql.*;


public class Conexion {
    
    private static final String JDBC_DRIVER ="com.mysql.jdbc.Driver";
    private static final String JDBC_URL ="jdbc:mysql://localhost/sga?useSSL=false";
    private static final String JDBC_USER="root";
    private static  String JDBC_PASS="admin";
    private static  Driver driver = null;
    
    public static synchronized Connection getConnection() throws SQLException{
        if (driver == null) {
            try {
                Class jdbcDriverClass = Class.forName(JDBC_DRIVER);
                driver = (Driver) jdbcDriverClass.newInstance();
            } catch (Exception e) {
                System.out.println("error al cargar el driver");
                e.printStackTrace();
            }
            
        }
       
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
        
    }
    
    public static void close(ResultSet rs){
        try {
            if (rs!=null) {
                rs.close();
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void close(PreparedStatement stmt) {
        try {
            if (stmt!=null) {
                stmt.cancel();
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public static void close(Connection conn) {
        try {
            if (conn !=null) {
                conn.close();
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
}
