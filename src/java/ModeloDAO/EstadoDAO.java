/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;
import Interfaces.EstadoInterface;
import Modelo.Estado;
import Config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author denni
 */
public class EstadoDAO implements EstadoInterface{
    
    PreparedStatement ps;
    ResultSet rs;
    Connection conexion;
    
    public EstadoDAO() throws ClassNotFoundException{
        Conexion con = new Conexion();
        conexion = con.getConexion();
    }
    
    @Override
    public List<Estado> listarEstados(){
        List<Estado> estados = new ArrayList<>();
        
        String cmd = "SELECT * FROM estado";
        try{
            ps = conexion.prepareStatement(cmd);
            rs = ps.executeQuery();
            
            while(rs.next()){
                Estado estado = new Estado();
                estado.setId(rs.getInt("id"));
                estado.setUbicacionPais(rs.getInt("ubicacionpaisid"));
                estado.setEstadoNombre(rs.getString("estadonombre"));
                
                estados.add(estado);
            }
 
            return estados;
        }catch(SQLException e){
            return null;
        }
    }
}
