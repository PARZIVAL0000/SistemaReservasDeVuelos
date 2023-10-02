package com.example.sistemareservadevuelos.ModeloDAO;

import com.example.sistemareservadevuelos.Config.Conexion;
import com.example.sistemareservadevuelos.Modelo.Provincia;

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
public class ProvinciaDAO {
    
    PreparedStatement ps;
    ResultSet rs;
    Connection conexion;
    
    public ProvinciaDAO() throws ClassNotFoundException{
        Conexion con = new Conexion();
        conexion = con.getConexion();
    }
    
    
    public List<Provincia> listarRegistro(){
        String sql = "SELECT * FROM provincia";
        List<Provincia> registros = new ArrayList<>();
        
        try{
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                //vamos a extraernos los datos a continuacion.
                Provincia pv = new Provincia(rs.getInt("id"), rs.getString("nombre"));
                registros.add(pv);
            }
        }catch(SQLException e){
            System.out.println("***** ERROR EN LISTAR PROVINCIAS *****");
            System.out.println("Mensaje: " + e.getMessage());
            System.out.println("Error: " + e.getErrorCode());
            return null;
        }
        
        return registros;
    }
    
    
    public List<Provincia> filtrarRegistro(Provincia provincia){
        List<Provincia> registro = new ArrayList<>();
        
        for(Provincia j : this.listarRegistro()){
            if(j.getNombre().toLowerCase().equals(provincia.getNombre().toLowerCase()) || j.getId() == provincia.getId()){
                registro.add(j);
                break;
            }
        }
        
        return registro;
    }
    
    
}
