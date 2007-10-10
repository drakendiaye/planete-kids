<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%--
<%@ page ... %> // importations de classes le plus souvent
<%!
 // variables d'instance de la page JSP (=globales)
 // nécessaire que si la page JSP a des méthodes partageant des variables (rare)
 ...
%>
<%
 // récupération des données envoyées par la servlet
 // soit dans la requête (request) soit dans la session (session)
 ...
%>
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

    <h1>JSP Page</h1>
    
    <%--
    This example uses JSTL, uncomment the taglib directive above.
    To test, display the page like this: index.jsp?sayHello=true&name=Murphy
    --%>
    <%--
    <c:if test="${param.sayHello}">
        <!-- Let's welcome the user ${param.name} -->
        Hello ${param.name}!
    </c:if>
    --%>
    
    </body>
</html>
