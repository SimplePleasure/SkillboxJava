$(document).ready(function(){


    size.onmousemove = function () {
    var count = this.value;
//    document.getElementById("amount").innerHTML = count;

    if(count.length <= 3) {
        document.getElementById("amount").innerHTML = count;
    }
    	var space = 0;
    	var number = '';

    	for(var i = count.length - 1; i >= 0; i--) {
    		if(space == 3) {
    			number = ' ' + number;
    			space = 0;
    		}
    		number = count.charAt(i) + number;
    		space++;
    	}
        document.getElementById("amount").innerHTML = number;
    };

    timeChanger.onmousemove = function () {
    var period = this.value;
        if (period < 2) {
            document.getElementById("showTime").innerHTML = period + ' год';
            return;
        }
        if (period < 5)  {
            document.getElementById("showTime").innerHTML = period + ' года';
            return;
        }
        document.getElementById("showTime").innerHTML = period + ' лет';
    };





});


