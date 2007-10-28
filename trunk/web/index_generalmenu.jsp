<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<h2><s:text name="generalmenu"/></h2>

<s:url id="url_content" namespace="/" action="index_content" includeParams="none"/>
<s:url id="url_location" namespace="/" action="index_location" includeParams="none"/>
<s:url id="url_contextualmenu" namespace="/" action="index_contextualmenu" includeParams="none"/>
<s:a onclick="navGo([new navRequest('%{url_content}', 'content'),
                     new navRequest('%{url_location}', 'location'),
                     new navRequest('%{url_contextualmenu}', 'contextualmenu')])">
    <s:text name="welcome"/>
</s:a>

<s:url id="url_content" namespace="/questionnaire" action="index_content" includeParams="none"/>
<s:url id="url_location" namespace="/questionnaire" action="index_location" includeParams="none"/>
<s:url id="url_contextualmenu" namespace="/questionnaire" action="index_contextualmenu" includeParams="none"/>
<s:a onclick="navGo([new navRequest('%{url_content}', 'content'),
                     new navRequest('%{url_location}', 'location'),
                     new navRequest('%{url_contextualmenu}', 'contextualmenu')])">
    <s:text name="questionnaire"/>
</s:a>
