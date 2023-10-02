/*
 * Este archivo esta destinado para generar funcionalidad al apartado de "vuelos", que es parte del cliente cuando 
 * se encuentra dentro de su cuenta... se puede revisar el archivo correspondiente, en /Pages/Cliente/vuelos.jsp
 * 
 * Con ayuda del javascript, lo que haremos a continuacion es generar eventos para nuestro nuestra tres opciones
 * 1.- Aerolineas Comunes
 * 2.- Vuelos Origen
 * 3.- Vuelos Destino
 */

document.addEventListener("DOMContentLoaded", () => {
    Cliente();
});


function Cliente(){
    VerificarBotones();
}

function VerificarBotones(){
    const botones = document.querySelectorAll(".listado-opciones .opcion");
    
    
    botones.forEach(boton => {
        boton.addEventListener("click", (e) => {
            let identificador = "";
            
            if(e.target.id.trim("") !== ""){
                identificador = e.target.id;
            }else{
                
                if(e.target.parentNode.id.trim("") !== ""){
                    identificador = e.target.parentNode.id;
                }else{
                    console.log(e.target.parentNode.parentNode);
                    if(e.target.parentNode.parentNode.id.trim("") !== ""){
                        identificador = e.target.parentNode.parentNode.id;
                    }
                }
            }
           
            console.log(identificador);
            
            if(identificador.trim("") !== ""){
                
                let seccionInfo = document.querySelector(`.contenedor-informacion-opciones #${identificador}`);
                //debemos controlar por las clases que mostrar que le damos a cada elemento.
                botones.forEach(b => {
                    let etiqueta = b.id;
                    if(etiqueta !== identificador){
                        //debemos quitarle el indicador a los que no corresponden en nuestro contenedor de informacion. 
                        let ci = document.querySelector(`.contenedor-informacion-opciones #${etiqueta}`);
                        ci.classList.remove("show");
                    }
                });

                //asignamos la clase mostrar al nuevo elemento.
                seccionInfo.classList.add("show");

                //vamos a dar un color diferente a nuestro boton que fue seleccionado....
                incluirAnimacion(boton);
            }
            
        });
        
        //sin generar el evento vamos a verificar por quien posee la clase .show
        let etiqueta = boton.id;
        let seccionInfo = document.querySelector(`.contenedor-informacion-opciones #${etiqueta}`);
        
        if(seccionInfo.classList[1] === "show"){
            incluirAnimacion(boton);
        }
        
    });
}

function incluirAnimacion(boton){
    switch(boton.classList[1]){
        case "opcion-1":
            boton.style.backgroundColor = "#987302";
            boton.nextElementSibling.style.backgroundColor = "#ff5722";
            boton.nextElementSibling.nextElementSibling.style.backgroundColor = "#198754c4";
            break;

        case "opcion-2":
            boton.style.backgroundColor = "#af3b16";
            boton.previousElementSibling.style.backgroundColor = "#ffc107";
            boton.nextElementSibling.style.backgroundColor = "#198754c4";
            break;

        case "opcion-3":
            boton.style.backgroundColor = "#0e4f31c4";
            boton.previousElementSibling.style.backgroundColor = "#ff5722";
            boton.previousElementSibling.previousElementSibling.style.backgroundColor = "#ffc107";
            break;

        default:
            break;
    }
}