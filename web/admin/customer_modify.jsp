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

	<s:form method="post" namespace="/admin" action="customer_valid">
	<table align="center">
	<input type="hidden" name="customer_id" value="<s:property value="getCustomerId()" />" />
	<tr><td align=right><s:text name="password"/>
        <td><input type="text" name="password" value="<s:property value="getCustomer(getCustomerId()).getPassword()" />" size="35"/>
	<tr><td align=right><s:text name="firstName"/>
        <td><input type="text" name="firstname" value="<s:property value="getCustomer(getCustomerId()).getFirstName()" />" size="35"/>
	<tr><td align=right><s:text name="lastName"/>
        <td><input type="text" name="lastname" value="<s:property value="getCustomer(getCustomerId()).getLastName()" />" size="35"/>
	<tr><td align=right><s:text name="addressLine1"/>
        <td><input type="text" name="addr1" value="<s:property value="getCustomer(getCustomerId()).getAddressLine1()" />" size="35"/>
	<tr><td align=right><s:text name="addressLine2"/>
        <td><input type="text" name="addr2" value="<s:property value="getCustomer(getCustomerId()).getAddressLine2()" />" size="35"/>
	<tr><td align=right><s:text name="addressLine3"/>
        <td><input type="text" name="addr3" value="<s:property value="getCustomer(getCustomerId()).getAddressLine3()" />" size="35"/>
	<tr><td align=right><s:text name="zipCode"/>
        <td><input type="text" name="zipcode" value="<s:property value="getCustomer(getCustomerId()).getZipCode()" />" size="35"/>
	<tr><td align=right><s:text name="code"/>
        <td><input type="text" name="city" value="<s:property value="getCustomer(getCustomerId()).getCity()" />" size="35"/>
	<tr><td align=right><s:text name="phoneNumber"/>
        <td><input type="text" name="phone" value="<s:property value="getCustomer(getCustomerId()).getPhoneNumber()" />" size="35"/>
	<tr><td align=right><s:text name="faxNumber"/>
        <td><input type="text" name="fax" value="<s:property value="getCustomer(getCustomerId()).getFaxNumber()" />" size="35"/>	
	</table><br />
	<input type="submit" value="<s:text name="valid" />" />
	</s:form>

	</div>
    </s:div>
</body>
</html>