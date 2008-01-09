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
	There are <s:property value="getCustomers().size()" /> customers in the database.<br />
		
	<s:url id="createcustomer" namespace="/admin" action="customer_create" includeParams="none" />
	<s:a href="%{createcustomer}">Create a new customer</s:a>

	<br /><br />

	<table border="2" align="center" cellpadding="2">
	<tr>
	<th>e-mail address</th>
	<th>Password</th>
	<th>First name</th>
	<th>Last name</th>
	<th>Address Line 1</th>
	<th>Address Line 2</th>
	<th>Address Line 3</th>
	<th>ZipCode</th>
	<th>City</th>
	<th width=220>Phone number</th>
	<th width=220>Fax number</th>
	<th>Action</th>
	</tr>

	<s:iterator value="getCustomers()">
	    <tr>
	    <td align=center><s:property value="getEmailAddress()" /></td>
	    <td align=center><s:property value="getPassword()" /></td>
	    <td align=center><s:property value="getFirstName()" /></td>
	    <td align=center><s:property value="getLastName()" /></td>
	    <td align=center><s:property value="getAddressLine1()" /></td>
	    <td align=center><s:property value="getAddressLine2()" /></td>
	    <td align=center><s:property value="getAddressLine3()" /></td>
	    <td align=center><s:property value="getZipCode()" /></td>
	    <td align=center><s:property value="getCity()" /></td>
	    <td align=center><s:property value="getPhoneNumber()" /></td>
	    <td align=center><s:property value="getFaxNumber()" /></td>

	    <td align=center>

	    <s:url id="modify" namespace="/admin" action="customer_modify" includeParams="none">
	    <s:param name="customer_id" value="getEmailAddress()" />
	    </s:url>
	    <s:a href="%{modify}"><s:text name="modify" /></s:a><br />
	    <s:url id="delete" namespace="/admin" action="customer_delete" includeParams="none">
	    <s:param name="customer_id" value="getEmailAddress()" />
	    </s:url>
	    <s:a href="%{delete}"><s:text name="delete" /></s:a>

	    </td>

	    </tr>
	</s:iterator>
	</table>

	</div>
    </s:div>
</body>
</html>