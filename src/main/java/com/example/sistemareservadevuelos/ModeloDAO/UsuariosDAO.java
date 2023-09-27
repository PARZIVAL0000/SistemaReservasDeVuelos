/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.sistemareservadevuelos.ModeloDAO;

import Config.Conexion;
import Interfaces.UsuariosInterface;
import Modelo.Usuarios;

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
public class UsuariosDAO implements UsuariosInterface{
    Connection conexion;
    PreparedStatement ps;
    ResultSet rs;
    
    
    public UsuariosDAO() throws ClassNotFoundException{
        Conexion n = new Conexion();
        conexion = n.getConexion();
    }
    
    @Override
    public List<Usuarios> listarUsuarios(){
        List<Usuarios> listadoUsuarios = new ArrayList<>();
        String cmd = "SELECT * FROM usuarios";
        try{
            ps = conexion.prepareStatement(cmd);
            rs = ps.executeQuery();
            
            while(rs.next()){
                Usuarios vl = new Usuarios();
                vl.setId(rs.getInt("usr_id"));
                vl.setNombre(rs.getString("usr_nombre"));
                vl.setApellido(rs.getString("usr_apellido"));
                vl.setCorreo(rs.getString("srv_correo"));
                vl.setCelular(rs.getString("usr_celular"));
                vl.setCedula(rs.getString("usr_cedula"));

                listadoUsuarios.add(vl);
            }
            
            return listadoUsuarios;
        }catch(SQLException e){
            System.out.println("Dentro del listado de pasajes");
            System.out.println("Error: " + e.getMessage());
            System.out.println("Causa: " + e.getCause());
            System.out.println("Estado: " + e.getSQLState());
            return null;
        }
    }
    
    public List<Usuarios> listarUsuarios2(){
        List<Usuarios> listadoUsuarios = new ArrayList<>();
        String cmd = "SELECT usuarios.usr_id, usuarios.usr_nombre, usuarios.usr_apellido, usuarios.srv_correo, usuarios.usr_celular, usuarios.usr_cedula, tipopasajero.Nombre AS tipopasajero, genero.tipo AS genero, provincia.nombre AS provincia FROM usuarios INNER JOIN tipopasajero ON usuarios.tipoPasajero_id = tipopasajero.id INNER JOIN genero ON genero.id = usuarios.genero_id INNER JOIN provincia ON provincia.id = usuarios.provincia_id;";
        try{
            ps = conexion.prepareStatement(cmd);
            rs = ps.executeQuery();
            
            while(rs.next()){
                Usuarios vl = new Usuarios();
                vl.setId(rs.getInt("usr_id"));
                vl.setNombre(rs.getString("usr_nombre"));
                vl.setApellido(rs.getString("usr_apellido"));
                vl.setCorreo(rs.getString("srv_correo"));
                vl.setCelular(rs.getString("usr_celular"));
                vl.setCedula(rs.getString("usr_cedula"));

                //en este caso lo que vamos a realizar es traernos todos los datos.
                vl.setTipoPasajero(rs.getString("tipopasajero"));
                vl.setGenero(rs.getString("genero"));
                vl.setProvincia(rs.getString("provincia"));
                
                listadoUsuarios.add(vl);
            }
            
            return listadoUsuarios;
        }catch(SQLException e){
            System.out.println("Dentro del listado de pasajes");
            System.out.println("Error: " + e.getMessage());
            System.out.println("Causa: " + e.getCause());
            System.out.println("Estado: " + e.getSQLState());
            return null;
        }
    }
    
    
    
    
    @Override
    public boolean registrarUsuario(Usuarios usuario){
        String sql = "INSERT INTO usuarios(usr_nombre, usr_apellido, srv_correo, usr_celular, usr_cedula, tipoPasajero_id, genero_id, provincia_id) VALUES(?, ?, ?, ?, ?, ?, ?, 1)";
        try{
            //Uno de los errores que podemos llegar a obtener es por la provincia_id que si no introducimos no nos permitira continuar...
            
            List<Usuarios> verificar = this.filtrarRegistro(usuario);

            if(verificar.size() == 0){
                ps = conexion.prepareStatement(sql);
                ps.setString(1, usuario.getNombre());
                ps.setString(2, usuario.getApellido());
                ps.setString(3, usuario.getCorreo());
                ps.setString(4, usuario.getCelular());
                ps.setString(5, usuario.getCedula());
                //aqui debemos
                ps.setInt(6, usuario.getPasajeroId());
                ps.setInt(7, usuario.getGeneroId());
                System.out.println(ps);
                ps.execute();
                
                return true;
            }else{
                
                return false;
            }
            
        }catch(SQLException e){
            System.out.println("***** ERROR AL MOMENTO DE INTRODUCIR AL USUARIO ****");
            System.out.println(e.getCause());
            System.out.println(e.getErrorCode());
            return false;
        }
        
        
    }
    
    
    public List<Usuarios> filtrarRegistro(Usuarios usuario){
        List<Usuarios> usl = new ArrayList<>();
        
        for(Usuarios j : this.listarUsuarios()){
            //nuestro numero de cedula deber ser unico... no debe repetirse por ninguna cirscuntancia en especifica.
            if(j.getCedula().equals(usuario.getCedula()) || j.getId() == usuario.getId()){
                usl.add(j);
                break;
            }
        }
        
        return usl;
    }
    
    
    public List<Usuarios> filtrarRegistro2(Usuarios usr){
        List<Usuarios> usl = new ArrayList<>();
        
        for(Usuarios j : this.listarUsuarios2()){
            if(j.getCorreo().equals(usr.getCorreo())){
                usl.add(j);
                break;
            }
        }
        
        return usl;
    }
    
    public List<Usuarios> filtroLogin(Usuarios usuario){
        List<Usuarios> usl = new ArrayList<>();
        
        for(Usuarios j : this.listarUsuarios()){
            if(j.getCedula().equals(usuario.getCedula()) && j.getCorreo().equals(usuario.getCorreo())){
                usl.add(j);
                break;
            }
        }
        
        return usl;
    }
    
    
    public boolean actualizarRegistro(Usuarios usuario){
        //dentro de este punto lo que haremos es actualizar dicha informacion que nos tremos
        //de nuestro formulario... 
        String sql = "UPDATE usuarios SET usr_nombre = '"+usuario.getNombre()+"', usr_apellido = '"+usuario.getApellido()+"', srv_correo = '"+usuario.getCorreo()+"', usr_celular = '"+usuario.getCelular()+"', usr_cedula = '"+usuario.getCedula()+"', tipoPasajero_id = '"+usuario.getPasajeroId()+"', genero_id = '"+usuario.getGeneroId()+"', provincia_id = '"+usuario.getProvinciaId()+"' WHERE usr_id = '"+usuario.getId()+"'";
        try{
            
            ps = conexion.prepareStatement(sql);
            ps.execute();
            
        }catch(SQLException e){
            System.out.println("**** NO PUDIMOS ACTUALIZAR EL REGISTRO DE USUARIO ****");
            System.out.println("Mensaje: " + e.getMessage());
            System.out.println("Causa: " + e.getCause());
            System.out.println("***** **** **** **** **** ***** **** **** **** **** **");
            return false;
        }
        
        return true;
    }
}
