<%-- 
    Document   : contraCheque
    Created on : May 4, 2017, 4:23:03 PM
    Author     : JotaWind
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
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
                                            <input type="submit" class="btn btn-primary" value="Emitir Holêrite"/>
                                        </div>
                                    </form>
                                    <c:choose>
                                        <c:when test="${func == null}"><h2 class="text-center unselectable cor-disabled">Selecione um mês e clique em "Fechar Folha"</h2></c:when>
                                        <c:otherwise>
                                            <c:set var="item" value="${func}"/>
                                            <table class="table">
                                                <tr>
                                                    <th>Funcionário</th>
                                                    <th>Email</th>
                                                    <th>Cpf</th>                                                    
                                                    <th>Departamento</th>
                                                </tr>

                                                <tr>
                                                    <td><c:out value="${item.func.nome}"/></td>
                                                    <td><c:out value="${item.func.email}"/></td>
                                                    <td><c:out value="${item.func.cpf}"/></td>
                                                    <td><c:out value="${item.func.departamento.nome}"/></td>
                                                </tr>
                                                <tr>
                                                    <th>Cargo</th>
                                                    <th>Salário bruto</th>
                                                    <th>Salário liquido</th>                                                    
                                                    <th>Carga minima</th>
                                                </tr>
                                                <tr>
                                                    <td><c:out value="${item.func.cargo.nome}"/></td>
                                                    <td><c:out value="${item.func.cargo.salario}"/></td>
                                                    <td><c:out value="${item.func.cargo.liquido}"/></td>
                                                    <td><c:out value="${item.func.cargo.cargaMinima}"/></td>
                                                </tr>
                                                <tr>
                                                    <th>Horas Trabalhadas</th>
                                                    <th>Mês de:</th>
                                                </tr>
                                                <tr>
                                                    <td><c:out value="${item.func.cargo.nome}"/></td>
                                                    <td><c:out value="${item.func.cargo.salario}"/></td>
                                                </tr>
                                            </table>
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
