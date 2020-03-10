
$(document).ready(function(){





    $('#show-form').click(function() {
        $('#addContact-form').css('display', '');
    });
//      скрыть div щелчком по кнопке
//    $('#hide').click(function(event) {
//        $('#addContact-form').css('display', 'none');
//    });
    $(document).mouseup(function (e) {
        var container = $('#addContact-form');
        if (container.has(e.target).length === 0){
            container.hide();
        }
    });








            const appendContact = function(data) {
                var contact = '<h4>' + data.name + '</h4>' + 'phone: ' + data.phone;
                $('#my-div').append('<div>' + contact + '</div>');
            };


            $.get('/contact/', function(response) {
                for (i in response) {
                    appendContact(response[i]);
                }
            });


            $('#save-btn').click( function() {
                var data = $('#addContact-form form').serialize();
                $.ajax({
                    method: "POST",
                    url: '/contact/',
                    data: data,
                    success: function(response) {
                        $('#addContact-form').css('display', 'none');
                        var cont = {};
                        cont.id = response.id;
                        var dataArray = $('#addContact-form form').serializeArray();

                        for (i in dataArray) {
                            cont[dataArray[i]['name']] = dataArray[i]['value'];
                        }
                        appendContact(cont);
                    }
                });
            });

//            $('#delBtn').click(function() {
//                var name = $('#delForm').serialize();
//                console.log(name);
//                $.ajax({
//                    method: "DELETE",
//                    url: 'contact' + name,
//                    data: name,
//                    success: function() {
//                         location.reload();
//                    }
//                });
//            });







});















