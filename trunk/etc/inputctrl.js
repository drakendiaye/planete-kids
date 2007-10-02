
function VerifyEmail(input) {
	email= input.value;
	if(email.indexOf('@',0) == -1 
	  || email.indexOf(';',0) != -1 || email.indexOf(' ',0) != -1
	  || email.indexOf('/',0) != -1 || email.indexOf(';',0) != -1 
	  || email.indexOf('<',0) != -1 || email.indexOf('>',0) != -1
	  || email.indexOf('*',0) != -1 || email.indexOf('|',0) != -1
	  || email.indexOf('`',0) != -1 || email.indexOf('&',0) != -1
	  || email.indexOf('$',0) != -1 || email.indexOf('!',0) != -1
	  || email.indexOf('"',0) != -1 || email.indexOf(':',0) != -1) { 
		alert("Bad e-mail address format: " + email + ", Please enter a valid address.");
	} 
}

function VerifySpace(input) {
	text= input.value;
	if(text.indexOf(' ',0) != -1) {
		alert("Enter a string without space character.");
		return false;
	} else {
		return true;
	}
}

function VerifyLength(input,len) {
	text= input.value;
	if(text.indexOf(' ',0) != -1) {
		alert("Enter a string.");
		return false;
	} else if(text.length < len) {
		alert("Enter at least "+len+" characters.");
		return false;
	} else {
		return true;
	}
}

function VerifyNumeric(input) {
	text= input.value;
	for( var i=0; i< text.length; i++) {
		var ch= text.substring(i,i+1);
		if (ch < "0" || ch > "9") {
         		alert("Enter a number");
			return false;
        	} 
	}
	return true;
}  

