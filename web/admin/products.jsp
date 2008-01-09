<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Planete-kids</title>
        <s:head theme="ajax" debug="true" />
        <link href="<s:url value='/style.css'/>" rel="stylesheet" type="text/css"/>

	<script type="text/javascript">
	  function confirmdelete(url) {
	    if (confirm('Are you sure?'))
		window.location.replace(url);
	  }
	</script>
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

	<s:url id="viewcommands" namespace="/admin" action="commands" includeParams="none" />
	<s:a href="%{viewcommands}"><s:text name="commands" /></s:a>

	<br /><br />
	<s:text name="adminP1"></s:text> <s:property value="getProducts().size()" /> <s:text name="products"></s:text> <s:text name="adminP2"></s:text><br />

	<s:url id="createproduct" namespace="/admin" action="product_create" includeParams="none" />
	<s:a href="%{createproduct}"><s:text name="createProduct"></s:text></s:a>

	<br /><br />

	<table border="2" align="center" cellpadding="2">
	<tr>
	<th><s:text name="enName"></s:text></th>
	<th><s:text name="frName"></s:text></th>
	<th>Description (en)</th>
	<th>Description (fr)</th>
	<th><s:text name="category"></s:text></th>
	<th><s:text name="color"></s:text></th>
	<th><s:text name="label"></s:text></th>
	<th><s:text name="age"></s:text></th>
	<th><s:text name="price"></s:text></th>
	<th>Stock</th>
	<th><s:text name="imageLarge"></s:text></th>
	<th><s:text name="imageMedium"></s:text></th>
	<th><s:text name="imageSmall"></s:text></th>
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
	    <td align=center><s:property value="getPrice()" />&nbsp;€</td>
	    <td align=center><s:property value="getStock()" /></td>
	    <td align=center><s:if test="getImage_large() != ''"><img src="../<s:property value="getImage_large()" />"></s:if></td>
	    <td align=center><s:if test="getImage_medium() != ''"><img src="../<s:property value="getImage_medium()" />"></s:if></td>
	    <td align=center><s:if test="getImage_small() != ''"><img src="../<s:property value="getImage_small()" />"></s:if></td>

	    <td align=center>

	    <s:url id="modify" namespace="/admin" action="product_modify" includeParams="none">
	    <s:param name="product_id" value="getId()" />
	    </s:url>
	    <s:a href="%{modify}"><s:text name="modify" /></s:a><br />
	    <s:url id="delete" namespace="/admin" action="product_delete" includeParams="none">
	    <s:param name="product_id" value="getId()" />
	    </s:url>
	    <s:a href="javascript:confirmdelete('%{delete}')"><s:text name="delete" /></s:a>

	    </td>
	    </tr>
	</s:iterator>
	</table>

	</div>
    </s:div>
</body>
</html>