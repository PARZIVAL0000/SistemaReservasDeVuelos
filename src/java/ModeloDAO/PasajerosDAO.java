package ModeloDAO;

import Config.Conexion;

import Interfaces.PasajerosInterface;
import Modelo.Pasajeros;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author denni
 */
public class PasajerosDAO implements PasajerosInterface{
    private Connection conexion;
    private PreparedStatement ps;
    private ResultSet rs; 
    
    public PasajerosDAO() throws ClassNotFoundException {
        Conexion cn = new Conexion();
        this.setConexion(cn.getConexion());
    }
    
    @Override
    public List<Pasajeros> listarPasajeros() {
        try{
            List<Pasajeros> listadoPasajeros = new ArrayList<>();
            String sql = "SELECT * FROM pasajeros";
            this.setPs(this.getConexion().prepareStatement(sql));
            this.setRs(this.getPs().executeQuery());
            
            while(this.getRs().next()){
                Pasajeros pasajero = new Pasajeros();
                pasajero.setPasajeros_id(0);
                pasajero.setPasajeros_nombre(this.getRs().getString("pasajeros_nombre"));    
                pasajero.setPasajeros_apellido(this.getRs().getString("pasajeros_apellido"));
                pasajero.setPasajeros_correo(this.getRs().getString("pasajeros_correo"));
                pasajero.setPasajeros_celular(this.getRs().getString("pasajeros_celular"));
                pasajero.setPasajeros_cedula(this.getRs().getString("pasajeros_cedula"));
                pasajero.setTipoPasajeroId(this.getRs().getInt("tipoPasajeroId"));
                pasajero.setPrecioVueloFinalId(this.getRs().getInt("precioVueloFinalId"));
                
                listadoPasajeros.add(pasajero);
            }
            
            return listadoPasajeros;
        }catch(SQLException e){
            this.mensaje(e);
            return null;
        }
       
    }

    @Override
    public List<Pasajeros> listarPasajero(int pasajero_id) {
        try{
            List<Pasajeros> listadoPasajeros = new ArrayList<>();
            String sql = "SELECT * FROM pasajeros WHERE pasajeros.pasajeros_id = ?";
            this.setPs(this.getConexion().prepareStatement(sql));
            this.getPs().setInt(1, pasajero_id);
            this.setRs(this.getPs().executeQuery());
            
            while(this.getRs().next()){
                Pasajeros pasajero = new Pasajeros();
                pasajero.setPasajeros_id(0);
                pasajero.setPasajeros_nombre(this.getRs().getString("pasajeros_nombre"));    
                pasajero.setPasajeros_apellido(this.getRs().getString("pasajeros_apellido"));
                pasajero.setPasajeros_correo(this.getRs().getString("pasajeros_correo"));
                pasajero.setPasajeros_celular(this.getRs().getString("pasajeros_celular"));
                pasajero.setPasajeros_cedula(this.getRs().getString("pasajeros_cedula"));
                pasajero.setTipoPasajeroId(this.getRs().getInt("tipoPasajeroId"));
                pasajero.setPrecioVueloFinalId(this.getRs().getInt("precioVueloFinalId"));
                
                listadoPasajeros.add(pasajero);
            }
            
            return listadoPasajeros;
        }catch(SQLException e){
            this.mensaje(e);
            return null;
        }
    }

    @Override
    public boolean eliminarPasajero(int pasajero_id) {
        try{
            String sql = "DELETE FROM pasajeros WHERE pasajeros_id = ?";
            
            this.setPs(this.getConexion().prepareStatement(sql));
            this.getPs().setInt(1, pasajero_id);
            this.getPs().execute();
                        
            return true;
        }catch(SQLException e){
            this.mensaje(e);
            return false;
        }
    }

    @Override
    public boolean actualizarPasajero(Pasajeros pasajero) {
        try{
            String sql = "UPDATE pasajeros SET pasajeros_nombre = ?, pasajeros_apellido = ?, pasajeros_correo = ?, pasajeros_celular = ?, pasajeros_cedula = ?, tipoPasajeroId = ?, precioVueloFinalId = ? WHERE pasajeros_id = ?";
            this.setPs(this.getConexion().prepareStatement(sql));
           
            this.getPs().setString(1, pasajero.getPasajeros_nombre());
            this.getPs().setString(2, pasajero.getPasajeros_apellido());
            this.getPs().setString(3, pasajero.getPasajeros_correo());
            this.getPs().setString(4, pasajero.getPasajeros_celular());
            this.getPs().setString(5, pasajero.getPasajeros_cedula());
            this.getPs().setInt(6, pasajero.getTipoPasajeroId());
            this.getPs().setInt(8, pasajero.getPrecioVueloFinalId());
            this.getPs().setInt(9, pasajero.getPasajeros_id());
            
            boolean resultado = this.getPs().execute();
            
            return resultado;
        }catch(SQLException e){
            this.mensaje(e);
            return false;
        }
    }

    @Override
    public boolean crearPasajero(List<Pasajeros> pasajero) {
        try{
            for(Pasajeros p : pasajero){
                String sql = "";
                if(p.getTipoPasajeroId() != 3){
                    sql = "INSERT INTO pasajeros(pasajeros_nombre, pasajeros_apellido, pasajeros_correo, pasajeros_celular, pasajeros_cedula, tipoPasajeroId, precioVueloFinalId) VALUES(?, ?, ?, ?, ?, ?, ?)";
                }else{
                    sql = "INSERT INTO pasajeros(pasajeros_nombre, pasajeros_apellido, pasajeros_cedula, tipoPasajeroId, precioVueloFinalId) VALUES(?, ?, ?, ?, ?)";
                }
                this.setPs(this.getConexion().prepareStatement(sql));
            
                this.getPs().setString(1, p.getPasajeros_nombre());
                this.getPs().setString(2, p.getPasajeros_apellido());
                
                if(p.getTipoPasajeroId() != 3){
                    this.getPs().setString(3, p.getPasajeros_correo());
                    this.getPs().setString(4, p.getPasajeros_celular());
                    this.getPs().setString(5, p.getPasajeros_cedula());
                    this.getPs().setInt(6, p.getTipoPasajeroId());
                    this.getPs().setInt(7, p.getPrecioVueloFinalId());
                }else{
                    this.getPs().setString(3, p.getPasajeros_cedula());
                    this.getPs().setInt(4, p.getTipoPasajeroId());
                    this.getPs().setInt(5, p.getPrecioVueloFinalId());
                }
                this.getPs().execute();
            }
            
            return true;
        }catch(SQLException e){ 
            this.mensaje(e);
            return false;
        }
    }
    
    public Pasajeros BuscarPorCedula(String cedula){
        try{
            String sql = "SELECT * FROM pasajeros WHERE pasajeros_cedula = ?";
            this.setPs(this.getConexion().prepareStatement(sql));
            this.getPs().setString(1, cedula);
            this.setRs(this.getPs().executeQuery());
            Pasajeros pasajero = new Pasajeros();
            while(this.getRs().next()){
                pasajero.setPasajeros_id(this.getRs().getInt("pasajeros_id"));
            }
            return pasajero;
        }catch(SQLException e){
            this.mensaje(e);
            return null;
        }
    }
    
    public Pasajeros BuscarPorCorreo(String correo){
        try{
            String sql = "SELECT * FROM pasajeros WHERE pasajeros_correo = ?";
            this.setPs(this.getConexion().prepareStatement(sql));
            this.getPs().setString(1, correo);
            this.setRs(this.getPs().executeQuery());
            Pasajeros pasajero = new Pasajeros();
            while(this.getRs().next()){
                pasajero.setPasajeros_id(this.getRs().getInt("pasajeros_id"));
            }
            return pasajero;
        }catch(SQLException e){
            this.mensaje(e);
            return null;
        }
    }
    
    public void mensaje(SQLException e){
        System.out.println("***** ERROR *****");
        System.out.println("* Mensaje Error: " + e.getMessage());
        System.out.println("* Codigo Error: " + e.getErrorCode());
        System.out.println("* Estado Error: " + e.getSQLState());
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
    
    
    public void setConexion(Connection cn){
        this.conexion = cn;
    }
    public Connection getConexion(){
        return conexion;
    }
    
}
