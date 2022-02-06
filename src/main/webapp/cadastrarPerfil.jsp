<%-- 
    Document   : cadastrarUsuario
    Created on : 18/10/2021, 09:06:11
    Author     : Vitor
--%>


<%@page import="java.io.PrintWriter"%>
<%@page import="control.GerenciarLogin"%>
<%@page contentType="text/html" pageEncoding="UTF-8"
        import="java.util.ArrayList"
        import="model.Perfil"
        import="model.Usuario"
        import="model.UsuarioDAO"
        import="model.PerfilDAO"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>

<!-- Novo teste de commit -->
<!DOCTYPE html>
<%   
    
    Usuario usuario = new Usuario();
    usuario = GerenciarLogin.verificarAcesso(request, response);
    PrintWriter saida = response.getWriter();
    if(usuario==null){
                     out.println("<script type='text/javascript'> "+
                     "location.href='login.jsp';</script>"); 
    }else{
        if(usuario.getPerfil().getIdPerfil()!=1){



                   saida.println("<script type='text/javascript'> "+"alert('Usuário não autorizado!');"+
                   "location.href='index.jsp';</script>");
        }
    }    
    %>
<html>
    <head>
        <title>Cadastro de Perfil</title>
        <meta charset="UTF-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, 
              maximum-scale=1, user-scalable=no" />
       <link rel="shortcut icon" type="image/x-icon" href="images/favicon.png">
      
        <link rel="stylesheet" href="css/font-awesome.min.css"/>
        <link rel="stylesheet" href="css/styles.css" type="text/css" media="all" />
   
        <link rel="stylesheet" href="css/formulario.css" >
        <script src="https://kit.fontawesome.com/3f3417947e.js" crossorigin="anonymous"></script>
        
       
     
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="css/style.css">
        
        

    </head>
  
        

    <body class="img js-fullheight" style="background-image: url(https://barbearialeolima.com.br/images/imagembanner.jpeg);">
          <%
        //HTTP 1.1
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        //HTTP 1.0
        response.setHeader("Pragma", "no-cache");
        //Proxie
        //response.setHeader("Expires", "0");
      
        %>
        
    
        <div id="imagemBanner" style="margin:40px;margin-top:40px;"><a href="index.jsp"><img  src="images/logo.png" width="200" ></a></div>

        <div class="form-style-10">
            <h1>Cadastro de Perfil <span>Cadastre um Perfil de Acesso</span></h1>
            <form action="gerenciarPerfil" method="POST">
                <div class="section"><span>1</span>Nome:</div>
                <div class="inner-wrap">
                    <label>Nome do serviço: <input type="text" name="nome" required/></label>
                    <input type="hidden" name="idPerfil" value=""/>
             

                </div>

                <div class="section"><span>2</span>Data de Cadastro:</div>
                <div class="inner-wrap">
                    <label>Data: <input type="date" name="dataCadastro"  required/></label>
                    
                </div>

                
                <div class="button-section">
                    <input type="submit" name="Cadastrar" value="Cadastrar"/>
                    <a href="listarPerfil.jsp" ><input style="float:right;background-color: #F3957A;border-color:#F3957A;" type="button" name="Cadastrar" value="Voltar"/></a>

                </div>
                <link href='http://fonts.googleapis.com/css?family=Bitter' rel='stylesheet' type='text/css'>
            </form>
        </div>     

        <jsp:include page="templates/footer.jsp"/>
        <script src="js/jquery.min.js"></script>
        <script src="js/popper.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/main.js"></script>
        
        



    </body>
</html>

