<%-- 
    Document   : cabecalho
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean scope="session" id="funcionarioLogado" class="beans.Funcionario"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/javascript/jquery.maskedinput.js"></script>
        <script src="${pageContext.request.contextPath}/javascript/scripts.js"></script>
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
                    <c:choose>
                        <c:when test="${funcionarioLogado.perfil == 'GERENTE-RH'}">
                            <ul class="nav navbar-nav">
                                <li><a href="${pageContext.request.contextPath}/RelatoriosServlet?action=RelatoriosGerente">Relatórios</a></li>
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
                                        <li><a href="${pageContext.request.contextPath}/CarregaListaDeptoServlet?action=listaDeptos">Lista de Departamentos</a></li>
                                        <li><a href="${pageContext.request.contextPath}/departamentos/cadastrar.jsp">Novo Departamento</a></li>
                                    </ul>
                                </li>
                                <li class="dropdown">
                                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">Cargos<span class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="${pageContext.request.contextPath}/CarregaListaCargoServlet?action=listaCargos">Lista de Cargos</a></li>
                                        <li><a href="${pageContext.request.contextPath}/cargos/cadastrar.jsp">Novo Cargo</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </c:when>
                        <c:when test="${(funcionarioLogado.perfil == 'FUNCIONARIO') || (funcionarioLogado.perfil == 'GERENTE')}">
                            <ul class="nav navbar-nav">
                                <li><a href="${pageContext.request.contextPath}/relatoriosF.jsp">Relatórios</a></li>
                                <li><a href="${pageContext.request.contextPath}/contraCheque.jsp">Folha de Pagamento</a></li>   
                            </ul>
                        </c:when>
                    </c:choose>
                    <ul class="nav navbar-nav navbar-right">
                        <li >
                            <p class="navbar-text">
                                <jsp:getProperty name="funcionarioLogado" property="nome"/>                                
                            </p>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/LoginServlet?action=logout">Logout <span class="glyphicon glyphicon-log-out"></span></a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </body>
</html>
