<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<s:div cssClass="contentHeader">
    <h2><s:text name="identify"/></h2>
</s:div>
<s:div cssClass="contentMain">
    <table class="account">
        <tr>
            <td align="center">
                <form id="identify" method="post">
                    <table>
                        <tr>
                            <td colspan="2" align="center">
                                <s:text name="please identify"/>&nbsp;:
                            </td>
                        </tr>
                        <tr>
                            <td align="right">
                                <s:text name="email"/>&nbsp;:&nbsp;
                                <script type="text/javascript">text_load('email');</script>
                                <s:hidden id="email" value="{ init : false, value : ''}"/>
                            </td>
                            <td align="left">
                                <input type="text" id="email_field" name="email" onkeyup="text_press('email')"/>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">
                                <s:text name="password"/>&nbsp;:&nbsp;
                                <script type="text/javascript">text_load('password');</script>
                                <s:hidden id="password" value="{ init : false, value : ''}"/>
                            </td>
                            <td align="left">
                                <input type="password" id="password_field" name="password" onkeyup="text_press('password')"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" align="center">
                                <s:url id="url_content" namespace="/account" action="identify_submit" includeParams="none"/>
                                <input type="submit" value="<s:text name="submit"/>" class="button" onclick="navExec(new navRequest('content', '%{url_content}', null, null, 'identify'))"/>
                            </td>
                        </tr>
                    </table>
                </form>
            </td>
            <td align="center">
                <s:text name="not subscribed"/><br/>
                <s:url id="url_create_location" namespace="/account" action="create_location" includeParams="none"/>
                <s:url id="url_create_content" namespace="/account" action="create_content" includeParams="none"/>
                <input type="button" class="button" value="<s:text name="create account"/>" onclick="navExec(new navRequest('location', '<s:property value="url_create_location"/>'));
                       navExec(new navRequest('content', '<s:property value="url_create_content"/>'));" />
            </td>
        </tr>
    </table>
</s:div>