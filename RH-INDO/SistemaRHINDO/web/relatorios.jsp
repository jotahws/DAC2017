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
        <title>Relatórios - Sistema RHINDO</title>
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
                            <h2>Você não pode acessar a essa página</h2>
                        </div>
                    </c:when>
                    <c:otherwise> 
                        <div class="container">
                            <!-- Row do input pesquisar: -->
                            <div class="row row-busca-titulo">
                                <div class="col-md-8 titulo">
                                    <h1 class="col-md-10">Relatórios</h1>
                                </div>
                            </div>
                            <!-- Row do relatório: -->
                            <div class="row row-lista-corpo">
                                <!-- corpo da página -->
                                <div class="col-md-12 corpo corpo-relatorio">
                                    <!-- Primeiro relatório -->
                                    <div class="corpo-relatorio">
                                        <legend>Lista de todos os Funcionários da empresa</legend>
                                        <div class="form-group col-md-2">
                                            <a href="RelatoriosServlet" class="btn btn-md btn-primary"><span class="glyphicon glyphicon-user"></span> Gerar Relatório</a>
                                        </div>                                    </div >
                                    <!-- Segundo relatório -->
                                    <div class="corpo-relatorio">
                                        <legend>Relatório de horas trabalhadas</legend>  
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
                                                <label for="depto">Mês</label>
                                                <select class="form-control" id="depto" name="depto">
                                                    <option>
                                                        Janeiro
                                                    </option>
                                                    <option>
                                                        Fevereiro
                                                    </option>
                                                    <option>
                                                        Março
                                                    </option>
                                                </select>
                                            </div>
                                            <div class="form-group col-md-1">
                                                <label>&nbsp;</label>
                                                <a class="btn btn-md btn-primary"><span class="glyphicon glyphicon-calendar"></span> Gerar Relatório</a>
                                            </div>
                                        </form>
                                    </div>
                                    <!-- Terceiro relatório -->
                                    <div class="corpo-relatorio">
                                        <legend>Funcionários com horário atrasado</legend>
                                        <form>
                                            <div class="form-group col-md-4">
                                                <label for="depto">Mês</label>
                                                <select class="form-control" id="depto" name="depto">
                                                    <option>
                                                        Janeiro
                                                    </option>
                                                    <option>
                                                        Fevereiro
                                                    </option>
                                                    <option>
                                                        Março
                                                    </option>
                                                </select>
                                            </div>
                                            <div class="form-group col-md-1">
                                                <label>&nbsp;</label>
                                                <a class="btn btn-md btn-primary"><span class="glyphicon glyphicon-time"></span> Gerar Relatório</a>
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
