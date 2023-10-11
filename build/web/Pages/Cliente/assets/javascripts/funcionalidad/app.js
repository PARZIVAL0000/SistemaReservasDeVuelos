document.addEventListener("DOMContentLoaded", () => {
    App();
});

function App(){
   
    Formulario();
}

//esta funcion lo que va a realizar es generar un evento para nuestro boton del formulario.
function Formulario(){
    let boton = document.querySelector("#actualizar");
    
    boton.addEventListener("click", (e) => {
        //seleccionaremos nuestro contenedor principal
        let contenedor = document.querySelector(".campo_botones");
        
        //cuando el usuario se encarga de dar click al boton lo que haremos a continuacion es desactivar ese boton
        // y crear un input[type=submit] para que el usuario pueda volver a reestablecer sus datos.
        let input = document.createElement('input');
        input.type="submit";
        input.value="Enviar";
        input.classList.add("btn","btn-warning");
//        input.classList.add("botonActualizar", "btn btn-primary");
        
        contenedor.appendChild(input);
        
        let selecciones = document.querySelectorAll(".formulario_info form select.seleccion");
        selecciones.forEach(seleccion => {
            seleccion.disabled=false;
            console.log(seleccion);
        });
       
        //vamos a desactivar a continuacion nuestros campos de input...
        let inputs = document.querySelectorAll("input[type=text]");
        inputs.forEach(input => {
            input.disabled=false;
        });
        
        
        //desactivamos nuestro boton de actualizar 
        e.target.disabled=true;
        
        
    });
    
}