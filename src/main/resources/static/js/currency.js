$(function () {
    let rublesInUsd;

    $.ajax({
        url: '/currencyCalculator/convert?code=usd',
        type: 'GET',
        success: function (result) {
            rublesInUsd = result.value;
            console.log('Текущий курс доллара в рублях ' + rublesInUsd);
        }
    });

    $('#calculateButton').click(function () {
        let currency = $('#amount').val();
        let currencyAsNumber;
        // currencyAsNumber = parseInt(currency, 10)
        if (!currency || isNaN(currencyAsNumber = parseFloat(currency)) ||
            currencyAsNumber < 0) {
            $('#result').text('Произошла ошибка во время расчета');
            alert('Введенное значение некорректно, что приводит к невозможности произвести конвертацию');
            return;
        }
        $('#currencyValue').val(currencyAsNumber);
        currencyAsNumber *= rublesInUsd;
        console.log('Результат вычисления ' + currencyAsNumber);
        $('#result').text('Результат конвертации : ' + currencyAsNumber + ' рублей');
    });
});