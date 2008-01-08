<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<table>
    <tr>
        <td>
            <img src="images/Panier.png"/>
        </td>
        <td align="left" valign="top">
            <h3><s:text name="cart"/></h3>
        </td>
    </tr>
</table>

<s:div>
    <s:iterator value="getCartProducts()">
        <script type="text/javascript">coldiv_load('cart_<s:property value="getId()" />');</script>
        <s:hidden id="cart_%{getId()}" value="{ init : false, col_img : 'images/down_enable.png', uncol_img : 'images/up_enable.png'}"/>
        <div>
            <img id="cart_<s:property value="getId()" />_img" onclick="coldiv_click('cart_<s:property value="getId()"/>')" style="cursor: hand;" />
            <img src="<s:property value="getImage_small()" />" width="20px" height="20px"/>
            <s:property value="getName(getLocale())" />
            <div id="cart_<s:property value="getId()" />_div">
                <s:property value="getPrice()" />
                <br/>
                <form id="product_<s:property value="getId()"/>_update_form">
                    <s:textfield name="update_%{getId()}_%{getCartProductNumber(getId())}" value="%{getCartProductNumber(getId())}" onkeyup="this.name = 'update_%{getId()}_' + this.value;"/>
                    <s:url id="url" namespace="/" action="index_cart" includeParams="none"/>
                    <input type="submit" value="Mettre à jour la quantitée" class="button"onclick="navExec(new navRequest('cart', '<s:property value="url"/>', null, null, 'product_<s:property value="getId()" />_update_form')); return(false);">
                </form>
                <br/>
                <form id="product_<s:property value="getId()"/>_delete_form">
                    <s:hidden name="delete_%{getId()}"/>
                    <s:url id="url" namespace="/" action="index_cart" includeParams="none"/>
                    <input type="submit" value="Retirer du panier" class="button"onclick="navExec(new navRequest('cart', '<s:property value="url"/>', null, null, 'product_<s:property value="getId()" />_delete_form')); return(false);">
                </form>
            </div>
        </div>
    </s:iterator>
</s:div>
