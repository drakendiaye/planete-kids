<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<h2><s:text name="product"/></h2>
<s:div cssClass="main">
    <s:iterator value="getProducts()">
        <script type="text/javascript">coldiv_load('product_<s:property value="getId()" />');</script>
        <s:hidden id="product_%{getId()}" value="{ init : false, col_img : 'images/down_enable.png', uncol_img : 'images/up_enable.png'}"/>
        <div class="product">
            <h3>
                <img id="product_<s:property value="getId()" />_img" onclick="coldiv_click('product_<s:property value="getId()"/>')" />
                <img src="<s:property value="getImage_small()" />" width="20px" height="20px"/>
                <s:property value="getName(getLocale())" />
            </h3>
            <div id="product_<s:property value="getId()" />_div">
                <div style="float:left;">
                    <img src="<s:property value="getImage_medium()" />" onmouseover="show('<img src=<s:property value="getImage_large()"/> width=100px height=100px/>')" onmouseout="hide()" width="50px" height="50px"/>
                </div>
                <div style="float:left;">
                    <s:property value="getDescription(getLocale())" /><br/>
                    <s:text name="price"/>&nbsp;:&nbsp;<s:property value="getPrice()" />&nbsp;Euros<br/>
                    <img src="<s:property value="getCategory().getImage_small()" />" width="20px" height="20px" onmouseover="show('<s:property value="getCategory().getName(getLocale())" />')" onmouseout="hide()"/>
                    <img src="<s:property value="getColor().getImage_small()" />" width="20px" height="20px" onmouseover="show('<s:property value="getColor().getName(getLocale())" />')" onmouseout="hide()"/>   
                    <img src="<s:property value="getLabel().getImage_small()" />" width="20px" height="20px" onmouseover="show('<s:property value="getLabel().getName(getLocale())" />')" onmouseout="hide()"/>
                </div>
                <br clear="both"/>
            </div>
        </div>
    </s:iterator>
</s:div>