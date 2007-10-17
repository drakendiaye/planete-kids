<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <title>Planete-kids</title>
        <s:head theme="ajax" debug="true"/>
        <link href="<s:url value='style.css'/>" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div id="container">
            <div id="header">
                <s:include value="header.jsp" />
            </div>
            <div id="page">
                <div id="leftmenu">
                    <div id="catalogue">
                        <s:include value="catalogue.jsp" />
                    </div>
                    <div id="generalmenu">
                        <s:include value="generalmenu.jsp" />
                    </div>
                </div>
                <div id="pagecenter">
                    <div id="topmenu">
                        <div id="location">
                            <s:include value="location.jsp" />
                        </div>
                        <div id="navigation">
                            <s:include value="navigation.jsp" />
                        </div>
                        <div id="language">
                            <s:include value="language.jsp" />
                        </div>
                        <br style="clear:both;" />
                    </div>
                    <s:url id="url" action="%{getCurrent_action()}" />
                    <s:div id="content" theme="ajax" href="%{url}" loadingText="%{getText('loading')}" listenTopics="/contentrefresh" />
                </div>
                <div id="rightmenu">
                    <div id="cart">
                        <s:include value="cart.jsp" />
                    </div>
                    <div id="contextualmenu">
                        <s:include value="contextualmenu.jsp" />
                    </div>
                </div>
                <br style="clear:both;" />
            </div>
            <div id="footer">
                <s:include value="footer.jsp" />
            </div>
        </div>
    </body>
</html>

