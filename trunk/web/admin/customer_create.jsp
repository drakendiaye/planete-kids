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

	<s:form method="post" namespace="/admin" action="customer_valid_create">
	<table align="center">
	<tr><td align=right><s:text name="password"></s:text>
        <td><input type="text" name="password" size="35"/>
	<tr><td align=right><s:text name="firstName"></s:text>
        <td><input type="text" name="firstname" size="35"/>
	<tr><td align=right><s:text name="lastName"></s:text>
        <td><input type="text" name="lastname" size="35"/>
	<tr><td align=right><s:text name="addressLine1"></s:text>
        <td><input type="text" name="addr1" size="35"/>
	<tr><td align=right><s:text name="addressLine2"></s:text>
        <td><input type="text" name="addr2" size="35"/>
	<tr><td align=right><s:text name="addressLine3"></s:text>
        <td><input type="text" name="addr3" size="35"/>
	<tr><td align=right><s:text name="zipCode"></s:text>
        <td><input type="text" name="zipcode" size="35"/>
	<tr><td align=right><s:text name="city"></s:text>
        <td><input type="text" name="city" size="35"/>
	<tr><td align=right><s:text name="phoneNumber"></s:text>
        <td><input type="text" name="phone" size="35"/>
	<tr><td align=right><s:text name="faxNumber"></s:text>
        <td><input type="text" name="fax" size="35"/>	
	</table><br />
	<input type="submit" value="Submit" />
	</s:form>

	</div>
    </s:div>
</body>
</html>