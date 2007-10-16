<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<h2><s:text name="content"/></h2>

<s:if test="questionnaires.size > 0">
    <ul>
        <s:iterator value="questionnaires">
            <li>
                <s:url id="url" action="index">
                    <s:param name="id"><s:property value="id"/></s:param>
                </s:url>
                <s:a href="%{url}"><s:property value="title" /></s:a>
            </li>
        </s:iterator>
    </ul>
</s:if>