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
                        <form action="../FuncionarioServlet?action=register" method="POST" class="cadastro">
                            <div class="row">
                                <legend>Geral</legend>
                                <div class="form-group col-md-6">
                                    <label for="nome">Nome:</label>
                                    <input type="text" class="form-control" id="nome" name="nome" placeholder="Maria dos Santos">
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="cpf">CPF:</label>
                                    <input type="text" class="form-control" id="cpf" name="cpf">
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="rg">RG:</label>
                                    <input type="text" class="form-control" id="rg" name="rg">
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-md-2">
                                    <label for="celular">Celular</label>
                                    <input type="text" class="form-control" id="celular" name="celular">
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="email">Email</label>
                                    <input type="email" class="form-control" id="email" name="email" placeholder="joe@exemplo.com">
                                </div>
                                <div class="form-group col-md-2">
                                    <label for="depto">Departamento</label>
                                    <select class="form-control" id="depto" name="depto">
                                        <option>Recursos Humanos</option>
                                        <option>Desenvolvimento</option>
                                        <option>Financeiro</option>
                                    </select>
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="cargo">Cargo</label>
                                    <select class="form-control" id="cargo" name="cargo">
                                        <option>mkting</option>
                                        <option>backenf</option>
                                        <option>frontend</option>
                                    </select>
                                </div>
                                <div class="form-group col-md-2">
                                    <label for="perfil">Perfil do Funcionário</label>
                                    <select class="form-control" id="perfil" name="perfil">
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
                                    <input type="text" class="form-control" id="cep" name="cep"/>
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="rua">Rua</label>
                                    <input type="text" class="form-control" id="rua" name="rua" placeholder="Ex. Rua das Dores"/>
                                </div>
                                <div class="form-group col-md-2">
                                    <label for="numero">Número</label>
                                    <input type="number" class="form-control" id="numero" name="numero"/>
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="bairro">Bairro</label>
                                    <input type="text" class="form-control" id="bairro" name="bairro" placeholder="Ex. São Francisco"/>
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="cidade">Cidade</label>
                                    <input type="text" class="form-control" id="cidade" name="cidade" placeholder="Ex. Curitiba"/>
                                </div>
                                <div class="form-group col-md-1">
                                    <label for="uf">UF</label>
                                    <select class="form-control" id="uf" name="uf">
                                        <option>RS</option>
                                        <option>SC</option>
                                        <option>PR</option>
                                    </select>
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="estado">Estado</label>
                                    <input type="text" class="form-control" disabled id="estado" name="estado"/>
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
