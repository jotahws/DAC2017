<%-- 
    Document   : corrigir
    Created on : 10/05/2017, 11:08:57
    Author     : MauMau
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Corrigir - Sistema ATOA</title>
    </head>
    <body>
        <%@include file="/pre-fabricado/cabecalho.jsp" %>
        <c:choose>
            <c:when test="${funcionarioLogado.email == null}">
                <c:redirect url="/login.jsp"/>
            </c:when>
            <c:otherwise>
                <c:choose>
                    <c:when test="${(funcionarioLogado.perfil != 'FUNCIONARIO')}">
                        <div class="container">
                            <h1>Acesso Negado.</h1>
                            <h2>Você não pode acessar a essa página</h2>
                        </div>
                    </c:when>
                    <c:otherwise> 
                        <div class="container">
                            <div class="row row-busca-titulo">
                                <div class="col-md-8 titulo">
                                    <h1 class="col-md-10">Corrigir Atividade</h1>
                                </div>
                            </div>
                            <div class="col-md-12 corpo">
                                <fieldset>
                                    <form method="POST" action="${pageContext.request.contextPath}/AtividadeServlet?action=corrigir" class="cadastro">
                                        <div class="row">
                                            <legend>Dados da Atividade</legend>
                                            <div class="form-group col-md-8">
                                                <label for="nome">Nome do tipo de atividade:</label>
                                                <input type="text" disabled class="form-control" id="nome" name="nome" value="${atividade.tipo.nome}">
                                            </div>
                                            <div class="form-group col-md-4">
                                                <label for="departamento">Departamento:</label>
                                                <select class="form-control" disabled id="departamento" name="departamento" >
                                                    <option>
                                                        ${atividade.tipo.departamento.nome}
                                                    </option>
                                                </select>
                                            </div>
                                            <div class="form-group col-md-12">
                                                <label for="descricao">Descrição:</label>
                                                <textarea class="form-control" id="descricao" name="descricao" rows="3"></textarea>
                                            </div>                                            
                                        </div>
                                        <div class="text-right">
                                            <button type="submit" id="botao" class="btn btn-primary">Salvar modificações</button>  
                                        </div>
                                    </form>
                                </fieldset>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
            </c:otherwise>
        </c:choose>
    </body>
</html>
