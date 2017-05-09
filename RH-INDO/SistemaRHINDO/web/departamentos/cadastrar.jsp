<%-- 
    Document   : novo
    Created on : May 4, 2017, 4:01:43 PM
    Author     : JotaWind
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
        <title>Cadastrar Departamento - Sistema RHINDO</title>
    </head>
    <body>
        <%@include file="../pre-fabricado/cabecalho.jsp" %>
        <div class="container">
            <!-- Row do input pesquisar: -->
            <div class="row row-busca-titulo">
                <div class="col-md-8 titulo">
                    <h1 class="col-md-10">Cadastrar Departamento</h1>
                </div>
            </div>
            <!-- Row do cadastro: -->
            <div class="row row-lista-corpo">
                <!-- corpo da página -->
                <div class="col-md-12 corpo">
                    <fieldset>
                        <form class="cadastro">
                            <div class="row">
                                <legend>Dados do Departamento</legend>
                                <div class="form-group col-md-6">
                                    <label for="nome">Nome:</label>
                                    <input type="text" class="form-control" id="nome" placeholder="Ex. Depto Financeiro">
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="localizacao">Localização</label>
                                    <input type="text" class="form-control" id="localizacao" placeholder="Ex. 20º Andar">
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
