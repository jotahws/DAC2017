<%-- 
    Document   : index.jsp
    Created on : Apr 29, 2017, 6:15:25 PM
    Author     : JotaWind
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="css/stylesheet.css"/>
        <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css"/>
        <title>Sistema RHINDO</title>
    </head>

    <body>
        <%@include file="pre-fabricado/cabecalho.jsp" %>
        <div class="jumbotron">
            <div class="container">
                <h1>Index.jsp</h1>
                <h4>(redirecionar para funcionarios ou para login)</h4>
                <a href="funcionarios">Funcionarios</a> <br>
                <a href="login.jsp">login</a>                
            </div>
        </div>
    </body>
</html>
