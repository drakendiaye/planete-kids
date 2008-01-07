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
	<br /><br />
	There are <s:property value="getNumLabels()" /> labels in the database.<br />
	<br />

	<table border="2">
	<tr>
	<th>Name (en)</th>
	<th>Name (fr)</th>
	<th>Description (en)</th>
	<th>Description (fr)</th>
	<th>Site</th>
	<th>Image (large)</th>
	<th>Image (medium)</th>
	<th>Image (small)</th>
	<th>Action</th>
	</tr>

	<s:iterator value="getLabels()">
	    <tr>
	    <td align=center><s:property value="getName('en')" /></td>
	    <td align=center><s:property value="getName('fr')" /></td>
	    <td align=center><s:property value="getDescription('en')" /></td>
	    <td align=center><s:property value="getDescription('fr')" /></td>
	    <td align=center><s:property value="getSite()" /></td>
	    <td align=center><img src="../<s:property value="getImage_large()" />"</td>
	    <td align=center><img src="../<s:property value="getImage_medium()" />"</td>
	    <td align=center><img src="../<s:property value="getImage_small()" />"</td>
	    <s:url id="modify" namespace="/admin" action="labels_modify" includeParams="none">
	    <s:param name="label_id" value="getId()"></s:param>
	    </s:url>
	    <td align=center><s:a href="%{modify}">Modify</s:a><br />Delete</td>
	    </tr>
	</s:iterator>
	</table>

	</div>
    </s:div>
</body>
</html>