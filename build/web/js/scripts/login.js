let contador = 10;

document.addEventListener("DOMContentLoaded", (e) => {
    Login();
});


function Login(){
    VerificarSesion();
}

function VerificarSesion(){
    eliminarMensajes();
}

function eliminarMensajes(){
    let error = document.querySelector(".error");
    
    if(error){
        
        //podriamos bloquear campos automaticamente.
        if(parseInt(document.querySelector("input[type=hidden]").value) === 0){
            document.querySelector("input[type=email]").disabled=true;
            document.querySelector("input[type=password]").disabled=true;
            document.querySelector("button[type=submit]").disabled=true;
        }
        
        setTimeout((e) => {
            error.remove();
            verNumeroIntentos(0);
        }, 3000);   
        
         
    }
}

function verNumeroIntentos(n = 0){
    let intentos = document.querySelector("input[type=hidden]");
  
    if(n !== 0){
        //este nos permitira generar un conteo en base a una llamada recursiva de nuestra funcion....
        contador = n;
    }
    
    if(parseInt(intentos.value) === 0){
        document.querySelector("input[type=email]").disabled=true;
        document.querySelector("input[type=password]").disabled=true;
        document.querySelector("button[type=submit]").disabled=true;
        //generamos un mensaje nuevo
        let contenedor = document.querySelector(".mensajes");
        let mensaje = document.createElement("P");
        mensaje.classList.add("error");
        
        mensaje.textContent = `La sesión se ha bloqueado, intentalo en ... ${contador}seg.`;
        contenedor.appendChild(mensaje);
        
        contador -= 1;
        if(contador >= 1 && contador <= 10){      
            //pasamos nuestro contador... entonces debemos hacer un modo verificacion y cambio. 

            setTimeout(() => {
                document.querySelector(".error").remove();
                verNumeroIntentos(contador);
            }, 1000);
        }else{
            verificarContador();
        }
    }
    
}

function verificarContador(){
    if(contador === 0){
       
        document.querySelector("input[type=email]").disabled=false;
        document.querySelector("input[type=password]").disabled=false;
        document.querySelector("button[type=submit]").disabled=false;
        
        //cambiamos por el numero de intenos
        document.querySelector("input[type=hidden]").value="-1";
        
        //eliminamos nuestro cuadro de alerta.
        eliminarMensajes();
    }
}