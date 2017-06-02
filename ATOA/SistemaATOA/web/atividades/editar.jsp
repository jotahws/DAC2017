<%-- 
    Document   : editar
    Created on : May 31, 2017, 12:28:25 PM
    Author     : JotaWind
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Tipo de Atividade - Sistema ATOA</title>
    </head>
    <body><%@include file="/pre-fabricado/cabecalho.jsp" %>
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
                        <jsp:useBean id="tipoAtv" class="beans.TipoAtividade"/>
                        
                        <div class="container">
                            <div class="row row-busca-titulo">
                                <div class="col-md-8 titulo">
                                    <h1 class="col-md-10">Editar Tipo de Atividade</h1>
                                </div>
                            </div>
                            <div class="col-md-12 corpo">
                                <fieldset>
                                    <form method="POST" action="${pageContext.request.contextPath}/" class="cadastro">
                                        <div class="row">
                                            <legend>Dados do Tipo</legend>
                                            <div class="form-group col-md-8">
                                                <label for="nome">Nome do tipo de atividade:</label>
                                                <input type="text" class="form-control" id="nome" name="nome" value="${tipo.nome}">
                                            </div>
                                            <div class="form-group col-md-4">
                                                <label for="departamento">Departamento:</label>
                                                <select class="form-control" id="departamento" name="departamento" >
                                                    <jsp:useBean id="depto" class="beans.Departamento"/>
                                                    <c:set var="lista" value="${deptos}"/>
                                                    <c:forEach var="item" items="${lista}">
                                                        <option value="${item.id}" <c:if test="${tipo.departamento.id == item.id}">selected</c:if>>
                                                            <c:out value="${item.nome}"/>
                                                        </option>
                                                    </c:forEach>
                                                </select>
                                            </div>                                        
                                        </div>
                                        <div class="text-right">
                                            <button type="submit" id="botao" class="btn btn-primary">Cadastrar</button>  
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
