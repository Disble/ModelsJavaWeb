### Models Java Web
### Descripción y contexto
---
Este es un proyecto en Netbeans en Java para Aplicaciones Web hecho por estudiantes para estudiantes con el fin de facilitar un poco el desarrollo en clases.

Las características principales del proyecto son dos clases diseñadas para el manejo de tablas en base de datos (TablaSQL.java) y un simple control de sesiones (Sesiones.java). Como dato adicional, al estar diseñada para ser usada en clases no hace un control de seguridad.

> Por favor no usar para proyectos reales.

### Guía de usuario
---
Explica los pasos básicos sobre cómo usar la herramienta digital. Es una buena sección para mostrar capturas de pantalla o gifs que ayuden a entender la herramienta digital.
 	
### Guía de instalación
---
Paso a paso de cómo instalar la herramienta digital. En esta sección es recomendable explicar la arquitectura de carpetas y módulos que componen el sistema.

Según el tipo de herramienta digital, el nivel de complejidad puede variar. En algunas ocasiones puede ser necesario instalar componentes que tienen dependencia con la herramienta digital. Si este es el caso, añade también la siguiente sección.

La guía de instalación debe contener de manera específica:
- Los requisitos del sistema operativo para la compilación (versiones específicas de librerías, software de gestión de paquetes y dependencias, SDKs y compiladores, etc.).
- Las dependencias propias del proyecto, tanto externas como internas (orden de compilación de sub-módulos, configuración de ubicación de librerías dinámicas, etc.).
- Pasos específicos para la compilación del código fuente y ejecución de tests unitarios en caso de que el proyecto disponga de ellos.

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
Esta sección explica a desarrolladores cuáles son las maneras habituales de enviar una solicitud de adhesión de nuevo código (“pull requests”), cómo declarar fallos en la herramienta y qué guías de estilo se deben usar al escribir más líneas de código. También puedes hacer un listado de puntos que se pueden mejorar de tu código para crear ideas de mejora.

### Código de conducta 
---
El código de conducta establece las normas sociales, reglas y responsabilidades que los individuos y organizaciones deben seguir al interactuar de alguna manera con la herramienta digital o su comunidad. Es una buena práctica para crear un ambiente de respeto e inclusión en las contribuciones al proyecto. 

La plataforma Github premia y ayuda a los repositorios dispongan de este archivo. Al crear CODE_OF_CONDUCT.md puedes empezar desde una plantilla sugerida por ellos. Puedes leer más sobre cómo crear un archivo de Código de Conducta (aquí)[https://help.github.com/articles/adding-a-code-of-conduct-to-your-project/]

### Autor/es
---
Nombra a el/los autor/es original/es. Consulta con ellos antes de publicar un email o un nombre personal. Una manera muy común es dirigirlos a sus cuentas de redes sociales.

### Información adicional
---
Esta es la sección que permite agregar más información de contexto al proyecto como alguna web de relevancia, proyectos similares o que hayan usado la misma tecnología.

### Licencia 
---
[LICENCIA](https://github.com/EL-BID/Plantilla-de-repositorio/blob/master/LICENSE.md)

La licencia especifica los permisos y las condiciones de uso que el desarrollador otorga a otros desarrolladores que usen y/o modifiquen la herramienta digital.

Incluye en esta sección una note con el tipo de licencia otorgado a esta herramienta digital. El texto de la licencia debe estar incluído en un archivo *LICENSE.md* o *LICENSE.txt* en la carpeta raíz.

Si desconoces qué tipos de licencias existen y cuál es la mejor para cada caso, te recomendamos visitar la página https://choosealicense.com/.

## Limitación de responsabilidades

El BID no será responsable, bajo circunstancia alguna, de daño ni indemnización, moral o patrimonial; directo o indirecto; accesorio o especial; o por vía de consecuencia, previsto o imprevisto, que pudiese surgir:

i. Bajo cualquier teoría de responsabilidad, ya sea por contrato, infracción de derechos de propiedad intelectual, negligencia o bajo cualquier otra teoría; y/o

ii. A raíz del uso de la Herramienta Digital, incluyendo, pero sin limitación de potenciales defectos en la Herramienta Digital, o la pérdida o inexactitud de los datos de cualquier tipo. Lo anterior incluye los gastos o daños asociados a fallas de comunicación y/o fallas de funcionamiento de computadoras, vinculados con la utilización de la Herramienta Digital.
