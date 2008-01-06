<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<s:if test="getCallback().size() > 0">
    <script type='text/javascript'>
            <s:iterator id="iterator" value="getCallback()">
                <s:url id="url" namespace="%{get(1)}" action="%{get(2)}" includeParams="none"/>
                navExec(new navRequest('<s:property value="get(0)"/>', '<s:property value="url"/>'));
            </s:iterator>
    </script>
</s:if>
