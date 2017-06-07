<%-- 
    Document   : login
    Created on : 10/05/2017, 10:15:48
    Author     : MauMau
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="CSS/style.css"/>
        <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css"/>
        <title>Login - Sistema ATOA</title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-4 col-md-offset-4">
                    <div class="panel panel-default">
                        <div class="panel-heading"><h2 class="page-title">Sistema ATOA</h2></div>
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
                                        <button type="submit" class="btn btn-success btn-sm">
                                            Entrar</button>
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

    </body>
</html>
