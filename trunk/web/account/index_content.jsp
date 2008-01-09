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
    <s:div cssClass="contentHeader">
        <h2><s:text name="account"/></h2>
    </s:div>
    <s:div cssClass="contentMain">
        Bonjour, <s:property value="getCustomer().getAccount().getFirstName()"/> !
        <br/>
        Voici les informations relatives à votre compte :<br/>
        <table width="99%" class="account">
            <tr>
                <td align="right">
                    <b><s:text name="email"/>&nbsp;:&nbsp;</b>
                </td>
                <td align="left">
                    <s:property value="getCustomer().getAccount().getEmailAddress()"/>
                </td>
            </tr>
            <tr>
                <td align="right">
                    <b><s:text name="password"/>&nbsp;:&nbsp;</b>
                </td>
                <td align="left">
                    <s:property value="getCustomer().getAccount().getPassword()"/>
                </td>
            </tr>
            <tr>
                <td align="right">
                    <b><s:text name="firstName"/>&nbsp;:&nbsp;</b>
                </td>
                <td align="left">
                    <s:property value="getCustomer().getAccount().getFirstName()"/>
                </td>
            </tr>
            <tr>
                <td align="right">
                    <b><s:text name="lastName"/>&nbsp;:&nbsp;</b>
                </td>
                <td align="left">
                    <s:property value="getCustomer().getAccount().getLastName()"/>
                </td>
            </tr>
            <tr>
                <td align="right">
                    <b><s:text name="addressLine1"/>&nbsp;:&nbsp;</b>
                </td>
                <td align="left">
                    <s:property value="getCustomer().getAccount().getAddressLine1()"/>
                </td>
            </tr>
            <tr>
                <td align="right">
                    <b><s:text name="addressLine2"/>&nbsp;:&nbsp;</b>
                </td>
                <td align="left">
                    <s:property value="getCustomer().getAccount().getAddressLine2()"/>
                </td>
            </tr>
            <tr>
                <td align="right">
                    <b><s:text name="addressLine3"/>&nbsp;:&nbsp;</b>
                </td>
                <td align="left">
                    <s:property value="getCustomer().getAccount().getAddressLine3()"/>
                </td>
            </tr>
            <tr>
                <td align="right">
                    <b><s:text name="zipCode"/>&nbsp;:&nbsp;</b>
                </td>
                <td align="left">
                    <s:property value="getCustomer().getAccount().getZipCode()"/>
                </td>
            </tr>
            <tr>
                <td align="right">
                    <b><s:text name="city"/>&nbsp;:&nbsp;</b>
                </td>
                <td align="left">
                    <s:property value="getCustomer().getAccount().getCity()"/>
                </td>
            </tr>
            <tr>
                <td align="right">
                    <b><s:text name="phoneNumber"/>&nbsp;:&nbsp;</b>
                </td>
                <td align="left">
                    <s:property value="getCustomer().getAccount().getPhoneNumber()"/>
                </td>
            </tr>
            <tr>
                <td align="right">
                    <b><s:text name="faxNumber"/>&nbsp;:&nbsp;</b>
                </td>
                <td align="left">
                    <s:property value="getCustomer().getAccount().getFaxNumber()"/>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <s:url id="url_modify_location" namespace="/account" action="modify_location" includeParams="none"/>
                    <s:url id="url_modify_content" namespace="/account" action="modify_content" includeParams="none"/>
                    <input type="button" class="button" value="<s:text name="modify account"/>" onclick="navExec(new navRequest('location', '<s:property value="url_modify_location"/>'));
                       navExec(new navRequest('content', '<s:property value="url_modify_content"/>'));" />
                </td>
            </tr>
        </table>
    </s:div>
</s:else>