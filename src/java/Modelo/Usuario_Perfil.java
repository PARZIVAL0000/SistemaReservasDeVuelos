/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author denni
 */
public class Usuario_Perfil {
    private int id;
    private int perfil_id;
    private int usuario_id;

    public Usuario_Perfil(int id, int perfil_id, int usuario_id){
        this.id = id;
        this.perfil_id = perfil_id;
        this.usuario_id = usuario_id;
    }
    
    public Usuario_Perfil(){
        
    }
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the perfil_id
     */
    public int getPerfil_id() {
        return perfil_id;
    }

    /**
     * @param perfil_id the perfil_id to set
     */
    public void setPerfil_id(int perfil_id) {
        this.perfil_id = perfil_id;
    }

    /**
     * @return the usuario_id
     */
    public int getUsuario_id() {
        return usuario_id;
    }

    /**
     * @param usuario_id the usuario_id to set
     */
    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }
    
    
}
