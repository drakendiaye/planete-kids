<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<s:url id="url_content" namespace="/" action="index_content" includeParams="none"/>
<s:url id="url_location" namespace="/" action="index_location" includeParams="none"/>
<s:url id="url_contextualmenu" namespace="/" action="index_contextualmenu" includeParams="none"/>
<s:a onclick="navGo([new navRequest('%{url_content}', 'content'),
                     new navRequest('%{url_location}', 'location'),
                     new navRequest('%{url_contextualmenu}', 'contextualmenu')])">
    Planete-Kids
</s:a>