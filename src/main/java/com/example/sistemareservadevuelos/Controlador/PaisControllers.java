/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.example.sistemareservadevuelos.Controlador;

import Modelo.Pais;
import ModeloDAO.PaisDAO;
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
@WebServlet(name = "PaisControllers", urlPatterns = {"", "/PaisControllers", "/login"})
public class PaisControllers extends HttpServlet {

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
        String accion = "";
        PaisDAO pd = null;
         
        try {
            pd = new PaisDAO();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PaisControllers.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(request.getRequestURI().equals("/ReservaDeVuelos/")){
            
            //vamos a traernos los datos de paises...
            List<Pais> paises = pd.listarPaises();
            request.setAttribute("paises", paises);
            accion = "index.jsp";
        }else if(request.getRequestURI().equals("/ReservaDeVuelos/login")){
            
            accion = "Pages/Login/login.jsp";
        }
        
        request.getRequestDispatcher(accion).forward(request, response);
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
        String accion = "";
        PaisDAO pd = null;
         
        try {
            pd = new PaisDAO();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PaisControllers.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(request.getRequestURI().equals("/ReservaDeVuelos/")){
            //vamos a traernos los datos de paises...
            List<Pais> paises = pd.listarPaises();
            request.setAttribute("paises", paises);
            accion = "index.jsp";
        }
        request.getRequestDispatcher(accion).forward(request, response);
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
