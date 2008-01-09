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

	<br />
	<br />

	<table align="center">
	<tr><td align=right><s:text name="emailID"/>: </td><td><s:property value="getCommand(getCommandId()).getAccount().getEmailAddress()" /></td></tr>
	<tr><td align=right>Date: </td><td><s:property value="getCommand(getCommandId()).getDate()" /></td></tr>
	</table>
	<br />
	<table border="2" align="center" cellpadding="2">
	<tr>
	<th><s:text name="enName"></s:text> </th>
	<th><s:text name="frName"></s:text> </th>
	<th><s:text name="price"></s:text> </th>
	<th><s:text name="quantity"></s:text> </th>
	</tr>

	<s:iterator value="getCommandLinesByCommand(getCommandId())">
	    <tr>
	    <td align=center><s:property value="getName().getEn()" /></td>
	    <td align=center><s:property value="getName().getFr()" /></td>
	    <td align=center><s:property value="getNumber()" />&nbsp;€</td>
	    <td align=center><s:property value="getPrice()" />&nbsp;€</td>
	    </tr>
	</s:iterator>
	    <tr><td colspan=3 align=right><s:text name="shippingCosts"></s:text> </td><td><s:property value="getCommand(getCommandId()).getShipping()" /></td></tr>
	    <tr><td colspan=3 align=right><b>Total</b></td><td><s:property value="getCommandTotal(getCommandId())" />&nbsp;€</td></tr>
	</table>

	<s:form method="post" namespace="/admin" action="command_valid">
	<table align="center">
	<tr><td align=right><s:text name="state"></s:text></td>
	<td>
	    <select name="state">
		<option value="0" ><s:text name="CREATED" /></option>
		<option value="1" ><s:text name="PROCESSING" /></option>
		<option value="2" ><s:text name="DISPATCHED" /></option>
		<option value="3" ><s:text name="FINISHED" /></option>
	    </select>
	</td></tr>
	</table>
	</s:form>
	
	</div>
    </s:div>
</body>
</html>