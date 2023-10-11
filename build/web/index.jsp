<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Modelo.Pais"%>
<%@page import="Modelo.Pais"%>
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
        <script src="js/vendor/modernizr-2.8.3-respond-1.4.2.min.js"></script>
        <link rel="stylesheet" href="css/modificado/style.css"/>
    </head>
    <body>
    
    <%
        List<Pais> paises = (ArrayList) request.getAttribute("paises");        
        List<String> mensaje = (ArrayList) request.getAttribute("mensaje");
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
                                <li><a href="/ReservaDeVuelos">Home<i class='bx bxs-plane-alt'><img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAAAAXNSR0IArs4c6QAAAjNJREFUSEvt1kmojlEYwPHfNQ8ZFliZN0pZSUjKRrKUoSgZMyVys1BSlJ2FUrIwZSdDSLFRspGhrFAIGRY2RIlk7Lmdq9frfN/3vt1Pd+Ns3uGc5/k/5zzT6dBLo6OXuP6D23nyU/EG73JK/8VR98MxrMJXrMC5Mrzd4P64hIUF0EeMw/siPAceg9F4hs81zn5wgs7PyKzFyWbgkbiFKWlR+OclXqRn+T18+BNDcRVzM9APmIB4/h7lHW/DQfSpuNPw4au0dnJG5geW4XwVH4/HLqzBoIoG5JZ9x1JcqBvV4edObMaImgY0hYauKlEd0IDvQARelbEcp5strALulo9jj+jciUkt6BGcj9sFDj0DcRm5lClyYv5au8CRp1cwr8JZ/5W3VaI6p7cONOT3Yl+dHY/FITzBQzzA85QSueIQxeM+ppUgx7G+Kjiqy41UZSqcZlfFWom7eFQSCP82jYNiVA9PVkb1CiNajXU4kRaF74uNISK6u+xm9eTSqS8Wp+IxswF9C44U5iLgrhe+vyE6VcPRKo/nYA8WFDTE9/6MxjuYUfg/Cm8bkVuBQ25IukkMS0pW41RG4RKcLfyfjns9AYfsgVSx4v0wtmYURkd7iolpbhEu9hQcaRY9OZTfxqwGCjcVfL89pWbl4Gpk5JnU5r6k449eWx4D8DrdYMoB+MfaKj7uFpiNmziKjenmkTNyd0rHDT2J6rJstMdiGuV0xzXoUxPDumTq7LjZBmrP9Rr4FzmMXx+LW5mDAAAAAElFTkSuQmCC"/></i></a></li>
                                <li><a href="#">Blog<i class='bx bx-edit'><img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAAAAXNSR0IArs4c6QAAAidJREFUSEvNls1LVkEUhx8jWkubsAQ/ymXROkXTjRH9L5l9LEwwUYvS1Ij+lKBVplktclHQIkhDwSRr1aKFqCU/OVcO473vndFXXmd15+s88ztzztxTR41aXY24HDtwPXAJOBHpkY/AX1t7BngO9AJvgZvAUmgnT/Et4AlwMhL6DOhz0PdAq9v7G7gCLHp7IfgcsBoJ1LIpoN/WnwVmgQvWnwG67Xsd6PDwENxpm7Veyj9VOMQWMG/zOrCg560/bftlY9LG3hl8txuCrwI6qZpO+yZCfQj9DFx2+14C14Ftf32HBQs6F9ypv4LbwES1FYdKFclSlrn7NdBj0F8WYHvRfVDFgurOmsywVN0FGi2Fmp2rFViK6u+VojrmjgVVfraYoUfAgDP6GLjnlCqav5XlcRm4SGlm9w4w7lJon9JsYYqr5ValTOjeGKgEbQAfUsGC6U6lWG0YeODcp++hAqWCvgKu+fSMUay3d8ECR7YHgTEHHbExDf0Autzb3AZ8AU6F70IM2N/7Q+C+g466vqAKpGU3XxgzqWD/mimIFEyZ0nZgJYjeqoGz9/sGoFcpg4ZKM37VwGE6yr15So8UXAYV/FCKVY34v02m5ivwM3RBNe+4xHbF6WjFKYVAzIHkKVUpaspv/UJ3W5hODcBajMXENf8B2dafKhesQRVuTxMqzLIzbFoZ9MIvLKqrTwMXczxSBgnn/wEqhf6EE8euoE9Vlry+Zop3ADmsmx8bXzHGAAAAAElFTkSuQmCC"/></i></a></li>
                            </ul>
                        </div>
                        <div class="page-direction-button">
                            <a href="contact.html"><i class="fa fa-phone"></i>Contáctanos Ahora</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-5 col-md-offset-1">
                    <section id="first-tab-group" class="tabgroup">
                        <div id="tab1">
                            <div class="submit-form <%= (mensaje != null) ? mensaje.get(0) : "" %>">
                                <h4>Verificar por la dirección de tu viaje:</h4>
                                <% if(mensaje != null && mensaje.get(0).equals("error")){ %>
                                    <p class="error_mensaje"><%= mensaje.get(1) %></p>
                                <% } %>
                                <form id="form-submit" class="formulario" action="ReservaControllers?accion=reservar" method="post">
                                    <div class="row">
                                        <div class="col-md-6">
                                             <fieldset>
                                                 <div class="opciones row">
                                                     <div class="opcion-1 col-5 pb-3">
                                                         <input type="radio" id="Ida" value="Ida" name="OpcionVuelo" checked="true"/>
                                                         <label for="Ida">Ida: </label>
                                                     </div>
                                                     <div class="opcion-2 col">
                                                         <input type="radio" id="Ida-Regreso" value="Ida-Regreso" name="OpcionVuelo"/>
                                                         <label for="Ida-Regreso">Ida y Regreso: </label>
                                                     </div>
                                                 </div>
                                             </fieldset>
                                        </div>
                                    </div>
                                    
                                    <div class="row">
                                        <div id="vueloDesde" class="col-md-6">
                                            <fieldset>
                                                <label for="from">Origen:</label>
                                                <select name='from' id="from" onchange='this.form.()'>
                                                    <option value="">Seleccionar lugar...</option>
                                                    
                                                    <% for(Pais j : paises){ %>
                                                    <option value="<%= j.getPaisNombre() %>"><%= j.getPaisNombre() %></option>
                                                    <% } %>
                                                   
                                                </select>
                                                    
                                            </fieldset>
                                            <div id="mensaje_paisSalida" class="mensaje ocultar">
                                                introduce tu pais de origen/salida.
                                            </div> 
                                        </div>
                                        <div id="vueloA" class="col-md-6">
                                            <fieldset>
                                                <label for="to">Destino:</label>
                                                <select name='to' id="to" onchange='this.form.()'>
                                                    <option value="" >Seleccionar lugar...</option>
                                                 
                                                    <% for(Pais j : paises){ %>
                                                    <option value="<%= j.getPaisNombre() %>"><%= j.getPaisNombre() %></option>
                                                    <% } %>
                                                </select>
                                            </fieldset>
                                            <div id="mensaje_paisDestino" class="mensaje ocultar">
                                                introduce tu vuelo de destino
                                            </div>
                                        </div>
                                        <div id="fechaSalida" class="col-md-6">
                                            <fieldset>
                                                <label for="departure">Fecha de Ida:</label>
                                                <input name="deparure" type="text" class="form-control date" id="deparure" placeholder="Selecciona Fecha"  onchange='this.form.()'>
                                            </fieldset>
                                            <div id="mensaje_fechaIda" class="mensaje ocultar">
                                                debes insertar una fecha de ida
                                            </div>
                                        </div>
                                        <div id="fechaRegreso" class="col-md-6">
                                            <fieldset >
                                                <label for="return">Fecha de Vuelta:</label>
                                                <input name="return" type="text" class="form-control date" id="return" placeholder="Selecciona Fecha"  onchange='this.form.()'>
                                            </fieldset>
                                            <div id="mensaje_fechaRegreso" class="mensaje ocultar">
                                                debes insertar una fecha de regreso
                                            </div>
                                        </div>
                                                
                                        <div id="pasajerosTotal" class="col-md-6">
                                            <fieldset>
                                                <label for="pasajero">Pasajeros:</label>
                                                <input name="pasajeros" min="1" max="4" type="number" id="pasajero" class="form-control pasajero" placeholder="Inserta el numero de personas a viajar"/>
                                            </fieldset>
                                            <div id="mensaje_pasajeros" class="mensaje ocultar">
                                                debes introducir un mínimo de 1 y un máximo de 4 pasajeros.
                                            </div>
                                        </div>
                                                
                                        <div class="col-md-6">
                                            <fieldset>
                                                <!--<input type="submit" id="form-submit" class="btn" value="Ordenar Boleto Ahora"/>-->
                                                <button type="submit" id="form-submit" class="btn">Buscar</button>
                                            </fieldset>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </section>
                </div>
            </div>
        </div>
    </section>


    <div class="tabs-content" id="weather">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="section-heading">
                        <h2>Consulta el clima para los proximos 5 días</h2>
                    </div>
                </div>
                <div class="wrapper">
                    <div class="col-md-12">
                        <div class="weather-content">
                            <div class="row">
                                <div class="col-md-12">
                                    <ul class="tabs clearfix" data-tabgroup="second-tab-group">
                                        <li><a href="#monday" class="active">Lunes</a></li>
                                        <li><a href="#tuesday">Martes</a></li>
                                        <li><a href="#wednesday">Miércoles</a></li>
                                        <li><a href="#thursday">Jueves</a></li>
                                        <li><a href="#friday">Viernes</a></li>
                                    </ul>    
                                </div>
                                <div class="col-md-12">
                                    <section id="second-tab-group" class="weathergroup">
                                        <div id="monday">
                                            <div class="row">
                                                <div class="col-md-4">
                                                    <div class="weather-item">
                                                        <h6>Myanmar</h6>
                                                        <div class="weather-icon">
                                                            <img src="img/weather-icon-03.png" alt="">
                                                        </div>
                                                        <span>32&deg;C</span>
                                                        <ul class="time-weather">
                                                            <li>6AM <span>26&deg;</span></li>
                                                            <li>12PM <span>32&deg;</span></li>
                                                            <li>6PM <span>28&deg;</span></li>
                                                            <li>12AM <span>22&deg;</span></li>
                                                        </ul>
                                                    </div>
                                                </div>
                                                <div class="col-md-4">
                                                    <div class="weather-item">
                                                        <h6>Thailand</h6>
                                                        <div class="weather-icon">
                                                            <img src="img/weather-icon-02.png" alt="">
                                                        </div>
                                                        <span>28&deg;C</span>
                                                        <ul class="time-weather">
                                                            <li>6AM <span>20&deg;</span></li>
                                                            <li>12PM <span>28&deg;</span></li>
                                                            <li>6PM <span>26&deg;</span></li>
                                                            <li>12AM <span>18&deg;</span></li>
                                                        </ul>
                                                    </div>
                                                </div>
                                                <div class="col-md-4">
                                                    <div class="weather-item">
                                                        <h6>India</h6>
                                                        <div class="weather-icon">
                                                            <img src="img/weather-icon-01.png" alt="">
                                                        </div>
                                                        <span>33&deg;C</span>
                                                        <ul class="time-weather">
                                                            <li>6AM <span>26&deg;</span></li>
                                                            <li>12PM <span>33&deg;</span></li>
                                                            <li>6PM <span>29&deg;</span></li>
                                                            <li>12AM <span>27&deg;</span></li>
                                                        </ul>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div id="tuesday">
                                            <div class="row">
                                                <div class="col-md-4">
                                                    <div class="weather-item">
                                                        <h6>Myanmar</h6>
                                                        <div class="weather-icon">
                                                            <img src="img/weather-icon-02.png" alt="">
                                                        </div>
                                                        <span>28&deg;C</span>
                                                        <ul class="time-weather">
                                                            <li>6AM <span>18&deg;</span></li>
                                                            <li>12PM <span>27&deg;</span></li>
                                                            <li>6PM <span>25&deg;</span></li>
                                                            <li>12AM <span>17&deg;</span></li>
                                                        </ul>
                                                    </div>
                                                </div>
                                                <div class="col-md-4">
                                                    <div class="weather-item">
                                                        <h6>Thailand</h6>
                                                        <div class="weather-icon">
                                                            <img src="img/weather-icon-03.png" alt="">
                                                        </div>
                                                        <span>31&deg;C</span>
                                                        <ul class="time-weather">
                                                            <li>6AM <span>19&deg;</span></li>
                                                            <li>12PM <span>28&deg;</span></li>
                                                            <li>6PM <span>22&deg;</span></li>
                                                            <li>12AM <span>18&deg;</span></li>
                                                        </ul>
                                                    </div>
                                                </div>
                                                <div class="col-md-4">
                                                    <div class="weather-item">
                                                        <h6>India</h6>
                                                        <div class="weather-icon">
                                                            <img src="img/weather-icon-01.png" alt="">
                                                        </div>
                                                        <span>26&deg;C</span>
                                                        <ul class="time-weather">
                                                            <li>6AM <span>19&deg;</span></li>
                                                            <li>12PM <span>26&deg;</span></li>
                                                            <li>6PM <span>22&deg;</span></li>
                                                            <li>12AM <span>20&deg;</span></li>
                                                        </ul>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div id="wednesday">
                                            <div class="row">
                                                <div class="col-md-4">
                                                    <div class="weather-item">
                                                        <h6>Myanmar</h6>
                                                        <div class="weather-icon">
                                                            <img src="img/weather-icon-03.png" alt="">
                                                        </div>
                                                        <span>31&deg;C</span>
                                                        <ul class="time-weather">
                                                            <li>6AM <span>19&deg;</span></li>
                                                            <li>12PM <span>28&deg;</span></li>
                                                            <li>6PM <span>22&deg;</span></li>
                                                            <li>12AM <span>18&deg;</span></li>
                                                        </ul>
                                                    </div>
                                                </div>
                                                <div class="col-md-4">
                                                    <div class="weather-item">
                                                        <h6>Thailand</h6>
                                                        <div class="weather-icon">
                                                            <img src="img/weather-icon-01.png" alt="">
                                                        </div>
                                                        <span>34&deg;C</span>
                                                        <ul class="time-weather">
                                                            <li>6AM <span>28&deg;</span></li>
                                                            <li>12PM <span>34&deg;</span></li>
                                                            <li>6PM <span>30&deg;</span></li>
                                                            <li>12AM <span>29&deg;</span></li>
                                                        </ul>
                                                    </div>
                                                </div>
                                                <div class="col-md-4">
                                                    <div class="weather-item">
                                                        <h6>India</h6>
                                                        <div class="weather-icon">
                                                            <img src="img/weather-icon-02.png" alt="">
                                                        </div>
                                                        <span>28&deg;C</span>
                                                        <ul class="time-weather">
                                                            <li>6AM <span>18&deg;</span></li>
                                                            <li>12PM <span>27&deg;</span></li>
                                                            <li>6PM <span>25&deg;</span></li>
                                                            <li>12AM <span>17&deg;</span></li>
                                                        </ul>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div id="thursday">
                                            <div class="row">
                                                <div class="col-md-4">
                                                    <div class="weather-item">
                                                        <h6>Myanmar</h6>
                                                        <div class="weather-icon">
                                                            <img src="img/weather-icon-01.png" alt="">
                                                        </div>
                                                        <span>27&deg;C</span>
                                                        <ul class="time-weather">
                                                            <li>6AM <span>21&deg;</span></li>
                                                            <li>12PM <span>27&deg;</span></li>
                                                            <li>6PM <span>22&deg;</span></li>
                                                            <li>12AM <span>18&deg;</span></li>
                                                        </ul>
                                                    </div>
                                                </div>
                                                <div class="col-md-4">
                                                    <div class="weather-item">
                                                        <h6>Thailand</h6>
                                                        <div class="weather-icon">
                                                            <img src="img/weather-icon-02.png" alt="">
                                                        </div>
                                                        <span>28&deg;C</span>
                                                        <ul class="time-weather">
                                                            <li>6AM <span>18&deg;</span></li>
                                                            <li>12PM <span>27&deg;</span></li>
                                                            <li>6PM <span>25&deg;</span></li>
                                                            <li>12AM <span>17&deg;</span></li>
                                                        </ul>
                                                    </div>
                                                </div>
                                                <div class="col-md-4">
                                                    <div class="weather-item">
                                                        <h6>India</h6>
                                                        <div class="weather-icon">
                                                            <img src="img/weather-icon-03.png" alt="">
                                                        </div>
                                                        <span>31&deg;C</span>
                                                        <ul class="time-weather">
                                                            <li>6AM <span>19&deg;</span></li>
                                                            <li>12PM <span>28&deg;</span></li>
                                                            <li>6PM <span>22&deg;</span></li>
                                                            <li>12AM <span>18&deg;</span></li>
                                                        </ul>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div id="friday">
                                            <div class="row">
                                                <div class="col-md-4">
                                                    <div class="weather-item">
                                                        <h6>Myanmar</h6>
                                                        <div class="weather-icon">
                                                            <img src="img/weather-icon-03.png" alt="">
                                                        </div>
                                                        <span>33&deg;C</span>
                                                        <ul class="time-weather">
                                                            <li>6AM <span>28&deg;</span></li>
                                                            <li>12PM <span>33&deg;</span></li>
                                                            <li>6PM <span>29&deg;</span></li>
                                                            <li>12AM <span>27&deg;</span></li>
                                                        </ul>
                                                    </div>
                                                </div>
                                                <div class="col-md-4">
                                                    <div class="weather-item">
                                                        <h6>Thailand</h6>
                                                        <div class="weather-icon">
                                                            <img src="img/weather-icon-02.png" alt="">
                                                        </div>
                                                        <span>31&deg;C</span>
                                                        <ul class="time-weather">
                                                            <li>6AM <span>24&deg;</span></li>
                                                            <li>12PM <span>31&deg;</span></li>
                                                            <li>6PM <span>26&deg;</span></li>
                                                            <li>12AM <span>23&deg;</span></li>
                                                        </ul>
                                                    </div>
                                                </div>
                                                <div class="col-md-4">
                                                    <div class="weather-item">
                                                        <h6>India</h6>
                                                        <div class="weather-icon">
                                                            <img src="img/weather-icon-01.png" alt="">
                                                        </div>
                                                        <span>28&deg;C</span>
                                                        <ul class="time-weather">
                                                            <li>6AM <span>24&deg;</span></li>
                                                            <li>12PM <span>28&deg;</span></li>
                                                            <li>6PM <span>26&deg;</span></li>
                                                            <li>12AM <span>22&deg;</span></li>
                                                        </ul>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </section>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <section class="services">
        <div class="container">
            <div class="row">
                <div class="col-md-4">
                    <div class="service-item first-service">
                        <div class="service-icon"></div>
                        <h4>Planea</h4>
                        <p>Planea el vuelo de tus sueños junto a nosotros, Aeroiluminaty.</p>
                        </a>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="service-item second-service">
                        <div class="service-icon"></div>
                        <h4>Sueña</h4>
                        <p>Tus sueños se podrán hacer realidad junto a nosotros, Aeroiluminaty.</p>
                        </a>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="service-item third-service">
                        <div class="service-icon"></div>
                        <h4>Experimenta</h4>
                        <p>Experimenta el mejor servicio de vuelo de la vida con Aeroiluminaty.</p>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <section id="most-visited">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="section-heading">
                        <h2>Los Paises Más Visitados</h2>
                    </div>
                </div>
                <div class="col-md-12">
                    <div id="owl-mostvisited" class="owl-carousel owl-theme">
                        <div class="item col-md-12">
                            <div class="visited-item">
                                <img src="img/place-01.jpg" alt="">
                                <div class="text-content">
                                    <h4>River Views</h4>
                                    <span>New York</span>
                                </div>
                            </div>
                        </div>
                        <div class="item col-md-12">
                            <div class="visited-item">
                                <img src="img/place-02.jpg" alt="">
                                <div class="text-content">
                                    <h4>Lorem ipsum dolor</h4>
                                    <span>Tokyo</span>
                                </div>
                            </div>
                        </div>
                        <div class="item col-md-12">
                            <div class="visited-item">
                                <img src="img/place-03.jpg" alt="">
                                <div class="text-content">
                                    <h4>Proin dignissim</h4>
                                    <span>Paris</span>
                                </div>
                            </div>
                        </div>
                        <div class="item col-md-12">
                            <div class="visited-item">
                                <img src="img/place-04.jpg" alt="">
                                <div class="text-content">
                                    <h4>Fusce sed ipsum</h4>
                                    <span>Hollywood</span>
                                </div>
                            </div>
                        </div>
                        <div class="item col-md-12">
                            <div class="visited-item">
                                <img src="img/place-02.jpg" alt="">
                                <div class="text-content">
                                    <h4>Vivamus egestas</h4>
                                    <span>Tokyo</span>
                                </div>
                            </div>
                        </div>
                        <div class="item col-md-12">
                            <div class="visited-item">
                                <img src="img/place-01.jpg" alt="">
                                <div class="text-content">
                                    <h4>Aliquam elit metus</h4>
                                    <span>New York</span>
                                </div>
                            </div>
                        </div>
                        <div class="item col-md-12">
                            <div class="visited-item">
                                <img src="img/place-03.jpg" alt="">
                                <div class="text-content">
                                    <h4>Phasellus pharetra</h4>
                                    <span>Paris</span>
                                </div>
                            </div>
                        </div>
                        <div class="item col-md-12">
                            <div class="visited-item">
                                <img src="img/place-04.jpg" alt="">
                                <div class="text-content">
                                    <h4>In in quam efficitur</h4>
                                    <span>Hollywood</span>
                                </div>
                            </div>
                        </div>
                        <div class="item col-md-12">
                            <div class="visited-item">
                                <img src="img/place-01.jpg" alt="">
                                <div class="text-content">
                                    <h4>Sed faucibus odio</h4>
                                    <span>NEW YORK</span>
                                </div>
                            </div>
                        </div>
                        <div class="item col-md-12">
                            <div class="visited-item">
                                <img src="img/place-02.jpg" alt="">
                                <div class="text-content">
                                    <h4>Donec varius porttitor</h4>
                                    <span>Tokyo</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
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
    <script src="js/scripts/app.js"></script>
</body>
</html>