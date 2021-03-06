<%-- 
    Document   : relatorios
    Created on : May 4, 2017, 4:20:51 PM
    Author     : JotaWind
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
        <title>Relat�rios - Sistema RHINDO</title>
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
                            <h2>Voc� n�o pode acessar a essa p�gina</h2>
                        </div>
                    </c:when>
                    <c:otherwise> 
                        <div class="container">
                            <!-- Row do input pesquisar: -->
                            <div class="row row-busca-titulo">
                                <div class="col-md-8 titulo">
                                    <h1 class="col-md-10">Relat�rios</h1>
                                </div>
                            </div>
                            <!-- Row do relat�rio: -->
                            <div class="row row-lista-corpo">
                                <!-- corpo da p�gina -->
                                <div class="col-md-12 corpo corpo-relatorio">
                                    <!-- Primeiro relat�rio -->
                                    <div class="corpo-relatorio">
                                        <legend>Lista de todos os Funcion�rios da empresa</legend>
                                        <div class="form-group col-md-2">
                                            <a href="RelatoriosServlet?action=relFuncionarios" class="btn btn-md btn-primary"><span class="glyphicon glyphicon-user"></span> Gerar Relat�rio</a>
                                        </div>                                    </div >
                                    <!-- Segundo relat�rio -->
                                    <div class="corpo-relatorio">
                                        <legend>Relat�rio de horas trabalhadas</legend>  
                                        <form method="POST" action="RelatoriosServlet?action=relMes">
                                            <div class="form-group col-md-4">
                                                <label for="depto">M�s</label>
                                                <select class="form-control" id="depto" name="mes">
                                                    <option value="01">Janeiro</option>
                                                    <option value="02">Fevereiro</option>
                                                    <option value="03">Mar�o</option>
                                                    <option value="04">Abril</option>
                                                    <option value="05">Maio</option>
                                                    <option value="06">Junho</option>
                                                    <option value="07">Julho</option>
                                                    <option value="08">Agosto</option>
                                                    <option value="09">Setembro</option>
                                                    <option value="10">Outubro</option>
                                                    <option value="11">Novembro</option>
                                                    <option value="12">Dezembro</option>
                                                </select>
                                            </div>
                                            <div class="form-group col-md-1">
                                                <label>&nbsp;</label>
                                                <input type="submit" class="btn btn-primary" value="Gerar Relat�rio"/>
                                            </div>
                                        </form>
                                    </div>
                                    <!-- Terceiro relat�rio -->
                                    <div class="corpo-relatorio">
                                        <legend>Funcion�rios com hor�rio atrasado</legend>
                                        <form method="POST" action="RelatoriosServlet?action=relAtrasados">
                                            <div class="form-group col-md-4">
                                                <label for="mes">M�s</label>
                                                <select class="form-control" id="depto" name="mes">
                                                    <option value="01">Janeiro</option>
                                                    <option value="02">Fevereiro</option>
                                                    <option value="03">Mar�o</option>
                                                    <option value="04">Abril</option>
                                                    <option value="05">Maio</option>
                                                    <option value="06">Junho</option>
                                                    <option value="07">Julho</option>
                                                    <option value="08">Agosto</option>
                                                    <option value="09">Setembro</option>
                                                    <option value="10">Outubro</option>
                                                    <option value="11">Novembro</option>
                                                    <option value="12">Dezembro</option>
                                                </select>
                                            </div>
                                            <div class="form-group col-md-1">
                                                <label>&nbsp;</label>
                                                <input type="submit" class="btn btn-primary" value="Gerar Relat�rio"/>
                                            </div>                                        
                                        </form>
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
