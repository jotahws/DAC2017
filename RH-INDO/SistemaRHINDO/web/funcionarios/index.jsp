<%-- 
    Document   : index
    Created on : May 3, 2017, 5:03:55 PM
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <title>Funcionários</title>
    </head>
    <body>
        <%@include file="../pre-fabricado/cabecalho.jsp" %>
        <%--   <jsp:getProperty name="funcionarioLogado" property="nome"/> --%>
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
                                        <input type="text" class="form-control" placeholder="Nome, CPF...">
                                        <span class="input-group-btn">
                                            <button class="btn btn-default" type="button">Pesquisar</button>
                                        </span>
                                    </div>
                                </div>
                                <div class="col-md-8 titulo">
                                    <h1 class="col-md-10">Funcionários</h1>
                                    <div class="text-right">
                                        <a href="${pageContext.request.contextPath}/CarregaListaFuncServlet?action=register" class="btn btn-info col-md-2">Novo</a>
                                    </div>
                                </div>
                            </div>
                            <!-- Row da lista de func e corpo da pagina: -->
                            <div class="row row-lista-corpo">
                                <!-- lista de funcioarios: -->
                                <div class="col-md-4 lista-lat">
                                    <div class="list-group">
                                        <jsp:useBean id="funcionario" class="beans.Funcionario"/>
                                        <c:set var="lista" value="${funcs}"/>
                                        <c:forEach var="item" items="${lista}">
                                            <a href="#${item.id}" role="tab" data-toggle="tab" class=" list-group-item">
                                                <c:out value="${item.nome}"/>
                                            </a>
                                        </c:forEach>
                                    </div>
                                </div>
                                <!-- corpo da página -->
                                <c:choose>
                                    <c:when test="${(param.status == 'successDelete')}">
                                        <div class="col-md-8 alert alert-warning alert-dismissable">
                                            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                                            <p> <span class="glyphicon glyphicon-trash"></span> O funcionário foi <strong>excluído</strong> com sucesso.</p>
                                        </div>
                                    </c:when>
                                    <c:when test="${(param.status == 'successEdit')}">
                                        <div class="col-md-8 alert alert-success alert-dismissable">
                                            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                                            <p> <span class="glyphicon glyphicon-edit"></span> <strong>Successo!</strong> O funcionário foi editado.</p>
                                        </div>
                                    </c:when>
                                    <c:when test="${(param.status == 'error')}">
                                        <div class="col-md-8 alert alert-danger alert-dismissable">
                                            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                                            <p> <strong>Ops!</strong> Ocorreu um erro! Tente novamente.</p>
                                        </div>
                                    </c:when>                                    
                                </c:choose>
                                <div class="corpo col-md-8 corpo">                                    
                                    <fieldset>
                                        <div class="tab-content">
                                            <div role="tabpanel" class="tab-pane active" id="">
                                                <h1 class="unselectable text-center cor-disabled">Selecione um funcionário</h1>
                                                <h1 class="text-center"><span class="glyphicon glyphicon-hand-left cor-disabled gi-5x"></span></h1>
                                            </div>
                                            <c:forEach var="item" items="${lista}">
                                                <div role="tabpanel" class="tab-pane fade" id="${item.id}">
                                                    <legend><h2>${item.nome}</h2></legend>
                                                    <p class="col-md-6">CPF: ${item.cpf}</p>
                                                    <p class="col-md-6">RG: ${item.rg}</p>
                                                    <p class="col-md-6">Celular: ${item.celular}</p>
                                                    <p class="col-md-6">Email: ${item.email}</p>
                                                    <p class="col-md-6">Perfil do Funcionário: ${item.perfil}</p>
                                                    <p class="col-md-6">Departamento: ${item.departamento.nome}</p>
                                                    <p class="col-md-6">Cargo: ${item.cargo.nome}</p>
                                                    <legend>Endereço</legend>
                                                    <p class="col-md-6">CEP: ${item.endereco.cep}</p>
                                                    <p class="col-md-6">Rua: ${item.endereco.rua}</p>
                                                    <p class="col-md-6">Número: ${item.endereco.numero}</p>
                                                    <p class="col-md-6">Bairro: ${item.endereco.bairro}</p>
                                                    <p class="col-md-6">Cidade: ${item.endereco.cidade.nome}</p>
                                                    <p class="col-md-6">Estado: ${item.endereco.cidade.estado.nome}</p>
                                                    <div class="text-right">
                                                        <a href="CarregaListaFuncServlet?action=edit&id=${item.id}" class="btn btn-primary">Editar</a>
                                                        <a class="btn btn-success">Folha de Pagamento</a>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </fieldset>
                                </div>
                            </div>
                        </div>
                    </body>
                </c:otherwise>
            </c:choose>
        </c:otherwise>
    </c:choose>
</html>
