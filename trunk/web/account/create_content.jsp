<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<h2><s:text name="create"/></h2>
<s:div cssClass="main">
    <div class="category">
        <form id="create" method="post">
            <s:hidden name="test" value="coucou"/>
            <s:url id="url_content" namespace="/account" action="create_submit" includeParams="none"/>
            <s:submit onclick="navExec(new navRequest('content', '%{url_content}', null, null, 'create'))"/>
        </form>
    </div>
</s:div>