(() => {
//-------------------------------
//No manipular objetos adaptados al codigo de abajo....
//Si lo que quieres es manipularlo deberas cambiar partes del codigo para
//poder adaptarlo al nuevo cambio... 
let registro = {
    "IDA": {
        "ID" : "",
        "CANTIDAD" : ""
    },
    "REGRESO" : {
        "ID" : "",
        "CANTIDAD" : ""
    },
    "TOTAL" : 0
};

let vuelos = {
    "IDA": {
        "ID":"",
        "CANTIDAD": "",
        "CLASE" : "", 
        "DESDE": "",
        "A" : "",
        "FECHA_SALIDA" : "",
        "HORA_SALIDA" : "",
        "HORA_LLEGADA" : "", 
        "AEROLINEA" : ""
    },
    "REGRESO":{
        "CANTIDAD":"",
        "ID":"",
        "CLASE": "",
        "DESDE" : "",
        "A" : "",
        "FECHA_SALIDA" : "",
        "HORA_SALIDA" : "",
        "HORA_LLEGADA" : "",
        "AEROLINEA": ""
    }
};

//este objeto nos permitira registrar absolutamente todo lo que el usuario escribe....
let pagoVuelo = {
    "cedula" : "",
    "dia" : "",
    "mes" : "",
    "year" : "",
    "titular" : "",
    "correo" : ""
};
    
document.addEventListener("DOMContentLoaded", () => {
    Vuelos();
});

function Vuelos(){
    ReservaVueloIda();
    ReservaVueloRegreso();
    //-----------------
    EscucharEntradas();
    //-----------------
    CapturarTipoMensaje(); //-> este se encarga por verificar por .class que son flags que nos ayudaran a sacar tipos de mensajes de error.
    
    
    let salida = CargarDatosSession(); //-> se encargara de cargar los datos a nivel de la session
    if(salida){
        FormularioFinal(); // -> se encarga de completar nuestro formulario final... debemos revisar cuando el formulario si inicializa por primera vez.
    }
}


//--------------------------------
function CapturarTipoMensaje(){ //-> encargado de verificar por unas clases que se autogeneran en nuestro "formulario final"
    let tipoClase = document.querySelector(".formulario_final");
    
    tipoClase.classList.forEach(clase => {
        //dentro de este punto lo que haremos a continuacion es observar por dos tipos de clases. 
        switch(clase){
            case "cedula_incorrecta":
                GenerarMensaje(clase);
                break;
            case "entradas_incorrecta":
                GenerarMensaje();
                break;
            default:
                break;
        }
    });
}

function CerrarVentanaEmergente(){
    let botonMensajeAlerta = document.querySelector("#errorAlerta");
   
    botonMensajeAlerta.addEventListener("click", () => {
       let contenedor = document.querySelector(".mensaje_alerta");
       contenedor.style.display='none';
    });
}

//con esta funcion escuchamos por las entradas o campos de nuestro formulario final...
function EscucharEntradas(){
    let elementos = document.querySelectorAll(".ver");
    elementos.forEach(elemento => {
        if(elemento.name === "cajaCheck"){
            if(elemento.checked){
                pagoVuelo["titular"] = document.querySelector("#nombre").value;
                pagoVuelo["correo"] = document.querySelector("#correo").value;
            }
        }
        
        elemento.addEventListener("input", (e) => {
            let tipoCampo = e.target.name;
            
            if(tipoCampo === "dniCompra"){
                pagoVuelo["cedula"] = e.target.value;
            }else if(tipoCampo === "dia"){
                pagoVuelo["dia"] = e.target.value;
            }else if(tipoCampo === "mes"){
                pagoVuelo["mes"] = e.target.value;
            }else if(tipoCampo === "year"){
                pagoVuelo["year"] = e.target.value;
            }else if(tipoCampo === "Nombre"){
                pagoVuelo["titular"] = e.target.value;
            }else if(tipoCampo === "Correo"){
                pagoVuelo["correo"] = e.target.value;
            }
        });
        
    });
}

//esta funcion escucha por el boton de pagar de nuestro formulario final...
function PagarVuelo(){
    let botonPagar = document.querySelector("#pagar");
    
    botonPagar.addEventListener("click", (e) => {
        //obtenemos una parte del dom, para identificar a nuestros elementos. 
        let nombreTitular = document.querySelector("#nombreTitular");
        let emailTitular = document.querySelector("#emailTitular");
        //obtenemos nuestros dos campos.
        let nombre = nombreTitular.querySelector("input[type=text]");
        let email = emailTitular.querySelector("input[type=email]");
       
        //dentro de aqui debemos realizar una comparacion
        if(pagoVuelo["cedula"] === "" || pagoVuelo["correo"] === "" || pagoVuelo["dia"] === "" || pagoVuelo["mes"] === "" || pagoVuelo["titular"] === "" || pagoVuelo["year"] === ""){
            e.preventDefault();          
            
            //evitamos duplicar nuestro mensaje de error.
            if(document.querySelectorAll('.error').length === 0){
                GenerarMensaje();
            }
        }else{
            //desactivamos sus respectivos campos para que puedan ser leidos....
            nombre.disabled=false;
            email.disabled=false;
           
            sessionStorage.setItem("registro", [[registro.IDA.ID, registro.IDA.CANTIDAD], [registro.REGRESO.ID, registro.REGRESO.CANTIDAD], registro.TOTAL]);
            sessionStorage.setItem("vuelos", [
                [vuelos.IDA.ID, vuelos.IDA.CANTIDAD, vuelos.IDA.CLASE, vuelos.IDA.DESDE, vuelos.IDA.A, vuelos.IDA.FECHA_SALIDA, vuelos.IDA.HORA_LLEGADA, vuelos.IDA.HORA_SALIDA],
                [vuelos.REGRESO.ID, vuelos.REGRESO.CANTIDAD, vuelos.REGRESO.CLASE, vuelos.REGRESO.DESDE, vuelos.REGRESO.A, vuelos.REGRESO.FECHA_SALIDA, vuelos.REGRESO.HORA_LLEGADA, vuelos.REGRESO.HORA_SALIDA]
            ]);
            
            sessionStorage.setItem("pagoVuelo", [
               pagoVuelo.cedula,
               pagoVuelo.dia, 
               pagoVuelo.mes,
               pagoVuelo.year,
               pagoVuelo.titular,
               pagoVuelo.correo
            ]); 
        }
        
    });
}

function cancelarVuelo(){
    let boton = document.querySelector('.vuelo_cancelar');
    boton.addEventListener('click', e => {
        //eliminamos los session
        sessionStorage.removeItem("pagoVuelo");
        sessionStorage.removeItem("vuelos");
        sessionStorage.removeItem("registro");
    });
}

function GenerarMensaje(clase = ''){
    let contenedor = document.createElement('DIV');
    contenedor.classList.add('error');
    let parrafo = document.createElement('P');
    parrafo.classList.add("error-parrafo");
    parrafo.textContent = "Todos los campos son obligatorios";
      
    if(clase === "cedula_incorrecta"){
        parrafo.textContent = "Cedula introducida incorrecta";
    }
  
    //seleccionamos una parte del DOM para introducir el mensaje. 
    let dom = document.querySelector(".formulario_informacion .secciones .seccion1");
    
    contenedor.appendChild(parrafo);
    dom.appendChild(contenedor);
}


function ReservaVueloIda(){
    //debemos leer a continuacion por los diferentes botones que se encuentran existentes
    let botonVuelos = document.querySelectorAll(".fly-from");
    
    botonVuelos.forEach(botonVuelo => {
        
        botonVuelo.addEventListener("click", () => {
            
            let mensaje = document.querySelector(".mensaje_alerta");
            
            //este de aqui nos ayudra para que solo pueda ser un valor ingresado...
            if(registro.IDA.CANTIDAD.trim() === "" && registro.IDA.ID.trim() === ""){
                //cuando el usuario de click al boton de confirmar... lo que haremos a continuacion
                //es ver por el tipo de vuelo que haya escogido.
                let botonRadio = document.querySelectorAll("input[type=radio].fly_from");
                botonRadio.forEach(br => {
                    let id = botonVuelo.parentNode.id; //el id identificador de cada boton...
                    
                    if(registro.IDA.CANTIDAD.trim() === "" && registro.IDA.ID.trim() === ""){
                        
                        //vamos a generar una validacion extra al programa.
                        if(id === br.value){
                            if(br.id === "flexRadioDefault1" && br.checked){
                               
                                //a continuacion vamos traernos unos datos para llenar nuestro objeto de "vuelos"
                                //como estamos en la parte de vuelo de ida... solo nos traeremos informacion de eso...
                                let horario = document.querySelector(`#collapseOne #id_${br.value} .hora`);
                                let fechaSalida = horario.querySelector(".fecha_salida");
                                let fechaLlegada = horario.querySelector(".fecha_llegada");
                                let fechaDisponible = document.querySelector(`#collapseOne #id_${br.value} .fecha p`);
                                let aerolinea = document.querySelector(`#collapseOne #id_${br.value} .aerolinea > p`);
                                
                                
                                //informacion sobre nuestra salida...
                                vuelos.IDA.HORA_SALIDA = fechaSalida.firstChild.textContent;
                                vuelos.IDA.DESDE = fechaSalida.firstChild.nextElementSibling.textContent;
                                //informacion sobre nuestra llegada o destino... 
                                vuelos.IDA.HORA_LLEGADA = fechaLlegada.firstChild.textContent;
                                vuelos.IDA.A = fechaLlegada.firstChild.nextElementSibling.textContent;
                                //lo unico que nos faltaria seria la fecha... osea la fecha disponible de nuestro viaje....
                                vuelos.IDA.FECHA_SALIDA = fechaDisponible.firstChild.nextElementSibling.textContent;
                                //introducimos el dato correspondiente para nuestra aerolinea.
                                vuelos.IDA.AEROLINEA = aerolinea.children[0].textContent;
                               
                               
                                //el usuario logro escoger la opcion de la aerolinea....
                                registro.IDA.ID = br.value;
                                //sacarnos el presupuesto...
                                let precio = document.querySelector(`#tarifa-${br.value}`).textContent;
                                registro.IDA.CANTIDAD = precio;
                                
                                //aumentamos el valor de nuestro total...
                                registro.TOTAL += parseFloat(registro.IDA.CANTIDAD);

                                //vamos a cambiar el valor del carrito de motod de interfaz...
                                let parrafoCarrito = document.querySelector("#tarifa");
                                parrafoCarrito.textContent = registro.IDA.CANTIDAD;

                                //vamos a cambiar a cambiar el boton de confirmacion por un boton que diga "cancelar vuelo"
                                //los demas botones terminaran quedando desactivados.
                                botonVuelo.style.display='none';
                                botonVuelo.nextElementSibling.style.display='inline-block';

                                //desactivamos los input[radio] solo de la opcion que el usuario eligio...
                                br.disabled="true";
                                br.parentNode.nextElementSibling.firstChild.nextElementSibling.disabled="true";

                                //desactivamos los demas botones de otros vuelos que ya no son necesarios por el momento.
                                let bntVuelos = document.querySelectorAll(".fly-from");
                                bntVuelos.forEach(n=>{
                                   if(parseInt(n.parentNode.id) !== parseInt(id)){
                                        n.disabled="true";
                                        n.style.cursor='no-drop';
                                    }
                                });
                                //mostramos nuestro formulario de tipo de vuelo para los clientes....
                                FormularioClaseVuelos(id, "ida");
                                BotonCancelar("ida");
                            }else if(br.id === "flexRadioDefault2" && br.checked){
                                
                                mensaje.style.display='block';
                                CerrarVentanaEmergente();
                                
                            }
                        }
                        
                    }
                });
            }else{
                //dentro de este punto tenemos que verificar a que tipo de boton le dio nuevamente click el usuario... 
                mensaje.querySelector(".mensaje .mensaje_parrafo").textContent="Ya seleccionaste un horario de vuelo";
                mensaje.querySelector(".mensaje .mensaje_titulo").textContent="!advertencia";
                mensaje.style.display='block';
                CerrarVentanaEmergente();
                
                //cuando el usuario haya cerrado nuestra ventana emergente... lo que haremos a continuacion es reestablecer dicha seleccion
                //a su valor original.
                let botonRadio = document.querySelectorAll("input[type=radio].fly_from");
                botonRadio.forEach(btn => {
                    let id = botonVuelo.parentNode.id;
                    
                    if(id === btn.value && btn.checked){
                        //vamos a cambiar dicho valor a su estado predeterminado....
                        if(!btn.parentNode.nextElementSibling.firstChild.nextElementSibling.checked){
                            btn.checked = false;
                            btn.parentNode.nextElementSibling.firstChild.nextElementSibling.checked = true;
                        }
                    }
                });
                
            }

        });
    });
}

function ReservaVueloRegreso(){
    let botonRetorno = document.querySelectorAll("#collapseTwo .listado_vuelos #vueloRetorno");
    
    botonRetorno.forEach(br => {
        br.addEventListener("click", ()=>{
            //ahora lo que haremos a continuacion 
            let mensaje = document.querySelector(".mensaje_alerta");
             
            if(registro.REGRESO.CANTIDAD.trim() === "" && registro.REGRESO.ID.trim() === ""){
                 //cuando no existe algun tipo de registro....
                 
                let opciones = document.querySelectorAll('input[type=radio].fly_to');
                opciones.forEach(opcion => {
                    let idBoton = br.parentNode.id;
                    //aqui vamos a generar unas modificaciones que nos permitan tener mayor control de las alertas que generamos...
                    
                    if(idBoton === opcion.value){
                        
                        if(opcion.id === "ViajeRetorno1" && opcion.checked){
                            
                            //a continuacion vamos traernos unos datos para llenar nuestro objeto de "vuelos"
                            //como estamos en la parte de vuelo de ida... solo nos traeremos informacion de eso...
                            let horario = document.querySelector(`#collapseTwo .id_${opcion.value} .hora`);
                            let fechaSalida = horario.querySelector(".fecha_salida");
                            let fechaLlegada = horario.querySelector(".fecha_llegada");
                            let fechaDisponible = document.querySelector(`#collapseTwo .id_${opcion.value} .fecha p`);
                            let aerolinea = document.querySelector(`#collapseTwo .id_${opcion.value} .aerolinea  > p`);

                            //informacion sobre nuestra salida...
                            vuelos.REGRESO.HORA_SALIDA = fechaSalida.firstChild.textContent;
                            vuelos.REGRESO.DESDE = fechaSalida.firstChild.nextElementSibling.textContent;
//                                //informacion sobre nuestra llegada o destino... 
                            vuelos.REGRESO.HORA_LLEGADA = fechaLlegada.firstChild.textContent;
                            vuelos.REGRESO.A = fechaLlegada.firstChild.nextElementSibling.textContent;
//                                //lo unico que nos faltaria seria la fecha... osea la fecha disponible de nuestro viaje....
                            vuelos.REGRESO.FECHA_SALIDA = fechaDisponible.firstChild.nextElementSibling.textContent;
                            vuelos.REGRESO.AEROLINEA = aerolinea.children[0].textContent;

                            //aqui ira el codigo necesario
                            //el usuario logro escoger la opcion de la aerolinea....
                            registro.REGRESO.ID = br.value;
                            //sacarnos el presupuesto...
                            let precio = document.querySelector(`#tarifa-${opcion.value}`).textContent;
                            registro.REGRESO.CANTIDAD = precio;

                            //aumentamos el valor de nuestro total...
                            registro.TOTAL += parseFloat(registro.IDA.CANTIDAD);

                            //vamos a cambiar el valor del carrito de motod de interfaz...
                            let parrafoCarrito = document.querySelector("#tarifa2");
                            parrafoCarrito.textContent = registro.REGRESO.CANTIDAD;


                            //vamos a cambiar a cambiar el boton de confirmacion por un boton que diga "cancelar vuelo"
                            //los demas botones terminaran quedando desactivados.
                            br.style.display='none';
                            br.nextElementSibling.style.display='inline-block';

                            //desactivamos los input[radio] solo de la opcion que el usuario eligio...
                            opcion.disabled="true";
                            opcion.parentNode.nextElementSibling.firstChild.nextElementSibling.disabled="true";

                            //desactivamos los demas botones de otros vuelos que ya no son necesarios por el momento.
                            let bntVuelos = document.querySelectorAll("#collapseTwo .listado_vuelos #vueloRetorno");
                            bntVuelos.forEach(n=>{
                               if(parseInt(n.parentNode.id) !== parseInt(idBoton)){
                                    n.disabled="true";
                                    n.style.cursor='no-drop';
                                }
                            });
                            
                            //mostramos nuestro formulario de tipo de vuelo para los clientes....
                            
                            FormularioClaseVuelos(idBoton, "regreso");
                            BotonCancelar("regreso");
                        }else if(opcion.id === "ViajeRetorno2" && opcion.checked){
                            //aqui ira la alerta...
                            //cuando el usuario no ingrese ninguna aerolinea.... lo que debemos hacer es generar una alerta....
                            mensaje.style.display='block';
                            CerrarVentanaEmergente();
                        }
                    }
                    
                }); 
            }else{
                //dentro de aqui lo que haremos es nuevamente generar el mesnaje de error.
                mensaje.querySelector(".mensaje .mensaje_parrafo").textContent="Ya seleccionaste un horario de vuelo";
                mensaje.querySelector(".mensaje .mensaje_titulo").textContent="!advertencia";
                mensaje.style.display='block';
                CerrarVentanaEmergente();
                
                //cuando el usuario haya cerrado nuestra ventana emergente... lo que haremos a continuacion es reestablecer dicha seleccion
                //a su valor original.
                let botonRadio = document.querySelectorAll("input[type=radio].fly_from");
                botonRadio.forEach(btn => {
                    let id = botonVuelo.parentNode.id;
                    
                    if(id === btn.value && btn.checked){
                        //vamos a cambiar dicho valor a su estado predeterminado....
                        if(!btn.parentNode.nextElementSibling.firstChild.nextElementSibling.checked){
                            btn.checked = false;
                            btn.parentNode.nextElementSibling.firstChild.nextElementSibling.checked = true;
                        }
                    }
                });
            }
        });
    });
}

//esta funcion nos despliega la clase de vuelo, y el cliente debe escogerlo.
function FormularioClaseVuelos(id, tipoVuelo){
    //desplegamos un listado de formularios para cada vuelo
    let formularios = document.querySelectorAll(".listado_claseVuelo");
    
    formularios.forEach(formulario => {
        if(formulario.classList[1] === id){
            //mostramos formulario especifico en base al identificador unico de cada ellos.
            formulario.style.display='block';
            
            //escuchamos datos de nuestro formulario.
            let botonesReserva = document.querySelectorAll(".botonClase");
            
            botonesReserva.forEach(botonReserva => {
                
                botonReserva.addEventListener("click", (e) => {
                    e.preventDefault();
                    
                    //escuchamos por los tres botones que son especificos de nuestro formulario. 
                    if(e.target.classList[1] === id){
                        
                        //cuando se encuentren iguales....
                        if(botonReserva.id === "boton-claseTurista"){
                            if(tipoVuelo === "ida"){
                                let numeroPersonas = document.querySelector(".numeroPasajeros");
                                
                                vuelos.IDA.CANTIDAD = "100";
                                vuelos.IDA.ID = `${id}`;
                                vuelos.IDA.CLASE = "turista";
                                
                                let totalPersonas = parseInt(vuelos.IDA.CANTIDAD)*parseInt(numeroPersonas.id);
                                
                                registro.IDA.CANTIDAD = parseFloat(registro.IDA.CANTIDAD) + parseFloat(totalPersonas);
                                registro.TOTAL += parseInt(totalPersonas);
                                
                                //cuando nuesto usuario se disponga a sar un click lo que haremos es redireccionar a nuestro usuario.
                                //vamos a cerrar el campo..
                                let ventana1 = document.querySelector("#collapseOne");
                                ventana1.classList.remove("show");
                                let ventana2 = document.querySelector("#collapseTwo");
                                ventana2.classList.add("show");
                                window.location.href="#collapseTwo";
                            
                            }else if(tipoVuelo === "regreso"){
                                let numeroPersonas2 = document.querySelector(".numeroPasajeros2");
                                
                                vuelos.REGRESO.CANTIDAD = "100";
                                vuelos.REGRESO.ID = `${id}`;
                                vuelos.REGRESO.CLASE = "turista";
                                
                                //esta parte de aqui calcula el dinero general de un vuelo turista y lo multiplica por el numero de pasajeros....
                                //de esta manera vamos aumentando el costo..
                                let numeroPasajeros = parseInt(vuelos.REGRESO.CANTIDAD)*parseInt(numeroPersonas2.id);
                                //vamos ir aumentando nuestra cantidad....
                                registro.REGRESO.CANTIDAD = parseFloat(registro.REGRESO.CANTIDAD) + parseFloat(numeroPasajeros);
                                registro.TOTAL += parseFloat(numeroPasajeros);
                                
                                //cerramos la seccion de escoger vuelo de regreso
                                let ventana2 = document.querySelector("#collapseTwo");
                                ventana2.classList.remove("show");
                                
                                FormularioFinal();
                            }
                            
                        }else if(botonReserva.id === "boton-clasePrimera"){
                            if(tipoVuelo === "ida"){
                                let numeroPersonas = document.querySelector(".numeroPasajeros");
                                vuelos.IDA.CANTIDAD = "350";
                                vuelos.IDA.ID = `${id}`;
                                vuelos.IDA.CLASE = "primera";
                                
                                //vamos ir aumentando nuestra cantidad....
                                let totalPasajeros = parseInt(vuelos.IDA.CANTIDAD)*parseInt(numeroPersonas.id);
                                registro.IDA.CANTIDAD = parseFloat(registro.IDA.CANTIDAD) + totalPasajeros;
                                registro.TOTAL += parseFloat(totalPasajeros);
                                
                                let ventana1 = document.querySelector("#collapseOne");
                                ventana1.classList.remove("show");
                                let ventana2 = document.querySelector("#collapseTwo");
                                ventana2.classList.add("show");
                                window.location.href="#collapseTwo";
                                
                            }else if(tipoVuelo === "regreso"){
                                let numeroPersonas2 = document.querySelector(".numeroPasajeros2");
                                vuelos.REGRESO.CANTIDAD = "350";
                                vuelos.REGRESO.ID = `${id}`;
                                vuelos.REGRESO.CLASE = "primera";
                                
                                //vamos ir aumentando nuestra cantidad....
                                registro.REGRESO.CANTIDAD = parseFloat(registro.REGRESO.CANTIDAD) + parseFloat(parseInt(vuelos.REGRESO.CANTIDAD)*parseInt(numeroPersonas2.id));
                                registro.TOTAL += parseFloat(parseInt(vuelos.REGRESO.CANTIDAD)*parseInt(numeroPersonas2.id));
                                
                                //cerramos la seccion de escoger vuelo de regreso
                                let ventana2 = document.querySelector("#collapseTwo");
                                ventana2.classList.remove("show");
                                
                                FormularioFinal();
                            }
                        }else if(botonReserva.id === "boton-claseEjecutiva"){
                            if(tipoVuelo === "ida"){
                                let numeroPersonas = document.querySelector(".numeroPasajeros");
                                vuelos.IDA.CANTIDAD = "200";
                                vuelos.IDA.ID = `${id}`;
                                vuelos.IDA.CLASE = "ejecutiva";
                                
                                let totalPasajeros = parseInt(vuelos.IDA.CANTIDAD)*parseInt(numeroPersonas.id);
                                registro.IDA.CANTIDAD = parseFloat(registro.IDA.CANTIDAD) + totalPasajeros;
                                registro.TOTAL += parseFloat(totalPasajeros);
                                
                                let ventana1 = document.querySelector("#collapseOne");
                                ventana1.classList.remove("show");
                                let ventana2 = document.querySelector("#collapseTwo");
                                ventana2.classList.add("show");
                                window.location.href="#collapseTwo";
                                
                            }else if(tipoVuelo === "regreso"){
                                let numeroPersonas2 = document.querySelector(".numeroPasajeros2");
                                vuelos.REGRESO.CANTIDAD = "200";
                                vuelos.REGRESO.ID = `${id}`;
                                vuelos.REGRESO.CLASE = "ejecutiva";
                                
                                //vamos ir aumentando nuestra cantidad....
                                registro.REGRESO.CANTIDAD = parseFloat(registro.REGRESO.CANTIDAD) + parseFloat(parseInt(vuelos.REGRESO.CANTIDAD)*parseInt(numeroPersonas2.id));
                                registro.TOTAL += parseFloat(parseInt(vuelos.REGRESO.CANTIDAD)*parseInt(numeroPersonas2.id));
                                
                                //cerramos la seccion de escoger vuelo de regreso
                                let ventana2 = document.querySelector("#collapseTwo");
                                ventana2.classList.remove("show");
                                
                                FormularioFinal();
                            }
                        }
                        
                        
                        //vamos a mostrar la cantidad de cada parte ... de ida y de vuela... 
                        //no mostraremos el total de la suma de todos los vuelos recolectados.... 
                        //ese total lo usaremos para el formulario final... el usuario solamente tendra que confirmar y ya estaria. 
                        if(tipoVuelo === "ida"){
                            //mostramos el valor de ida...
                            let carritoIda = document.querySelector("#tarifa");
                            carritoIda.textContent = `${registro.IDA.CANTIDAD.toString().slice(0, 6)}`;
                        }else if(tipoVuelo === "regreso"){
                            //mostramos el valor de regreso...  
                            let carritoRetorno = document.querySelector("#carrito2 #tarifa2");
                            carritoRetorno.textContent = `${registro.REGRESO.CANTIDAD.toString().slice(0, 6)}`;
                        } 
                    }
                    
                    
                });
            });
        }
    });
}


//el formulario final nos servira mucho porque sera la parte final de nuestra seccion de desarrollo de la reserva del vuelo.
function FormularioFinal(){
    //vamos a generar el script para poder introducirlo dentro de nuestro formulario...
    let vueloIda = document.querySelector(".vuelo_ida");
    vueloIda.innerHTML = `
    <ul>
        <li class="vuelo_info"><box-icon type='solid' name='plane-take-off'><img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAAAAXNSR0IArs4c6QAAAZlJREFUSEvt1D1rVFEQxvHfakAQO0mRKBjBb2FjIIKkMGBjY2HpZ7AQhIiFdWwENUYRsRcCgdionY2VosQXiJVpYqPEFwbOhruH+7K77rpb7MAt7r3nzH+e58yZlhFFa0RcE/B/c35idW71QVzEFl4N8hzqrD6L2ziVgG+xmp7tfy2iDnwEt3Alg/zBBh7gKX5UFHEAp3EeJ3Edb9pru2muedzDiRLAdzxKRbzEISzgApZwtLBnHed6AcfaKvXFWj5gBocrHPhcLL4bxcU8oT4sPt7HGd/E1SrF0/iG3zWJQ/0TLHYB/4VnWEFYvR9FxcfwAntYTsryAqJJruESpmrAr1P3P8RO2boiOJonwFFAxHvcSAXEvybg19Rod/CuyY38jOcSfLaw8QviPYZJHj/TlYrODivrjqhjb1lz5crLio+rE8PkMXab1DVZXfxfBv+ENdxNI7QfXmlz5YnC9rAvZnRcoU3E1BpI9HqPBwKNJGMDDnsvD0xWZ6L7+Nj+lCs+k85yGOwYt8/HDjwMpaU5x6a5JoqH5sBfXZNFHxeyvKAAAAAASUVORK5CYII="/></box-icon><p>${vuelos.IDA.DESDE} a ${vuelos.IDA.A}</p></li>
        <li class="vuelo_fecha">
            <p class="fecha_ida">${vuelos.IDA.FECHA_SALIDA}</p>
            <p class="clase_ida">${vuelos.IDA.CLASE}</p>
            <input type="hidden" name="VueloIda_desde" value="${vuelos.IDA.DESDE}" />
            <input type="hidden" name="VueloIda_fecha" value="${vuelos.IDA.FECHA_SALIDA}"/>
            <input type="hidden" name="VueloIda_horaSalida" value="${vuelos.IDA.HORA_SALIDA}"/>
            <input type="hidden" name="VueloIda_horaLlegada" value="${vuelos.IDA.HORA_LLEGADA}"/>
            <input type="hidden" name="VueloIda_aerolinea" value="${vuelos.IDA.AEROLINEA}"/>
        </li>
        <li class="vuelo_hora">
            <p class="hora_salida"><span>Hora Salida: </span>${vuelos.IDA.HORA_SALIDA}</p>
            <p class="hora_llegada"><span>Hora Llegada: </span>${vuelos.IDA.HORA_LLEGADA}</p>
            <input type="hidden" name="VueloIda_a" value="${vuelos.IDA.A}" />
        </li>
    </ul>
    `;
    
    let vueloRegreso = document.querySelector(".vuelo_regreso");
    vueloRegreso.innerHTML = `
    <ul>
        <li class="vuelo_info"><box-icon name='plane-land' type='solid' ><img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAAAAXNSR0IArs4c6QAAAa9JREFUSEvt1E9LVkEUx/GPEIktI1qXRVRaVO6jCNtE9A6i6FUEGkmbWgatIkLBl9AiqEgEd+0KooiIcGFoK1cS/eHIKLdhHpj7XMoHauBy4dy5v+/vnDlzhuzQGtohrv/gv1b52lJPYh5f8BAPujqsBQfnLY7iB85iqQu8BB7Fx4LoDTxK8RWcxGq/8Bz8HgcxjneZ6DCWsS/FXyCO4Gc/8Bz8HBfwDBcLgjO41YjfRsRarxx8B1NJ5TKeZIr78RmRfazINoy+bEvOwZcasA84jm+Z6GNcb8S+Yix1fDU/B+9FCG2te41rtJYyjPN/nREWcT51fBW81NXRVEcKf39PXRx3+Rh2Z3vuYxrrNeQSeA5Xa37uYe4VouPjibu+UdIqgQ/hCs6kJ4ZGm0HT5AT0KW6mAbT9rUZwD05hIhmJd5R6V4uqvMGJ5v4acEk/rtPphpEwFpOsl5lPaTC1yrg2sWi2yCqOaKs6YSZM3k3l/iPgksERHEaU+rfR2m+pa6vQc9/AgA/gWud0ygKziCbbXHnG5/oZ+JVGY6QuDBy40nz3bQPTXN1TqVT49zL+BQp7RB/Nm3UpAAAAAElFTkSuQmCC"/></box-icon><p>${vuelos.REGRESO.DESDE} a ${vuelos.REGRESO.A}</p></li>
        <li class="vuelo_fecha">
            <p class="fecha_ida">${vuelos.REGRESO.FECHA_SALIDA}</p>
            <p class="clase_ida">${vuelos.REGRESO.CLASE}</p>
            <input type="hidden" name="VueloRegreso_desde" value="${vuelos.REGRESO.DESDE}" />
            <input type="hidden" name="VueloRegreso_fecha" value="${vuelos.REGRESO.FECHA_SALIDA}"/>
            <input type="hidden" name="VueloRegreso_horaSalida" value="${vuelos.REGRESO.HORA_SALIDA}"/>
            <input type="hidden" name="VueloRegreso_horaLlegada" value="${vuelos.REGRESO.HORA_LLEGADA}"/>
            <input type="hidden" name="VueloRegreso_aerolinea" value="${vuelos.REGRESO.AEROLINEA}"/>
        </li>
        <li class="vuelo_hora">
            <p class="hora_salida"><span>Hora Salida: </span>${vuelos.REGRESO.HORA_SALIDA}</p>
            <p class="hora_llegada"><span>Hora Llegada: </span>${vuelos.REGRESO.HORA_LLEGADA}</p>
            <input type="hidden" name="VueloRegreso_a" value="${vuelos.REGRESO.A}" />
        </li>
    </ul>
    `;
    
    
    //vamos a desplegar el formulario para que el usuario pueda pagar... (simular que va a realizar un pago con ayuda de su cedula)
    let pagoTotal = document.querySelector(".informacion_pagoFinal");
    let reseteoPago = registro.TOTAL.toString().slice(0, 6);
    pagoTotal.innerHTML = ` <p>Total a pagar <span>USD ${reseteoPago}</span></p>`;
    
    //el formulario para realizar el pago...
    let formulario = document.querySelector(".formulario_final");
    formulario.style.display='block';
    
    //la funcion se encargara de gestionar y verificar cuando el usuario le de click al boton de cancelar.
    //En base al boton de "pagar vuelo" como se trata de un <form> el "action" va hacer que me redireccione automaticamente
    //a la direccion que le estoy dando... por eso, pienso que no voy a leer el boton de "pagar vuelo"... en el backend
    //es mas probable que me sirva y pueda obtener los datos para poder hacer una verficacion y validacion respectiva.
    let botonCancelar = document.querySelector(".formulario_informacion .informacion_facturacion #cancelar");
    botonCancelar.addEventListener("click", (e) => {
        e.preventDefault();
        window.location.href="/ReservaDeVuelos";
    });
         
    IncluirFechas();
    
    //vamos a incluir un evento para nuestro boton checkbox y asi poder verificar si debemos introducir los datos de nuestro primer
    //cliente o que el usuario pueda introducir sus propios datos. 
    CargarDatos();
    
    
    PagarVuelo();
    cancelarVuelo();
    EscucharEntradas();
}


let SalvarDatos = [];
//esta funcion esta relacionado con la seccion "DATOS DE FACTURACION" de nuestro formulario final.
//el proposito era escuchar por un checkbox... si existe el visto cargamos datos obtenidos automaticamente
//caso contrario los campos quedan en blanco para que el cliente introduzca sus propios datos. 
function CargarDatos(){
    let botonCheckbox = document.querySelector(".formulario_final .informacion_facturacion input[type=checkbox]");
    //obtenemos una parte del dom, para identificar a nuestros elementos. 
    let nombreTitular = document.querySelector("#nombreTitular");
    let emailTitular = document.querySelector("#emailTitular");
    //
    let nombre = nombreTitular.querySelector("input[type=text]");
    let email = emailTitular.querySelector("input[type=email]");
    
    botonCheckbox.addEventListener("click", (e) => {        
        //cuando el usuario haya seleccionado la opcion vamos a desactivar nuestras entradas y las dejaremos 
        //completadas con los datos del usuario...
        
        if(!e.target.checked){
            
            let datos = [];
            datos.push(nombre.value);
            datos.push(email.value);
            
            SalvarDatos = [...datos];
            
            nombre.disabled=false;
            email.disabled=false;

            nombre.value="";
            email.value="";
           
        }else{
            nombre.value=`${SalvarDatos[0]}`;
            email.value=`${SalvarDatos[1]}`;
            
            nombre.disabled=true;
            email.disabled=true;
        }
    });
    
    nombre.disabled=true;
    email.disabled=true;
}

function IncluirFechas(){
    
    //vamos a generar las fechas para nuestro formulario debido a que no las posee por el momento...
    let i = 1;
    let m = 1;
    let date = new Date();
    let y = date.getFullYear();
    let ymin = 2000;
    
    for(; i <= 31; i++){
        let dias = document.querySelector("#days");
        let opcion = document.createElement('OPTION');
        opcion.value=`${i}`;
        opcion.textContent=`${i}`;
        
        dias.appendChild(opcion);
    }
    
    for(; m <= 12; m++){
        let meses = document.querySelector("#months");
        let opcion = document.createElement('OPTION');
        if(m >= 10){
            opcion.value=`${m}`;
            opcion.textContent=`${m}`;
        }else{
            opcion.value=`0${m}`;
            opcion.textContent=`0${m}`;
        }
        meses.appendChild(opcion);
    }
    
    //vamos a generar para nuestra cedula...
    for(; ymin <= y; ymin++){
        let years = document.querySelector("#years");
        let opcion = document.createElement('OPTION');
        opcion.value=`${ymin}`;
        opcion.textContent=`${ymin}`;
        years.appendChild(opcion);
    }
}

//este boton se genera cuando el usuario escoge su aerolinea. Esta funcion
//permite generar un boton para que el usuario pueda cancelar o eliminar dicha aerolinea,
//y pueda escoger otra. 
function BotonCancelar(tipo){
    
    let botonCancelar = document.querySelectorAll(".boton_cancelar");
    botonCancelar.forEach(bc => {
        bc.addEventListener("click", (e) => {
            let idBtn = e.target.id;
            let botonesRadio = "";
            if(tipo === "ida"){
                //vamos a desactivar nuestros inputs[radio]
                botonesRadio = document.querySelectorAll("input[type=radio].fly_from");
            }else if(tipo === "regreso"){
                botonesRadio = document.querySelectorAll("input[type=radio].fly_to");
            }
           
            
            botonesRadio.forEach(btnRadio => {
                if(idBtn === btnRadio.value){
                    btnRadio.disabled=false;
                    
                    if(tipo === "ida"){
                        if(btnRadio.id === "flexRadioDefault2"){
                            btnRadio.checked=true;
                        }
                    }else if(tipo === "regreso"){
                        if(btnRadio.id === "ViajeRetorno2"){
                            btnRadio.checked=true;
                        }
                    }
                }
            });
            
            
            //vamos a esconder el formulario nuevamente....
            let formularios = document.querySelectorAll(".listado_claseVuelo");
            formularios.forEach(formulario => {
                let idForm = formulario.classList[1];
                if(idBtn === idForm){
                    formulario.style.display='none';
                }
            });
            
            //finalmente cambiamos el boton
            e.target.style.display='none';
            e.target.previousElementSibling.style.display='inline-block';
            
            //activamos nuevamente los demas botones que se encontraban desactivados...
            let btns = "";
            if(tipo === "ida"){
                btns = document.querySelectorAll(".fly-from");
            }else if(tipo === "regreso"){
                btns = document.querySelectorAll(".vueloRetorno");
               
            }
            
            
            btns.forEach(btn => {
                let idbtn = btn.parentNode.id;
                
                if(idBtn !== idbtn){
                    btn.disabled=false;
                    btn.style.cursor="default";
                }
            });
            
            
            //debemos restaurar cierta informacion a su fase inicial... debido a que el usuario cancelo su deseo de
            //contratar o arrendar una aerolinea para poder ser llevada.
            if(tipo === "ida"){
                //informacion sobre nuestra salida...
                vuelos.IDA.HORA_SALIDA = "";
                vuelos.IDA.DESDE = "";
                //informacion sobre nuestra llegada o destino... 
                vuelos.IDA.HORA_LLEGADA =  "";
                vuelos.IDA.A = ""; 
                //lo unico que nos faltaria seria la fecha... osea la fecha disponible de nuestro viaje....
                vuelos.IDA.FECHA_SALIDA = "";
                
                //volvemos a limpiar nuestro arreglo....
                registro.IDA.CANTIDAD = "";
                registro.IDA.ID = "";
                
                //la parte del carrito volvemos a establecerlo en cero....
                let parrafoCarrito = document.querySelector("#tarifa");
                parrafoCarrito.textContent="0.0";
            }else if(tipo === "regreso"){
                //informacion sobre nuestra salida...
                vuelos.REGRESO.HORA_SALIDA = "";
                vuelos.REGRESO.DESDE = "";
                //informacion sobre nuestra llegada o destino... 
                vuelos.REGRESO.HORA_LLEGADA =  "";
                vuelos.REGRESO.A = ""; 
                //lo unico que nos faltaria seria la fecha... osea la fecha disponible de nuestro viaje....
                vuelos.REGRESO.FECHA_SALIDA = "";
                
                registro.REGRESO.CANTIDAD = "";
                registro.REGRESO.ID = "";
                
                //la parte del carrito volvemos a establecerlo en cero...
                let parrafoCarrito = document.querySelector("#carrito2 #tarifa2");
                parrafoCarrito.textContent="0.0";
            }
        });
    });
}


//con esta funcion lo que haremos es traernos dichos datos que los encontramos dentro de nuestra session.
function CargarDatosSession(){
    //vamos a volver a rellenar nuestros objetos con ayuda de los datos guardados en sesion.
    let vuelosSessionStorage = sessionStorage.getItem("vuelos");
    let pagoVuelosSessionStorage = sessionStorage.getItem("pagoVuelo");
    let registrosSessionStorage = sessionStorage.getItem("registro");
    
    if(vuelosSessionStorage !== null && pagoVuelosSessionStorage !== null && registrosSessionStorage !== null){
        //listamos nuestros datos
        vuelosSessionStorage = vuelosSessionStorage.split(",");
        pagoVuelosSessionStorage = pagoVuelosSessionStorage.split(",");
        registrosSessionStorage = registrosSessionStorage.split(",");

        //introducimos nuestros datos a sus campos respectivos.
        for(let i = 0; i < vuelosSessionStorage.length; i++){
            if(i%8===0){
                switch(i){
                    case 0:
                        vuelos.IDA.ID = vuelosSessionStorage[i];
                        vuelos.IDA.CANTIDAD = vuelosSessionStorage[i+1];
                        vuelos.IDA.CLASE = vuelosSessionStorage[i+2];
                        vuelos.IDA.DESDE = vuelosSessionStorage[i+3];
                        vuelos.IDA.A = vuelosSessionStorage[i+4];
                        vuelos.IDA.FECHA_SALIDA = vuelosSessionStorage[i+5];
                        vuelos.IDA.HORA_SALIDA = vuelosSessionStorage[i+6];
                        vuelos.IDA.HORA_LLEGADA = vuelosSessionStorage[i+7];
                        break;
                    case 8:
                        vuelos.REGRESO.ID = vuelosSessionStorage[i];
                        vuelos.REGRESO.CANTIDAD = vuelosSessionStorage[i+1];
                        vuelos.REGRESO.CLASE = vuelosSessionStorage[i+2];
                        vuelos.REGRESO.DESDE = vuelosSessionStorage[i+3];
                        vuelos.REGRESO.A = vuelosSessionStorage[i+4];
                        vuelos.REGRESO.FECHA_SALIDA = vuelosSessionStorage[i+5];
                        vuelos.REGRESO.HORA_SALIDA = vuelosSessionStorage[i+6];
                        vuelos.REGRESO.HORA_LLEGADA = vuelosSessionStorage[i+7];
                        break;
                    default:
                        break;
                }
            }
        }

        //realizamos la insercion para pagosVuelos.
        pagoVuelo.cedula = pagoVuelosSessionStorage[0];
        pagoVuelo.dia = pagoVuelosSessionStorage[1];
        pagoVuelo.mes = pagoVuelosSessionStorage[2];
        pagoVuelo.year = pagoVuelosSessionStorage[3];
        pagoVuelo.titular = pagoVuelosSessionStorage[4];
        pagoVuelo.correo = pagoVuelosSessionStorage[5];

        //realizamos la insercion para registros
        registro.IDA.ID = registrosSessionStorage[0];
        registro.IDA.CANTIDAD = registrosSessionStorage[1];
        registro.REGRESO.ID = registrosSessionStorage[2];
        registro.REGRESO.CANTIDAD = registrosSessionStorage[3];
        registro.TOTAL = registrosSessionStorage[4];
        
        return true;
    }else{
        
        return false;
    }
    
   
}
    
})();