package Controlador;

import Modelo.Aerolineas;
import Modelo.Destino;
import Modelo.Origen;
import Modelo.Pais;
import Modelo.Pasajero_Aerolinea;
import Modelo.Pasajero_Clase;
import Modelo.Pasajeros;
import Modelo.Pasajeros_Destino;
import Modelo.Pasajeros_Origen;
import Modelo.PrecioVueloFinal;
import Modelo.TipoPasajeros;
import Modelo.Usuarios;
import ModeloDAO.AerolineasDAO;
import ModeloDAO.DestinoDAO;
import ModeloDAO.OrigenDAO;
import ModeloDAO.PaisDAO;
import ModeloDAO.Pasajero_AerolineaDAO;
import ModeloDAO.Pasajero_ClaseDAO;
import ModeloDAO.PasajerosDAO;
import ModeloDAO.ProvinciaDAO;
import ModeloDAO.Pasajeros_DestinoDAO;
import ModeloDAO.Pasajeros_OrigenDAO;
import ModeloDAO.PrecioVueloFinalDAO;
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
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
import com.google.gson.*;

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
            TipoPasajerosDAO tpd = null;
            PasajerosDAO psjd = null;
            PrecioVueloFinalDAO pvfd = null;
            Pasajero_AerolineaDAO pad = null;
            Pasajero_ClaseDAO pcd = null;
            Pasajeros_OrigenDAO pod = null;
            Pasajeros_DestinoDAO pdd = null;
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
                tpd = new TipoPasajerosDAO();
                psjd = new PasajerosDAO();
                pvfd = new PrecioVueloFinalDAO();
                pad = new Pasajero_AerolineaDAO();
                pcd = new Pasajero_ClaseDAO();
                pod = new Pasajeros_OrigenDAO();
                pdd = new Pasajeros_DestinoDAO();
                
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
                            
                            if(tipoVuelo.equals("Ida-Regreso")){
                                List<String> vuelo = (List<String>) informacion.get(0);
                                informacion.remove(0);
                                List<Pasajeros> pasajero = new ArrayList<>();
                                for(Object p : informacion){
                                    pasajero.add((Pasajeros) p);
                                }

                                request.setAttribute("vuelo", vuelo);
                                request.setAttribute("pasajero", pasajero);
                            }else{
                                request.setAttribute("InformacionPasajeros", informacion);
                            }
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
                    JSONObject informacion = (JSONObject) request.getAttribute("informacion");
                    
                    //almacenar precio del vuelo seleccionado.
                    JSONObject registro = informacion.getJSONObject("registro");
                   
                    boolean resultado = AlmacenarPrecioVuelo(registro, pvfd);
                    
                    if(resultado){
                        //almacenar pasajeros...
                        JSONObject data = informacion.getJSONObject("PasajerosInfo");
                        resultado = AlmacenarPasajeros(data, psjd, pvfd, tpd, registro);
                        
                        if(resultado){
                            resultado = AlmacenarPasajeroAerolinea(data, registro, psjd, ad, pad);
                            
                            if(resultado){
                                JSONObject informacionPago = informacion.getJSONObject("InformacionPago");
                                resultado = CrearUsuarioAPropietario(informacionPago, psjd, ud);
                                
                                if(resultado){
                                    resultado = AlmacenarPasajeroClase(informacionPago, data, psjd, pcd);
                                    
                                    if(resultado){
                                        //vamos ir almacenando los diferentes vuelos que poseemos.
                                        resultado = AlmacenarVuelosIda(registro, ad, pd, od, dd);
                                        
                                        if(resultado){
                                            //esta funcion va a manejar las tablas intermedia (pasajero_origen) y (pasajero_destino)
                                            AlmacenarPasajerosVuelos(data, pd, psjd, od, dd, pod, pdd);
                                        }
                                    }
                                }
                            }
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
            
            //pasaremos este string a nuestro pago de ida final....          
            JSONObject data = new JSONObject(contenido);
            
            //definimos nuestras cabeceras...
            response.setHeader("Access-Control-Allow-Methods", "POST");
            response.setHeader("Content-Type", "application/json");

            //redireccionamos a otro apartado.
            redireccionar = "ReservaControllers?accion=pagarIda";
            request.setAttribute("informacion", data);
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
                String fechaRegreso = request.getParameter("return");
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
    
    public void AlmacenarPasajerosVuelos(JSONObject data, PaisDAO pd, PasajerosDAO psjd, OrigenDAO od, DestinoDAO dd, Pasajeros_OrigenDAO pod, Pasajeros_DestinoDAO pdd){
        int numeroRegistros = Integer.parseInt(data.getString("registros"));
        
        /* Almacenamiento origen */
        Pasajeros_Origen psjOrigen = new Pasajeros_Origen();
        psjOrigen.setPasajeroOrigenId(0);
        
        Origen idOrigenes = od.OrigenId();
               
        for(int i = 1; i < numeroRegistros+1; i++){
            String cedula = data.getJSONObject("registro-"+Integer.toString(i)).getString("Cedula-"+Integer.toString(i));
            
            Pasajeros pasajero = psjd.BuscarPorCedula(cedula);
            psjOrigen.setPasajeroId(pasajero.getPasajeros_id());
            psjOrigen.setOrigenId(idOrigenes.getId());
            
            pod.crearPasajeroOrigen(psjOrigen);
        }
       
        /* Almacenamiento destino */
        Pasajeros_Destino psjDestino = new Pasajeros_Destino();
        psjDestino.setPasajeroDestinoId(0);

        Destino idDestino = dd.idDestino();

        for(int i = 1; i < numeroRegistros+1; i++){
            String cedula = data.getJSONObject("registro-"+Integer.toString(i)).getString("Cedula-"+Integer.toString(i));

            Pasajeros pasajero = psjd.BuscarPorCedula(cedula);
            psjDestino.setPasajeroId(pasajero.getPasajeros_id());
            psjDestino.setDestinoId(idDestino.getId());

            pdd.crearPasajerosDestino(psjDestino);
        }

    }
    
    
    public boolean AlmacenarVuelosIda(JSONObject registroVuelos, AerolineasDAO ad, PaisDAO pd, OrigenDAO org, DestinoDAO dst){
        String paisSalida = registroVuelos.getString("PaisSalida");
        String paisLlegada = registroVuelos.getString("PaisLlegada");
        
        String horaSalida = registroVuelos.getString("HoraSalida");
        String horaLlegada = registroVuelos.getString("HoraLlegada");
        
        String fechaDisponible = registroVuelos.getString("FechaDisponible");
        
        String aerolinea = registroVuelos.getString("Aerolinea");
        
        Origen vueloOrigen = new Origen();
        vueloOrigen.setFecha(fechaDisponible);
        vueloOrigen.setHora_salida(horaSalida);
        vueloOrigen.setHora_llegada(horaLlegada);
        
        Aerolineas idAerolinea = ad.buscarPorNombre(aerolinea);
        Pais idPaisSalida = pd.buscarPorNombre(paisSalida);
        
        vueloOrigen.setAerolinea_id(idAerolinea.getId());
        vueloOrigen.setPais_id(idPaisSalida.getId());
        
        Destino vueloDestino = new Destino();
        vueloDestino.setFecha(fechaDisponible);
        vueloDestino.setHora_salida(horaSalida);
        vueloDestino.setHora_llegada(horaLlegada);
        vueloDestino.setAerolinea_id(idAerolinea.getId());
        
        Pais idPaisLlegada = pd.buscarPorNombre(paisLlegada);
        
        vueloDestino.setPais_id(idPaisLlegada.getId());
        
        boolean resultado = org.insertarDatos(vueloOrigen);
        
        if(resultado){
            resultado = dst.insertarDatos(vueloDestino);
            
            if(resultado){
                return true;
            }
        }
        
        return false;
    }
    
    public boolean AlmacenarPasajeroClase(JSONObject informacionPago, JSONObject pasajerosInfo, PasajerosDAO psjd, Pasajero_ClaseDAO pcd){
        List<Pasajero_Clase> registroPasajerosClase = new ArrayList<>();
        
        String clase = informacionPago.getString("Clase");
        
        Pasajero_Clase pasajeroClase = new Pasajero_Clase();
        pasajeroClase.setPasajero_clase_id(0);
        
        if(clase.toLowerCase().equals("primera")){
            pasajeroClase.setClaseId(2);
        }else if(clase.toLowerCase().equals("turista")){
            pasajeroClase.setClaseId(1);
        }else if(clase.toLowerCase().equals("ejecutiva")){
            pasajeroClase.setClaseId(3);
        }
        
        int numeroRegistros = Integer.parseInt(pasajerosInfo.getString("registros"));
        
        for(int i = 1; i < numeroRegistros+1; i++){
            String cedula = pasajerosInfo.getJSONObject("registro-"+Integer.toString(i)).getString("Cedula-"+Integer.toString(i));
            
            Pasajeros pasajeroId = psjd.BuscarPorCedula(cedula);
            pasajeroClase.setPasajeroId(pasajeroId.getPasajeros_id());
            
            registroPasajerosClase.add(pasajeroClase);
        }
        
        boolean resultado = pcd.crearPasajeroClase(registroPasajerosClase);
        
        return resultado;
    }
    
    public boolean CrearUsuarioAPropietario(JSONObject informacionPago, PasajerosDAO psjd, UsuariosDAO ud){
        String correoElectronico = informacionPago.getString("CorreoElectronico");
        Pasajeros idPasajero = psjd.BuscarPorCorreo(correoElectronico);
        
        Usuarios usuario = new Usuarios();
        usuario.setUsuarioId(0);
        usuario.setPasajeroId(idPasajero.getPasajeros_id());
        
        boolean resultado = ud.crearUsuario(usuario);
        
        return resultado;
    }
    
    /* Esta funcion manipula tabla intermedia, para la insercion de indices de diferentes 
       tablas relacionadas.
    */
    public boolean AlmacenarPasajeroAerolinea(JSONObject data, JSONObject registro , PasajerosDAO psjd, AerolineasDAO ad, Pasajero_AerolineaDAO pad){
        int registros = data.getInt("registros");
        List<Pasajero_Aerolinea> registrosAerolineas = new ArrayList<>();
        
        for(int i = 1; i < registros+1; i++){
            JSONObject pasajeros = data.getJSONObject("registro-"+ Integer.toString(i));
            String cedula = pasajeros.getString("Cedula-"+Integer.toString(i));
            String aerolinea = registro.getString("Aerolinea");
                    
            Pasajero_Aerolinea pasajeroAerolinea = new Pasajero_Aerolinea();
            
            Pasajeros idPasajero = psjd.BuscarPorCedula(cedula); 
            Aerolineas idAerolinea = ad.buscarPorNombre(aerolinea);
            
            pasajeroAerolinea.setPasajeroId(idPasajero.getPasajeros_id());
            pasajeroAerolinea.setAerolineaId(idAerolinea.getId());
            
            registrosAerolineas.add(pasajeroAerolinea);
        }
        
        boolean resultado = pad.crearPasajeroAerolinea(registrosAerolineas);
        
        return resultado;
    }
    
    public boolean AlmacenarPrecioVuelo(JSONObject registro, PrecioVueloFinalDAO pvfd){
        PrecioVueloFinal precioVuelo = new PrecioVueloFinal();
        precioVuelo.setPrecioVueloFinal_id(0);
        
        precioVuelo.setPrecioVueloFinal_cantidad( Double.parseDouble(registro.getString("PrecioVuelo")));
        
        /* Introducir en la db */       
        boolean resultado = pvfd.crearPrecioVueloFinal(precioVuelo);
       
        
        return resultado;
    }
    
    public boolean AlmacenarPasajeros(JSONObject data, PasajerosDAO psjd, PrecioVueloFinalDAO pvfd,TipoPasajerosDAO tpd, JSONObject rgstr){
        int registros = data.getInt("registros");
        List<Pasajeros> ListadoPasajeros = new ArrayList<>();

        for(int i = 1; i < registros+1; i++){
            JSONObject pasajeros = data.getJSONObject("registro-"+ Integer.toString(i));

            String tipoPasajero = pasajeros.getString("TipoPasajero-"+Integer.toString(i));
            String cedula = pasajeros.getString("Cedula-"+Integer.toString(i));
            
            /*Datos Comunes*/
            String nombres = "";
            String apellidos = "";

            String[] na = pasajeros.getString("Nombres-"+Integer.toString(i)).split(" ");
            for(int n = 0; n < na.length; n++){
                if(n == 0 || n == 1){
                    nombres += na[n];
                }else if(n == 2 || n == 3){
                    apellidos += na[n];
                }
            }

            //registro a almacenar
            Pasajeros psj = new Pasajeros();
            psj.setPasajeros_id(0);
            psj.setPasajeros_nombre(nombres);
            psj.setPasajeros_apellido(apellidos);
            psj.setPasajeros_cedula(cedula);
            psj.setTipoPasajeroId(3);
            
            if(!(tipoPasajero.equals("Niño"))){
                psj.setPasajeros_celular(pasajeros.getString("Celular-"+Integer.toString(i)));
                psj.setPasajeros_correo(pasajeros.getString("Email-"+Integer.toString(i)));
                
                if(tipoPasajero.equals("Hombre")){
                    psj.setTipoPasajeroId(1);
                }else{
                    psj.setTipoPasajeroId(2);
                }
            }
             
            /*buscando precioVueloFinal almacenado en la DB*/
            PrecioVueloFinal precioFinal = pvfd.buscarUltimoPrecio();
            psj.setPrecioVueloFinalId(precioFinal.getPrecioVueloFinal_id());
            /*buscando precioVueloFinal almacenado en la DB*/
            
            ListadoPasajeros.add(psj);
        }
        
        boolean resultado = psjd.crearPasajero(ListadoPasajeros);
        return resultado;
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
            if(tipoPasajero.equals("nino")){
                tipo_pasajero.setNombre("Niño");
            }else{
                tipo_pasajero.setNombre(tipoPasajero); 
            }
            
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
