
package com.example.sistemareservadevuelos.Interfaces;

import Modelo.Usuarios;

import java.util.List;
/**
 *
 * @author denni
 */
public interface UsuariosInterface {
    public List<Usuarios> listarUsuarios();
    
    public boolean registrarUsuario(Usuarios usuario);
}
