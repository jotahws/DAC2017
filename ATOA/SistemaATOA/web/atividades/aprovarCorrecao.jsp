<%-- 
    Document   : aprovarCorrecao
    Created on : 11/05/2017, 16:09:51
    Author     : MauMau
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Aprovar Correção - Sistema ATOA</title>
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
                                <div class="col-md-4"></div>

                                <div class="col-md-8 titulo">
                                    <h1 class="col-md-10">Aprovar uma correção</h1>
                                </div>
                            </div>
                            <!-- Row da lista de departamentos e atividades de capa departamento e corpo da pagina: -->
                            <div class="row row-lista-corpo">
                                <!-- lista de edicoes -->
                                <div class="col-md-4 lista-lat">
                                    <h3 class="text-center">Funcionario Solicitante</h3>
                                    <div class="list-group">
                                        <c:set var="lista" value="${edicoes}"/>
                                        <c:forEach var="item" items="${lista}">
                                            <a href="#${item.id}" role="tab" data-toggle="tab" class="list-group-item">
                                                <c:out value="${item.atividade.funcionario.nome}"/>
                                            </a>
                                        </c:forEach>      
                                    </div>
                                </div>
                                <!-- corpo da página -->
                                <div class="corpo col-md-8 corpo">
                                    <fieldset>
                                        <div class="tab-content">
                                            <div role="tabpanel" class="tab-pane active" id="">
                                                <h1 class="unselectable text-center cor-disabled">Selecione uma solicitação</h1>
                                                <h1 class="text-center"><span class="glyphicon glyphicon-hand-left cor-disabled gi-5x"></span></h1>
                                            </div>
                                            <c:forEach var="item" items="${edicoes}">
                                                <div role="tabpanel" class="tab-pane fade" id="${item.id}">
                                                    <legend><h2>De ${item.atividade.funcionario.nome}</h2></legend>
                                                    <h4 class="col-md-12"><b class="text-info">Tipo da atividade:</b> ${item.atividade.tipo.nome}</h4>                                                    
                                                    <h4 class="col-md-12"><b class="text-info">Departamento:</b> ${item.atividade.tipo.departamento.nome}</h4>
                                                    <h4 class="col-md-6"><b class="text-info">Data de início da atividade:</b>
                                                        <jsp:useBean id="data" class="java.util.Date"/>
                                                        <c:choose>
                                                            <c:when test="${data.date == item.atividade.inicio.date && data.day == item.atividade.inicio.day}">
                                                                <c:out value="Hoje,"/>
                                                                <fmt:formatDate value="${item.atividade.inicio}" type="BOTH" pattern=" HH:mm"/>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <fmt:formatDate value="${item.atividade.inicio}" type="BOTH" pattern="dd/MM/yyy HH:mm"/>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </h4>
                                                        <h4 class="col-md-6"><b class="text-info">Status: </b>
                                                        <c:choose>
                                                            <c:when test="${item.atividade.statusAtividade == 2}">Encerrada</c:when>
                                                            <c:otherwise>Em andamento</c:otherwise>
                                                        </c:choose>
                                                        
                                                        </h4>
                                                    <div class=""><h4 class="col-md-12"><b class="text-danger">Descrição Adicionada:</b> "${item.descricao}"</h4></div>
                                                    <div class="text-right">
                                                        <a href="${pageContext.request.contextPath}/ListaEdicaoServlet?action=aprovada&id=${item.id}" class="btn btn-success">Aprovar Alteração</a>
                                                        <a href="${pageContext.request.contextPath}/ListaEdicaoServlet?action=recusada&id=${item.id}" class="btn btn-danger">Recusar Alteração</a>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </fieldset>
                                </div>                                
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
            </c:otherwise>
        </c:choose>
    </body>
</html>
