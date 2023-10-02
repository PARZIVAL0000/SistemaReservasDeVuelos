/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.sistemareservadevuelos.ModeloDAO;

import com.example.sistemareservadevuelos.Config.Conexion;
import com.example.sistemareservadevuelos.Interfaces.PaisInterface;
import com.example.sistemareservadevuelos.Modelo.Pais;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaisDAO implements PaisInterface{
    PreparedStatement ps;
    ResultSet rs;
    Connection conexion;
    
    public PaisDAO() throws ClassNotFoundException{
        Conexion con = new Conexion();
        conexion = con.getConexion();
    }
    
    @Override
    public List<Pais> listarPaises() {
        List<Pais> paises = new ArrayList<>();
        
        String cmd = "SELECT * FROM pais";
        try{
            ps = conexion.prepareStatement(cmd);
            rs = ps.executeQuery();
            
            while(rs.next()){
                Pais pais = new Pais();
                pais.setId(rs.getInt("id"));
                pais.setPaisNombre(rs.getString("paisnombre"));
                paises.add(pais);
            }
 
            return paises;
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
    
}
