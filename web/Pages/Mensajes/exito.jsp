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
        <!--<link rel="stylesheet" href="css/modificado/style.css"/>-->
        <link rel="stylesheet" href="css/tooplate-style.css">

        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
        <script src="js/vendor/modernizr-2.8.3-respond-1.4.2.min.js"></script>
        <link rel="stylesheet" href="css/modificado/mensaje.css"/>
    </head>

    <body>

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
                                <div class="submit-form">
                                    <h4>COMPRA DE VIAJE</h4>
                                    <form id="form-submit" class="formulario" action="ReservaControllers?accion=reservar" method="post">
                                        <h2> La compra realiza ha sido procesada correctamente, puedes revisar tu email y verificarlo. </h2>
                                    </form>
                                </div>
                            </div>
                        </section>
                    </div>
                </div>
            </div>
        </section>
    </body>
    <script src="js/scripts/clearSession.js"></script>
</html>