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
        <h2><s:text name="modify"/></h2>
    </s:div>
    <s:div cssClass="contentMain">
        <form id="modify_account_form" method="post" name="modify_account_form">
            <table class="account" width="99%">
                <tr>
                    <td align="right">
                        <b><s:text name="firstName"/>&nbsp;:&nbsp;</b>
                    </td>
                    <td align="left">
                        <input type="text" name="firstName" value="<s:property value="getCustomer().getAccount().getFirstName()"/>"/>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <b><s:text name="lastName"/>&nbsp;:&nbsp;</b>
                    </td>
                    <td align="left">
                        <input type="text" name="lastName" value="<s:property value="getCustomer().getAccount().getLastName()"/>"/>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <b><s:text name="addressLine1"/>&nbsp;:&nbsp;</b>
                    </td>
                    <td align="left">
                        <input type="text" size="30" name="addressLine1" value="<s:property value="getCustomer().getAccount().getAddressLine1()"/>"/>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <b><s:text name="addressLine2"/>&nbsp;:&nbsp;</b>
                    </td>
                    <td align="left">
                        <input type="text" size="30" name="addressLine2" value="<s:property value="getCustomer().getAccount().getAddressLine2()"/>"/>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <b><s:text name="addressLine3"/>&nbsp;:&nbsp;</b>
                    </td>
                    <td align="left">
                        <input type="text" size="30" name="addressLine3" value="<s:property value="getCustomer().getAccount().getAddressLine3()"/>"/>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <b><s:text name="zipCode"/>&nbsp;:&nbsp;</b>
                    </td>
                    <td align="left">
                        <input type="text" size="5" name="zipCode" value="<s:property value="getCustomer().getAccount().getZipCode()"/>"/>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <b><s:text name="city"/>&nbsp;:&nbsp;</b>
                    </td>
                    <td align="left">
                        <input type="text" name="city" value="<s:property value="getCustomer().getAccount().getCity()"/>"/>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <b><s:text name="phoneNumber"/>&nbsp;:&nbsp;</b>
                    </td>
                    <td align="left">
                        <input type="text" size="14" name="phoneNumber" value="<s:property value="getCustomer().getAccount().getPhoneNumber()"/>"/>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <b><s:text name="faxNumber"/>&nbsp;:&nbsp;</b>
                    </td>
                    <td align="left">
                        <input type="text" size="14" name="faxNumber" value="<s:property value="getCustomer().getAccount().getFaxNumber()"/>"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <s:url id="url_content" namespace="/account" action="modify_submit" includeParams="none"/>
                        <s:url id="url_callback_content" namespace="/account" action="index_content" includeParams="none"/>
                        <s:url id="url_callback_location" namespace="/account" action="index_location" includeParams="none"/>
                        <s:hidden name="callback" value="navExec(new navRequest('content', '%{url_callback_content}'));
                                  navExec(new navRequest('location', '%{url_callback_location}'));"/>
                        <input type="submit" value="<s:text name="valid"/>" class="button" onclick="navExec(new navRequest('content', '<s:property value="url_content"/>', null, null, 'modify_account_form'))"/>
                    </td>
                </tr>
            </table>
        </form>
    </s:div>
</s:else>