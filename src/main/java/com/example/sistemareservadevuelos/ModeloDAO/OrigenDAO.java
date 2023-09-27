/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.sistemareservadevuelos.ModeloDAO;

import Config.Conexion;
import Interfaces.OrigenInterface;
import Modelo.Origen;

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
public class OrigenDAO implements OrigenInterface{
    
    Connection conexion;
    PreparedStatement ps;
    ResultSet rs;
    
    
    public OrigenDAO() throws ClassNotFoundException{
        Conexion n = new Conexion();
        conexion = n.getConexion();
    }

    @Override 
    public List<Origen> listadoDatos(){
        List<Origen> listado = new ArrayList<>();
        String sql = "SELECT * FROM origen";
        try{
            
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                Origen org = new Origen();
                org.setId(rs.getInt("id"));
                org.setFecha(rs.getString("fecha"));
                org.setHora_salida(rs.getTime("hora_salida").toString());
                org.setHora_llegada(rs.getTime("hora_llegada").toString());
                org.setPais_id(rs.getInt("pais_id"));
                org.setAerolinea_id(rs.getInt("aerolinea_id"));
                
                listado.add(org);
            }
        
            return listado;
        }catch(SQLException e){
            System.out.println("dentro de la funcion 'listadoDatos()' de OrigenDAO");
            System.out.println("Error: " + e.getMessage());
            System.out.println("Estado: " + e.getSQLState());
            return null;
        }
        
    }
    
    public List<Origen> listadoDatosInner(){
        try{
            List<Origen> datos = new ArrayList<>();
            String sql = "SELECT origen.id, fecha, hora_salida, hora_llegada, origen.pais_id, p.paisnombre, aerolineas.aero_nombre FROM ORIGEN INNER JOIN pais as p ON ORIGEN.pais_id = p.id INNER JOIN aerolineas ON origen.aerolinea_id = aerolineas.aero_id";
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Origen org = new Origen();
                org.setId(rs.getInt("id"));
                org.setFecha(rs.getString("fecha"));
                org.setHora_salida(rs.getTime("hora_salida").toString());
                org.setHora_llegada(rs.getTime("hora_llegada").toString());
                org.setPais_id(Integer.parseInt(rs.getString("pais_id")));
                org.setPaisnombre(rs.getString("paisnombre"));
                org.setAero_nombre(rs.getString("aero_nombre"));
                
                datos.add(org);
            }
            
            return datos;
        }catch(SQLException e){
            System.out.println("***** ERROR EN EL LISTADO DE DATOS *****");
            System.out.println("Error: " + e.getMessage());
            System.out.println("Estado: " + e.getSQLState());
            return null;
        }
    }
    
    @Override
    public boolean insertarDatos(Origen origen) {
        String sql = "INSERT INTO origen(fecha, hora_salida, hora_llegada, pais_id, aerolinea_id) VALUES('"+origen.getFecha()+"','"+origen.getHora_salida()+"','"+origen.getHora_llegada()+"','"+origen.getPais_id()+"', '"+origen.getAerolinea_id()+"')";
        
        try{
            List<Origen> verificar = this.filtroRegistro(origen);
            
            if(verificar.size() == 0){ //esto evita volver a insertar el registro ya introducido.
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
    public List<Origen> filtroRegistro(Origen org){
        List<Origen> registro = new ArrayList<>();
        
        for(Origen j : this.listadoDatos()){
            /*Por el momento dejaremos el filtro con el ID... ya que no filtraremos de otra manera... */
            
            
            if(j.getFecha().equals(org.getFecha()) && j.getHora_llegada().equals(org.getHora_llegada()+":00") &&
               j.getHora_salida().equals(org.getHora_salida()+":00") && j.getPais_id() == org.getPais_id()){
                registro.add(j);
                break;
            }
            
            
        }
        
        return registro;
    }
    
    
    public List<Origen> filtroRegistro2(Origen org){
        List<Origen> registro = new ArrayList<>();
        
        for(Origen j : this.listadoDatosInner()){
            /*Por el momento dejaremos el filtro con el ID... ya que no filtraremos de otra manera... */
           
            if(j.getId() == org.getId()){
                registro.add(j);
                break;
            }
        }
        
        return registro;
    }
    
}
