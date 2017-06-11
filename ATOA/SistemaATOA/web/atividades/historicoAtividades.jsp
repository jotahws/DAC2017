<%-- 
    Document   : historicoAtividades
    Created on : May 31, 2017, 10:03:06 AM
    Author     : JotaWind
--%>


<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
                    <c:when test="${(funcionarioLogado.perfil != 'FUNCIONARIO' )}">
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
                                    <h1 class="col-md-10">Lista de Atividades</h1>
                                </div>
                            </div>
                            <!-- Row da lista de departamentos e atividades de capa departamento e corpo da pagina: -->
                            <div class="row row-lista-corpo">
                                <!-- lista de departamentos e atividades -->
                                <div class="col-md-1"><h1>&nbsp;</h1></div>
                                <div class="col-md-6 lista-lat">
                                    <fmt:formatDate value="${atividadeiniciada.inicio}" type="DATE" pattern="MM"/>
                                    <form method="POST" action="${pageContext.request.contextPath}/ListaAtividadeServlet?action=atvMesFunc" >
                                        <div class="col-md-4">
                                            <select name="mes" class="form-control">
                                                <option value="01" <c:if test="${param.mes == '01'}">selected</c:if>>Janeiro</option>
                                                <option value="02" <c:if test="${param.mes == '02'}">selected</c:if>>Fevereiro</option>
                                                <option value="03" <c:if test="${param.mes == '03'}">selected</c:if>>Março</option>
                                                <option value="04" <c:if test="${param.mes == '04'}">selected</c:if>>Abril</option>
                                                <option value="05" <c:if test="${param.mes == '05'}">selected</c:if>>Maio</option>
                                                <option value="06" <c:if test="${param.mes == '06'}">selected</c:if>>Junho</option>
                                                <option value="07" <c:if test="${param.mes == '07'}">selected</c:if>>Julho</option>
                                                <option value="08" <c:if test="${param.mes == '08'}">selected</c:if>>Agosto</option>
                                                <option value="09" <c:if test="${param.mes == '09'}">selected</c:if>>Setembro</option>
                                                <option value="10" <c:if test="${param.mes == '10'}">selected</c:if>>Outubro</option>
                                                <option value="11" <c:if test="${param.mes == '11'}">selected</c:if>>Novembro</option>
                                                <option value="12" <c:if test="${param.mes == '12'}">selected</c:if>>Dezembro</option>
                                                </select>
                                            </div>
                                            <input type="submit" class="btn btn-primary col-md-2" value="Listar"/>
                                        </form>
                                    </div>
                                    <!-- corpo da página -->
                                    <div class="col-md-10">
                                        <div class=" panel panel-primary margin-topo">
                                            <div class="panel-heading text-center">Histórico de Atividades</div>
                                        <c:choose>
                                            <c:when test="${empty atividades}"><h2 class="text-center unselectable cor-disabled">Nenhuma atividade para esse mês</h2></c:when>
                                            <c:otherwise>
                                                <table class="table">
                                                    <tr>
                                                        <th>Tipo da Atividade</th>
                                                        <th>Descrição</th>
                                                        <th>Início</th>
                                                        <th>Fim</th>
                                                    </tr>
                                                    <c:forEach var="item" items="${atividades}">
                                                        <tr>
                                                            <td class="tg-031e">${item.tipo.nome}</td>
                                                            <td class="tg-031e">${item.descricao}</td>
                                                            <td class="tg-031e">
                                                                <jsp:useBean id="data" class="java.util.Date"/>
                                                                <c:choose>
                                                                    <c:when test="${data.date == item.inicio.date && data.day == item.inicio.day}">
                                                                        <c:out value="Hoje,"/>
                                                                        <fmt:formatDate value="${item.inicio}" type="BOTH" pattern=" HH:mm"/>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <fmt:formatDate value="${item.inicio}" type="BOTH" pattern="dd/MM/yyy HH:mm"/>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </td>
                                                            <td class="tg-031e">
                                                                <c:choose>
                                                                    <c:when test="${data.date == item.fim.date && data.day == item.fim.day}">
                                                                        <c:out value="Hoje,"/>
                                                                        <fmt:formatDate value="${item.fim}" type="BOTH" pattern=" HH:mm"/>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <fmt:formatDate value="${item.fim}" type="BOTH" pattern="dd/MM/yyy HH:mm"/>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </td>

                                                        </tr>  
                                                    </c:forEach>
                                                </c:otherwise>
                                            </c:choose>
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
