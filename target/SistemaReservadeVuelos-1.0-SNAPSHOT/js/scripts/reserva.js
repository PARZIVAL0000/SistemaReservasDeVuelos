document.addEventListener("DOMContentLoaded", () => {
    App();
});

function App(){
    Redireccionar();
    FormularioOtrosPasajeros(); // insertarHTML
    CompletarEntradas(); //completa los 4 primeros campos.
    CopiarCiudades();
    VerificarCamposFechas();
    VerificarCamposFechas2();
    VerificarBotonEnviar();
    ObservarEntradas();
    InsertarAlertas();
    //esta funcion nos permitira verificar por las cedulas incorrectas...
    VerificarCedula();
    ResetearFormulario();
}

let entrada = [];

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


function Redireccionar(){
    window.location.href='#formulario_reserva';
}

//con esta funcion nos encargaremos de resetear la mayoria de nuestros datos...
function ResetearFormulario(){
    let botonResetear = document.querySelector("#boton_resetear");
    botonResetear.addEventListener("click", (e) => {
        //lo primero que tenemos que hacer es eliminar los campos dentro de nuestro SessionStorage()
        sessionStorage.removeItem("tipoUsuario");
        sessionStorage.removeItem("cedula");
        sessionStorage.removeItem("celular");
        sessionStorage.removeItem("genero");
        sessionStorage.removeItem("email");
        sessionStorage.removeItem("apellido");
        sessionStorage.removeItem("nombre");
        
        //despues debemos resetear todo nuestro formulario...
        window.location.reload();
        
    });
}

function VerificarCedula(){
    let identificador = document.querySelector(".errorCampo");
    if(identificador !== null){
        identificador = identificador.classList;
        identificador.forEach(valor => {
            //en nuestro html solo es aceptable hasta 4 registros....
            switch(valor){
                case "1":
                    document.querySelector("#Cedula-1").classList.add("error");
                    document.querySelector("#Cedula-1").previousElementSibling.classList.add("error");
                    document.querySelector("#Cedula-1").nextElementSibling.nextElementSibling.style.display="block";
                    break;
                case "2":
                    document.querySelector("#Cedula-2").classList.add("error");
                    document.querySelector("#Cedula-2").previousElementSibling.classList.add("error");
                    document.querySelector("#Cedula-2").nextElementSibling.nextElementSibling.style.display="block";
                    break;
                case "3":
                    document.querySelector("#Cedula-3").classList.add("error");
                    document.querySelector("#Cedula-3").previousElementSibling.classList.add("error");
                    document.querySelector("#Cedula-3").nextElementSibling.nextElementSibling.style.display="block";
                    break;
                case "4":
                    document.querySelector("#Cedula-4").classList.add("error");
                    document.querySelector("#Cedula-4").previousElementSibling.classList.add("error");
                    document.querySelector("#Cedula-4").nextElementSibling.nextElementSibling.style.display="block";
                    break;
                default:
                    break;
            }
        });
    }
}

function ObservarEntradas(){
    let entradas = document.querySelectorAll(".ver");
    
    entradas.forEach(entrada => {
        entrada.addEventListener("input", (e) => {
            //vamos escuchando por cada vez que el usuario teclea.
            let tipoCampo = e.target.name.split("-")[0];
            let idCampo = parseInt(e.target.classList[0]);
            /*
             * Puede existir muchos campos por el tipoCampo... por ejemplo Nombres... puede existir muchos campos de este tipo...
             */
            if(tipoCampo === "Nombres"){
                registro["nombre"][idCampo-1] = e.target.value;
            }else if(tipoCampo === "Apellidos"){
                registro["apellido"][idCampo-1] = e.target.value;
            }else if(tipoCampo === "Cedula"){
                registro["cedula"][idCampo-1] = e.target.value;
            }else if(tipoCampo === "tipo_pasajero"){
                //los errores que se generan terminan evitando que se pueda ejecutar esta parte de nuestro codigo...
                //tener mucho cuidado para cuestiones futuras... debido a que posiblemente no se sepa porque no
                //se introducen nuestros datos en el objeto... 
                
                console.log('ejecutando dentro de este codigo...');
                
                if(e.target.value === "nino"){
                    registro["celular"][idCampo-1] = "nino";
                    registro["email"][idCampo-1] = "nino";
                }
                
                registro["tipoPasajero"][idCampo-1] = e.target.value;
                
            }else if(tipoCampo === "Email"){
                registro["email"][idCampo-1] = e.target.value;
            }else if(tipoCampo === "Celular"){
                registro["celular"][idCampo-1] = e.target.value;
            }else if(tipoCampo === "genero"){
                registro["genero"][idCampo-1] = e.target.value;
            }
        });
    });
}

//esta funcion nos permitira completar nuestros campos...
function CompletarEntradas(){
   
    registro["nombre"] = (sessionStorage.getItem("nombre") !== "" && sessionStorage.getItem("nombre") !== null) ? sessionStorage.getItem("nombre").split(",") : [];
    registro["apellido"] = (sessionStorage.getItem("apellido") !== "" && sessionStorage.getItem("apellido") !== null) ? sessionStorage.getItem("apellido").split(",") : [];
    registro["cedula"] = (sessionStorage.getItem("cedula") !== "" && sessionStorage.getItem("cedula") !== null) ? sessionStorage.getItem("cedula").split(",") : [];
    registro["tipoPasajero"] = (sessionStorage.getItem("tipoUsuario") !== "" && sessionStorage.getItem("tipoUsuario") !== null) ? sessionStorage.getItem("tipoUsuario").split(",") : [];

    if(registro["nombre"].length !== 0){
        let indice = 1;
        for(const valor of registro["nombre"]){
            document.querySelector(`#Nombres-${indice}`).value=valor;
            indice+=1;
        }
    }
    
    if(registro["apellido"].length !== 0){
        let indice = 1;
        for(const valor of registro["apellido"]){
            document.querySelector(`#Apellidos-${indice}`).value=valor;
            indice+=1;
        }
    }
    
    if(registro["cedula"].length !== 0){
        let indice = 1;
        for(const valor of registro["cedula"]){
            document.querySelector(`#Cedula-${indice}`).value=valor;
            indice+=1;
        }
    }
    
    if(registro["tipoPasajero"].length !== 0){
        let indice = 1;
        for(const valor of registro["tipoPasajero"]){
            document.querySelector(`.tipo_pasajero-${indice}`).value=valor;
            indice+=1;
        }
    }
    
    

    TipoPasajeroCampo(); //insertarHTML2
   
}

function CompletarEntradas2(){
    registro["email"] = (sessionStorage.getItem("email") !== "" && sessionStorage.getItem("email") !== null) ? sessionStorage.getItem("email").split(",") : [];
    registro["celular"] = (sessionStorage.getItem("celular") !== "" && sessionStorage.getItem("celular") !== null) ? sessionStorage.getItem("celular").split(",") : [];
    registro["genero"] = (sessionStorage.getItem("genero") !== "" && sessionStorage.getItem("genero") !== null) ? sessionStorage.getItem("genero").split(",") : [];


    if(registro["email"].length !== 0){
        let indice = 1;
        for(const valor of registro["email"]){
            document.querySelector(`#Email-${indice}`).value = valor;
            indice +=1;
        }
    }

    if(registro["celular"].length !== 0){
        let indice = 1;
        for(const valor of registro["celular"]){
            document.querySelector(`#Celular-${indice}`).value = valor;
            indice+=1;
        }
    }
    
    if(registro["genero"].length !== 0){
        let indice = 1;
        for(const valor of registro["genero"]){
            document.querySelector(`.Genero-${indice}`).value = valor;
            indice+=1;
        }
    }
    
}

//este codigo de aqui nos permitira desplegar formulario... en base al numero
//que pongamos dentro de nuestro input -> numero de pasajeros
function FormularioOtrosPasajeros(){
    //seleccionaremos nuestro campos de numeros de pasajeros...
    let campo = document.querySelector("#Pasajeros");
   
    campo.addEventListener("input", (e)=>{
        //esto limpia nuestro contenido anterior... reemplazandolo por uno nuevo.
        if(document.querySelectorAll(".section_psj").length !== 0){
            document.querySelectorAll(".section_psj").forEach(elemento => {
                elemento.remove();
            });
        }
        
        let numero_pasajeros = e.target.value;
        EscribirHTML(numero_pasajeros);
        CompletarEntradas();//esta funcion rellena los 4 primero inputs de la seccion "perfil otros pasajeros" 
        
    });
    
    EscribirHTML(document.querySelector("#Pasajeros").value);
}

//vamos a verificar por los errores....
let error = document.querySelectorAll(".error");

let mensaje = "";
if(error.length !== 0){
    mensaje = "error";
}


function InsertarAlertas(){
    //despues de la escritura procesamos los mensajes....
    //activamos nuestro mensaje de error en cada campo....
    //aqui solamente procesamos cuando existe la clase de error nada mas...
    
    if(error.length !== 0){  
        let campo_error = document.querySelectorAll(".mensaje_error");
        campo_error.forEach(campo => {
            
            if(campo.previousElementSibling.value === ""){
                campo.style.display = "block";
            }else{
                campo.style.display = "none";
                campo.previousElementSibling.classList.remove("error");
                campo.previousElementSibling.previousElementSibling.classList.remove("error");
            }
        });
    }
}

function ValidarCampos(){
    const entradas = document.querySelectorAll(".ver");
    entradas.forEach(entrada => {
        entrada.addEventListener("input", (e) => {
            if(e.target.parentNode.id === "Nombres"){
                const expresion = new RegExp("^([A-Za-zÑñÁáÉéÍíÓóÚú]+[A-Za-zÑñÁáÉéÍíÓóÚú]+)(\s+([[A-Za-zÑñÁáÉéÍíÓóÚú]+))*$");
                let resultado = e.target.value.search(expresion);
                
                console.log(resultado);
            }else if(e.target.parentNode.id === "Apellidos"){
                
            }else if(e.target.parentNode.id === "Cedula"){
                
            }else if(e.target.parentNode.id === "Tipo"){
                
            }
        });
    });
}


function EscribirHTML(numero_pasajeros = 0){
    
    for(let i = 0; i < numero_pasajeros; i++){
        let seccion2 = document.createElement('DIV');
        seccion2.classList.add("section_psj");
        seccion2.innerHTML = `
            <input type="hidden" value="${i+1}" name="identificador-${i+1}"/>
            <fieldset>
                <legend>Datos Del Pasajero ${i+1}<legend>
                <div class="datos_personales" id="contenedor-${i+1}">
                    <div class="input-group input-group-lg" id="Nombres">
                        <span class="input-group-text datos_personales--campo ${mensaje}" style="height: 4.6rem;" id="inputGroup-sizing-lg">Nombres:</span>
                        <input type="text" class="${i+1} form-control ${mensaje} ver" id="Nombres-${i+1}" name="Nombres-${i+1}" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-lg">
                        <i class="formulario__validacion-estado fas fa-times-circle"></i>
                        <p class="formulario__input-error" id="mensaje-${i+1}">El nombre no debe estar vacío, poseer números o contener carácteres especiales.</p>
                    </div>
                    <div class="input-group input-group-lg" id="Apellidos">
                        <span class="input-group-text datos_personales--campo ${mensaje}" style="height: 4.6rem;" id="inputGroup-sizing-lg">Apellidos:</span>
                        <input type="text" class="${i+1} form-control ${mensaje} ver" id="Apellidos-${i+1}" name="Apellidos-${i+1}" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-lg">
                        <i class="formulario__validacion-estado fas fa-times-circle"></i>
                        <p class="formulario__input-error" id="mensaje-${i+1}">El apellido no debe estar vacío, poseer números o contener carácteres especiales.</p>

                    </div>
                    <div class="input-group input-group-lg" id="Cedula">
                        <span class="input-group-text datos_personales--campo ${mensaje}" style="height: 4.6rem;" id="inputGroup-sizing-lg">Cedula:</span>
                        <input type="text" class="${i+1} form-control ${mensaje} ver w-50" id="Cedula-${i+1}" name="Cedula-${i+1}" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-lg">
                        <i class="formulario__validacion-estado fas fa-times-circle"></i>
                        <p class="formulario__input-error" id="mensaje-${i+1}">La cedula debe ser válida y no estar vacía.</p>
                    </div>
                    <div class="input-group input-group-lg" id="Tipo">
                        <span class="input-group-text datos_personales--campo ${mensaje}" style="height: 4.6rem;" id="inputGroup-sizing-lg">Tipo:</span>
                        <select id="${i+1}" class="${i+1} form-select tipoPasajero ${mensaje} tipo_pasajero-${i+1} ver" aria-label="Default select example" name="tipo_pasajero-${i+1}">
                            <option value="" disabled selected>Selecciona el tipo de pasajero</option>
                            <option value="nino">Ni&ntilde;o</option>
                            <option value="Hombre">Hombre</option>
                            <option value="Mujer">Mujer</option>
                        </select>
                        <i class="formulario__validacion-estado fas fa-times-circle"></i>
                        <p class="formulario__input-error" id="mensaje-${i+1}">El campo no debe estar vacio</p>
                    </div>
                </div>
            </fieldset>
        `;    
        let seccion = document.querySelector(".seccion_pasajeros");

        seccion.appendChild(seccion2);
    }
    
    InsertarAlertas();
    ValidarCampos();
   
}

//esta funcion lo que hara es escribirnos el html de los campos adicionales que se necesita para completar
//la informacion de la persona.
function EscribirHTML2(){
    //usamos el querySelectorAll()... porque queremos obtener los tipo de pasajeros de tdoas los registros que pueda existir...
    let campoTipoPasajeros = document.querySelectorAll(".tipoPasajero");
   
    campoTipoPasajeros.forEach(pasajero => {
        
        pasajero.addEventListener("change", (e) => {
            let id = parseInt(e.target.id);
            let tipo = e.target.value;

            let numeroTotalPasajeros = document.querySelector('input[type=number]').value;

            for(let i = 0; i < numeroTotalPasajeros; i++){
                
                if(i+1 === id){
                    //seleccionaremos el contenedor respectivo...
                    let contenedor = document.querySelector(`#contenedor-${id}`);

                    //ajustaremos unos campos adicionales para hombre
                    //generaremos el script.
                    let cnt = document.createElement('DIV');
                    cnt.classList.add("input-group", "input-group-lg", "campo-extra");
                    cnt.innerHTML = `
                        <span class="input-group-text datos_personales--campo ${mensaje}" id="inputGroup-sizing-lg">Email:</span>
                        <input type="email" class="${i+1} form-control ${mensaje} ver w-50" id="Email-${i+1}" name="Email-${i+1}" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-lg">
                        <i class="formulario__validacion-estado fas fa-times-circle"></i>
                        <p class="formulario__input-error" id="mensaje-${i+1}">El email no debe estar vacío y debe tener el @.</p>
                    `;


                    let cnt2 = document.createElement('DIV');
                    cnt2.classList.add("input-group", "input-group-lg", "campo-extra");
                    cnt2.innerHTML = `
                        <span class="input-group-text datos_personales--campo ${mensaje}" id="inputGroup-sizing-lg">Celular:</span>
                        <input type="tel" class="${i+1} form-control ${mensaje} ver" id="Celular-${i+1}" name="Celular-${i+1}" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-lg">
                        <i class="formulario__validacion-estado fas fa-times-circle"></i>
                        <p class="formulario__input-error" id="mensaje-${i+1}">El celular no debe estar vacío y debe tener una longitud de 10.</p>
                    `;

                    let cnt3 = document.createElement('DIV');
                    cnt3.classList.add('input-group', 'input-group-lg', "campo-extra");
                    cnt3.innerHTML = `
                        <span class="input-group-text datos_personales--campo ${mensaje}" id="inputGroup-sizing-lg">G&Eacute;nero:</span>
                        <select id="${i+1}" class="${i+1} form-select tipoPasajero ${mensaje} Genero-${i+1} ver" aria-label="Default select example" name="genero-${i+1}">
                            <option  value="" disabled selected>Selecciona el g&eacute;nero</option>
                            <option value="masculino">Masculino</option>
                            <option value="femenino">Femenino</option>
                            <option value="Otro">Otro</option>
                        </select>
                        <i class="formulario__validacion-estado fas fa-times-circle"></i>
                        <p class="formulario__input-error" id="mensaje-${i+1}">El campo no debe estar vacio</p>
                    `;


                    if(tipo === "Hombre"){
                        //despues de la escritura procesamos los mensajes....

                        //la limpieza del html es correcto... sin embargo, debemos especificar en donde quiero que me 
                        //haga la limpieza del html... caso contrario le da la puta gana de limpiar en otro lado que no es....

                        //activamos nuestro mensaje de error en cada campo....
                        //eliminamos campos anteriores que posiblemente hayan estado incluido.....
                        if(document.querySelectorAll(`#contenedor-${id} .campo-extra`).length !== 0){
                            document.querySelectorAll(`#contenedor-${id} .campo-extra`).forEach(n => {
                                n.remove();
                            });
                        }

                        contenedor.appendChild(cnt);
                        contenedor.appendChild(cnt2);
                        contenedor.appendChild(cnt3);
                    }else if(tipo === "Mujer"){
                        //la puteria de arriba... me hace entender que debo colocar el identificador ...
                        //eso me ayudara a comprender en donde realmente tengo que eliminar los campos. :)


                        //eliminamos campos anteriores que posiblemente hayan estado incluido.....
                        if(document.querySelectorAll(`#contenedor-${id} .campo-extra`).length !== 0){
                            document.querySelectorAll(`#contenedor-${id} .campo-extra`).forEach(n => {
                                n.remove();
                            });
                        }
                        //ajustaremos unos campos adicionales para mujer
                        contenedor.appendChild(cnt);
                        contenedor.appendChild(cnt2);
                        contenedor.appendChild(cnt3);
                    }else if(tipo === "nino"){
                        //eliminamos campos anteriores que posiblemente hayan estado incluido.....
                        if(document.querySelectorAll(`#contenedor-${id} .campo-extra`).length !== 0){
                            document.querySelectorAll(`#contenedor-${id} .campo-extra`).forEach(n => {
                                n.remove();
                            });
                        }
                        //ajustaremos unos campos adicionales para el nino...
                        //en este caso... no creo que el pequeño timmy necesite correo electrónico o celular....
                        //a menos que sea un nativo digital... lo dejaremos por el momento así.
                        contenedor.appendChild(cnt3);
                    }
                }
                
            }
            
            ObservarEntradas();
            InsertarAlertas();

        });//foreach
         
        if(pasajero.value !== ""){
            
            let id = parseInt(pasajero.id);
            let tipo = pasajero.value;

            let numeroTotalPasajeros = document.querySelector('input[type=number]').value;
            
            for(let i = 0; i < numeroTotalPasajeros; i++){
                
                if(i+1 === id){
                    //seleccionaremos el contenedor respectivo...
                    let contenedor = document.querySelector(`#contenedor-${id}`);

                    //ajustaremos unos campos adicionales para hombre
                    //generaremos el script.
                    let cnt = document.createElement('DIV');
                    cnt.classList.add("input-group", "input-group-lg", "campo-extra");
                    cnt.innerHTML = `
                        <span class="input-group-text datos_personales--campo ${mensaje}" id="inputGroup-sizing-lg">Email:</span>
                        <input type="email" class="${i+1} form-control ${mensaje} ver" id="Email-${i+1}" name="Email-${i+1}" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-lg">
                        <div class="mensaje_error">
                            Campo incompleto!
                        </div>
                    `;


                    let cnt2 = document.createElement('DIV');
                    cnt2.classList.add("input-group", "input-group-lg", "campo-extra");
                    cnt2.innerHTML = `
                        <span class="input-group-text datos_personales--campo ${mensaje}" id="inputGroup-sizing-lg">Celular:</span>
                        <input type="tel" class="${i+1} form-control ${mensaje} ver" id="Celular-${i+1}" name="Celular-${i+1}" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-lg">
                        <div class="mensaje_error">
                            Campo incompleto!
                        </div>
                    `;

                    let cnt3 = document.createElement('DIV');
                    cnt3.classList.add('input-group', 'input-group-lg', "campo-extra");
                    cnt3.innerHTML = `
                        <span class="input-group-text datos_personales--campo ${mensaje}" id="inputGroup-sizing-lg">G&Eacute;nero:</span>
                        <select id="${i+1}" class="${i+1} form-select tipoPasajero ${mensaje} Genero-${i+1} ver" aria-label="Default select example" name="genero-${i+1}">
                            <option  value="" disabled selected>Selecciona el g&eacute;nero</option>
                            <option value="masculino">Masculino</option>
                            <option value="femenino">Femenino</option>
                            <option value="Otro">Otro</option>
                        </select>
                        <div class="mensaje_error">
                            Campo incompleto!
                        </div>
                    `;


                    if(tipo === "Hombre"){
                        //despues de la escritura procesamos los mensajes....

                        //la limpieza del html es correcto... sin embargo, debemos especificar en donde quiero que me 
                        //haga la limpieza del html... caso contrario le da la puta gana de limpiar en otro lado que no es....

                        //activamos nuestro mensaje de error en cada campo....
                        //eliminamos campos anteriores que posiblemente hayan estado incluido.....
                        if(document.querySelectorAll(`#contenedor-${id} .campo-extra`).length !== 0){
                            document.querySelectorAll(`#contenedor-${id} .campo-extra`).forEach(n => {
                                n.remove();
                            });
                        }

                        contenedor.appendChild(cnt);
                        contenedor.appendChild(cnt2);
                        contenedor.appendChild(cnt3);
                    }else if(tipo === "Mujer"){
                        //la puteria de arriba... me hace entender que debo colocar el identificador ...
                        //eso me ayudara a comprender en donde realmente tengo que eliminar los campos. :)

                        //eliminamos campos anteriores que posiblemente hayan estado incluido.....
                        if(document.querySelectorAll(`#contenedor-${id} .campo-extra`).length !== 0){
                            document.querySelectorAll(`#contenedor-${id} .campo-extra`).forEach(n => {
                                n.remove();
                            });
                        }
                        //ajustaremos unos campos adicionales para mujer
                        contenedor.appendChild(cnt);
                        contenedor.appendChild(cnt2);
                        contenedor.appendChild(cnt3);
                    }else if(tipo === "nino"){
                        //eliminamos campos anteriores que posiblemente hayan estado incluido.....
                        if(document.querySelectorAll(`#contenedor-${id} .campo-extra`).length !== 0){
                            document.querySelectorAll(`#contenedor-${id} .campo-extra`).forEach(n => {
                                n.remove();
                            });
                        }
                        //ajustaremos unos campos adicionales para el nino...
                        //en este caso... no creo que el pequeño timmy necesite correo electrónico o celular....
                        //a menos que sea un nativo digital... lo dejaremos por el momento así.
                        contenedor.appendChild(cnt3);
                    }
                }
                
            }
            
        }
    });
    
    //esta funcion se encarga de rellenar los 4 campos adicionales que se generan cuando 
    //el usuario escoge el tipo de usuario que es... (niño, hombre y mujer)... en base a esas tres opciones. 
    CompletarEntradas2();
    
    
    InsertarAlertas();
}


let ciudades = {
    "ESTADOS" : []
};

function CopiarCiudades(){
    //vamos hacer una copia de los datos encontrados en el html...
     //encontrado nombre pais y el identificador unico... vamos a generar unos cuantos cambios en el campo de "ciudad desde"
    let datos = [];
    let opcionCampoCiudadDesde = document.querySelectorAll("#ciudad_desde > option");
    opcionCampoCiudadDesde.forEach(ciudad => {
        let l = {"ID" : "", "VUELO" : ""};
        
        let id = ciudad.id;
        let vuelo = ciudad.value;
        
        l.ID = id;
        l.VUELO = vuelo;
        
        datos.push(l);
    });
    
    ciudades.ESTADOS = [...datos];
}


//proposito de la funcion: cuando el usuario escoge su pais de origen, en el campo de "ciudad desde" debe aparecer
//solo las ciudades respectivas de ese pais escogido y no otras ciudades que no pertenezcan
//lo mismo se aplica para el campo de pais destino y sus ciudades respectivas... 
function VerificarCamposFechas(){
   
    let campoPaisDesde = document.querySelector("select#from");
    
    campoPaisDesde.addEventListener("change", (e) => {
        let pais = e.target.value;
        FiltrarCiudades1(pais);
    });
    
    
    let pais = campoPaisDesde.value;
    FiltrarCiudades1(pais);
}


function FiltrarCiudades1(p){
    let pais = p;
    let id = 0;

    let opcionesCiudadDesde = document.querySelectorAll("select#from > option");

    opcionesCiudadDesde.forEach(ciudad => {
        if(ciudad.value === pais){
           id = parseInt(ciudad.id);
        }
    });


    //a continuacion vamos hacer uso de nuestra lista de datos hecho una copia.
    let contenedor = document.querySelector("#ciudad_desde");

    //dentro de nuestro contenedor vamos a limpiar los elementos...
    document.querySelectorAll("#ciudad_desde > option").forEach(elemento => {
        elemento.remove();
    });

    ciudades.ESTADOS.forEach(estado => {

        let idCiudad = parseInt(estado.ID);
        if(id === idCiudad){
            let opcion = document.createElement('OPTION');
            opcion.id = estado.ID;
            opcion.value = estado.VUELO;
            opcion.textContent = estado.VUELO;

            contenedor.appendChild(opcion); 
        }
    });
}


//el mismo proceso de nuestra funcion VerificarCamposFecha() -> lo haremos dentro de esta funcion...
function VerificarCamposFechas2(){
    //este es nuestro campo segundo de "vuelo a"
    let campoPaisA = document.querySelector("select#to");

    campoPaisA.addEventListener("change", (e) => {
        let pais = e.target.value;
        FiltrarCiudades2(pais);
    });
    
    let pais = campoPaisA.value;
    FiltrarCiudades2(pais);
}

function FiltrarCiudades2(p){
    let pais = p;
    let id = 0;
    let opcionesPaisA = document.querySelectorAll("select#to > option");
    opcionesPaisA.forEach(ciudad => {
        if(ciudad.value === pais){
           id = parseInt(ciudad.id);
        }
    });


    //a continuacion vamos hacer uso de nuestra lista de datos hecho una copia.
    let contenedor = document.querySelector("#ciudad_a");

    //dentro de nuestro contenedor vamos a limpiar los elementos...
    document.querySelectorAll("#ciudad_a > option").forEach(elemento => {
        elemento.remove();
    });

    ciudades.ESTADOS.forEach(estado => {

        let idCiudad = parseInt(estado.ID);
        if(id === idCiudad){
            let opcion = document.createElement('OPTION');
            opcion.id = estado.ID;
            opcion.value = estado.VUELO;
            opcion.textContent = estado.VUELO;

            contenedor.appendChild(opcion); 
        }
    });
}


//proposito: => verificar por el tipo de pasajero que es. Niño, Hombre, Mujer....
//en base a eso... la funcion desplegara campos adicionales para que el cliente
//los llene...
function TipoPasajeroCampo(){
    //uno de los fallos que tenemos en el codigo es que debemos ir actualizando las acciones que realiza un cliente...
    //debemos estar pendientes de cuando realizar un tipo de accion espeficio...
    let campoNumeroPasajeros = document.querySelector("#Pasajeros");
    
    campoNumeroPasajeros.addEventListener("input", (e) => {
        EscribirHTML2();
    });
    
    
    EscribirHTML2();
}

function VerificarBotonEnviar(){
    //cuando el usuario de click en enviar... lo que haremos a continuacion 
    //es generar una verificacion correspondiente.
    let botonEnviar = document.querySelector("#boton_formulario");
    
    botonEnviar.addEventListener("click", (e) => {
        //entonces en caso de que el usuario no introduzca un valor... lo que haremos es guardar solamente
        //un string vacio... caso contrario guardamos la informacion introducida por el usuario....
        
        sessionStorage.setItem("nombre", registro.nombre);
        sessionStorage.setItem("apellido", registro.apellido);
        sessionStorage.setItem("cedula", registro.cedula);
        sessionStorage.setItem("tipoUsuario", registro.tipoPasajero);
        sessionStorage.setItem("email", registro.email);
        sessionStorage.setItem("celular", registro.celular);
        sessionStorage.setItem("genero", registro.genero);
       
    });
}

