
$(document).ready(function(){





//      скрыть div щелчком по кнопке
//    $('#hide').click(function(event) {
//        $('#addContact-form').css('display', 'none');
//    });
    $('#show-form').click(function() {
        $('#addContact-form').css('display', '');
    });
    $(document).mouseup(function (event) {
        var container = $('#addContact-form');
        if (container.has(event.target).length === 0){
            container.hide();
        }
    });


    $('#showDel-form').click(function() {
            $('#delContact-form').css('display', '');
        });
    $(document).mouseup(function (event) {
        var container = $('#delContact-form');
        if (container.has(event.target).length === 0){
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
                var data = $('#addContact-form form').serialize();    //  form - обращение к форме
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



            delBtn.onclick = function() {
                var name = document.getElementById("delOnName").value;
                $.ajax({
                    method: "DELETE",
                    url: '/contact/'+name,
                    success: location.reload()
                });
            }











});















