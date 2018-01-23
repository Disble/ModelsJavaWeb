<%-- 
    Document   : consultas
    Created on : 20-ene-2018, 22:08:55
    Author     : Disble
--%>
<%@page import="com.model.ccr.TablaSQL"%>
<%@page import="com.model.ccr.Consultas"%>
<%@page import="com.model.ccr.Reportes"%>
<%@page import="java.util.ArrayList"%>
<%  String sql = "INSERT INTO ECUACIONES2GRADO(idU, imaginario, discriminante) VALUES(2,15,-12345);";
    boolean guardado = Consultas.insertarDB(sql); // Retorna false si falla la consulta a la BD
    sql = "SELECT * FROM USUARIO;";
    TablaSQL tabla = Consultas.getFromDB(sql, true); // Retorna null si falla la consulta a BD
    ArrayList<ArrayList<Object>> resp = tabla.getData();
    int lastId = Consultas.getLastIdTableDB("SELECT nombreU, passU FROM USUARIO");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <a href="login.jsp">Login</a>
        <%=Reportes.reporteCSS(2)%>
    </head>
    <body>
        <h1>Hello World!</h1>
        <h1><%=guardado ? "Guardado" : "Fallo la consulta"%></h1>
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
        <p>Ultimo id de una tabla : <%=lastId%></p>
        <br>
        <%=Reportes.reporteHTML("SELECT * FROM ECUACIONES2GRADO;", true)%>
        <p>Retorna una fila de una tabla : 
        <%
            Object[] fila = tabla.getRow(0);
            for (Object col : fila) {
        %>
        <%=col%>,
        <%
                }
        %>
        </p>
        <p>Retorna una celda de una tabla : <%=tabla.getCell(1,1)%></p>
        <p>Retorna una celda llamando el nombre de una columna de una tabla : <%=tabla.getCell(2, "idU")%></p>
    </body>
</html>
