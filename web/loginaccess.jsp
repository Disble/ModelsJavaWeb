<%@page import="com.model.ct.TablaSQL"%>
<%@page import="com.model.sl.Sesiones"%>
<%
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        
        int rol = -1;
        int idU = -1;
        
        Sesiones sesion =  new Sesiones(request.getSession()); // Esta clase va a controlar la sesion desde ahora
            
        String sql = "SELECT idU, rolU FROM USUARIO WHERE nombreU='" + user + "' AND passU='" + pass + "';";
        TablaSQL tsql = new TablaSQL(sql);
        Object[] resp = tsql.getRow(0); // Retorna un array con la primera fila

        if (resp != null) {
            idU = (Integer) resp[0];
            rol = (Integer) resp[1];
        }

        System.out.println("Rol : " + rol); // solo imprime el rol en terminal
        if (rol == 0) { // valor cero es predefinido para usuario normal
            
            sesion.setSesion(idU, user); // asigna a sesion id y nombre de usuario
            sesion.setNivelUser(); // asigna el nivel con un valor predefinido
            response.sendRedirect("user.jsp"); // redirecciona a el jsp correspondiente
            
        } else if (rol == 1){ // valor uno es predefinido para administrador
            
            sesion.setSesion(idU, user); // asigna a sesion id y nombre de usuario
            sesion.setNivelAdmin(); // asigna el nivel con un valor predefinido
            response.sendRedirect("admin.jsp"); // redirecciona a el jsp correspondiente
            
        } else {
            response.sendRedirect("login.jsp"); // redirecciona al login en caso de no coincidir con ninguno
        }
%>