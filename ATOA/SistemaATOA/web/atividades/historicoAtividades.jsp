<%-- 
    Document   : historicoAtividades
    Created on : May 31, 2017, 10:03:06 AM
    Author     : JotaWind
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
                    <c:when test="${(funcionarioLogado.perfil != 'GERENTE' && funcionarioLogado.perfil != 'FUNCIONARIO' )}">
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
                                <div class="col-md-3 lista-lat">
                                    <div>
                                        <form>
                                            <select class="form-control">
                                                <option>Janeiro</option>
                                                <option>Fevereiro</option>
                                                <option>Março</option>
                                            </select>
                                        </form>
                                    </div>
                                </div>
                                <!-- corpo da página -->
                                <div class="col-md-7">
                                    <div class=" panel panel-primary">
                                        <div class="panel-heading">Histórico de Atividades:</div>
                                        <table class="table">
                                            <tr>
                                                <th>Início</th>
                                                <th>Fim</th>
                                                <th>Atividade</th>
                                                <th>Descrição</th>
                                            </tr>
                                            <tr>
                                                <td class="tg-031e">8:09</td>
                                                <td class="tg-031e">12:30</td>
                                                <td class="tg-031e">Relatório</td>
                                                <td class="tg-031e">Desenvolver um relatório sobre tal coisa asim assim assado como descrito na reunião</td>
                                            </tr>      
                                            <tr>
                                                <td class="tg-031e">12:34</td>
                                                <td class="tg-031e">18:02</td>
                                                <td class="tg-031e">Cobrança</td>
                                                <td class="tg-031e">Fazer a cobrança dos relatórios como pedido pelo chefão</td>
                                            </tr> 
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
