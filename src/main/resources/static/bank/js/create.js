$(function () {

    $('#buttonResume').click(function () {
        alert('Вызов квери 2')
        let owner = $('#inputOwner').val();
        let balance = $('#inputTransfer').val();
        $.ajax({
            url: '/bank',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: 'POST',
            // dataType: 'json',
            data: JSON.stringify({
                'owner': owner,
                'balance': 0
            }),
            success: function () {
                alert('Создание успешно!');
            },
            error: function(xhr, status, error) {
                alert(xhr.responseJSON.error.message);
            }
        });
    });


});