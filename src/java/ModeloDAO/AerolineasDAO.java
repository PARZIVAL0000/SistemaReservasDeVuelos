/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;
import Interfaces.AerolineaInterface;
import Modelo.Aerolineas;
import Config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author denni
 */
public class AerolineasDAO implements AerolineaInterface{
    private PreparedStatement ps;
    private ResultSet rs;
    private Connection conexion;
    
    public AerolineasDAO() throws ClassNotFoundException{
        Conexion con = new Conexion();
        this.setConexion(con.getConexion());
    }
    
    @Override
    public List<Aerolineas> listarAerolineas() {
        try{
            List<Aerolineas> aerolineas = new ArrayList<>();
            String sql = "SELECT * FROM aerolineas";
            this.setPs(this.getConexion().prepareStatement(sql));
            this.setRs(this.getPs().executeQuery());
            
            while(this.getRs().next()){
                Aerolineas aerolinea = new Aerolineas();
                aerolinea.setId(rs.getInt("aero_id"));
                aerolinea.setNombre(rs.getString("aero_nombre"));
                aerolinea.setEstado(rs.getInt("aero_estado"));
                
                aerolineas.add(aerolinea);
            }
            return aerolineas;
        }catch(SQLException e){
            this.mensaje(e);
            return null;
        }
    }
    
    public Aerolineas buscarPorNombre(String aerolinea){
        try{
            String sql = "SELECT * FROM aerolineas WHERE aero_nombre = ?";
            this.setPs(this.getConexion().prepareStatement(sql));
            this.getPs().setString(1, aerolinea);
            this.setRs(this.getPs().executeQuery());
            Aerolineas registro = new Aerolineas();
            while(this.getRs().next()){
                registro.setId(this.getRs().getInt("aero_id"));
                registro.setNombre(this.getRs().getString("aero_nombre"));
                registro.setEstado(this.getRs().getInt("aero_estado"));
            }
        
            return registro;
        }catch(SQLException e){
            this.mensaje(e);
            return null;
        }
    }
    
    public List<Aerolineas> filtrarRegistro(Aerolineas aerolinea){
        List<Aerolineas> registro = new ArrayList<>();
        
        for(Aerolineas j : this.listarAerolineas()){
            if(j.getNombre().toLowerCase().equals(aerolinea.getNombre().toLowerCase())){
                registro.add(j);
                break;
            }
        }
        
        return registro;
    }
    
    public void mensaje(SQLException e){
        System.out.println("***** ERROR EN LA CONEXION A LA BASE DE DATOS *****");
        System.out.println("* Mensaje: " + e.getMessage());
        System.out.println("* Causa: " + e.getCause());
        System.out.println("* Estado: " + e.getErrorCode());
    }
    
    /* Getter y Setter */
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

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }
}
