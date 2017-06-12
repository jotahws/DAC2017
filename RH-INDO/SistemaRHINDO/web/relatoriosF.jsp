<%-- 
    Document   : relatoriosF
    Created on : May 4, 2017, 4:22:51 PM
    Author     : JotaWind
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
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
                    <c:when test="${(funcionarioLogado.perfil != 'GERENTE') && (funcionarioLogado.perfil != 'FUNCIONARIO')}">
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
                                    <h1 class="col-md-10">Meus Relat�rios</h1>
                                </div>
                            </div>
                            <!-- Row do cadastro: -->
                            <div class="row row-lista-corpo">
                                <c:choose>
                                    <c:when test="${(param.status == 'error')}">
                                        <div class="alert alert-danger alert-dismissable">
                                            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                                            <p><strong>Ops!</strong> Selecione um per�odo de datas para gerar o relat�rio.</p>
                                        </div>
                                    </c:when>
                                </c:choose>
                                <!-- corpo da p�gina -->
                                    <div class="col-md-12 corpo corpo-relatorio">
                                        <legend>Relat�rio de horas trabalhadas</legend>  
                                        <form method="POST" action="RelatoriosServlet?action=relFunc">
                                            <div class="form-group col-md-4">
                                                <label for="mesde">De</label>
                                                <input type="date" name="mesde" class="mes form-control"> (dd/mm/aaaa)
                                                
                                            </div>
                                            <div class="form-group col-md-4">
                                                <label for="mesate">At�</label>
                                                <input type="date" name="mesate" class="mes form-control"> (dd/mm/aaaa)
                                            </div>
                                            <div class="form-group col-md-1">
                                                <label>&nbsp;</label>
                                                <input type="submit" class="btnSubmitData btn btn-primary" value="Gerar Relat�rio"/>
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
