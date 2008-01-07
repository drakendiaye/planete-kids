<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Planete-kids</title>
        <s:head theme="ajax" debug="true" />
        <link href="<s:url value='/style.css'/>" rel="stylesheet" type="text/css"/>
    </head>
    <body>

    <s:div cssClass="main">
	<div align=center>
	<h1>Planete-Kids</h1>
	<h2><s:text name="admin"/></h2>
	<a href=labels.action><s:text name="labels" /></a>
	<s:text name="colors" />
	<s:text name="categories" />
	<s:text name="products" />
	<s:text name="customers" />
	</div>
    </s:div>
</body>
</html>