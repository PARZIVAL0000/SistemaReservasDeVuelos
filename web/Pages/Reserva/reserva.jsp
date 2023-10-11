<%@page import="ModeloDAO.PaisDAO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Modelo.Pais"%>
<%@page import="Modelo.Pasajeros"%>
<%@page import="Modelo.Estado"%>
<%@page import="ModeloDAO.EstadoDAO"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    	<title>Flight - Travel and Tour</title>
    
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="apple-touch-icon" href="apple-touch-icon.png">

        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="css/fontAwesome.css">
        <link rel="stylesheet" href="css/hero-slider.css">
        <link rel="stylesheet" href="css/owl-carousel.css">
        <link rel="stylesheet" href="css/datepicker.css">
        <link rel="stylesheet" href="css/tooplate-style.css">

        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
        <script src="js/vendor/modernizr-2.8.3-respond-1.4.2.min.js"></script>
        <link href="css/modificado/reservas.css" rel="stylesheet"/>
        <link href="css/modificado/style.css" rel="stylesheet"/>
        
        <!--<link rel="stylesheet" href="https://necolas.github.io/normalize.css/8.0.1/normalize.css">--> 
	<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">  
    </head>

    <body>

        <% 
            ArrayList<String> datos = (ArrayList) request.getAttribute("datos");
            String tipoVuelo = datos.get(0);
            String fechaRegreso = "";
            if(tipoVuelo.equals("Ida-Regreso")){
                fechaRegreso = datos.get(5);
            }
            
            PaisDAO pd = new PaisDAO();
            EstadoDAO ed = new EstadoDAO();
            
            //nos vamos a traer los datos de los paises disponibles...
            List<Pais> paises = pd.listarPaises();
            //vamos a incluir nuestra ciudad...
            List<Estado> ciudades = ed.listarEstados();
            
        %>

        <section class="banner" id="top">
            <div class="container">
                <div class="row">
                    <div class="col-md-5">
                        <div class="left-side">
                            <div class="logo">
                                <img src="img/logo.png" alt="Flight Template">
                            </div>
                            <div class="tabs-content">
                                <h4>Realiza una de las acciones a continuación:</h4>
                                <ul class="social-links">
                                    <li><a href="/ReservaDeVuelos/login">Iniciar Sesión<i class='bx bx-user'><img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAAAAXNSR0IArs4c6QAAAq1JREFUSEvF1luoT3kUB/DPGUwjKbnkUiLGpTxJRJSSR6PkgacJpUgJIZQUQyQhUhSleVB4wxMPJrkkI7fIrTwgQjy4pDHROv32aZ/d/u/9dw6d9fTvv9da33X7rfVt0UXS0kW4vhf4F0zCCAxJQT/HY1zF12YTaRZ4GDZgLvo1cP4KJ7ENT+sCqAP+FRuxFvG7GfmMrSmA/xsZVAEPxilMaAatROefVKE3ZfaNgIfjMgZ1EDQzi95PxuuinzLgPriGkZ0EzcyvYxo+5f2VAR/BwhrQL7ifdMaiW43+DqyrAh6De1Q+sxNYiqx3/XEIcyrAPyLaF5PfKsWMw8HiCgdRtnjHxWmNib+JyL6RbMamMuAo11v0rjBehgMNvq/CrgrbaE1bYPmMp+N8Ta9m4UwDnfgWz69KRuNhsdR/4miN4Urs6WDGYTY7Cy6f8WrsrAG+g/GIqc5Ld9zFqBr7FdhbzLiuR5nP41iS5iH+G4CDNVOd2QbG7iLwfByriTj7HBnHs+uRsqx7x5ndPETg7Z5TrLZYkz9TJqat2A44bu2LVLoy8Li1sfhjqiPbD0mpF8bhj7QaGwX+DEOzm11cIPFGYysV5QYWpCVRVZHI6G/EBixK9DZ63CpF4N9TNjGlmbxMGZWetxKAYCa30Tf37b/EWtoIQtmROIxFOaN3mIl/m2x+3O9ziCuXyT4sz9uXAUekVwpv8j3+wv5cb4txxKoN5+sRfc/kFqYifLRJIyIQZO4SBha8x5U5i4t4kM5h3O1YtzPQs6D/JBGBaFc7qaI+0avTaVM1WeV2ahfSUvku6pN5+C2dsjVNHPvMJsjeFmwvOZ+1pS5mGDs4ehfHPj80eb0oZ5CEYBudprfFAGI1TkHw7GChsVSC0D9KG+mHE/qO9LjSpo7Q/3DAzOE3IyR2Hx7JfUAAAAAASUVORK5CYII="/></i></a></li>
                                    <li><a href="/ReservaDeVuelos/">Home<i class='bx bxs-plane-alt'><img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAAAAXNSR0IArs4c6QAAAjNJREFUSEvt1kmojlEYwPHfNQ8ZFliZN0pZSUjKRrKUoSgZMyVys1BSlJ2FUrIwZSdDSLFRspGhrFAIGRY2RIlk7Lmdq9frfN/3vt1Pd+Ns3uGc5/k/5zzT6dBLo6OXuP6D23nyU/EG73JK/8VR98MxrMJXrMC5Mrzd4P64hIUF0EeMw/siPAceg9F4hs81zn5wgs7PyKzFyWbgkbiFKWlR+OclXqRn+T18+BNDcRVzM9APmIB4/h7lHW/DQfSpuNPw4au0dnJG5geW4XwVH4/HLqzBoIoG5JZ9x1JcqBvV4edObMaImgY0hYauKlEd0IDvQARelbEcp5strALulo9jj+jciUkt6BGcj9sFDj0DcRm5lClyYv5au8CRp1cwr8JZ/5W3VaI6p7cONOT3Yl+dHY/FITzBQzzA85QSueIQxeM+ppUgx7G+Kjiqy41UZSqcZlfFWom7eFQSCP82jYNiVA9PVkb1CiNajXU4kRaF74uNISK6u+xm9eTSqS8Wp+IxswF9C44U5iLgrhe+vyE6VcPRKo/nYA8WFDTE9/6MxjuYUfg/Cm8bkVuBQ25IukkMS0pW41RG4RKcLfyfjns9AYfsgVSx4v0wtmYURkd7iolpbhEu9hQcaRY9OZTfxqwGCjcVfL89pWbl4Gpk5JnU5r6k449eWx4D8DrdYMoB+MfaKj7uFpiNmziKjenmkTNyd0rHDT2J6rJstMdiGuV0xzXoUxPDumTq7LjZBmrP9Rr4FzmMXx+LW5mDAAAAAElFTkSuQmCC"/></i></a></li>
                                    <li><a href="#">Blog<i class='bx bx-edit'><img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAAAAXNSR0IArs4c6QAAAidJREFUSEvNls1LVkEUhx8jWkubsAQ/ymXROkXTjRH9L5l9LEwwUYvS1Ij+lKBVplktclHQIkhDwSRr1aKFqCU/OVcO473vndFXXmd15+s88ztzztxTR41aXY24HDtwPXAJOBHpkY/AX1t7BngO9AJvgZvAUmgnT/Et4AlwMhL6DOhz0PdAq9v7G7gCLHp7IfgcsBoJ1LIpoN/WnwVmgQvWnwG67Xsd6PDwENxpm7Veyj9VOMQWMG/zOrCg560/bftlY9LG3hl8txuCrwI6qZpO+yZCfQj9DFx2+14C14Ftf32HBQs6F9ypv4LbwES1FYdKFclSlrn7NdBj0F8WYHvRfVDFgurOmsywVN0FGi2Fmp2rFViK6u+VojrmjgVVfraYoUfAgDP6GLjnlCqav5XlcRm4SGlm9w4w7lJon9JsYYqr5ValTOjeGKgEbQAfUsGC6U6lWG0YeODcp++hAqWCvgKu+fSMUay3d8ECR7YHgTEHHbExDf0Autzb3AZ8AU6F70IM2N/7Q+C+g466vqAKpGU3XxgzqWD/mimIFEyZ0nZgJYjeqoGz9/sGoFcpg4ZKM37VwGE6yr15So8UXAYV/FCKVY34v02m5ivwM3RBNe+4xHbF6WjFKYVAzIHkKVUpaspv/UJ3W5hODcBajMXENf8B2dafKhesQRVuTxMqzLIzbFoZ9MIvLKqrTwMXczxSBgnn/wEqhf6EE8euoE9Vlry+Zop3ADmsmx8bXzHGAAAAAElFTkSuQmCC"/></i></a></li>
                                </ul>
                            </div>
                            <div class="page-direction-button">
                                <a href="contact.html"><i class="fa fa-phone"></i>Contactar Ahora!</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-5 col-md-offset-1">
                        <section id="first-tab-group" class="tabgroup">
                            <div id="tab1">
                                <div class="submit-form">
                                    <h4>Completa Perfil</h4>
                                    <p>Rellena el formulario a continuación para poder finalizar con tu reserva de vuelo</p>
                                </div>
                            </div>
                        </section>
                    </div>
                </div>
            </div>
        </section>

        <!-- Dentro de esta seccion lo que haremos es generar un formulario... -->
        <section id="formulario_reserva" class="formulario_reservacion">

            <h1 class="header_seccion" id="titulo_principal">Perfil Reserva Vuelo</h1>
            <form id="form-submit" class="formulario_reserva formulario" method="POST" action="ReservaControllers?accion=perfil_vuelo">
                <input type="hidden" name="TipoVuelo" class="TipoVuelos" value="<%= tipoVuelo %>"/>
                <fieldset>
                    <h2 class="header_seccion">Datos de Vuelo</h2>
                    <legend>Completa tu informacion de vuelo</legend>
                    <div class="vuelos">
                         <!--final de la inclusion de nuestros paises... -->
                        <div class="col-md-6 campo_vuelo">
                            <label for="from">Desde:</label>
                            <select name='from' onchange='this.form.()' id="from" class="form-select" aria-label="Default select example">

                                <%
                                    for(Pais j : paises){
                                       
                                        if(j.getPaisNombre().equals(datos.get(1))){ %>
                                        <option id="<%= j.getId() %>" value="<%= datos.get(1) %>" selected><%= datos.get(1) %></option>
                                <%      }else{ %>
                                        <option id="<%= j.getId() %>" value="<%= j.getPaisNombre() %>"><%= j.getPaisNombre() %></option>
                                <%      }
                                    }
                                %>
                            </select>
                            <div class="mensaje ocultar" id="mensaje_desde">
                                Debes introducir tu pais de origen/salida.
                            </div>
                        </div>
                        <div class="col-md-6 campo_vuelo">
                            <label for="to">A:</label>
                            <select name='to' onchange='this.form.()' id="to" class="form-select" aria-label="Default select example">
                                <%
                                    for(Pais j : paises){
                                        if(j.getPaisNombre().equals(datos.get(2))){ %>
                                            <option id="<%= j.getId() %>" value="<%= datos.get(2) %>" selected><%= datos.get(2) %></option>
                                <%      }else{ %>
                                            <option id="<%= j.getId() %>" value="<%= j.getPaisNombre() %>"><%= j.getPaisNombre() %></option>
                                <%      }
                                    }
                                %>
                            </select>
                            <div class="mensaje ocultar" id="mensaje_a">
                                Debes introducir tu pais de destino.
                            </div>
                        </div>
                        <!--final de la inclusion de nuestros paises... -->
                            
                        <!-- .vamos a incluir tambien la ciudad el cual el usuario desea viajar... -->
                        <div class="col-md-6 campo_vuelo">
                            <label for="to">Estado Desde:</label>
                            <select id="ciudad_desde" name='ciudad_from' onchange='this.form.()' class="form-select" aria-label="Default select example">
                                <% for(Estado i : ciudades){ %>
                                    <option id="<%= i.getUbicacionPais() %>" value="<%= i.getEstadoNombre() %>"><%= i.getEstadoNombre() %></option>
                                <% } %>
                            </select>
                            <div class="mensaje ocultar" id="mensaje_estadoDesde">
                                Introduce tu estado de origen.
                            </div>
                        </div>
                        <div class="col-md-6 campo_vuelo">
                            <label for="to">Estado A:</label>
                            <select id="ciudad_a" name='ciudad_to' onchange='this.form.()' class="form-select" aria-label="Default select example">
                               <% for(Estado i : ciudades){ %>
                                    <option id="<%= i.getUbicacionPais() %>" value="<%= i.getEstadoNombre() %>"><%= i.getEstadoNombre() %></option>
                                <% } %>
                            </select>
                            <div class="mensaje ocultar" id="mensaje_estadoA">
                                Introduce el estado de destino.
                            </div>
                        </div>
                        
                        <div class="col-md-6 campo_vuelo">
                             <fieldset>
                                 <label for="departure">Fecha de Ida:</label>
                                 <input  name="deparure" value="<%= datos.get(4) %>" type="text" class="form-control date" id="deparure" placeholder="Selecciona Fecha" onchange='this.form.()'>
                             </fieldset>
                             <div class="mensaje ocultar" id="mensaje_fechaIda">
                                 Inserta tu fecha de salida.
                             </div>
                         </div>
                             
                         <div class="col-md-6 campo_vuelo">
                             <fieldset>
                                 <label for="return">Fecha de Vuelta:</label>
                                 <input  name="return" value="<%= fechaRegreso %>" type="text" class="form-control date" id="return" placeholder="Selecciona Fecha" onchange='this.form.()'>
                             </fieldset>
                             <div class="mensaje ocultar" id="mensaje_fechaRetorno">
                                 Inserta tu fecha de retorno.
                             </div>
                         </div>
                       
                        <div class="col-md-6 campo_vuelo">
                            <label for="Pasajeros">Numero Pasajeros:</label>
                            <input id="Pasajeros" class="form-control" min="1" max="4" type="number" name="pasajeros" value="<%= datos.get(3) %>"/>
                            
                            <div class="mensaje ocultar" id="mensaje_pasajeros">
                                Inserta como mínimo un pasajero y 4 como máximo.
                            </div>
                        </div>
                    </div>
                </fieldset>

                <h2 class="header_seccion">Perfil Otros Pasajeros</h2>
                <div class="seccion_pasajeros">
                    
                </div>
                
                <button type="submit" id="boton_formulario" class="btn btn-success boton_registro_reserva">Completar Perfil Registro</button>
                <!--<button type="button" id="boton_resetear" class="btn btn-warning boton_registro_reserva">Resetear Perfil Registro</button>-->
            </form>
        </section>
                
        <footer>
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="primary-button">
                            <a href="#" class="scroll-top">Volver al Inicio</a>
                        </div>
                    </div>
                    <div class="col-md-12">
                        <ul class="social-icons">
                            <li><a href="https://www.facebook.com/tooplate"><i class="fa fa-facebook"></i></a></li>
                            <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                            <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
                            <li><a href="#"><i class="fa fa-rss"></i></a></li>
                            <li><a href="#"><i class="fa fa-behance"></i></a></li>
                        </ul>
                    </div>

                    </div>
                </div>
            </div>
        </footer>

        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script>window.jQuery || document.write('<script src="js/vendor/jquery-1.11.2.min.js"><\/script>')</script>

        <script src="js/vendor/bootstrap.min.js"></script>

        <script src="js/datepicker.js"></script>
        <script src="js/plugins.js"></script>
        <script src="js/main.js"></script>

        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js" type="text/javascript"></script>
        <script src="js/scripts/reserva.js"></script>
        <script src="https://kit.fontawesome.com/2c36e9b7b1.js" crossorigin="anonymous"></script>
    </body>
</html>