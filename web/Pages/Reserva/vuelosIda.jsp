<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Modelo.Pasajeros"%>
<%@page import="Modelo.CarteleraViajes"%>
<%@page import="Modelo.Aerolineas"%>
<%@page import="Modelo.Pasajes"%>
<%@page import="ModeloDAO.PasajesDAO"%>
<%@page import="ModeloDAO.AerolineasDAO"%>
<%@page import="ModeloDAO.PaisDAO"%>
<%@page import="Modelo.Vuelos"%>
<%@page import="ModeloDAO.VuelosDAO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Modelo.Pais"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    	<title>Vuelos - Viaje y mas</title>
    
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
        <link rel="stylesheet" href="css/modificado/vuelos.css"/>
        <link href="css/modificado/style.css" rel="stylesheet"/>

    </head>
    <body>
        <%
            List<Object> informacion = (ArrayList) request.getAttribute("InformacionPasajeros");
            
            List<String> infoViaje = (ArrayList) informacion.get(0);
            String paisOrigen = infoViaje.get(0);
            String paisDestino = infoViaje.get(1);
            String ciudadOrigen = infoViaje.get(2);
            String ciudadDestino = infoViaje.get(3);
            String pTotal = infoViaje.get(4);
            String fechaIda = infoViaje.get(5);
            int mesIda = Integer.parseInt(fechaIda.split("/")[0]);
            
            informacion.remove(0);
            List<Pasajeros> infoPasajeros = (ArrayList) informacion;
            
            
            VuelosDAO vd = new VuelosDAO();
            List<CarteleraViajes> cv = vd.listadoCarteleraViajes();
            List<CarteleraViajes> viajesIda = new ArrayList<>();
                        
            for(CarteleraViajes j : cv){
                if(Integer.parseInt(j.getFecha().split("-")[1]) == mesIda){
                    if(j.getPaisOrigen().equals(paisOrigen) && j.getPaisDestino().equals(paisDestino)){
                        viajesIda.add(j);
                    }
                }
            }
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
                                <a href="contact.html"><i class="fa fa-phone"></i>Contáctanos Ahora</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-5 col-md-offset-1">
                        <section id="first-tab-group" class="tabgroup">
                            <div id="tab1">
                                <div class="submit-form">
                                    <h4>Completar Viaje</h4>
                                    <p>Escoge una de las Aerolineas para tu viaje de Ida.</p>
                                </div>
                            </div>
                        </section>
                    </div>
                </div>
            </div>
        </section>
        
        <section class="vuelos">
            <div class="accordion" id="accordionExample">
                <div class="accordion-item">
                    <h2 class="accordion-header">
                      <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                        Eligue tu vuelo de ida: <%= paisOrigen %> a <%= paisDestino %>
                      </button>
                    </h2>
                
                    <div id="collapseOne" class="accordion-collapse collapse show" data-bs-parent="#accordionExample">
                        <!-- Vamos ir verificando por los diferentes vuelos que pueda existir...  -->
                        <div class="accordion-body">
                            <div class="barra_informacion">
                                <h2 class="barra_informacion--titulo"><%= paisOrigen %> a <%= paisDestino %></h2>
                                <ul class="listado_info">
                                    <li><box-icon type='solid' name='plane-take-off'><img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAAAAXNSR0IArs4c6QAAAZlJREFUSEvt1D1rVFEQxvHfakAQO0mRKBjBb2FjIIKkMGBjY2HpZ7AQhIiFdWwENUYRsRcCgdionY2VosQXiJVpYqPEFwbOhruH+7K77rpb7MAt7r3nzH+e58yZlhFFa0RcE/B/c35idW71QVzEFl4N8hzqrD6L2ziVgG+xmp7tfy2iDnwEt3Alg/zBBh7gKX5UFHEAp3EeJ3Edb9pru2muedzDiRLAdzxKRbzEISzgApZwtLBnHed6AcfaKvXFWj5gBocrHPhcLL4bxcU8oT4sPt7HGd/E1SrF0/iG3zWJQ/0TLHYB/4VnWEFYvR9FxcfwAntYTsryAqJJruESpmrAr1P3P8RO2boiOJonwFFAxHvcSAXEvybg19Rod/CuyY38jOcSfLaw8QviPYZJHj/TlYrODivrjqhjb1lz5crLio+rE8PkMXab1DVZXfxfBv+ENdxNI7QfXmlz5YnC9rAvZnRcoU3E1BpI9HqPBwKNJGMDDnsvD0xWZ6L7+Nj+lCs+k85yGOwYt8/HDjwMpaU5x6a5JoqH5sBfXZNFHxeyvKAAAAAASUVORK5CYII="/></box-icon><p><span>Fecha de Ida:</span> <%= fechaIda %></p></li>
                                    
                                    <li><box-icon name='male-female'><img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAAAAXNSR0IArs4c6QAAAclJREFUSEvt179LlWEUB/CPOITQYA5FW2ODDYnYIlRD5BC52qTgn+DQ0FAt4uDemLRUY0ERLtXQkpGDs5Q4RZA5FoJyLveFh9f31+2+cRE8cLmX5z3n+T7v93y/5+EOGVAMDQhXFfBdXMUmXvdwwEVcxDt8KasrA36IR0lR/H7cAPwV4sBZ3MGboroy4D2MJgW/ca4GeBIbuZxgavZEAA+M6mAnejWBr/9bXNHvKxir6OUvbOEwl3MGUxguqN3Gbn49FdccnjdQ7j28yOWFdW6X1P7FZXxLn6fAYZnobV2ErVKrhfqDiaqZ8ADLbQM3YeoTptsGfoqFGppCEzEH9rO8Nqj+gfN1/UEw87It4PGuyhvgeob5toCXsNoEFT9xIbNiv1Sv41ZD4EgLr3fmeb/A9zGCGVyrOMAK/uAtPueBQ5mZOq8XbPKxu7aG+KTxAUU1Wc4NZPWdtTLT50diVW6My7BJfJfFsfu8DeB4m/c1fQ5GbpYNkHS9lzduMmqjv2dxUGSnfwWOwRCXwKXUp93NdhI9PEEMm9Z6nAooT3kIKlpxLNro8SlwSmvPVKcXfbZR0VoKEuLKX4/fC4ZNpbhqbNn/44H9dzoCcmBpH84v/EIAAAAASUVORK5CYII="/></box-icon><p id="<%= pTotal %>" class="numeroPasajeros"><span>Número de Pasajeros:</span><%= pTotal %></p></li>
                                    <li>
                                        <div class="carrito_total">
                                            <p><box-icon type='solid' name='cart'><img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAAAAXNSR0IArs4c6QAAAblJREFUSEvN1j1oFGEQxvFfxEbBIAhiKViJTZqQJo1WFmKhSRM7QRPBlIJpjKm0sBLEj8bKVLFMqSnEQtBCCCRFTKGIgo0kpIgBDQO7sC738e7tnufANbcz8995ZuZ9d8iAbGhAXP8F+CTiF7aND/1Uo1jxXcwXYBs4j0/9eIFO4OB9xEi/wUWpL2E2A47jbdPwdsN1DN9xEIu48q/AwXmBKfzCRezWhH/GZp6j0zqFxG9qwsrhE3gZf3bb41WcaRB+Fc9TwNN40hB4D8fxMwV8GN8w3AB8CZMpPc59HhZWqw7/ApargE8hTrE69gMn8LsKOHxf4VwN8gPcKsZ3m+rc9zKiR73aaaz3Aj6Ar5lcVeHvMVoOSq044u5goSoVN/GoDjiG40t2fqfy/9rdXqTOY57hWioVTzHTyr+K1BEfvY5+HUqA7yD6+6cJcOS4jqN4nH0ilfMewY3saAyFWlrViu/hdpZppc1uv8bZzOc+5pqouJg0JAzpyxanU15Qu5frei2Wk4bMMTBhIWPcXmWL5+EXFs9byl1V6kgWp1DcVu86DNgYtrDWVI8ThjnNpZeK0zJ38RoYeB9k9EEfWOi2dAAAAABJRU5ErkJggg=="/></box-icon><span>USD</span></p>
                                            <p id="tarifa">0.0</p>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                                    
                            <div class="listado_vuelos">
                                <% if(viajesIda.size() == 0){ %>
                                   <div class="vuelo">
                                        <p class="mensaje_vuelos">Sin vuelos disponibles...</p>
                                   </div>
                                   <div class="regresar">
                                        <a class="btn btn-primary rgrs" href="ReservaControllers?accion=reservar&to=<%= paisDestino %>&from=<%= paisOrigen %>&deparure=<%= fechaIda %>&pasajeros=<%= pTotal %>&OpcionVuelo=Ida" role="button">Regresar</a>
                                   </div>
                                <% }else{ %>
                                    <% for(CarteleraViajes j : viajesIda){ %>

                                    <div class="vuelo" id="id_<%= j.getId() %>">
                                        <section class="seccion1">
                                            <div class="boton-confirmar" id="<%= j.getId() %>">
                                                <button class="boton boton_confirmar fly-from" id="vuelo_desde_<%= pTotal %>">Confirmar Vuelo</button>
                                                <button class="boton boton_cancelar" id="<%= j.getId() %>">Cancelar Vuelo</button>
                                            </div>
                                            <div class="hora">
                                                <p class="fecha_salida"><%= j.getHoraSalida().split(":")[0] %>:<%= j.getHoraSalida().split(":")[1] %><span><%= j.getPaisOrigen() %></span><span style="font-weight:lighter;font-size:1.4rem;display:block;text-align:center;">Ciudad: <strong><%= ciudadOrigen %></strong></span></p>
                                                <p class="fecha_llegada"><%= j.getHoraLlegada().split(":")[0] %>:<%= j.getHoraLlegada().split(":")[1] %><span><%= j.getPaisDestino() %></span><span style="font-weight:lighter;font-size:1.4rem;display:block;text-align:center;">Ciudad: <strong><%= ciudadDestino %></strong></span></p>
                                            </div>
                                            <div class="fecha">
                                                <p>Fecha Disponible <span><%= j.getFecha() %></span></p>
                                            </div>
                                            <div class="aerolinea">
                                                <p>Aerolinea: <span><%= j.getAerolinea() %></span></p>
                                            </div>
                                            <div class="seleccion">
                                                <div class="form-check">
                                                    <input class="form-check-input fly_from" type="radio" name="aerolineaIda-<%= j.getId() %>" value="true" id="flexRadioDefault1" >
                                                    <label class="form-check-label" for="flexRadioDefault1">
                                                        <p>Quiero <%= j.getAerolinea() %></p>
                                                    </label>
                                                </div>
                                                <div class="form-check">
                                                    <input class="form-check-input fly_from" type="radio" name="aerolineaIda-<%= j.getId() %>" value="false" id="flexRadioDefault2" checked>
                                                    <label class="form-check-label" for="flexRadioDefault2">
                                                        <p>No Quiero <%= j.getAerolinea() %></p>
                                                    </label>
                                                </div>
                                            </div>
                                        </section>
                                        <section class="seccion2">
                                            <p>Desde <span class="simbolo">USD</span><span class="precio" id="tarifa-<%= j.getId() %>"><%= j.getTarifa() %></span></p>
                                        </section>
                                        
                                        <!-- Este listado clave vuelos nos permitira hacer escoger al cliente que tipo de vuelo desea poder realizar. -->
                                        <div class="listado_claseVuelo <%= j.getId() %>" id="listado_claseVuelo">
                                            <!-- Vamos a ingresar por el tipo de vuelo... de clase  -->
                                            <h2 class="listado_claseVuelo_heading">escoge como deseas volar</h2>
                                            <div class="clases">

                                                <div class="card" id="clase-turista">

                                                  <div class="card-body">
                                                    <h5 class="card-title titulo-clase">clase turista</h5>
                                                    <p class="subtitulo">Práctico y ligero</p>
                                                    <p class="precio"><span>USD</span> 100,00</p>
                                                    <ul>
                                                        <li><box-icon type='solid' name='backpack'><img width="20" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAABnElEQVRIS9WWgU3DMBBF2w1gg7IBTEDYADYoEwATUCYANsgGsAFhAtiAbEA3KP9JNrq4dmNHRhEnnaKml3s++/vs5WImW87EXUwBn2qwjfzIDXqr56e8KymiFPyo5LcJQKv317nwEjBAwIfsQX9ucuAl4C8lXMl7+ZWbXhi8e3NPpv24NnjnEj7peRckp8p79y6rmKwgJWxcVeTu5O8B+NzF8PrMzEay+DHwWl/eyFFyiaHyZ3mb+igFZqswdSkF5w7iVYHoYc9S4EPbJhfq46LwGLjRF6i0pl0oWWcTxsAvCrisSVUu1hzR/VoM7PdrTfbe/g7BqPejJtHkGmyzEBxbX0aLQNgevZzfKWPgLJNvJjZusM45YLoU3arEgKOVyWArCmaDivxxGBtI62aF/1gy23iKKrZ9OUwUA1sRhb2gCGyPOX9IjE25X75NsNb/D8wa51jngqpVnAO1MdXAs1X8p+Jij36b+aqp6hOzx6P3ans61QLTWAZX39RFYK3AlRyFWpXmCAxRYWgC7+X0+kGPH7tz5YAmxcwG/gHLSGYfoQsxKgAAAABJRU5ErkJggg=="/></box-icon> 1 Equipaje de Mano (10kg) + bolso</li>
                                                        <li><box-icon name='x'><img width="20" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAAA/klEQVRIS+2V3Q3CMAyE2w0YgRXYACagbAATISZgBNiAjsAojMCd1EhRlfgnES0PqeSnJP58Fzvtu5W+fiVu18CLOd+s/iurN6jmjrghRqWyM9aPiJOmwHLHDyQZpkQHAU7oFcFCnxrcAt4jyStSkILH0A/2XiZ4VrgFzMMS3A1lQis4B99G9pqUBgs84BScMN6pC+pVHIqd2+6GloLjOw3FSN2ebDCv1fNGos1FcA841b20WRu1KsXSyEijVjXHljl1wzWreYd8MplY694Y/sb+XVYuFjQwzwY4fxJ8g6WPcL7X7HIWWmW1wipbtiguy6ycauCf2JpK2qxezOovXik5H5avpD8AAAAASUVORK5CYII="/></box-icon> Abordaje Prioritario</li>
                                                        <li><box-icon name='x'><img width="20" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAAA/klEQVRIS+2V3Q3CMAyE2w0YgRXYACagbAATISZgBNiAjsAojMCd1EhRlfgnES0PqeSnJP58Fzvtu5W+fiVu18CLOd+s/iurN6jmjrghRqWyM9aPiJOmwHLHDyQZpkQHAU7oFcFCnxrcAt4jyStSkILH0A/2XiZ4VrgFzMMS3A1lQis4B99G9pqUBgs84BScMN6pC+pVHIqd2+6GloLjOw3FSN2ebDCv1fNGos1FcA841b20WRu1KsXSyEijVjXHljl1wzWreYd8MplY694Y/sb+XVYuFjQwzwY4fxJ8g6WPcL7X7HIWWmW1wipbtiguy6ycauCf2JpK2qxezOovXik5H5avpD8AAAAASUVORK5CYII="/></box-icon> Cambios de Vuelo</li>
                                                        <li><box-icon name='x'><img width="20" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAAA/klEQVRIS+2V3Q3CMAyE2w0YgRXYACagbAATISZgBNiAjsAojMCd1EhRlfgnES0PqeSnJP58Fzvtu5W+fiVu18CLOd+s/iurN6jmjrghRqWyM9aPiJOmwHLHDyQZpkQHAU7oFcFCnxrcAt4jyStSkILH0A/2XiZ4VrgFzMMS3A1lQis4B99G9pqUBgs84BScMN6pC+pVHIqd2+6GloLjOw3FSN2ebDCv1fNGos1FcA841b20WRu1KsXSyEijVjXHljl1wzWreYd8MplY694Y/sb+XVYuFjQwzwY4fxJ8g6WPcL7X7HIWWmW1wipbtiguy6ycauCf2JpK2qxezOovXik5H5avpD8AAAAASUVORK5CYII="/></box-icon> Seleccion de asiento</li>
                                                        <li><box-icon name='x'><img width="20" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAAA/klEQVRIS+2V3Q3CMAyE2w0YgRXYACagbAATISZgBNiAjsAojMCd1EhRlfgnES0PqeSnJP58Fzvtu5W+fiVu18CLOd+s/iurN6jmjrghRqWyM9aPiJOmwHLHDyQZpkQHAU7oFcFCnxrcAt4jyStSkILH0A/2XiZ4VrgFzMMS3A1lQis4B99G9pqUBgs84BScMN6pC+pVHIqd2+6GloLjOw3FSN2ebDCv1fNGos1FcA841b20WRu1KsXSyEijVjXHljl1wzWreYd8MplY694Y/sb+XVYuFjQwzwY4fxJ8g6WPcL7X7HIWWmW1wipbtiguy6ycauCf2JpK2qxezOovXik5H5avpD8AAAAASUVORK5CYII="/></box-icon> Check-in en aeropuerto</li>
                                                        <li><box-icon name='x'><img width="20" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAAA/klEQVRIS+2V3Q3CMAyE2w0YgRXYACagbAATISZgBNiAjsAojMCd1EhRlfgnES0PqeSnJP58Fzvtu5W+fiVu18CLOd+s/iurN6jmjrghRqWyM9aPiJOmwHLHDyQZpkQHAU7oFcFCnxrcAt4jyStSkILH0A/2XiZ4VrgFzMMS3A1lQis4B99G9pqUBgs84BScMN6pC+pVHIqd2+6GloLjOw3FSN2ebDCv1fNGos1FcA841b20WRu1KsXSyEijVjXHljl1wzWreYd8MplY694Y/sb+XVYuFjQwzwY4fxJ8g6WPcL7X7HIWWmW1wipbtiguy6ycauCf2JpK2qxezOovXik5H5avpD8AAAAASUVORK5CYII="/></box-icon> Equipaje de bodega (23kg)</li>
                                                        <li><box-icon name='x'><img width="20" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAAA/klEQVRIS+2V3Q3CMAyE2w0YgRXYACagbAATISZgBNiAjsAojMCd1EhRlfgnES0PqeSnJP58Fzvtu5W+fiVu18CLOd+s/iurN6jmjrghRqWyM9aPiJOmwHLHDyQZpkQHAU7oFcFCnxrcAt4jyStSkILH0A/2XiZ4VrgFzMMS3A1lQis4B99G9pqUBgs84BScMN6pC+pVHIqd2+6GloLjOw3FSN2ebDCv1fNGos1FcA841b20WRu1KsXSyEijVjXHljl1wzWreYd8MplY694Y/sb+XVYuFjQwzwY4fxJ8g6WPcL7X7HIWWmW1wipbtiguy6ycauCf2JpK2qxezOovXik5H5avpD8AAAAASUVORK5CYII="/></box-icon> Reembolso no permitido</li>
                                                    </ul>

                                                    <a href="#" id="boton-claseTurista" class="botonClase <%= j.getId() %>">Seleccionar</a>
                                                    <p class="enunciado">precio por pasajero</p>
                                                  </div>
                                                </div>

                                                <div class="card" id="clase-primera">

                                                  <div class="card-body">
                                                    <h5 class="card-title titulo-clase">Primera Clase</h5>
                                                    <p class="subtitulo">¡Más Flexibilidad!</p>
                                                    <p class="precio"><span>USD</span> 350,00</p>
                                                    <ul>
                                                        <li><box-icon name='coin-stack'><img width="20" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAABd0lEQVRIS+2W21HDMBBFkw4owXQAFcRUAHQQOiAVMFQAHQAVABUAFQAdpAPoAO6Z2Z1R5Mh68OEPvDM768fKR/uwpOViIllOxF3Ugo800c4m6/Zb91sp9qM0kBJwr4+dSrGAxwT4k/TZbNJ3DAzkSnpWGkXkR/Tnlo3BJ1JgoC/SAxtBJK/ST7OeXl7j05mSmXiiJzZmB54CPwYf2Oj6XgqsVC7leGPOW9nDeGAK/GWRUC/S1SLh5AecFPgnIBHxbQWZ1K+l9IeXqgkM07v1Tdc0jdfYP9zpGUqN6Y+4+5vBFQHvda0GX1uEREL6cuLd/yDHlZQmQ6rB1JYau/jK5b+Qd3qYfn9GVzeDAc7NlavzvvfVNZ6bqyTN88rVtID8n+Z6D3aYv65cLKfHcVem9uNejhx9XFq3RcZdSDlQ7EjusHcXRF7yK4U+RAoUO5CS4+1ao9jisDkhQkBsi0SZPKeVgENYrxsWiJJtcXSSteBcxMXvJwP/AgeGgB+Z/ftsAAAAAElFTkSuQmCC"/></box-icon>Reembolso Permitido</li>
                                                        <li><box-icon name='street-view'><img width="20" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAAB40lEQVRIS92WgVHDMAwA2w1gg7ABTECYAJiAMAEwAWUDmIAwAWxANgAmIBvABqDvyTmd6zSW26N36E7n2LX1kizbnc92JPMdcWel4D1xuBalbUucLwEDexU9VOC3tPteeAm4EchjBLqR/r0HXgI+E8BzBLn0prwEnEr1gYBJebaUgIPxa/2guFxQ1nnBRFtpNcfRddnhOsFUMXsLOCVEfS6a5YAn4jcxGo7QWHDZR8sD/slMZZbNrEkK/N9gqvdW9EG0L4wYGxy5RWqLUqmmaqleCqkV5VZCvKkGiPPvaoN2kBgMlAeAFmHyiSjVWuvYhbSNNSLfreiTjnXa2lPAeuwM8BjM5R+Mvqin8a20kHEisXInHcZjsfZwCPhSLJjU4iXCS8OLkxIPmPUWfiT9ZdQW3Ogkxrn0+y2BKbIvtTU8nxZsn7s/BVfi1ad6xv5y724j1XZrsIntldfJVuLYPteyDrXSSQe1Eu6C8Hzye7K4WMRkoqZFKAQukTYyuq7LWgqVyg8OTh4nDI49f2TgQ7Q3CgSttD2WtjGOB+cpqs56u+6RIEVXatQR8DCVKMkWDsd3QdY/EKr9VCOZcgBA2B4iXAEGA55nkTWklK0I6cVwgIXvUVhuqqei2+h3b8QbweziX0pvah/oL5DzAAAAAElFTkSuQmCC"/></box-icon>Abordaje Prioritario</li>
                                                        <li><box-icon type='solid' name='plane-alt'><img width="20" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAABqElEQVRIS+2V4U0DMQxG2w1ggzIBZQOYANigTFCYgDIBbEA3gA3oBpQJYAMYAT8pllwrdhN0cH+wZF17l/jZ8Xe+6WQkm47EnfyD/+zkf+OoDyT7W/GF+Jf4jfizr2hoMND7ArWsI/nzYW/UwGwm016LoMS59FV7MJsfxS9KhmRJEm8li427anIZlP1n4tusYvoCuNVIjICAT4NNV3J/7Z/Vjnomi1QcrQlE66pQFmfiogpOALH8xELoPrCFXXcmkEJ7wJoECSzFaUdmJ/JwR0wtPc4CZuq1+1DxJgvUM0BaofCYVg9DgHug8O7EVz1geoeKGRgclfapNgY1LmvmDsJsZlqFZo8a6Is4V2tMHiquGeolwXf3kHv0uQnMolbVsta+Mky7haGQ7GEPWNcyq89dMBvHv6cc9esQYI1BQMYniahFigVse03F4Veu9XWih7NCjsAk92QSTIdIKxhV038sU6xNcJBZjao/CzgTDgLTz2o6RForhmmVm+3TqtMh0gNW5a4lCaqJhEPVx2WNafnuzx4wO1fizOBQrSHJPegFt8bdu2408DfCm08fzg0K5AAAAABJRU5ErkJggg=="/></box-icon>Cambios de Vuelo</li>
                                                        <li><box-icon type='solid' name='plane-alt'><img width="20" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAABqElEQVRIS+2V4U0DMQxG2w1ggzIBZQOYANigTFCYgDIBbEA3gA3oBpQJYAMYAT8pllwrdhN0cH+wZF17l/jZ8Xe+6WQkm47EnfyD/+zkf+OoDyT7W/GF+Jf4jfizr2hoMND7ArWsI/nzYW/UwGwm016LoMS59FV7MJsfxS9KhmRJEm8li427anIZlP1n4tusYvoCuNVIjICAT4NNV3J/7Z/Vjnomi1QcrQlE66pQFmfiogpOALH8xELoPrCFXXcmkEJ7wJoECSzFaUdmJ/JwR0wtPc4CZuq1+1DxJgvUM0BaofCYVg9DgHug8O7EVz1geoeKGRgclfapNgY1LmvmDsJsZlqFZo8a6Is4V2tMHiquGeolwXf3kHv0uQnMolbVsta+Mky7haGQ7GEPWNcyq89dMBvHv6cc9esQYI1BQMYniahFigVse03F4Veu9XWih7NCjsAk92QSTIdIKxhV038sU6xNcJBZjao/CzgTDgLTz2o6RForhmmVm+3TqtMh0gNW5a4lCaqJhEPVx2WNafnuzx4wO1fizOBQrSHJPegFt8bdu2408DfCm08fzg0K5AAAAABJRU5ErkJggg=="/></box-icon> Asiento Plus(sujeto a disponibilidad)</li>
                                                        <li><box-icon type='solid' name='backpack'><img width="20" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAABnElEQVRIS9WWgU3DMBBF2w1gg7IBTEDYADYoEwATUCYANsgGsAFhAtiAbEA3KP9JNrq4dmNHRhEnnaKml3s++/vs5WImW87EXUwBn2qwjfzIDXqr56e8KymiFPyo5LcJQKv317nwEjBAwIfsQX9ucuAl4C8lXMl7+ZWbXhi8e3NPpv24NnjnEj7peRckp8p79y6rmKwgJWxcVeTu5O8B+NzF8PrMzEay+DHwWl/eyFFyiaHyZ3mb+igFZqswdSkF5w7iVYHoYc9S4EPbJhfq46LwGLjRF6i0pl0oWWcTxsAvCrisSVUu1hzR/VoM7PdrTfbe/g7BqPejJtHkGmyzEBxbX0aLQNgevZzfKWPgLJNvJjZusM45YLoU3arEgKOVyWArCmaDivxxGBtI62aF/1gy23iKKrZ9OUwUA1sRhb2gCGyPOX9IjE25X75NsNb/D8wa51jngqpVnAO1MdXAs1X8p+Jij36b+aqp6hOzx6P3ans61QLTWAZX39RFYK3AlRyFWpXmCAxRYWgC7+X0+kGPH7tz5YAmxcwG/gHLSGYfoQsxKgAAAABJRU5ErkJggg=="/></box-icon> 1 equipaje de bodega(23kg)</li>
                                                        <li><box-icon type='solid' name='backpack'><img width="20" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAABnElEQVRIS9WWgU3DMBBF2w1gg7IBTEDYADYoEwATUCYANsgGsAFhAtiAbEA3KP9JNrq4dmNHRhEnnaKml3s++/vs5WImW87EXUwBn2qwjfzIDXqr56e8KymiFPyo5LcJQKv317nwEjBAwIfsQX9ucuAl4C8lXMl7+ZWbXhi8e3NPpv24NnjnEj7peRckp8p79y6rmKwgJWxcVeTu5O8B+NzF8PrMzEay+DHwWl/eyFFyiaHyZ3mb+igFZqswdSkF5w7iVYHoYc9S4EPbJhfq46LwGLjRF6i0pl0oWWcTxsAvCrisSVUu1hzR/VoM7PdrTfbe/g7BqPejJtHkGmyzEBxbX0aLQNgevZzfKWPgLJNvJjZusM45YLoU3arEgKOVyWArCmaDivxxGBtI62aF/1gy23iKKrZ9OUwUA1sRhb2gCGyPOX9IjE25X75NsNb/D8wa51jngqpVnAO1MdXAs1X8p+Jij36b+aqp6hOzx6P3ans61QLTWAZX39RFYK3AlRyFWpXmCAxRYWgC7+X0+kGPH7tz5YAmxcwG/gHLSGYfoQsxKgAAAABJRU5ErkJggg=="/></box-icon> 1 equipaje de mano(10kg)+bolso</li>
                                                    </ul>

                                                    <a href="#" id="boton-clasePrimera" class="botonClase <%= j.getId() %>">Seleccionar</a>
                                                    <p class="enunciado">precio por pasajero</p>
                                                  </div>
                                                </div>

                                                <div class="card" id="clase-ejecutiva">

                                                  <div class="card-body">
                                                    <h5 class="card-title titulo-clase">clase ejecutiva</h5>
                                                    <p class="subtitulo">Más equipaje</p>
                                                    <p class="precio"><span>USD</span> 200,00</p>
                                                    <ul>
                                                        <li><box-icon name='street-view'><img width="20" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAAB40lEQVRIS92WgVHDMAwA2w1gg7ABTECYAJiAMAEwAWUDmIAwAWxANgAmIBvABqDvyTmd6zSW26N36E7n2LX1kizbnc92JPMdcWel4D1xuBalbUucLwEDexU9VOC3tPteeAm4EchjBLqR/r0HXgI+E8BzBLn0prwEnEr1gYBJebaUgIPxa/2guFxQ1nnBRFtpNcfRddnhOsFUMXsLOCVEfS6a5YAn4jcxGo7QWHDZR8sD/slMZZbNrEkK/N9gqvdW9EG0L4wYGxy5RWqLUqmmaqleCqkV5VZCvKkGiPPvaoN2kBgMlAeAFmHyiSjVWuvYhbSNNSLfreiTjnXa2lPAeuwM8BjM5R+Mvqin8a20kHEisXInHcZjsfZwCPhSLJjU4iXCS8OLkxIPmPUWfiT9ZdQW3Ogkxrn0+y2BKbIvtTU8nxZsn7s/BVfi1ad6xv5y724j1XZrsIntldfJVuLYPteyDrXSSQe1Eu6C8Hzye7K4WMRkoqZFKAQukTYyuq7LWgqVyg8OTh4nDI49f2TgQ7Q3CgSttD2WtjGOB+cpqs56u+6RIEVXatQR8DCVKMkWDsd3QdY/EKr9VCOZcgBA2B4iXAEGA55nkTWklK0I6cVwgIXvUVhuqqei2+h3b8QbweziX0pvah/oL5DzAAAAAElFTkSuQmCC"/></box-icon>Asiento Economy</li>
                                                        <li><box-icon type='solid' name='backpack'><img width="20" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAABnElEQVRIS9WWgU3DMBBF2w1gg7IBTEDYADYoEwATUCYANsgGsAFhAtiAbEA3KP9JNrq4dmNHRhEnnaKml3s++/vs5WImW87EXUwBn2qwjfzIDXqr56e8KymiFPyo5LcJQKv317nwEjBAwIfsQX9ucuAl4C8lXMl7+ZWbXhi8e3NPpv24NnjnEj7peRckp8p79y6rmKwgJWxcVeTu5O8B+NzF8PrMzEay+DHwWl/eyFFyiaHyZ3mb+igFZqswdSkF5w7iVYHoYc9S4EPbJhfq46LwGLjRF6i0pl0oWWcTxsAvCrisSVUu1hzR/VoM7PdrTfbe/g7BqPejJtHkGmyzEBxbX0aLQNgevZzfKWPgLJNvJjZusM45YLoU3arEgKOVyWArCmaDivxxGBtI62aF/1gy23iKKrZ9OUwUA1sRhb2gCGyPOX9IjE25X75NsNb/D8wa51jngqpVnAO1MdXAs1X8p+Jij36b+aqp6hOzx6P3ans61QLTWAZX39RFYK3AlRyFWpXmCAxRYWgC7+X0+kGPH7tz5YAmxcwG/gHLSGYfoQsxKgAAAABJRU5ErkJggg=="/></box-icon>1 equipaje de bodega(23kg)</li>
                                                        <li><box-icon type='solid' name='backpack'><img width="20" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAABnElEQVRIS9WWgU3DMBBF2w1gg7IBTEDYADYoEwATUCYANsgGsAFhAtiAbEA3KP9JNrq4dmNHRhEnnaKml3s++/vs5WImW87EXUwBn2qwjfzIDXqr56e8KymiFPyo5LcJQKv317nwEjBAwIfsQX9ucuAl4C8lXMl7+ZWbXhi8e3NPpv24NnjnEj7peRckp8p79y6rmKwgJWxcVeTu5O8B+NzF8PrMzEay+DHwWl/eyFFyiaHyZ3mb+igFZqswdSkF5w7iVYHoYc9S4EPbJhfq46LwGLjRF6i0pl0oWWcTxsAvCrisSVUu1hzR/VoM7PdrTfbe/g7BqPejJtHkGmyzEBxbX0aLQNgevZzfKWPgLJNvJjZusM45YLoU3arEgKOVyWArCmaDivxxGBtI62aF/1gy23iKKrZ9OUwUA1sRhb2gCGyPOX9IjE25X75NsNb/D8wa51jngqpVnAO1MdXAs1X8p+Jij36b+aqp6hOzx6P3ans61QLTWAZX39RFYK3AlRyFWpXmCAxRYWgC7+X0+kGPH7tz5YAmxcwG/gHLSGYfoQsxKgAAAABJRU5ErkJggg=="/></box-icon>1 equipaje de mano (10kg) + bolso</li>
                                                        <li><box-icon name='dollar'><img width="20" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAAB20lEQVRIS+WWgU3DMBBF2w1gg7ABTECYANggbAATABMAE1AmACYgnQA2IBvACPwn3UnXpImdtKgSWLLsuPa9++ez3flsR2W+I+5sKriQw5U5vVDbjBUwFVwK9GawE7X1nwWjlHqqemgqP0zxUu1LrvLcUAN5DLA++zhxlRP6HDBQ9nMv0EimIih29T7lSB2c6C054E+DfKt9sHDiREwuIJXqnZGYu78JGCXvZoAQ3lu/bIFrGwfOllAGVacUXwYVB+o3CTCRcNVP6rtDHfEp8JlWPNuqc7WetX2Kh6K78lsKjIIvW8G+AUfFr4NhRtV8k0ieTHzHSGxNsRuq1PGkWWecKOAMWd/k0FOhjjYI+7UqTsQz3ebcauAmBR8DjrZi+Be250WYcKE+471lKriUxfbrxBgnwKPBBUJCri3bBAOIkRh8LlNgDPESUbi5XME6xcyJxy/edB3VKXA0FPetD1yJ4Nk/eMxSYDz1R4I+Gct97S8WY4TUzzVZj7NEhit2oz2OD4WHrFYH1RSghQH5BkZ0/Hr1NSttjmIWACdjAQwVv0QWiXmj/2VWMnisStL5sUFhrfpqKnvDG53JVdwWUGrgf/zLdOXsNWGnsJ9Nak/bv08N9VhOZ/7OwD+TGWgf9fLCkAAAAABJRU5ErkJggg=="/></box-icon> Abordaje prioritario</li>
                                                        <li><box-icon name='x'><img width="20" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAAA/klEQVRIS+2V3Q3CMAyE2w0YgRXYACagbAATISZgBNiAjsAojMCd1EhRlfgnES0PqeSnJP58Fzvtu5W+fiVu18CLOd+s/iurN6jmjrghRqWyM9aPiJOmwHLHDyQZpkQHAU7oFcFCnxrcAt4jyStSkILH0A/2XiZ4VrgFzMMS3A1lQis4B99G9pqUBgs84BScMN6pC+pVHIqd2+6GloLjOw3FSN2ebDCv1fNGos1FcA841b20WRu1KsXSyEijVjXHljl1wzWreYd8MplY694Y/sb+XVYuFjQwzwY4fxJ8g6WPcL7X7HIWWmW1wipbtiguy6ycauCf2JpK2qxezOovXik5H5avpD8AAAAASUVORK5CYII="/></box-icon> Reembolso no permitido</li>
                                                    </ul>
                                                    <a href="#" id="boton-claseEjecutiva" class="botonClase <%= j.getId() %>">Seleccionar</a>
                                                    <p class="enunciado">precio por pasajero<p>
                                                  </div>
                                                </div>

                                            </div>
                                        </div><!-- .listado_claseVuelo -->
                                        
                                    </div> <!-- .vuelo -->
                                    <% } %>
                                <% } %>
                            </div>
                            
                        </div>
                    </div>
                </div>
            </div>
            <div class="mensaje_alerta" >
                <div class="mensaje">
                    <h2 class="mensaje_titulo">!ERROR</h2>
                    <p class="mensaje_parrafo">Para completar el proceso debes escoger una aerolínea</p>
                    <button type="button" id="errorAlerta" class="mensaje_botonCerrar">Cerrar</button>
                </div>
            </div>
        </section>
                            
                            
        <!-- Lo que haremos dentro de este apartado es verificar por nuestro fomulario final -->      
        <div class="formulario_final">
            <div class="informacion">
                <h2 id="titulo_info">Pagar y Confirmar Reserva</h2>
                
                <form class="formulario_informacion" id="form" method="POST" action="ReservaControllers?accion=pagarIda">
                    <div class="secciones">
                        
                        <div class="seccion1">

                            <div class="informacion_tarjeta">
                                <h3>Datos de la tarjeta</h3>
                                <div class="campo">
                                    <label for="dni">Número de Cédula: </label>
                                    <input type="text" id="dni" name="dniCompra" class="ver" placeholder="Introduce el número de cédula para la compra"/>
                                    <p id="cedulaMensaje" class="formulario__input-error">El número de tarjeta no debe estar vacío y debe ser válido.</p>
                                </div>
                                <div class="campo">
                                    <label for="fecha">Fecha Vencimiento:</label>
                                    <div class="fecha_vencimiento">
                                        <select name="dia" id="days" class="ver">
                                            <option disabled="" selected>Dia</option>
                                        </select>
                                        <select name="mes" id="months" class="ver">
                                            <option disabled="" selected>Mes</option>
                                        </select>
                                        <select name="year" id="years" class="ver">
                                            <option disabled="" selected>Año</option>
                                        </select>
                                    </div>
                                </div>
                                
                            </div>
                            
                            <div class="informacion_facturacion">
                                <div class="informacion_facturacion--checkbox">
                                    <h3>Datos de facturacion</h3>
                                    <p><input type="checkbox" value="cargarDatos" class="ver" name="cajaCheck" checked=true/> Cargar los datos del primer pasajero</p>
                                </div>
                                
                                <div class="campo" id="nombreTitular">     
                                    <label for="nombre">Nombre del titular de la tarjeta: </label>
                                    <input type="text" id="nombre" class="ver" name="Names" value="<%= infoPasajeros.get(0).getNombre() %>" placeholder="Nombres Del Titular"/>
                                </div>

                                <div class="campo" id="emailTitular">
                                    <label for="correo">Correo Electronico: </label>
                                    <input type="email" id="correo" class="ver" name="Emails" value="<%= infoPasajeros.get(0).getCorreo() %>" placeholder="Dirección Email"/>
                                </div>
                                
                                <div class="acciones">
                                    <input id="pagar" type="submit" value="Pagar Vuelo" class="boton vuelo_pagar" />
                                    <a id="cancelar" href="#" class="vuelo_cancelar">Cancelar Vuelo</a>
                                </div>
                            </div>
                        </div><!-- seccion1 -->
                        
                        <div class="seccion2">
                            <h2>Resumen de Compra</h2>

                            <div class="informacion_vuelos">
                                <h3>Vuelos</h3>
                                <!--  Dentro de aqui debera existir el vuelo seleccionado por el cliente. -->
                                <div class="vuelo_ida">
                                    <!-- generado con javascript -->
                                </div>
                                
                            </div>

                            <div class="informacion_compra">
                                <div class="accordion" id="accordionPanelsStayOpenExample">
                                    <div class="accordion-item">
                                      <h2 class="accordion-header">
                                        <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapseOne" aria-expanded="true" aria-controls="panelsStayOpen-collapseOne">
                                          Ver detalles
                                        </button>
                                      </h2>
                                      <div id="panelsStayOpen-collapseOne" class="accordion-collapse collapse show">
                                        <div class="accordion-body inf">
                                            
                                            <p>Numero de Pasajeros: <%= pTotal %></p>
                                            <hr/>
                                            <%  int i = 1;
                                                for(Pasajeros j : infoPasajeros){ 
                                            %>
                                            <p>Pasajero <%= i %></p>
                                            <p>Nombres: <%= j.getNombre() %> <%= j.getApellido() %></p>
                                            <p>Cedula: <%= j.getCedula() %></p>
                                            <%
                                                if(!j.getTipo_pasajero().equals("nino")){
                                            %>
                                            <p>Tipo Pasajero: <%= j.getTipo_pasajero() %></p>
                                            <p>Email: <%= j.getCorreo() %></p>
                                            <p>Celular: <%= j.getCelular() %> </p>
                                            <%
                                                }else{
                                            %>
                                            <p>Tipo Pasajero: Niño</p>
                                            <% } %>
                                            <hr/>
                                            <% 
                                                i++;
                                            } 
                                            %>
                                           
                                        </div>
                                      </div>
                                    </div>
                                </div>
                            </div>

                            <div class="informacion_pagoFinal">
                               
                            </div>
                        </div>
                        
                        <div class="listadoPasajerosExtra">
                            <input type="hidden" name="NumeroPasajeros" value="<%= pTotal %>" />
                            <% for(Pasajeros j : infoPasajeros){ %>
                            <input type="hidden" name="Nombres-<%=j.getId() %>" value="<%= j.getNombre() %>"/>
                            <input type="hidden" name="Apellidos-<%= j.getId() %>" value="<%= j.getApellido() %>"/>
                            <input type="hidden" name="Correo-<%= j.getId() %>" value="<%= j.getCorreo() %>"/>
                            <input type="hidden" name="Telefono-<%= j.getId() %>" value="<%= j.getCelular() %>"/>
                            <input type="hidden" name="Cedula-<%= j.getId() %>" value="<%= j.getCedula() %>"/>
                            <input type="hidden" name="Pasajeros-<%= j.getId() %>" value="<%= j.getTipo_pasajero() %>"/>
                            <input type="hidden" name="Genero-<%= j.getId() %>" value="<%= j.getGenero() %>"/>
                            <% } %>
                        </div>
                        
                        <div class="listadoVuelos">
                            
                            <input type="hidden" name="PaisOrigen" value="<%= paisOrigen %>" />
                            <input type="hidden" name="PaisDestino" value="<%= paisDestino %>" />
                            <input type="hidden" name="ciudadOrigen" value="<%= ciudadOrigen %>" />
                            <input type="hidden" name="ciudadDestino" value="<%= ciudadDestino %>" />
                            <input type="hidden" name="fechaIda" value="<%= fechaIda %>" />
                            <input type="hidden" name="pTotal" value="<%= pTotal %>" />
                        </div>
                        
                        
                    </div><!-- .secciones -->
                </form>
            </div>
        </div><!-- podemos desplegar este formulario de manera mucho mas animada. -->
        
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
        <script>window.jQuery || document.write('<script src="js/vendor/jquery-1.11.2.min.js"><\/script>');</script>

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
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>

        <script src="js/scripts/vuelosIda.js"></script>
        <script src="Pages/Reserva/scripts/validacion.js"></script>
    </body>
</html>
