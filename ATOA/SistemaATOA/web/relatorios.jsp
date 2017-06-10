<%-- 
    Document   : relatorios
    Created on : 10/05/2017, 11:04:23
    Author     : MauMau
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Relatórios - Sistema ATOA</title>
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
                            <div class="row row-busca-titulo">
                                <div class="col-md-8 titulo">
                                    <h1 class="col-md-10">Relatórios
                                </div>
                            </div>
                            <c:choose>
                                <c:when test="${(param.status == 'erro')}">
                                    <div class="alert alert-danger alert-dismissable">
                                        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                                        <p> <strong>Ops!</strong> O funcionário não contém nenhuma atividade.</p>
                                    </div>
                                </c:when>
                                <c:when test="${(param.status == 'erroDepart')}">
                                    <div class="alert alert-danger alert-dismissable">
                                        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                                        <p> <strong>Ops!</strong> O Departamento não contém nenhuma atividade.</p>
                                    </div>
                                </c:when>
                            </c:choose>
                            <div class="col-md-12 corpo">
                                <div class="col-md-6">
                                    <h3 class="text-center ">Por Funcionários Consolidado</h3>
                                    <div class=" panel panel-primary">
                                        <table class="table">
                                            <tr>
                                                <th>Funcionario</th>
                                                <th>Email</th>
                                                <th>Cargo</th>
                                                <th></th>
                                            </tr>
                                            <!--<c:set var="listaFunc" value="${listaFunc}"/>-->
                                            <c:forEach var="item" items="${listaFunc}">
                                                <tr>
                                                    <td class="tg-031e"><c:out value="${item.nome}"/></td>
                                                    <td class="tg-031e"><c:out value="${item.email}"/></td>
                                                    <td class="tg-031e"><c:out value="${item.cargo.nome}"/></td>
                                                    <td class="tg-031e"><a href="RelatorioServlet?action=relConsolidado&id=${item.id}" class="col-md-12 btn btn-success">Relatório</a></td>                                                    
                                                </tr>
                                            </c:forEach>
                                        </table>
                                    </div> 

                                </div>
                                <div  class="col-md-6 relatorio-dep">
                                    <h3 class="text-center">Por Departamentos Consolidados</h3>
                                    <h4 class="col-md-12">Selecione um Dia:</h4>
                                    <div class="col-md-5">
                                        <input class="form-control " value="" type="date"/>
                                    </div>
                                    <div class="col-md-7">
                                        <p >(dd/mm/aaaa)</p><br>
                                    </div>
                                    <div class=" panel panel-primary">
                                        <table class="table">
                                            <tr>
                                                <th>Departamento</th>
                                                <th>Localização</th>
                                                <th></th>
                                            </tr>
                                            <!--<c:set var="listaDepart" value="${listaDepart}"/>-->
                                            <c:forEach var="item" items="${listaDepart}">
                                                <tr>
                                                    <td class="tg-031e"><c:out value="${item.nome}"/></td>
                                                    <td class="tg-031e"><c:out value="${item.localizacao}"/></td>
                                                    <td class="tg-031e"><a href="RelatorioServlet?action=relDepartamento&id=${item.id}" class="col-md-12 btn btn-success">Relatório</a></td>
                                                </tr>
                                            </c:forEach>
                                        </table>

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
