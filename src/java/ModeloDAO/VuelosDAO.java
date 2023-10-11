package ModeloDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Config.Conexion;
import Interfaces.VuelosInterface;
import Modelo.CarteleraViajes;
import java.util.List;
import java.util.ArrayList;
import Modelo.Vuelos;
/**
 *
 * @author denni
 */
public class VuelosDAO implements VuelosInterface{
    
    Connection conexion;
    PreparedStatement ps;
    ResultSet rs;
    
    
    public VuelosDAO() throws ClassNotFoundException{
        Conexion n = new Conexion();
        conexion = n.getConexion();
    }
    
    @Override
    public List<Vuelos> listadoVuelos(){
        List<Vuelos> listadoVuelos = new ArrayList<>();
        String cmd = "SELECT * FROM vuelos";
        try{
            ps = conexion.prepareStatement(cmd);
            rs = ps.executeQuery();
            
            while(rs.next()){
                Vuelos vl = new Vuelos();
                vl.setId(rs.getInt("id"));
                vl.setEstado(rs.getInt("estado"));
                vl.setAerolinea_id(rs.getInt("aero_id"));
                vl.setPasaje_id(rs.getInt("pasaje_id"));
                vl.setFecha(rs.getString("fecha"));
                vl.setHora_llegada(rs.getString("hora_llegada"));
                vl.setHora_salida(rs.getString("hora_salida"));
                vl.setPais_origen(rs.getInt("pais_origen"));
                vl.setPais_destino(rs.getInt("pais_destino"));
                
                listadoVuelos.add(vl);
            }
            
            return listadoVuelos;
        }catch(SQLException e){
            System.out.println("Dentro del listado de vuelos");
            System.out.println("Error: " + e.getMessage());
            System.out.println("Causa: " + e.getCause());
            System.out.println("Estado: " + e.getSQLState());
            return null;
        }
        
    }
    
    @Override
    public List<CarteleraViajes> listadoCarteleraViajes(){
        List<CarteleraViajes> cv = new ArrayList<>();
        String cmd = "SELECT vuelos.id, vuelos.estado, aerolineas.aero_nombre AS aerolinea, pasajes.tarifa, vuelos.hora_llegada, vuelos.hora_salida, pais.paisnombre AS pais_origen, p.paisnombre AS pais_destino, vuelos.fecha FROM vuelos INNER JOIN aerolineas ON vuelos.aero_id = aerolineas.aero_id INNER JOIN pasajes ON pasajes.pasaje_id = vuelos.pasaje_id INNER JOIN pais ON pais.id = vuelos.pais_origen INNER JOIN pais AS p ON p.id = vuelos.pais_destino";
        
        try{
            ps = conexion.prepareStatement(cmd);
            rs = ps.executeQuery();
            while(rs.next()){
                CarteleraViajes crtv = new CarteleraViajes();
                crtv.setId(rs.getInt("id"));
                crtv.setEstado(rs.getInt("estado"));
                crtv.setAerolinea(rs.getString("aerolinea"));
                crtv.setTarifa(rs.getDouble("tarifa"));
                crtv.setHoraLlegada(rs.getString("hora_llegada"));
                crtv.setHoraSalida(rs.getString("hora_salida"));
                crtv.setPaisOrigen(rs.getString("pais_origen"));
                crtv.setPaisDestino(rs.getString("pais_destino"));
                crtv.setFecha(rs.getString("fecha"));
                
                cv.add(crtv);
            }
            
            return cv;
        }catch(SQLException e){
            System.out.println("Mensaje: "+ e.getMessage());
            System.out.println("Estado: "+ e.getSQLState() );
            return null;
        }
    }
    
    public boolean eliminarRegistro( int id , Vuelos vl){
        //vamos a eliminar a continuacion uno de los registros existentes dentro de nusetra base de datos...
        String sql = "DELETE FROM vuelos WHERE id = '"+id+"'";
        try{
            
            List<Vuelos> verificar = this.filtrarRegistro(vl);
            if(verificar.size() != 0){
                
                ps = conexion.prepareStatement(sql);
                ps.execute();
                return true;
            }else{
                
                return false;
            }
            

        }catch(SQLException e){
            System.out.println("******* ERROR AL ELIMINAR UN REGISTRO DE VUELO *******");
            System.out.println("mensaje " + e.getMessage());
            System.out.println("error " + e.getErrorCode());
            return false;
        }
    }
    
    public boolean insertarRegistro(Vuelos vuelo){
        String sql = "INSERT INTO vuelos(estado, aero_id, pasaje_id, hora_llegada, hora_salida, pais_origen, pais_destino, fecha) VALUES('"+vuelo.getEstado()+"', '"+vuelo.getAerolinea_id()+"', '"+vuelo.getPasaje_id()+"', '"+vuelo.getHora_llegada()+"', '"+vuelo.getHora_salida()+"', '"+vuelo.getPais_origen()+"', '"+vuelo.getPais_destino()+"', '"+vuelo.getFecha()+"')";
        
        try{
            ps = conexion.prepareStatement(sql);
            ps.execute();
        }catch(SQLException e){
            System.out.println("***** NO PUDIMOS INSERTAR EL VUELO *****");
            System.out.println("Mensaje: " + e.getMessage());
            System.out.println("Error: " + e.getErrorCode());
            return false;
        }
        
        return true;
    }
    
    public boolean actualizarRegistro(Vuelos vl){
        String sql = "UPDATE vuelos SET estado='"+vl.getEstado()+"', aero_id='"+vl.getAerolinea_id()+"', pasaje_id='"+vl.getPasaje_id()+"', hora_llegada='"+vl.getHora_llegada()+"', hora_salida='"+vl.getHora_salida()+"', pais_origen='"+vl.getPais_origen()+"', pais_destino='"+vl.getPais_destino()+"', fecha='"+vl.getFecha()+"' WHERE id = '"+vl.getId()+"'";
        try{
            ps = conexion.prepareStatement(sql);
            ps.execute();
            return true;
        }catch(SQLException e){
            System.out.println("******* ERROR AL ELIMINAR UN REGISTRO DE VUELO *******");
            System.out.println("mensaje " + e.getMessage());
            System.out.println("error " + e.getErrorCode());
            return false;
        }
    }
    
    public List<Vuelos> filtrarRegistro(Vuelos vl){
        List<Vuelos> registro = new ArrayList<>();
        
        for(Vuelos j : this.listadoVuelos()){
            //dentro de nuestro listado de los vuelos lo que haremos es una verificacion.
            if(j.getId() == vl.getId()){
                registro.add(j);
                break;
            }
        }
        
        return registro;
    }
    
    public List<CarteleraViajes> filtrarRegistro2(CarteleraViajes vl){
        List<CarteleraViajes> registro = new ArrayList<>();
        
        for(CarteleraViajes j : this.listadoCarteleraViajes()){
            if(j.getId() == vl.getId()){
                registro.add(j);
                break;
            }
        }
        
        return registro;
    }
}
