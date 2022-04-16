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
                disableViews(result === null);
                if (result !== null) {
                    fillData(result.id, result.owner, result.balance);
                    alert('Владелец счета ' + result.owner);
                } else {
                    fillData("отсутствует", "", "" );
                    alert('Такой счет не найден');
                }
            },
        });
    });

    $('#buttonDeposit').click(function () {
        id = $('#inputId').val();
        transfer = $('#inputDeposit').val();
        if (!id || isNaN(id = parseInt($('#inputId').val()) || id < 1)) {
            alert('Введенно некорректное значение, попробуйте еще раз');
            return;
        }
        if (!transfer || isNaN(transfer = parseFloat($('#inputDeposit').val()) || transfer < 0)) {
            alert('Введена некорректная сумма');
            return;
        }

        $.ajax({
            url: '/bank/deposit?id=' + id + '&transfer=' + transfer,
            type: 'GET',
            success: function (result) {
                if (result === true) {
                    fillData(result.id, result.owner, result.balance);
                    alert('Счет успешно пополнен');
                } else {
                    alert('Не удалось пополнить счет');
                }
            },
        });
    });

    $('#buttonWithdraw').click(function () {
        id = $('#inputId').val();
        transfer = $('#inputWithdraw').val();
        if (!id || isNaN( id= parseInt($('#inputId').val()) || id < 1)) {
            alert('Введенно некорректное значение, попробуйте еще раз');
            return;
        }
        if (!transfer || isNaN(transfer = parseFloat($('#inputWithdraw').val()) || transfer < 0)) {
            alert('Введена некорректная сумма');
            return;
        }
        $.ajax({
            url: '/bank/withdraw?id=' + id + '&transfer=' + transfer,
            type: 'GET',
            success: function (result) {
                if (result === true) {
                    fillData(result.id, result.owner, result.balance);
                    alert('Требуемая сумма снятя со счета');
                } else {
                    alert('Не удалось снять деньги со счета');
                }
            },
        });
    });

    $('#buttonClose').click(function () {
        id = $('#inputId').val();
        if (!id || isNaN( id= parseInt($('#inputId').val()) || id < 1)) {
            alert('Введенно некорректное значение, попробуйте еще раз');
            return;
        }
        $.ajax({
            url: '/bank/closeAccount?id=' + id,
            type: 'GET',
            success: function (result) {
                if (result === true) {
                    $('#spanId').text('Номер счета: ');
                    $('#spanOwner').text('Владелец счета: ');
                    $('#spanBalance').text('Баланс: ');
                    $('#inputDeposit').disabled = true
                    $('#inputWithdraw').disabled = true
                    document.getElementById('buttonDeposit').disabled = true;
                    document.getElementById('buttonWithdraw').disabled = true;
                    document.getElementById('buttonClose').disabled = true;
                    document.getElementById('inputDeposit').disabled = true;
                    document.getElementById('inputWithdraw').disabled = true;
                    window.location.href = '/bank/start.html';
                    alert('Счет закрыт');
                } else {
                    alert('Не удалось закрыть счет');
                }
            },
        });
    });

    function fillData(id, owner, balance) {
        $('#spanId').text('Номер счета: ' + id);
        $('#spanOwner').text('Владелец счета: ' + owner);
        $('#spanBalance').text('Баланс: ' + balance);
    }

    function disableViews (availability) {
        $('#inputDeposit').disabled = true
        $('#inputWithdraw').disabled = true
        document.getElementById('buttonDeposit').disabled = availability;
        document.getElementById('buttonWithdraw').disabled = availability;
        document.getElementById('buttonClose').disabled = availability;
        document.getElementById('inputDeposit').disabled = availability;
        document.getElementById('inputWithdraw').disabled = availability;
    }
});