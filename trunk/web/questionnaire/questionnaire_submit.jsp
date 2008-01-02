<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<p><s:text name="thank"/></p>

<s:url id="url_content" namespace="/questionnaire" action="index_content" includeParams="none"/>
<s:url id="url_location" namespace="/questionnaire" action="index_location" includeParams="none"/>
<s:a onclick="navGo([new navRequest('content', '%{url_content}'),
                     new navRequest('location', '%{url_location}')])">
    <s:text name="goback"/>
</s:a>