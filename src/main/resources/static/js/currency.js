$(function () {
    let rublesInUsd;
    let request;
    let code = $('#code').val();

    $.ajax({
        url: '/currencyCalculator/convert?code=' + code,
        type: 'GET',
        success: function (result) {
            rublesInUsd = result;
        }
    });

    $('#calculateButton').click(function () {
        let currency = $('#amount').val();
        let currencyAsNumber;
        // currencyAsNumber = parseInt(currency, 10)
        if (!currency || isNaN(currencyAsNumber = parseFloat(currency)) ||
            currencyAsNumber < 0) {
            $('#result').text('Произошла ошибка во время расчета');
            alert('Введенно некорректное значение, попробуйте еще раз');
            return;
        }
        $('#currencyValue').val(currencyAsNumber);
        currencyAsNumber *= rublesInUsd;
        $('#result').text('Сумма в рублях: ' + currencyAsNumber);
    });
});