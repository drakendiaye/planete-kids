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
    <div align="center">
        <b><s:property value="getCartPrice()"/>&nbsp;€</b>
    </div>
    <hr>
    <div align="center">
        <form id="product_<s:property value="getId()"/>_flush_form">
            <s:hidden name="flush"/>
            <s:url id="url" namespace="/" action="index_cart" includeParams="none"/>
            <input type="submit" value="<s:text name="emptyCart"/>" class="button" onclick="navExec(new navRequest('cart', '<s:property value="url"/>', null, null, 'product_<s:property value="getId()" />_flush_form')); return(false);">
        </form>
    </div>
    <hr>
    <div align="center">
        <form id="product_<s:property value="getId()"/>_validate_form">
            <s:hidden name="validate"/>
            <s:url id="url" namespace="/" action="valid_cart" includeParams="none" />
            <input type="submit" value="<s:text name="validateCart"/>" class="button" onclick="navExec(new navRequest('cart', '<s:property value="url"/>', null, null, 'product_<s:property value="getId()" />_validate_form')); return(false);">
        </form>
    </div>
    <hr>
    <div style="overflow: auto; height: 300px;">
        <s:iterator value="getCartProducts()">
            <div class="cartProduct">
                <script type="text/javascript">coldiv_load('cart_<s:property value="getId()" />');</script>
                <s:hidden id="cart_%{getId()}" value="{ init : false, col_img : 'images/down_enable.png', uncol_img : 'images/up_enable.png'}"/>
                <table>
                    <tr>
                        <td align="left" valign="middle">
                            <img id="cart_<s:property value="getId()" />_img" onclick="coldiv_click('cart_<s:property value="getId()"/>')" style="cursor: pointer;" />
                        </td>
                        <td align="left" valign="top">
                            <img src="<s:property value="getImage_small()" />" width="20px" height="20px"/>
                            <s:property value="getName(getLocale())" />
                        </td>
                    </tr>
                </table>
                <div id="cart_<s:property value="getId()" />_div" align="center" style="display: none;">
                    <form id="product_<s:property value="getId()"/>_update_form" onsubmit="checkValue_<s:property value="getId()"/>()">
                        <s:textfield id="product_%{getId()}_update_textfield" name="update_%{getId()}_%{getCartProductNumber(getId())}" value="%{getCartProductNumber(getId())}" onkeyup="this.name = 'update_%{getId()}_' + this.value;" size="1"/>&nbsp;x&nbsp;<s:property value="getPrice()" />&nbsp;€
                        <s:url id="url" namespace="/" action="index_cart" includeParams="none"/>
                        <br/>
                        <script type="text/javascript">
                            function checkValue_<s:property value="getId()"/>() {
                                if (dojo.byId('product_<s:property value="getId()"/>_update_textfield').value > <s:property value="getStock()"/>) {
                                    dojo.byId('product_<s:property value="getId()"/>_update_textfield').value = "<s:property value="getCartProductNumber(getId())"/>";
                                    alert('<s:property value="getStock()"/> <s:text name="maxStock"/>');
                                    navExec(new navRequest('cart', '<s:property value="url"/>', null, null, null)); return(false);
                                }
                                else {
                                    navExec(new navRequest('cart', '<s:property value="url"/>', null, null, 'product_<s:property value="getId()" />_update_form')); return(false);
                                }
                            }
                        </script>
                        <input type="button" value="<s:text name="updateQuantity"/>" class="button" onclick="checkValue_<s:property value="getId()"/>()"/>
                    </form>
                    <form id="product_<s:property value="getId()"/>_delete_form">
                        <s:hidden name="delete_%{getId()}"/>
                        <s:url id="url" namespace="/" action="index_cart" includeParams="none"/>
                        <input type="submit" value="<s:text name="removeCart"/>" class="button" onclick="navExec(new navRequest('cart', '<s:property value="url"/>', null, null, 'product_<s:property value="getId()" />_delete_form')); return(false);"/>
                    </form>
                </div>
            </div>
            <br/>
        </s:iterator>
    </div>
</s:div>
