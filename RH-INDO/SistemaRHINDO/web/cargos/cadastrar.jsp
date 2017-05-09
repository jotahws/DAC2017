<%-- 
    Document   : novo
    Created on : May 4, 2017, 4:03:33 PM
    Author     : JotaWind
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
        <title>Cadastrar Cargo - Sistema RHINDO</title>
    </head>
    <body>
        <%@include file="../pre-fabricado/cabecalho.jsp" %>
        <div class="container">
            <!-- Row do input pesquisar: -->
            <div class="row row-busca-titulo">
                <div class="col-md-8 titulo">
                    <h1 class="col-md-10">Cadastrar Cargo</h1>
                </div>
            </div>
            <!-- Row do cadastro: -->
            <div class="row row-lista-corpo">
                <!-- corpo da página -->
                <div class="col-md-12 corpo">
                    <fieldset>
                        <form class="cadastro">
                            <div class="row">
                                <legend>Dados do Cargo</legend>
                                <div class="form-group col-md-6">
                                    <label for="nome">Nome:</label>
                                    <input type="text" class="form-control" id="nome" placeholder="Ex. Marketing">
                                </div>
                                <div class="form-group col-md-2">
                                    <label for="salario">Salário:</label>
                                    <input type="number" class="form-control" id="salario" placeholder="R$:">
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="carga">Carga de Trabalho (Horas/Mês):</label>
                                    <input type="number" class="form-control" id="carga" placeholder="Horas/Mês" value="">
                                </div>
                                <div class="form-group col-md-8">
                                    <label for="requisitos">Requisitos:</label>
                                    <textarea class="form-control" rows="3"></textarea>
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="imposto">Desconto de Imposto (%):</label>
                                    <input type="number" class="form-control" id="imposto" placeholder="%">
                                </div>
                            </div>
                            <div class="text-right">
                                <button type="submit" id="botao" class="btn btn-primary">Salvar Alterações</button>  
                            </div>
                        </form>
                    </fieldset>
                </div>
            </div>
        </div>
    </body>
</html>
