package Controlador;

import Modelo.Aerolineas;
import Modelo.Destino;
import Modelo.Pais;
import Modelo.Pasajeros;
import Modelo.TipoPasajeros;
import ModeloDAO.AerolineasDAO;
import ModeloDAO.DestinoDAO;
import ModeloDAO.OrigenDAO;
import ModeloDAO.PaisDAO;
import ModeloDAO.ProvinciaDAO;
import ModeloDAO.Pasajeros_DestinoDAO;
import ModeloDAO.Pasajeros_OrigenDAO;
import ModeloDAO.TipoPasajerosDAO;
import ModeloDAO.Usuario_PerfilDAO;
import ModeloDAO.UsuariosDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author denni
 */
@WebServlet(name = "ReservaControllers", urlPatterns = {"/ReservaControllers", "/informacion"})
public class ReservaControllers extends HttpServlet {
    
    private final List<List> almacenar;
    
    
    public ReservaControllers(){
        almacenar = new ArrayList<>();
    }
    
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
        String redireccionar = "index.jsp";
        
        if(request.getRequestURI().equals("/ReservaDeVuelos/ReservaControllers")){
          
            String accion = request.getParameter("accion");
            
            if(accion.equals("reservar")){
                
                String opcionVuelo = request.getParameter("OpcionVuelo");
                List<String> datos = new ArrayList<>();

                String origen = request.getParameter("from");
                String destino = request.getParameter("to");
                String fecha_salida = request.getParameter("deparure");
                String numero_pasajeros = request.getParameter("pasajeros");

                datos.add(opcionVuelo);
                datos.add(origen);
                datos.add(destino);
                datos.add(numero_pasajeros);
                datos.add(fecha_salida);

                if(opcionVuelo.equals("Ida-Regreso")){
                    String fecha_llegada = request.getParameter("return");    
                    datos.add(fecha_llegada);
                }

                redireccionar = "Pages/Reserva/reserva.jsp";
                request.setAttribute("datos", datos);
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
       
        String redireccionar = "";
        if(request.getRequestURI().equals("/ReservaDeVuelos/ReservaControllers")){
            String accion = request.getParameter("accion");
            
            PaisDAO pd = null;
            ProvinciaDAO pvd = null;
            Usuario_PerfilDAO upd = null;
            OrigenDAO od = null;
            DestinoDAO dd = null;
            UsuariosDAO ud = null;
            Pasajeros_OrigenDAO uod = null;
            Pasajeros_DestinoDAO udd = null;
            AerolineasDAO ad = null;
            try {
                pd = new PaisDAO();
                pvd = new ProvinciaDAO();
                upd = new Usuario_PerfilDAO();
                od = new OrigenDAO();
                dd = new DestinoDAO();
                ud = new UsuariosDAO();
                uod = new Pasajeros_OrigenDAO();
                udd = new Pasajeros_DestinoDAO();
                ad = new AerolineasDAO();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ReservaControllers.class.getName()).log(Level.SEVERE, null, ex);
            }
           
            switch (accion) {
                case "reservar" -> {
                    String opcionVuelo = request.getParameter("OpcionVuelo");
                   
                    List<String> datos = new ArrayList<>();
                    
                    String origen = request.getParameter("from");
                    String destino = request.getParameter("to");
                    String fecha_salida = request.getParameter("deparure");
                    String numero_pasajeros = request.getParameter("pasajeros");
                    
                    datos.add(opcionVuelo);
                    datos.add(origen);
                    datos.add(destino);
                    datos.add(numero_pasajeros);
                    datos.add(fecha_salida);
                    
                    if(opcionVuelo.equals("Ida-Regreso")){
                        String fecha_llegada = request.getParameter("return");    
                        datos.add(fecha_llegada);
                    }
                    
                    
                    redireccionar = "Pages/Reserva/reserva.jsp";
                    request.setAttribute("datos", datos);
                }
                
                case "perfil_vuelo" -> {
                    String tipoVuelo = request.getParameter("TipoVuelo");
                    
                    if(tipoVuelo.equals("Ida")){
                        redireccionar = "Pages/Reserva/vuelosIda.jsp";
                    }else if(tipoVuelo.equals("Ida-Regreso")){
                        redireccionar = "Pages/Reserva/vuelos.jsp";
                    }
                    
                    if(tipoVuelo.equals("Ida") || tipoVuelo.equals("Ida-Regreso")){
                        
                        try{
                            List<Object> informacion = InformacionFormulario(request, tipoVuelo);
                            request.setAttribute("InformacionPasajeros", informacion);
                        }catch(ClassNotFoundException e){
                            System.out.println("[!] Error no pudimos encontrar la clase....");
                        }catch(IndexOutOfBoundsException e){
                            System.out.println("Mensaje: " + e.getMessage());
                            System.out.println("Causa: " + e.getCause());
                            System.out.println("Localizar Mensaje: " + e.getLocalizedMessage());
                            System.out.println("[!] Uff existe un error por el momento, vuelve intentarlo mas tarde...  ");
                        }
                        
                    }

                }
                
                case "pagar" -> {
                    
                }
                
                case "pagarIda" -> {    
                    //informacion...
                    String registro = (String) request.getAttribute("informacion");
                    registro = registro.replace("[{", "");
                    registro = registro.replace("}}]", "");
                    String[] datos = registro.split(",");
                    
                    Destino destinoVuelo = new Destino();
                    Pasajeros pasajero = new Pasajeros();
                    
                    for(String j : datos){
                        String[] dato = j.split(":");
                        String value = dato[1];
                            
                        switch(dato[0]){
                            case "HoraSalida":
                                destinoVuelo.setId(0);
                                destinoVuelo.setHora_salida(value);
                                break;
                                
                            case "HoraLlegada":
                                destinoVuelo.setHora_llegada(value);
                                break;
                            
                            case "FechaDisponible":
                                destinoVuelo.setFecha(value);
                                break;
                                
                            case "PaisLlegada":
                                List<Pais> r_pais = pd.tipoPais(value);
                                destinoVuelo.setPais_id(r_pais.get(0).getId());
                                break;
                                
                            case "Aerolinea":
                                Aerolineas ar = new Aerolineas();
                                ar.setNombre(dato[1]);

                                List<Aerolineas> r_aerolinea =  ad.filtrarRegistro(ar);
                                destinoVuelo.setAerolinea_id(r_aerolinea.get(0).getId());
                                break;
                            
                            default:
                                break;
                        }
                        
                    }
                    
                    redireccionar = "/";
                }
                
                case "login" -> System.out.println(this.getAlmacenar());
                    //redireccionar = "Pages/Reserva/reserva.jsp";
                default -> {
                }
            }
            
            request.getRequestDispatcher(redireccionar).forward(request, response);
        }else if(request.getRequestURI().equals("/ReservaDeVuelos/informacion")){
            String contenido = request.getParameter("resultadoFinal");
            
            //definimos nuestras cabeceras...
            response.setHeader("Access-Control-Allow-Methods", "POST");
            response.setHeader("Content-Type", "application/json");

            //redireccionamos a otro apartado.
            redireccionar = "ReservaControllers?accion=pagarIda";
            request.setAttribute("informacion", contenido);
            request.getRequestDispatcher(redireccionar).forward(request, response);
        }
        
    }
    
    
    public List<Object> InformacionFormulario(HttpServletRequest request, String tipo) throws ClassNotFoundException{
        List<Object> informacion = new ArrayList<>();
        
        List<String> infoVuelo = new ArrayList<>();
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        String estado_from = request.getParameter("ciudad_from");
        String estado_to = request.getParameter("ciudad_to");
        String nPasajeros = request.getParameter("pasajeros");
        String fechaSalida = request.getParameter("deparure");
        
        infoVuelo.add(from);
        infoVuelo.add(to);
        infoVuelo.add(estado_from);
        infoVuelo.add(estado_to);
        infoVuelo.add(nPasajeros);
        infoVuelo.add(fechaSalida);
        
        switch(tipo){
            case "Ida" -> {
                informacion.add(infoVuelo);
                List<Pasajeros> informacionFormulario = this.formPasajeros(request, Integer.parseInt(infoVuelo.get(4))+1);
                for(Pasajeros j : informacionFormulario){
                    informacion.add(j);
                }
            }
            
            case "Ida-Regreso" -> {
                String fechaRegreso = request.getParameter("deparure");
                infoVuelo.add(fechaRegreso);
                informacion.add(infoVuelo);
                
                List<Pasajeros> informacionFormulario = this.formPasajeros(request, Integer.parseInt(infoVuelo.get(4))+1);
                for(Pasajeros j : informacionFormulario){
                    informacion.add(j);
                }
                
            }
        }
        
        return informacion;
    }
    
    
    public List<Pasajeros> formPasajeros(HttpServletRequest request, int limite) throws ClassNotFoundException{
        //informacion pasajeros
        List<Pasajeros> lista_pasajeros = new ArrayList<>();
        TipoPasajerosDAO tpd = new TipoPasajerosDAO();
        TipoPasajeros tipo_pasajero = new TipoPasajeros();
        
        for(int i = 1; i < limite; i++){
            Pasajeros pasajero = new Pasajeros();
            
            String nombres = request.getParameter("Nombres-"+Integer.toString(i));
            String apellidos = request.getParameter("Apellidos-"+Integer.toString(i));
            String cedula = request.getParameter("Cedula-"+Integer.toString(i));
            String tipoPasajero = request.getParameter("tipo_pasajero-"+Integer.toString(i));
            /*  Esta seccion saca el id de cada tipo de pasajero para almacenarlo... y generar los respectivos
                procesos.
            */
            tipo_pasajero.setId(0);
            tipo_pasajero.setNombre(tipoPasajero); 
            List<TipoPasajeros> idTipoPasajero = tpd.filtrarRegistro(tipo_pasajero);
            
            pasajero.setPasajeros_nombre(nombres);
            pasajero.setPasajeros_apellido(apellidos);
            pasajero.setPasajeros_cedula(cedula);
            pasajero.setTipoPasajeroId(idTipoPasajero.get(0).getId());
            
            if(tipoPasajero.equals("Hombre") || tipoPasajero.equals("Mujer")){
                
                String email = request.getParameter("Email-"+Integer.toString(i));
                String celular = request.getParameter("Celular-"+Integer.toString(i));
                
                pasajero.setPasajeros_correo(email);
                pasajero.setPasajeros_celular(celular);
                
            }else{
                
                pasajero.setPasajeros_correo("false");
                pasajero.setPasajeros_celular("false");

            }
            
            pasajero.setPrecioVueloFinalId(0);

            lista_pasajeros.add(pasajero);
        }

        return lista_pasajeros;
    }
    
    
    public void setAlmacenar(List<String> almacenar){
        this.almacenar.add(almacenar);
    }
    
    public List<List> getAlmacenar(){
        return this.almacenar;
    }

}
