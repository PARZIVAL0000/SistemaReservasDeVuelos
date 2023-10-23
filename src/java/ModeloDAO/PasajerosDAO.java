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
            this.setPS(sql);
            this.setRS(this.getPS());
            
            while(this.getRS().next()){
                Pasajeros pasajero = new Pasajeros();
                pasajero.setPasajeros_id(0);
                pasajero.setPasajeros_nombre(this.getRS().getString("pasajeros_nombre"));
                pasajero.setPasajeros_apellido(this.getRS().getString("pasajeros_apellido"));
                pasajero.setPasajeros_correo(this.getRS().getString("pasajeros_correo"));
                pasajero.setPasajeros_celular(this.getRS().getString("pasajeros_celular"));
                pasajero.setPasajeros_cedula(this.getRS().getString("pasajeros_cedula"));
                pasajero.setTipoPasajeroId(this.getRS().getInt("tipoPasajeroId"));
                pasajero.setGeneroId(this.getRS().getInt("generoId"));
                pasajero.setPrecioVueloFinalId(this.getRS().getInt("precioVueloFinalId"));
                
                listadoPasajeros.add(pasajero);
            }
            
            return listadoPasajeros;
        }catch(SQLException e){
            System.out.println("***** ERROR *****");
            System.out.println("* Mensaje Error: " + e.getMessage());
            System.out.println("* Codigo Error: " + e.getErrorCode());
            System.out.println("* Estado Error: " + e.getSQLState());
        
            return null;
        }
       
    }

    @Override
    public List<Pasajeros> listarPasajero(int pasajero_id) {
        try{
            List<Pasajeros> listadoPasajeros = new ArrayList<>();
            String sql = "SELECT * FROM pasajeros WHERE pasajeros_id = ?";
            this.setPS(sql, pasajero_id);
            this.setRS(this.getPS());
            
            while(this.getRS().next()){
                Pasajeros pasajero = new Pasajeros();
                pasajero.setPasajeros_id(0);
                pasajero.setPasajeros_nombre(this.getRS().getString("pasajeros_nombre"));
                pasajero.setPasajeros_apellido(this.getRS().getString("pasajeros_apellido"));
                pasajero.setPasajeros_correo(this.getRS().getString("pasajeros_correo"));
                pasajero.setPasajeros_celular(this.getRS().getString("pasajeros_celular"));
                pasajero.setPasajeros_cedula(this.getRS().getString("pasajeros_cedula"));
                pasajero.setTipoPasajeroId(this.getRS().getInt("tipoPasajeroId"));
                pasajero.setGeneroId(this.getRS().getInt("generoId"));
                pasajero.setPrecioVueloFinalId(this.getRS().getInt("precioVueloFinalId"));
                
                listadoPasajeros.add(pasajero);
            }
            
            return listadoPasajeros;
        }catch(SQLException e){
            System.out.println("***** ERROR *****");
            System.out.println("* Mensaje Error: " + e.getMessage());
            System.out.println("* Codigo Error: " + e.getErrorCode());
            System.out.println("* Estado Error: " + e.getSQLState());
        
            return null;
        }
    }

    @Override
    public boolean eliminarPasajero(int pasajero_id) {
        try{
            String sql = "DELETE FROM pasajeros WHERE pasajeros_id = ?";
            this.setPS(sql, pasajero_id);
            this.setRS(this.getPS());
                        
            return true;
        }catch(SQLException e){
            System.out.println("***** ERROR *****");
            System.out.println("* Mensaje Error: " + e.getMessage());
            System.out.println("* Codigo Error: " + e.getErrorCode());
            System.out.println("* Estado Error: " + e.getSQLState());
        
            return false;
        }
    }

    @Override
    public boolean actualizarPasajero(Pasajeros pasajero) {
        try{
            String sql = "UPDATE pasajeros SET pasajeros_nombre = ?, pasajeros_apellido = ?, pasajeros_correo = ?, pasajeros_celular = ?, pasajeros_cedula = ?, tipoPasajeroId = ?, generoId = ?, precioVueloFinalId = ? WHERE pasajeros_id = ?";
            PreparedStatement ps = this.getConexion().prepareStatement(sql);
            ps.setString(1, pasajero.getPasajeros_nombre());
            ps.setString(2, pasajero.getPasajeros_apellido());
            ps.setString(3, pasajero.getPasajeros_correo());
            ps.setString(4, pasajero.getPasajeros_celular());
            ps.setString(5, pasajero.getPasajeros_cedula());
            ps.setInt(6, pasajero.getTipoPasajeroId());
            ps.setInt(7, pasajero.getGeneroId());
            ps.setInt(8, pasajero.getPrecioVueloFinalId());
            ps.setInt(9, pasajero.getPasajeros_id());
            
            boolean resultado = ps.execute();
            
            return resultado;
        }catch(SQLException e){
            System.out.println("***** ERROR *****");
            System.out.println("* Mensaje Error: " + e.getMessage());
            System.out.println("* Codigo Error: " + e.getErrorCode());
            System.out.println("* Estado Error: " + e.getSQLState());
        
            return false;
        }
    }

    @Override
    public boolean crearPasajero(List<Pasajeros> pasajero) {
        try{
            String sql = "INSERT INTO pasajeros(pasajeros_nombre, pasajeros_apellido, pasajeros_correo, pasajeros_celular, pasajeros_cedula, tipoPasajeroId, generoId, precioVueloFinalId) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = this.getConexion().prepareStatement(sql);
            for(Pasajeros p : pasajero){
                ps.setString(1, p.getPasajeros_nombre());
                ps.setString(2, p.getPasajeros_apellido());
                ps.setString(3, p.getPasajeros_correo());
                ps.setString(4, p.getPasajeros_celular());
                ps.setString(5, p.getPasajeros_cedula());
                ps.setInt(6, p.getTipoPasajeroId());
                ps.setInt(7, p.getGeneroId());
                ps.setInt(8, p.getPrecioVueloFinalId());

                ps.execute();
            }
            
            return true;
        }catch(SQLException e){
            System.out.println("***** ERROR *****");
            System.out.println("* Mensaje Error: " + e.getMessage());
            System.out.println("* Codigo Error: " + e.getErrorCode());
            System.out.println("* Estado Error: " + e.getSQLState());
            
            return false;
        }
    }
    
    
    
    /* Preparacion y Resultado */

    /**
     *
     * @param sql
     * @param flag
     * @param filtro
     * @throws java.sql.SQLException
     */

    public void setPS(String sql) throws SQLException {
        this.ps = this.getConexion().prepareStatement(sql);
    }
    public void setPS(String sql, int id) throws SQLException {
        this.ps = this.getConexion().prepareStatement(sql);
        this.ps.setInt(1, id);
    }
    
    public PreparedStatement getPS(){
        return ps;
    }
    
    
    public void setRS(PreparedStatement ps) throws SQLException{
        this.rs = ps.executeQuery();
    }
    public ResultSet getRS(){
        return rs;
    }
    
    /* Conexion */
    
    public void setConexion(Connection conexion){
        this.conexion = conexion;
    }
    
    public Connection getConexion(){
        return conexion;
    }
    
}
