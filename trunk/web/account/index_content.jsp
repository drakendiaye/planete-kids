<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<s:if test="getCustomer().getAccount() == null">
    <form id="identify_callback" method="post">
        <s:url id="url_content" namespace="/account" action="index_content" includeParams="none"/>
        <s:url id="url_location" namespace="/account" action="index_location" includeParams="none"/>
        <s:hidden name="callback" value="navExec(new navRequest('content', '%{url_content}'));
                  navExec(new navRequest('location', '%{url_location}'));
                  dojo.byId('loginout_img').src = 'images/logout.png';"/>
    </form>
    <script type='text/javascript'>
        <s:url id="url" namespace="/account" action="identify_content" includeParams="none"/>
        navExec(new navRequest('content', '<s:property value="url"/>', null, null, 'identify_callback'));
    </script>
</s:if>
<s:else>
    <h2><s:text name="account"/></h2>
    <s:div cssClass="main">
        
    </s:div>
</s:else>