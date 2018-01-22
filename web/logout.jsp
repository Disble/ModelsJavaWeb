<%
    HttpSession sesion = request.getSession();
    sesion.invalidate();
    response.sendRedirect("login.jsp");
%>