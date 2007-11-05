<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Planete-kids</title>
        <s:head theme="ajax" debug="true" />
        <link href="<s:url value='/style.css'/>" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" >
            var nav_locale;
            var nav_current;
            var nav_previous = new Array();
            var nav_next = new Array();
            
            function navRequest(target, url, loading, error, form) {
                this.target = target;
                this.url = url;
                this.loading = (loading)?loading:"loading...";
                this.error = (error)?error:"error...";
                this.form = form;
                this.inner_previous = "";
                this.inner_next = "";
            }
            
            function navExec(request) {
                var bind_arg =
                {
                    url:        request.url,
                    load:       function(type, data, event) {
                                    dojo.byId(request.target).innerHTML = data;
                                    var scripts = dojo.byId(request.target).getElementsByTagName("script");
                                    for each (script in scripts) {
                                        if(script != null) window.eval(script.text);
                                    }
                                },
                    error:      function(type, errorObject) {
                                    dojo.byId(target).innerHTML = request.error;
                                    for each (test in errorObject) {
                                        dojo.byId(target).innerHTML += "<br/>"+test+"<br/>";                                    
                                    }
                                }
                }
                
                if(!nav_locale) nav_locale = "fr";
                if(bind_arg.url.indexOf("?") < 0) bind_arg.url += "?request_locale=" + nav_locale;
                else if(bind_arg.url.indexOf("request_locale") < 0) bind_arg.url += "&amp;request_locale=" + nav_locale;
                else bind_arg.url = bind_arg.url.replace(/request_locale=../i,"request_locale=" + nav_locale);
                
                if(request.form) {
                    bind_arg.formNode = dojo.byId(request.form);
                    request.form = null;
                }
                
                dojo.byId(request.target).innerHTML = request.loading;
                
                dojo.io.bind(bind_arg);
            }
            
            function navGo(requests) {
                for each(request in requests) {
                    request.inner_previous = dojo.byId(request.target).innerHTML;
                    navExec(request);
                }
                
                if(nav_current) nav_previous.push(nav_current);
                nav_current = requests;
                nav_next = new Array();
                
                dojo.byId("img_previous").src = "images/previous_enable.png";
                dojo.byId("img_next").src = "images/next_disable.png";
                if(nav_previous.length > 0) dojo.byId("img_previous").src = "images/previous_enable.png";
            }
            
            function navLocale(locale) {
                nav_locale = locale;
                var temp = new Array();
                
                for each (requests in nav_previous)
                    for each (request in requests)
                        temp[request.target] = new navRequest(request.target, request.url, request.loading, request.error, request.form);
                
                for each (var request in nav_current)
                    temp[request.target] = new navRequest(request.target, request.url, request.loading, request.error, request.form);
                
                navGo(temp);
            }
            
            function navRefresh() {
                var temp = new Array();
                
                for each (requests in nav_previous)
                    for each (request in requests)
                        temp[request.target] = new navRequest(request.target, request.url, request.loading, request.error, request.form);
                
                for each (var request in nav_current)
                    temp[request.target] = new navRequest(request.target, request.url, request.loading, request.error, request.form);
                
                for (key in temp)
                    navExec(temp[key]);
            }
            
            function navPrevious() {
                if(nav_previous.length > 0) {
                    for each(request in nav_current) {
                        request.inner_next = dojo.byId(request.target).innerHTML;
                        dojo.byId(request.target).innerHTML = request.inner_previous;
                        var scripts = dojo.byId(request.target).getElementsByTagName("script");
                        for each (script in scripts) {
                            if(script != null) window.eval(script.text);
                        }
                    }
                    
                    nav_next.push(nav_current);
                    nav_current = nav_previous.pop();
                    
                    dojo.byId("img_next").src = "images/next_enable.png";
                    if(nav_previous.length == 0) dojo.byId("img_previous").src = "images/previous_disable.png";
                }
            }

            function navNext() {
                if(nav_next.length > 0) {
                    nav_previous.push(nav_current);
                    nav_current = nav_next.pop();
                    
                    for each(request in nav_current) {
                        request.inner_previous = dojo.byId(request.target).innerHTML;
                        dojo.byId(request.target).innerHTML = request.inner_next;
                        var scripts = dojo.byId(request.target).getElementsByTagName("script");
                        for each (script in scripts) {
                            if(script != null) window.eval(script.text);
                        }
                    }
                    
                    dojo.byId("img_previous").src = "images/previous_enable.png";
                    if(nav_next.length == 0) dojo.byId("img_next").src = "images/next_disable.png";
                }
            }
        </script>
    </head>
    <body onload="navGo([new navRequest('cart', '<s:url namespace="%{getCart_namespace()}" action="%{getCart_action()}" includeParams="none"/>'),
                         new navRequest('catalogue', '<s:url namespace="%{getCatalogue_namespace()}" action="%{getCatalogue_action()}" includeParams="none"/>'),
                         new navRequest('contextualmenu', '<s:url namespace="%{getContextualmenu_namespace()}" action="%{getContextualmenu_action()}" includeParams="none"/>'),
                         new navRequest('content', '<s:url namespace="%{getContent_namespace()}" action="%{getContent_action()}" includeParams="none"/>'),
                         new navRequest('footer', '<s:url namespace="%{getFooter_namespace()}" action="%{getFooter_action()}" includeParams="none"/>'),
                         new navRequest('generalmenu', '<s:url namespace="%{getGeneralmenu_namespace()}" action="%{getGeneralmenu_action()}" includeParams="none"/>'),
                         new navRequest('header', '<s:url namespace="%{getHeader_namespace()}" action="%{getHeader_action()}" includeParams="none"/>'),
                         new navRequest('location', '<s:url namespace="%{getLocation_namespace()}" action="%{getLocation_action()}" includeParams="none"/>')]);">
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

