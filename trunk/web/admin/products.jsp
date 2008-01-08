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
	<div style="text-align:center">
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

	<br /><br />
	There are <s:property value="getProducts().size()" /> products in the database.<br />
	<br />

	<table border="2" align="center" cellpadding="2">
	<tr>
	<th>Name (en)</th>
	<th>Name (fr)</th>
	<th>Description (en)</th>
	<th>Description (fr)</th>
	<th>Category</th>
	<th>Color</th>
	<th>Label</th>
	<th>Age</th>
	<th>Price</th>
	<th>Stock</th>
	<th>Image (large)</th>
	<th>Image (medium)</th>
	<th>Image (small)</th>
	<th>Action</th>
	</tr>

	<s:iterator value="getProducts()">
	    <tr>
	    <td align=center><s:property value="getName('en')" /></td>
	    <td align=center><s:property value="getName('fr')" /></td>
	    <td align=center><s:property value="getDescription('en')" /></td>
	    <td align=center><s:property value="getDescription('fr')" /></td>
	    <td align=center><s:property value="getCategory().getName(getLocale())" /></td>
	    <td align=center><s:property value="getColor().getName(getLocale())" /></td>
	    <td align=center><s:property value="getLabel().getName()" /></td>
	    <td align=center><s:property value="getAge().getName(getLocale())" /></td>
	    <td align=center><s:property value="getPrice()" /></td>
	    <td align=center><s:property value="getStock()" /></td>
	    <td align=center><img src="../<s:property value="getImage_large()" />"</td>
	    <td align=center><img src="../<s:property value="getImage_medium()" />"</td>
	    <td align=center><img src="../<s:property value="getImage_small()" />"</td>

	    <td align=center>

	    <s:url id="modify" namespace="/admin" action="products_modify" includeParams="none">
	    <s:param name="product_id" value="getId()" />
	    </s:url>
	    <s:a href="%{modify}">Modify</s:a><br />
	    <s:url id="delete" namespace="/admin" action="product_delete" includeParams="none">
	    <s:param name="product_id" value="getId()" />
	    </s:url>
	    <s:a href="%{delete}">Delete</s:a>

	    </td>
	    </tr>
	</s:iterator>
	</table>

	</div>
    </s:div>
</body>
</html>