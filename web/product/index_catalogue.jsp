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

<div style="width:95%;margin:auto;margin-bottom:5px;max-height:50px;overflow:auto;">
    <s:iterator value="getAges()">
        <img src="<s:property value="getImage_small()" />"
             onmouseover="show('<s:property value="getName(getLocale())"/>');"
             onmouseout="hide();"
             onclick="var temp = ',' + dojo.byId('ageFilterCat').value + ',';if(temp == ',,') {dojo.byId('ageFilterCat').value = <s:property value="getId()"/>; this.style.borderColor='blue';} else if(temp.search(/,<s:property value="getId()"/>,/) == -1) {dojo.byId('ageFilterCat').value += ',' + <s:property value="getId()"/>; this.style.borderColor='blue';} else {temp = temp.replace(/,<s:property value="getId()"/>,/, ','); if(temp.length == 1) dojo.byId('ageFilterCat').value = ''; else dojo.byId('ageFilterCat').value = temp.substring(1,temp.length-1); this.style.borderColor='white';}"
             style="border-style: solid; border-width: 2px; border-color: <s:if test="isAgeFiltered(getId())">blue</s:if><s:else>white</s:else>;width:15px;height:15px;min-width:15px;min-height:15px;"/>
    </s:iterator>
</div>
<div style="width:95%;margin:auto;margin-bottom:5px;max-height:50px;overflow:auto;">
    <s:iterator value="getCategories()">
        <img src="<s:property value="getImage_small()" />"
             onmouseover="show('<s:property value="getName(getLocale())"/>');"
             onmouseout="hide();"
             onclick="var temp = ',' + dojo.byId('categoryFilterCat').value + ',';if(temp == ',,') {dojo.byId('categoryFilterCat').value = <s:property value="getId()"/>; this.style.borderColor='blue';} else if(temp.search(/,<s:property value="getId()"/>,/) == -1) {dojo.byId('categoryFilterCat').value += ',' + <s:property value="getId()"/>; this.style.borderColor='blue';} else {temp = temp.replace(/,<s:property value="getId()"/>,/, ','); if(temp.length == 1) dojo.byId('categoryFilterCat').value = ''; else dojo.byId('categoryFilterCat').value = temp.substring(1,temp.length-1); this.style.borderColor='white';}"
             style="border-style: solid; border-width: 2px; border-color: <s:if test="isCategoryFiltered(getId())">blue</s:if><s:else>white</s:else>;width:15px;height:15px;min-width:15px;min-height:15px;"/>
    </s:iterator>
</div>
<div style="width:95%;margin:auto;margin-bottom:5px;max-height:50px;overflow:auto;">
    <s:iterator value="getColors()">
        <img src="<s:property value="getImage_small()" />"
             onmouseover="show('<s:property value="getName(getLocale())"/>');"
             onmouseout="hide();"
             onclick="var temp = ',' + dojo.byId('colorFilterCat').value + ',';if(temp == ',,') {dojo.byId('colorFilterCat').value = <s:property value="getId()"/>; this.style.borderColor='blue';} else if(temp.search(/,<s:property value="getId()"/>,/) == -1) {dojo.byId('colorFilterCat').value += ',' + <s:property value="getId()"/>; this.style.borderColor='blue';} else {temp = temp.replace(/,<s:property value="getId()"/>,/, ','); if(temp.length == 1) dojo.byId('colorFilterCat').value = ''; else dojo.byId('colorFilterCat').value = temp.substring(1,temp.length-1); this.style.borderColor='white';}"
             style="border-style: solid; border-width: 2px; border-color: <s:if test="isColorFiltered(getId())">blue</s:if><s:else>white</s:else>;width:15px;height:15px;min-width:15px;min-height:15px;"/>
    </s:iterator>
</div>
<div style="width:95%;margin:auto;margin-bottom:5px;max-height:22px;overflow:auto;">
    <s:iterator value="getLabels()">
        <img src="<s:property value="getImage_small()" />"
             onmouseover="show('<s:property value="getName()"/>');"
             onmouseout="hide();"
             onclick="var temp = ',' + dojo.byId('labelFilterCat').value + ',';if(temp == ',,') {dojo.byId('labelFilterCat').value = <s:property value="getId()"/>; this.style.borderColor='blue';} else if(temp.search(/,<s:property value="getId()"/>,/) == -1) {dojo.byId('labelFilterCat').value += ',' + <s:property value="getId()"/>; this.style.borderColor='blue';} else {temp = temp.replace(/,<s:property value="getId()"/>,/, ','); if(temp.length == 1) dojo.byId('labelFilterCat').value = ''; else dojo.byId('labelFilterCat').value = temp.substring(1,temp.length-1); this.style.borderColor='white';}"
             style="border-style: solid; border-width: 2px; border-color: <s:if test="isLabelFiltered(getId())">blue</s:if><s:else>white</s:else>;width:15px;height:15px;min-width:15px;min-height:15px;"/>
    </s:iterator>
</div>

<form id="filter_form" method="post" style="display:none;">
    <s:hidden id="ageFilterCat" name="ageFilter" value="%{getAgeFilterString()}"/>
    <s:hidden id="categoryFilterCat" name="categoryFilter" value="%{getCategoryFilterString()}"/>
    <s:hidden id="colorFilterCat" name="colorFilter" value="%{getColorFilterString()}"/>
    <s:hidden id="labelFilterCat" name="labelFilter" value="%{getLabelFilterString()}"/>
    <s:hidden id="andFilterCat" name="andFilter" value="%{getAndFilterString()}"/>
</form>

<s:url id="url_content" namespace="/product" action="index_content" includeParams="none"/>
<s:url id="url_location" namespace="/product" action="index_location" includeParams="none"/>
<s:url id="url_catalogue" namespace="/product" action="index_catalogue" includeParams="none"/>
<input type="button" value="<s:text name="search"/>" class="button" onclick="navGo([new navRequest('location', '<s:property value="url_location"/>'),
                                                                    new navRequest('content', '<s:property value="url_content"/>', null, null, 'filter_form'),
                                                                    new navRequest('catalogue', '<s:property value="url_catalogue"/>', null, null, 'filter_form')]);"/>

<s:text name="and"/> <input type="checkbox" <s:if test="getAndFilter()"> checked="true" </s:if> onclick="if(dojo.byId('andFilterCat').value == 'true') dojo.byId('andFilterCat').value = false; else dojo.byId('andFilterCat').value = true;"/>
