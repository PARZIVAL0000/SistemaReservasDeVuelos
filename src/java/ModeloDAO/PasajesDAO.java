/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;

import Config.Conexion;
import Modelo.Pasajes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Interfaces.PasajesInterface;

/**
 *
 * @author denni
 */
public class PasajesDAO implements PasajesInterface{
    Connection conexion;
    PreparedStatement ps;
    ResultSet rs;
    
    
    public PasajesDAO() throws ClassNotFoundException{
        Conexion n = new Conexion();
        conexion = n.getConexion();
    }
    
    @Override
    public List<Pasajes> listarPasajes(){
        List<Pasajes> listadoPasajes = new ArrayList<>();
        String cmd = "SELECT * FROM pasajes";
        try{
            ps = conexion.prepareStatement(cmd);
            rs = ps.executeQuery();
            
            while(rs.next()){
                Pasajes vl = new Pasajes();
                vl.setId(rs.getInt("pasaje_id"));
                vl.setTarifa(rs.getDouble("tarifa"));
               
                
                listadoPasajes.add(vl);
            }
            
            return listadoPasajes;
        }catch(SQLException e){
            System.out.println("Dentro del listado de pasajes");
            System.out.println("Error: " + e.getMessage());
            System.out.println("Causa: " + e.getCause());
            System.out.println("Estado: " + e.getSQLState());
            return null;
        }
        
    }
    
    @Override
    public List<Pasajes> filtrarRegistro(Pasajes p){
        List<Pasajes> registro = new ArrayList<>();
        
        for(Pasajes j : this.listarPasajes()){
            if(j.getTarifa() == p.getTarifa()){
                registro.add(j);
                break;
            }
        }
        
        return registro;
    }
}
