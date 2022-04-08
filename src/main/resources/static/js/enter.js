$(function () {
    let id;
    let owner;
    let balance;

    $('#buttonInput').click(function () {
        id = $('#inputId').val();
        if (!id || isNaN(id = parseInt($('#inputId').val()) ||  id < 1)) {
            alert('Введенно некорректное значение, попробуйте еще раз');
            return;
        }
        $.ajax({
            url: '/bank/findById?id=' + id,
            type: 'GET',
            success: function (result) {
                owner = result.owner;
                balance = result.balance;
                $('#spanId').text('Id: ' + id);
                $('#spanOwner').text('Owner: ' + result.owner);
                $('#spanBalance').text('Balance: ' + result.balance);
                alert('Владелец счета ' + result.owner);

            },
            error: function () {
                alert('Такой счет не найден');
            },

        });

    });
});