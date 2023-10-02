/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.example.sistemareservadevuelos.Controlador;

import com.example.sistemareservadevuelos.Modelo.Genero;
import com.example.sistemareservadevuelos.Modelo.Provincia;
import com.example.sistemareservadevuelos.Modelo.TipoPasajeros;
import com.example.sistemareservadevuelos.Modelo.Usuarios;
import com.example.sistemareservadevuelos.ModeloDAO.GeneroDAO;
import com.example.sistemareservadevuelos.ModeloDAO.ProvinciaDAO;
import com.example.sistemareservadevuelos.ModeloDAO.TipoPasajerosDAO;
import com.example.sistemareservadevuelos.ModeloDAO.UsuariosDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author denni
 */
@WebServlet(name = "ClienteControllers", urlPatterns = {"/ClienteControllers"})
public class ClienteControllers extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //dentro de este punto lo que haremos es obtener los datos...
        if(request.getRequestURI().equals("/ReservaDeVuelos/ClienteControllers")){
            String accion = request.getParameter("accion");
            String redireccionar = "";
            
            UsuariosDAO usd = null;
            try {
                //declaramos nuestros daos
                usd = new UsuariosDAO();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClienteControllers.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(accion == null || accion.equals("informacion")){
                //cuando terminemos de obtener el valor lo que vamos a realizar a continuacion es sacarnos la informacion de la db
                int id = Integer.parseInt(request.getParameter("id"));
                Usuarios usr = new Usuarios();
                usr.setId(id);
                
                List<Usuarios> registro = usd.filtrarRegistro(usr);
                
                if(registro.size() != 0){
                    request.setAttribute("info", registro);
                    redireccionar = "Pages/Cliente/index.jsp";
                }
                
            }else if(accion.equals("vuelos")){
                int id = Integer.parseInt(request.getParameter("id"));
                Usuarios usr = new Usuarios();
                usr.setId(id);
                
                //luego de eso vamos a traernos el registro.
                List<Usuarios> registro = usd.filtrarRegistro(usr);
                
                
                request.setAttribute("info", registro);
                redireccionar = "Pages/Cliente/vuelos.jsp";
            }else if(accion.equals("logout")){
                //cuando el usuario le de al boton lo tenemos que sacas de la sesion
                redireccionar = "/login";
            }
            
            request.getRequestDispatcher(redireccionar).forward(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        TipoPasajerosDAO tpd = null;
        GeneroDAO gd = null;
        ProvinciaDAO pvd = null;
        UsuariosDAO ud = null;
        try {
            tpd = new TipoPasajerosDAO();
            gd = new GeneroDAO();
            pvd = new ProvinciaDAO();
            ud = new UsuariosDAO();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteControllers.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(request.getRequestURI().equals("/ReservaDeVuelos/ClienteControllers")){
            
            //llegados a este punto lo que haremos es generar las respectivas actualizaciones de los datos....
            String redireccionar = "";
            String accion = request.getParameter("accion");
            
            if(accion.equals("actualizarDatos")){
                //obtenemos los datos sacados del formulario...
                int id = Integer.parseInt(request.getParameter("id"));
                String nombre = request.getParameter("Nombre");
                String apellido = request.getParameter("Apellido");
                String correo = request.getParameter("Correo");
                String celular = request.getParameter("Celular");
                String cedula = request.getParameter("Cedula");
                String tipoPasajero = request.getParameter("TipoPasajero");
                String genero = request.getParameter("Genero");
                String provincia = request.getParameter("Provincia");
                
                if(!nombre.equals("") && !apellido.equals("") && !correo.equals("") && !celular.equals("") &&
                   !cedula.equals("") && !tipoPasajero.equals("") && !genero.equals("") && !provincia.equals("")){
                    //cuando todas nuestras entradas terminen encontrandose vacias... lo que haremos a continuacion sera una actualizacion de la inforamcion
                    //de dicho usuario....
                    Usuarios us = new Usuarios();
                    us.setId(id);
                    us.setNombre(nombre);
                    us.setApellido(apellido);
                    us.setCorreo(correo);
                    us.setCelular(celular);
                    us.setCedula(cedula);
                    
                    //A continuacion son tres atributos el cual debemos generarlos para solamente traernos su identificador.
                    
                    //1.- primero pasamos a convertir nuestro tipoPasajero
                    TipoPasajeros tp = new TipoPasajeros();
                    tp.setNombre(tipoPasajero);
                    List<TipoPasajeros> registro = tpd.filtrarRegistro(tp);
                    
                    //2.- segundo pasamos a convertir nuestro genero.
                    Genero g = new Genero();
                    g.setTipo(genero);
                    List<Genero> registro2 = gd.filtrarRegistro(g);
                    
                    //3.- tercero pasamos a convertir nuestra provincia..
                    Provincia pv = new Provincia();
                    pv.setNombre(provincia);
                    List<Provincia> registro3 = pvd.filtrarRegistro(pv);
                    
                    //despues de eso... 
                    us.setPasajeroId(registro.get(0).getId());
                    us.setGeneroId(registro2.get(0).getId());
                    us.setProvinciaId(registro3.get(0).getId());
                    
                    
                    //lo que nos tocaria al final es realizar una actualizacion respectiva de los datos...
                    boolean actualizarinfo = ud.actualizarRegistro(us);
                    redireccionar = "ClienteControllers?accion=perfil";
                    
                    //vamos a pasar cierto tipo de informacion...
                    
                    //cuando haya sido actualizado la informacion lo que haremos a continuacion es volver a reestablecer los valores.
                    us.setProvincia(provincia);
                    us.setTipoPasajero(tipoPasajero);
                    us.setGenero(genero);
                    
                    request.setAttribute("informacion", us);
                    
                   
                }
            }else if(accion.equals("perfil")){
                
             
                Usuarios us = (Usuarios) request.getAttribute("informacion");
                List<Usuarios> listado = new ArrayList<>();
                listado.add(us);
                
                request.setAttribute("info", listado);
                redireccionar = "Pages/Cliente/index.jsp";
            }
            
            
            request.getRequestDispatcher(redireccionar).forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
