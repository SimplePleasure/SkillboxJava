$(document).ready(function(){


    size.onmousemove = function () {
        var count = this.value;

        //разделение по 3 знака
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

        // рассчёт платежа
        appendPayment();
    };

    timeChanger.onmousemove = function () {
    appendPayment();
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



    const appendPayment = function() {

        var amount = document.getElementById("size").value;
        var time = document.getElementById("timeChanger").value;
        if(time == 1) {
            var pay = Math.trunc((amount/(time*12))*1.1);
            document.getElementById("payment").innerHTML = pay;
        }
        if(time == 2) {
            var pay = Math.trunc((amount/(time*12))*1.2);
            document.getElementById("payment").innerHTML = pay;
        }
        if(time == 3) {
            var pay = Math.trunc((amount/(time*12))*1.3);
            document.getElementById("payment").innerHTML = pay;
        }
        if(time == 4) {
            var pay = Math.trunc((amount/(time*12))*1.4);
            document.getElementById("payment").innerHTML = pay;
        }
        if(time == 5) {
            var pay = Math.trunc((amount/(time*12))*1.5);
            document.getElementById("payment").innerHTML = pay;
        }
    }






});


