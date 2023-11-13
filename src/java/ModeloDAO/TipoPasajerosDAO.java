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
    private Connection conexion;
    private PreparedStatement ps;
    private ResultSet rs;
    
    
    public TipoPasajerosDAO() throws ClassNotFoundException{
        Conexion n = new Conexion();
        this.setConexion(n.getConexion());
    }
    
    public List<TipoPasajeros> listadoRegistros(){
        try{
            List<TipoPasajeros> listado = new ArrayList<>();
            String sql = "SELECT * FROM tipopasajero";
            this.setPS(this.getConexion().prepareStatement(sql));
            this.setRS(this.getPS().executeQuery());
           
            while(this.getRS().next()){
                TipoPasajeros tp = new TipoPasajeros();
                tp.setId(this.getRS().getInt("id"));
                tp.setNombre(this.getRS().getString("Nombre"));
                
                listado.add(tp);
            }
            
            return listado;
        }catch(SQLException e){
            System.out.println("***** LISTADO REGISTROS *****");
            return null;
        }
    }
    
    public TipoPasajeros listadoRegistro(int id){
        try{
            List<TipoPasajeros> listado = new ArrayList<>();
            
            String sql = "SELECT * FROM tipopasajero WHERE id = ?";
       
            this.setPS(this.getConexion().prepareStatement(sql));
            this.getPS().setInt(1, id);
            this.setRS(this.getPS().executeQuery());
            
            while(this.getRS().next()){
                TipoPasajeros tp = new TipoPasajeros();
                tp.setId(this.getRS().getInt("id"));
                tp.setNombre(this.getRS().getString("Nombre"));
                
                listado.add(tp);
            }
            
            return listado.get(0);
        }catch(SQLException e){
            System.out.println("***** ERROR EN LA CONSULTA *****");
            return null;
        }
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
    
    public void setConexion(Connection conexion){
        this.conexion = conexion;
    }
    public Connection getConexion(){
        return conexion;
    }
    
    /* Preparacion y Resultado */
    public void setPS(PreparedStatement ps){
        this.ps = ps;
    }
    public PreparedStatement getPS(){
        return ps;
    }
    
    public void setRS(ResultSet rs){
        this.rs = rs;
    }
    public ResultSet getRS(){
        return rs;
    }
}
