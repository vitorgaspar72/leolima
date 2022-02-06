<%-- 
    Document   : index
    Created on : 17 de ago. de 2021, 16:26:47
    Author     : laors
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- Novo teste de commit -->
<!DOCTYPE html>

<html>
    <head>
        <title>Página de Login</title>
        <meta http-equiv="x-ua-compatible" content="ie=edge"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, 
              maximum-scale=1, user-scalable=no" />
        <link rel="stylesheet" href="bootstrap/bootstrap.min.css" type="text/css" media="all"/>
        <link rel="stylesheet" href="css/menu.css" type="text/css" media="all" />
        <link rel="stylesheet" href="css/font-awesome.min.css"/>
          <link rel="stylesheet" href="css/formulario.css"/>
        <link rel="stylesheet" href="css/styles.css" type="text/css" media="all" />
        <link rel="stylesheet" href="datatables/dataTables.bootstrap4.min.css"/>
         
        <script src="https://kit.fontawesome.com/3f3417947e.js" crossorigin="anonymous"></script>
        <script src="js/jquery-3.6.0.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/sweetalert2.all.min.js"type="text/javascript"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="shortcut icon" type="image/x-icon" href="images/favicon.png">
        <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700&display=swap" rel="stylesheet">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

        <link rel="stylesheet" href="css/style.css">
		 
    </head>
    <body>

    <body class="img js-fullheight" style="background-image: url(https://barbearialeolima.com.br/images/imagembanner.jpeg);">
        <div id="imagemBanner" style="margin:40px;margin-top:40px;"><a href="index.jsp"><img  src="images/logo.png" width="200" ></a></div>

        <section class="ftco-section">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-md-6 text-center mb-5">
                        <h2 class="heading-section" style="color:#fbceb5;">Página de Login</h2>
                    </div>
                </div>
                <div class="row justify-content-center">
                    <div class="col-md-6 col-lg-4">
                        <div class="login-wrap p-0">
                            <h3 class="mb-4 text-center">Você já é <a href="cadastrarUsuario.jsp">cadastrado?</a></h3>
                            <form action="gerenciarLogin" method="POST" class="signin-form" id="form">
                                <div class="form-group">
                                    <input type="text" class="form-control" placeholder="Email" name="login" required>
                                </div>
                                <div class="form-group">
                                    <input id="password-field" type="password" class="form-control" placeholder="Senha" name="senha" required>
                                    <span toggle="#password-field" class="fa fa-fw fa-eye field-icon toggle-password"></span>
                                </div>
                                <div class="form-group">
                                    
                                    <button 
                                    	class="form-control btn btn-primary submit px-3" type="submit" onload="return onloadCallback()">Login</button>
                                    	<div class="w-50 text-md-center">
                                        <a href="recuperarSenha.jsp" style="color: #fbceb5;float: right;margin-right: -180px;">Recuperar Senha</a>
                                        <a href="index.jsp" style="color: white;float:left;">Voltar</a><br>
                                    </div>
                                </div>
                                <div class="form-group d-md-flex" style="text-align: center;">
                                  
                                    
                                    <br><br>
                                    <div class="g-recaptcha" data-sitekey="6LekakodAAAAAHrSl3RCgmTX-0J8Xjf8bx2Il6kZ"></div>
                                    
                                </div>
                            </form>
                           <br><br> <div id="error"></div>
                         
							<script src="https://www.google.com/recaptcha/api.js" async defer></script>
							
							<script>
								window.onload= function(){
									let isValid= false;
									const form= document.getElementById("form");
									const error= document.getElementById("error");
									
									form.addEventListener("submit",function(event){
										event.preventDefault();
										const response = grecaptcha.getResponse();
										console.log(response);
										if(response){
											form.submit();
										}
									});
								}
							</script>
                            </section>

                            <script src="js/jquery.min.js"></script>
                            <script src="js/popper.js"></script>
                         
                            <script src="js/main.js"></script>
							
							<script src="https://www.google.com/recaptcha/api.js?onload=onloadCallback&render=explicit"
							    async defer>
							</script>


    </body>
</html>
