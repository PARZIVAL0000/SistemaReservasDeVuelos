/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;

import Config.Conexion;
import Modelo.Genero;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author denni
 */
public class GeneroDAO {
    Connection conexion;
    PreparedStatement ps;
    ResultSet rs;
    
    
    public GeneroDAO() throws ClassNotFoundException{
        Conexion n = new Conexion();
        conexion = n.getConexion();
    }
    
    public List<Genero> listadoRegistros(){
        List<Genero> listado = new ArrayList<>();
        String sql = "SELECT * FROM genero";
        try{
            
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Genero gn = new Genero();
                gn.setId(rs.getInt("id"));
                gn.setTipo(rs.getString("tipo"));
                
                listado.add(gn);
            }
            
            return listado;
            
        }catch(SQLException e){
            System.out.println("***** LISTADO GENERO *****");
            return null;
        }
        
    }
    
    public List<Genero> filtrarRegistro(Genero tp){
        List<Genero> registro = new ArrayList<>();
        
        for(Genero i : this.listadoRegistros()){
            
            if(i.getTipo().equals(tp.getTipo())){
                registro.add(i);
                break;
            }
        }
        
        return registro;
    }
}
