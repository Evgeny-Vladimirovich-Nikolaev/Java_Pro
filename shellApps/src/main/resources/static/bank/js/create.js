$(function () {

    $('#buttonResume').click(function () {
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
                'balance': balance
            }),
            success: function (result) {
                alert('Счет создан успешно:');
            },
            error: function(xhr, status, error) {
                alert(xhr.responseJSON.error.message);
            },
        });
    });

});