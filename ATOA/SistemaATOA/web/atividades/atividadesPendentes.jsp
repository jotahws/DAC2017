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
                            <h2>Voc� n�o pode acessar a essa p�gina</h2>
                        </div>
                    </c:when>
                    <c:otherwise> 
                        <div class="container">
                            <!-- Row do input pesquisar: -->
                            <div class="col-md-2"><h1>&nbsp;</h1></div>
                            <div class="row col-md-8">
                                <div class="col-md-12 text-center titulo">
                                    <h1>Corre��es Pendentes</h1>
                                </div>
                            </div>
                            <div class="col-md-2"><h1>&nbsp;</h1></div>
                            <div class="col-md-2"><h1>&nbsp;</h1></div>
                            <!-- corpo da p�gina -->
                            <div class=" col-md-8 " style=" margin-top: 30px;">
                                <!-- TABELA -->
                                <div class=" panel panel-primary">
                                    <div class="panel-heading">Inicie ou encerre uma atividade:</div>
                                    <table class="table">
                                        <tr>
                                            <th>Atividade</th>
                                            <th>Descri��o</th>
                                            <th>Status</th>
                                        </tr>
                                        <tr>
                                            <td class="tg-031e">Relat�rios</td>
                                            <td class="tg-031e">Desenvolver um relat�rio sobre tal coisa asim assim assado como descrito na reuni�o</td>
                                            <td class="tg-031e"><p class="text-warning">N�o corrigida</p></td>
                                        </tr>      
                                        <tr>
                                            <td class="tg-031e">Cobran�a</td>
                                            <td class="tg-031e">Fazer a cobran�a dos relat�rios como pedido pelo chef�o</td>
                                            <td class="tg-031e"><p class="text-success">Corre��o aceita</p></td>
                                        </tr> 
                                        <tr>
                                            <td class="tg-031e">Back-end</td>
                                            <td class="tg-031e">Desenvolver um relat�rio sobre tal coisa asim assim assado como descrito na reuni�o dos chefes bosses</td>
                                            <td class="tg-031e"><p class="text-danger">Corre��o negada</p></td>
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
