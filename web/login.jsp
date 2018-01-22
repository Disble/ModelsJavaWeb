<%-- 
    Document   : login
    Created on : 21-ene-2018, 11:23:18
    Author     : Disble
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form action="loginaccess.jsp" method="post">
            <table>
                <tr>
                    <td>Usuario</td>
                    <td><input type="text" name="user"></td>
                </tr>
                <tr>
                    <td>Contraseña</td>
                    <td><input type="password" name="pass"></td>
                </tr>
                <tr>
                    <td></td><td><input type="submit" value="Ingresar"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
