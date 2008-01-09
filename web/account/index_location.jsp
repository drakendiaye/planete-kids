<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<s:if test="getCustomer().getAccount() == null">
    <script type='text/javascript'>
        <s:url id="url" namespace="/account" action="identify_location" includeParams="none"/>
        navExec(new navRequest('location', '<s:property value="url"/>'));
    </script>
</s:if>
<s:else>
    <s:url id="url_content" namespace="/" action="index_content" includeParams="none"/>
    <s:url id="url_location" namespace="/" action="index_location" includeParams="none"/>
    <s:a onclick="navGo([new navRequest('content', '%{url_content}'),
         new navRequest('location', '%{url_location}')])">
        Planete-Kids>>
    </s:a>
    
    <s:url id="url_content" namespace="/account" action="index_content" includeParams="none"/>
    <s:url id="url_location" namespace="/account" action="index_location" includeParams="none"/>
    <s:a onclick="navGo([new navRequest('content', '%{url_content}'),
         new navRequest('location', '%{url_location}')])">
        <s:text name="account"/>
    </s:a>
</s:else>