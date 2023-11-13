package ModeloDAO;

import Config.Conexion;
import Modelo.Destino;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Interfaces.DestinoInterface;
/**
 *
 * @author denni
 */
public class DestinoDAO implements DestinoInterface{
    private Connection conexion;
    private PreparedStatement ps;
    private ResultSet rs;

    public DestinoDAO() throws ClassNotFoundException{
        Conexion cn = new Conexion();
        this.setConexion(cn.getConexion());
    }

    @Override
    public List<Destino> listadoDatos(){
        try{
            List<Destino> listado = new ArrayList<>();
            String sql = "SELECT * FROM destino";
            this.setPs(this.getConexion().prepareStatement(sql));
            this.setRs(this.getPs().executeQuery());
            
            while(this.getRs().next()){
                Destino dst = new Destino();
                dst.setId(this.getRs().getInt("id"));
                dst.setFecha(this.getRs().getString("fecha"));
                dst.setHora_salida(this.getRs().getTime("hora_salida").toString());
                dst.setHora_llegada(this.getRs().getTime("hora_llegada").toString());
                dst.setPais_id(this.getRs().getInt("pais_id"));
                dst.setAerolinea_id(this.getRs().getInt("aerolinea_id"));
                
                listado.add(dst);
            }
        
            return listado;
        }catch(SQLException e){
            System.out.println("dentro de la funcion 'listadoDatos()' de DestinoDAO");
            System.out.println("Error: " + e.getMessage());
            System.out.println("Estado: " + e.getSQLState());
            return null;
        }
    }
    
    public List<Destino> listadoDatosInner(){
        try{
           List<Destino> datos = new ArrayList<>();
           String sql = "SELECT destino.id, fecha, hora_salida, hora_llegada, destino.pais_id, p.paisnombre, aerolineas.aero_nombre FROM DESTINO INNER JOIN pais as p ON DESTINO.pais_id = p.id INNER JOIN aerolineas ON destino.aerolinea_id = aerolineas.aero_id";
           this.setPs(this.getConexion().prepareStatement(sql));
           this.setRs(this.getPs().executeQuery());
           
            while(this.getRs().next()){
                Destino dst = new Destino();
                dst.setId(this.getRs().getInt("id"));
                dst.setFecha(this.getRs().getString("fecha"));
                dst.setHora_salida(this.getRs().getTime("hora_salida").toString());
                dst.setHora_llegada(this.getRs().getTime("hora_llegada").toString());
                dst.setPais_id(this.getRs().getInt("pais_id"));
                dst.setPaisnombre(this.getRs().getString("paisnombre"));
                dst.setAero_nombre(this.getRs().getString("aero_nombre"));
                
                datos.add(dst);
            }
           return datos;
        }catch(SQLException e){
           System.out.println("Error: " + e.getMessage());
           System.out.println("Estado: " + e.getSQLState());
           System.out.println("***** ERROR EN LISTAR DATOS *****");
           return null;
        }
    }
    
    
    @Override
    public boolean insertarDatos(Destino destino) {
        try{
            List<Destino> verificar = this.filtroRegistro(destino);
            String sql = "INSERT INTO destino(fecha, hora_salida, hora_llegada, pais_id, aerolinea_id) VALUES(?, ?, ?, ?, ?)";
            this.setPs(this.getConexion().prepareStatement(sql));
            this.getPs().setString(1, destino.getFecha());
            this.getPs().setString(2, destino.getHora_salida());
            this.getPs().setString(3, destino.getHora_llegada());
            this.getPs().setInt(4, destino.getPais_id());
            this.getPs().setInt(5, destino.getAerolinea_id());
            
            this.getPs().execute();
            
            return true;
        }catch(SQLException e){
            return false;
        }
    }
    
    public Destino idDestino(){
        try{
            Destino destino = new Destino();
            String sql = "SELECT * FROM destino ORDER BY id DESC LIMIT 1";
            this.setPs(this.getConexion().prepareStatement(sql));
            this.setRs(this.getPs().executeQuery());
            
            while(this.getRs().next()){
                destino.setId(this.getRs().getInt("id"));
            }
        
            return destino;
        }catch(SQLException e){
            System.out.println("dentro de la funcion 'listadoDatos()' de DestinoDAO");
            System.out.println("Error: " + e.getMessage());
            System.out.println("Estado: " + e.getSQLState());
            return null;
        }
    }
    
    @Override
    public List<Destino> filtroRegistro(Destino dst){
        List<Destino> registro = new ArrayList<>();
        
        for(Destino j : this.listadoDatos()){
            /*Por el momento dejaremos el filtro con el ID... ya que no filtraremos de otra manera... */
            if(j.getFecha().equals(dst.getFecha()) && j.getHora_llegada().equals(dst.getHora_llegada()+":00") &&
               j.getHora_salida().equals(dst.getHora_salida()+":00") && j.getPais_id() == dst.getPais_id()){
                registro.add(j);
                break;
            }
            
        }
        
        return registro;
    }
    
    public List<Destino> filtroRegistro2(Destino dst){
        List<Destino> registro = new ArrayList<>();
        
        for(Destino j : this.listadoDatosInner()){
            /*Por el momento dejaremos el filtro con el ID... ya que no filtraremos de otra manera... */
            if(j.getId() == dst.getId()){
                registro.add(j);
                break;
            }
            
        }
        
        return registro;
    }
    
    
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
