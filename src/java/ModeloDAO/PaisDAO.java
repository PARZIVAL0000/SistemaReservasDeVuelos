/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;
import Interfaces.PaisInterface;
import Modelo.Pais;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Config.Conexion;
import java.sql.Connection;

public class PaisDAO implements PaisInterface{
    private PreparedStatement ps;
    private ResultSet rs;
    private Connection conexion;

    public PaisDAO() throws ClassNotFoundException{
        Conexion con = new Conexion();
        this.setConexion(con.getConexion());
    }
    
    @Override
    public List<Pais> listarPaises() {
        try{
            List<Pais> paises = new ArrayList<>();
            String sql = "SELECT * FROM pais";
            this.setPs(this.getConexion().prepareStatement(sql));
            this.setRs(this.getPs().executeQuery());
            
            while(this.getRs().next()){
                Pais pais = new Pais();
                pais.setId(this.getRs().getInt("id"));
                pais.setPaisNombre(this.getRs().getString("paisnombre"));
                paises.add(pais);
            }
 
            return paises;
        }catch(SQLException e){
            return null;
        }
        
    }
    
    public Pais buscarPorNombre(String nombre){
        try{
            String sql = "SELECT * FROM pais WHERE paisnombre = ?";
            this.setPs(this.getConexion().prepareStatement(sql));
            this.getPs().setString(1, nombre);
            this.setRs(this.getPs().executeQuery());
            Pais registro = new Pais();
            
            while(this.getRs().next()){
                registro.setId(this.getRs().getInt("id"));
                registro.setPaisNombre(this.getRs().getString("paisnombre"));
            }
            
            return registro;
        }catch(SQLException e){
            return null;
        }
    }
    
    public List<Pais> tipoPais(String nombre){
        List<Pais> pais = new ArrayList<>();
        
        for(Pais j : listarPaises()){
            if(j.getPaisNombre().equals(nombre)){
                pais.add(j);
                break;
            }
        }
        
        return pais;
    }
    
    /* Setter y Getter */
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
