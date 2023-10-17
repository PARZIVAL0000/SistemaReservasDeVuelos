function validarNumeroCedula(ci){
    ci = ci.split("");
    
    console.log(ci);
    
    for(let i = 0; i < ci.length; i++){
        if(i%2===0){
            let resultado1 = parseInt(ci[i])*2;
            ci[i] = resultado1;
        }else{
            let resultado2 = parseInt(ci[i])*1; 
            ci[i] = resultado2;
        }
    }
    
    console.log(ci);
   
    for(let i = 0; i < ci.length-1; i++){
        if(ci[i] >= 10){
            const n1 = parseInt(ci[i].toString()[0]);
            const n2 = parseInt(ci[i].toString()[1]);
            const resultado = n1+n2;
            
            ci[i] = resultado;
        }
    }
  
    console.log(ci);
    
    let sumaTotal=0;
    for(let i = 0; i < ci.length-1; i++){
        sumaTotal+=ci[i];
    }
    
    console.log(sumaTotal);
    
    if(sumaTotal%10 === ci[ci.length-1]){
        console.log(true);
    }else{
        console.log(false);
    }
    
}