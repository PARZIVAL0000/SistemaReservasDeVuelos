/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.sistemareservadevuelos.ModeloDAO;

import Config.Conexion;
import Modelo.Perfil;

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
public class PerfilDAO {
    Connection conexion;
    PreparedStatement ps;
    ResultSet rs;
    
    public PerfilDAO() throws ClassNotFoundException{
        Conexion cn = new Conexion();
        conexion = cn.getConexion();
    }
    
    
    public List<Perfil> listarRegistro(){
        String sql = "SELECT * FROM perfil";
        List<Perfil> listadoPerfil = new ArrayList<>();
        try{
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                Perfil pf = new Perfil(rs.getInt("id"), rs.getString("perfil"), rs.getInt("estado"));
                
                listadoPerfil.add(pf);
            }
            
            return listadoPerfil;
        }catch(SQLException e){
            System.out.println("***** NO PUDIMOS LISTAR LA TABLA PERFIL *****");
            System.out.println("Error: " + e.getErrorCode());
            System.out.println("Mensaje: " + e.getMessage());
            return null;
        }
    }
    
    
    public List<Perfil> filtrarRegistro(Perfil pf){
        List<Perfil> registro = new ArrayList<>();
        
        for(Perfil j : this.listarRegistro()){
            if(j.getId() == pf.getId()){
                registro.add(j);
                break;
            }
        }
        
        return registro;
    }
}
