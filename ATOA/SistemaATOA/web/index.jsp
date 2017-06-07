<%-- 
    Document   : index
    Created on : 20/05/2017, 14:37:42
    Author     : MauMau
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="CSS/stylesheet.css"/>
        <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css"/>
        <title>Sistema ATOA</title>
    </head>
    <body>
       <%@include file="/pre-fabricado/cabecalho.jsp" %>
        <c:choose>
            <c:when test="${funcionarioLogado.email == null}">
                <c:redirect url="/login.jsp"/>
            </c:when>
            <c:otherwise>
                <c:choose>
                    <c:when test="${(funcionarioLogado.perfil == 'GERENTE') || (funcionarioLogado.perfil == 'FUNCIONARIO')}">
                        <c:redirect url="atividades/listaAtividades.jsp"/>
                    </c:when>
                    <c:when test="${(funcionarioLogado.perfil == 'GERENTE-RH')}">
                        <c:redirect url="ListaAtividadeServlet?action=ListaAtividades"/>
                    </c:when>
                    <c:otherwise> 
                        <div class="jumbotron">
                            <div class="container">
                                <h2>Voc� n�o possui nenhum perfil de funcion�rio v�lido.</h2>
                                <h4>Verifique sua conta com um Gerente de RH e tente novamente.</h4>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
            </c:otherwise>
        </c:choose>
    </body>
</html>
