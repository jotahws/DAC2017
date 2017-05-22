<%-- 
    Document   : index
    Created on : May 4, 2017, 4:03:16 PM
    Author     : JotaWind
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
        <title>Cargos - Sistema RHINDO</title>
    </head>
    <body>
        <%@include file="../pre-fabricado/cabecalho.jsp" %>
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
                                <div class="col-md-4">
                                    <div class="input-group">
                                        <input type="text" class="form-control" placeholder="Nome, Salário...">
                                        <span class="input-group-btn">
                                            <button class="btn btn-default" type="button">Pesquisar</button>
                                        </span>
                                    </div>
                                </div>
                                <div class="col-md-8 titulo">
                                    <h1 class="col-md-10">Cargos</h1>
                                    <div class="text-right">
                                        <a href="${pageContext.request.contextPath}/cargos/cadastrar.jsp" class="btn btn-info col-md-2">Novo</a>
                                    </div>
                                </div>
                            </div>
                            <!-- Row da lista de cargos e corpo da pagina: -->
                            <div class="row row-lista-corpo">
                                <!-- lista de cargos -->
                                <div class="col-md-4 lista-lat">
                                    <div class="list-group">
                                        <jsp:useBean id="cargo" class="beans.Cargo"/>
                                        <c:set var="lista" value="${cargos}"/>
                                        <c:forEach var="item" items="${lista}">
                                            <a href="#${item.id}" role="tab" data-toggle="tab" class=" list-group-item">
                                                <c:out value="${item.nome}"/>
                                            </a>
                                        </c:forEach>  
                                    </div>
                                </div>
                                <!-- corpo da página -->
                                <div class="corpo col-md-8 corpo">
                                    <fieldset>
                                        <legend class="legenda">Editar Cargo</legend>
                                        <div class="tab-content">
                                            <div role="tabpanel" class="tab-pane active" id="">
                                                <h1 class="unselectable text-center cor-disabled">Selecione um Cargo</h1>
                                                <h1 class="text-center"><span class="glyphicon glyphicon-hand-left cor-disabled gi-5x"></span></h1>
                                            </div>
                                            <c:forEach var="item" items="${lista}">
                                                <div role="tabpanel" class="tab-pane fade" id="${item.id}">
                                                    <form method="POST" action="CargoServlet?action=edit&idCargo=${item.id}">
                                                        <div class="row">
                                                            <div class="form-group col-md-8">
                                                                <label for="nome">Nome:</label>
                                                                <input type="text" class="form-control" name="nome" id="nome" value="${item.nome}">
                                                            </div>
                                                            <div class="form-group col-md-4">
                                                                <label for="salario">Salário:</label>
                                                                <input type="number" class="form-control" name="salario" id="salario" value="${item.salario}">
                                                            </div>
                                                            <div class="form-group col-md-12">
                                                                <label for="requisitos">Requisitos:</label>
                                                                <textarea class="form-control" name="requisitos" id="requisitos" rows="3">${item.requisitos}</textarea>
                                                            </div>
                                                            <div class="form-group col-md-4">
                                                                <label for="carga">Carga de Trabalho (Horas/Mês):</label>
                                                                <input type="number" class="form-control" name="carga" id="carga" value="${item.cargaMinima}">
                                                            </div>
                                                            <div class="form-group col-md-4">
                                                                <label for="imposto">Desconto de Imposto (%):</label>
                                                                <input type="number" class="form-control" name="imposto" id="imposto" value="${item.descImposto}">
                                                            </div>
                                                        </div>
                                                        <div class="text-right">
                                                            <button type="submit" id="botao" class="btn btn-primary">Salvar Alterações</button>  
                                                            <a href="CargoServlet?action=delete&idCargo=${item.id}" class="btn btn-danger">Excluir</a>  
                                                        </div>
                                                    </form>
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
