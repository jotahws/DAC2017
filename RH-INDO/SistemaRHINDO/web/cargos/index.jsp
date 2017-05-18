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
                    <c:when test="${(funcionarioLogado.perfil == 'FUNCIONARIO') || (funcionarioLogado.perfil == 'GERENTE')}">
                        <div class="container">
                            <h1>Acesso Negado.</h1>
                            <h2>Você não pode acessar a essa página</h2>
                            TA LOGADO MAS É FUNCIONARIO OU GERENTE COMUM
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
                                        <a href="cadastrar.jsp" class="btn btn-info col-md-2">Novo</a>
                                    </div>
                                </div>
                            </div>
                            <!-- Row da lista de cargos e corpo da pagina: -->
                            <div class="row row-lista-corpo">
                                <!-- lista de cargos -->
                                <div class="col-md-4 lista-lat">
                                    <div class="list-group">
                                        <a class="list-group-item">Cargo 1</a>
                                        <a class="list-group-item">Cargo 2</a>
                                        <a class="list-group-item">Cargo 3</a>
                                        <a class="list-group-item">Cargo 4</a>
                                        <a class="list-group-item">Cargo 5</a>
                                        <a class="list-group-item">Cargo 6</a>       
                                    </div>
                                </div>
                                <!-- corpo da página -->
                                <div class="corpo col-md-8 corpo">
                                    <fieldset>
                                        <legend class="legenda">Editar Cargo</legend>
                                        <form>
                                            <div class="row">
                                                <div class="form-group col-md-8">
                                                    <label for="nome">Nome:</label>
                                                    <input type="text" class="form-control" id="nome" placeholder="Ex. Marketing">
                                                </div>
                                                <div class="form-group col-md-4">
                                                    <label for="salario">Salário:</label>
                                                    <input type="number" class="form-control" id="salario" placeholder="R$:">
                                                </div>
                                                <div class="form-group col-md-12">
                                                    <label for="requisitos">Requisitos:</label>
                                                    <textarea class="form-control" rows="3"></textarea>
                                                </div>
                                                <div class="form-group col-md-4">
                                                    <label for="carga">Carga de Trabalho (Horas/Mês):</label>
                                                    <input type="number" class="form-control" id="carga" placeholder="Horas/Mês" value="">
                                                </div>
                                                <div class="form-group col-md-4">
                                                    <label for="imposto">Desconto de Imposto (%):</label>
                                                    <input type="number" class="form-control" id="imposto" placeholder="%">
                                                </div>
                                            </div>
                                            <div class="text-right">
                                                <button type="submit" id="botao" class="btn btn-primary">Salvar Alterações</button>  
                                                <button class="btn btn-danger">Excluir</button>  
                                            </div>
                                        </form>
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
