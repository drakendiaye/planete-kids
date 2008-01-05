        // Variable d&eacute;terminant si l'infobulle est visible
	var isVisible = false;
 
 
	// Fonction servant &agrave; bouger l'infobulle
	function move(e) {
		if (isVisible) {
			// Pour les navigateurs autres que IE
			if (navigator.appName != "Microsoft Internet Explorer") {
				dojo.byId("infobulle").style.left = e.pageX + 5 + "px";
				dojo.byId("infobulle").style.top = e.pageY + 10 + "px";
		    }
		    // Pour IE
			else {
				if(document.documentElement.clientWidth > 0) {
					dojo.byId("infobulle").style.left = 10 + event.x + document.documentElement.scrollLeft + "px";
					dojo.byId("infobulle").style.top = 20 + event.y + document.documentElement.scrollTop + "px";
				}
				else {
					dojo.byId("infobulle").style.left = 10 + event.x + document.body.scrollLeft + "px";
					dojo.byId("infobulle").style.top = 20 + event.y + document.body.scrollTop + "px";
				}
			}
		}
	}
	
	
	// Fonction affichant l'infobulle
	function show (str) {
		if (!isVisible) {
			document.onmouseover = move;
			dojo.byId("infobulle").style.visibility = "visible";
			dojo.byId("infobulle").innerHTML = str;
			isVisible = true;
		}
	}


	// Fonction cachant l'infobulle
	function hide () {
		if (isVisible) {
			dojo.byId("infobulle").style.visibility = "hidden";
			isVisible = false;
		}
	}
	
	// D&egrave;s que la souris bouge, on r&eacute;actualise la position de l'infobulle
	document.onmousemove = move;