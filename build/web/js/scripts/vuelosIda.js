( () => {

//Nuestra lista guardara un objeto con informacion recolectada de todo lo que hace el cliente.
//con eso podemos analizar acciones y mostrar resultados.
const informacionVuelo = [];

const expresion = {
	nombres: /^[a-zA-ZÀ-ÿ\s]{1,40}$/, // Letras y espacios, pueden llevar acentos.
        cedula : /^[0-9]{10,10}$/,
	correo: /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/,
	telefono: /^\d{10,10}$/ // 7 a 14 numeros.
};

const formularioFinal = {
    "NumeroCedula" : false,
    "FechaVencimiento" : false,
    "NombreTitular" : false,
    "CorreoElectronico" : false 
};

const selectFecha = {
    "dia" : false,
    "mes" : false,
    "ano" : false
};


document.addEventListener("DOMContentLoaded", () => {
    VuelosIDA();
});

function VuelosIDA(){
    ReservaVueloDeIda();
    CerrarVentanaEmergente();
}

async function comprobarCedula(numeroCedula){
    const url = `http://127.0.0.1:8888/cedula/${numeroCedula}`; 
    const conexion = await fetch(url, {
        method: 'POST',
        headers: {"Content-Type":"text/plain"}
    });
      
    const resultado = await conexion.text();
   
    return resultado === "Correcto";
}

function EnviarFormulario(informacion){    
    const nodo = document.getElementById("resultadoFinal");
    const resultado = filtrarInformacionDeVuelo(informacion); 
    nodo.value = JSON.stringify(resultado[0]);
}

function filtrarInformacionDeVuelo(informacion){
    const keys =  Object.keys(informacion[0]);
    
    for(let i = 0; i < keys.length; i++){
        switch(keys[i]){
            case "InformacionPago":
                const keysPago = Object.keys(informacion[0][keys[i]]);
                for(let j = 0; j < keysPago.length; j++){
                    if(keysPago[j] === "PrecioClase"){
                        cambiarTipoDato(informacion, keys[i], keysPago[j]);
                    }else if(keysPago[j] === "id"){
                        cambiarTipoDato(informacion, keys[i], keysPago[j]);
                    }
                }
                break;
            case "PasajerosInfo":
                cambiarTipoDato(informacion, key="PasajerosInfo", key2='', tipo=false);
                formatearInformacionPasajeros(informacion);
                break;
            case "registro":
                const sub_keys = Object.keys(informacion[0][keys[i]]);
              
                for(let j = 0; j < sub_keys.length; j++){
                    
                    if(sub_keys[i] === "NumeroPasajeros"){
                        cambiarTipoDato(informacion, keys[i], sub_keys[j]);
                    }else if(sub_keys[i] === "PrecioVuelo"){
                        cambiarTipoDato(informacion, keys[i], sub_keys[j]);
                    }else if(sub_keys[i] === "id"){
                        cambiarTipoDato(informacion, keys[i], sub_keys[j]);
                    }
                }
                
                break;
            default:
                break;
        }
    }
    
    return informacion;
}

function formatearInformacionPasajeros(informacion){
    const keys = Object.keys(informacion[0]["PasajerosInfo"]);
    
    for(let i = 0; i < keys.length; i++){
        if(keys[i] !== "id" && keys[i] !== "registros"){
            const keysInfo = Object.keys(informacion[0]["PasajerosInfo"][keys[i]]);
            keysInfo.pop(); //eliminar el ultimo elemento.
            const nuevoRegistro = {};
            
            for(let j = 0; j < keysInfo.length; j++){
                const key = keysInfo[j];
                const value = informacion[0]["PasajerosInfo"][keys[i]][key];
                
                nuevoRegistro[key] = value;
            }
            
            informacion[0]["PasajerosInfo"][keys[i]] = nuevoRegistro;
        }
    }
}

function cambiarTipoDato(informacion='', key='', key2='' ,tipo=true){
    if(tipo){
        const value = informacion[0][key][key2].toString();
        informacion[0][key][key2] = value;
    }else{
        if(key !== "PasajerosInfo"){
            const value = informacion[0][key].toString();
            informacion[0][key] = value;
            return;
        }
        
        const keysPasajeros = Object.keys(informacion[0][key]);
        for(let i = 0; i < keysPasajeros.length; i++){
            const indice = keysPasajeros[i];
            
            if(indice !== "id" && indice !== "registros"){
                const registro = informacion[0][key][`${indice}`];
                
                const keysInfoPasajeros = Object.keys(registro);
                for(let i = 0; i < keysInfoPasajeros.length; i++){
                    const value = informacion[0][key][`${indice}`][`${keysInfoPasajeros[i]}`].toString();
                    informacion[0][key][`${indice}`][`${keysInfoPasajeros}`] = value;
                }
            }else{
                const value = informacion[0][key][`${indice}`].toString();
                informacion[0][key][`${indice}`] = value;
            }
        }
    }
}

//esta funcion se ejecuta al mostrar formulario final
function recolectarInformacionPasajeros(){
    const informacionVuelos =  document.getElementsByClassName("informacionPasajero");
    
    for(let i = 0; i < informacionVuelos.length; i++){
        const registro = { };
        
        registro[`Nombres-${i+1}`] = "";
        registro[`Cedula-${i+1}`] = "";
        registro[`TipoPasajero-${i+1}`] = "";
        registro[`Email-${i+1}`] = "";
        registro[`Celular-${i+1}`] = "";
        
        almacenarInformacion(`registro-${i+1}`, registro, true, 2);
    }
    
    for(let i = 0; i < informacionVuelos.length; i++){
        introducirDatosInfoPasajeros(`Nombres`, i, informacionVuelos);
        introducirDatosInfoPasajeros(`Cedula`, i,  informacionVuelos);
        const resultado = introducirDatosInfoPasajeros(`TipoPasajero`, i, informacionVuelos);

        if(resultado !== "Niño"){
            introducirDatosInfoPasajeros("Email", i, informacionVuelos);
            introducirDatosInfoPasajeros("Celular", i, informacionVuelos);
        }        
    }
}

function introducirDatosInfoPasajeros(tipo, i, informacionVuelos){
    const resultado = informacionVuelos[i].querySelector(`.info-${tipo}-${i+1}`).textContent.split(":")[1].trim();
    almacenarInformacion(tipo, resultado, true, 2, i+1);
    
    if(tipo === "TipoPasajero"){
        return resultado;
    }
}

//este cambia dos estados de boton (Confirmar) y (Cancelar)
function cambiarTipoBoton(boton, botonVuelo){
    if(boton === "confirmar"){
        //finalmente cambiamos el boton
        botonVuelo.target.style.display='none';
        botonVuelo.target.previousElementSibling.style.display='inline-block';
    }else if(boton === "cancelar"){
        //los demas botones terminaran quedando desactivados.
        botonVuelo.style.display='none';
        botonVuelo.nextElementSibling.style.display='inline-block';
    }
}

function cambiarAccesoInputRadio(estado){
    const opcionRadio = document.querySelectorAll("input[type=radio]");
    
    if(estado === "accesible"){
        //desactivamos inputs (en general)
        opcionRadio.forEach(radio => {
            radio.disabled=false; 
            radio.style.cursor='default'; 
            if(radio.value === 'false'){
                radio.checked=true;
            }else{
                radio.checked=false;
            }
        });
    }else if(estado === "inaccesible"){
        //desactivamos los input[radio] solo de la opcion que el usuario eligio...
        opcionRadio.forEach(radio => {radio.disabled = true; radio.style.cursor='no-drop';});
    }
}

function cambiarAccesoBotonConfirmar(estado){
    if(estado === "accesible"){
        //desactivamos botones 
        document.querySelectorAll(".boton_confirmar").forEach(boton => { boton.disabled=false; boton.style.cursor="default"; });
       
    }else if(estado === "inaccesible"){
        //desactivamos botones 
        document.querySelectorAll(".boton_confirmar").forEach(boton => { boton.disabled=true; boton.style.cursor="no-drop"; });
    }
}

function ValidarCamposFormularioPago(){
    const inputs = document.querySelectorAll(".formulario_final input");
    const selects = document.querySelectorAll(".formulario_final select");
    
    const validarEntradasInput = (e, flag=false) => {
        let name = "";
        let value = "";
        
        if(flag){
            name = e.name;
            value = e.value;
        }else{
            name = e.target.name;
            value = e.target.value;
        }
        
        switch(name){
            case "dniCompra":              
                verificaryGuardarInfo("NumeroCedula","dniCompra",value,"cedula","cedulaMensaje", idCampo=["dni"]);
                break;
                
            case "Names":               
                verificaryGuardarInfo("NombreTitular","NombreTitular",value,"nombres","NombreTitularMensaje", idCampo=["nombre"]);        
                break;
                
            case "Emails":               
                verificaryGuardarInfo("CorreoElectronico","Email",value,"correo","CorreoMensaje", idCampo=["correo"]);
                break;
                
            default:
                break;
        }
    };
    
    const validarEntradaSelect = (e, flag = false) => {
        let name = "";
        let value = "";
        
        if(flag){
            name = e.name;
            value = e.value;
        }else{
            name = e.target.name;
            value = e.target.value;
        }
        
        switch(name){
            case "dia":
                verificarPartesFecha(value, "dia");
                break;
                
            case "mes":
                verificarPartesFecha(value, "mes");
                break;
                
            case "year":
                verificarPartesFecha(value, "ano");
                break;
                
            default:
                break;
        }
    };
    
    inputs.forEach(input => {
        input.addEventListener("keyup", validarEntradasInput);
        input.addEventListener("blur", validarEntradasInput);
        input.addEventListener("input", validarEntradasInput);
        
        validarEntradasInput(input, true);
    });
    
     
    selects.forEach(select => {
        select.addEventListener("input", validarEntradaSelect);
        select.addEventListener("blur", validarEntradaSelect);
        
        validarEntradaSelect(select, true);
    });
}

function verificarPartesFecha(value, key){
    if(value.trim() !== ""){
        selectFecha[`${key}`] = true;
        almacenarInformacion(key,value,true);
    }else{
        selectFecha[`${key}`] = false;
    }
    
    const resultado = verificarFechaCompleta();
  
    if(!resultado){
        introducirMensajeError(true,"fechaVencimientoMensaje", idCampo=["days", "months", "years"]);
    }else{
        introducirMensajeError(false,"fechaVencimientoMensaje", idCampo=["days", "months", "years"]);
    }
}

function verificarFechaCompleta(){
    if(selectFecha.dia && selectFecha.mes && selectFecha.ano){
        formularioFinal.FechaVencimiento = true;
        return true;
    }
    
    return false;
}

function ProcesaryAlmacenar(estado, idMensaje, idCampo, keyFormulario, value=''){
    if(estado){
        almacenarInformacion(keyFormulario,value,true);
        introducirMensajeError(false,idMensaje, idCampo);
        formularioFinal[`${keyFormulario}`] = true;
    }else{
        introducirMensajeError(true,idMensaje, idCampo);
        formularioFinal[`${keyFormulario}`] = false;
    }
}

function verificaryGuardarInfo(keyFormulario, key, value, validacion, idMensaje, idCampo=[]){
    if(value.trim() !== ""){
        if(key === "dniCompra"){
            if(expresion[`${validacion}`].test(value)){
                const comprobacion = comprobarCedula(value);
                comprobacion.then(respuesta => {
                   
                    if(respuesta){
                        ProcesaryAlmacenar(true, idMensaje, idCampo, keyFormulario, value);
                    }else{
                        ProcesaryAlmacenar(false, idMensaje, idCampo, keyFormulario);
                    }

                });
                
                
            }else{
               ProcesaryAlmacenar(false, idMensaje, idCampo, keyFormulario);
            }
        }else{
            if(expresion[`${validacion}`].test(value)){                    
                ProcesaryAlmacenar(true, idMensaje, idCampo, keyFormulario, value);
            }else{
                ProcesaryAlmacenar(false, idMensaje, idCampo, keyFormulario);
            }
        }
        
    }else{
        ProcesaryAlmacenar(false, idMensaje, idCampo, keyFormulario);
    }
}

//[*]
function CerrarVentanaEmergente(){
    let botonMensajeAlerta = document.querySelector("#errorAlerta");
    
    if(botonMensajeAlerta){
        botonMensajeAlerta.addEventListener("click", () => {
           let contenedor = document.querySelector(".mensaje_alerta");
           contenedor.style.display='none';
        });
    }
}

function GenerarVentanaEmergente(){
    let mensaje = document.getElementsByClassName("mensaje_alerta")[0];
    mensaje.style.display='block';
}

function introducirMensajeError(estado,idMensaje, idCampo=[]){
    let campo = [];
    const mensaje = document.getElementById(`${idMensaje}`);
    
    if(idCampo.length === 1){
        campo.push(document.getElementById(`${idCampo[0]}`));
    }else if(idCampo.length === 3){
        campo = [];
        for(let i = 0; i < idCampo.length; i++){
            campo.push(document.getElementById(`${idCampo[i]}`));
        }
    }
    
    if(estado){
        mensaje.classList.add("mostrar");
        mensaje.classList.remove("ocultar");
        
        if(campo.length === 3){
            campo.forEach(nodo => {
                
                if(!nodo.classList.contains("alerta_campo")){
                    nodo.classList.add("alerta_campo");
                }
            });
            
        }else if(campo.length === 1){
            
            campo[0].classList.add("alerta_campo");
        }
        
       
    }else{
        mensaje.classList.add("ocultar");
        mensaje.classList.remove("mostrar");
        
        if(campo.length === 3){
            campo.forEach(nodo => {
                if(nodo.classList.contains("alerta_campo")){
                    nodo.classList.remove("alerta_campo");
                }
            });
            
        }else if(campo.length === 1){   
            campo[0].classList.remove("alerta_campo");
        }
    }
}


function almacenarInformacion(key='',value='',flag=false, id=1, registro=0){
    if(informacionVuelo.length !== 0){
        if(informacionVuelo[0].registro.id === 1){
            if(!flag){
                informacionVuelo[0]['registro'][`${key}`] = value;
            }else{
                
                if(id === 3){
                    informacionVuelo[0][`${key}`] = value;
                }else{
                    if(informacionVuelo[0]['InformacionPago']['id'] === id){
                        informacionVuelo[0]['InformacionPago'][`${key}`] = value;
                    }else if(informacionVuelo[0]['PasajerosInfo']['id'] === id){
                        if(registro !== 0){  
                            informacionVuelo[0]['PasajerosInfo'][`registro-${registro}`][`${key}-${registro}`] = value;
                        }else{
                            informacionVuelo[0]['PasajerosInfo'][`${key}`] = value;

                            //vamos incrementando el numero de regsitros que se va introduciendo.
                            informacionVuelo[0]['PasajerosInfo']['registros'] += 1;
                        }
                    }
                }
                
            }
        }   
        return;
    }
    informacionVuelo.push( {"registro" : { "id": 1 } } );
}

function actualizarCarritoCompra(valor){
    let parrafoCarrito = document.querySelector("#tarifa");
    parrafoCarrito.textContent = valor;
}

const seleccionAerolinea = {
    "inputRadio" : false,
    "botonConfirmar" : false
};

function ReservaVueloDeIda(){
    almacenarInformacion();
    const buttonConfirm = document.querySelectorAll(".boton_confirmar");
    
    const verificarConfirmacion = (e) => {
        const botonVuelo = e.target;
        const id = e.target.parentNode.id;
        const inputsRadio = document.querySelectorAll(`#id_${id} input[type=radio]`); 
        inputsRadio.forEach(input => {
            const value = input.value;
            
            if(value.trim() === "true" && input.checked){
                almacenarInformacion("Aerolinea", document.querySelector(`#id_${id} #MiAerolinea`).textContent);
                almacenarInformacion("NumeroPasajeros", parseInt(document.querySelector(".numeroPasajeros").textContent.split(":")[1]));
                almacenarInformacion("PrecioVuelo", parseFloat(document.querySelector(`#id_${id} #tarifa-${id}`).textContent));
                almacenarInformacion("FechaDisponible", document.querySelector(`#id_${id} .fecha span`).textContent);
                almacenarInformacion("HoraSalida", document.querySelector(`#id_${id} .hora #horaSalida`).textContent);
                almacenarInformacion("HoraLlegada", document.querySelector(`#id_${id} .hora #horaLlegada`).textContent);
                almacenarInformacion("PaisSalida", document.querySelector(`#id_${id} .hora #paisSalida`).textContent);
                almacenarInformacion("PaisLlegada", document.querySelector(`#id_${id} .hora #paisLlegada`).textContent);
                
                //vamos a cambiar el valor del carrito de motod de interfaz.
                actualizarCarritoCompra(informacionVuelo[0]["registro"].PrecioVuelo);
                
                cambiarAccesoInputRadio("inaccesible");
                     
                cambiarTipoBoton("cancelar", botonVuelo);
                
                cambiarAccesoBotonConfirmar("inaccesible");
                
                //mostramos nuestro formulario de tipo de vuelo para los clientes.
                FormularioClaseVuelos(id);
                
                //verificamos cuando el usuario quiera cancelar la opcion que eligio.
                BotonCancelar(id);
            }else if(value.trim() === "false" && input.checked){
                GenerarVentanaEmergente();
            }
            
        });
    };
    
    buttonConfirm.forEach(button => {
        button.addEventListener("click", verificarConfirmacion);
    });
}

//esta funcion nos despliega la clase de vuelo, y el cliente debe escogerlo.
function FormularioClaseVuelos(id){
    const formulario = document.querySelector(`#id_${id} .listado_claseVuelo`);
    //crear nuestro contenedor de informacion de pago
    almacenarInformacion("InformacionPago", {"id" : 1}, true, 3);
    //mostramos clase de vuelos
    formulario.style.display='block';
    //escuchamos datos de nuestro formulario.
    let botonesReserva = formulario.querySelectorAll(".botonClase");

    botonesReserva.forEach(botonReserva => {

        botonReserva.addEventListener("click", (e) => {
            e.preventDefault();
            const tipoBoton = e.target;

            //cuando se encuentren iguales....
           if(tipoBoton.id === "boton-claseTurista"){
               almacenarInformacion("Clase", "turista", true);
               almacenarInformacion("PrecioClase", 100, true);
               organizarTipoClaseVuelo();      

            }else if(tipoBoton.id === "boton-clasePrimera"){
               almacenarInformacion("Clase", "primera", true);
               almacenarInformacion("PrecioClase", 350, true);
               organizarTipoClaseVuelo();
  
            }else if(tipoBoton.id === "boton-claseEjecutiva"){
               almacenarInformacion("Clase", "ejecutiva", true);
               almacenarInformacion("PrecioClase", 200, true);
               organizarTipoClaseVuelo();
            }

            actualizarCarritoCompra(informacionVuelo[0]["registro"].PrecioVuelo);
            FormularioFinal();
        });
        
    });
}

function organizarTipoClaseVuelo(){   
    const resultado = informacionVuelo[0]["registro"].PrecioVuelo + (informacionVuelo[0]["InformacionPago"].PrecioClase*informacionVuelo[0]["registro"].NumeroPasajeros);
    informacionVuelo[0]["registro"].PrecioVuelo = resultado;

    const ventana1 = document.querySelector("#collapseOne");
    ventana1.classList.remove("show");
}


function BotonCancelar(id){
    const btnCancelar = document.querySelector(`#id_${id} .boton_cancelar`);
    btnCancelar.addEventListener("click", (e) => {
        //esconder nuestro contenido de clases de vuelos.
        claseVuelo(false, id);
       
        //cambiamos a nuestro boton de confirmar
        cambiarTipoBoton("confirmar", e);
        
        //reseteamos nuestra lista que captura la informacion.
        informacionVuelo[0] = {"registro" : { "id": 1 } };
        
        actualizarCarritoCompra('0.0');
        
        //accemos accesible todos nuestros input radios
        cambiarAccesoInputRadio("accesible");
      
        cambiarAccesoBotonConfirmar("accesible");  
    });
}

function claseVuelo(accion = true, id){
    if(!accion){
         //vamos a esconder el formulario nuevamente....
        let clasesVuelo = document.querySelector(`#id_${id} .listado_claseVuelo`);
        clasesVuelo.style.display='none';
    }
}

function FormularioFinal(accion = true){
    if(accion){
        almacenarInformacion("PasajerosInfo",{"id":2, "registros":0}, true, 3);
        
        //vamos a generar el script para poder introducirlo dentro de nuestro formulario...
        let vueloIda = document.querySelector(".vuelo_ida");
        vueloIda.innerHTML = `
        <ul>
            <li class="vuelo_info"><box-icon type='solid' name='plane-take-off'><img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAAAAXNSR0IArs4c6QAAAZlJREFUSEvt1D1rVFEQxvHfakAQO0mRKBjBb2FjIIKkMGBjY2HpZ7AQhIiFdWwENUYRsRcCgdionY2VosQXiJVpYqPEFwbOhruH+7K77rpb7MAt7r3nzH+e58yZlhFFa0RcE/B/c35idW71QVzEFl4N8hzqrD6L2ziVgG+xmp7tfy2iDnwEt3Alg/zBBh7gKX5UFHEAp3EeJ3Edb9pru2muedzDiRLAdzxKRbzEISzgApZwtLBnHed6AcfaKvXFWj5gBocrHPhcLL4bxcU8oT4sPt7HGd/E1SrF0/iG3zWJQ/0TLHYB/4VnWEFYvR9FxcfwAntYTsryAqJJruESpmrAr1P3P8RO2boiOJonwFFAxHvcSAXEvybg19Rod/CuyY38jOcSfLaw8QviPYZJHj/TlYrODivrjqhjb1lz5crLio+rE8PkMXab1DVZXfxfBv+ENdxNI7QfXmlz5YnC9rAvZnRcoU3E1BpI9HqPBwKNJGMDDnsvD0xWZ6L7+Nj+lCs+k85yGOwYt8/HDjwMpaU5x6a5JoqH5sBfXZNFHxeyvKAAAAAASUVORK5CYII="/></box-icon><p>${informacionVuelo[0]["registro"].PaisSalida} a ${informacionVuelo[0]["registro"].PaisLlegada}</p></li>
            <li class="vuelo_fecha">
                <p class="fecha_ida">${informacionVuelo[0]["registro"].FechaDisponible}</p>
                <p class="clase_ida">${informacionVuelo[0]["InformacionPago"].Clase}</p>
            </li>
            <li class="vuelo_hora">
                <p class="hora_salida"><span>Hora Salida: </span>${informacionVuelo[0]["registro"].HoraSalida}</p>
                <p class="hora_llegada"><span>Hora Llegada: </span>${informacionVuelo[0]["registro"].HoraLlegada}</p>
            </li>
        </ul>
        `;

        //vamos a desplegar el formulario para que el usuario pueda pagar... (simular que va a realizar un pago con ayuda de su cedula)
        let pagoTotal = document.querySelector(".informacion_pagoFinal");
        pagoTotal.innerHTML = ` <p>Total a pagar <span>USD ${informacionVuelo[0]["registro"].PrecioVuelo}</span></p>`;

        //el formulario para realizar el pago...
        let formulario = document.querySelector(".formulario_final");
        formulario.style.display='block';

        
        recolectarInformacionPasajeros();

        //la funcion se encargara de gestionar y verificar cuando el usuario le de click al boton de cancelar.
        //En base al boton de "pagar vuelo" como se trata de un <form> el "action" va hacer que me redireccione automaticamente
        //a la direccion que le estoy dando... por eso, pienso que no voy a leer el boton de "pagar vuelo"... en el backend
        //es mas probable que me sirva y pueda obtener los datos para poder hacer una verficacion y validacion respectiva.
        let botonCancelar = document.querySelector(".formulario_informacion .informacion_facturacion #cancelar");
        botonCancelar.addEventListener("click", (e) => {
            e.preventDefault();
            window.location.href="/ReservaDeVuelos";
        });

        IncluirFechas(); //es un campo simulado.

        //vamos a incluir un evento para nuestro boton checkbox y asi poder verificar si debemos introducir los datos de nuestro primer
        //cliente o que el usuario pueda introducir sus propios datos. 
        CargarDatos();

        ValidarCamposFormularioPago();
        GenerarPagoBoton();
    }
}


let guardarDatos = [];
//esta funcion esta relacionado con la seccion "DATOS DE FACTURACION" de nuestro formulario final.
//el proposito era escuchar por un checkbox... si existe el visto cargamos datos obtenidos automaticamente
//caso contrario los campos quedan en blanco para que el cliente introduzca sus propios datos. 
function CargarDatos(){
    const botonCheckbox = document.querySelector("input[type=checkbox]#cajaCheck");
    let cambiar = false;
    
    const validarCheckBox = (e, flag=false) => {
        let radio = "";
        
        if(flag){
            radio = e;
        }else{
            radio = e.target;
        }
            
        const nombreCampo = document.getElementById('nombre');
        const emailCampo = document.getElementById('correo');

        if(!radio.checked){
            nombreCampo.disabled = false;
            emailCampo.disabled = false;
            
            nombreCampo.classList.remove("bloquear-estilo");
            emailCampo.classList.remove("bloquear-estilo");
            
            nombreCampo.value = "";
            emailCampo.value = "";
            
        }else{
            if(cambiar){
                nombreCampo.disabled = true;
                emailCampo.disabled = true;
                
                nombreCampo.classList.add("bloquear-estilo");
                emailCampo.classList.add("bloquear-estilo");
                        
                nombreCampo.value = guardarDatos[0].nombreTitular;
                emailCampo.value = guardarDatos[0].correo;

            }else{            
                guardarDatos.push({"nombreTitular" : nombreCampo.value, "correo" : emailCampo.value});
                cambiar = true;
            }
        }

        ValidarCamposFormularioPago();
    };
    
    botonCheckbox.addEventListener("input", validarCheckBox);
    validarCheckBox(botonCheckbox, true);
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
function GenerarPagoBoton(){
    const botonPago = document.getElementById('pagar');
    botonPago.addEventListener("click", (e) => {
      
        if(!formularioFinal.NumeroCedula || !formularioFinal.FechaVencimiento || !formularioFinal.NombreTitular || !formularioFinal.CorreoElectronico){
            e.preventDefault();
        }else{
            
            EnviarFormulario(informacionVuelo);
        }
    });
}


})();
