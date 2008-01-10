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
<s:property value="%{getFiltersString()}"/><br/>
<s:property value="%{getAgeFilterString()}"/><br/>
<s:property value="%{getCategoryFilterString()}"/><br/>
<s:property value="%{getColorFilterString()}"/><br/>
<s:property value="%{getLabelFilterString()}"/><br/>
<s:property value="%{getAndFilterString()}"/>