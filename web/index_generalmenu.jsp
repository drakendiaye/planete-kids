<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<table>
    <tr>
        <td>
            <img src="images/Menu.png"/>
        </td>
        <td align="left" valign="top">
            <h3><s:text name="generalmenu"/></h3>
        </td>
    </tr>
</table>
<table>
<tr>
<td><img src="images/littleEarth.png"/></td>
<td>
<s:url id="url_content" namespace="/" action="index_content" includeParams="none"/>
<s:url id="url_location" namespace="/" action="index_location" includeParams="none"/>
<s:a onclick="navGo([new navRequest('content', '%{url_content}'),
                     new navRequest('location', '%{url_location}')])">
    <s:text name="welcome"/>
</s:a>
</td>
</tr>
<tr>
<td></td>
<td>
<s:url id="url_content" namespace="/account" action="index_content" includeParams="none"/>
<s:url id="url_location" namespace="/account" action="index_location" includeParams="none"/>
<s:a onclick="navGo([new navRequest('content', '%{url_content}'),
                     new navRequest('location', '%{url_location}')])">
    <s:text name="account"/>
</s:a>
</td>
</tr>
<tr>
<td><img src="images/littleEarth.png"/></td>
<td>
<s:url id="url_content" namespace="/category" action="index_content" includeParams="none"/>
<s:url id="url_location" namespace="/category" action="index_location" includeParams="none"/>
<s:a onclick="navGo([new navRequest('content', '%{url_content}'),
                     new navRequest('location', '%{url_location}')])">
    <s:text name="categories"/>
</s:a>
</td>
</tr>
<tr>
<td><img src="images/littleEarth.png"/></td>
<td>
<s:url id="url_content" namespace="/color" action="index_content" includeParams="none"/>
<s:url id="url_location" namespace="/color" action="index_location" includeParams="none"/>
<s:a onclick="navGo([new navRequest('content', '%{url_content}'),
                     new navRequest('location', '%{url_location}')])">
    <s:text name="colors"/>
</s:a>
</td>
</tr>
<tr>
<td><img src="images/littleEarth.png"/></td>
<td>
<s:url id="url_content" namespace="/label" action="index_content" includeParams="none"/>
<s:url id="url_location" namespace="/label" action="index_location" includeParams="none"/>
<s:a onclick="navGo([new navRequest('content', '%{url_content}'),
                     new navRequest('location', '%{url_location}')])">
    <s:text name="labels"/>
</s:a>
</td>
</tr>
<tr>
<td><img src="images/littleEarth.png"/></td>
<td>
<s:url id="url_content" namespace="/age" action="index_content" includeParams="none"/>
<s:url id="url_location" namespace="/age" action="index_location" includeParams="none"/>
<s:a onclick="navGo([new navRequest('location', '%{url_location}'),
                     new navRequest('content', '%{url_content}')])">
    <s:text name="ages"/>
</s:a>
</td>
</tr>
<tr>
<td><img src="images/littleEarth.png"/></td>
<td>
<s:url id="url_content" namespace="/questionnaire" action="index_content" includeParams="none"/>
<s:url id="url_location" namespace="/questionnaire" action="index_location" includeParams="none"/>
<s:a onclick="navGo([new navRequest('content', '%{url_content}'),
                     new navRequest('location', '%{url_location}')])">
    <s:text name="questionnaire"/>
</s:a>
</td>
</tr>
</table>
