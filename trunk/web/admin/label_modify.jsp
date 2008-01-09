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
	<div style="text-align:center">
	<img src="../images/Planete-Kids.png" /><br />
	<h2><s:text name="admin"/></h2>

	<s:url id="viewlabels" namespace="/admin" action="labels" includeParams="none" />
	<s:a href="%{viewlabels}"><s:text name="labels" /></s:a>
	
	<s:url id="viewcolors" namespace="/admin" action="colors" includeParams="none" />
	<s:a href="%{viewcolors}"><s:text name="colors" /></s:a>
	
	<s:url id="viewcategories" namespace="/admin" action="categories" includeParams="none" />
	<s:a href="%{viewcategories}"><s:text name="categories" /></s:a>
	
	<s:url id="viewproducts" namespace="/admin" action="products" includeParams="none" />
	<s:a href="%{viewproducts}"><s:text name="products" /></s:a>
	
	<s:url id="viewcustomers" namespace="/admin" action="customers" includeParams="none" />
	<s:a href="%{viewcustomers}"><s:text name="customers" /></s:a>

	<s:url id="viewages" namespace="/admin" action="ages" includeParams="none" />
	<s:a href="%{viewages}"><s:text name="ages" /></s:a>
	<br />
	<br />

	<s:form method="post" namespace="/admin" action="label_valid">
	<table align="center" >
	<input type="hidden" name="label_id" value="<s:property value="getLabelId()" />" />
	<tr><td align=right>Name
        <td><input type="text" name="name" value="<s:property value="getLabel(getLabelId()).getName()" />" size="35"/>
	<tr><td align=right>Description (en)
        <td><input type="text" name="description_en" value="<s:property value="getLabel(getLabelId()).getDescription('en')" />" size="35"/>
	<tr><td align=right>Description (fr)
        <td><input type="text" name="description_fr" value="<s:property value="getLabel(getLabelId()).getDescription('fr')" />" size="35"/>
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
	</s:form>

	</div>
    </s:div>
</body>
</html>