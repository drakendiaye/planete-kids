<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div style="float : left;">
    <s:if test="getCustomer().getAccount() == null">
        <s:url id="url_content" namespace="/account" action="identify_content" includeParams="none"/>
        <s:url id="url_location" namespace="/account" action="identify_location" includeParams="none"/>
        <s:a onclick="navGo([new navRequest('content', '%{url_content}'),
             new navRequest('location', '%{url_location}')])">
            <img id="connected" src="images/radio_deselected.png" width="20px" height="20px"/>
        </s:a>
    </s:if>
    <s:else>
        <s:url id="url" namespace="/" action="index_logout" includeParams="none"/>
        <s:a onclick="navExec(new navRequest('content', '%{url}')); navPrevious();">
            <img id="connected" src="images/radio_selected.png" width="20px" height="20px"/>
        </s:a>
    </s:else>
</div>
<div style="float : left;">
    <s:url id="url_content" namespace="/" action="index_content" includeParams="none"/>
    <s:url id="url_location" namespace="/" action="index_location" includeParams="none"/>
    <s:a onclick="navGo([new navRequest('content', '%{url_content}'),
         new navRequest('location', '%{url_location}')])">
        Planete-Kids>>
    </s:a>
    
    <s:url id="url_content" namespace="/questionnaire" action="index_content" includeParams="none"/>
    <s:url id="url_location" namespace="/questionnaire" action="index_location" includeParams="none"/>
    <s:a onclick="navGo([new navRequest('content', '%{url_content}'),
         new navRequest('location', '%{url_location}')])">
        <s:text name="questionnaire"/>>>
    </s:a>
    
    <s:url id="url_content" namespace="/questionnaire" action="questionnaire_content" includeParams="none">
        <s:param name="id"><s:property value="getId()"/></s:param>
    </s:url>
    <s:url id="url_location" namespace="/questionnaire" action="questionnaire_location" includeParams="none">
        <s:param name="id"><s:property value="getId()"/></s:param>
    </s:url>
    <s:a onclick="navGo([new navRequest('content', '%{url_content}'),
         new navRequest('location', '%{url_location}')])">
        <s:text name="questionnaire"/> <s:property value="%{getId()}" />
    </s:a>
</div>
<br style="clear:both;"/>
