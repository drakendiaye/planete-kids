<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<s:url id="url_content" namespace="/" action="index_content" includeParams="none"/>
<s:url id="url_location" namespace="/" action="index_location" includeParams="none"/>
<s:a onclick="navGo([new navRequest('content', '%{url_content}'),
     new navRequest('location', '%{url_location}')])">
    Planete-Kids>>
</s:a>

<s:url id="url_content" namespace="/label" action="index_content" includeParams="none"/>
<s:url id="url_location" namespace="/label" action="index_location" includeParams="none"/>
<s:a onclick="navGo([new navRequest('content', '%{url_content}'),
     new navRequest('location', '%{url_location}')])">
    <s:text name="labels"/>
</s:a>
