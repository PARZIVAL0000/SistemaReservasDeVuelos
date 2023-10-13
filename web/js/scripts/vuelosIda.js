
    
let registro = {
    "IDA": {
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


//Nuestra lista guardara un objeto con informacion recolectada de todo lo que hace el cliente.
//con eso podemos analizar acciones y mostrar resultados.
const informacionVuelo = [];

document.addEventListener("DOMContentLoaded", () => {
    VuelosIDA();
});

function VuelosIDA(){
    ReservaVueloDeIda();
//    ReservaVueloIda();
    CerrarVentanaEmergente();
}


function CerrarVentanaEmergente(){
    let botonMensajeAlerta = document.querySelector("#errorAlerta");
    
    if(botonMensajeAlerta){
        botonMensajeAlerta.addEventListener("click", () => {
           let contenedor = document.querySelector(".mensaje_alerta");
           contenedor.style.display='none';
        });
    }
}

function almacenarInformacion(key='',value=''){
    
    if(informacionVuelo.length !== 0){
        if(informacionVuelo[0].registro === 1){
            informacionVuelo[0][`${key}`] = value;
        }
        
        return;
    }
    
    
    informacionVuelo.push( {"registro" : 1} );
    
}

function actualizarCarritoCompra(valor){
    let parrafoCarrito = document.querySelector("#tarifa");
    parrafoCarrito.textContent = valor;
}

function ReservaVueloDeIda(){
    almacenarInformacion();
    
    const inputsRadio = document.querySelectorAll("input[type=radio]");
    
    const verificarAerolinea = (e) => {
        const input = e.target;
        const name = e.target.name;
        const value = e.target.value;
        
        if(value === "true"){
            const id = name.split("-")[1];
            
            const botonConfirmar = document.querySelector(`#id_${id} .boton_confirmar`);
            botonConfirmar.addEventListener("click", (e) => {
                const botonVuelo = e.target;
                
                almacenarInformacion("Aerolinea", document.querySelector(`#id_${id} #MiAerolinea`).textContent);
                almacenarInformacion("NumeroPasajeros", parseInt(document.querySelector(".numeroPasajeros").textContent.split(":")[1]));
                almacenarInformacion("PrecioVuelo", parseInt(document.querySelector(`#id_${id} #tarifa-${id}`).textContent));
                almacenarInformacion("FechaDisponible", document.querySelector(`#id_${id} .fecha span`).textContent);
                almacenarInformacion("HoraSalida", document.querySelector(`#id_${id} .hora #horaSalida`).textContent);
                almacenarInformacion("HoraLlegada", document.querySelector(`#id_${id} .hora #horaLlegada`).textContent);
                almacenarInformacion("PaisSalida", document.querySelector(`#id_${id} .hora #paisSalida`).textContent);
                almacenarInformacion("PaisLlegada", document.querySelector(`#id_${id} .hora #paisLlegada`).textContent);
                
                //vamos a cambiar el valor del carrito de motod de interfaz...
                actualizarCarritoCompra(informacionVuelo[0].PrecioVuelo);
                
                
                //desactivamos los input[radio] solo de la opcion que el usuario eligio...
                input.disabled="true";
                input.parentNode.nextElementSibling.firstChild.nextElementSibling.disabled="true";
                
                 //desactivamos los demas botones de otros vuelos que ya no son necesarios por el momento.
                let inputs = document.querySelectorAll("input[type=radio]");
                inputs.forEach(entrada=>{
                   if(parseInt(entrada.parentNode.id) !== parseInt(id)){
                        entrada.disabled="true";
                        entrada.style.cursor='no-drop';
                    }
                });
                
                //vamos a cambiar a cambiar el boton de confirmacion por un boton que diga "cancelar vuelo"
                //los demas botones terminaran quedando desactivados.
                botonVuelo.style.display='none';
                botonVuelo.nextElementSibling.style.display='inline-block';
                
                //mostramos nuestro formulario de tipo de vuelo para los clientes....
                FormularioClaseVuelos(id, "ida");
                BotonCancelar("ida");
            });
            
        }
        
    };
    
    inputsRadio.forEach(radio => {
        radio.addEventListener("click", verificarAerolinea);
    });
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
                                organizarTipoClaseVuelo(key=["Clase", "PrecioClase"], value=["turista", 100]);      
                            }
                            
                        }else if(botonReserva.id === "boton-clasePrimera"){
                            if(tipoVuelo === "ida"){
                                organizarTipoClaseVuelo(key=["Clase", "PrecioClase"], value=["primera", 350]);
                            }
                        }else if(botonReserva.id === "boton-claseEjecutiva"){
                            if(tipoVuelo === "ida"){
                                organizarTipoClaseVuelo(key=["Clase", "PrecioClase"], value=["ejecutiva", 200]);
                            }
                        }
                        
                        
                        actualizarCarritoCompra(informacionVuelo[0].PrecioVuelo);
                        FormularioFinal();
                   }
                    
                    
                });
            });
        }
    });
}

function organizarTipoClaseVuelo(key=[], value=[]){
    almacenarInformacion(key[0], value[0]);
    almacenarInformacion(key[1], value[1]);
    informacionVuelo[0].PrecioVuelo += (informacionVuelo[0].PrecioClase*informacionVuelo[0].NumeroPasajeros);

    let ventana1 = document.querySelector("#collapseOne");
    ventana1.classList.remove("show");
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
            }
        });
    });
}

function FormularioFinal(){
    //vamos a generar el script para poder introducirlo dentro de nuestro formulario...
    let vueloIda = document.querySelector(".vuelo_ida");
    vueloIda.innerHTML = `
    <ul>
        <li class="vuelo_info"><box-icon type='solid' name='plane-take-off'><img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAAAAXNSR0IArs4c6QAAAZlJREFUSEvt1D1rVFEQxvHfakAQO0mRKBjBb2FjIIKkMGBjY2HpZ7AQhIiFdWwENUYRsRcCgdionY2VosQXiJVpYqPEFwbOhruH+7K77rpb7MAt7r3nzH+e58yZlhFFa0RcE/B/c35idW71QVzEFl4N8hzqrD6L2ziVgG+xmp7tfy2iDnwEt3Alg/zBBh7gKX5UFHEAp3EeJ3Edb9pru2muedzDiRLAdzxKRbzEISzgApZwtLBnHed6AcfaKvXFWj5gBocrHPhcLL4bxcU8oT4sPt7HGd/E1SrF0/iG3zWJQ/0TLHYB/4VnWEFYvR9FxcfwAntYTsryAqJJruESpmrAr1P3P8RO2boiOJonwFFAxHvcSAXEvybg19Rod/CuyY38jOcSfLaw8QviPYZJHj/TlYrODivrjqhjb1lz5crLio+rE8PkMXab1DVZXfxfBv+ENdxNI7QfXmlz5YnC9rAvZnRcoU3E1BpI9HqPBwKNJGMDDnsvD0xWZ6L7+Nj+lCs+k85yGOwYt8/HDjwMpaU5x6a5JoqH5sBfXZNFHxeyvKAAAAAASUVORK5CYII="/></box-icon><p>${vuelos.IDA.DESDE} a ${vuelos.IDA.A}</p></li>
        <li class="vuelo_fecha">
            <p class="fecha_ida">${informacionVuelo[0].FechaDisponible}</p>
            <p class="clase_ida">${informacionVuelo[0].Clase}</p>
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
    
    
//    PagarVuelo();
//    cancelarVuelo();
//    EscucharEntradas();
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




