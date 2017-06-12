<%-- 
    Document   : contraCheque
    Created on : May 4, 2017, 4:23:03 PM
    Author     : JotaWind
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contra-cheque - Sistema RHINDO</title>
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
                            <h2>Você não pode acessar a essa página</h2>
                        </div>
                    </c:when>
                    <c:otherwise> 
                        <div class="container">
                            <!-- Row do input pesquisar: -->
                            <div class="row row-busca-titulo">
                                <div class="col-md-8 titulo">
                                    <h1 class="col-md-10">Holerite</h1>
                                </div>
                            </div>
                            <!-- Row do cadastro: -->
                            <div class="row row-lista-corpo">
                                <c:choose>
                                    <c:when test="${(param.status == 'error')}">
                                        <div class="alert alert-danger alert-dismissable">
                                            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                                            <p><strong>Ops!</strong> Parece que a folha de pagamento para esse mês ainda não foi fechada.</p>
                                        </div>
                                    </c:when>
                                </c:choose>
                                <!-- corpo da página -->
                                <div class="col-md-12 corpo">
                                    <legend>Emitir Contra-cheque</legend>
                                    <form method="POST" action="FolhaPagamentoServlet?action=holeriteFunc">
                                        <div class="form-group col-md-3">
                                            <label for="depto">Mês</label>
                                            <select class="form-control" id="depto" name="mes">
                                                <option value="01">Janeiro</option>
                                                <option value="02">Fevereiro</option>
                                                <option value="03">Março</option>
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
                                            <input type="submit" class="btn btn-primary" value="Emitir Holerite"/>
                                        </div>
                                    </form>
                                    <c:choose>
                                        <c:when test="${func == null}"><h2 class="col-md-7 text-center unselectable cor-disabled">Selecione um mês e clique em "Emitir Holerite"</h2></c:when>
                                        <c:otherwise>
                                            <div class="col-md-8"><h1>&nbsp;</h1></div>
                                            <c:set var="item" value="${func}"/>
                                            <div class="col-md-12">
                                                <div class="panel col-md-4 panel-info">
                                                    <div class="panel-heading">
                                                        <h3 class="panel-title">Seus dados</h3>
                                                    </div>
                                                    <div class="panel-body">
                                                        <p><b>Nome: </b><c:out value="${item.func.nome}"/></p>
                                                        <p><b>Email: </b><c:out value="${item.func.email}"/></p>
                                                        <p><b>Celular:</b> <c:out value="${item.func.celular}"/></p>
                                                        <p><b>Cpf: </b><c:out value="${item.func.cpf}"/></p>                                                    
                                                        <p><b>Bairro:</b> <c:out value="${item.func.endereco.bairro}"/></p>                                                    
                                                        <p><b>Departamento:</b> <c:out value="${item.func.departamento.nome}"/></p>
                                                    </div>
                                                </div>
                                                <div class="panel col-md-7 margin-esq panel-info">
                                                    <div class="panel-heading">
                                                        <h3 class="panel-title">Holerite</h3>
                                                    </div>
                                                    <jsp:useBean id="data" class="java.util.Date"/>
                                                    <fmt:formatDate var="ano" value="${data}" pattern="yyyy" />
                                                    <div class="panel-body">
                                                        <p class="col-md-6"><b>Mês:</b> <c:out value="${item.mes}"/>/${ano}</p>
                                                        <p class="col-md-6"><b>Cargo:</b> <c:out value="${item.func.cargo.nome}"/></p>
                                                        <p class="col-md-6"><b>Carga Horária Mínima:</b> <c:out value="${item.func.cargo.cargaMinima}"/> horas</p>
                                                        <p class="col-md-6"><b>Horas Trabalhadas:</b> <c:out value="${item.horasTrabalhadas}"/> horas</p>
                                                        <p class="col-md-6"><b>Salario Bruto:</b> R$ <c:out value="${item.func.cargo.salario}"/></p>
                                                        <p class="col-md-6"><b>Salario Líquido:</b> R$ <c:out value="${item.func.cargo.liquido}"/></p>                                                    
                                                        <p class="col-md-6"><b>Desconto de Impostos:</b> <c:out value="${item.func.cargo.descImposto}"/>%</p>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
            </c:otherwise>
        </c:choose>
    </body>
</html>
