<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<table>
    <tr>
        <td>
            <img src="images/Catalogue.png"/>
        </td>
        <td align="left" valign="top">
            <h3><s:text name="catalogue"/></h3>
        </td>
    </tr>
</table>

<s:iterator value="getAgeFilter()">
    <img src="<s:property value="getAge(intValue()).getImage_small()" />" width="20px" height="20px"/>
</s:iterator>
<br/>
<s:iterator value="getCategoryFilter()">
    <img src="<s:property value="getCategory(intValue()).getImage_small()" />" width="20px" height="20px"/>
</s:iterator>
<br/>
<s:iterator value="getClorFilter()">
    <img src="<s:property value="getColor(intValue()).getImage_small()" />" width="20px" height="20px"/>
</s:iterator>
<br/>
<s:iterator value="getLabelFilter()">
    <img src="<s:property value="getLabel(intValue()).getImage_small()" />" width="20px" height="20px"/>
</s:iterator>
