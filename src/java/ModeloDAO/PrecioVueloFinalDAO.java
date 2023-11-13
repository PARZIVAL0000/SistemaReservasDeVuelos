/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;

import Config.Conexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

import Modelo.PrecioVueloFinal;

import Interfaces.PrecioVueloFinalInterface;

import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author denni
 */
public class PrecioVueloFinalDAO implements PrecioVueloFinalInterface{
    private PreparedStatement ps;
    private ResultSet rs;
    private  Connection conexion;

    public PrecioVueloFinalDAO() throws ClassNotFoundException{
        Conexion cn = new Conexion();
        this.setConexion(cn.getConexion());
    }
    
    @Override
    public List<PrecioVueloFinal> listarPreciosVuelosFinal() {
        try{
            List<PrecioVueloFinal> registros = new ArrayList<>();
            String sql = "SELECT * FROM preciovuelofinal";
            this.setPs(this.getConexion().prepareStatement(sql));
            this.setRs(this.getPs().executeQuery());
            
            while(this.getRs().next()){
                PrecioVueloFinal precioFinal = new PrecioVueloFinal();
                precioFinal.setPrecioVueloFinal_id(this.getRs().getInt("precioVueloFinal_id"));
                precioFinal.setPrecioVueloFinal_cantidad(this.getRs().getDouble("precioVueloFinal_cantidad"));
                
                registros.add(precioFinal);
            }
            
            return registros;
        }catch(SQLException e){
            this.getMensaje(e);
            return null;
        }
    }

    @Override
    public List<PrecioVueloFinal> listarPrecioVueloFinal(int precioVueloFinalId) {
        try{
            List<PrecioVueloFinal> registros = new ArrayList<>();
            String sql = "SELECT * FROM preciovuelofinal WHERE precioVueloFinal_id = ?";
            this.setPs(this.getConexion().prepareStatement(sql));
            this.getPs().setInt(1, precioVueloFinalId);
            this.setRs(this.getPs().executeQuery());
            
            while(this.getRs().next()){
                PrecioVueloFinal precioFinal = new PrecioVueloFinal();
                precioFinal.setPrecioVueloFinal_id(this.getRs().getInt("precioVueloFinal_id"));
                precioFinal.setPrecioVueloFinal_cantidad(this.getRs().getDouble("precioVueloFinal_cantidad"));
                
                registros.add(precioFinal);
            }
            
            return registros;
        }catch(SQLException e){
            this.getMensaje(e);
            return null;
        }
    }

    @Override
    public boolean eliminarPrecioVueloFinal(int precioVueloFinalId) {
        try{
            String sql = "DELETE FROM preciovuelofinal WHERE precioVueloFinal_id = ?";
            
            this.setPs(this.getConexion().prepareStatement(sql));
            this.getPs().setInt(1, precioVueloFinalId);
            this.getPs().execute();
            
            return true;
        }catch(SQLException e){
            this.getMensaje(e);
            return false;
        }
    }

    @Override
    public boolean actualizarPrecioVueloFinal(PrecioVueloFinal precio_vuelo_final) {
        try{
            String sql = "UPDATE preciovuelofinal SET precioVueloFinal_cantidad = ? WHERE precioVueloFinal_id = ?";
            
            this.setPs(this.getConexion().prepareStatement(sql));
            this.getPs().setDouble(1, precio_vuelo_final.getPrecioVueloFinal_cantidad());
            this.getPs().setInt(2, precio_vuelo_final.getPrecioVueloFinal_id());
            this.getPs().execute();
            
            return true;
        }catch(SQLException e){
            this.getMensaje(e);
            return false;
        }
    }

    @Override
    public boolean crearPrecioVueloFinal(PrecioVueloFinal precio_vuelo_final) {
        try{
            String sql = "INSERT INTO preciovuelofinal(precioVueloFinal_cantidad) VALUES(?)";
            this.setPs(this.getConexion().prepareStatement(sql));
            this.getPs().setDouble(1, precio_vuelo_final.getPrecioVueloFinal_cantidad());
            this.getPs().execute();
            
            return true;
        }catch(SQLException e){
            this.getMensaje(e);
            return false;
        }
    }
    
    /* Funciones adicionales */
    @Override
    public PrecioVueloFinal buscarPorPrecio(PrecioVueloFinal registro){
        PrecioVueloFinal precio = new PrecioVueloFinal();
        
        for(PrecioVueloFinal p : this.listarPreciosVuelosFinal()){
            if(p.getPrecioVueloFinal_cantidad() == registro.getPrecioVueloFinal_cantidad()){
                precio.setPrecioVueloFinal_id(p.getPrecioVueloFinal_id());
                precio.setPrecioVueloFinal_cantidad(p.getPrecioVueloFinal_cantidad());
                break;
            }
        }
        
        return precio;
    }
    
    @Override
    public PrecioVueloFinal buscarUltimoPrecio(){
        try{
            String sql = "SELECT * FROM preciovuelofinal ORDER BY precioVueloFinal_id DESC LIMIT 1";
            this.setPs(this.getConexion().prepareStatement(sql));
            this.setRs(this.getPs().executeQuery());
            PrecioVueloFinal precioVF = new PrecioVueloFinal();
            while(this.getRs().next()){
                precioVF.setPrecioVueloFinal_id(this.getRs().getInt("precioVueloFinal_id"));
                precioVF.setPrecioVueloFinal_cantidad(this.getRs().getDouble("precioVueloFinal_cantidad"));
            }
            return precioVF;
        }catch(SQLException e){
            this.getMensaje(e);
            return null;
        }
    }
    
    /* Mensaje de alerta */
    public void getMensaje(SQLException e){
        System.out.println("***** ERROR EN LA CONEXION A LA BASE DE DATOS *****");
        System.out.println("* Mensaje: " + e.getMessage());
        System.out.println("* Error: " + e.getErrorCode());
        System.out.println("* Estado: " + e.getSQLState());
    }
    
    /* Setter and Getter */
    
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
