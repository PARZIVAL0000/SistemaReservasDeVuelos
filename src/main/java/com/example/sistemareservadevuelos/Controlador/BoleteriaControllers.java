/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.example.sistemareservadevuelos.Controlador;

import Modelo.Aerolineas;
import Modelo.Pais;
import Modelo.Pasajes;
import Modelo.Vuelos;
import ModeloDAO.AerolineasDAO;
import ModeloDAO.PaisDAO;
import ModeloDAO.PasajesDAO;
import ModeloDAO.VuelosDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author denni
 */
@WebServlet(name = "BoleteriaControllers", urlPatterns = {"/BoleteriaControllers"})
public class BoleteriaControllers extends HttpServlet {

    

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
        String redireccionar = "";
        if(request.getRequestURI().equals("/ReservaDeVuelos/BoleteriaControllers")){
            String accion = request.getParameter("accion");
            System.out.println("LA ACCION ES -> " + accion);
            VuelosDAO vd = null;
            AerolineasDAO ad = null;
            PasajesDAO pd = null;
            PaisDAO psd = null;
            try {
                vd = new VuelosDAO();
                ad = new AerolineasDAO();
                pd = new PasajesDAO();
                psd = new PaisDAO();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BoleteriaControllers.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(accion.equals("regresar")){
                redireccionar = "Pages/Boleteria/perfil.jsp";
            }else if(accion.equals("crear")){
                redireccionar = "Pages/Boleteria/crear.jsp";
            }else if(accion.equals("eliminar")){
                
                int id = Integer.parseInt(request.getParameter("id"));
                Vuelos registro = new Vuelos();
                registro.setId(id);
                
                boolean eliminarRegistro = vd.eliminarRegistro(id, registro);
                redireccionar = "Pages/Boleteria/perfil.jsp";
                
            }else if(accion.equals("actualizar")){
                int id = Integer.parseInt(request.getParameter("id"));
                
                Vuelos vl = new Vuelos();
                vl.setId(id);
                
                List<Vuelos> registro = vd.filtrarRegistro(vl);
                
                request.setAttribute("registro", registro);
                redireccionar = "Pages/Boleteria/actualizar.jsp";
            
            }else if(accion.equals("generarReserva")){
                //dentro de aqui iremos obteniendo cada uno de los valores de nuestro formulario.
                String aerolinea = request.getParameter("Aerolinea");
                String pasaje = request.getParameter("Pasaje");
                String fecha_disponible = request.getParameter("FechaDisponible");
                String hora_salida = request.getParameter("HoraSalida");
                String hora_llegada = request.getParameter("HoraLlegada");
                String paisOrigen = request.getParameter("Pais_Origen");
                String paisDestino = request.getParameter("Pais_Destino");
                
                //al momento de generar una creacion, nuestro reserva siempre se va a encontrar disponible como predeterminado.
                
                //1.-Vamos a crear nuestro modelo.
                Vuelos vl = new Vuelos();
                vl.setId(0);
            }else if(accion.equals("logout")){
                //esta parte nos dice que va a salir del sistema....
                redireccionar = "Pages/Login/login.jsp";
            }
            
        }
        
        request.getRequestDispatcher(redireccionar).forward(request, response);
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
        String redireccionar = "";
        if(request.getRequestURI().equals("/ReservaDeVuelos/BoleteriaControllers")){
            String accion = request.getParameter("accion");
            System.out.println("LA ACCION ES -> " + accion);
            VuelosDAO vd = null;
            AerolineasDAO ad = null;
            PasajesDAO pd = null;
            PaisDAO psd = null;
            try {
                vd = new VuelosDAO();
                ad = new AerolineasDAO();
                pd = new PasajesDAO();
                psd = new PaisDAO();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BoleteriaControllers.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(accion.equals("modificar")){
                //aqui vamos a poner los respectivos datos traidos del formulario...
                int id = Integer.parseInt(request.getParameter("id"));
                String disponible = request.getParameter("Disponible");
                String aerolinea = request.getParameter("Aerolinea");
                String pasaje = request.getParameter("Pasaje");
                String fecha_disponible = request.getParameter("FechaDisponible");
                String hora_salida = request.getParameter("HoraSalida");
                String hora_llegada = request.getParameter("HoraLlegada");
                String paisOrigen = request.getParameter("Pais_Origen");
                String paisDestino = request.getParameter("Pais_Destino");
                
                //no vamos actualizar nuestra tabla imaginar ... lo que vamos actualizar es la tabla real.. de donde parte todo
                //la tabla VUELOS <- es la que debemos generar ciertas modificaciones...
                //lo que tenemos que hacer es traernos los identificadores de cada las tablas secundarias o relacionadas con la tabla vuelos
                //para poder hacer la respectiva actualizacion...
                //vamos a realizar la mayor parte de las cosas por pasos..
                
                //1.- extraemos el id de la tabla aerolinea.
                Aerolineas a = new Aerolineas();
                a.setNombre(aerolinea);
                List<Aerolineas> listadoAerolinea = ad.filtrarRegistro(a);
                
                
                //2.- extraemos el id de la tabla pasaje
                Pasajes p = new Pasajes();
                p.setTarifa(Double.parseDouble(pasaje));
                List<Pasajes> listadoPasajes = pd.filtrarRegistro(p);
                
                //3.- extraemos el id del pais de origen
                Pais p1 = new Pais();
                p1.setPaisNombre(paisOrigen);
                List<Pais> listadoPaisOrigen = psd.tipoPais(p1.getPaisNombre());
                
                //4.- extraemos el id del pais de destino.
                Pais p2 = new Pais();
                p2.setPaisNombre(paisDestino);
                List<Pais> listadoPaisDestino = psd.tipoPais(p2.getPaisNombre());
                
                //con los demas datos que tenemos, fecha, hora_salida, hora_llegada. No necesita que busquemos por registros..
                //ya que son datos propios de la tabla del cual vamos actualizar...
                
                
                //paso final -> actualizar la tabla vuelos.
                Vuelos vl = new Vuelos();
                vl.setId(id); // ->
                vl.setEstado(Integer.parseInt(disponible)); 
                vl.setAerolinea_id(listadoAerolinea.get(0).getId());
                vl.setPasaje_id(listadoPasajes.get(0).getId());
                vl.setFecha(fecha_disponible);
                vl.setHora_llegada(hora_llegada);
                vl.setHora_salida(hora_salida);
                vl.setPais_origen(listadoPaisOrigen.get(0).getId());
                vl.setPais_destino(listadoPaisDestino.get(0).getId());
                
                //ya que tenemos nuestro objeto... vamos a actualizar.
                System.out.println(vl.getEstado());
                boolean actualizarRegistro = vd.actualizarRegistro(vl);
                
//                redireccionar = "Pages/Boleteria/perfil.jsp";
                redireccionar = "/BoleteriaControllers?accion=regresar";
            }else if(accion.equals("regresar")){
                redireccionar = "/Pages/Boleteria/perfil.jsp";
            }else if(accion.equals("generarReserva")){
                
                //capturamos nuestros datos para introducirlos dentro de nuestra tabla. 
                String aerolinea = request.getParameter("Aerolinea");
                String pasaje = request.getParameter("Pasaje");
                String fecha_disponible = request.getParameter("FechaDisponible");
                String hora_salida = request.getParameter("HoraSalida");
                String hora_llegada = request.getParameter("HoraLlegada");
                String paisOrigen = request.getParameter("Pais_Origen");
                String paisDestino = request.getParameter("Pais_Destino");
                String disponible = "1";
                
           
                
                //1.- extraemos el id de la tabla aerolinea.
                Aerolineas a = new Aerolineas();
                a.setNombre(aerolinea);
                List<Aerolineas> listadoAerolinea = ad.filtrarRegistro(a);
                
                
                //2.- extraemos el id de la tabla pasaje
                Pasajes p = new Pasajes();
                p.setTarifa(Double.parseDouble(pasaje));
                List<Pasajes> listadoPasajes = pd.filtrarRegistro(p);
                
                //3.- extraemos el id del pais de origen
                Pais p1 = new Pais();
                p1.setPaisNombre(paisOrigen);
                List<Pais> listadoPaisOrigen = psd.tipoPais(p1.getPaisNombre());
                
                //4.- extraemos el id del pais de destino.
                Pais p2 = new Pais();
                p2.setPaisNombre(paisDestino);
                List<Pais> listadoPaisDestino = psd.tipoPais(p2.getPaisNombre());
                
                
                Vuelos vl = new Vuelos();
                vl.setId(0); // ->
                vl.setEstado(Integer.parseInt(disponible)); 
                vl.setAerolinea_id(listadoAerolinea.get(0).getId());
                vl.setPasaje_id(listadoPasajes.get(0).getId());
                vl.setFecha(fecha_disponible);
                vl.setHora_llegada(hora_llegada);
                vl.setHora_salida(hora_salida);
                vl.setPais_origen(listadoPaisOrigen.get(0).getId());
                vl.setPais_destino(listadoPaisDestino.get(0).getId());
                
                //vamos a ver los datos ahora para verificar si estan correctos.
                System.out.println("=====================");
                System.out.println(vl.getId());
                System.out.println(vl.getEstado());
                System.out.println(vl.getAerolinea_id());
                System.out.println(vl.getPasaje_id());
                System.out.println(vl.getFecha());
                System.out.println(vl.getHora_llegada());
                System.out.println(vl.getHora_salida());
                System.out.println(vl.getPais_origen());
                System.out.println(vl.getPais_destino());
                System.out.println("=====================");
                
                boolean insertarRegistro = vd.insertarRegistro(vl);
                redireccionar = "/BoleteriaControllers?accion=regresar";
                
            }
            
            
            request.getRequestDispatcher(redireccionar).forward(request, response);
        }
        
    }

    

}
