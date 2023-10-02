/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.sistemareservadevuelos.ModeloDAO;

import com.example.sistemareservadevuelos.Config.Conexion;
import com.example.sistemareservadevuelos.Modelo.Usuario_Origen;

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
public class Usuario_OrigenDAO {
    
    Connection conexion = null;
    PreparedStatement ps;
    ResultSet rs;
    
    public Usuario_OrigenDAO() throws ClassNotFoundException{
        Conexion con = new Conexion();
        conexion = con.getConexion();
    }
    
    public List<Usuario_Origen> listarRegistros(){
        List<Usuario_Origen> uo = new ArrayList<>();
        String sql = "SELECT * FROM usuario_origen";
        try{
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Usuario_Origen u_org = new Usuario_Origen();
                u_org.setId(rs.getInt("id"));
                u_org.setUsuario_id(rs.getInt("usuario_id"));
                u_org.setOrigen_id(rs.getInt("origen_id"));
                
                uo.add(u_org);
            }
            
            
        }catch(SQLException e){
            System.out.println("*** ERROR EN LISTAR REGISTRO DE TABLA 'USUARIO_ORIGEN' *** ");
            return null;
        }
        
        return uo;
    }

    public boolean introducirRegistro(Usuario_Origen uo){
        String sql = "INSERT INTO usuario_origen(usuario_id, origen_id) VALUES ('"+uo.getUsuario_id()+"', '"+uo.getOrigen_id()+"')";
        try{
            ps = conexion.prepareStatement(sql);
            ps.execute();
            
        }catch(SQLException e){
            System.out.println("*** ERROR EN INTRODUCIR REGISTRO EN LA TABLA 'USUARIO_ORIGEN'");
            return false;
        }
        
        return true;
    }
    
    public List<Usuario_Origen> filtroRegistro(Usuario_Origen uo){
        List<Usuario_Origen> registro = new ArrayList<>();
        
        //aqui vamos a intentar sacarnos el dato que vayamos ir generando una comparacion...
        for(Usuario_Origen j : this.listarRegistros()){
            
            if(j.getOrigen_id() == uo.getOrigen_id()){
                registro.add(j);
                break;
            }
        }
        
        return registro;
    }
}
