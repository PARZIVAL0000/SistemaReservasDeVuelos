/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.sistemareservadevuelos.ModeloDAO;

import Config.Conexion;
import Interfaces.DestinoInterface;
import Modelo.Destino;

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
public class DestinoDAO implements DestinoInterface{
    Connection conexion;
    PreparedStatement ps;
    ResultSet rs;
    
    
    public DestinoDAO() throws ClassNotFoundException{
        Conexion n = new Conexion();
        conexion = n.getConexion();
    }

    @Override
    public List<Destino> listadoDatos(){
        List<Destino> listado = new ArrayList<>();
        String sql = "SELECT * FROM destino";
        try{
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                Destino dst = new Destino();
                dst.setId(rs.getInt("id"));
                dst.setFecha(rs.getString("fecha"));
                dst.setHora_salida(rs.getTime("hora_salida").toString());
                dst.setHora_llegada(rs.getTime("hora_llegada").toString());
                dst.setPais_id(rs.getInt("pais_id"));
                dst.setAerolinea_id(rs.getInt("aerolinea_id"));
                
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
           ps = conexion.prepareStatement(sql);
           rs = ps.executeQuery();
           while(rs.next()){
                Destino dst = new Destino();
                dst.setId(rs.getInt("id"));
                dst.setFecha(rs.getString("fecha"));
                dst.setHora_salida(rs.getTime("hora_salida").toString());
                dst.setHora_llegada(rs.getTime("hora_llegada").toString());
                dst.setPais_id(rs.getInt("pais_id"));
                dst.setPaisnombre(rs.getString("paisnombre"));
                dst.setAero_nombre(rs.getString("aero_nombre"));
                
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
        String sql = "INSERT INTO destino(fecha, hora_salida, hora_llegada, pais_id, aerolinea_id) VALUES('"+destino.getFecha()+"','"+destino.getHora_salida()+"','"+destino.getHora_llegada()+"','"+destino.getPais_id()+"', '"+destino.getAerolinea_id()+"')";
        
        try{
            List<Destino> verificar = this.filtroRegistro(destino);
            if(verificar.size() == 0){
                ps = conexion.prepareStatement(sql);
                ps.execute();
                return true;
            }else{
                return false;
            }
            
        }catch(SQLException e){
            return false;
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
}
