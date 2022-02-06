<%-- 
    Document   : cadastrarUsuario
    Created on : 18/10/2021, 09:06:11
    Author     : Vitor
--%>


<%@page import="control.GerenciarLogin"%>
<%@page contentType="text/html" pageEncoding="UTF-8"
        import="java.util.ArrayList"
        import="model.Perfil"
        import="model.Usuario"
        import="model.UsuarioDAO"
        import="model.PerfilDAO"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- Novo teste de commit -->
<!DOCTYPE html>
<%   
    
    Usuario usuario = new Usuario();
    usuario = GerenciarLogin.verificarAcesso(request, response);
    
     if(usuario==null){
                     out.println("<script type='text/javascript'> "+
                     "location.href='login.jsp';</script>"); 
    }
     
    %>

<html>
    <head>
        <title>Alteração de Usuário</title>
        <meta charset="UTF-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, 
              maximum-scale=1, user-scalable=no" />
        <link rel="stylesheet" href="bootstrap/bootstrap.min.css" type="text/css" media="all"/>
        <link rel="stylesheet" href="css/menu.css" type="text/css" media="all" />
        <link rel="stylesheet" href="css/font-awesome.min.css"/>
        <link rel="stylesheet" href="css/styles.css" type="text/css" media="all" />
        <link rel="stylesheet" href="datatables/dataTables.bootstrap4.min.css"/>
        <link rel="stylesheet" href="css/formulario.css" >
        <script src="https://kit.fontawesome.com/3f3417947e.js" crossorigin="anonymous"></script>
        <script src="js/jquery-3.6.0.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/sweetalert2.all.min.js"type="text/javascript"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- Máscaras -->
        <link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="//assets.locaweb.com.br/locastyle/2.0.6/stylesheets/locastyle.css">
        <!-- Fim Máscaras -->
        <link rel="shortcut icon" type="image/x-icon" href="images/favicon.png">
        <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700&display=swap" rel="stylesheet">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

        <link rel="stylesheet" href="css/style.css">

    </head>
   
    <c:if test="${user.perfil.idPerfil==1 && usuario.perfil.idPerfil != 1}">  <p>Usuário não autorizado!</p>
        </c:if>
    <c:if test="${user.perfil.idPerfil!=1 || user.perfil.idPerfil==1 && usuario.perfil.idPerfil == 1}">
    <body class="img js-fullheight" style="background-image: url(https://leolima.azurewebsites.net/images/imagembanner.jpeg);">
        <div id="imagemBanner" style="margin:40px;margin-top:40px;"><a href="index.jsp"><img  src="images/logo.png" width="200" ></a></div>

        <div class="form-style-10">
            <h1>Alterar Cadastro <span>Altere seus dados!</span></h1>
            <form action="gerenciarUsuario" method="POST" enctype="multipart/form-data">
                <div class="section"><span>1</span>Nome e ID:</div>
                <div class="inner-wrap">
                    <label>ID Cadastro: <input type="text" name="idUsuario" readonly value="${user.idUsuario}"/></label>
                    <label>Seu Nome: <input type="text" name="nome" required value="${user.nome}"/></label>
                    
                   

                <div class="section"><span>2</span>Email e Telefone:</div>
                <div class="inner-wrap">
                    <label>Seu Email: <input type="email" name="login" readonly value="${user.login}"/></label>
                    <label>Telefone: <input type="text" name="telefone" class="cel-sp-mask" required value="${user.telefone}"/></label>
                    <input type="hidden" name="status" value="1"/>
                </div>

                <div class="section"><span>3</span>Senha e CPF:</div>
                <div class="inner-wrap">
                    <label>Senha: <input type="password" name="senha" minlength="8" value="${user.senha}" required/></label>
                    <label>CPF: <input type="text" name="cpf" class="cpf-mask" minlength="9" value="${user.cpf}" <c:if test="${user.cpf==''}">required</c:if><c:if test="${user.cpf!=''}">readonly</c:if>/></label>
                </div>
                <div class="section"><span>3</span>Nascimento e Endereço:</div>
                <div class="inner-wrap">
                    <label>Data de Nascimento: <input type="date" name="dataNascimento"  required value="${user.dataNascimento}" /></label>
                    <label>Endereço: <input type="text" name="endereco" maxlenght="45" minlenght="10" required value="${user.endereco}"/></label>
                </div>
                
                <div class="section"><span>4</span>Foto:</div>
                <div class="inner-wrap">
                    <label>Foto de Exibição: <input type="file" name="file"  /></label>
                   
                </div>
                
                <div class="button-section">
                     <c:if test="${usuario.perfil.idPerfil!=1}"><input type="hidden" name="idPerfil" value="${user.perfil.idPerfil}"/></c:if>
                    <c:if test="${usuario.perfil.idPerfil==1}">
                       <div class="section"><span>5</span>Acesso:</div> 
                        <div class="inner-wrap">
                     <jsp:useBean class="model.PerfilDAO" id="pdao"/>          
                                  <select name="idPerfil" required >
                                      <option value="" selected> Escolha o perfil de acesso:</option>
                                      <c:forEach var="perfil" items="${pdao.lista}">
                                          <option value="${perfil.idPerfil}" <c:if test="${perfil.idPerfil == user.perfil.idPerfil}">selected</c:if> >${perfil.nome}</option>
                                      
                                     </c:forEach>
                                      
                                      
                                  </select>
                    </div></c:if>
                </div>
                
                <div class="button-section">
                      <input type="checkbox" required >Você aceita os <a href="termos.jsp" style="color:blue;">termos</a> de uso do site. 
                </div>    
                    

                    
                <div class="button-section">       
                        <input style="float:left;" type="submit" style="float: left;" name="Salvar" value="Salvar" />
                        <a <c:if test="${usuario.perfil.idPerfil < 4}">href="listarUsuario.jsp"</c:if> <c:if test="${usuario.perfil.idPerfil == 4}"></c:if> href="index.jsp" ><input style="float:right;background-color: #F3957A;border-color:#F3957A;" type="button" name="Cadastrar" value="Voltar"/></a>
                        
                </div>   
                </div>
                <link href='http://fonts.googleapis.com/css?family=Bitter' rel='stylesheet' type='text/css'>
            </form>
        </div>     


        <script src="js/jquery.min.js"></script>
        <script src="js/popper.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/main.js"></script>
        <jsp:include page="templates/footer.jsp"/>
        <script async="" src="//www.google-analytics.com/analytics.js"></script><script type="text/javascript" src="//code.jquery.com/jquery-2.0.3.min.js"></script>
        <script type="text/javascript" src="//assets.locaweb.com.br/locastyle/2.0.6/javascripts/locastyle.js"></script>
        <script type="text/javascript" src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>


     </c:if>
    </body>
</html>

