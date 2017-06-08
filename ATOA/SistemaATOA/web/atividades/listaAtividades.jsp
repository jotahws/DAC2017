<%-- 
    Document   : listaAtividades
    Created on : 11/05/2017, 16:06:20
    Author     : MauMau
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quadro de Atividades</title>
    </head>
    <body>
        <%@include file="/pre-fabricado/cabecalho.jsp" %>
        <c:choose>
            <c:when test="${funcionarioLogado.email == null}">
                <c:redirect url="/login.jsp"/>
            </c:when>
            <c:otherwise>
                <c:choose>
                    <c:when test="${(funcionarioLogado.perfil != 'GERENTE' && funcionarioLogado.perfil != 'FUNCIONARIO' )}">
                        <div class="container">
                            <h1>Acesso Negado.</h1>
                            <h2>Você não pode acessar a essa página</h2>
                        </div>
                    </c:when>
                    <c:otherwise> 
                        <div class="container">
                            <!-- Row do input pesquisar: -->
                            <div class="col-md-2"><h1>&nbsp;</h1></div>
                            <div class="row col-md-8">
                                <div class="col-md-12 text-center titulo">
                                    <h1>Quadro de Atividades</h1>
                                </div>
                            </div>
                            <div class="col-md-2"><h1>&nbsp;</h1></div>
                            <div class="col-md-2"><h1>&nbsp;</h1></div>
                            <!-- corpo da página -->
                            <div class=" col-md-8 " style=" margin-top: 30px;">
                                <div class=" panel panel-success">
                                    <div class="panel-heading">Atividade em andamento</div>
                                    <table class="table">

                                        <c:choose>
                                            <c:when test="${atividadeiniciada != null}">
                                                <tr>
                                                    <th>Atividade</th>
                                                    <th>Início</th>
                                                    <th></th>
                                                    <th></th>
                                                    <th></th>
                                                </tr>
                                                <jsp:useBean id="data" class="java.util.Date"/>
                                                <tr>
                                                    <td class="tg-031e"><c:out value="${atividadeiniciada.tipo.nome}"/></td>
                                                    <td class="tg-031e">
                                                        <c:choose>
                                                            <c:when test="${data.date == atividadeiniciada.inicio.date && data.day == atividadeiniciada.inicio.day}">
                                                                <c:out value="Hoje,"/>
                                                                <fmt:formatDate value="${atividadeiniciada.inicio}" type="BOTH" pattern=" HH:mm"/>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <fmt:formatDate value="${atividadeiniciada.inicio}" type="BOTH" pattern="dd/MM/yyy HH:mm"/>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                    <td class="tg-031e"><a class="col-md-12 btn btn-primary">Pausar</a></td>
                                                    <td class="tg-031e"><a href="AtividadeServlet?action=Encerrar" class="col-md-12 btn btn-danger">Encerrar</a></td>
                                                    <td class="tg-031e"><a href="corrigir.jsp" class="btn btn-warning col-md-12">Corrigir</a></td>   
                                                </tr>
                                            </c:when>
                                            <c:otherwise>
                                                <h1 class="text-center unselectable cor-disabled">
                                                    <span class="glyphicon glyphicon-arrow-down"></span>
                                                    Vamos trabalhar?
                                                    <span class="glyphicon glyphicon-arrow-down"></span>
                                                </h1>
                                            </c:otherwise>
                                        </c:choose>
                                    </table>
                                </div>

                                <!-- TABELA -->
                                <div class=" panel panel-primary">
                                    <div class="panel-heading">Inicie uma atividade:</div>
                                    <table class="table">
                                        <tr>
                                            <th>Atividade</th>
                                            <th>Departamento</th>
                                            <th></th>
                                            <th></th>
                                        </tr>
                                        <c:set var="lista" value="${tipos}"/>
                                        <c:forEach var="item" items="${lista}">
                                            <tr>
                                                <td class="tg-031e"><c:out value="${item.nome}"/></td>
                                                <td class="tg-031e"><c:out value="${item.departamento.nome}"/></td>
                                                <td class="tg-031e"><a href="AtividadeServlet?action=IniciarAtv&id=${item.id}" class="col-md-12 btn btn-primary">Iniciar</a></td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </div>
                            </div>
                            <div class="col-md-2"><h1>&nbsp;</h1></div>
                        </div>
                    </c:otherwise>
                </c:choose>
            </c:otherwise>
        </c:choose>
    </body>
</html>
