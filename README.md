### Models Java Web
### Descripción y contexto
---
Este es un proyecto en Netbeans en Java para Aplicaciones Web hecho por estudiantes para estudiantes con el fin de facilitar un poco el desarrollo en clases.

Las características principales del proyecto son dos clases diseñadas para el manejo de tablas en base de datos (`TablaSQL.java`) y un simple control de sesiones (`Sesiones.java`). 

Como nota importante, al estar diseñada para ser usada en clases no hace un control de seguridad.

> Por favor no usar para proyectos reales.

### Guía de usuario
---

#### Hello TablaSQL!

Hoy vamos a hablar sobre el objeto `TablaSQL`. Que no es más que un objeto de terceros para manejar una tabla de una base de datos relacional como Microsoft SQL Server.

¿Pero para que usar este objeto si ya existe ResultSet?

Bueno, resulta que ResultSet funciona junto con Statement y adicionalmente utiliza manejadores de try-catch, también hay que controlar que la conexión este abierta para operar sobre los elementos del ResultSet y no olvidar de cerrarla después. Esto por supuesto se vuelve engorroso y confuso cuando más datos y consultas manejamos.

Y eso por eso que TablaSQL existe. Para facilitarnos un poco más la vida.

TablaSQL no solo nos ayuda con un archivo de Conexión fácilmente configurable, sino que en realidad nunca tendremos que llamarlo para utilizarlo. Además, viene con una serie de métodos para el manejo de la tabla que van desde obtener filas o celdas especificas hasta generar reportes HTML.

**Pero veamos cómo funciona**

Para empezar, primero hay que empezar importado la clase del paquete.

```java
com.model.ct.TablaSQL
```

#### Ingresando a la Base de Datos

Ahora si queremos hacer una consulta a la base de datos, la forma en que lo hagamos dependerá de lo que queramos hacer. Si solo queremos ingresar datos a la base, pueden usar el método estático.

```java
insertarDB(String sql)
```

Que como pueden ver solo recibe el string para ingresar datos y retorna true o false dependiendo de si completo con éxito la consulta o no.

```java
String sql = "INSERT INTO USUARIO(nombreU, passU, rolU) VALUES('TablaSQL', 'TablaSQL', 1)"; 
boolean guardado = TablaSQL.insertarDB(sql);
```

Como podrán fijarse, si una inserción falla devuelve un false, esto se debe a que por dentro no solo hace la consulta, sino que ya está validando en caso de errores.

#### Recuperando una tabla de la Base de Datos

El anterior ejemplo nos mostró como ingresar datos a una base de datos, pero TableSQL brilla más cuando recuperamos datos.

**Empecemos.**

Para recuperar datos de un base de datos usaremos inicializaremos un objeto TablaSQL.

```java
String sql = "SELECT * FROM USUARIO";
TablaSQL tsql = new TablaSQL(sql, true);
```

Lo que nos devolverá será un objeto que representa la tabla que estamos solicitando. Claro que, dependiendo de la consulta, nos devolverá toda la tabla o solo una parte.

El constructor también acepta un boolean que indica si se quiere los nombres de las columnas junto con los datos.

Si queríamos ver los datos en HTML tenemos el método.

```java
reporteHTML()
```

Ejemplo:

```java
tsql.reporteHTML(); // retorna un String HTML
```

Si queremos algo de estilos puede usar la clase css `reporte` ya predefinida con la tabla o a su vez usar el método.

```java
reporteCSS()
```

Ejemplo:

```java
tsql.reporteCSS(); // retorna un String HTML con etiqueta style 
tsql.reporteHTML(); // retorna un String HTML
```

#### Recorriendo los elementos de la tabla

Pero muchas veces, no queremos hacer solo un reporte simple de la tabla. Para eso tenemos una serie de métodos enlistados a continuación.

```java
getData() // retorna un doble ArrayList con toda la tabla
getRow(int fila) // retorna un Object[] de una sola fila
getCell(int fila, int columna) /* ó */ getCell(int fila, String columna) // retorna una celda de la tabla
getNumRows() // retorna el numero de filas de la tabla
getNumColumns() // retorna el numero de columnas de la tabla
```

Veamos estos métodos de forma separada.

##### getData()

Uno de los métodos más importantes es `getData()` que devuelve un doble ArrayList.

```java
ArrayList<ArrayList<Object>> tabla = tsql.getData();
```

`getData()` devuelve un ArrayList que puede ser recorrido por un par de foreach.

```java
for (ArrayList<Object> fila : tabla) {
    for (Object columna : fila) {
        out.println(columna);
    }
}
```

##### getRow() y getNumRows()

Pero si no queremos complicarnos con un doble ArrayList hay otra alternativa que es `getRow()` que devuelve un simple array de objetos, este método puede combinarse junto con `getNumRows()` para obtener el mismo resultado.

```java
int tam = tsql.getNumRows();
for (int i = 0; i < tam; i++) {
    Object[] fila = tsql.getRow(i);
    for (int j = 0; j < fila.length; j++) {
        out.print(fila[j]);
    }
}
```

##### getCell()

Podemos ser incluso más específicos y obtener incluso una celda de la tabla en concreta.

```java
Object celda = tsql.getCell(1, "nombreU");
out.print(celda);
```

Como pueden ver `getCell()` acepta una sobrecarga que recibe el nombre de la columna en lugar de su número.

Bien, con esto termina este pequeño tutorial de cómo usar el objeto TablaSQL.

Gracias por leer hasta el final y hasta otra.

> Nota: Este mismo tutorial se encuentra en un jsp llamado `tutorial.jsp` dentro del proyecto. ¡Este tutorial contiene codigo jsp funcional!


### Guía de instalación
---

Se puede **descargar como un zip** directamente desde la pagina o utilizando git con los siguiente comandos.

```bash
git clone https://gitlab.com/Disble/ModelsJavaWeb.git
cd ModelsJavaWeb
```

#### Requisitos

- Netbeans 8.2 por lo menos en su versión Java EE (aunque se recomienda tener también las opciones HTML5/JavaScript).
- Microsoft SQL Server 2014 o superior.

Una vez descargado de forma local el repositorio se abrira desde Netbeans como un proyecto normal.

#### Dependencias

La libreria JDBC para MS SQL Server. Debe ser agregado a través de `Project Properties -> Libraries -> Add JAR/Folder.`

- sqljdbc42.jar

Es importante tener configurado MS SQL Server para recibir autentificaciones por comandos y TCP/IP (se recomienda usar el puerto 1433).

Se debe configurar en Netbeans la conexión con MS SQL Server: `Services -> Databases -> Clic derecho -> New Connection… -> Driver -> New Driver… -> Add.. -> Open -> Next -> [Llenar con los datos de su Base de datos, puerto, usuario, contraseña] -> Test Connection -> Finish.`

Una vez configurado correctamente la conexión de Netbeans con su base de datos es hora de configurar el archivo de conexión. Ubicado en `Source Packages -> com.model.ct -> Conexion.java`.

```java
conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;dataBaseName=PIVPROJECT", "sa", "root");
```

Se debe modificar los datos pertinentes en la presente línea.

Por último, en `Web Pages -> ScriptDB -> FisicoPIVPROJECT.sql` se encuentra los comandos para crear las tablas de la base de datos correspondiente.

```sql
CREATE TABLE USUARIO(
    idU INT PRIMARY KEY IDENTITY (1,1),
    nombreU VARCHAR(20),
    passU VARCHAR(30),
    rolU INT
);
```

### Cómo contribuir
---

Este proyecto esta abierto a contribuciones, se puede enviar `pull request` tanto para código como documentación.

### Autor/es
---
Autor: [Disble](@Disble)

### Licencia 
---
[LICENCIA MIT](./LICENSE)

## Limitación de responsabilidades

El autor no será responsable, bajo circunstancia alguna, de daño ni indemnización, moral o patrimonial; directo o indirecto; accesorio o especial; o por vía de consecuencia, previsto o imprevisto, que pudiese surgir:

i. Bajo cualquier teoría de responsabilidad, ya sea por contrato, infracción de derechos de propiedad intelectual, negligencia o bajo cualquier otra teoría; y/o

ii. A raíz del uso de la Herramienta Digital, incluyendo, pero sin limitación de potenciales defectos en la Herramienta Digital, o la pérdida o inexactitud de los datos de cualquier tipo. Lo anterior incluye los gastos o daños asociados a fallas de comunicación y/o fallas de funcionamiento de computadoras, vinculados con la utilización de la Herramienta Digital.