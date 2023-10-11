package Modelo;

/**
 *
 * @author denni
 */
public class Vuelos {
    private int id;
    private int estado;
    private int aerolinea_id;
    private int pasaje_id;
    private String fecha;
    private String hora_llegada;
    private String hora_salida;
    private int pais_origen;
    private int pais_destino;
    
    public Vuelos(){
    
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
     * @return the estado
     */
    public int getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(int estado) {
        this.estado = estado;
    }

    /**
     * @return the aerolinea_id
     */
    public int getAerolinea_id() {
        return aerolinea_id;
    }

    /**
     * @param aerolinea_id the aerolinea_id to set
     */
    public void setAerolinea_id(int aerolinea_id) {
        this.aerolinea_id = aerolinea_id;
    }

    /**
     * @return the pasaje_id
     */
    public int getPasaje_id() {
        return pasaje_id;
    }

    /**
     * @param pasaje_id the pasaje_id to set
     */
    public void setPasaje_id(int pasaje_id) {
        this.pasaje_id = pasaje_id;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the hora_llegada
     */
    public String getHora_llegada() {
        return hora_llegada;
    }

    /**
     * @param hora_llegada the hora_llegada to set
     */
    public void setHora_llegada(String hora_llegada) {
        this.hora_llegada = hora_llegada;
    }

    /**
     * @return the hora_salida
     */
    public String getHora_salida() {
        return hora_salida;
    }

    /**
     * @param hora_salida the hora_salida to set
     */
    public void setHora_salida(String hora_salida) {
        this.hora_salida = hora_salida;
    }

    /**
     * @return the pais_origen
     */
    public int getPais_origen() {
        return pais_origen;
    }

    /**
     * @param pais_origen the pais_origen to set
     */
    public void setPais_origen(int pais_origen) {
        this.pais_origen = pais_origen;
    }

    /**
     * @return the pais_destino
     */
    public int getPais_destino() {
        return pais_destino;
    }

    /**
     * @param pais_destino the pais_destino to set
     */
    public void setPais_destino(int pais_destino) {
        this.pais_destino = pais_destino;
    }

    
    
}
