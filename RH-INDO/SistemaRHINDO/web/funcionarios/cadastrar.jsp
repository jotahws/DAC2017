<%-- 
    Document   : cadastrar
    Created on : May 4, 2017, 4:00:37 PM
    Author     : JotaWind
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
        <title>Cadastrar Funcionário - Sistema RHINDO</title>
    </head>
    <body>
        <%@include file="../pre-fabricado/cabecalho.jsp" %>
        <div class="container">
            <!-- Row do input pesquisar: -->
            <div class="row row-busca-titulo">
                <div class="col-md-8 titulo">
                    <h1 class="col-md-10">Cadastrar Funcionário</h1>
                </div>
            </div>
            <!-- Row do cadastro: -->
            <div class="row row-lista-corpo">
                <!-- corpo da página -->
                <div class="col-md-12 corpo">
                    <fieldset>
                        <form class="cadastro">
                            <div class="row">
                                <legend>Geral</legend>
                                <div class="form-group col-md-6">
                                    <label for="nome">Nome:</label>
                                    <input type="text" class="form-control" id="nome" placeholder="Maria dos Santos">
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="cpf">CPF:</label>
                                    <input type="text" class="form-control" id="cpf" >
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="rg">RG:</label>
                                    <input type="text" class="form-control" id="rg">
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-md-2">
                                    <label for="celular">Celular</label>
                                    <input type="text" class="form-control" id="celular">
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="email">Email</label>
                                    <input type="email" class="form-control" id="email" placeholder="joe@exemplo.com">
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="depto">Departamento</label>
                                    <select class="form-control" id="depto">
                                        <option>Recursos Humanos</option>
                                        <option>Desenvolvimento</option>
                                        <option>Financeiro</option>
                                    </select>
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="perfil">Perfil do Funcionário</label>
                                    <select class="form-control" id="perfil">
                                        <option>Funcionário comum</option>
                                        <option>Gerente</option>
                                        <option>Gerente RH</option>
                                    </select>
                                </div>
                            </div>
                            <div class="row">
                                <legend>Endereço</legend>
                                <div class="form-group col-md-4">
                                    <label for="cep">CEP</label>
                                    <input type="text" class="form-control" id="cep"/>
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="rua">Rua</label>
                                    <input type="text" class="form-control" id="rua" placeholder="Ex. Rua das Dores"/>
                                </div>
                                <div class="form-group col-md-2">
                                    <label for="numero">Número</label>
                                    <input type="number" class="form-control" id="numero"/>
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="bairro">Bairro</label>
                                    <input type="text" class="form-control" id="bairro" placeholder="Ex. São Francisco"/>
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="cidade">Cidade</label>
                                    <input type="text" class="form-control" id="cidade" placeholder="Ex. Curitiba"/>
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="estado">Estado</label>
                                    <input type="text" class="form-control" id="estado" placeholder="Ex. Paraná"/>
                                </div>
                            </div>
                            <div class="text-right">
                                <button type="submit" class="btn btn-primary form-group">Cadastrar</button>  
                            </div>
                        </form>
                    </fieldset>
                </div>
            </div>
        </div>
    </body>
</html>
