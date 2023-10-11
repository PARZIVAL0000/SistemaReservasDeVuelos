(() => {
    
    const expresiones = {
	propietario: /^[a-zA-ZÀ-ÿ\s]{1,40}$/, // Letras, numeros, guion y guion_bajo
        cedula : /^[0-9]{10,10}$/,
	correo: /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/
    };
    
    
    document.addEventListener("DOMContentLoaded", () => {

        Validacion();
    });
    
    function Validacion(){
        const formulario = document.getElementById("form");
        const inputDni = document.querySelector("#form #dni");
        
        
        const validarEntrada = (e) => {
            const name = e.target.name; 
            
            
            switch(name){
                case "dniCompra":
                    inputDni.classList.add('border');
                    inputDni.classList.add('border-3');
                    if(expresiones.cedula.test(e.target.value)){
                      inputDni.classList.add('border-success');
                      inputDni.classList.remove('border-danger');
                      
                      
                      document.querySelector("#cedulaMensaje").classList.remove("formulario__input-error-activo");
                    } else {
                      inputDni.classList.remove('border-success');
                      inputDni.classList.add('border-danger');
                    
                      console.log(document.querySelector("#cedulaMensaje"));
                    
                      document.querySelector("#cedulaMensaje").classList.add("formulario__input-error-activo");
                    }
                    
                    break;
               
                case "Names":
                    document.querySelector("#nombre").classList.add('border');
                    document.querySelector("#nombre").classList.add('border-3');
                    if(expresiones.propietario.test(e.target.value)){
                      document.querySelector("#nombre").classList.add('border-success');
                    } else {
                      document.querySelector("#nombre").classList.remove('border-success');
                    }
                    
                    break;
                    
                
                case "Emails":
                    document.querySelector("#correo").classList.add('border');
                    document.querySelector("#correo").classList.add('border-3');
                    if(expresiones.correo.test(e.target.value)){
                      document.querySelector("#correo").classList.add('border-success');
                    } else {
                      document.querySelector("#correo").classList.remove('border-success');
                    }
                    
                    break;
            }
        };
        
        inputDni.addEventListener("keyup", validarEntrada);
        inputDni.addEventListener("blur", validarEntrada);
    }
})();