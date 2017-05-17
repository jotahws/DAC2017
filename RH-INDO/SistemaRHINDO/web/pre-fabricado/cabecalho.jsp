<%-- 
    Document   : cabecalho
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/stylee.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css"/>     
    </head>
    <body>
        <div class="nav navbar-inverse navbar-static-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>                        
                    </button>
                    <a class="navbar-brand">Sistema RHINDO</a>
                </div>
                <div class="collapse navbar-collapse" id="myNavbar">
                    <ul class="nav navbar-nav">
                        <li><a href="${pageContext.request.contextPath}/relatorios.jsp">Relatórios</a></li>
                        <li><a href="${pageContext.request.contextPath}/fecharFolha.jsp">Folha de Pagamento</a></li>
                        <li class="dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown" href="#">Funcionários<span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="${pageContext.request.contextPath}/CarregaListaFuncServlet?action=listaFuncionarios">Lista de Funcionários</a></li>
                                <li><a href="${pageContext.request.contextPath}/CarregaListaFuncServlet?action=register">Novo Funcionário</a></li>
                            </ul>
                        </li>       
                        <li class="dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown" href="#">Departamentos<span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="${pageContext.request.contextPath}/departamentos">Lista de Departamentos</a></li>
                                <li><a href="${pageContext.request.contextPath}/departamentos/cadastrar.jsp">Novo Departamento</a></li>
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown" href="#">Cargos<span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="${pageContext.request.contextPath}/cargos">Lista de Cargos</a></li>
                                <li><a href="${pageContext.request.contextPath}/cargos/cadastrar.jsp">Novo Cargo</a></li>
                            </ul>
                        </li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <a href="#">Logout <span class="glyphicon glyphicon-log-out"></span></a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </body>
</html>
