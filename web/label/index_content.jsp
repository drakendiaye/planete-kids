<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<s:div cssClass="contentHeader">
    <h2><s:text name="labels"/></h2>
</s:div>
<s:div cssClass="contentMain">
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
                            <s:property value="getName()" />
                        </h3>
                    </td>
				<td align="right">
				<form id="label_<s:property value="getId()"/>_form">
					<s:hidden name="labelFilter" value="%{getId()}"/>
					<s:url id="url_content" namespace="/product" action="index_content" includeParams="none"/>
					<s:url id="url_location" namespace="/product" action="index_location" includeParams="none"/>
					<s:url id="url_catalogue" namespace="/product" action="index_catalogue" includeParams="none"/>
					<input type="button" value="<s:text name="viewProducts"/>" class="button" 
					onclick="navGo([new navRequest('location','<s:property value="url_location"/>'),
					new navRequest('catalogue','<s:property value="url_catalogue"/>',null,null,'label_<s:property value="getId()"/>_form'),
					new navRequest('content','<s:property value="url_content"/>',null,null,'label_<s:property value="getId()"/>_form')])"/>
				</form>
				</td>
                </tr>
            </table>
            <div id="label_<s:property value="getId()" />_div">
                <hr/>
                <div style="float:left;">
                    <table width="99%">
                        <tr>
                            <td>
                                <img src="<s:property value="getImage_medium()" />" onmouseover="show('<img src=<s:property value="getImage_large()"/> width=100px height=100px/>')" onmouseout="hide()" width="50px" height="50px"/>
                            </td>
                            <td>
                                <s:property value="getDescription(getLocale())" /><br/>
                                <s:a href="http://%{getSite()}"><s:property value="getSite()" /></s:a>
                            </td>
                        </tr>
                    </table>
                </div>
                <br clear="both"/>
            </div>
        </div>
    </s:iterator>
</s:div>