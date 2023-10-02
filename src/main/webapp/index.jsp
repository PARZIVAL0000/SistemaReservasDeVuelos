<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.example.sistemareservadevuelos.Modelo.Pais"%>
<%@page import="com.example.sistemareservadevuelos.Modelo.Pais"%>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP - Hello World</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="apple-touch-icon" href="apple-touch-icon.png">

        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="css/fontAwesome.css">
        <link rel="stylesheet" href="css/hero-slider.css">
        <link rel="stylesheet" href="css/owl-carousel.css">
        <link rel="stylesheet" href="css/datepicker.css">
        <link rel="stylesheet" href="css/modificado/style.css"/>
        <link rel="stylesheet" href="css/tooplate-style.css">

        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
        <script src="js/vendor/modernizr-2.8.3-respond-1.4.2.min.js"></script>
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
                                <h4>Realiza una de las acciones a continuaci�n:</h4>
                                <ul class="social-links">
                                    <li><a href="/ReservaDeVuelos/login">Iniciar Sesi�n<i class='bx bx-user'><img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAAAAXNSR0IArs4c6QAAAq1JREFUSEvF1luoT3kUB/DPGUwjKbnkUiLGpTxJRJSSR6PkgacJpUgJIZQUQyQhUhSleVB4wxMPJrkkI7fIrTwgQjy4pDHROv32aZ/d/u/9dw6d9fTvv9da33X7rfVt0UXS0kW4vhf4F0zCCAxJQT/HY1zF12YTaRZ4GDZgLvo1cP4KJ7ENT+sCqAP+FRuxFvG7GfmMrSmA/xsZVAEPxilMaAatROefVKE3ZfaNgIfjMgZ1EDQzi95PxuuinzLgPriGkZ0EzcyvYxo+5f2VAR/BwhrQL7ifdMaiW43+DqyrAh6De1Q+sxNYiqx3/XEIcyrAPyLaF5PfKsWMw8HiCgdRtnjHxWmNib+JyL6RbMamMuAo11v0rjBehgMNvq/CrgrbaE1bYPmMp+N8Ta9m4UwDnfgWz69KRuNhsdR/4miN4Urs6WDGYTY7Cy6f8WrsrAG+g/GIqc5Ld9zFqBr7FdhbzLiuR5nP41iS5iH+G4CDNVOd2QbG7iLwfByriTj7HBnHs+uRsqx7x5ndPETg7Z5TrLZYkz9TJqat2A44bu2LVLoy8Li1sfhjqiPbD0mpF8bhj7QaGwX+DEOzm11cIPFGYysV5QYWpCVRVZHI6G/EBixK9DZ63CpF4N9TNjGlmbxMGZWetxKAYCa30Tf37b/EWtoIQtmROIxFOaN3mIl/m2x+3O9ziCuXyT4sz9uXAUekVwpv8j3+wv5cb4txxKoN5+sRfc/kFqYifLRJIyIQZO4SBha8x5U5i4t4kM5h3O1YtzPQs6D/JBGBaFc7qaI+0avTaVM1WeV2ahfSUvku6pN5+C2dsjVNHPvMJsjeFmwvOZ+1pS5mGDs4ehfHPj80eb0oZ5CEYBudprfFAGI1TkHw7GChsVSC0D9KG+mHE/qO9LjSpo7Q/3DAzOE3IyR2Hx7JfUAAAAAASUVORK5CYII="/></i></a></li>
                                    <li><a href="/ReservaDeVuelos">Home<i class='bx bxs-plane-alt'><img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAAAAXNSR0IArs4c6QAAAjNJREFUSEvt1kmojlEYwPHfNQ8ZFliZN0pZSUjKRrKUoSgZMyVys1BSlJ2FUrIwZSdDSLFRspGhrFAIGRY2RIlk7Lmdq9frfN/3vt1Pd+Ns3uGc5/k/5zzT6dBLo6OXuP6D23nyU/EG73JK/8VR98MxrMJXrMC5Mrzd4P64hIUF0EeMw/siPAceg9F4hs81zn5wgs7PyKzFyWbgkbiFKWlR+OclXqRn+T18+BNDcRVzM9APmIB4/h7lHW/DQfSpuNPw4au0dnJG5geW4XwVH4/HLqzBoIoG5JZ9x1JcqBvV4edObMaImgY0hYauKlEd0IDvQARelbEcp5strALulo9jj+jciUkt6BGcj9sFDj0DcRm5lClyYv5au8CRp1cwr8JZ/5W3VaI6p7cONOT3Yl+dHY/FITzBQzzA85QSueIQxeM+ppUgx7G+Kjiqy41UZSqcZlfFWom7eFQSCP82jYNiVA9PVkb1CiNajXU4kRaF74uNISK6u+xm9eTSqS8Wp+IxswF9C44U5iLgrhe+vyE6VcPRKo/nYA8WFDTE9/6MxjuYUfg/Cm8bkVuBQ25IukkMS0pW41RG4RKcLfyfjns9AYfsgVSx4v0wtmYURkd7iolpbhEu9hQcaRY9OZTfxqwGCjcVfL89pWbl4Gpk5JnU5r6k449eWx4D8DrdYMoB+MfaKj7uFpiNmziKjenmkTNyd0rHDT2J6rJstMdiGuV0xzXoUxPDumTq7LjZBmrP9Rr4FzmMXx+LW5mDAAAAAElFTkSuQmCC"/></i></a></li>
                                    <li><a href="#">Blog<i class='bx bx-edit'><img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAAAAXNSR0IArs4c6QAAAidJREFUSEvNls1LVkEUhx8jWkubsAQ/ymXROkXTjRH9L5l9LEwwUYvS1Ij+lKBVplktclHQIkhDwSRr1aKFqCU/OVcO473vndFXXmd15+s88ztzztxTR41aXY24HDtwPXAJOBHpkY/AX1t7BngO9AJvgZvAUmgnT/Et4AlwMhL6DOhz0PdAq9v7G7gCLHp7IfgcsBoJ1LIpoN/WnwVmgQvWnwG67Xsd6PDwENxpm7Veyj9VOMQWMG/zOrCg560/bftlY9LG3hl8txuCrwI6qZpO+yZCfQj9DFx2+14C14Ftf32HBQs6F9ypv4LbwES1FYdKFclSlrn7NdBj0F8WYHvRfVDFgurOmsywVN0FGi2Fmp2rFViK6u+VojrmjgVVfraYoUfAgDP6GLjnlCqav5XlcRm4SGlm9w4w7lJon9JsYYqr5ValTOjeGKgEbQAfUsGC6U6lWG0YeODcp++hAqWCvgKu+fSMUay3d8ECR7YHgTEHHbExDf0Autzb3AZ8AU6F70IM2N/7Q+C+g466vqAKpGU3XxgzqWD/mimIFEyZ0nZgJYjeqoGz9/sGoFcpg4ZKM37VwGE6yr15So8UXAYV/FCKVY34v02m5ivwM3RBNe+4xHbF6WjFKYVAzIHkKVUpaspv/UJ3W5hODcBajMXENf8B2dafKhesQRVuTxMqzLIzbFoZ9MIvLKqrTwMXczxSBgnn/wEqhf6EE8euoE9Vlry+Zop3ADmsmx8bXzHGAAAAAElFTkSuQmCC"/></i></a></li>
                                </ul>
                            </div>
                            <div class="page-direction-button">
                                <a href="contact.html"><i class="fa fa-phone"></i>Cont�ctanos Ahora</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-5 col-md-offset-1">
                        <section id="first-tab-group" class="tabgroup">
                            <div id="tab1">
                                <div class="submit-form <%= (mensaje != null) ? mensaje.get(0) : "" %>">
                                    <h4>Verificar por la direcci�n de tu viaje:</h4>
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
                                            <div id="desde" class="col-md-6">
                                                <fieldset  id="desde">
                                                    <label for="from">Origen:</label>
                                                    <select required name='from' onchange='this.form.()'>
                                                        <option value="">Seleccionar lugar...</option>

                                                        <% for(Pais j : paises){ %>
                                                        <option value="<%= j.getPaisNombre() %>"><%= j.getPaisNombre() %></option>
                                                        <% } %>

                                                    </select>
                                                </fieldset>
                                            </div>
                                            <div id="a" class="col-md-6">
                                                <fieldset   class="">
                                                    <label for="to">Destino:</label>
                                                    <select required  name='to' onchange='this.form.()'>
                                                        <option value="" >Seleccionar lugar...</option>

                                                        <% for(Pais j : paises){ %>
                                                        <option value="<%= j.getPaisNombre() %>"><%= j.getPaisNombre() %></option>
                                                        <% } %>
                                                    </select>
                                                </fieldset>
                                            </div>
                                            <div id="fechaSalida" class="col-md-6">
                                                <fieldset>
                                                    <label for="departure">Fecha de Ida:</label>
                                                    <input name="deparure" type="text" class="form-control date" id="deparure" placeholder="Selecciona Fecha"  onchange='this.form.()' required>
                                                </fieldset>
                                            </div>
                                            <div id="fechaRegreso" class="col-md-6">
                                                <fieldset >
                                                    <label for="return">Fecha de Vuelta:</label>
                                                    <input name="return" type="text" class="form-control date" id="return" placeholder="Selecciona Fecha"  onchange='this.form.()' required>
                                                </fieldset>
                                            </div>

                                            <div if="pasajerosTotal" class="col-md-6">
                                                <fieldset>
                                                    <label for="pasajero">Pasajeros:</label>
                                                    <input name="pasajeros" min="1" max="4" type="number" id="pasajero" class="form-control pasajero" placeholder="Inserta el numero de personas a viajar" required/>
                                                </fieldset>
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
                            <h2>Consulta el clima para los proximos 5 d�as</h2>
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
                                            <li><a href="#wednesday">Mi�rcoles</a></li>
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
                            <h4>Easy Tooplate</h4>
                            <p>Donec varius porttitor iaculis. Integer sollicitudin erat et ligula viverra vulputate. In in quam efficitur, pulvinar justo ut, tempor nunc. Phasellus pharetra quis odio.</p>
                            </a>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="service-item second-service">
                            <div class="service-icon"></div>
                            <h4>Unique Ideas</h4>
                            <p>Cras ligula diam, tristique at aliquam at, fermentum auctor turpis. Proin leo massa, iaculis elementum massa et, consectetur varius dolor. Fusce sed ipsum sit.</p>
                            </a>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="service-item third-service">
                            <div class="service-icon"></div>
                            <h4>Best Support</h4>
                            <p>Fusce leo dui. Mauris et justo eget arcu ultricies porta. Nulla facilisi. Nulla nec risus sit amet magna hendrerit venenatis. Sed porta tincidunt lectus eget ultrices.</p>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </section>



        <div class="tabs-content" id="recommended-hotel">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="section-heading">
                            <h2>Recomendaci�n de Aerolineas</h2>
                        </div>
                    </div>
                    <div class="wrapper">
                        <div class="col-md-4">
                            <ul class="tabs clearfix" data-tabgroup="third-tab-group">
                                <li><a href="#livingroom" class="active">Living Room <i class="fa fa-angle-right"></i></a></li>
                                <li><a href="#suitroom">Suit Room <i class="fa fa-angle-right"></i></a></li>
                                <li><a href="#swimingpool">Swiming Pool <i class="fa fa-angle-right"></i></a></li>
                                <li><a href="#massage">Massage Service <i class="fa fa-angle-right"></i></a></li>
                                <li><a href="#fitness">Fitness Life <i class="fa fa-angle-right"></i></a></li>
                                <li><a href="#event">Evening Event <i class="fa fa-angle-right"></i></a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md-8">
                        <section id="third-tab-group" class="recommendedgroup">
                            <div id="livingroom">
                                <div class="text-content">
                                    <iframe width="100%" height="400px" src="https://www.youtube.com/embed/rMxTreSFMgE">
                                    </iframe>
                                </div>
                            </div>
                            <div id="suitroom">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div id="owl-suiteroom" class="owl-carousel owl-theme">
                                            <div class="item">
                                                <div class="suiteroom-item">
                                                    <img src="img/suite-02.jpg" alt="">
                                                    <div class="text-content">
                                                        <h4>Clean And Relaxing Room</h4>
                                                        <span>Aurora Resort</span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="item">
                                                <div class="suiteroom-item">
                                                    <img src="img/suite-01.jpg" alt="">
                                                    <div class="text-content">
                                                        <h4>Special Suite Room TV</h4>
                                                        <span>Khao Yai Hotel</span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="item">
                                                <div class="suiteroom-item">
                                                    <img src="img/suite-03.jpg" alt="">
                                                    <div class="text-content">
                                                        <h4>The Best Sitting</h4>
                                                        <span>Hotel Grand</span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div id="swimingpool">
                                <img src="img/swiming-pool.jpg" alt="">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="text-content">
                                            <h4>Lovely View Swiming Pool For Special Guests</h4>
                                            <span>Victoria Resort and Spa</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div id="massage">
                                <img src="img/massage-service.jpg" alt="">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="text-content">
                                            <h4>Perfect Place For Relaxation</h4>
                                            <span>Napali Beach</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div id="fitness">
                                <img src="img/fitness-service.jpg" alt="">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="text-content">
                                            <h4>Insane Street Workout</h4>
                                            <span>Hua Hin Beach</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div id="event">
                                <img src="img/evening-event.jpg" alt="">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="text-content">
                                            <h4>Finest Winery Night</h4>
                                            <span>Queen Restaurant</span>
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




        <section id="most-visited">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="section-heading">
                            <h2>Los Paises M�s Visitados</h2>
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

        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js" type="text/javascript"></script>
        <script type="text/javascript">
            $(document).ready(function() {



                // navigation click actions
                $('.scroll-link').on('click', function(event){
                    event.preventDefault();
                    var sectionID = $(this).attr("data-id");
                    scrollToID('#' + sectionID, 750);
                });
                // scroll to top action
                $('.scroll-top').on('click', function(event) {
                    event.preventDefault();
                    $('html, body').animate({scrollTop:0}, 'slow');
                });
                // mobile nav toggle
                $('#nav-toggle').on('click', function (event) {
                    event.preventDefault();
                    $('#main-nav').toggleClass("open");
                });
            });
            // scroll function
            function scrollToID(id, speed){
                var offSet = 0;
                var targetOffset = $(id).offset().top - offSet;
                var mainNav = $('#main-nav');
                $('html,body').animate({scrollTop:targetOffset}, speed);
                if (mainNav.hasClass("open")) {
                    mainNav.css("height", "1px").removeClass("in").addClass("collapse");
                    mainNav.removeClass("open");
                }
            }
            if (typeof console === "undefined") {
                console = {
                    log: function() { }
                };
            }
        </script>
        <script src="js/scripts/app.js"></script>
    </body>
</html>