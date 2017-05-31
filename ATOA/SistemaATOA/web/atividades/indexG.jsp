<%-- 
    Document   : index
    Created on : 10/05/2017, 11:05:10
    Author     : MauMau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Atividades - Sistema ATOA</title>
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
                                    <h1 class="col-md-10">Atividade</h1>
                                    <div class="text-right">
                                        <a href="${pageContext.request.contextPath}/ListaDepartamentosServlet?action=register" class="btn btn-info col-md-2">
                                            Novo Tipo
                                        </a>
                                    </div>
                                </div>
                            </div>
                            <!-- Row da lista de departamentos e atividades de capa departamento e corpo da pagina: -->
                            <div class="row row-lista-corpo">
                                <!-- lista de departamentos e atividades -->
                                <div class="col-md-4 lista-lat">
                                    <div class="list-group">
                                        <a class="list-group-item">Depto 1</a>
                                        <a class="list-group-item">Depto 2</a>
                                        <a class="list-group-item">Depto 3</a>
                                        <a class="list-group-item">Depto 4</a>
                                        <a class="list-group-item">Depto 5</a>
                                        <a class="list-group-item">Depto 6</a>       
                                    </div>
                                </div>
                                <!-- corpo da página -->
                                <div class="corpo col-md-8 corpo">
                                    <fieldset>
                                        <div class="tab-content">
                                            <div role="tabpanel" class="tab-pane active" id="">
                                                <h1 class="unselectable text-center cor-disabled">Selecione uma atividade</h1>
                                                <h1 class="text-center"><span class="glyphicon glyphicon-hand-left cor-disabled gi-5x"></span></h1>
                                            </div>
                                            <c:forEach var="item" items="${lista}">
                                                <div role="tabpanel" class="tab-pane fade" id="${item.id}">
                                                    <legend><h2>${item.nome}</h2></legend>
                                                    <p class="col-md-6">Tipo: ${item.tipo}</p>                                                    
                                                    <p class="col-md-6">Departamento: ${item.departamento}</p>
                                                </div>
                                            </c:forEach>
                                            <div class="panel panel-default">
                                                <!-- Default panel contents -->
                                                <div class="panel-heading">Funcionários</div>

                                                <!-- Tabela -->
                                                <table class="table">
                                                    <tr>
                                                        <th class="">Nome</th>
                                                        <th class="">Inicío</th>
                                                        <th class="">Cargo</th>
                                                        <th class=""></th>
                                                    </tr>
                                                    <tr>
                                                        <td class="tg-031e">Mauricio Araujo da Silva Pinto</td>
                                                        <td class="tg-031e">Hoje, 12:17</td>
                                                        <td class="tg-031e">Desenvolvedor</td>
                                                        <td class="tg-031e"><a class="btn btn-primary">Fechar atividade</a></td>
                                                    </tr>                                                    
                                                </table>
                                            </div>
                                            <div class="text">         
                                                <a class="btn btn-primary">Encerrar Atividades</a>
                                                <a href="editar.jsp" class="btn btn-success">Editar Tipo</a>
                                                <a class="btn btn-danger">Excluir Tipo</a>
                                            </div>
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
