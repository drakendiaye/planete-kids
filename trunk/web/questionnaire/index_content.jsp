<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<s:iterator value="questionnaires">
    <s:url id="url_content" namespace="/questionnaire" action="questionnaire_content" includeParams="none">
        <s:param name="id"><s:property value="getId()"/></s:param>
    </s:url>
    <s:url id="url_location" namespace="/questionnaire" action="questionnaire_location" includeParams="none">
        <s:param name="id"><s:property value="getId()"/></s:param>
    </s:url>
    <s:url id="url_contextualmenu" namespace="/questionnaire" action="questionnaire_contextualmenu" includeParams="none">
        <s:param name="id"><s:property value="getId()"/></s:param>
    </s:url>
    <s:a cssClass="questionnaire" onclick="navGo([new navRequest('%{url_content}', 'content'),
                                 new navRequest('%{url_location}', 'location'),
                                 new navRequest('%{url_contextualmenu}', 'contextualmenu')])})">
        <s:div>
            <h3><s:property value="getTitle(getLocale())" /></h3>
            <p><s:property value="getDescription(getLocale())" /></p>
        </s:div>
    </s:a>
</s:iterator>