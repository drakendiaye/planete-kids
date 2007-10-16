<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div id="frflag">
    <s:url id="url" action="index">
        <s:param name="request_locale">fr</s:param>
    </s:url>
    <s:a href="%{url}"><img src="images/fr.png" /></s:a>
</div>
<div id="enflag">
    <s:url id="url" action="index">
        <s:param name="request_locale">en</s:param>
    </s:url>
    <s:a href="%{url}"><img src="images/en.png" /></s:a>
</div>
<br style="clear:both;" />
