/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.sistemareservadevuelos.ModeloDAO;

import Config.Conexion;
import Interfaces.Usuario_DestinoInterface;
import Modelo.Usuario_Destino;

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
public class Usuario_DestinoDAO implements Usuario_DestinoInterface{
    
    Connection conexion;
    PreparedStatement ps;
    ResultSet rs;
    
    public Usuario_DestinoDAO() throws ClassNotFoundException{
        Conexion cn = new Conexion();
        conexion = cn.getConexion();
    }
    
    public List<Usuario_Destino> listarRegistros(){
        List<Usuario_Destino> ud = new ArrayList<>();
        String sql = "SELECT * FROM usuario_destino";
        try{
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Usuario_Destino u_dst = new Usuario_Destino();
                u_dst.setId(rs.getInt("id"));
                u_dst.setUsuario_id(rs.getInt("usuario_id"));
                u_dst.setDestino_id(rs.getInt("destino_id"));
                
                ud.add(u_dst);
            }
            
        }catch(SQLException e){
            System.out.println("*** ERROR EN LISTAR REGISTRO DE TABLA 'USUARIO_DESTINO' *** ");
            return null;
        }
        
        return ud;
    }
    
    
    @Override
    public boolean introducirDato(Usuario_Destino ud){
        String sql = "INSERT INTO usuario_destino(usuario_id, destino_id) VALUES('"+ud.getUsuario_id()+"', '"+ud.getDestino_id()+"')";
        try{
            ps = conexion.prepareStatement(sql);
            ps.execute();
            return true;
        }catch(SQLException e){
            System.out.println("**** NO PUDIMOS INTRODUCIR EL REGISTRO EN USUARIO_DESTINO ****");
            System.out.println(e.getCause());
            System.out.println(e.getErrorCode());
            return false;
        }
        
    }
}
