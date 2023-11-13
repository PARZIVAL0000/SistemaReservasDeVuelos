package ModeloDAO;

import Interfaces.Pasajero_AerolineaInterface;

import Modelo.Pasajero_Aerolinea;

import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Config.Conexion;
import java.util.ArrayList;
/**
 *
 * @author denni
 */
public class Pasajero_AerolineaDAO implements Pasajero_AerolineaInterface{
    private Connection conexion;
    private PreparedStatement ps;
    private ResultSet rs;

   
    public Pasajero_AerolineaDAO() throws ClassNotFoundException{
        Conexion con = new Conexion();
        this.setConexion(con.getConexion());
    }
    
    @Override
    public List<Pasajero_Aerolinea> listarPasajerosAerolineas() {
        try{
            List<Pasajero_Aerolinea> registros = new ArrayList<>();
            String sql = "SELECT * FROM pasajeros_aerolineas";
            this.setPs(this.getConexion().prepareStatement(sql));
            this.setRs(this.getPs().executeQuery());
            
            while(this.getRs().next()){
                Pasajero_Aerolinea pasajeroAerolinea = new Pasajero_Aerolinea(this.getRs().getInt("pasajeros_aerolineas_id"), 
                                                                                            this.getRs().getInt("aerolineaId"), 
                                                                                                this.getRs().getInt("pasajeroId"));
                registros.add(pasajeroAerolinea);
            }
            
            return registros;
        }catch(SQLException e){
            this.getMessage(e);
            return null;
        }
    }

    @Override
    public List<Pasajero_Aerolinea> listarPasajeroAerolinea(int pasajerosAerolineaId) {
        try{
            List<Pasajero_Aerolinea> registros = new ArrayList<>();
            String sql = "SELECT * FROM pasajeros_aerolineas WHERE pasajeros_aerolineas_id = ?";
            this.setPs(this.getConexion().prepareStatement(sql));
            this.getPs().setInt(1, pasajerosAerolineaId);
            this.setRs(this.getPs().executeQuery());
            
            while(this.getRs().next()){
                Pasajero_Aerolinea pasajeroAerolinea = new Pasajero_Aerolinea(this.getRs().getInt("pasajeros_aerolineas_id"), 
                                                                                            this.getRs().getInt("aerolineaId"), 
                                                                                                this.getRs().getInt("pasajeroId"));
                registros.add(pasajeroAerolinea);
            }
            
            return registros;
        }catch(SQLException e){
            this.getMessage(e);
            return null;
        }
    }

    @Override
    public boolean eliminarPasajeroAerolinea(int pasajerosAerolineaId) {
        try{
            String sql = "DELETE FROM pasajeros_aerolineas WHERE pasajeros_aerolineas_id = ?";
            this.setPs(this.getConexion().prepareStatement(sql));
            this.getPs().setInt(1, pasajerosAerolineaId);
            this.getPs().execute();
         
            return true;
        }catch(SQLException e){
            this.getMessage(e);
            return false;
        }
    }

    @Override
    public boolean actualizarPasajeroAerolinea(Pasajero_Aerolinea pasajero_aerolinea) {
        try{
            String sql = "UPDATE pasajeros_aerolineas SET aerolineaId = ?, pasajeroId = ? WHERE pasajeros_aerolineas_id = ?";
            this.setPs(this.getConexion().prepareStatement(sql));
           
            this.getPs().setInt(1, pasajero_aerolinea.getAerolineaId());
            this.getPs().setInt(2, pasajero_aerolinea.getPasajeroId());
            this.getPs().setInt(3, pasajero_aerolinea.getParajerosAerolineasId());
            
            this.getPs().execute();
            
            return true;
        }catch(SQLException e){
            this.getMessage(e);
            return false;
        }
    }

    @Override
    public boolean crearPasajeroAerolinea(List<Pasajero_Aerolinea> pasajeros_aerolineas) {
        try{
            String sql = "INSERT INTO pasajeros_aerolineas(aerolineaId, pasajeroId) VALUES(?, ?)";
            this.setPs(this.getConexion().prepareStatement(sql));
            
            for(Pasajero_Aerolinea j : pasajeros_aerolineas){
                this.getPs().setInt(1, j.getAerolineaId());
                this.getPs().setInt(2, j.getPasajeroId());
                
                this.getPs().execute();
            }
            return true;
        }catch(SQLException e){
            this.getMessage(e);
            return false;
        }
    }
    
    
    /* Mensaje */
    public void getMessage(SQLException e){
        System.out.println("***** ERROR EN LA CONEXION CON LA BASE DE DATOS *****");
        System.out.println("* Error: " + e.getErrorCode());
        System.out.println("* Mensaje: " + e.getMessage());
        System.out.println("* Estado: " + e.getSQLState());
    }
    
    /* Getter y Setter */
    
    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

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
