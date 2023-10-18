package Controlador;

import Modelo.Aerolineas;
import Modelo.Destino;
import Modelo.Origen;
import Modelo.Pais;
import Modelo.Pasajeros;
import Modelo.Provincia;
import Modelo.Usuario_Destino;
import Modelo.Usuario_Origen;
import Modelo.Usuario_Perfil;
import Modelo.Usuarios;
import ModeloDAO.AerolineasDAO;
import ModeloDAO.DestinoDAO;
import ModeloDAO.OrigenDAO;
import ModeloDAO.PaisDAO;
import ModeloDAO.ProvinciaDAO;
import ModeloDAO.Usuario_DestinoDAO;
import ModeloDAO.Usuario_OrigenDAO;
import ModeloDAO.Usuario_PerfilDAO;
import ModeloDAO.UsuariosDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;
/**
 *
 * @author denni
 */
@WebServlet(name = "ReservaControllers", urlPatterns = {"/ReservaControllers"})
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
        
        if(request.getRequestURI().equals("/ReservaDeVuelos/ReservaControllers")){
          
            String accion = request.getParameter("accion");
            String redireccionar = "index.jsp";
            
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
       
        if(request.getRequestURI().equals("/ReservaDeVuelos/ReservaControllers")){
            String accion = request.getParameter("accion");
            String redireccionar = "";
            
            
            PaisDAO pd = null;
            ProvinciaDAO pvd = null;
            Usuario_PerfilDAO upd = null;
            OrigenDAO od = null;
            DestinoDAO dd = null;
            UsuariosDAO ud = null;
            Usuario_OrigenDAO uod = null;
            Usuario_DestinoDAO udd = null;
            AerolineasDAO ad = null;
            try {
                pd = new PaisDAO();
                pvd = new ProvinciaDAO();
                upd = new Usuario_PerfilDAO();
                od = new OrigenDAO();
                dd = new DestinoDAO();
                ud = new UsuariosDAO();
                uod = new Usuario_OrigenDAO();
                udd = new Usuario_DestinoDAO();
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
                        List<Object> informacion = InformacionFormulario(request, tipoVuelo);
                        
                        
                        request.setAttribute("InformacionPasajeros", informacion);
                    }

                }
                
                case "pagar" -> {
                    //vamos a obtener la mayoria de todos los datos de nuestro formulario para realizar el proceso de introducirlo dentro de
                    //nuestra base de datos...
                    try{
                        String cedulaCompra = (request.getParameter("dniCompra") == null) ? "" : request.getParameter("dniCompra");
                        //vamos a obtener las partes de la fecha de vencimiento de la cedula del usuario
                        String diaCedula = (request.getParameter("dia") == null) ? "" : request.getParameter("dia");
                        String mesCedula = (request.getParameter("mes") == null) ? "" : request.getParameter("mes");
                        String yearCedula = ( request.getParameter("year") == null) ? "" : request.getParameter("year");
                        //nombre del comprador titular....
                        String nombreTitular = ( request.getParameter("Names") == null) ? "" : request.getParameter("Names");
                        String correoTitular = ( request.getParameter("Emails") == null) ? "" : request.getParameter("Emails");
                        
                        
                        //los datos anteriores deben encontrarse validados para que se pueda pasar al siguiente paso.
                        if(!cedulaCompra.equals("") && !diaCedula.equals("") && !mesCedula.equals("") &&
                                !yearCedula.equals("") && !nombreTitular.equals("") && !correoTitular.equals("")){
                            
                            //vamos a validar nuestro numero de cedula correspondiente.
                            Provincia provincia = new Provincia();
                            provincia.setNombre("");
                            provincia.setId(Integer.parseInt(cedulaCompra.substring(0, 2)));
                            List<Provincia> listadoProvincias = pvd.filtrarRegistro(provincia);
                            
                            
                            if(!listadoProvincias.isEmpty()){
                                //generaremos el proceso que valida por modulo 10.
                                //despues a continuacion hacemos el proceso de hacer la validacion de la cuenta...
                                int resultadoFinal = 0;
                                //1.- primero multiplicamos por el coeficiente del modulo 10 2.1.2.1....
                                int j = 1;
                                for(int digito = 0; digito < cedulaCompra.length()-1; digito++){
                                    
                                    if(digito%2==0){
                                        
                                        int resultado = Integer.parseInt(cedulaCompra.substring(digito, j))*2;
                                        
                                        if(resultado >= 10){
                                            resultado = resultado - 9;
                                        }
                                        
                                        resultadoFinal += resultado;
                                    }else{
                                        
                                        int resultado = Integer.parseInt(cedulaCompra.substring(digito, j))*1;
                                        
                                        if(resultado >= 10){
                                            resultado = resultado - 9;
                                        }
                                        
                                        resultadoFinal += resultado;
                                        
                                    }
                                    
                                    j+=1;
                                }
                                
                                int decimalMayor = Integer.parseInt(Integer.toString(resultadoFinal).substring(0,1)+"0")+10;
                                resultadoFinal = decimalMayor - resultadoFinal;
                                
                                if( resultadoFinal == Integer.parseInt(cedulaCompra.substring(cedulaCompra.length()-1, cedulaCompra.length())) ){
                                    
                                    
                                    //vamos a listar nuestra estructura de vuelo
                                    //desde a -> vuelo ida
                                    String VueloIda_desde = request.getParameter("VueloIda_desde");
                                    String VueloIda_a = request.getParameter("VueloIda_a");
                                    //nos traeremos las fecha y hora de vuelos de ida.
                                    String VueloIda_fecha = request.getParameter("VueloIda_fecha");
                                    String VueloIda_horaSalida = request.getParameter("VueloIda_horaSalida");
                                    String VueloIda_horaLlegada = request.getParameter("VueloIda_horaLlegada");
                                    String VueloIda_aerolinea = request.getParameter("VueloIda_aerolinea");
                                    //---------------------------------------------------------------------------------------
                                    
                                    //nos traeremos las fechas y hora de vuelos de regreso.
                                    //desde a -> vuelo regreso
                                    String VueloRegreso_desde = request.getParameter("VueloRegreso_desde");
                                    String VueloRegreso_a = request.getParameter("VueloRegreso_a");
                                    String VueloRegreso_fecha = request.getParameter("VueloRegreso_fecha");
                                    String VueloRegreso_horaSalida = request.getParameter("VueloRegreso_horaSalida");
                                    String VueloRegreso_horaLlegada = request.getParameter("VueloRegreso_horaLlegada");
                                    String VueloRegreso_aerolinea = request.getParameter("VueloRegreso_aerolinea");
                                    //----------------------------------------------------------------------------------------
                                    //----------------------------------------------------------------------------------------
                                    
                                    //vamos a traernos el identificador del pais que terminanos seleccionando...
                                    Aerolineas aerolinea_ida = new Aerolineas();
                                    aerolinea_ida.setId(0);
                                    aerolinea_ida.setNombre(VueloIda_aerolinea.toLowerCase());
                                    aerolinea_ida.setEstado(1);
                                    
                                    Aerolineas aerolinea_regreso = new Aerolineas();
                                    aerolinea_regreso.setId(0);
                                    aerolinea_regreso.setNombre(VueloRegreso_aerolinea.toLowerCase());
                                    aerolinea_regreso.setEstado(1);
                                    //tenemos nuestras respectivas entradas.....
                                    
                                    List<Aerolineas> id_vueloIda = ad.filtrarRegistro(aerolinea_ida);
                                    List<Aerolineas> id_vueloRegreso = ad.filtrarRegistro(aerolinea_regreso);
                                    
                                    //vamos a introducir la informacin en la tabla de origen... los viajes de origen del usuario..
                                    List<Pais> p1 = pd.tipoPais(VueloIda_desde);
                                    List<Pais> p2 = pd.tipoPais(VueloRegreso_desde);
                                    
                                    //---------------------------------------------
                                    Origen org1 = new Origen();
                                    Origen org2 = new Origen(); // cuando el usuario se encuentra en el otrao lado...
                                    
                                    
                                    org1.setId(0);
                                    org1.setFecha(VueloIda_fecha);
                                    org1.setHora_salida(VueloIda_horaSalida);
                                    org1.setHora_llegada(VueloIda_horaLlegada);
                                    org1.setPais_id(p1.get(0).getId());
                                    org1.setAerolinea_id(id_vueloIda.get(0).getId());
                                    
                                    
                                    org2.setId(0);
                                    org2.setFecha(VueloRegreso_fecha);
                                    org2.setHora_salida(VueloRegreso_horaSalida);
                                    org2.setHora_llegada(VueloRegreso_horaLlegada);
                                    org2.setPais_id(p2.get(0).getId());
                                    org2.setAerolinea_id(id_vueloRegreso.get(0).getId());
                                    
                                    
                                    boolean introducirOrigen1 = od.insertarDatos(org1);
                                    boolean introducirOrigen2 = od.insertarDatos(org2);
                                    
                                    
                                    //vamos a introducr la informacion en la tabla de destino...
                                    List<Pais> p_1 = pd.tipoPais(VueloIda_a);
                                    List<Pais> p_2 = pd.tipoPais(VueloRegreso_a);
                                    Destino dst1 = new Destino();
                                    Destino dst2 = new Destino();
                                    
                                    dst1.setId(0);
                                    dst1.setFecha(VueloIda_fecha);
                                    dst1.setHora_salida(VueloIda_horaSalida);
                                    dst1.setHora_llegada(VueloIda_horaLlegada);
                                    dst1.setPais_id(p_1.get(0).getId());
                                    dst1.setAerolinea_id(id_vueloIda.get(0).getId()); // Actualizar id
                                    
                                    
                                    dst2.setId(0);
                                    dst2.setFecha(VueloRegreso_fecha);
                                    dst2.setHora_salida(VueloRegreso_horaSalida);
                                    dst2.setHora_llegada(VueloRegreso_horaLlegada);
                                    dst2.setPais_id(p_2.get(0).getId());
                                    dst2.setAerolinea_id(id_vueloRegreso.get(0).getId()); // Actualizar id
                                    
                                    boolean introducirDestino1 = dd.insertarDatos(dst1);
                                    boolean introducirDestino2 = dd.insertarDatos(dst2);
                                    
                                    //----------------------------------------------------------------------------------------
                                    //vamos a obtener los datos de cada uno de nuestros usuarios el cual se encuentra ocultos..
                                    int n = Integer.parseInt(request.getParameter("NumeroPasajeros"));
                                    
                                    for(int i = 0; i < n; i++){
                                        int indice = i+1;
                                        Usuarios us = new Usuarios();
                                        us.setNombre(request.getParameter("Nombres-"+indice));
                                        us.setApellido(request.getParameter("Apellidos-"+indice));
                                        us.setCorreo(request.getParameter("Correo-"+indice));
                                        us.setCelular(request.getParameter("Telefono-"+indice));
                                        us.setCedula(request.getParameter("Cedula-"+indice));
                                        
                                        //estas dos de aqui abajo representa datos personales de la person a y el numero es el id unico de
                                        //un registro que se encuentra en otra tabla...
                                        if(request.getParameter("Pasajeros-"+indice).toLowerCase().equals("hombre")){
                                            //dentro de este punto lo que vamos a realizar a continuacion es generar un identificador
                                            us.setPasajeroId(1);
                                        }else if(request.getParameter("Pasajeros-"+indice).toLowerCase().equals("mujer")){
                                            us.setPasajeroId(2);
                                        }else if(request.getParameter("Pasajeros-"+indice).toLowerCase().equals("niño")){
                                            us.setPasajeroId(3);
                                        }
                                        
                                        //el genero de la persona.
                                        if(request.getParameter("Genero-"+indice).toLowerCase().equals("masculino")){
                                            us.setGeneroId(1);
                                        }else if(request.getParameter("Genero-"+indice).toLowerCase().equals("femenino")){
                                            us.setGeneroId(2);
                                        }else if(request.getParameter("Genero-"+indice).toLowerCase().equals("otro")){
                                            us.setGeneroId(3);
                                        }
                                        
                                        boolean introducirDatos = ud.registrarUsuario(us); //registramos nuestros usuarios...
                                        
                                        //vamos a introducir dentro de nuestra tabla de usuario_origen
                                        Usuario_Origen uo = new Usuario_Origen();
                                        List<Usuarios> usuario = ud.filtrarRegistro(us);
                                        
                                        uo.setId(0);
                                        uo.setUsuario_id(usuario.get(0).getId());
                                        //vamos a filtrar por la fecha... no importa mucho por ello ya que es un valor que puede obtenerlo muchas personas.
                                        List<Origen> listadoRegistroOrigen = od.filtroRegistro(org1);
                                        //vamos a actualizar nuestro respectivo dato....
                                        uo.setOrigen_id(listadoRegistroOrigen.get(0).getId());
                                        //                        //vamos a introducirlo dentro de la tabla..
                                        boolean registroId = uod.introducirRegistro(uo);
                                        //
                                        Usuario_Origen uo2 = new Usuario_Origen();
                                        uo2.setId(0);
                                        List<Usuarios> usuario2 = ud.filtrarRegistro(us);
                                        uo2.setUsuario_id(usuario2.get(0).getId());
                                        
                                        List<Origen> listadoRegistroOrigen2 = od.filtroRegistro(org2);
                                        uo2.setOrigen_id(listadoRegistroOrigen2.get(0).getId());
                                        boolean registroId2 = uod.introducirRegistro(uo2);
                                        
                                        //a continuacion vamos a introducir los datos para nuestra tabla intermedia usuario_destino
                                        Usuario_Destino u_d = new Usuario_Destino();
                                        u_d.setId(0);
                                        u_d.setUsuario_id(usuario.get(0).getId());
                                        List<Destino> listadoRegistroDestino = dd.filtroRegistro(dst1);
                                        u_d.setDestino_id(listadoRegistroDestino.get(0).getId());
                                        
                                        boolean registrarUsuarioDestino = udd.introducirDato(u_d);
                                        
                                        Usuario_Destino u_d2 = new Usuario_Destino();
                                        u_d2.setId(0);
                                        u_d2.setUsuario_id(usuario.get(0).getId());
                                        List<Destino> listadoRegistroDestino2 = dd.filtroRegistro(dst2);
                                        u_d2.setDestino_id(listadoRegistroDestino2.get(0).getId());
                                        
                                        boolean registrarUsuarioDestino2 = udd.introducirDato(u_d2);
                                    }
                                    
                                    //ahora lo que debemos hacer como paso final es introducir a nuestro titular...
                                    //creandole un perfil para que de ese modo pueda logearse...
                                    Usuarios usTitular = new Usuarios();
                                    usTitular.setCedula(cedulaCompra);
                                    List<Usuarios> titularRegistro = ud.filtrarRegistro(usTitular); //esta es la informacion de ese titular que realizo la compra...
                                    
                                    //cuando tengamos el registro de nuestro titular lo que haremos es tomar su id y colocarlo dentro de la
                                    //tabla usuario_perfil
                                    Usuario_Perfil usuarioPerfilTitular = new Usuario_Perfil();
                                    usuarioPerfilTitular.setUsuario_id(titularRegistro.get(0).getId());
                                    usuarioPerfilTitular.setPerfil_id(3);
                                    
                                    boolean registroSalida = upd.insertarRegistro(usuarioPerfilTitular);
                                    //--------------------------------------------------------------------------------------------------------------
                                    //vamos a realizar el mismo proceso para nuestro destino.
                                    List<Pais> paises = pd.listarPaises();
                                    request.setAttribute("paises", paises);
                                    redireccionar = "Pages/Mensajes/exito.jsp";
                                    
                                }else{
                                    this.definirDatos(request, response);
                                    request.setAttribute("mensaje", "cedula_incorrecta");
                                    request.setAttribute("accion", "show_form");
                                    redireccionar = "Pages/Reserva/vuelos.jsp";
                                }
                                
                            }else{
                                //tenemos que pasar como una referencia para que el formulario final vuelva aparecer....
                                //y no permitr al usuario que tenga que escoger nuevamente la aerolinea.
                                this.definirDatos(request, response);
                                request.setAttribute("mensaje", "cedula_incorrecta");
                                request.setAttribute("accion", "show_form");
                                redireccionar = "Pages/Reserva/vuelos.jsp";
                            }

                        }else{
                            this.definirDatos(request, response);
                            request.setAttribute("mensaje", "entradas_incorrecta");
                            request.setAttribute("accion", "show_form");
                            //debemos redireccionar nuevamente a la pagina de vuelos
                            redireccionar = "Pages/Reserva/vuelos.jsp";
                        }
                        
                        
                    }catch(NumberFormatException e){
                        
                        this.definirDatos(request, response);
                        request.setAttribute("mensaje", "cedula_incorrecta");
                        request.setAttribute("accion", "show_form");
                        //debemos redireccionar nuevamente a la pagina de vuelos
                        redireccionar = "Pages/Reserva/vuelos.jsp";
                        
                    }
                }
                case "pagarIda" -> {
                    //vamos a obtener las partes de la fecha de vencimiento de la cedula del usuario
                    //nombre del comprador titular....
                    String cedulaCompra = (request.getParameter("dniCompra") == null) ? "" : request.getParameter("dniCompra");
                    String nombreTitular = ( request.getParameter("Names") == null) ? "" : request.getParameter("Names");
                    String correoTitular = ( request.getParameter("Emails") == null) ? "" : request.getParameter("Emails");
                    if(!cedulaCompra.isEmpty() && !nombreTitular.isEmpty() && !correoTitular.isEmpty()){
                        //vamos a listar nuestra estructura de vuelo
                        //desde a -> vuelo ida
                        String VueloIda_desde = request.getParameter("VueloIda_desde");
                        String VueloIda_a = request.getParameter("VueloIda_a");
                        //nos traeremos las fecha y hora de vuelos de ida.
                        String VueloIda_fecha = request.getParameter("VueloIda_fecha");
                        String VueloIda_horaSalida = request.getParameter("VueloIda_horaSalida");
                        String VueloIda_horaLlegada = request.getParameter("VueloIda_horaLlegada");
                        String VueloIda_aerolinea = request.getParameter("VueloIda_aerolinea");
                        
                        //vamos a traernos el identificador del pais que terminanos seleccionando...
                        Aerolineas aerolinea_ida = new Aerolineas();
                        aerolinea_ida.setId(0);
                        aerolinea_ida.setNombre(VueloIda_aerolinea.toLowerCase());
                        aerolinea_ida.setEstado(1);
                        
                        List<Aerolineas> id_vueloIda = ad.filtrarRegistro(aerolinea_ida);
                        
                        //vamos a introducir la informacin en la tabla de origen... los viajes de origen del usuario..
                        List<Pais> p1 = pd.tipoPais(VueloIda_desde);
                        
                        
                        //==========================================================================================
                        Origen org1 = new Origen();
                        
                        org1.setId(0);
                        org1.setFecha(VueloIda_fecha);
                        org1.setHora_salida(VueloIda_horaSalida);
                        org1.setHora_llegada(VueloIda_horaLlegada);
                        org1.setPais_id(p1.get(0).getId());
                        org1.setAerolinea_id(id_vueloIda.get(0).getId());
                        
                        boolean introducirOrigen1 = od.insertarDatos(org1);
                        
                        List<Pais> p_1 = pd.tipoPais(VueloIda_a);
                        
                        Destino dst1 = new Destino();
                        
                        dst1.setId(0);
                        dst1.setFecha(VueloIda_fecha);
                        dst1.setHora_salida(VueloIda_horaSalida);
                        dst1.setHora_llegada(VueloIda_horaLlegada);
                        dst1.setPais_id(p_1.get(0).getId());
                        dst1.setAerolinea_id(id_vueloIda.get(0).getId()); // Actualizar id
                        
                        
                        boolean introducirDestino1 = dd.insertarDatos(dst1);
                        
                        //----------------------------------------------------------------------------------------
                        //vamos a obtener los datos de cada uno de nuestros usuarios el cual se encuentra ocultos..
                        int n = Integer.parseInt(request.getParameter("NumeroPasajeros"));
                        
                        
                        for(int i = 0; i < n; i++){
                            int indice = i+1;
                            Usuarios us = new Usuarios();
                            us.setNombre(request.getParameter("Nombres-"+indice));
                            us.setApellido(request.getParameter("Apellidos-"+indice));
                            us.setCorreo(request.getParameter("Correo-"+indice));
                            us.setCelular(request.getParameter("Telefono-"+indice));
                            us.setCedula(request.getParameter("Cedula-"+indice));
                            
                            //estas dos de aqui abajo representa datos personales de la person a y el numero es el id unico de
                            //un registro que se encuentra en otra tabla...
                            if(request.getParameter("Pasajeros-"+indice).toLowerCase().equals("hombre")){
                                //dentro de este punto lo que vamos a realizar a continuacion es generar un identificador
                                us.setPasajeroId(1);
                            }else if(request.getParameter("Pasajeros-"+indice).toLowerCase().equals("mujer")){
                                us.setPasajeroId(2);
                            }else if(request.getParameter("Pasajeros-"+indice).toLowerCase().equals("niño")){
                                us.setPasajeroId(3);
                            }
                            
                            //el genero de la persona.
                            if(request.getParameter("Genero-"+indice).toLowerCase().equals("masculino")){
                                us.setGeneroId(1);
                            }else if(request.getParameter("Genero-"+indice).toLowerCase().equals("femenino")){
                                us.setGeneroId(2);
                            }else if(request.getParameter("Genero-"+indice).toLowerCase().equals("otro")){
                                us.setGeneroId(3);
                            }
                            
                            boolean introducirDatos = ud.registrarUsuario(us); //registramos nuestros usuarios...
                            
                            //vamos a introducir dentro de nuestra tabla de usuario_origen
                            Usuario_Origen uo = new Usuario_Origen();
                            List<Usuarios> usuario = ud.filtrarRegistro(us);
                            
                            uo.setId(0);
                            uo.setUsuario_id(usuario.get(0).getId());
                            //vamos a filtrar por la fecha... no importa mucho por ello ya que es un valor que puede obtenerlo muchas personas.
                            List<Origen> listadoRegistroOrigen = od.filtroRegistro(org1);
                            //vamos a actualizar nuestro respectivo dato....
                            uo.setOrigen_id(listadoRegistroOrigen.get(0).getId());
                            //                        //vamos a introducirlo dentro de la tabla..
                            boolean registroId = uod.introducirRegistro(uo);
                            
                            //a continuacion vamos a introducir los datos para nuestra tabla intermedia usuario_destino
                            Usuario_Destino u_d = new Usuario_Destino();
                            u_d.setId(0);
                            u_d.setUsuario_id(usuario.get(0).getId());
                            List<Destino> listadoRegistroDestino = dd.filtroRegistro(dst1);
                            u_d.setDestino_id(listadoRegistroDestino.get(0).getId());
                            
                            boolean registrarUsuarioDestino = udd.introducirDato(u_d);
                        }
                        
                        //ahora lo que debemos hacer como paso final es introducir a nuestro titular...
                        //creandole un perfil para que de ese modo pueda logearse...
                        Usuarios usTitular = new Usuarios();
                        usTitular.setCedula(cedulaCompra);
                        List<Usuarios> titularRegistro = ud.filtrarRegistro(usTitular); //esta es la informacion de ese titular que realizo la compra...
                        
                        //cuando tengamos el registro de nuestro titular lo que haremos es tomar su id y colocarlo dentro de la
                        //tabla usuario_perfil
                        Usuario_Perfil usuarioPerfilTitular = new Usuario_Perfil();
                        usuarioPerfilTitular.setUsuario_id(titularRegistro.get(0).getId());
                        usuarioPerfilTitular.setPerfil_id(3);
                        
                        boolean registroSalida = upd.insertarRegistro(usuarioPerfilTitular);
                        //--------------------------------------------------------------------------------------------------------------
                        //vamos a realizar el mismo proceso para nuestro destino.
                        List<Pais> paises = pd.listarPaises();
                        request.setAttribute("paises", paises);
                        redireccionar = "Pages/Mensajes/exito.jsp";
                        
                    }else{
//                    redireccionar = "Pages/Reserva/vuelosIda.jsp";
                    }
                }
                case "login" -> System.out.println(this.getAlmacenar());
//                redireccionar = "Pages/Reserva/reserva.jsp";
                default -> {
                }
            }
            
            request.getRequestDispatcher(redireccionar).forward(request, response);
        }
    }
    
    
    public List<Object> InformacionFormulario(HttpServletRequest request, String tipo){
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
    
    
    public List<Pasajeros> formPasajeros(HttpServletRequest request, int limite){
        //informacion pasajeros
        List<List<String>> lista = new ArrayList<>();
        List<Pasajeros> lista_pasajeros = new ArrayList<>();
        for(int i = 1; i < limite; i++){
            List<String> formPasajero1 = new ArrayList<>();
            Pasajeros pasajero = new Pasajeros();
            String nombres = request.getParameter("Nombres-"+Integer.toString(i));
            String apellidos = request.getParameter("Apellidos-"+Integer.toString(i));
            String cedula = request.getParameter("Cedula-"+Integer.toString(i));
            String tipoPasajero = request.getParameter("tipo_pasajero-"+Integer.toString(i));

            
            pasajero.setNombre(nombres);
            pasajero.setApellido(apellidos);
            pasajero.setCedula(cedula);
            pasajero.setTipo_pasajero(tipoPasajero);
            
            
            formPasajero1.add(nombres);
            formPasajero1.add(apellidos);
            formPasajero1.add(cedula);
            formPasajero1.add(tipoPasajero);
            
            if(tipoPasajero.equals("Hombre") || tipoPasajero.equals("Mujer")){
                
                String email = request.getParameter("Email-"+Integer.toString(i));
                String celular = request.getParameter("Celular-"+Integer.toString(i));
                
                pasajero.setCorreo(email);
                pasajero.setCelular(celular);
                
                formPasajero1.add(email);
                formPasajero1.add(celular);
            }else{
                
                pasajero.setCorreo("false");
                pasajero.setCelular("false");
                
                formPasajero1.add("false");
            }

            lista.add(formPasajero1);
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
    
    //estos datos lo devolveremos a nuestro archivo vuelos.jsp... porque los necesita para
    //desplegar el contenido. 
    public void definirDatos(HttpServletRequest request, HttpServletResponse response){
        //dentro de este punto empezaremos ir definiendo 
        int nTotal = Integer.parseInt(request.getParameter("NumeroPasajeros"));
        List<Pasajeros> listado = new ArrayList<>();
        for(int i = 0; i < nTotal; i++){
            Pasajeros p = new Pasajeros();
            p.setId(i+1);
            p.setNombre(request.getParameter("Nombres-"+(i+1)));
            p.setApellido(request.getParameter("Apellidos-"+(i+1)));
            p.setCorreo(request.getParameter("Correo-"+(i+1)));
            p.setCelular(request.getParameter("Telefono-"+(i+1)));
            p.setCedula(request.getParameter("Cedula-"+(i+1)));
            p.setTipo_pasajero(request.getParameter("Pasajeros-"+(i+1)));
            p.setGenero(request.getParameter("Genero-"+(i+1)));

            listado.add(p);
        }

        List<String> info = new ArrayList<>();
        info.add(request.getParameter("PaisOrigen"));
        info.add(request.getParameter("PaisDestino"));
        info.add(request.getParameter("ciudadOrigen"));
        info.add(request.getParameter("ciudadDestino"));
        info.add(request.getParameter("fechaIda"));
        info.add(request.getParameter("fechaRetorno"));
        info.add(request.getParameter("pTotal"));

        request.setAttribute("info_pasajeros", listado);
        request.setAttribute("info", info);
    }
    
    public Pasajeros capturarDatos(int indice, String nombre, String apellido, String cedula, String tipo_pasajero, String genero, String email, String celular){
        Pasajeros p = new Pasajeros();
        p.setId(indice);
        p.setNombre(nombre);
        p.setApellido(apellido);
        p.setCedula(cedula);
        p.setTipo_pasajero(tipo_pasajero);
        p.setGenero(genero);
        p.setCorreo(email);
        p.setCelular(celular);

        return p;
        
    }

}
