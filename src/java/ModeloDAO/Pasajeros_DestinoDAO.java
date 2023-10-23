/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;
import Config.Conexion;
import java.util.List;
import java.sql.Connection;
import Modelo.Pasajeros_Destino;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Interfaces.Pasajeros_DestinoInterface;
import java.util.ArrayList;
/**
 *
 * @author denni
 */
public class Pasajeros_DestinoDAO implements Pasajeros_DestinoInterface{
    
    private Connection conexion;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public Pasajeros_DestinoDAO() throws ClassNotFoundException{
        Conexion cn = new Conexion();
        this.setConexion(cn.getConexion());
    }
    
    
    @Override
    public List<Pasajeros_Destino> listarPasajerosDestino() {
        try{
            List<Pasajeros_Destino> registro = new ArrayList<>();
            String sql = "SELECT * FROM pasajero_destino";
            this.setPS(sql);
            this.setRS(this.getPS());
            
            while(this.getRS().next()){
                Pasajeros_Destino pasajeroDestino = new Pasajeros_Destino();
                pasajeroDestino.setPasajeroDestinoId(this.getRS().getInt("pasajero_destino_id"));
                pasajeroDestino.setDestinoId(this.getRS().getInt("destino_id"));
                pasajeroDestino.setPasajeroId(this.getRS().getInt("pasajero_id"));
                
                registro.add(pasajeroDestino);
            }
            
            return registro;
        }catch(SQLException e){
            this.obtenerMensaje(e);
            return null;
        }
    }
    

    @Override
    public List<Pasajeros_Destino> listarPasajeroDestino(int pasajeroDestinoId) {
        try{
            List<Pasajeros_Destino> registro = new ArrayList<>();
            String sql = "SELECT * FROM pasajero_destino WHERE pasajero_destino_id = ?";
            this.setPS(sql, pasajeroDestinoId);
            this.setRS(this.getPS());
            
            while(this.getRS().next()){
                Pasajeros_Destino pasajeroDestino = new Pasajeros_Destino();
                pasajeroDestino.setPasajeroDestinoId(this.getRS().getInt("pasajero_destino_id"));
                pasajeroDestino.setDestinoId(this.getRS().getInt("destino_id"));
                pasajeroDestino.setPasajeroId(this.getRS().getInt("pasajero_id"));
                
                registro.add(pasajeroDestino);
            }
            
            return registro;
        }catch(SQLException e){
            this.obtenerMensaje(e);
            return null;
        }
    }

    @Override
    public boolean actualizarPasajerosDestino(Pasajeros_Destino pasajeroDestino) {
        try{
            String sql = "UPDATE pasajero_destino SET destino_id = ?, pasajero_id = ? WHERE pasajero_destino_id = ?";
            PreparedStatement ps = this.getConexion().prepareStatement(sql);
            ps.setInt(1, pasajeroDestino.getDestinoId());
            ps.setInt(2, pasajeroDestino.getPasajeroId());
            ps.setInt(3, pasajeroDestino.getPasajeroDestinoId());
            
            ps.execute();
            
            return true;
        }catch(SQLException e){
            this.obtenerMensaje(e);
            return false;
        }
    }

    @Override
    public boolean eliminarPasajerosDestino(int pasajeroDestinoId) {
        try{
            String sql = "DELETE FROM pasajero_destino WHERE pasajero_destino_id = ?";
            this.setPS(sql, pasajeroDestinoId);
            this.setRS(this.getPS(), true);
            
            return true;
        }catch(SQLException e){
            this.obtenerMensaje(e);
            return false;
        }
    }

    @Override
    public boolean crearPasajerosDestino(List<Pasajeros_Destino> pasajeros) {
        try{
            String sql = "INSERT INTO pasajero_destino(destino_id, pasajero_id) VALUES(?, ?)";
            PreparedStatement ps = this.getConexion().prepareStatement(sql);
            
            for(Pasajeros_Destino pd : pasajeros){
         
                ps.setInt(1, pd.getDestinoId());
                ps.setInt(2, pd.getPasajeroId());
                ps.execute();
            }
            
            return true;
        }catch(SQLException e){
            this.obtenerMensaje(e);
            return false;
        }
    }
    
    /*Mensaje*/
    public void obtenerMensaje(SQLException e){
        System.out.println("****** ERROR CON LA BASE DE DATOS *****");
        System.out.println("* Mensaje: " + e.getMessage());
        System.out.println("Codigo: " + e.getErrorCode());
        System.out.println("Estado: " + e.getSQLState());
    }
    
    /*Conexion*/
    
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
    
    public void setPS(String sql, int pasajeroId) throws SQLException{
        this.ps = this.getConexion().prepareStatement(sql);
        this.ps.setInt(1, pasajeroId);
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
