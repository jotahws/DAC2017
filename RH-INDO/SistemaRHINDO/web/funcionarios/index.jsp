<%-- 
    Document   : index
    Created on : May 3, 2017, 5:03:55 PM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
        <title>Funcionários</title>
    </head>
    <body>
        <%@include file="../pre-fabricado/cabecalho.jsp" %>
        <div class="container">
            <!-- Row do input pesquisar: -->
            <div class="row row-busca-titulo">
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="Nome, CPF...">
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="button">Pesquisar</button>
                        </span>
                    </div>
                </div>
                <div class="col-md-8 titulo">
                    <h1 class="col-md-10">Funcionários</h1>
                    <div class="text-right">
                        <a href="cadastrar.jsp" class="btn btn-info col-md-2">Novo</a>
                    </div>
                </div>
            </div>
            <!-- Row da lista de func e corpo da pagina: -->
            <div class="row row-lista-corpo">
                <!-- lista de funcioarios: -->
                <div class="col-md-4 lista-lat">
                    <div class="list-group">
                        <a class="list-group-item">func 1</a>
                        <a class="list-group-item">func 2</a>
                        <a class="list-group-item">func 3</a>
                        <a class="list-group-item">func 4</a>
                        <a class="list-group-item">func 5</a>
                        <a class="list-group-item">func 6</a>
                        <a class="list-group-item">func 7</a>
                        <a class="list-group-item">func 8</a>
                        <a class="list-group-item">func 9</a>
                        <a class="list-group-item">func 10</a>
                        <a class="list-group-item">func 11</a>
                    </div>
                </div>
                <!-- corpo da página -->
                <div class="corpo col-md-8 corpo">
                    <fieldset>
                        <legend><h2>Carmen Suely Silva</h2></legend>
                        <p class="col-md-6">CPF: 045.192.192-41</p>
                        <p class="col-md-6">RG: 14.192.202-0</p>
                        <p class="col-md-6">Celular: (41) 9.9709-5866</p>
                        <p class="col-md-6">Email: carmenlindinha@yahoo.com</p>
                        <p class="col-md-6">Perfil do Funcionário: Gerente de RH</p>
                        <p class="col-md-6">Departamento: Recursos Humanos</p>
                        <legend>Endereço</legend>
                        <p class="col-md-6">CEP: 80.320-200</p>
                        <p class="col-md-6">Rua: Doracy Cezzarino</p>
                        <p class="col-md-6">Número: 138</p>
                        <p class="col-md-6">Bairro: Portão</p>
                        <p class="col-md-6">Cidade: Curitiba</p>
                        <p class="col-md-6">Estado: Paraná</p>
                        <div class="text-right">
                            <a class="btn btn-primary">Editar</a>
                            <a class="btn btn-success">Folha de Pagamento</a>
                        </div>

                    </fieldset>
                </div>
            </div>
        </div>
    </body>
</html>