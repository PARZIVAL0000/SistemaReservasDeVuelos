package Modelo;

/**
 *
 * @author denni
 */
public class Pasajero_Clase {
    private int pasajero_clase_id;
    private int pasajeroId;
    private int claseId;

    public Pasajero_Clase(){
    }
    
    public Pasajero_Clase(int pasajeroClaseId, int pasajeroId, int claseId){
        this.pasajero_clase_id = pasajeroClaseId;
        this.pasajeroId = pasajeroId;
        this.claseId = claseId;
    }
    
    public int getPasajero_clase_id() {
        return pasajero_clase_id;
    }

    public void setPasajero_clase_id(int pasajero_clase_id) {
        this.pasajero_clase_id = pasajero_clase_id;
    }

    public int getPasajeroId() {
        return pasajeroId;
    }

    public void setPasajeroId(int pasajeroId) {
        this.pasajeroId = pasajeroId;
    }

    public int getClaseId() {
        return claseId;
    }

    public void setClaseId(int claseId) {
        this.claseId = claseId;
    }
    
    
}
