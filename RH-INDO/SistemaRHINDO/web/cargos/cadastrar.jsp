<%-- 
    Document   : novo
    Created on : May 4, 2017, 4:03:33 PM
    Author     : JotaWind
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
        <title>Cadastrar Cargo - Sistema RHINDO</title>
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
                            <div class="col-md-8 titulo">
                                <h1 class="col-md-10">Cadastrar Cargo</h1>
                            </div>
                        </div>
                        <!-- Row do cadastro: -->
                        <div class="row row-lista-corpo">
                            ${status}
                            <!-- corpo da página -->
                            <div class="col-md-12 corpo">
                                <fieldset>
                                    <form method="POST" action="${pageContext.request.contextPath}/CargoServlet?action=register" class="cadastro">
                                        <div class="row">
                                            <legend>Dados do Cargo</legend>
                                            <div class="form-group col-md-6">
                                                <label for="nome">Nome:</label>
                                                <input type="text" class="form-control" id="nome" name="nome" placeholder="Ex. Marketing">
                                            </div>
                                            <div class="form-group col-md-2">
                                                <label for="salario">Salário:</label>
                                                <input type="number" class="form-control" id="salario" name="salario" placeholder="R$:">
                                            </div>
                                            <div class="form-group col-md-4">
                                                <label for="carga">Carga de Trabalho (Horas/Mês):</label>
                                                <input type="number" class="form-control" id="carga" name="carga" placeholder="Horas/Mês" value="">
                                            </div>
                                            <div class="form-group col-md-8">
                                                <label for="requisitos">Requisitos:</label>
                                                <textarea class="form-control" id="requisitos" name="requisitos" rows="3"></textarea>
                                            </div>
                                            <div class="form-group col-md-4">
                                                <label for="imposto">Desconto de Imposto (%):</label>
                                                <input type="number" class="form-control" id="imposto" name="imposto" placeholder="%">
                                            </div>
                                        </div>
                                        <div class="text-right">
                                            <button type="submit" id="botao" class="btn btn-primary">Cadastrar</button>  
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
