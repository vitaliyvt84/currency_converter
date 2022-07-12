$(document).ready(function () {
    $("#convertData").submit(function (event) {
        event.preventDefault();
        convertation_submit();
    });
    $("#historyData").submit(function (e) {
        e.preventDefault();
        history_submit();
    });
    $("#locales").change(function () {
        var selectedOption = $('#locales').val();
        if (selectedOption != ''){
            if (document.getElementById('formName').getAttribute('value') === 'login') {
                window.location.replace('login?lang=' + selectedOption);
            } else if (document.getElementById('formName').getAttribute('value') === 'converter') {
                window.location.replace('convert?lang=' + selectedOption);
            }
        }
    });
});

function history_submit() {
    var history = {};
    history["firstCurrency"] = $('#firstCurrency').val();
    history["secondCurrency"] = $('#secondCurrency').val();
    history["date"] = $('#historyDate').val();
    $('#btn-search').prop("disabled", true);
    $.ajax({
        type:"POST",
        contentType: "application/json",
        url: "/history",
        data: JSON.stringify(history),
        dataType: 'json',
        timeout: 600000,
        success: function(data) {
            $("#historyTable tbody tr").remove();
            var tr;
            for (var i=0; i<data.length; i++) {
                tr = $('<tr/>');
                tr.append("<td>" + data[i]['firstCurrency'] + "</td>");
                tr.append("<td>" + data[i]['secondCurrency'] + "</td>");
                tr.append("<td>" + data[i]['currencyRate'] + "</td>");
                tr.append("<td>" + data[i]['sourceSum'] + "</td>");
                tr.append("<td>" + data[i]['resultSum'] + "</td>");
                tr.append("<td>" + data[i]['date'] + "</td>");
                $('#tbody').append(tr);

            }

            //$('#history').html(json);
            $('#btn-search').prop("disabled", false);
        },
        error: function (e) {
            var json = "<h4>History Response</h4>"
                + e.responseText;
            $('#history').html(json);
            $('#btn-search').prop("disabled", false);

        }
    })
}


function convertation_submit() {
    var data = {};
    data["firstCurrency"] = $('#currency1').val();
    data["secondCurrency"] = $('#currency2').val();
    data["amount"] = $("#amount").val();
    $("#btn-convert").prop("disabled", true);
    $.ajax({
        type:"POST",
        contentType: "application/json",
        url: "/convert",
        data: JSON.stringify(data),
        dataType: 'json',
        timeout: 600000,
        success: function (data) {
            var json =  JSON.stringify(data, null, 4);
            $('#result').val(data);
            console.log("SUCCESS : ", data);
            $("#btn-convert").prop("disabled", false);
        },
        error: function (e) {
            var json = "<h4>Ajax Response</h4>&lt;pre&gt;"
                + e.responseText + "&lt;/pre&gt;";
            $('#feedback').html(json);

            console.log("ERROR: ", e);
            $("#btn-convert").prop("disabled", false);
        }
    });


}