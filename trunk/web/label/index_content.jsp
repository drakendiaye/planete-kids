<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<h2><s:text name="label"/></h2>
<s:div cssClass="main">
    <s:iterator value="getLabels()">
        <script type="text/javascript">coldiv_load('label_<s:property value="getId()" />');</script>
        <s:hidden id="label_%{getId()}" value="{ init : false, col_img : 'images/down_enable.png', uncol_img : 'images/up_enable.png'}"/>
        <div class="label">
            <table width="99%">
                <tr>
                    <td align="left">
                        <h3>
                            <img id="label_<s:property value="getId()" />_img" onclick="coldiv_click('label_<s:property value="getId()"/>')" />
                            <img src="<s:property value="getImage_small()" />" width="20px" height="20px"/>
                            <s:property value="getName(getLocale())" />
                        </h3>
                    </td>
                </tr>
            </table>
            <div id="label_<s:property value="getId()" />_div">
                <hr/>
                <div style="float:left;">
                    <img src="<s:property value="getImage_medium()" />" onmouseover="show('<img src=<s:property value="getImage_large()"/> width=100px height=100px/>')" onmouseout="hide()" width="50px" height="50px"/>
                </div>
                <div style="float:left;">
                    <s:property value="getDescription(getLocale())" /><br/>
                    <s:a href="%{getSite()}"><s:property value="getSite()" /></s:a>
                </div>
                <br clear="both"/>
            </div>
        </div>
    </s:iterator>
</s:div>