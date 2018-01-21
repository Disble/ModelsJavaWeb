<%-- 
    Document   : consultas
    Created on : 20-ene-2018, 22:08:55
    Author     : Disble
--%>
<%@page import="com.model.ccr.Reportes"%>
<%@page import="java.util.ArrayList"%>
<%  String sql = "INSERT INTO ECUACIONES2GRADO(idU, imaginario, discriminante) VALUES(27,17,-1237);";
    boolean si = Consultas.insertarDB(sql); // Retorna false si falla la consulta a la BD
    sql = "SELECT nombreU, passU FROM USUARIO;";
    ArrayList<ArrayList<Object>> resp = Consultas.getFromDB(sql, true); // Retorna null si falla la consulta a BD
    int lastId = Consultas.getLastIdTableDB("SELECT nombreU, passU FROM USUARIO");
%>
<%@page import="com.model.ccr.Consultas"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%=Reportes.reporteCSS()%>
    </head>
    <body>
        <h1>Hello World!</h1>
        <h1><%=si ? "Guardado" : "Fallo la consulta"%></h1>
        <table>
            <%
                // Este for imprime las filas
                for (ArrayList<Object> row : resp) {
            %>
            <tr>
                <%
                    // Este for imprime las columnas
                    for (Object col : row) {
                %>
                    <td><%=col%></td>
                <%
                    }
                %>
            </tr>
            <%
                }
            %>
            
        </table>
        <p><%=lastId%></p>
        <br>
        <%=Reportes.reporteHTML("SELECT * FROM ECUACIONES2GRADO;")%>
    </body>
</html>
