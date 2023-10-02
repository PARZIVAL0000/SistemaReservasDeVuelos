/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.sistemareservadevuelos.ModeloDAO;

import com.example.sistemareservadevuelos.Config.Conexion;
import com.example.sistemareservadevuelos.Modelo.Usuario_Perfil;

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
public class Usuario_PerfilDAO {
    
    Connection conexion;
    PreparedStatement ps;
    ResultSet rs;
    
    public Usuario_PerfilDAO() throws ClassNotFoundException{
        Conexion cn = new Conexion();
        conexion = cn.getConexion();
    }
    
    public List<Usuario_Perfil> listarRegistros(){
        String sql = "SELECT * FROM usuario_perfil";
        try{
            List<Usuario_Perfil> listado = new ArrayList<>();
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                //sacamos los datos
                Usuario_Perfil up = new Usuario_Perfil(rs.getInt("id"), rs.getInt("perfil_id"), rs.getInt("usuario_id"));
                listado.add(up);
            }
            
            return listado;
        }catch(SQLException e){
            System.out.println("***** NO PUDIMOS RECORRER LA TABLA USUARIO_PERFIL *****");
            System.out.println("Error: " + e.getErrorCode());
            System.out.println("Mensaje: " + e.getMessage());
            return null;
        }
    }
    
    public boolean insertarRegistro(Usuario_Perfil registro){
        //insertar dentro de nuestra tabla de usuario_perfil
        try{
            String sql = "INSERT INTO usuario_perfil(perfil_id, usuario_id) VALUES('"+registro.getPerfil_id()+"','"+registro.getUsuario_id()+"')";
            ps = conexion.prepareStatement(sql);
            ps.execute();
            return true;
        }catch(SQLException e){
            return false;
        }
    }
    
    public List<Usuario_Perfil> filtrarRegistro(int id){
        List<Usuario_Perfil> registro = new ArrayList<>();
        
        for(Usuario_Perfil j : this.listarRegistros()){
            if(j.getUsuario_id() == id){
                registro.add(j);
                break;
            }
        }
        
        return registro;
    }
}
