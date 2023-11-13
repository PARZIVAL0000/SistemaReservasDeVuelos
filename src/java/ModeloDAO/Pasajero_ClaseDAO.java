package ModeloDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Config.Conexion;
import Interfaces.Pasajero_ClaseInterface;
import Modelo.Pasajero_Clase;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author denni
 */
public class Pasajero_ClaseDAO implements Pasajero_ClaseInterface{
    private Connection conexion;
    private PreparedStatement ps;
    private ResultSet rs;

    
    public Pasajero_ClaseDAO() throws ClassNotFoundException{
        Conexion cn = new Conexion();
        this.setConexion(cn.getConexion());
    }
    
    @Override
    public List<Pasajero_Clase> listarPasajerosClases() {
        try{
            List<Pasajero_Clase> registros = new ArrayList<>();
            String sql = "SELECT * FROM pasajero_clase";
            this.setPs(this.getConexion().prepareStatement(sql));
            this.setRs(this.getPs().executeQuery());
            
            while(this.getRs().next()){
                Pasajero_Clase pasajero = new Pasajero_Clase();
                pasajero.setClaseId(this.getRs().getInt("pasajero_clase_id"));
                pasajero.setPasajeroId(this.getRs().getInt("pasajeroId"));
                pasajero.setPasajero_clase_id(this.getRs().getInt("claseId"));
                
                registros.add(pasajero);
            }
            
            return registros;
        }catch(SQLException e){
            this.message(e);
            return null;
        }
    }

    @Override
    public List<Pasajero_Clase> listarPasajeroClase(int pasajeroClaseId) {
        try{
            List<Pasajero_Clase> registros = new ArrayList<>();
            String sql = "SELECT * FROM pasajero_clase WHERE pasajero_clase_id = ?";
            this.setPs(this.getConexion().prepareStatement(sql));
            this.getPs().setInt(1, pasajeroClaseId);
            
            this.setRs(this.getPs().executeQuery());
            
            while(this.getRs().next()){
                Pasajero_Clase pasajero = new Pasajero_Clase();
                pasajero.setClaseId(this.getRs().getInt("pasajero_clase_id"));
                pasajero.setPasajeroId(this.getRs().getInt("pasajeroId"));
                pasajero.setPasajero_clase_id(this.getRs().getInt("claseId"));
                
                registros.add(pasajero);
            }
            
            return registros;
        }catch(SQLException e){
            this.message(e);
            return null;
        }
    }

    @Override
    public boolean eliminarPasajeroClase(int pasajeroClaseId) {
        try{
            String sql = "DELETE FROM pasajero_clase WHERE pasajero_clase_id = ?";
            this.setPs(this.getConexion().prepareStatement(sql));
            this.getPs().setInt(1, pasajeroClaseId);
            this.getPs().execute();
            
            return true;
        }catch(SQLException e){
            this.message(e);
            return false;
        }
    }

    @Override
    public boolean actualizarPasajeroClase(Pasajero_Clase pasajeroClase) {
        try{
            String sql = "UPDATE pasajero_clase SET pasajeroId = ?, claseId = ? WHERE pasajero_clase_id = ?";
            this.setPs(this.getConexion().prepareStatement(sql));
           
            this.getPs().setInt(1, pasajeroClase.getPasajeroId());
            this.getPs().setInt(2, pasajeroClase.getClaseId());
            this.getPs().setInt(3, pasajeroClase.getPasajero_clase_id());
            this.getPs().execute();
            
            return true;
        }catch(SQLException e){
            this.message(e);
            return false;
        }
    }

    @Override
    public boolean crearPasajeroClase(List<Pasajero_Clase> pasajeroClase) {
        try{
            String sql = "INSERT INTO pasajero_clase(pasajeroId, claseId) VALUES(?, ?)";
            this.setPs(this.getConexion().prepareStatement(sql));
            
            for(Pasajero_Clase pc : pasajeroClase){
                this.getPs().setInt(1, pc.getPasajeroId());
                this.getPs().setInt(2, pc.getClaseId());
                this.getPs().execute();
            }
            
            return true;
        }catch(SQLException e){
            this.message(e);
            return false;
        }
    }
    
    public void message(SQLException e){
        System.out.println("***** ERROR EN LA CONEXION A LA BASE DE DATOS *****");
        System.out.println("* Mensaje: " + e.getMessage());
        System.out.println("* Error: " + e.getErrorCode());
        System.out.println("* Estado" + e.getSQLState());
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
