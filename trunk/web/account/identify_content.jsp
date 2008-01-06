<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<h2><s:text name="identify"/></h2>
<s:div cssClass="main">
    <div class="category">
        <form id="identify" method="post">
            <s:text name="email"/>&nbsp;:&nbsp;
            <script type="text/javascript">text_load('email');</script>
            <s:hidden id="email" value="{ init : false, value : ''}"/>
            <s:textfield id="email_field" name="email" onkeyup="text_press('email')"/>
            <br/>
            <s:text name="password"/>&nbsp;:&nbsp;
            <script type="text/javascript">text_load('password');</script>
            <s:hidden id="password" value="{ init : false, value : ''}"/>
            <s:textfield id="password_field" name="password" onkeyup="text_press('password')"/>
            <br/>
            <s:url id="url_content" namespace="/account" action="identify_submit" includeParams="none"/>
            <s:submit onclick="navExec(new navRequest('content', '%{url_content}', null, null, 'identify'))"/>
        </form>
    </div>
</s:div>