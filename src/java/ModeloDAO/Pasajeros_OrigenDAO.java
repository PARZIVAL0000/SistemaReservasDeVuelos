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
        this.setConexion(con.getConexion());
    }
    
    @Override
    public List<Pasajeros_Origen> listadoPasajerosOrigen() {
        try{
            List<Pasajeros_Origen> registros = new ArrayList<>();
            String sql = "SELECT * FROM pasajero_origen";
            
            this.setPs(this.getConexion().prepareStatement(sql));
            this.setRs(this.getPs().executeQuery());
            
            while(this.getRs().next()){
                Pasajeros_Origen pasajeroOrigen = new Pasajeros_Origen();
                pasajeroOrigen.setPasajeroOrigenId(this.getRs().getInt("pasajero_origen_id"));
                pasajeroOrigen.setOrigenId(this.getRs().getInt("origen_id"));
                pasajeroOrigen.setPasajeroId(this.getRs().getInt("pasajero_id"));
                
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
            this.setPs(this.getConexion().prepareStatement(sql));
            this.getPs().setInt(1, pasajeroOrigenId);
            this.setRs(this.getPs().executeQuery());
            
            while(this.getRs().next()){
                Pasajeros_Origen pasajeroOrigen = new Pasajeros_Origen();
                pasajeroOrigen.setPasajeroOrigenId(this.getRs().getInt("pasajero_origen_id"));
                pasajeroOrigen.setOrigenId(this.getRs().getInt("origen_id"));
                pasajeroOrigen.setPasajeroId(this.getRs().getInt("pasajero_id"));
                
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
            this.setPs(this.getConexion().prepareStatement(sql));
            
            this.getPs().setInt(1, pasajeroOrigen.getOrigenId());
            this.getPs().setInt(2, pasajeroOrigen.getPasajeroId());
            this.getPs().setInt(3, pasajeroOrigen.getPasajeroOrigenId());
            
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
            this.setPs(this.getConexion().prepareStatement(sql));
            this.getPs().setInt(1, pasajeroOrigenId);
            
            this.getPs().execute();
           
            return true;
        }catch(SQLException e){
            this.mensajeAlerta(e);
            return false;
        }
    }

    @Override
    public boolean crearPasajeroOrigen(Pasajeros_Origen pasajeros) {
        try{
            String sql = "INSERT INTO pasajero_origen(origen_id, pasajero_id) VALUES(?, ?)";
            this.setPs(this.getConexion().prepareStatement(sql)); 
            this.getPs().setInt(1, pasajeros.getOrigenId());
            this.getPs().setInt(2, pasajeros.getPasajeroId());
            this.getPs().execute();
            
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
    public PreparedStatement getPs() {
        return ps;
    }

    public void setPs(PreparedStatement ps) {
        this.ps = ps;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

}
