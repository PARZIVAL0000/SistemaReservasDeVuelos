package ModeloDAO;

import Config.Conexion;

import Modelo.Pasajeros_Origen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;

import Interfaces.Pasajeros_OrigenInterface;
/**
 *
 * @author denni
 */
public class Pasajeros_OrigenDAO implements Pasajeros_OrigenInterface{
    
    private Connection conexion = null;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public Pasajeros_OrigenDAO() throws ClassNotFoundException{
        Conexion con = new Conexion();
        conexion = con.getConexion();
    }
    
    @Override
    public List<Pasajeros_Origen> listadoPasajerosOrigen() {
        try{
            List<Pasajeros_Origen> registros = new ArrayList<>();
            
            String sql = "SELECT * FROM pasajero_origen";
            this.setPS(sql);
            this.setRS(this.getPS());
            
            while(this.getRS().next()){
                Pasajeros_Origen pasajeroOrigen = new Pasajeros_Origen();
                pasajeroOrigen.setPasajeroOrigenId(this.getRS().getInt("pasajero_origen_id"));
                pasajeroOrigen.setOrigenId(this.getRS().getInt("origen_id"));
                pasajeroOrigen.setPasajeroId(this.getRS().getInt("pasajero_id"));
                
                registros.add(pasajeroOrigen);
            }
            
            return registros;
        }catch(SQLException e){
            this.mensajeAlerta(e);
            return null;
        }
    }

    @Override
    public List<Pasajeros_Origen> listadoPasajeroOrigen(int pasajeroOrigenId) {
        try{
            List<Pasajeros_Origen> registros = new ArrayList<>();
            
            String sql = "SELECT * FROM pasajero_origen WHERE pasajero_origen_id = ?";
            this.setPS(sql, pasajeroOrigenId);
            this.setRS(this.getPS());
            
            while(this.getRS().next()){
                Pasajeros_Origen pasajeroOrigen = new Pasajeros_Origen();
                pasajeroOrigen.setPasajeroOrigenId(this.getRS().getInt("pasajero_origen_id"));
                pasajeroOrigen.setOrigenId(this.getRS().getInt("origen_id"));
                pasajeroOrigen.setPasajeroId(this.getRS().getInt("pasajero_id"));
                
                registros.add(pasajeroOrigen);
            }
            
            return registros;
        }catch(SQLException e){
            this.mensajeAlerta(e);
            return null;
        }
    }

    @Override
    public boolean actualizarPasajeroOrigen(Pasajeros_Origen pasajeroOrigen) {
        try{
            String sql = "UPDATE pasajero_origen SET origen_id = ?, pasajero_id = ? WHERE pasajero_origen_id = ?";
            PreparedStatement ps = this.getConexion().prepareStatement(sql);
            ps.setInt(1, pasajeroOrigen.getOrigenId());
            ps.setInt(2, pasajeroOrigen.getPasajeroId());
            ps.setInt(3, pasajeroOrigen.getPasajeroOrigenId());
            
            boolean resultado = ps.execute();
            
            return resultado;
        }catch(SQLException e){
            this.mensajeAlerta(e);
            return false;
        }
    }

    @Override
    public boolean eliminarPasajeroOrigen(int pasajeroOrigenId) {
        try{
            String sql = "DELETE FROM pasajero_origen WHERE pasajero_origen_id = ?";
            this.setPS(sql, pasajeroOrigenId);
            this.setRS(this.getPS(), true);
           
            return true;
        }catch(SQLException e){
            this.mensajeAlerta(e);
            return false;
        }
    }

    @Override
    public boolean crearPasajeroOrigen(List<Pasajeros_Origen> pasajeros) {
        try{
            String sql = "INSERT INTO pasajeros_origen(origen_id, pasajero_id) VALUES(?, ?)";
            PreparedStatement ps = this.getConexion().prepareStatement(sql);
            
            for(Pasajeros_Origen po : pasajeros){
                ps.setInt(1, po.getOrigenId());
                ps.setInt(2, po.getPasajeroId());
                ps.execute();
            }
            
            return true;
        }catch(SQLException e){
            this.mensajeAlerta(e);
            return false;
        }
    }
    
    
    /* Mensaje alerta */
    public void mensajeAlerta(SQLException e){
        System.out.println("***** ERROR EN LA CONEXION CON LA BASE DE DATOS *****");
        System.out.println("* Mensaje: " + e.getMessage());
        System.out.println("* Estado: " + e.getSQLState());
        System.out.println("* Codigo: " + e.getErrorCode());
    }
    
    /* Establecer la conexion */
    
    public void setConexion(Connection conexion){
        this.conexion = conexion;
    }
    public Connection getConexion(){
        return conexion;
    }

    /* Preparacion y Resultado */
    public void setPS(String sql) throws SQLException{
        this.ps = this.getConexion().prepareStatement(sql);
    }
    
    public void setPS(String sql, int pasajeroOrigenId) throws SQLException{
        this.ps = this.getConexion().prepareStatement(sql);
        this.ps.setInt(1, pasajeroOrigenId);
    }
    
    public PreparedStatement getPS(){
        return ps;
    }
    
    public void setRS(PreparedStatement ps) throws SQLException{
        this.rs = ps.executeQuery();
    }
    
    public void setRS(PreparedStatement ps, boolean flag) throws SQLException{
        if(flag){
            ps.execute();
        }
    }
    
    public ResultSet getRS(){
        return rs;
    }

}
