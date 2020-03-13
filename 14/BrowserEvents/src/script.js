
$(document).ready(function(){


    function rename() {
    var text = prompt("Type new text","new text");
    document.getElementById('div').textContent = text;
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


    hideButton.onclick = function() {
//        var t = document.getElementById('hideButton').value;
        document.getElementById('hideButton').hidden = true;
    }
//    hideButton.onclick = function() {
//        document.getElementById('hideButton').style.display = 'none';
//    }


//    for(let element of document.querySelectorAll('*')) {
//        element.addEventListener("click", ()=> alert(`Погружение: ${element.tagName}`), true);
//        element.addEventListener("click", e=> alert(`Всплытие: ${element.tagName}`));
//    }










});










