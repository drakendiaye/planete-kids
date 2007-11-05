<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<h3><s:text name="contextualmenu"/></h3>

<s:url id="url_content" namespace="/questionnaire" action="questionnaire_submit" includeParams="none"/>
<s:url id="url_location" namespace="/questionnaire" action="index_location" includeParams="none"/>
<s:url id="url_contextualmenu" namespace="/questionnaire" action="index_contextualmenu" includeParams="none"/>
<s:a onclick="navGo([new navRequest('content', '%{url_content}', null, null, 'questionnaire_form'),
                     new navRequest('location', '%{url_location}'),
                     new navRequest('contextualmenu', '%{url_contextualmenu}')])">
    <s:text name="submit"/>
</s:a>