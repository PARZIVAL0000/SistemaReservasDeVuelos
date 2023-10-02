/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.sistemareservadevuelos.ModeloDAO;

import com.example.sistemareservadevuelos.Config.Conexion;
import com.example.sistemareservadevuelos.Interfaces.AerolineaInterface;
import com.example.sistemareservadevuelos.Modelo.Aerolineas;

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
    PreparedStatement ps;
    ResultSet rs;
    Connection conexion;
    
    public AerolineasDAO() throws ClassNotFoundException{
        Conexion con = new Conexion();
        conexion = con.getConexion();
    }
    
    @Override
    public List<Aerolineas> listarAerolineas() {
        List<Aerolineas> aerolineas = new ArrayList<>();
        
        String cmd = "SELECT * FROM aerolineas";
        try{
            ps = conexion.prepareStatement(cmd);
            rs = ps.executeQuery();
            
            while(rs.next()){
                Aerolineas aerolinea = new Aerolineas();
                aerolinea.setId(rs.getInt("aero_id"));
                aerolinea.setNombre(rs.getString("aero_nombre"));
                aerolinea.setEstado(rs.getInt("aero_estado"));
                
                aerolineas.add(aerolinea);
            }
 
            return aerolineas;
        }catch(SQLException e){
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
}
