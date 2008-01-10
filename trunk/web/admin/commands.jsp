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

	<s:text name="adminP1"/> <s:property value="getCommands().size()" /> <s:text name="adminCommands"/> <s:text name="adminP2"/><br />

	<br /><br />

	<table border="1" align="center" cellpadding="2">
	<tr>
	<th><s:text name="emailID" /></th>
	<th>Date</th>
	<th><s:text name="shippingCosts" /></th>
	<th>Total</th>
	<th><s:text name="cmdstate" /></th>
	<th>Action</th>
	</tr>

	<s:iterator value="getCommands()">
	    <tr>
	    <td align=center><s:property value="getAccount().getEmailAddress()" /></td>
	    <td align=center><s:property value="getDate()" /></td>
	    <td align=center><s:property value="getShipping()" /></td>
	    <td align=center><s:property value="getCommandTotal(getId())" /></td>
	    <td align=center><s:property value="getCommandState(getId()).toString()" /></td>
	    <td align=center>

	    <s:url id="modify" namespace="/admin" action="command_modify" includeParams="none">
	    <s:param name="command_id" value="getId()" />
	    </s:url>
	    <s:a href="%{modify}"><s:text name="details" /></s:a><br />
	    <s:url id="delete" namespace="/admin" action="command_delete" includeParams="none">
	    <s:param name="command_id" value="getId()" />
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