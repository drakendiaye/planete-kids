<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<h2><s:text name="category"/></h2>
<s:div cssClass="main">
    <s:iterator value="getCategories()">
        <script type="text/javascript">coldiv_load('category_<s:property value="getId()" />');</script>
        <s:hidden id="category_%{getId()}" value="{ init : false, col_img : 'images/down_enable.png', uncol_img : 'images/up_enable.png'}"/>
        <div class="category">
            <table width="99%">
                <tr>
                    <td align="left">
                        <h3>
                            <img id="category_<s:property value="getId()" />_img" onclick="coldiv_click('category_<s:property value="getId()"/>')" />
                            <img src="<s:property value="getImage_small()" />" width="20px" height="20px"/>
                            <s:property value="getName(getLocale())" />
                        </h3>
                    </td>
                </tr>
            </table>
            <div id="category_<s:property value="getId()" />_div">
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
                        </tr>
                    </table>
                </div>
                <br clear="both"/>
            </div>
        </div>
    </s:iterator>
</s:div>