(() => {
    
    let entradas = {
        "Email" : "",
        "Password" : ""
    };
    
    document.addEventListener("DOMContentLoaded", () => {
        ReservaInicioSesion();
    });

    function ReservaInicioSesion(){
        BotonSalirFormulario();
        EscucharEntradas();
        EnviarDatos();
    }
    
    function EnviarDatos(){
        let botonEnviar = document.querySelector("input[type=submit]");
        botonEnviar.addEventListener("click", (e) => {
            
            if( (entradas.Email.trim("") === "" && entradas.Password.trim("") === "") || (entradas.Email.trim("") === "" || entradas.Password.trim("") === "")){
                e.preventDefault();
                
                MensajeError("error", "Los campos son obligatorios");
            }
        });
    }
    
    
    
    
    function EscucharEntradas(){
        let campos = document.querySelectorAll(".listen");
        campos.forEach((campo) => {
            campo.addEventListener("input", (e) => {
                let tipo = e.target.name;
                
                if(tipo === "Email"){
                    entradas.Email = e.target.value;
                }else if(tipo === "Password"){
                    entradas.Password = e.target.value;
                }
            });
        });
    }
    
    
    function BotonSalirFormulario(){
        let botonSalir = document.querySelector("#exit");
        botonSalir.addEventListener("click", (e) => {
            CerrarFormulario();
        });
    }
    
    
    /*
     * Funciones Externas que logran complementar a nuestro codigo. 
     */
    
    function CerrarFormulario(event){
        let formulario = document.querySelector(".seccion_formulario");
        formulario.classList.add("hidden");
    }
    
    
    function MensajeError(tipo, texto){
        
        if(document.querySelectorAll(`.${tipo}`).length === 0){
        
            if(tipo === "error"){
                
                let parrafo = document.createElement('P');
                parrafo.classList.add("alerta",tipo);
                parrafo.textContent = texto;

                let contenedor = document.querySelector(".mensajes-flotante");

                contenedor.appendChild(parrafo);
                
                
                eliminarElemento(parrafo);
            }
        
        }
    }
    
    
    function eliminarElemento(parrafo){
        setTimeout(() => {
            parrafo.remove();
        },3000);
    }
})();
