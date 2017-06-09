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
                            <div class="col-md-12 corpo">
                                <div class="col-md-6">
                                    <h3 class="text-center ">Funcionario Consolidado</h3>
                                    <div class=" panel panel-primary">
                                        <table class="table">
                                            <tr>
                                                <th>Funcionário</th>
                                                <th>CPF</th>
                                            </tr>
                                            <tr>
                                                <td class="tg-031e">João Alberto</td>
                                                <td class="tg-031e">0452291829</td>
                                            </tr>      
                                            <tr>
                                                <td class="tg-031e">Ricardo Vicentin</td>
                                                <td class="tg-031e">9201992039</td>
                                            </tr> 
                                        </table>
                                    </div> 
                                    <input type="submit" class="btn btn-primary" value="Gerar relatório"/>

                                </div>
                                <div  class="col-md-6 relatorio-dep">
                                    <h3 class="text-center">Por departamento</h3>
                                    <div class=" panel panel-primary">
                                        <table class="table">
                                            <tr>
                                                <th>Departamento</th>
                                                <th>Localização</th>
                                            </tr>
                                            <tr>
                                                <td class="tg-031e">Recursos Humanos</td>
                                                <td class="tg-031e">Sala 10</td>
                                            </tr>      
                                            <tr>
                                                <td class="tg-031e">Marketing</td>
                                                <td class="tg-031e">Sala 2</td>
                                            </tr>
                                        </table>

                                    </div>
                                    <form action="action">
                                        <div class="form-group">
                                            <h4 class="col-md-12">Dia:</h4>
                                            <div class="col-md-5">
                                                <input class="form-control " type="date"/>
                                            </div>
                                            <div class="col-md-7">
                                                <p >(dd/mm/aaaa)</p><br>
                                            </div>
                                            <input type="submit" class="btn btn-primary" value="Gerar relatório"/>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
            </c:otherwise>
        </c:choose>
    </body>
</html>
