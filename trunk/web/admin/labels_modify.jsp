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
	<h1>Planete-Kids</h1>
	<div align=center>
	<h2><s:text name="admin"/></h2>
	<a href=labels.action><s:text name="labels" /></a>
	<s:text name="colors" />
	<s:text name="categories" />
	<s:text name="products" />
	<s:text name="customers" />
	<br />
	<br />
	<div align=left>
	<form method="post" name="labelform">
	<table>
	<tr><td align=right>Name
        <td><input type="text" name="name" value="<s:property value="getLabel(getLabelId()).getName()" />" size="35"/>
	<tr><td align=right>Description (en)
        <td><input type="text" name="description_fr" value="<s:property value="getLabel(getLabelId()).getDescription('en')" />" size="35"/>
	<tr><td align=right>Description (fr)
        <td><input type="text" name="description_en" value="<s:property value="getLabel(getLabelId()).getDescription('fr')" />" size="35"/>
	<tr><td align=right>Site
        <td><input type="text" name="site" value="<s:property value="getLabel(getLabelId()).getSite()" />" size="35"/>
	<tr><td align=right>Image (large)
        <td><input type="text" name="image_large" value="<s:property value="getLabel(getLabelId()).getImage_large()" />" size="35"/>
	<tr><td align=right>Image (medium)
        <td><input type="text" name="image_medium" value="<s:property value="getLabel(getLabelId()).getImage_medium()" />" size="35"/>
	<tr><td align=right>Image (small)
        <td><input type="text" name="image_small" value="<s:property value="getLabel(getLabelId()).getImage_small()" />" size="35"/>
	</table><br />
	<input type="submit" value="Submit" />
	</form>
	</div>
	</div>
    </s:div>
</body>
</html>