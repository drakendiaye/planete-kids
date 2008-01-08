<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<h2><s:text name="product"/></h2>
<s:div cssClass="main">
    <s:iterator value="getProducts()">
        <script type="text/javascript">coldiv_load('product_<s:property value="getId()" />');</script>
        <s:hidden id="product_%{getId()}" value="{ init : false, col_img : 'images/down_enable.png', uncol_img : 'images/up_enable.png'}"/>
        <div class="product">
            <table width="99%">
                <tr>
                    <td align="left">
                        <h3>
                            <img id="product_<s:property value="getId()" />_img" onclick="coldiv_click('product_<s:property value="getId()"/>')" style="cursor: hand;" />
                            <img src="<s:property value="getImage_small()" />" width="20px" height="20px"/>
                            <s:property value="getName(getLocale())" />
                        </h3>
                    </td>
                    <td align="right">
                        <s:property value="getPrice()" />&nbsp;€
                        <input type="submit" value="Ajouter au panier" class="button">
                    </td>
                </tr>
            </table>
            <div id="product_<s:property value="getId()" />_div">
                <hr/>
                <div style="float:left;">
                    <table width="99%">
                        <tr>
                            <td>
                                <img src="<s:property value="getImage_medium()" />" onmouseover="show('<img src=<s:property value="getImage_large()"/> width=100px height=100px/>')" onmouseout="hide()" width="50px" height="50px"/>
                            </td>
                            <td>
                                <s:property value="getDescription(getLocale())" />
                            </td>
                            <td>
                                <table>
                                    <tr>
                                        <td align="right">
                                            <s:text name="category"/> : 
                                        </td>
                                        <td align="left">
                                            <img src="<s:property value="getCategory().getImage_small()" />" width="20px" height="20px" onmouseover="show('<s:property value="getCategory().getName(getLocale())" />')" onmouseout="hide()"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right">
                                            <s:text name="color"/> : 
                                        </td>
                                        <td align="left">
                                            <img src="<s:property value="getColor().getImage_small()" />" width="20px" height="20px" onmouseover="show('<s:property value="getColor().getName(getLocale())" />')" onmouseout="hide()"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right">
                                            <s:text name="label"/> : 
                                        </td>
                                        <td align="left">
                                            <img src="<s:property value="getLabel().getImage_small()" />" width="20px" height="20px" onmouseover="show('<s:property value="getLabel().getName()" />')" onmouseout="hide()"/>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </div>
                <br clear="both"/>
            </div>
        </div>
    </s:iterator>
</s:div>