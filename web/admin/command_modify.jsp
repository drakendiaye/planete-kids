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

	<br />
	<br />

	<table align="center">
	<tr><td align=right><s:text name="emailID"/>: </td><td><s:property value="getCommand(getCommandId()).getAccount().getEmailAddress()" /></td></tr>
	<tr><td align=right>Date: </td><td><s:property value="getCommand(getCommandId()).getDate()" /></td></tr>
	</table>
	<br />
	<table border="1" align="center" cellpadding="2">
	<tr>
	<th><s:text name="name"/> </th>
	<th><s:text name="quantity"/> </th>
	<th><s:text name="price"/> </th>
	</tr>

	<s:iterator value="getCommandLinesByCommand(getCommandId())">
	    <tr>
	    <td align=center><s:property value="getName(getLocale())" /></td>
	    <td align=center><s:property value="getNumber()" /></td>
	    <td align=center><s:property value="getPrice()" />&nbsp;€</td>
	    </tr>
	</s:iterator>
	    <tr><td colspan=2 align=right><s:text name="shippingCosts"/> </td><td><s:property value="getCommand(getCommandId()).getShipping()" />&nbsp;€</td></tr>
	    <tr><td colspan=2 align=right><b>Total</b></td><td><s:property value="getCommandTotal(getCommandId())" />&nbsp;€</td></tr>
	</table>

	<s:form method="post" namespace="/admin" action="command_valid">
	<input type="hidden" name="command_id" value="<s:property value="getCommandId()" />" />	
	<br />
	<table align="center">
	<tr><td align=right><s:text name="changestate"/>: </td>
	<td>
	    <select name="state">
		<option value="CREATED" <s:if test="getCommandState(getCommandId()).toString()=='CREATED'">selected</s:if>><s:text name="CREATED" /></option>
		<option value="PROCESSING" <s:if test="getCommandState(getCommandId()).toString()=='PROCESSING'">selected</s:if>><s:text name="PROCESSING" /></option>
		<option value="DISPATCHED" <s:if test="getCommandState(getCommandId()).toString()=='DISPATCHED'">selected</s:if>><s:text name="DISPATCHED" /></option>
		<option value="FINISHED" <s:if test="getCommandState(getCommandId()).toString()=='FINISHED'">selected</s:if>><s:text name="FINISHED" /></option>
	    </select>
	</td></tr>
	</table><br />
	<input type="submit" value="<s:text name="valid" />" />
	</s:form>
	
	</div>
    </s:div>
</body>
</html>