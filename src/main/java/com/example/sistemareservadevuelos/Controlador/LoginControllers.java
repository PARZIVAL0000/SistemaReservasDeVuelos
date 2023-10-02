package com.example.sistemareservadevuelos.Controlador;


import com.example.sistemareservadevuelos.ModeloDAO.UsuariosDAO;
import com.example.sistemareservadevuelos.ModeloDAO.Usuario_PerfilDAO;
import com.example.sistemareservadevuelos.ModeloDAO.PerfilDAO;
import com.example.sistemareservadevuelos.Modelo.Usuarios;
import com.example.sistemareservadevuelos.Modelo.Usuario_Perfil;
import com.example.sistemareservadevuelos.Modelo.Perfil;
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
@WebServlet(name = "LoginControllers", urlPatterns = {"/LoginControllers"})
public class LoginControllers extends HttpServlet {

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
        
        UsuariosDAO ud = null;
        Usuario_PerfilDAO upd = null;
        PerfilDAO pfd = null;
        try {
            ud = new UsuariosDAO();
            upd = new Usuario_PerfilDAO();
            pfd = new PerfilDAO();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginControllers.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
        if(request.getRequestURI().equals("/ReservaDeVuelos/LoginControllers")){
            
            String accion = request.getParameter("accion");
            
            String redireccionar = "Pages/Login/login.jsp";
            List<String> mensaje = new ArrayList<>();
            mensaje.add("error");
            mensaje.add("Los campos no deben estar vacios");
            
            int numeroIntentos = 3;
            
            if(accion.equals("sesion")){
                //establecer el numero de intentos...
                int intentosObtenidos = Integer.parseInt(request.getParameter("intentos"));
                if(intentosObtenidos != -1){
                    numeroIntentos = intentosObtenidos;
                }

                //vamos a obtener los datos.
                String email = request.getParameter("Email");
                String password = request.getParameter("Password");
                
                
                if((email.equals("") || email == null) || (password.equals("") || password == null)){
                    
                    request.setAttribute("error", mensaje);
                }else if(email != null && password != null){
                    //cuando el usuario haya introducido los valores

                    //1.-vamos a traernos el registro del usuario.
                    Usuarios us = new Usuarios();
                    us.setCorreo(email);
                    us.setCedula(password); //el password siempre debera ser el numero de cedula del usuario.
                    
                    List<Usuarios> verificar = ud.filtroLogin(us);
                    
                    if(!verificar.isEmpty()){
                        int id = verificar.get(0).getId(); //tenemos el id del cliente o usuario...
                        //2.- con el id usuario vamos a traernos el id del perfil dentro de nuestra tabla intermedia usuario_perfil;
                        List<Usuario_Perfil> registro = upd.filtrarRegistro(id);
                        
                        //debemos hacer una validacion dentro de este punto...
                        if(!registro.isEmpty()){
                        
                            int idPerfil = registro.get(0).getPerfil_id();
                            //3.- con el idPerfil vamos a sacarnos el registro de la tabla perfil.
                            Perfil p = new Perfil();
                            p.setId(idPerfil);

                            List<Perfil> registroPerfil = pfd.filtrarRegistro(p);
                            
                            //cuando hayamos obtenido la informacion... lo que vamos a realizar a continuacion es redireccionarlo a su 
                            //perfil correspondiente. 
                            if(registroPerfil.get(0).getPerfil().equals("boletariado")){
                                redireccionar = "Pages/Boleteria/perfil.jsp";
                            }else if(registroPerfil.get(0).getPerfil().equals("cliente")){
                                
                                //dentro de este punto lo que haremos es pasar la informacion de manera completa.
                                List<Usuarios> registroInfo = ud.filtrarRegistro2(us);
                                
                                
                                request.setAttribute("info", registroInfo);
                                redireccionar = "Pages/Cliente/index.jsp";
                                
                              
                            }else{
                                mensaje.set(1, "Cuenta no registrada");
                                request.setAttribute("error", mensaje);
                            }

                        }else{
                            mensaje.set(1, "Cuenta no registrada");
                            request.setAttribute("error", mensaje);
                        }
                        
                    }else{
                        numeroIntentos -= 1;
                        request.setAttribute("intentos", numeroIntentos);
                        
                        mensaje.set(1, "Email o contraseña incorrecta");
                        request.setAttribute("error", mensaje);
                    }
                }
            }else if(accion.equals("login")){
             
                redireccionar = this.validarLogin(request.getParameter("Email"), request.getParameter("Password"));
                
                if(redireccionar.equals("")){
                
                    redireccionar = "ReservaControllers?accion=login";
                   
                }
              
            }
            
            request.getRequestDispatcher(redireccionar).forward(request, response);
        }
    }

   
    public String validarLogin(String email, String password){
        
        String path = "";
        
        if(!email.equals("") && !password.equals("")){
            
           try{
                Usuarios us = new Usuarios();
                UsuariosDAO ud = new UsuariosDAO();

                us.setCorreo(email);
                us.setCedula(password);

                List<Usuarios> respuesta = ud.filtroLogin(us);


                if(!respuesta.isEmpty()){

                    Usuario_PerfilDAO upd = new Usuario_PerfilDAO();

                    List<Usuario_Perfil> perfil = upd.filtrarRegistro(respuesta.get(0).getId());

                    int id_perfil = perfil.get(0).getPerfil_id();

                    switch (id_perfil) {
                        case 1 : {
                            path = "Pages/Boleteria/perfil.jsp";
                            //boletariado...
                            return path;
                        }
                        case 2 : {
                            path = "Pages/Administrador";
                            //administrador...
                            return path;
                        }
                        case 3 : {
                            path = "Pages/Cliente/index.jsp";
                            //cliente...
                            return path;
                        }
                        default : {
                        }
                    }
                }
           
           }catch(ClassNotFoundException e){
                System.out.println("Error en encontrar una coincidencia");
                return path;
           }
           
          
        }
        
        return path;
       
        
    }

}
