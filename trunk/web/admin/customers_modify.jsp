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

	<br />
	<br />
	<div align=left>
	<form method="post" name="customerform">
	<table>
	<tr><td align=right>Password
        <td><input type="text" name="name" value="<s:property value="getCustomer(getCustomerId()).getPassword()" />" size="35"/>
	<tr><td align=right>First Name
        <td><input type="text" name="description_fr" value="<s:property value="getCustomer(getCustomerId()).getFirstName()" />" size="35"/>
	<tr><td align=right>Last Name
        <td><input type="text" name="description_en" value="<s:property value="getCustomer(getCustomerId()).getLastName()" />" size="35"/>
	<tr><td align=right>Address Line 1
        <td><input type="text" name="site" value="<s:property value="getCustomer(getCustomerId()).getAddressLine1()" />" size="35"/>
	<tr><td align=right>Address Line 2
        <td><input type="text" name="image_large" value="<s:property value="getCustomer(getCustomerId()).getAddressLine2()" />" size="35"/>
	<tr><td align=right>Address Line 3
        <td><input type="text" name="image_medium" value="<s:property value="getCustomer(getCustomerId()).getAddressLine3()" />" size="35"/>
	<tr><td align=right>ZipCode
        <td><input type="text" name="image_small" value="<s:property value="getCustomer(getCustomerId()).getZipCode()" />" size="35"/>
	<tr><td align=right>City
        <td><input type="text" name="image_small" value="<s:property value="getCustomer(getCustomerId()).getCity()" />" size="35"/>
	<tr><td align=right>Phone Number
        <td><input type="text" name="image_small" value="<s:property value="getCustomer(getCustomerId()).getPhoneNumber()" />" size="35"/>
	<tr><td align=right>Fax Number
        <td><input type="text" name="image_small" value="<s:property value="getCustomer(getCustomerId()).getFaxNumber()" />" size="35"/>	
	</table><br />
	<input type="submit" value="Submit" />
	</form>
	</div>
	</div>
    </s:div>
</body>
</html>