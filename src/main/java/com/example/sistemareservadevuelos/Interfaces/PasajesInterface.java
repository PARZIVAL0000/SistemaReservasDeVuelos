/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.sistemareservadevuelos.Interfaces;

import Modelo.Pasajes;

import java.util.List;

/**
 *
 * @author denni
 */
public interface PasajesInterface {
    
    public List<Pasajes> listarPasajes();
    
    public List<Pasajes> filtrarRegistro(Pasajes p);
}
