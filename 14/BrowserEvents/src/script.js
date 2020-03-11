
$(document).ready(function(){


    function rename() {
    var text = prompt("Type new text","new text");
    document.getElementById("div").textContent = text;
    };
    div.onclick = rename;

//    div.onclick = function() {
//        var text = prompt("Type text");
//        document.getElementById("div").textContent = text;
//
//    }



    div2.addEventListener("click", ()=> document.getElementById("div2").textContent="redacted");

    div3.onclick = function(event) {
        alert(event.clientX +" "+ event.clientY)
    }







});










