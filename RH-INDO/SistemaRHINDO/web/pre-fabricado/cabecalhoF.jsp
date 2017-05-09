<%-- 
    Document   : cabecalho
    Created on : May 3, 2017, 5:04:55 PM
    Author     : JotaWind
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="../bootstrap/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="../css/stylesheet.css"/>
        <link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.min.css"/>     
    </head>
    <body>
        <div class="nav navbar-default navbar-static-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>                        
                    </button>
                    <a class="navbar-brand">Sistema ATOA</a>
                </div>
                <div class="collapse navbar-collapse" id="myNavbar">
                    <ul class="nav navbar-nav">
                        <li><a href="${pageContext.request.contextPath}/relatoriosF.jsp">Relatórios</a></li>
                        <li><a href="${pageContext.request.contextPath}/contraCheque.jsp">Folha de Pagamento</a></li>   
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <a>Logout <span class="glyphicon glyphicon-log-out glyphicon-lg"></span></a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </body>
</html>
