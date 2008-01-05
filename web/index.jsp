<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Planete-kids</title>
        <s:head theme="ajax" debug="true" />
        <link href="<s:url value='/style.css'/>" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="includes/toolkit.js"></script>
        <script type="text/javascript" src="includes/navigation.js"></script>
        <script type="text/javascript" src="includes/infobulle.js"></script>
    </head>
    <body onload="navGo([new navRequest('cart', '<s:url namespace="%{getCart_namespace()}" action="%{getCart_action()}" includeParams="none"/>'),
          new navRequest('catalogue', '<s:url namespace="%{getCatalogue_namespace()}" action="%{getCatalogue_action()}" includeParams="none"/>'),
          new navRequest('content', '<s:url namespace="%{getContent_namespace()}" action="%{getContent_action()}" includeParams="none"/>'),
          new navRequest('footer', '<s:url namespace="%{getFooter_namespace()}" action="%{getFooter_action()}" includeParams="none"/>'),
          new navRequest('generalmenu', '<s:url namespace="%{getGeneralmenu_namespace()}" action="%{getGeneralmenu_action()}" includeParams="none"/>'),
          new navRequest('header', '<s:url namespace="%{getHeader_namespace()}" action="%{getHeader_action()}" includeParams="none"/>'),
          new navRequest('location', '<s:url namespace="%{getLocation_namespace()}" action="%{getLocation_action()}" includeParams="none"/>')]);">
        <div id="infobulle" class="infobulle"></div>                             
        <s:div id="container">
        <s:div id="header" />
        <s:div id="page">
        <s:div id="leftmenu">
        <s:div id="catalogue" />
        <s:div id="generalmenu" />
        </s:div>
        <s:div id="pagecenter">
        <s:div id="topmenu">
        <s:div id="previous" >
            <s:a onclick="	navPrevious();
                 if(navHasPrevious()) {
                 dojo.byId('img_next').src = 'images/next_enable.png';
                 } else {
                 dojo.byId('img_previous').src = 'images/previous_disable.png';
                 }
                 "><img id="img_previous" src="images/previous_disable.png" /></s:a>
        </s:div>
        <s:div id="next">
            <s:a onclick="	navNext();
                 if(navHasNext()) {
                 dojo.byId('img_previous').src = 'images/previous_enable.png';
                 } else {
                 dojo.byId('img_next').src = 'images/next_disable.png';
                 }
                 "><img id="img_next" src="images/next_disable.png" /></s:a>
        </s:div>
        <s:div id="refresh">
            <s:a onclick="navRefresh(true)"><img id="refresh_img" src="images/refresh.png" /></s:a>
        </s:div>
        <s:div id="location" />
            <s:div id="frflag" >
                <s:a onclick="navLocale('fr')"><img src="images/fr.png" /></s:a>
            </s:div>
            <s:div id="enflag" >
                <s:a onclick="navLocale('en')"><img src="images/en.png" /></s:a>
            </s:div>
            <br style="clear:both;" />
        </s:div>
        <s:div id="content" />
        </s:div>
        <s:div id="rightmenu">
            <s:div id="cart" />
            </s:div>
            <br style="clear:both;" />
        </s:div>
        <s:div id="footer" />
        </s:div>
    </body>
</html>
