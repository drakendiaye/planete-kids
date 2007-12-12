<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<h2><s:text name="questionnaires"/></h2>
<s:div cssClass="main">
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
        <s:a cssClass="questionnaire" onclick="navGo([new navRequest('content', '%{url_content}'),
                                     new navRequest('location', '%{url_location}'),
                                     new navRequest('contextualmenu', '%{url_contextualmenu}')])})">
            <!--<s:div>
                <h4><s:property value="getTitle(getLocale())" /></h4>
                <p><s:property value="getDescription(getLocale())" /></p>
            </s:div>-->

        </s:a>
    </s:iterator>
</s:div>
