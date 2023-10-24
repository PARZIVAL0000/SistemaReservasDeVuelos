package Modelo;

/**
 *
 * @author denni
 */
public class Pasajero_Aerolinea {
    private int pasajeros_aerolineas_id;
    private int aerolinea_id;
    private int pasajero_id;
    
    public Pasajero_Aerolinea(){
    }
    
    public Pasajero_Aerolinea(int pasajerosAerolineaId, int aerolineaId, int pasajeroId){
        this.pasajeros_aerolineas_id = pasajerosAerolineaId;
        this.aerolinea_id = aerolineaId;
        this.pasajero_id = pasajeroId;
    }
    
    public void setPasajerosAerolineasId(int pasajerosAerolineasId){
        this.pasajeros_aerolineas_id = pasajerosAerolineasId;
    }
    public int getParajerosAerolineasId(){
        return pasajeros_aerolineas_id;
    }
    
    public void setAerolineaId(int aerolineaId){
        this.aerolinea_id = aerolineaId;
    }
    public int getAerolineaId(){
        return aerolinea_id;
    }
    
    public void setPasajeroId(int pasajeroId){
        this.pasajero_id = pasajeroId;
    }
    public int getPasajeroId(){
        return pasajero_id;
    }
}
