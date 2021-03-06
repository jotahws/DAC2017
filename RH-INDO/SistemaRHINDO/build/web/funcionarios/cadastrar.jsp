<%-- 
    Document   : cadastrar
    Created on : May 4, 2017, 4:00:37 PM
    Author     : JotaWind
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
        <title>Cadastrar Funcion?rio - Sistema RHINDO</title>
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
                            <h2>Voc? n?o pode acessar a essa p?gina</h2>
                        </div>
                    </c:when>
                    <c:otherwise> 
                        <div class="container">
                            <!-- Row do input pesquisar: -->
                            <div class="row row-busca-titulo">
                                <div class="col-md-8 titulo">
                                    <h1 class="col-md-10">Cadastrar Funcion?rio</h1>
                                </div>
                            </div>
                            <!-- Row do cadastro: -->
                            <div class="row row-lista-corpo">
                                <c:choose>
                                    <c:when test="${(param.status == 'success')}">
                                        <div class="alert alert-success alert-dismissable">
                                            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                                            <p> <strong>Successo!</strong> O novo funcion?rio foi cadastrado. <a href="CarregaListaFuncServlet?action=listaFuncionarios">Lista de Funcion?rios</a></p>
                                        </div>
                                    </c:when>
                                    <c:when test="${(param.status == 'error')}">
                                        <div class="alert alert-danger alert-dismissable">
                                            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                                            <p> <strong>Ops!</strong> Ocorreu um erro ao cadastrar o funcion?rio. Tente novamente.</p>
                                        </div>
                                    </c:when>
                                </c:choose>
                                <!-- corpo da p?gina -->
                                <div class="col-md-12 corpo">
                                    <fieldset>
                                        <form action="${pageContext.request.contextPath}/FuncionarioServlet?action=register" method="POST" class="cadastro">
                                            <div class="row">
                                                <legend>Geral</legend>
                                                <div class="form-group col-md-4">
                                                    <label for="nome">Nome:</label>
                                                    <input type="text" required class="form-control" id="nome" name="nome" placeholder="Maria dos Santos">
                                                </div>
                                                <div class="form-group col-md-2">
                                                    <label for="cpf">CPF:</label>
                                                    <input type="text" required class="form-control" id="cpf" name="cpf">
                                                </div>
                                                <div class="form-group col-md-3">
                                                    <label for="rg">RG:</label>
                                                    <input type="text" required class="form-control" id="rg" name="rg">
                                                </div>
                                                <div class="form-group col-md-3">
                                                    <label for="senha">Senha:</label>
                                                    <input type="password" required class="form-control" id="senha" name="senha">
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group col-md-2">
                                                    <label for="celular">Celular</label>
                                                    <input type="text" required class="form-control" id="celular" name="celular">
                                                </div>
                                                <div class="form-group col-md-3">
                                                    <label for="email">Email</label>
                                                    <input type="email" required class="form-control" id="email" name="email" placeholder="joe@exemplo.com">
                                                </div>
                                                <div class="form-group col-md-2">
                                                    <label for="depto">Departamento</label>
                                                    <select class="form-control" id="depto" name="depto">
                                                        <jsp:useBean id="depto" class="beans.Departamento"/>
                                                        <c:set var="lista" value="${deptos}"/>
                                                        <c:forEach var="item" items="${lista}">
                                                            <option value="${item.id}">
                                                                <c:out value="${item.nome}"/>
                                                            </option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <div class="form-group col-md-3">
                                                    <label for="cargo">Cargo</label>
                                                    <select class="form-control" id="cargo" name="cargo">
                                                        <jsp:useBean id="cargo" class="beans.Cargo"/>
                                                        <c:set var="lista" value="${cargos}"/>
                                                        <c:forEach var="item" items="${lista}">
                                                            <option value="${item.id}">
                                                                <c:out value="${item.nome}"/>
                                                            </option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <div class="form-group col-md-2">
                                                    <label for="perfil">Perfil do Funcion?rio</label>
                                                    <select class="form-control" id="perfil" name="perfil">
                                                        <option value="FUNCIONARIO">Funcion?rio Comum</option>
                                                        <option value="GERENTE">Gerente</option>
                                                        <option value="GERENTE-RH">Gerente de RH</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <legend>Endere?o</legend>
                                                <div class="form-group col-md-4">
                                                    <label for="cep">CEP</label>
                                                    <input type="text" class="form-control" id="cep" name="cep"/>
                                                </div>
                                                <div class="form-group col-md-6">
                                                    <label for="rua">Rua</label>
                                                    <input type="text" class="form-control" id="rua" name="rua" placeholder="Ex. Rua das Dores"/>
                                                </div>
                                                <div class="form-group col-md-2">
                                                    <label for="numero">N?mero</label>
                                                    <input type="number" class="form-control" id="numero" name="numero"/>
                                                </div>
                                                <div class="form-group col-md-4">
                                                    <label for="bairro">Bairro</label>
                                                    <input type="text" class="form-control" id="bairro" name="bairro" placeholder="Ex. S?o Francisco"/>
                                                </div>
                                                <div class="form-group col-md-4">
                                                    <label for="cidade">Cidade</label>
                                                    <input type="text" class="form-control" id="cidade" name="cidade" placeholder="Ex. Curitiba"/>
                                                </div>
                                                <div class="form-group col-md-1">
                                                    <label for="uf">UF</label>
                                                    <select class="form-control" id="uf" name="uf">
                                                        <jsp:useBean id="uf" class="beans.Estado"/>
                                                        <c:set var="lista" value="${estados}"/>
                                                        <c:forEach var="item" items="${lista}">
                                                            <option>
                                                                <c:out value="${item.uf}"/>
                                                            </option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="text-right">

                                                <button type="submit" id="btnSubmitFunc" class="btn btn-primary form-group">Cadastrar</button>  

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
