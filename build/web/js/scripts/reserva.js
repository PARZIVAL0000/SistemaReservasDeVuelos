document.addEventListener("DOMContentLoaded", () => {
    App();
});

function App(){
    CopiaEstados();
    ValidacionCamposFormulario();
    VerificarBotonEnviar();
}

let copiaEstados = [];

//captura lo que escribe la persona.
let entrada = [];
//captura lo que escribe la persona.

//!!! no alterar objeto registro.
let registro = {
    "nombre" : [],
    "apellido" : [],
    "cedula" : [],
    "tipoPasajero" : [],
    "email" : [],
    "celular" : [],
    "genero" : []
};

const form_reserva = {
    "desde" : false,
    "a" : false,
    "estado_desde" : false,
    "estado_a" : false,
    "fecha_ida" : false,
    "numero_pasajeros" : false,
    "vueloIda" : {
        "estado" : false
    },
    "vueloIdaRegreso" : {
        "estado" : false,
        "fecha_vuelta" : false
    }
};

const expresiones = {
    nombre: /^[a-zA-ZÀ-ÿ\s]{1,40}$/, // Letras, numeros, guion y guion_bajo
    apellido: /^[a-zA-ZÀ-ÿ\s]{1,40}$/, // Letras y espacios, pueden llevar acentos.
    cedula : /^[0-9]{10,10}$/,
    correo: /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/,
    celular: /^[0-9]{10,10}$/ // 7 a 14 numeros.
};

const formPasajero = {
    '1' : [false, false, false, false],
    '2' : [false, false, false, false],
    '3' : [false, false, false, false],
    '4' : [false, false, false, false]
};

const formPasajero2 = {
    '1' : [false, false],
    '2' : [false, false],
    '3' : [false, false],
    '4' : [false, false]
};

function sacarId(e, flag){
    let nodeOption = "";
    if(flag){
        nodeOption = e.querySelectorAll("option");
    }else{
       nodeOption = e.target.querySelectorAll('option');
    }
    
    let identificador = "";
    for(let i = 0; i < nodeOption.length; i++){
        if(nodeOption[i].selected){
            identificador=nodeOption[i].id;
            break;
        }
    }
    
    return identificador;
}

function CopiaEstados(){
    const options = document.querySelectorAll('#ciudad_desde > option');
    const options2 = document.querySelectorAll('#ciudad_a > option');
   
    copiaEstados = [...options, ...options2];
}


//Introducimos los estados filtrados... en pocas solamente mostramos los estados que tengan relacion con el pais seleccionado.
function IntroducirEstados(id,tipo){ 
    if(tipo === "to"){
        agregarNodos(document.getElementById('ciudad_a'), id, "Miestado--a");
    }else if(tipo === "from"){
        agregarNodos(document.getElementById('ciudad_desde'), id, "Miestado--desde");
    }
}


function agregarNodos(contenedor, id, clase){
    contenedor.innerHTML = '';
    let copia = [];
    for(let i = 0; i < copiaEstados.length; i++){
        
        if(clase === copiaEstados[i].classList[0]){
            
            if(parseInt(copiaEstados[i].id) === parseInt(id)){
                contenedor.options.add(copiaEstados[i]);
            }
        }
        
    }
    
    
}


function ValidacionCamposFormulario(){
    const selects = document.querySelectorAll("select");
    const inputs = document.querySelectorAll("input");
    verificarTipoVuelo(document.getElementsByClassName("TipoVuelos")[0].value);
    PermitirCampo(document.getElementById('return')); // -> esta funcion hara que el campo "Fecha Vuelta" se muestre o se oculte en base al tipo de vuelo.
    
    const validarSelect = (e, flag=false) => {
        let name = "";
        let value = "";
        let id = "";
        
        if(flag){
            name = e.name;
            value = e.value;
            id = sacarId(e,flag);
        }else{
            name = e.target.name;
            value = e.target.value;
            id = sacarId(e,flag);
        }
       
        switch(name){
            case "from":
                if(value.trim() !== ''){
                    form_reserva.desde = true;
                    IntroducirEstados(id,"from");
                    mostrarAlerta(false, 'mensaje_desde', 'from');
                }else{
                    form_reserva.desde = false;
                    mostrarAlerta(true, 'mensaje_desde', 'from');
                }
                break;
                
            case "to":
                if(value.trim() !== ''){
                    form_reserva.a = true;
                    IntroducirEstados(id, "to");
                    mostrarAlerta(false, 'mensaje_a', 'to');
                }else{
                    form_reserva.a = false;
                    mostrarAlerta(true, 'mensaje_a', 'to');
                }
                break;
                
            case "ciudad_from":
                if(value.trim() !== ''){
                    form_reserva.estado_desde = true;
                    mostrarAlerta(false, 'mensaje_estadoDesde', 'ciudad_desde');
                }else{
                    form_reserva.estado_desde = false;
                    mostrarAlerta(true, 'mensaje_estadoDesde', 'ciudad_desde');
                }
                break;
                
            case "ciudad_to":
                if(value.trim() !== ''){
                    form_reserva.estado_a = true;
                    mostrarAlerta(false, 'mensaje_estadoA', 'ciudad_a');
                }else{
                    form_reserva.estado_a = false;
                    mostrarAlerta(true, 'mensaje_estadoA', 'ciudad_a');
                }
                break;
                
            default:
                break;
        }
    };
    
    const validarInput = (e, flag=false) => {
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
            case "deparure":
                if(value.trim() !== ''){
                    form_reserva.fecha_ida = true;
                    mostrarAlerta(false, 'mensaje_fechaIda', 'deparure');
                }else{
                    form_reserva.fecha_ida = false;
                    mostrarAlerta(true, 'mensaje_fechaIda', 'deparure');
                }
                break;
                
            case "return":
                
                if(value.trim() !== ''){
                    form_reserva.vueloIdaRegreso.fecha_vuelta = true;
                    mostrarAlerta(false, 'mensaje_fechaRetorno', 'return');
                }else{
                    form_reserva.vueloIdaRegreso.fecha_vuelta = false;
                    mostrarAlerta(true, 'mensaje_fechaRetorno', 'return');
                }
                
                break;
                
            case "pasajeros":
                if(value.trim() !== ''){
                    if(parseInt(value) >= 1 && parseInt(value) <= 4){
                        form_reserva.numero_pasajeros = true;
                        mostrarAlerta(false, 'mensaje_pasajeros', 'Pasajeros');
                    }else{
                        form_reserva.numero_pasajeros = false;
                        mostrarAlerta(true, 'mensaje_pasajeros', 'Pasajeros');
                        
                        if(!flag){
                            e.target.value = '4';
                            value = e.target.value;
                        }
                    }
                    
                    //insertar pequeños formularios por cada pasajero.
                    FormularioOtrosPasajeros(value);
                }else{
                    form_reserva.numero_pasajeros = false;
                    mostrarAlerta(true, 'mensaje_pasajeros', 'Pasajeros');
                }
                break;
                
            default:
                break;
        }
    };
    
    selects.forEach(select => {
        select.addEventListener("input", validarSelect);
        
        validarSelect(select, true);
    });
    
    inputs.forEach(input => {
        input.addEventListener("keyup", validarInput);
        input.addEventListener("blur", validarInput);
        input.addEventListener("input", validarInput);
        
        validarInput(input, true);
    });
}



function ValidacionFormularioCliente(nFormularios){
    const inputs = document.querySelectorAll(".seccion_pasajeros .datos_personales input");
    const selects = document.querySelectorAll(".seccion_pasajeros .datos_personales select");
    
    const validarInputs = (e, flag = false) => {
        let name = "";
        let value = "";
        
        if(flag){
            name = e.name;
            value = e.value;
        }else{
            name = e.target.name;
            value = e.target.value;
        }
        
        for(let i = 0; i < nFormularios; i++){
            
            switch(name){
                case `Nombres-${i+1}`:
                    if(value.trim() !== ''){
                        if(expresiones.nombre.test(value)){
                            
                            formPasajero[`${i+1}`][0] = true;
                            
                            guardarInformacion(i,value, "Nombres");
                            
                            mostrarAlerta(false, `nombreCliente-${i+1}`, `Nombres-${i+1}`);
                        }else{
                            formPasajero[`${i+1}`][0] = false;
                            mostrarAlerta(true, `nombreCliente-${i+1}`, `Nombres-${i+1}`);
                        }
                    }else{
                        formPasajero[`${i+1}`][0] = false;
                        mostrarAlerta(true, `nombreCliente-${i+1}`, `Nombres-${i+1}`);
                    }
                    break;
                    
                case `Apellidos-${i+1}`:
                    if(value.trim() !== ''){
                        if(expresiones.apellido.test(value)){
                            
                            formPasajero[`${i+1}`][1] = true;
                            
                            guardarInformacion(i,value,"Apellidos");
                            
                            mostrarAlerta(false, `apellidoCliente-${i+1}`, `Apellidos-${i+1}`);
                        }else{
                            formPasajero[`${i+1}`][1] = false;
                            mostrarAlerta(true, `apellidoCliente-${i+1}`, `Apellidos-${i+1}`);
                        }
                    }else{
                        formPasajero[`${i+1}`][1] = false;
                        mostrarAlerta(true, `apellidoCliente-${i+1}`, `Apellidos-${i+1}`);
                    }
                    break;
                    
                case `Cedula-${i+1}`:
                    if(value.trim() !== ''){
                        if(expresiones.cedula.test(value)){
                            const resp = comprobarCedula(value);
                            resp.then(respuesta => {
                                if(respuesta){
                                    
                                    formPasajero[`${i+1}`][2] = true;
                                    guardarInformacion(i,value, "Cedula");
                                    mostrarAlerta(false, `cedulaCliente-${i+1}`, `Cedula-${i+1}`);
                                    
                                }else{
                                    formPasajero[`${i+1}`][2] = false;
                                    mostrarAlerta(true, `cedulaCliente-${i+1}`, `Cedula-${i+1}`)
                                }
                            });
                            
                        }else{
                            formPasajero[`${i+1}`][2] = false;
                            mostrarAlerta(true, `cedulaCliente-${i+1}`, `Cedula-${i+1}`);
                        }
                    }else{
                        formPasajero[`${i+1}`][2] = false;
                        mostrarAlerta(true, `cedulaCliente-${i+1}`, `Cedula-${i+1}`);
                    }
                    break;
                    
                default:
                    break;
            }
        }
       
    };
    
    const validarSelects = (e, flag = false) => {
        let name = "";
        let value = "";
        
        if(flag){
            name = e.name;
            value = e.value;
        }else{
            name = e.target.name;
            value = e.target.value;
        }
        
        for(let i = 0; i < nFormularios; i++){
            switch(name){
                case `tipo_pasajero-${i+1}`:
                    if(value.trim() !== ''){ 
                        formPasajero[`${i+1}`][3] = true;
                        
                        guardarInformacion(i,value, "tipoPasajero");
                        
                        EscribirHTML2(i+1, value);
                        rellenarCampos(nFormularios);
                        if(value === "Hombre" || value === "Mujer"){
                            validacionOtrosCampos(i+1);
                        }
                        
                        mostrarAlerta(false, `tipoCliente-${i+1}`, `tipoPasajero-${i+1}`);
                        
                    }else{
                        formPasajero[`${i+1}`][3] = false;
                        mostrarAlerta(true, `tipoCliente-${i+1}`, `tipoPasajero-${i+1}`);
                    }
                    break;
                        
                default:
                    break;
            }
        }
        
    };
    
    inputs.forEach(input => {
        input.addEventListener("keyup", validarInputs);
        input.addEventListener("blur", validarInputs);
        
        validarInputs(input, true);
    });
    
    selects.forEach(select => {
        select.addEventListener("blur", validarSelects);
        select.addEventListener("input", validarSelects);
        
        validarSelects(select, true);
    });
}

async function comprobarCedula(numeroCedula){
    try{
        const url = `http://127.0.0.1:8888/cedula/${numeroCedula}`;  
        const conexion = await fetch(url, {
            method: 'POST' 
        });
        const respuesta = await conexion.text();

        return respuesta.trim() === "Correcto";
    }catch(error){
        //control de error....
    }
}


function validacionOtrosCampos(i){
    const inputs = document.querySelectorAll("input");
    
    const validarCampo = (e, flag=false) => {
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
            case `Email-${i}`:
                if(value.trim() !== ""){
                    
                    if(expresiones.correo.test(value)){
                        guardarInformacion(i-1,value, "Email");
                        mostrarAlerta(false, `emailCliente-${i}`, `Eml-${i}`);
                        //cambiar a true en la matriz de la persona.
                        formPasajero2[`${i}`][0] = true; 
                    }else{
                        mostrarAlerta(true, `emailCliente-${i}`, `Eml-${i}`);
                        formPasajero2[`${i}`][0] = false; 
                    }
                    
                }else{
                    mostrarAlerta(true, `emailCliente-${i}`, `Eml-${i}`);
                    formPasajero2[`${i}`][0] = false;
                }
                break;
                
            case `Celular-${i}`:
                if(value.trim() !== ""){
                    
                    if(expresiones.celular.test(value)){
                        guardarInformacion(i-1,value, "Celular");
                        mostrarAlerta(false, `celularCliente-${i}`, `Cel-${i}`);
                        formPasajero2[`${i}`][1] = true;
                    }else{
                        mostrarAlerta(true, `celularCliente-${i}`, `Cel-${i}`);
                        formPasajero2[`${i}`][1] = false;
                    }
                    
                }else{
                    mostrarAlerta(true, `celularCliente-${i}`, `Cel-${i}`);
                    formPasajero2[`${i}`][1] = false;
                }
                break;
                
            default:
                break;
        }
    };
    
    inputs.forEach(input => {
        input.addEventListener("keyup", validarCampo);
        input.addEventListener("blur", validarCampo);
        
        validarCampo(input, true);
    });
}


function guardarInformacion(i,value, key){
    const info = {
        "id" : i+1,
        key : value,
        "estado" : false
    };
    
    if(entrada.length !== 0){
        for(let j of entrada){
            if(j.id === i+1){
                j[`${key}`] = value;
                return;
            }
        }
    }
    
    entrada.push(info);
}

function mostrarAlerta(estado, mensaje, campo){
    const m = document.getElementById(`${mensaje}`);
    const i = document.getElementById(`${campo}`);
    if(estado){
        m.classList.add("mostrar");
        i.classList.add("alerta_campo");
        m.classList.remove("ocultar");
    }else{
        m.classList.add("ocultar");
        m.classList.remove("mostrar");
        i.classList.remove("alerta_campo");
    }
}

function verificarTipoVuelo(tipoVuelo){
    if(tipoVuelo === "Ida-Regreso"){
        form_reserva.vueloIdaRegreso.estado = true;
    }else if(tipoVuelo === "Ida"){
        form_reserva.vueloIda.estado = true;
    }
}

/*
 * Esta funcion lo que hace es rellenar nuestras entradas, para que no se pierda 
 * lo que el usuario teclea.
 */
function rellenarCampos(nFormularios){
    if(entrada.length !== 0){
        for(let i = 0; i < entrada.length; i++){
            if(parseInt(nFormularios) === 1 && entrada.length === 1){
                entrada[0].id = 1;
                entrada[0].estado = true;
            }else if(parseInt(nFormularios) === 2 && entrada.length === 2){
                let cambiar = false;
                for(let i = 0; i < entrada.length; i++){
                    
                    if(entrada[i].id === 1){
                        cambiar = true;
                    }
                    
                    if(!cambiar){
                        if(entrada[i].id === 2 && !entrada[i].estado){
                            if(!cambiar){
                                entrada[i].id = 1;
                                entrada[i].estado = true;
                            }
                        }else if(entrada[i].id === 3  && !entrada[i].estado){
                            entrada[i].id = 2;
                            entrada[i].estado = true;
                        }
                    }
                }
            }
            
            introducirValue(i);
        }
    }
}

/* esta funcion se encarga de rellenar los campos Email y Celular (si es que existen datos para llenar) */
function rellenarUltimosCampos(i){
    if(entrada.length !== 0){
        //campos extras.
        try{
            const Email = entrada[i].Email;
            const Celular = entrada[i].Celular;

            if(Email !== undefined){
               const campo_email = document.getElementById(`Eml-${parseInt(entrada[i].id)}`);
               if(campo_email !== null){
                   campo_email.value=Email;
               }
            }

            if(Celular !== undefined){
               const campo_celular = document.getElementById(`Cel-${parseInt(entrada[i].id)}`);
               if(campo_celular !== null){
                   campo_celular.value=Celular.toString();
               }
            }
        }catch(TypeError){
            //quitamos nuestro error
        }
    }
}

function introducirValue(i){
    try{
        const id = parseInt(entrada[i].id);
        const nombres = entrada[i].Nombres;
        const apellidos = entrada[i].Apellidos;
        const cedula = entrada[i].Cedula;
        const tipoPasajero = entrada[i].tipoPasajero;

        if(nombres !== undefined && nombres !== null){
            document.getElementById(`Nombres-${id}`).value=nombres;
        }

        if(apellidos !== undefined && apellidos !== null){
            document.getElementById(`Apellidos-${id}`).value=apellidos;
        }

        if(cedula !== undefined && cedula !== null){
            document.getElementById(`Cedula-${id}`).value=cedula;
        }

        if(tipoPasajero !== undefined && tipoPasajero !== null){
            document.getElementById(`tipoPasajero-${id}`).value=tipoPasajero;
        }


        rellenarUltimosCampos(i);
    }catch(TypeError){
        //ayudamos a evitar nuestro error...
    }
}


//esta funcion hara que el campo "Fecha Vuelta" se muestre o se oculte en base al tipo de vuelo.
/**
 * ValidacionCamposFormulario(campo)
 */
function PermitirCampo(campo){
    if(form_reserva.vueloIda.estado && !form_reserva.vueloIdaRegreso.estado){
        campo.parentNode.parentNode.classList.add('hidden');
        campo.disabled=true;
    } 

}


function Redireccionar(){
    window.location.href='#formulario_reserva';
}


//este codigo de aqui nos permitira desplegar formulario... en base al numero
//que pongamos dentro de nuestro input -> numero de pasajeros
function FormularioOtrosPasajeros(nFormularios){
    //seleccionaremos nuestro campos de numeros de pasajeros...
    EscribirHTML(nFormularios);
    //rellena con los datos guardados en cada uno de los minis formularios.
    rellenarCampos(nFormularios);
    ValidacionFormularioCliente(nFormularios);
    
    
}


function limpiarFormCliente(){
    //esto limpia nuestro contenido anterior... reemplazandolo por uno nuevo.
    if(document.querySelectorAll(".section_psj").length !== 0){
        let nodo = document.getElementsByClassName("seccion_pasajeros")[0];
        nodo.innerHTML = '';
    }
}


function EscribirHTML(numero_pasajeros = 0){
    limpiarFormCliente();
    
    for(let i = 0; i < numero_pasajeros; i++){
        let seccion2 = document.createElement('DIV');
        seccion2.classList.add("section_psj");
        seccion2.innerHTML = `
            <input type="hidden" value="${i+1}" name="identificador-${i+1}"/>
            <fieldset>
                <legend>Datos Del Pasajero ${i+1}<legend>
                <div class="datos_personales" id="contenedor-${i+1}">
                    <div class="campo-grupo input-group-lg" id="Nombres">
                        <div class="campo">
                            <p class="input-group-text datos_personales--campo" id="inputGroup-sizing-lg">Nombres:</p>
                            <input type="text" class="${i+1} form-control  ver" id="Nombres-${i+1}" name="Nombres-${i+1}" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-lg" placeholder="Introduce tus dos nombres">
                        </div>
                        <div class="mensaje ocultar" id="nombreCliente-${i+1}">
                            <p>inserta tus nombres sin números o carácteres especiales.</p>
                        </div>
                    </div>
                    <div class="campo-grupo input-group-lg" id="Apellidos">
                        <div class="campo">
                            <p class="input-group-text datos_personales--campo" id="inputGroup-sizing-lg">Apellidos:</p>
                            <input type="text" class="${i+1} form-control  ver" id="Apellidos-${i+1}" name="Apellidos-${i+1}" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-lg" placeholder="Introduce tus dos apellidos">
                        </div>
                        <div class="mensaje ocultar" id="apellidoCliente-${i+1}">
                            <p>inserta tus apellidos sin números o carácteres especiales.</p>
                        </div>
                    </div>
                    <div class="campo-grupo input-group-lg" id="Cedula">
                        <div class="campo">
                            <p class="input-group-text datos_personales--campo" id="inputGroup-sizing-lg">Cedula:</p>
                            <input type="text" class="${i+1} form-control ver w-50" id="Cedula-${i+1}" name="Cedula-${i+1}" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-lg" placeholder="Introduce tu número de cédula">
                        </div>
                        <div class="mensaje ocultar" id="cedulaCliente-${i+1}">
                            <p>inserta un número de cédula válida</p>
                        </div>
                    </div>
                    <div class="campo-grupo input-group-lg" id="Tipo">
                        <div class="campo">
                            <p class="input-group-text datos_personales--campo" id="inputGroup-sizing-lg">Tipo:</p>
                            <select id="tipoPasajero-${i+1}" class="${i+1} form-select tipoPasajero tipo_pasajero-${i+1} ver" aria-label="Default select example" name="tipo_pasajero-${i+1}" placeholder="Introduce el tipo de pasajero">
                                <option value="" disabled selected>Selecciona el tipo de pasajero</option>
                                <option value="nino">Ni&ntilde;o</option>
                                <option value="Hombre">Hombre</option>
                                <option value="Mujer">Mujer</option>
                            </select>
                        </div>
                        <div class="mensaje ocultar" id="tipoCliente-${i+1}">
                            <p>Debes insertar el tipo de pasajero</p>
                        </div>
                    </div>
                </div>
            </fieldset>
        `;    
        let seccion = document.querySelector(".seccion_pasajeros");

        seccion.appendChild(seccion2);
    }

}


//escribe mas campos en cada uno de los mini formularios, cuando y solo cuando el usuario eligue un "Tipo" de persona.... 
function EscribirHTML2(i, value){
    const validate = document.querySelectorAll(`#Email-${i}`);
    const validate2 = document.querySelectorAll(`#Celular-${i}`);
    
    const contenedor = document.getElementById(`contenedor-${i}`);

    const contenedorEmail = document.createElement("DIV");
    contenedorEmail.classList.add("campo-grupo", "input-group-lg");
    contenedorEmail.id = `Email-${i}`;

    const contenedorCelular = document.createElement("DIV");
    contenedorCelular.classList.add("campo-grupo", "input-group-lg");
    contenedorCelular.id = `Celular-${i}`;

    contenedorEmail.innerHTML = `
        <div class="campo">
            <p class="input-group-text datos_personales--campo" id="inputGroup-sizing-lg">Email:</p>
            <input type="email" class="${i} form-control ver w-50" id="Eml-${i}" name="Email-${i}" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-lg" placeholder="Introduce tu correo electrónico">
        </div>
        <div class="mensaje ocultar" id="emailCliente-${i}">
            <p>inserta un email válido.</p>
        </div>
    `;
    
    contenedorCelular.innerHTML = `
         <div class="campo">
            <p class="input-group-text datos_personales--campo" id="inputGroup-sizing-lg">Celular:</p>
            <input type="tel" class="${i} form-control ver w-50" id="Cel-${i}" name="Celular-${i}" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-lg" placeholder="Introduce un número de celular válido">
        </div>
        <div class="mensaje ocultar" id="celularCliente-${i}">
            <p>inserta un número de celular.</p>
        </div>
    `;
    
    
    if(validate.length === 0 && validate2.length === 0){
        if(value === "Hombre" || value === "Mujer"){
            contenedor.appendChild(contenedorEmail);
            contenedor.appendChild(contenedorCelular);
        }
    }
    
       
    if(value === "nino"){
        if(validate.length !== 0 && validate2.length !== 0){     
            document.querySelector(`#Email-${i}`).remove();
            document.querySelector(`#Celular-${i}`).remove();
        }
    }
}


function VerificarBotonEnviar(){
    //cuando el usuario de click en enviar... lo que haremos a continuacion 
    //es generar una verificacion correspondiente.
    let botonEnviar = document.querySelector("#boton_formulario");
   
    botonEnviar.addEventListener("click", (e) => {
        
        if(form_reserva.vueloIda.estado){
            if(!form_reserva.desde || !form_reserva.a || !form_reserva.estado_desde || !form_reserva.estado_a || !form_reserva.fecha_ida || !form_reserva.numero_pasajeros){
                e.preventDefault();
            }else{
                
                const respuesta = verificarInputsPasajeros(parseInt(document.getElementById("Pasajeros").value));
                
                if(!respuesta){
                    e.preventDefault();
                }else{
                    //la ultima validacion aqui....
                    const respuesta2 = verificarCamposFinal(parseInt(document.getElementById("Pasajeros").value));
                    console.log(respuesta2);
                    if(!respuesta2){
                        e.preventDefault();
                    }
                }
            }
        }else if(form_reserva.vueloIdaRegreso.estado){
            if(!form_reserva.desde || !form_reserva.a || !form_reserva.estado_desde || !form_reserva.estado_a || !form_reserva.fecha_ida || !form_reserva.vueloIdaRegreso.fecha_vuelta || !form_reserva.numero_pasajeros){
                e.preventDefault();
            }else{
                const respuesta = verificarInputsPasajeros(parseInt(document.getElementById("Pasajeros").value));
                if(!respuesta){
                    e.preventDefault();
                }else{
                    //la ultima validacion aqui...
                    const respuesta2 = verificarCamposFinal(parseInt(document.getElementById("Pasajeros").value));
                    
                    if(!respuesta2){
                        e.preventDefault();
                    }
                }
            }
        }
      
    });
}

function verificarCamposFinal(nPasajeros){
    
    for(let i = 1; i < nPasajeros+1; i++){
        const tipoPasajero = entrada[i-1].tipoPasajero;
        
        if(tipoPasajero !== "nino"){
            const email = formPasajero2[i.toString()][0];
            const celular = formPasajero2[i.toString()][1];

            if(!email || !celular){
                return false;
            }
        }
    }
    
    return true;
}

function verificarInputsPasajeros(nPasajeros){
    for(let i = 1; i < nPasajeros+1; i++){
        const nombres = formPasajero[i.toString()][0];
        const apellidos = formPasajero[i.toString()][1];
        const cedula = formPasajero[i.toString()][2];
        const tipo = formPasajero[i.toString()][3];

        if(!nombres || !apellidos || !cedula || !tipo){
            
            return false;
        }
    }
    
    return true;
}

