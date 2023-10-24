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
            this.setPS(sql);
            this.setRS(this.getPS());
            
            while(this.getRS().next()){
                ResultSet r = this.getRS();
                Pasajero_Aerolinea pasajeroAerolinea = new Pasajero_Aerolinea(r.getInt("pasajeros_aerolineas_id"), r.getInt("aerolineaId"), r.getInt("pasajeroId"));
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
            this.setPS(sql, pasajerosAerolineaId);
            this.setRS(this.getPS());
            
            while(this.getRS().next()){
                ResultSet r = this.getRS();
                Pasajero_Aerolinea pasajeroAerolinea = new Pasajero_Aerolinea(r.getInt("pasajeros_aerolineas_id"), r.getInt("aerolineaId"), r.getInt("pasajeroId"));
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
            this.setPS(sql, pasajerosAerolineaId);
            this.setRS(this.getPS(), true);
            
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
            PreparedStatement ps = this.getConexion().prepareStatement(sql);
            ps.setInt(1, pasajero_aerolinea.getAerolineaId());
            ps.setInt(2, pasajero_aerolinea.getPasajeroId());
            ps.setInt(3, pasajero_aerolinea.getParajerosAerolineasId());
            
            ps.execute();
            
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
            PreparedStatement ps = this.getConexion().prepareStatement(sql);
            
            for(Pasajero_Aerolinea j : pasajeros_aerolineas){
                ps.setInt(1, j.getAerolineaId());
                ps.setInt(2, j.getPasajeroId());
                
                ps.execute();
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
    
    /* Conexion. */
    public void setConexion(Connection conexion){
        this.conexion = conexion;
    }
    public Connection getConexion(){
        return conexion;
    }
    
    
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
