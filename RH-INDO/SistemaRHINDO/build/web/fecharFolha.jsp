<%-- 
    Document   : fecharFolha
    Created on : May 4, 2017, 4:21:04 PM
    Author     : JotaWind
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Folha de Pagamento - Sistema RHINDO</title>
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
                                <div class="col-md-8 titulo">
                                    <h1 class="col-md-10">Fechar Folha de Pagamento</h1>
                                </div>
                            </div>
                            <!-- Row do cadastro: -->
                            <div class="row row-lista-corpo">
                                <c:choose>
                                    <c:when test="${param.status == 'success'}">
                                        <div class="col-md-12 alert alert-success alert-dismissable">
                                            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                                            <p> A folha de pagamento foi fechada com sucesso.</p>
                                        </div>
                                    </c:when>
                                    <c:when test="${param.status == 'error'}">
                                        <div class="col-md-12 alert alert-danger alert-dismissable">
                                            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                                            <p> Ops! Ocorreu um erro ao fechar folha de pagamento.</p>
                                        </div>
                                    </c:when>
                                </c:choose>
                                <!-- corpo da página -->
                                <div class="col-md-12 corpo">
                                    <legend>&nbsp;</legend>

                                    <!-- lista de departamentos e atividades -->
                                    <div class="col-md-1"><h1>&nbsp;</h1></div>
                                    <div class="col-md-7 lista-lat">
                                        <fmt:formatDate value="${atividadeiniciada.inicio}" type="DATE" pattern="MM"/>
                                        <form method="POST" action="${pageContext.request.contextPath}/FolhaPagamentoServlet?action=fechar" >
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
                                                <input type="submit" class="btn btn-primary col-md-3" value="Fechar Folha"/>
                                            </form>
                                        </div>
                                        <!-- corpo da página -->
                                        <div class="col-md-10">
                                            <div class=" panel panel-primary margin-topo">
                                                <div class="panel-heading">Lista da folha de pagamento do mês:</div>
                                            <c:choose>
                                                <c:when test="${horas == null}"><h2 class="text-center unselectable cor-disabled">Selecione um mês e clique em "Fechar Folha"</h2></c:when>
                                                <c:otherwise>
                                                    <table class="table">
                                                        <tr>
                                                            <th>Funcionário</th>
                                                            <th>Email</th>
                                                            <th>Horas</th>
                                                            <th>Departamento</th>
                                                            <th>Cargo</th>
                                                        </tr>
                                                        <jsp:useBean id="folha" class="beans.HorasTrabalhadas"/>
                                                        <c:forEach var="item" items="${horas}">
                                                            <tr>
                                                                <td><c:out value="${item.func.nome}"/></td>
                                                                <td><c:out value="${item.func.email}"/></td>
                                                                <td><c:out value="${item.horasTrabalhadas}"/></td>
                                                                <td><c:out value="${item.func.departamento.nome}"/></td>
                                                                <td><c:out value="${item.func.cargo.nome}"/></td>
                                                            </tr>
                                                        </c:forEach>
                                                    </table>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
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
