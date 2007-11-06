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
                
                dojo.byId("img_previous").src = "images/previous_disable.png";
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
                    
                    //dojo.byId("img_next").src = "images/next_enable.png";
                    //if(nav_previous.length == 0) dojo.byId("img_previous").src = "images/previous_disable.png";
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
                    
                    //dojo.byId("img_previous").src = "images/previous_enable.png";
                    //if(nav_next.length == 0) dojo.byId("img_next").src = "images/next_disable.png";
                }
            }
            
            
	        function navHasPrevious() {
            	return (nav_previous.length != 0);
            } 
            
            function navHasNext() {
            	return (nav_next.length != 0);
            }