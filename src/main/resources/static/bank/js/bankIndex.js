$(function () {


    $('#enter').click(function () {
        $.ajax({
            url: 'bank/findById?id=1',
            type: 'GET',
            success: function (result) {

            }
        });
    });
});