(() => {
    
    const expresiones = {
	nombre: /^[a-zA-ZÀ-ÿ\s]{1,40}$/, // Letras, numeros, guion y guion_bajo
	apellido: /^[a-zA-ZÀ-ÿ\s]{1,40}$/, // Letras y espacios, pueden llevar acentos.
        cedula : /^[0-9]{10,10}$/,
	correo: /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/,
	telefono: /^\d{10,10}$/ // 7 a 14 numeros.
    };
    
    
    
    document.addEventListener("DOMContentLoaded", () => {
        Validar();
    });
    
    function Validar(){
        Formulario();
    }
    
    function Formulario(entrada = false){
        const formulario = document.getElementById("form-submit");
        const inputs = document.querySelectorAll("#form-submit input");
        const selects = document.querySelectorAll("#form-submit select");
        const inputPasajeros = document.getElementById("Pasajeros");
        

        const validarFormulario = (e) => {
            const name = e.target.name;
            const nPasajeros = document.getElementById("Pasajeros").value;
            if(name === "pasajeros"){
                nPasajeros = e.target.value;
            }
           
           for(let i = 0; i < parseInt(nPasajeros); i++){
                switch(name){
                    case `Nombres-${i+1}`:
                        document.getElementById(`Nombres-${i+1}`).classList.add("border");
                        if(expresiones.nombre.test(e.target.value)){
                            document.getElementById(`Nombres-${i+1}`).classList.add("border-primary");
                            document.getElementById(`Nombres-${i+1}`).classList.remove("border-danger");
                            
                           const elementFather = document.getElementById(`Nombres-${i+1}`).parentNode;
                           elementFather.querySelector(`#mensaje-${i+1}`).classList.remove('formulario__input-error-activo');
                        }else{
                            //border border-danger
                            document.getElementById(`Nombres-${i+1}`).classList.add("border-danger");
                            document.getElementById(`Nombres-${i+1}`).classList.remove("border-primary");
                            
                            const elementFather = document.getElementById(`Nombres-${i+1}`).parentNode;
                            elementFather.querySelector(`#mensaje-${i+1}`).classList.add('formulario__input-error-activo');
                            
                        }
                        break;
                    
                    case `Apellidos-${i+1}`:
                        document.getElementById(`Apellidos-${i+1}`).classList.add("border");
                        if(expresiones.apellido.test(e.target.value)){
                            document.getElementById(`Apellidos-${i+1}`).classList.add("border-primary");
                            document.getElementById(`Apellidos-${i+1}`).classList.remove("border-danger");
                            
                            const elementFather = document.getElementById(`Apellidos-${i+1}`).parentNode;
                           elementFather.querySelector(`#mensaje-${i+1}`).classList.remove('formulario__input-error-activo');
                        }else{
                            //border border-danger
                            document.getElementById(`Apellidos-${i+1}`).classList.add("border-danger");
                            document.getElementById(`Apellidos-${i+1}`).classList.remove("border-primary");
                            
                            const elementFather = document.getElementById(`Apellidos-${i+1}`).parentNode;
                            elementFather.querySelector(`#mensaje-${i+1}`).classList.add('formulario__input-error-activo');
                        }
                        break;
                        
                    
                    case `Cedula-${i+1}`:
                        document.getElementById(`Cedula-${i+1}`).classList.add("border");
                        if(expresiones.cedula.test(e.target.value)){
                            document.getElementById(`Cedula-${i+1}`).classList.add("border-primary");
                            document.getElementById(`Cedula-${i+1}`).classList.remove("border-danger");
                            
                            const elementFather = document.getElementById(`Cedula-${i+1}`).parentNode;
                            elementFather.querySelector(`#mensaje-${i+1}`).classList.remove('formulario__input-error-activo');
                        }else{
                            //border border-danger
                            document.getElementById(`Cedula-${i+1}`).classList.add("border-danger");
                            document.getElementById(`Cedula-${i+1}`).classList.remove("border-primary");
                            
                            const elementFather = document.getElementById(`Cedula-${i+1}`).parentNode;
                            elementFather.querySelector(`#mensaje-${i+1}`).classList.add('formulario__input-error-activo');
                        }
                        break;
                        
                    
                    case `tipo_pasajero-${i+1}`:
                        
                        document.getElementsByClassName(`tipo_pasajero-${i+1}`)[0].classList.add("border");
                        if(e.target.value !== ""){
                            document.getElementsByClassName(`tipo_pasajero-${i+1}`)[0].classList.add("border-primary");
                            document.getElementsByClassName(`tipo_pasajero-${i+1}`)[0].classList.remove("border-danger");
                            
                            const elementFather = document.getElementsByClassName(`tipo_pasajero-${i+1}`)[0].parentNode;
                            elementFather.querySelector(`#mensaje-${i+1}`).classList.remove('formulario__input-error-activo');
                            
                            
                            Formulario(true); //volvemos a llamar a nuestra funcion para que pueda analizar otros campos adicionales.
                        }else{
                            //border border-danger
                            document.getElementsByClassName(`tipo_pasajero-${i+1}`)[0].classList.add("border-danger");
                            document.getElementsByClassName(`tipo_pasajero-${i+1}`)[0].classList.remove("border-primary");
                            
                            const elementFather = document.getElementsByClassName(`tipo_pasajero-${i+1}`)[0].parentNode;
                            elementFather.querySelector(`#mensaje-${i+1}`).classList.add('formulario__input-error-activo');
                        }
                        break;
                        
                        
                    case `Email-${i+1}`:
                        document.getElementById(`Email-${i+1}`).classList.add("border");
                        if(expresiones.correo.test(e.target.value)){
                           document.getElementById(`Email-${i+1}`).classList.add("border-primary");
                           document.getElementById(`Email-${i+1}`).classList.remove("border-danger");
                            
                           const elementFather = document.getElementById(`Email-${i+1}`).parentNode;
                           elementFather.querySelector(`#mensaje-${i+1}`).classList.remove('formulario__input-error-activo');
                        }else{
                            //border border-danger
                            document.getElementById(`Email-${i+1}`).classList.add("border-danger");
                            document.getElementById(`Email-${i+1}`).classList.remove("border-primary");
                            
                            const elementFather = document.getElementById(`Email-${i+1}`).parentNode;
                            elementFather.querySelector(`#mensaje-${i+1}`).classList.add('formulario__input-error-activo');
                        }
                        break;
                    
                    
                    case `Celular-${i+1}`:
                        document.getElementById(`Celular-${i+1}`).classList.add("border");
                        if(expresiones.telefono.test(e.target.value)){
                           document.getElementById(`Celular-${i+1}`).classList.add("border-primary");
                           document.getElementById(`Celular-${i+1}`).classList.remove("border-danger");
                            
                           const elementFather = document.getElementById(`Celular-${i+1}`).parentNode;
                           elementFather.querySelector(`#mensaje-${i+1}`).classList.remove('formulario__input-error-activo');
                        }else{
                            //border border-danger
                            document.getElementById(`Celular-${i+1}`).classList.add("border-danger");
                            document.getElementById(`Celular-${i+1}`).classList.remove("border-primary");
                            
                            const elementFather = document.getElementById(`Celular-${i+1}`).parentNode;
                            elementFather.querySelector(`#mensaje-${i+1}`).classList.add('formulario__input-error-activo');
                        }
                        break;
                        
                    
                    case `genero-${i+1}`:
                        document.getElementsByClassName(`Genero-${i+1}`)[0].classList.add("border");
                        if(e.target.value !== ""){
                            document.getElementsByClassName(`Genero-${i+1}`)[0].classList.add("border-primary");
                            document.getElementsByClassName(`Genero-${i+1}`)[0].classList.remove("border-danger");
                            
                            const elementFather = document.getElementsByClassName(`Genero-${i+1}`)[0].parentNode;
                            elementFather.querySelector(`#mensaje-${i+1}`).classList.remove('formulario__input-error-activo');
                            
                            
                            ComprobarEntradas(nPasajeros);
                        }else{
                            //border border-danger
                            document.getElementsByClassName(`Genero-${i+1}`)[0].classList.add("border-danger");
                            document.getElementsByClassName(`Genero-${i+1}`)[0].classList.remove("border-primary");
                            
                            const elementFather = document.getElementsByClassName(`Genero-${i+1}`)[0].parentNode;
                            elementFather.querySelector(`#mensaje-${i+1}`).classList.add('formulario__input-error-activo');
                        }
                        break;
                    
                    default:
                        break;
                }   
            }
            
        };
        
        const verificarEnvio = (e) => {
//            e.preventDefault();
        };

        if(entrada){
            inputs.forEach(input => {
                input.addEventListener("keyup", validarFormulario);
                input.addEventListener("blur", validarFormulario);
                inputPasajeros.addEventListener("input", validarFormulario);
            });

            selects.forEach(select => {
                 select.addEventListener("change", validarFormulario);
            });
        }
    
        inputs.forEach(input => {
            input.addEventListener("keyup", validarFormulario);
            input.addEventListener("blur", validarFormulario);
            inputPasajeros.addEventListener("input", validarFormulario);
        });
        
        selects.forEach(select => {
             select.addEventListener("change", validarFormulario);
        });
        
        formulario.addEventListener("submit", verificarEnvio);
    }
    
    
    function Boton(){
        document.getElementById("boton_formulario").disabled=true;
        document.getElementById("boton_resetear").disabled=true;
    }
    
    
    function ComprobarEntradas(nPasajeros){
        const inputs = document.querySelectorAll("#form-submit input");
        const selects = document.querySelectorAll("#form-submit select");
        
        inputs.forEach(input => {
            
        });
    }
    
})(); 

