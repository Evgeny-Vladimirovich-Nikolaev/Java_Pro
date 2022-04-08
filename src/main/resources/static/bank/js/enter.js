$(function () {
    let id;
    let owner;
    let balance;

    $('#buttonInput').click(function () {
        id = $('#inputId').val();
        if (!id || isNaN(id = parseInt($('#inputId').val()) ||  id < 0)) {
            $('#result').text('Произошла ошибка во время расчета');
            alert('Введенно некорректное значение, попробуйте еще раз');
            return;
        }
        $.ajax({
            url: '/bank/enter/findBiId?id=' + 1,
            type: 'GET',
            success: function (result) {
                alert('Владелец счета ' + result.owner);
                id = result.id;
                owner = result.owner;
                balance = result.balance;
            }
        });
        $('#spanId').text('Id: ' + id);
        $('#spanOwner').text('Owner: ' + owner);
        $('#spanBalance').text('Balance ' + balance);


    });
});