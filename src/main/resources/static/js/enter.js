$(function () {
    let id;
    let owner;
    let balance;
    let transfer;

    $('#buttonInput').click(function () {
        id = $('#inputId').val();
        if (!id || isNaN(id = parseInt($('#inputId').val()) || id < 1)) {
            alert('Введенно некорректное значение, попробуйте еще раз');
            return;
        }
        $.ajax({
            url: '/bank/findById?id=' + id,
            type: 'GET',
            success: function (result) {
                if (result !== null) {
                    wner = result.owner;
                    balance = result.balance;
                    $('#spanId').text('Id: ' + id);
                    $('#spanOwner').text('Owner: ' + result.owner);
                    $('#spanBalance').text('Balance: ' + result.balance);
                    $('#buttonDeposit').disabled = false;
                    $('#buttonWithdraw').disabled = true;
                    document.getElementById('buttonDeposit').disabled = false;
                    document.getElementById('buttonWithdraw').disabled = false;
                    document.getElementById('buttonClose').disabled = false;
                    document.getElementById('inputDeposit').disabled = false;
                    document.getElementById('inputWithdraw').disabled = false;
                    alert('Владелец счета ' + result.owner);
                } else {
                    $('#spanId').text('Id: ');
                    $('#spanOwner').text('Owner: ');
                    $('#spanBalance').text('Balance: ');
                    $('#inputDeposit').disabled = true
                    $('#inputWithdraw').disabled = true
                    document.getElementById('buttonDeposit').disabled = true;
                    document.getElementById('buttonWithdraw').disabled = true;
                    document.getElementById('buttonClose').disabled = true;
                    document.getElementById('inputDeposit').disabled = true;
                    document.getElementById('inputWithdraw').disabled = true;
                    alert('Такой счет не найден');
                }
            },
        });
    });

    $('#buttonDeposit').click(function () {
        id = $('#inputId').val();
        transfer = $('#inputDeposit');
        if (!id || isNaN(id = parseInt($('#inputId').val()) || id < 1)) {
            alert('Введенно некорректное значение, попробуйте еще раз');
            return;
        }
        if (!transfer || isNaN(transfer = parseFloat($('#inputDeposit').val()) || transfer < 0)) {
            alert('Введена некорректная сумма');
            return;
        }
        $.ajax({
            url: '/bank/deposit?id=' + id + 'transfer=' + transfer,
            type: 'GET',
            success: function (result) {
                if (result === true) {
                    alert('Счет успешно пополнен');
                } else {
                    alert('Не удалось пополнить счет');
                }
            },
        });
    });

    $('#buttonWithdraw').click(function () {
        id = $('#inputId').val();
        transfer = $('#inputWithdraw');
        if (!id || isNaN( id= parseInt($('#inputId').val()) || id < 1)) {
            alert('Введенно некорректное значение, попробуйте еще раз');
            return;
        }
        if (!transfer || isNaN(transfer = parseFloat($('#inputWithdraw').val()) || transfer < 0)) {
            alert('Введена некорректная сумма');
            return;
        }
        $.ajax({
            url: '/bank/withdraw?id=' + id + 'transfer=' + transfer,
            type: 'GET',
            success: function (result) {
                if (result === true) {
                    alert('Счет успешно пополнен');
                } else {
                    alert('Не удалось пополнить счет');
                }
            },
        });
    });
});