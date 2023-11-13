package ModeloDAO;

import Config.Conexion;
import Modelo.Origen;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Interfaces.OrigenInterface;
/**
 *
 * @author denni
 */
public class OrigenDAO implements OrigenInterface{    
    private Connection conexion;
    private PreparedStatement ps;
    private ResultSet rs;

    public OrigenDAO() throws ClassNotFoundException{
        Conexion n = new Conexion();
        this.setConexion(n.getConexion());
    }

    @Override 
    public List<Origen> listadoDatos(){
        try{
            List<Origen> listado = new ArrayList<>();
            String sql = "SELECT * FROM origen";
            this.setPs(this.getConexion().prepareStatement(sql));
            this.setRs(this.getPs().executeQuery());
            
            while(this.getRs().next()){
                Origen org = new Origen();
                org.setId(this.getRs().getInt("id"));
                org.setFecha(this.getRs().getString("fecha"));
                org.setHora_salida(this.getRs().getTime("hora_salida").toString());
                org.setHora_llegada(this.getRs().getTime("hora_llegada").toString());
                org.setPais_id(this.getRs().getInt("pais_id"));
                org.setAerolinea_id(this.getRs().getInt("aerolinea_id"));
                
                listado.add(org);
            }
            return listado;
        }catch(SQLException e){
            System.out.println("dentro de la funcion 'listadoDatos()' de OrigenDAO");
            System.out.println("Error: " + e.getMessage());
            System.out.println("Estado: " + e.getSQLState());
            return null;
        }
    }
    
    public List<Origen> listadoDatosInner(){
        try{
            List<Origen> datos = new ArrayList<>();
            String sql = "SELECT origen.id, fecha, hora_salida, hora_llegada, origen.pais_id, p.paisnombre, aerolineas.aero_nombre FROM ORIGEN INNER JOIN pais as p ON ORIGEN.pais_id = p.id INNER JOIN aerolineas ON origen.aerolinea_id = aerolineas.aero_id";
            this.setPs(this.getConexion().prepareStatement(sql));
            this.setRs(this.getPs().executeQuery());

            while(this.getRs().next()){
                Origen org = new Origen();
                org.setId(this.getRs().getInt("id"));
                org.setFecha(this.getRs().getString("fecha"));
                org.setHora_salida(this.getRs().getTime("hora_salida").toString());
                org.setHora_llegada(this.getRs().getTime("hora_llegada").toString());
                org.setPais_id(Integer.parseInt(this.getRs().getString("pais_id")));
                org.setPaisnombre(this.getRs().getString("paisnombre"));
                org.setAero_nombre(this.getRs().getString("aero_nombre"));
                
                datos.add(org);
            }
            return datos;
        }catch(SQLException e){
            System.out.println("***** ERROR EN EL LISTADO DE DATOS *****");
            System.out.println("Error: " + e.getMessage());
            System.out.println("Estado: " + e.getSQLState());
            return null;
        }
    }
    
    
    @Override
    public boolean insertarDatos(Origen origen) {
        try{
            String sql = "INSERT INTO origen(fecha, hora_salida, hora_llegada, pais_id, aerolinea_id) VALUES(?, ?, ?, ?, ?)";
            this.setPs(this.getConexion().prepareStatement(sql));
            this.getPs().setString(1, origen.getFecha());
            this.getPs().setString(2, origen.getHora_salida());
            this.getPs().setString(3, origen.getHora_llegada());
            this.getPs().setInt(4, origen.getPais_id());
            this.getPs().setInt(5, origen.getAerolinea_id());
            
            this.getPs().execute();
            
            return true;
        }catch(SQLException e){
            return false;
        }
        
    }
    
    @Override
    public List<Origen> filtroRegistro(Origen org){
        List<Origen> registro = new ArrayList<>();
        
        for(Origen j : this.listadoDatos()){
            /*Por el momento dejaremos el filtro con el ID... ya que no filtraremos de otra manera... */
           
            if(j.getFecha().equals(org.getFecha()) && j.getHora_llegada().equals(org.getHora_llegada()+":00") &&
               j.getHora_salida().equals(org.getHora_salida()+":00") && j.getPais_id() == org.getPais_id()){
                registro.add(j);
                break;
            }
            
            
        }
        
        return registro;
    }
    
    
    public List<Origen> filtroRegistro2(Origen org){
        List<Origen> registro = new ArrayList<>();
        
        for(Origen j : this.listadoDatosInner()){
            /*Por el momento dejaremos el filtro con el ID... ya que no filtraremos de otra manera... */
           
            if(j.getId() == org.getId()){
                registro.add(j);
                break;
            }
        }
        
        return registro;
    }
    
    public Origen OrigenId(){
        try{
            Origen origen = new Origen();
            String sql = "SELECT * FROM origen ORDER BY id DESC LIMIT 1";
            this.setPs(this.getConexion().prepareStatement(sql));
            this.setRs(this.getPs().executeQuery());
        
            while(this.getRs().next()){
                origen.setId(this.getRs().getInt("id"));
            }
            
            return origen;
        }catch(SQLException e){
            this.mensaje(e);
            return null;
        }
    }
    
    /* Mensaje */
    public void mensaje(SQLException e){
        System.out.println("*********** ERROR EN LA CONEXION A LA BASE DE DATOS **********");
        System.out.println("* Causa: " + e.getCause());
        System.out.println("* Mensaje: " + e.getMessage());
        System.out.println("* Estado: " + e.getSQLState());
        System.out.println("****************************************************************");
    }
 
    /* Setter y Getter */
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
