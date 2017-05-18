<%-- 
    Document   : login
    Created on : May 1, 2017, 2:57:52 PM
    Author     : JotaWind
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="CSS/stylee.css"/>
        <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css"/>
        <title>Login - Sistema RHINDO</title>
    </head>
    <body>
    <c:choose>
        <c:when test="${funcionarioLogado.email != null}">
            <c:redirect url="CarregaListaFuncServlet?action=listaFuncionarios"/>
        </c:when>
        <c:otherwise>
            <div class="container">
                <div class="row">
                    <div class="col-md-4 col-md-offset-4">
                        <div class="panel panel-default">
                            <div class="panel-heading"><h2 class="page-title">Sistema RH-INDO</h2></div>
                            <div class="panel-body">
                                <form class="form-horizontal" role="form" action="LoginServlet?action=login" method="POST">
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">
                                            Login:</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" name="login" placeholder="login" required>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">
                                            Senha:</label>
                                        <div class="col-sm-9">
                                            <input type="password" class="form-control" name="senha" placeholder="senha" required>
                                        </div>
                                    </div>
                                    <div class="form-group last">
                                        <div class="col-sm-offset-9 col-sm-3">
                                            <input type="submit" value="Entrar" class="btn btn-success btn-sm">
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="panel-footer">
                                <a href="forgotPswDados.jsp">Esqueceu a senha?</a></div>
                        </div>
                    </div>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</body>
</html>
