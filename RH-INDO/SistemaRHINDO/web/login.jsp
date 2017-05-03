<%-- 
    Document   : login
    Created on : May 1, 2017, 2:57:52 PM
    Author     : JotaWind
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="CSS/stylee.css"/>
        <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-4 col-md-offset-4">
                    <div class="panel panel-default">
                        <div class="panel-heading"><h2 class="page-title">Sistema RH-INDO</h2></div>
                        <div class="panel-body">
                            <form class="form-horizontal" role="form" action="LoginServlet" method="POST">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">
                                        Login:</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" placeholder="login" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">
                                        Senha:</label>
                                    <div class="col-sm-9">
                                        <input type="password" class="form-control" placeholder="senha" required>
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
                            <a href="http://www.jquery2dotnet.com">Esqueceu a senha?</a></div>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
