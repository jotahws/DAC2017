<%-- 
    Document   : cabecalho
    Created on : 11/05/2017, 09:01:03
    Author     : MauMau
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
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style.css"/>
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
                    <a class="navbar-brand">Sistema ATOA</a>
                </div>
                <div class="collapse navbar-collapse" id="myNavbar">
                    <c:choose>
                        <c:when test="${funcionarioLogado.perfil == 'GERENTE-RH'}">
                            <ul class="nav navbar-nav">
                                <li><a href="${pageContext.request.contextPath}/ListaAtividadeServlet?action=ListaAtividades">Atividades</a></li>
                                <li><a href="${pageContext.request.contextPath}/ListaFuncionarioServlet?action=ListaFuncionarios">Relatórios</a></li>                        
                                <li><a href="${pageContext.request.contextPath}/atividades/aprovarCorrecao.jsp">Aprovar Correção</a></li>
                            </ul>
                        </c:when>
                        <c:when test="${(funcionarioLogado.perfil == 'FUNCIONARIO') || (funcionarioLogado.perfil == 'GERENTE')}">
                            <ul class="nav navbar-nav">
                                <li><a href="${pageContext.request.contextPath}/ListaAtividadeServlet?action=QuadroAtividade">Home</a></li>
                                <li><a href="${pageContext.request.contextPath}/atividades/historicoAtividades.jsp">Lista de Atividades</a></li>
                                <li><a href="${pageContext.request.contextPath}/atividades/atividadesPendentes.jsp">Atividades pendentes</a></li>
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