//esto permitira que el codigo de aqui no pueda ser vista o procesada por otro archivo js
(() => {
    const formulario = {
        "origen" : false,
        "destino" : false,
        "pasajeros" : false,
        "fechaIda" : false,
        "ida":{
            "estado" : false
        },
        "idaRegreso":{
            "estado" : false,
            "fechaRegreso" : false
        }
    };
    
    //mensajes de cada campo
    const origen = document.querySelector("#mensaje_paisSalida");
    const destino = document.querySelector("#mensaje_paisDestino");
    const fechaSalida = document.querySelector("#mensaje_fechaIda");
    const fechaRetorno = document.querySelector("#mensaje_fechaRegreso");
    const numeroPasajeros = document.querySelector("#mensaje_pasajeros");
    //estilos para cada campo.
    const campo_origen = document.querySelector("#from");
    const campo_destino = document.querySelector("#to");
    const campo_fechaSalida = document.querySelector("#deparure");
    const campo_fechaRetorno = document.querySelector("#return");
    const campo_pasajeros = document.querySelector("#pasajero");
    
    
    document.addEventListener("DOMContentLoaded", () => {
        App();
    });

    function App(){
        EscucharTipoVuelo();
        ValidarFormulario();
        EnviarFormulario();
    }


    function EscucharTipoVuelo(){
        let tipoVuelo = document.querySelectorAll("input[type=radio]");
        tipoVuelo.forEach(vuelo => {
            const toDate = document.querySelector('#fechaRegreso');
           
            vuelo.addEventListener("input", (e) => {
               const elemento = e.target.value;
               
               if(elemento === "Ida"){
                    //cuando tengamso solo esta opcion, eliminamos nuestra campo de opcion de vuelo de regreso..
                    toDate.querySelector('input[type=text]').disabled=true;
                    toDate.classList.add("hidden");
                    //clases bootstrap para que los inputs restantes tomen un width:100
                    document.querySelector('#fechaSalida').classList.add('w-100');
                    
                    cambiarEstado(elemento);
               }else if(elemento === "Ida-Regreso"){     
                    toDate.classList.remove('hidden');
                    toDate.querySelector('input[type=text]').disabled=false;
                    //clases bootstrap para que los inputs restantes tomen un width:100
                    document.querySelector('#fechaSalida').classList.remove('w-100');
                    
                    cambiarEstado(elemento);
               }   
            });
           
           
           if(vuelo.checked){
                if(vuelo.value === "Ida"){
                    toDate.querySelector('input[type=text]').disabled=true;
                    toDate.classList.add('hidden');
                    //clases bootstrap para que los inputs restantes tomen un width:100
                    document.querySelector('#fechaSalida').classList.add('w-100');
                    
                    
                    formulario.ida.estado = true;
                }
           }
           
        });
    }
    
    function ValidarFormulario(){
        const selects = document.querySelectorAll("select");
        const inputs = document.querySelectorAll("input");
        
        const ValidarSeleccion = (e) => {
            const name = e.target.name;
            const value = e.target.value;
            
            switch(name){
                case "from":
                    if(value.trim() !== ''){
                        formulario.origen = true;
                        ejecutarMensaje(false, origen, campo_origen);
                    }else{
                        formulario.origen = false;
                    }
                    break;
                    
                case "to":
                    if(value.trim() !== ''){
                        formulario.destino = true;
                        ejecutarMensaje(false, destino, campo_destino);
                    }else{
                        formulario.destino = false;
                    }
                    break;
                    
                default:
                    break;
            }
        };
        
        const ValidarInput = (e) => {
            const tipo = e.target.name;
            const value = e.target.value;
       
            if(formulario.ida.estado){
                
                if(tipo === "deparure"){
                    
                    if(value.trim() !== ''){
                    
                        formulario.fechaIda = true;
                        ejecutarMensaje(false, fechaSalida, campo_fechaSalida);
                    }else{
                        formulario.fechaIda = false;
                    }
                }else if(tipo === "pasajeros"){
                    
                    if(value.trim() !== ''){
                    
                        if(parseInt(value) >= 1 && parseInt(value) <= 4){
                            formulario.pasajeros = true;
                            ejecutarMensaje(false, numeroPasajeros, campo_pasajeros);
                        }else{
                            formulario.pasajeros = false;
                            ejecutarMensaje(true, numeroPasajeros, campo_pasajeros);
                        }
                    }else{
                        formulario.pasajeros = false;
                    }
                }
                
            }else if(formulario.idaRegreso.estado){
             
                if(tipo === "deparure"){
                    
                    if(value.trim() !== ''){
                        
                        formulario.fechaIda = true;
                        ejecutarMensaje(false, fechaSalida, campo_fechaSalida);
                    }else{
                        formulario.fechaIda = false;
                    }
                }else if(tipo === "return"){
                    
                    if(value.trim() !== ''){
                        
                        formulario.idaRegreso.fechaRegreso = true;
                        ejecutarMensaje(false, fechaRetorno, campo_fechaRetorno);
                    }else{
                        formulario.fechaRegreso = false;
                    }
                }else if(tipo === "pasajeros"){
                    
                    if(value.trim() !== ''){
                        if(parseInt(value) >= 1 && parseInt(value) <= 4){
                 
                            formulario.pasajeros = true;
                            ejecutarMensaje(false, numeroPasajeros, campo_pasajeros);
                        }else{
                            formulario.pasajeros = false;
                            ejecutarMensaje(true, numeroPasajeros, campo_pasajeros);
                        }
                    }else{
                        formulario.pasajeros = false;
                    }
                }
            }
        };
        
        selects.forEach(select => {
            select.addEventListener("change", ValidarSeleccion);
        });
        
        inputs.forEach(input => {
            input.addEventListener("blur", ValidarInput);
        });
    }
    
    function EnviarFormulario(){
        const boton = document.querySelector("button[type=submit]");
        
        boton.addEventListener("click", (e) => {
                
            if(formulario.ida.estado){
                if(!formulario.origen || !formulario.destino || !formulario.fechaIda || !formulario.pasajeros){
                    //no pasaste....
                    e.preventDefault();
                    
                    LlamarAlerta();
                }
            }
            
            if(formulario.idaRegreso.estado){
                if(!formulario.origen || !formulario.destino || !formulario.pasajeros || !formulario.fechaIda || !formulario.idaRegreso.fechaRegreso){
                    //no pasaste....
                    e.preventDefault();
                    
                    LlamarAlerta();
                }
            }
            
        });
    }
    
    function LlamarAlerta(){
        
        if(!formulario.origen){
            ejecutarMensaje(true, origen, campo_origen);
        }else{
            ejecutarMensaje(false, origen, campo_origen);
        }
        
        
        if(!formulario.destino){
            ejecutarMensaje(true, destino, campo_destino);
        }else{
            ejecutarMensaje(false, destino, campo_destino);
        }
        
        
        if(!formulario.fechaIda){
            ejecutarMensaje(true, fechaSalida, campo_fechaSalida);
        }else{
            ejecutarMensaje(false, fechaSalida, campo_fechaSalida);
        }
        
        
        if(!formulario.idaRegreso.fechaRegreso){
            ejecutarMensaje(true, fechaRetorno, campo_fechaRetorno);
        }else{
            ejecutarMensaje(false, fechaRetorno, campo_fechaRetorno);
        }
        
        
        if(!formulario.pasajeros){
            ejecutarMensaje(true, numeroPasajeros, campo_pasajeros);
        }else{
            ejecutarMensaje(false, numeroPasajeros, campo_pasajeros);
        }
    }
    
    function cambiarEstado(elemento){
        if(elemento === "Ida"){
            formulario.ida.estado = true;
            formulario.idaRegreso.estado = false;
        }else if(elemento === "Ida-Regreso"){
            formulario.idaRegreso.estado = true;
            formulario.ida.estado = false;
        }
    }
    
    function ejecutarMensaje(entrada, campo, input){
        if(entrada){
            campo.classList.add("mostrar");
            campo.classList.remove("ocultar");
            input.classList.add("alerta_campo");
        }else{
            campo.classList.remove("mostrar");
            campo.classList.add("ocultar");
            input.classList.remove("alerta_campo");
        }
    }
    
})();