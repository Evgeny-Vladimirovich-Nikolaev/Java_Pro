$(function () {
    $('#calculate').click(function (){
        let arg1 = $('#arg1').val();
        let arg2 = $('#arg2').val();
        $.ajax({
            url: '/currencyCalculator/convert?arg1=' + arg1 + '&arg2=' + arg2,
            type: 'POST',
            success: function (result) {
                $('#result').text('Результат расчета суммы: ' + result.value);
            }
        });
    });
});