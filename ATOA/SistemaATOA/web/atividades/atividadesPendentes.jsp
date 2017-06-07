<%-- 
    Document   : atividadesPendente
    Created on : 11/05/2017, 16:01:30
    Author     : MauMau
--%>


<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
                                    <h1>Correções Pendentes</h1>
                                </div>
                            </div>
                            <div class="col-md-2"><h1>&nbsp;</h1></div>
                            <div class="col-md-2"><h1>&nbsp;</h1></div>
                            <!-- corpo da página -->
                            <div class=" col-md-8 " style=" margin-top: 30px;">
                                <!-- TABELA -->
                                <div class=" panel panel-primary">
                                    <div class="panel-heading">Inicie ou encerre uma atividade:</div>
                                    <table class="table">
                                        <tr>
                                            <th>Atividade</th>
                                            <th>Descrição</th>
                                            <th>Status</th>
                                        </tr>
                                        <tr>
                                            <td class="tg-031e">Relatórios</td>
                                            <td class="tg-031e">Desenvolver um relatório sobre tal coisa asim assim assado como descrito na reunião</td>
                                            <td class="tg-031e"><p class="text-warning">Não corrigida</p></td>
                                        </tr>      
                                        <tr>
                                            <td class="tg-031e">Cobrança</td>
                                            <td class="tg-031e">Fazer a cobrança dos relatórios como pedido pelo chefão</td>
                                            <td class="tg-031e"><p class="text-success">Correção aceita</p></td>
                                        </tr> 
                                        <tr>
                                            <td class="tg-031e">Back-end</td>
                                            <td class="tg-031e">Desenvolver um relatório sobre tal coisa asim assim assado como descrito na reunião dos chefes bosses</td>
                                            <td class="tg-031e"><p class="text-danger">Correção negada</p></td>
                                        </tr> 
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
