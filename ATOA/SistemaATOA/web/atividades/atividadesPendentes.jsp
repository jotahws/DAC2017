<%-- 
    Document   : atividadesPendente
    Created on : 11/05/2017, 16:01:30
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
                    <c:when test="${(funcionarioLogado.perfil != 'FUNCIONARIO' )}">
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
                                    <h1>Correções Pendentes</h1>
                                </div>
                            </div>
                            <div class="col-md-2"><h1>&nbsp;</h1></div>
                            <div class="col-md-1"><h1>&nbsp;</h1></div>
                            <!-- corpo da página -->
                            <div class=" col-md-10 " style=" margin-top: 30px;">
                                <!-- TABELA -->
                                <div class=" panel panel-primary">
                                    <div class="panel-heading text-center">Este é o seu histórico de correções</div>
                                    <table class="table table-striped">
                                        <tr>
                                            <th>Atividade</th>
                                            <th>Descrição</th>
                                            <th>Data de Início</th>
                                            <th>Status</th>
                                        </tr>
                                        <c:forEach var="item" items="${edicoes}" >
                                            <tr>
                                                <td class="tg-031e col-md-3">${item.atividade.tipo.nome}</td>
                                                <td class="tg-031e col-md-5">${item.descricao}</td>
                                                <td class="tg-031e col-md-2">
                                                    <jsp:useBean id="data" class="java.util.Date"/>
                                                    <c:choose>
                                                        <c:when test="${data.date == item.atividade.inicio.date && data.day == item.atividade.inicio.day}">
                                                            <c:out value="Hoje,"/>
                                                            <fmt:formatDate value="${item.atividade.inicio}" type="BOTH" pattern=" HH:mm"/>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <fmt:formatDate value="${item.atividade.inicio}" type="BOTH" pattern="dd/MM/yyy HH:mm"/>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td class="tg-031e">
                                                    <c:choose>
                                                        <c:when test="${item.statusAprovacao == 'PENDENTE'}"><p class="text-warning">Correção em andamento</p></c:when>
                                                        <c:when test="${item.statusAprovacao == 'RECUSADO'}"><p class="text-danger">Correção Recusada</p></c:when>
                                                        <c:when test="${item.statusAprovacao == 'APROVADO'}"><p class="text-success">Correção aprovada</p></c:when>
                                                    </c:choose>
                                                    
                                                </td>
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
