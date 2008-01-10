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
	    if (confirm('Êtes-vous sûr ?'))
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

	<s:url id="indexadminfr" namespace="/admin" action="index" includeParams="none">
	<s:param name="request_locale" >fr</s:param>
	</s:url>

	<s:url id="indexadminen" namespace="/admin" action="index" includeParams="none">
	<s:param name="request_locale" >en</s:param>
	</s:url>

        <s:a href="%{indexadminfr}"><img src="../images/fr.png" /></s:a>
        <s:a href="%{indexadminen}"><img src="../images/en.png" /></s:a>

	<br /><br />
	<s:text name="adminP1"/> <s:property value="getNbProducts()" /> <s:text name="products"/> <s:text name="adminP2"/><br />

	<s:url id="createproduct" namespace="/admin" action="product_create" includeParams="none" />
	<s:a href="%{createproduct}"><s:text name="createProduct"/></s:a>

	<br /><br />

	<s:text name="nbpages" />: <s:property value="getNbProductPage()" />

	<s:form method="post" namespace="/admin" action="products">
	    Page: <input size="4" type="text" name="pagenum" value="<s:property value="getProductPagenum()" />" />
        </s:form>

	<s:url id="viewproductsprev" namespace="/admin" action="products" includeParams="none">
	<s:param name="pagenum" value="getProductPagenum() - 1" />
	</s:url>

	<s:url id="viewproductsnext" namespace="/admin" action="products" includeParams="none">
	<s:param name="pagenum" value="getProductPagenum() + 1" />
	</s:url>

	<table align="center"><tr><td align="right" style="width:160px">
	<s:if test="(getProductPagenum() - 1) > 0">
	    <s:a href="%{viewproductsprev}"><s:text name="prevpage" /></s:a>
	</s:if>
	</td><td align="left" style="width:160px">
	<s:if test="getProductPagenum() < getNbProductPage()">
	    <s:a href="%{viewproductsnext}"><s:text name="nextpage" /></s:a>
	</s:if>
	</td></tr>
	</table>

	<br />

	<table border="1" align="center" cellpadding="2">
	<tr>
	<th><s:text name="enName"/></th>
	<th><s:text name="frName"/></th>
	<th>Description (en)</th>
	<th>Description (fr)</th>
	<th><s:text name="category"/></th>
	<th><s:text name="color"/></th>
	<th><s:text name="label"/></th>
	<th><s:text name="age"/></th>
	<th><s:text name="price"/></th>
	<th>Stock</th>
	<th><s:text name="imageLarge"/></th>
	<th><s:text name="imageMedium"/></th>
	<th><s:text name="imageSmall"/></th>
	<th>Action</th>
	</tr>

	<s:iterator value="getPagedProducts()">
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
	    <s:param name="pagenum" value="getProductPagenum()" />
	    </s:url>
	    <s:a href="%{modify}"><s:text name="modify" /></s:a><br />
	    <s:url id="delete" namespace="/admin" action="product_delete" includeParams="none">
	    <s:param name="product_id" value="getId()" />
	    <s:param name="pagenum" value="getProductPagenum()" />
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