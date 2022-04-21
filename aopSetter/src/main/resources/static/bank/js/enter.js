$(function () {

    $('#buttonId').click(function () {
        let id = $('#inputId').val();
        findById(id);
    });

    function findById(id) {
        if (!id || isNaN(id = parseInt($('#inputId').val()) || id < 1)) {
            alert('Введенно некорректное значение, попробуйте еще раз');
            return;
        }
        $.ajax({
            url: '/bank/accounts/' + id,
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
                clearSpans();
            },
        });

    }

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
            url: '/bank/' + id + '/deposit&transfer=' + transfer,
            type: 'PUT',
            success: function (result) {
                if (result === true) {
                    findById(id);
                    alert('Счет успешно пополнен');
                } else {
                    alert('Не удалось пополнить счет');
                }
                clearSpans();
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
            url: '/bank/' + id + '/withdraw&transfer=' + transfer,
            type: 'PUT',
            success: function (result) {
                if (result === true) {
                    findById(id);
                    alert('Требуемая сумма снятя со счета');
                } else {
                    alert('Не удалось снять деньги со счета');
                }
                clearSpans();
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
            url: '/bank/' + id,
            type: 'DELETE',
            success: function (result) {
                if (result === true) {
                    alert('Счет закрыт');
                    location.href = '/bank/start.html'
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

    function clearSpans() {
        document.getElementById('inputDeposit').value = "";
        document.getElementById('inputWithdraw').value = "";
    }

    function disableViews (availability) {
        document.getElementById('inputId').disabled = !availability;
        document.getElementById('buttonId').disabled = !availability;
        document.getElementById('buttonDeposit').disabled = availability;
        document.getElementById('buttonWithdraw').disabled = availability;
        document.getElementById('buttonClose').disabled = availability;
        document.getElementById('inputDeposit').disabled = availability;
        document.getElementById('inputWithdraw').disabled = availability;
    }
});