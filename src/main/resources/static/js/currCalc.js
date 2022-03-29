$(function () {
    $('#calculate').click(function (){
        let code = $('#code').val();
        let amount = $('#amount').val();
        $.ajax({
            url: '/currencyCalculator/convert?code=' + code + '&amount=' + amount,
            type: 'POST',
            success: function (result) {
                $('#result').text('Результат расчета суммы: ' + result);
            }
        });
    });
});