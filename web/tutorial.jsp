<%-- 
    Document   : consultas
    Created on : 20-ene-2018, 22:08:55
    Author     : Disble
--%>
<%@page import="com.model.ct.TablaSQL"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            a{
                text-decoration: none;
                color: orange;
            }
            a:hover {
                color: red;
            }
            .codigo, .codigo pre{
                background-color: black;
                color: orange;
                padding: 10px;
            }
            .container {
                margin: 0 150px;
                text-align: justify;
            }
            .subtitle {
                color: red;
            }
            @media only screen and (max-width: 600px) {
                .container {
                    margin: 0 20px;
                }
            }
        </style>
    </head>
    <body>
        <a href="login.jsp">Aquí Login Listo para usarse \(^o^)/</a>
        <div class="container">
            <h1>Hello TablaSQL!</h1>
            <p>Como nota antes de empezar: Este tutorial fue hecho en un JSP
            y hay partes del documento que estan en un color distinto, eso 
            significa dentro del mismo JSP hay codigo real que puede copiarse 
            para agilizar las cosas.</p>
            <p>Hoy vamos a hablar sobre el objeto TablaSQL. Que no es mas que un objeto
            de terceros para manejar una tabla de una base de datos relacional como
            SQL Server.</p>
            <p>¿Pero para que usar este objeto si ya existe ResultSet?</p>
            <p>Bueno, resulta que ResultSet funciona junto con Statement y 
            adicionalmente utiliza manejadores de try-catch, también hay que 
            controlar que la conexión este abierta para operar sobre los 
            elementos del ResultSet y no olvidar de cerrarla despues. Esto 
            por supuesto se vuelve engorroso y confuso cuando mas datos y 
            consultas manejamos.</p>
            <p>Y eso por eso que <b>TablaSQL</b> existe. Para facilitarnos un poco 
            más la vida.</p>
            <p>TablaSQL no solo nos ayuda, con un archivo de Conexión facilmente 
            configurable, sino que en realidad nunca tendremos que llamarlo para 
            utilizarlo. Además viene con una serie de métodos para el manejo de 
            la tabla que van desde obtener filas o celdas especificas hasta 
            generar reportes HTML.</p>
            <p><b>Pero veamos como funciona</b></p>
            <p>Para empezar primero hay que empezar importado la clase del paquete.</p>
            
            <p class="codigo">com.model.ct.TablaSQL</p>
            
            <h2 class="subtitle">Ingresando a la Base de Datos</h2>
            
            <p>Ahora si queremos hacer una consulta a la base de datos, la forma 
            en que lo hagamos dependera de lo que queramos hacer. Si solo queremos 
            ingresar datos a la base, pueden usar el metodo estatico.</p>
            <p><b>insertarDB(String sql)</b></p>
            <p>Que como pueden ver solo recibe el string para ingresar datos y 
            retorna true o false dependiendo de si completo con exito la 
            consulta o no.</p>
            <%
                String sql = "INSERT INTO USUARIO(nombreU, passU, rolU) VALUES('TablaSQL', 'TablaSQL', 1)";
                boolean guardado = TablaSQL.insertarDB(""); // retorna false porque tiene un string vacio
            %>
            <p>JSP dice: <b><%=guardado ? "Guardado con Exito": "Hubo un problema al guardar"%></b></p>
            <p class="codigo">
                String sql = "INSERT INTO USUARIO(nombreU, passU, rolU) VALUES('TablaSQL', 'TablaSQL', 1)";
                boolean guardado = TablaSQL.insertarDB(sql);
            </p>
            <p>Como podrán fijarse, si una inserción falla devuelve un false, esto 
                se debe a que por dentro no solo hace la consulta sino que ya 
            esta validando en caso de errores.</p>
            
            <h2 class="subtitle">Recuperando una tabla de la Base de Datos</h2>
            
            <p>El anterior ejemplo nos mostro como ingresar datos a una base de 
            datos, pero TableSQL brilla más cuando recuperamos datos.</p>
            <p><b>Empecemos.</b></p>
            
            <p>Para recuperar datos de un base de datos usaremos inicalizaremos
            un objeto TablaSQL.</p>
            
            <%
                sql = "SELECT * FROM USUARIO";
                TablaSQL tsql = new TablaSQL(sql, true);
            %>
            <p class="codigo">
                String sql = "SELECT * FROM USUARIO";<br>
                TablaSQL tsql = new TablaSQL(sql, true);
            </p>
            
            <p>Lo que nos devolvera sera un objeto que representa la tabla que 
            estamos solicitando. Claro que dependiendo de la consulta, nos 
            devolvera toda la tabla o solo una parte.</p>
            <p>El constructor tambien acepta un boolean que indica si se quiere
            los nombres de las columnas junto con los datos.</p>
            <p>Si quieramos ver los datos en HTML tenemos el método.</p>
            <p><b>reporteHTML()</b></p>
            <%=tsql.reporteCSS()%>
            <%=tsql.reporteHTML()%>
            <p class="codigo">
                tsql.reporteHTML(); // retorna un String HTML
            </p>
            
            <p>Si queremos algo de estilos puede usar la clase css <b>reporte</b>
             ya predefinida con la tabla o a su vez usar el metodo.</p>
            <p><b>reporteCSS()</b></p>
            
            <p class="codigo">
                tsql.reporteCSS(); // retorna un String HTML con etiqueta style <br>
                tsql.reporteHTML(); // retorna un String HTML
            </p>
            
            <h2 class="subtitle">Recorriendo los elementos de la tabla</h2>
            
            <p>Pero muchas veces, no queremos hacer solo un reporte simple de 
            la tabla. Para eso tenemos una serie de metodo enlistados a
            continuación.</p>
            
            <ul>
                <li>getData() // retorna un doble ArrayList con toda la tabla</li>
                <li>getRow(int fila) // retorna un Object[] de una sola fila</li>
                <li>getCell(int fila, int columna) ó getCell(int fila, String columna) 
                // retorna una celda de la tabla</li>
                <li>getNumRows() // retorna el numero de filas de la tabla</li>
                <li>getNumColumns() // retorna el numero de columnas de la tabla</li>
            </ul>
            
            <p><b>getData()</b></p>
            
            <p>Uno de los métodos mas importantes es getData() que devuelve un doble 
            ArrayList.</p>
            
            <%
                // &lt; -> '<' en HTML
                // &gt; -> '>' en HTML
                // &lt;&gt;
                ArrayList<ArrayList<Object>> tabla = tsql.getData();
            %>
            <p class="codigo">ArrayList&lt;ArrayList&lt;Object&gt;&gt; tabla = tsql.getData();</p>
            
            <p>getData() devuelve un ArrayList que puede ser recorrido por un 
                par de foreach</p>
            
                <table>
                    <%
                        // Este for imprime las filas
                        for (ArrayList<Object> row : tabla) {
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
            <p>     
            <pre class="codigo">
                for (ArrayList&lt;Object&gt; fila : tabla) {
                    for (Object columna : fila) {
                        out.println(columna);
                    }
                }
            </pre>
            </p>
            
            <p><b>getRow() y getNumRows()</b></p>
            
            <p>Pero si no queremos complicarnos con un doble ArrayList hay otra 
            alternativa que es <b>getRow()</b> que devuleve un simple array de 
            objetos, este método puede combinarse junto con getNumRows() para 
            obtener el mismo resultado.</p>
            
            <table>
            <%
                int tam = tsql.getNumRows();
                for (int i = 0; i < tam; i++) {
                    %>
                <tr>
                    <%
                    Object[] fila = tsql.getRow(i);
                    for (int j = 0; j < fila.length; j++) {
                        %>
                        <td><%=fila[j]%></td>
                        <%
                    }
                    %>
                </tr>
                    <%
                }
            %>
            </table>
            <p><pre class="codigo">
                int tam = tsql.getNumRows();
                for (int i = 0; i < tam; i++) {
                    Object[] fila = tsql.getRow(i);
                    for (int j = 0; j < fila.length; j++) {
                        out.print(fila[j]);
                    }
                }
            </pre></p>
            
            <p><b>getCell()</b></p>
            
            <p>Podemos ser incluso más especificos y obtener incluso una celda 
            de la tabla en concreta.</p>
            
            <%
                Object celda = tsql.getCell(1, "nombreU");
                out.print("JSP dice: " + celda);
            %>
            
            <p><pre class="codigo">
                Object celda = tsql.getCell(1, "nombreU");
                out.print(celda);
            </pre></p>
            
            <p>Como pueden ver getCell() acepta una sobrecarga que recibe el 
            nombre de la columna en lugar de su número.</p>
            <p>Bien, con esto termina este pequeño tutorial de como usar 
            el objeto TablaSQL</p>
            <p>Gracias por leer hasta el final y hasta otra.</p>
        </div>
    </body>
</html>
