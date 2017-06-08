<%-- 
    Document   : relatorios
    Created on : May 4, 2017, 4:20:51 PM
    Author     : JotaWind
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
        <title>Relat�rios - Sistema RHINDO</title>
    </head>
    <body>
        <%@include file="/pre-fabricado/cabecalho.jsp" %>
        <c:choose>
            <c:when test="${funcionarioLogado.email == null}">
                <c:redirect url="/login.jsp"/>
            </c:when>
            <c:otherwise>
                <c:choose>
                    <c:when test="${(funcionarioLogado.perfil != 'GERENTE-RH')}">
                        <div class="container">
                            <h1>Acesso Negado.</h1>
                            <h2>Voc� n�o pode acessar a essa p�gina</h2>
                        </div>
                    </c:when>
                    <c:otherwise> 
                        <div class="container">
                            <!-- Row do input pesquisar: -->
                            <div class="row row-busca-titulo">
                                <div class="col-md-8 titulo">
                                    <h1 class="col-md-10">Relat�rios</h1>
                                </div>
                            </div>
                            <!-- Row do relat�rio: -->
                            <div class="row row-lista-corpo">
                                <!-- corpo da p�gina -->
                                <div class="col-md-12 corpo corpo-relatorio">
                                    <!-- Primeiro relat�rio -->
                                    <div class="corpo-relatorio">
                                        <legend>Lista de todos os Funcion�rios da empresa</legend>
                                        <div class="form-group col-md-2">
                                            <a href="RelatoriosServlet" class="btn btn-md btn-primary"><span class="glyphicon glyphicon-user"></span> Gerar Relat�rio</a>
                                        </div>                                    </div >
                                    <!-- Segundo relat�rio -->
                                    <div class="corpo-relatorio">
                                        <legend>Relat�rio de horas trabalhadas</legend>  
                                        <form>
                                            <div class="form-group col-md-4">
                                                <label for="depto">Departamento</label>
                                                <select class="form-control" id="depto" name="depto">
                                                    <jsp:useBean id="depto" class="beans.Departamento"/>
                                                    <c:set var="lista" value="${deptos}"/>
                                                    <c:forEach var="item" items="${lista}">
                                                        <option value="${item.id}" <c:if test="${func.departamento.nome == item.nome}">selected</c:if> >
                                                            <c:out value="${item.nome}"/>
                                                        </option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="form-group col-md-4">
                                                <label for="depto">M�s</label>
                                                <select class="form-control" id="depto" name="depto">
                                                    <option>
                                                        Janeiro
                                                    </option>
                                                    <option>
                                                        Fevereiro
                                                    </option>
                                                    <option>
                                                        Mar�o
                                                    </option>
                                                </select>
                                            </div>
                                            <div class="form-group col-md-1">
                                                <label>&nbsp;</label>
                                                <a class="btn btn-md btn-primary"><span class="glyphicon glyphicon-calendar"></span> Gerar Relat�rio</a>
                                            </div>
                                        </form>
                                    </div>
                                    <!-- Terceiro relat�rio -->
                                    <div class="corpo-relatorio">
                                        <legend>Funcion�rios com hor�rio atrasado</legend>
                                        <form>
                                            <div class="form-group col-md-4">
                                                <label for="depto">M�s</label>
                                                <select class="form-control" id="depto" name="depto">
                                                    <option>
                                                        Janeiro
                                                    </option>
                                                    <option>
                                                        Fevereiro
                                                    </option>
                                                    <option>
                                                        Mar�o
                                                    </option>
                                                </select>
                                            </div>
                                            <div class="form-group col-md-1">
                                                <label>&nbsp;</label>
                                                <a class="btn btn-md btn-primary"><span class="glyphicon glyphicon-time"></span> Gerar Relat�rio</a>
                                            </div>                                        
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
            </c:otherwise>
        </c:choose>
    </body> 
</html>
