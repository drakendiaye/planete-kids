<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:div cssClass="contentHeader">
	<h2><s:text name="ship" /></h2>
</s:div>
<s:div cssClass="contentMain">
	<form id="product_validate_form">
	<table class="account">
		<tr>
			<td align="right"><input type="radio" checked="checked" name="ship" value="5" /></td>
			<td align="left">Colissimo (5 €)</td>
		</tr>
		<tr>
			<td align="right"><input type="radio" name="ship" value="10" /></td>
			<td align="left">Chronopost (10 €)</td>
		</tr>
		<tr>
			<td colspan="2" align="center"><s:url id="url_paiement_content" namespace="/" action="paiement_content" includeParams="none" /> <s:url
				id="url_paiement_location" namespace="/" action="paiement_location" includeParams="none" /> <s:url id="url" namespace="/"
				action="valid_cart" includeParams="none" /> <input type="button" value="<s:text name="validateCart"/>" class="button"
				onclick="navExec(new navRequest('cart', '<s:property value="url"/>', null, null, 'product_validate_form'));
            navGo([new navRequest('content', '<s:property value="url_paiement_content"/>'),
     new navRequest('location', '<s:property value="url_paiement_location"/>')]);" />
			</td>
		</tr>
	</table>
	</form>
</s:div>