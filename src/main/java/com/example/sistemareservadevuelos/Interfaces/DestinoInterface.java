/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.sistemareservadevuelos.Interfaces;

import Modelo.Destino;

import java.util.List;
/**
 *
 * @author denni
 */
public interface DestinoInterface {
    
    public List<Destino> listadoDatos();
    
    public boolean insertarDatos(Destino destino);
    
    public List<Destino> filtroRegistro(Destino dst);
}
