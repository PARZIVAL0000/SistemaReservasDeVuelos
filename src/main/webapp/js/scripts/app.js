//esto permitira que el codigo de aqui no pueda ser vista o procesada por otro archivo js
(() => {
    document.addEventListener("DOMContentLoaded", () => {
        App();
    });

    function App(){
        console.log("conexion...");
//        Pasajeros();
        DispararRedireccion();
        EscucharTipoVuelo();
    }

    function Pasajeros(){
        //dentro de este punto lo que haremos a continuacion soloes verificar por los dichos pasajeros..
        let pasajero = document.querySelector("#psj");
        pasajero.addEventListener("click", (e) => {
            e.preventDefault();
        });
    }
    
    //con nuestra funcion lo que haremos es generar un disparo para que el usuario no pueda retroceder a una pagina....
    function DispararRedireccion(){
        window.addEventListener("beforeunload", (e) => {
            e.preventDefault();
        });
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
               }else if(elemento === "Ida-Regreso"){     
                    toDate.classList.remove('hidden');
                    toDate.querySelector('input[type=text]').disabled=false;
                    
                    //clases bootstrap para que los inputs restantes tomen un width:100
                    document.querySelector('#fechaSalida').classList.remove('w-100');
               }   
           });
           
           
           if(vuelo.checked){
                if(vuelo.value === "Ida"){
                    toDate.querySelector('input[type=text]').disabled=true;
                    toDate.classList.add('hidden');
                    //clases bootstrap para que los inputs restantes tomen un width:100
                    document.querySelector('#fechaSalida').classList.add('w-100');
                    
                }
           }
           
        });
    }
    
})();