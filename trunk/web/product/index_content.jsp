<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<s:div cssClass="contentHeader">
    <h2>
        <s:text name="product"/>
        <s:if test="getPages().size() > 1">
            <form id="pageForm" method="post" style="display: none">
                <s:hidden id="page_number" name="page"/>
            </form>
            <s:url id="url" namespace="/product" action="index_content" includeParams="none"/>
            <s:if test="1 == getPage()">
                <input type="button" disabled class="disabledButton" value="|<" onclick="dojo.byId('page_number').value = 1;navGo([new navRequest('content', '<s:property value="url"/>', null, null, 'pageForm')]);"/>
            </s:if>
            <s:else>
                <input type="button" class="button" value="|<" onclick="dojo.byId('page_number').value = 1;navGo([new navRequest('content', '<s:property value="url"/>', null, null, 'pageForm')]);"/>
            </s:else>
            <s:if test="getPage() - 1 < 1">
                <input type="button" disabled class="disabledButton" value="<" onclick="dojo.byId('page_number').value = <s:property value="getPage()"/> - 1;navGo([new navRequest('content', '<s:property value="url"/>', null, null, 'pageForm')]);"/>
            </s:if>
            <s:else>
                <input type="button" class="button" value="<" onclick="dojo.byId('page_number').value = <s:property value="getPage()"/> - 1;navGo([new navRequest('content', '<s:property value="url"/>', null, null, 'pageForm')]);"/>
            </s:else>
            <s:iterator value="getPages()">
                <s:if test="intValue() == getPage()">
                    <input type="button" disabled class="disabledButton" value="<s:property value="intValue()"/>" onclick="dojo.byId('page_number').value = <s:property value="intValue()"/>;navGo([new navRequest('content', '<s:property value="url"/>', null, null, 'pageForm')]);"/>
                </s:if>
                <s:else>
                    <input type="button" class="button" value="<s:property value="intValue()"/>" onclick="dojo.byId('page_number').value = <s:property value="intValue()"/>;navGo([new navRequest('content', '<s:property value="url"/>', null, null, 'pageForm')]);"/>
                </s:else>
            </s:iterator>
            <s:if test="getPage() + 1 > getNbPage()">
                <input type="button" disabled class="disabledButton" value=">" onclick="dojo.byId('page_number').value = <s:property value="getPage()"/> + 1;navGo([new navRequest('content', '<s:property value="url"/>', null, null, 'pageForm')]);"/>
            </s:if>
            <s:else>
                <input type="button" class="button" value=">" onclick="dojo.byId('page_number').value = <s:property value="getPage()"/> + 1;navGo([new navRequest('content', '<s:property value="url"/>', null, null, 'pageForm')]);"/>
            </s:else>
            <s:if test="getPage() == getNbPage()">
                <input type="button" disabled class="disabledButton" value=">|" onclick="dojo.byId('page_number').value = <s:property value="getNbPage()"/>;navGo([new navRequest('content', '<s:property value="url"/>', null, null, 'pageForm')]);"/>
            </s:if>
            <s:else>
                <input type="button" class="button" value=">|" onclick="dojo.byId('page_number').value = <s:property value="getNbPage()"/>;navGo([new navRequest('content', '<s:property value="url"/>', null, null, 'pageForm')]);"/>
            </s:else>
        </s:if>
    </h2>
</s:div>
<s:div cssClass="contentMain">
    <s:iterator value="getProducts()">
        <script type="text/javascript">coldiv_load('product_<s:property value="getId()" />');</script>
        <s:hidden id="product_%{getId()}" value="{ init : false, col_img : 'images/down_enable.png', uncol_img : 'images/up_enable.png'}"/>
        <div class="product">
            <table width="99%">
                <tr>
                    <td align="left">
                        <h3>
                            <img id="product_<s:property value="getId()" />_img" onclick="coldiv_click('product_<s:property value="getId()"/>')" style="cursor: pointer;" />
                            <img src="<s:property value="getImage_small()" />" width="20px" height="20px"/>
                            <s:property value="getName(getLocale())" />
                        </h3>
                    </td>
                    <td align="right">
                        <form id="product_<s:property value="getId()"/>_form">
                            <s:property value="getPrice()" />&nbsp;€
                            <s:hidden name="add_%{getId()}"/>
                            <s:url id="url" namespace="/" action="index_cart" includeParams="none"/>
                            <input type="submit" value="<s:text name="addCart"/>" class="button"onclick="navExec(new navRequest('cart', '<s:property value="url"/>', null, null, 'product_<s:property value="getId()" />_form')); return(false);">
                        </form>
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
                            <td width="60%">
                                <s:property value="getDescription(getLocale())" />
                            </td>
                            <td width="30%">
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
                                    <tr>
                                        <td align="right">
                                            <s:text name="age"/> : 
                                        </td>
                                        <td align="left">
                                            <s:property value="getAge().getName(getLocale())" />
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