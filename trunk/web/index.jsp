<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <title>Planete-kids</title>
        <s:head theme="ajax" debug="true" />
        <link href="<s:url value='/style.css'/>" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" >
            var locale_current;
            var content_default = new Array();
            var content_current = new Array();
            var link_current;
            var link_previous = new Array();
            var link_next = new Array();
            
            function navRequest(url, target) {
                this.url = url;
                this.target = target;
            }
            
            
            function navExecJS(data) {
                var reg = new RegExp("<script.*/script>", "gi");
                var scripts = data.replace(reg,"coucou");
                alert(scripts);
                //eval();
            }
            
            function navExec(target, url) {
                dojo.io.bind({
                    url:        url,
                    load:       function(type, data, event) {
                                    dojo.byId(target).innerHTML = data;
                                    var scripts = dojo.byId(target).getElementsByTagName("script");
                                    for each (script in scripts) {
                                        window.eval(script.text);
                                    }
                                },
                    error:      function(type, errorObject) {
                                    dojo.byId(target).innerHTML = "Error...";
                                }
                });
            }

            function navRefresh(all) {
                if(locale_current == null) locale_current = "fr";
                
                var content_tmp = new Array();
                for (key in content_default) content_tmp[key] = content_default[key];
                for each (request in link_current) {
                    if(content_default[request.target] == null) {
                        content_default[request.target] = request.url;
                    }
                    content_tmp[request.target] = request.url;
                }
                for (key in content_tmp) {
                    if(content_tmp[key].indexOf("?") < 0) content_tmp[key] += "?request_locale=" + locale_current;
                    else if(content_tmp[key].indexOf("request_locale") < 0) content_tmp[key] += "&amp;request_locale=" + locale_current;
                    else content_tmp[key] = content_tmp[key].replace(/request_locale=../i,"request_locale=" + locale_current);
                    if(all || content_tmp[key] != content_current[key]) navExec(key, content_tmp[key]);
                }
                for (key in content_tmp) content_current[key] = content_tmp[key];
                
                if(link_previous.length == 0) dojo.byId("img_previous").src = "images/previous_disable.png";
                else dojo.byId("img_previous").src = "images/previous_enable.png";
                if(link_next.length == 0) dojo.byId("img_next").src = "images/next_disable.png";
                else dojo.byId("img_next").src = "images/next_enable.png";
            }
            
            function navGo(link) {
                if(link_current != null) link_previous.push(link_current);
                link_current = link;
                link_next = new Array();
                navRefresh(false);
            }
            
            function navLocale(locale) {
                locale_current = locale;
                navRefresh();
            }

            function navPrevious() {
                if(link_previous.length > 0) {
                    link_next.push(link_current);
                    link_current = link_previous.pop();
                    navRefresh(false);
                    dojo.byId("img_next").src = "images/next_enable.png";
                    if(link_previous.length == 0) dojo.byId("img_previous").src = "images/previous_disable.png";
                }
            }

            function navNext() {
                if(link_next.length > 0) {
                    link_previous.push(link_current);
                    link_current = link_next.pop();
                    navRefresh(false);
                    dojo.byId("img_previous").src = "images/previous_enable.png";
                    if(link_next.length == 0) dojo.byId("img_next").src = "images/next_disable.png";
                }
            }
        </script>
    </head>
    <body onload="navGo([new navRequest('<s:url namespace="%{getCart_namespace()}" action="%{getCart_action()}" includeParams="none"/>', 'cart'),
                         new navRequest('<s:url namespace="%{getCatalogue_namespace()}" action="%{getCatalogue_action()}" includeParams="none"/>', 'catalogue'),
                         new navRequest('<s:url namespace="%{getContextualmenu_namespace()}" action="%{getContextualmenu_action()}" includeParams="none"/>', 'contextualmenu'),
                         new navRequest('<s:url namespace="%{getContent_namespace()}" action="%{getContent_action()}" includeParams="none"/>', 'content'),
                         new navRequest('<s:url namespace="%{getFooter_namespace()}" action="%{getFooter_action()}" includeParams="none"/>', 'footer'),
                         new navRequest('<s:url namespace="%{getGeneralmenu_namespace()}" action="%{getGeneralmenu_action()}" includeParams="none"/>', 'generalmenu'),
                         new navRequest('<s:url namespace="%{getHeader_namespace()}" action="%{getHeader_action()}" includeParams="none"/>', 'header'),
                         new navRequest('<s:url namespace="%{getLocation_namespace()}" action="%{getLocation_action()}" includeParams="none"/>', 'location')]);">
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
                            <s:a onclick="navPrevious()"><img id="img_previous" src="images/previous_disable.png" /></s:a>
                        </s:div>
                        <s:div id="next">
                            <s:a onclick="navNext()"><img id="img_next" src="images/next_disable.png" /></s:a>
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
                    <s:div id="contextualmenu" />
                </s:div>
                <br style="clear:both;" />
            </s:div>
            <s:div id="footer" />
        </s:div>
    </body>
</html>

