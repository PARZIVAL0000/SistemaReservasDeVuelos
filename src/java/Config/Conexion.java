package Config;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author denni
 */
public class Conexion {
    
    public Connection getConexion() throws ClassNotFoundException{
        Connection conexion = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_rvuelos", "root", "dennis07@");
                    
            return conexion;
        }catch(SQLException e){
            System.out.println(e.getErrorCode());
            return null;
        }
    }
}
