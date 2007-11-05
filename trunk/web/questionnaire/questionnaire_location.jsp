<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<s:url id="url_content" namespace="/" action="index_content" includeParams="none"/>
<s:url id="url_location" namespace="/" action="index_location" includeParams="none"/>
<s:url id="url_contextualmenu" namespace="/" action="index_contextualmenu" includeParams="none"/>
<s:a onclick="navGo([new navRequest('content', '%{url_content}'),
                     new navRequest('location', '%{url_location}'),
                     new navRequest('contextualmenu', '%{url_contextualmenu}')])">
    Planete-Kids>>
</s:a>

<s:url id="url_content" namespace="/questionnaire" action="index_content" includeParams="none"/>
<s:url id="url_location" namespace="/questionnaire" action="index_location" includeParams="none"/>
<s:url id="url_contextualmenu" namespace="/questionnaire" action="index_contextualmenu" includeParams="none"/>
<s:a onclick="navGo([new navRequest('content', '%{url_content}'),
                     new navRequest('location', '%{url_location}'),
                     new navRequest('contextualmenu', '%{url_contextualmenu}')])">
    questionnaire>>
</s:a>

<s:url id="url_content" namespace="/questionnaire" action="questionnaire_content" includeParams="none">
    <s:param name="id"><s:property value="getId()"/></s:param>
</s:url>
<s:url id="url_location" namespace="/questionnaire" action="questionnaire_location" includeParams="none">
    <s:param name="id"><s:property value="getId()"/></s:param>
</s:url>
<s:url id="url_contextualmenu" namespace="/questionnaire" action="questionnaire_contextualmenu" includeParams="none">
    <s:param name="id"><s:property value="getId()"/></s:param>
</s:url>
<s:a onclick="navGo([new navRequest('content', '%{url_content}'),
                     new navRequest('location', '%{url_location}'),
                     new navRequest('contextualmenu', '%{url_contextualmenu}')])">
    questionnaire_<s:property value="%{getId()}" />
</s:a>