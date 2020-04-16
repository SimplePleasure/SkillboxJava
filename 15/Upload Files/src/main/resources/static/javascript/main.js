$(document).ready(function(){


    picsList.onclick = function(event) {

        let target  = event.target;
        if(target.className != 'del') return;
        var toDel =target.parentElement.firstChild.innerHTML;
        $.ajax({
            method: "DELETE",
            url: '/up/' + toDel,
            success: function () {
                target.parentElement.remove();
            }
        });
    };


});


