<%-- 
    Document   : login
    Created on : May 1, 2017, 2:57:52 PM
    Author     : JotaWind
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="CSS/stylee.css"/>
        <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-4  centrologin">
                    <h2 id="textologin">Sistema RH-INDO</h2><br/>
                    <div class="form-group">
                        <form action="LoginServlet" method="POST">
                            <div class="row">
                                <label class="col-md-3 text-right " >Login:</label><input class="col-md-5" type="text"><br/>
                                <div class="col-md-3"></div>
                            </div>
                            <div class="row">
                                <label class="col-md-3 text-right" >Senha:</label><input class="col-md-5" type="password"><br/>
                            </div><br/>
                            <div class="row alinhadireita">

                                <a href="recuperarsenha.jsp" class="">Esqueci minha senha</a><br/>
                                <input type="submit" class="btn btn-default " value="Entrar">
                            </div>
                        </form>
                    </div>
                </div>
                <div class="col-md-4"></div>
            </div>

        </div>
    </body>
</html>
