<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<h2><s:text name="questionnaire"/></h2>

<s:if test="questions.size > 0">
    <ul>
        <s:iterator value="questions">
            <li>
                <s:url id="url" action="index">
                    <s:param name="id"><s:property value="id"/></s:param>
                </s:url>
                <s:a href="%{url}"><s:property value="text" /></s:a>
            </li>
        </s:iterator>
    </ul>
</s:if>