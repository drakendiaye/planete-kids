<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<p>Merci de nous avoir accorder du temp.</p>

<s:url id="url_content" namespace="/questionnaire" action="index_content" includeParams="none"/>
<s:url id="url_location" namespace="/questionnaire" action="index_location" includeParams="none"/>
<s:url id="url_contextualmenu" namespace="/questionnaire" action="index_contextualmenu" includeParams="none"/>
<s:a onclick="navGo([new navRequest('%{url_content}', 'content'),
                     new navRequest('%{url_location}', 'location'),
                     new navRequest('%{url_contextualmenu}', 'contextualmenu')])">
    <s:text name="goback"/>
</s:a>