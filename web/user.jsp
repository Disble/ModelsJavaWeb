<%-- 
    Document   : user
    Created on : 21-ene-2018, 19:01:13
    Author     : Disble
--%>

<%@page import="com.model.sl.Sesiones"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Sesiones sesion = new Sesiones(request.getSession());
    if (!sesion.validarUsuario()){
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Bienvenido <%=sesion.getNombre()%>!</h1>
        <a href="logout.jsp">Cerrar Sesion</a>
    </body>
</html>
