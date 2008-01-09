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
	There are <s:property value="getAges().size()" /> ages in the database.<br />

	<s:url id="createage" namespace="/admin" action="age_create" includeParams="none" />
	<s:a href="%{createage}">Create a new age</s:a>

	<br /><br />

	<table border="2" align="center" cellpadding="2" >
	<tr>
	<th>Name (en)</th>
	<th>Name (fr)</th>
	<th>Description (en)</th>
	<th>Description (fr)</th>
	<th>Action</th>
	</tr>

	<s:iterator value="getAges()">
	    <tr>
	    <td align=center><s:property value="getName('en')" /></td>
	    <td align=center><s:property value="getName('fr')" /></td>
	    <td align=center><s:property value="getDescription('en')" /></td>
	    <td align=center><s:property value="getDescription('fr')" /></td>
	   	    
	    <td align=center>

	    <s:url id="modify" namespace="/admin" action="age_modify" includeParams="none">
	    <s:param name="age_id" value="getId()" />
	    </s:url>
	    <s:a href="%{modify}"><s:text name="modify" /></s:a><br />
	    <s:url id="delete" namespace="/admin" action="age_delete" includeParams="none">
	    <s:param name="age_id" value="getId()" />
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