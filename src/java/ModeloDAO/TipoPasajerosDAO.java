/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;

import Config.Conexion;
import Modelo.TipoPasajeros;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author denni
 */
public class TipoPasajerosDAO {
    Connection conexion;
    PreparedStatement ps;
    ResultSet rs;
    
    
    public TipoPasajerosDAO() throws ClassNotFoundException{
        Conexion n = new Conexion();
        conexion = n.getConexion();
    }
    
    public List<TipoPasajeros> listadoRegistros(){
        List<TipoPasajeros> listado = new ArrayList<>();
        String sql = "SELECT * FROM tipopasajero";
        try{
            
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                TipoPasajeros tp = new TipoPasajeros();
                tp.setId(rs.getInt("id"));
                tp.setNombre(rs.getString("Nombre"));
                
                listado.add(tp);
            }
            
            
        }catch(SQLException e){
            System.out.println("***** LISTADO REGISTROS *****");
            return null;
        }
        
        return listado;
    }
    
    public List<TipoPasajeros> filtrarRegistro(TipoPasajeros tp){
        List<TipoPasajeros> registro = new ArrayList<>();
        
        for(TipoPasajeros i : this.listadoRegistros()){
            if(i.getNombre().equals(tp.getNombre())){
                registro.add(i);
                break;
            }
        }
        
        return registro;
    }
}
