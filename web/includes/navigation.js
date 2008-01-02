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
				    
                                    for (script in scripts) {
                                        if (scripts[script] != null && scripts[script].text != null) {
					    window.eval(scripts[script].text);
					}
				    }
                                },
                    error:      function(type, errorObject) {
                                    dojo.byId(request.target).innerHTML = request.error;
                                    for (test in errorObject) {
                                        dojo.byId(request.target).innerHTML += "<br/>"+errorObject[test]+"<br/>";                                    
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
                for (request in requests) {
                    requests[request].inner_previous = dojo.byId(requests[request].target).innerHTML;
                    navExec(requests[request]);
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
                
                for (requests in nav_previous)
                    for (request in nav_previous[requests])
                        temp[nav_previous[requests][request].target] = new navRequest(nav_previous[requests][request].target, nav_previous[requests][request].url, nav_previous[requests][request].loading, nav_previous[requests][request].error, nav_previous[requests][request].form);
                
                for (var request in nav_current)
                    temp[nav_current[request].target] = new navRequest(nav_current[request].target, nav_current[request].url, nav_current[request].loading, nav_current[request].error, nav_current[request].form);
                
                navGo(temp);
            }
            
            function navRefresh() {
                var temp = new Array();
                
                for (requests in nav_previous)
                    for (request in nav_previous[requests])
                        temp[nav_previous[requests][request].target] = new navRequest(nav_previous[requests][request].target, nav_previous[requests][request].url, nav_previous[requests][request].loading, nav_previous[requests][request].error, nav_previous[requests][request].form);
                
                for (var request in nav_current)
                    temp[nav_current[request].target] = new navRequest(nav_current[request].target, nav_current[request].url, nav_current[request].loading, nav_current[request].error, nav_current[request].form);
                
                for (key in temp)
                    navExec(temp[key]);
            }
            
            function navPrevious() {
                if(nav_previous.length > 0) {
                    for (request in nav_current) {
                        nav_current[request].inner_next = dojo.byId(nav_current[request].target).innerHTML;
                        dojo.byId(nav_current[request].target).innerHTML = nav_current[request].inner_previous;
                        var scripts = dojo.byId(nav_current[request].target).getElementsByTagName("script");
                        for (script in scripts) {
                            if(scripts[script] != null) window.eval(scripts[script].text);
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
                    
                    for (request in nav_current) {
                        nav_current[request].inner_previous = dojo.byId(nav_current[request].target).innerHTML;
                        dojo.byId(nav_current[request].target).innerHTML = nav_current[request].inner_next;
                        var scripts = dojo.byId(nav_current[request].target).getElementsByTagName("script");
                        for (script in scripts) {
                            if(scripts[script] != null) window.eval(scripts[script].text);
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