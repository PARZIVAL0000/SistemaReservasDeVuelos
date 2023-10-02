/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.sistemareservadevuelos.Interfaces;

import com.example.sistemareservadevuelos.Modelo.CarteleraViajes;
import com.example.sistemareservadevuelos.Modelo.Vuelos;

import java.util.List;

/**
 *
 * @author denni
 */
public interface VuelosInterface {
    
    public List<Vuelos> listadoVuelos();
    
    public List<CarteleraViajes> listadoCarteleraViajes();
}
