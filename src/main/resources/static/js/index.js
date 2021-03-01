var INTERVAL = {
    "1m": "1m",
    "3m": "3m",
    "5m": "5m",
    "15m": "15m",
    "30m": "30m",
    "1h": "1h",
    "2h": "2h",
    "4h": "4h",
    "6h": "6h",
    "8h": "8h",
    "12h": "12h",
    "1d": "1d",
    "3d": "3d",
    "1w": "1w",
    "1M": "1M",
}

window.addEventListener('load', function() {
    // show candlestick data button
    $('#getCandlestickDataBtn').click(function(){
        getCandlestickData(drawChart);
    })

    // set interval options
    $.each(INTERVAL, function (i, element) { 
        $('#intervalOptions').append($('<option>').html(element).val(element));         
    });

    // set symbol option
    $.get("/symbol", {
        datatype: "json",
    }).done(function(result){
        var json = JSON.parse(result.exchangeInfoData);
        $.each(json.symbols, function (i, element) {
            $('#symbolOptions').append($('<option>').html(element.symbol).val(element.symbol));         
        });
    });

});


function getCandlestickData(callback){
    $.post("/chart", {
        symbol: $('#symbolOptions').val(), 
        interval: $('#intervalOptions').val(),
        datatype: "json",
    }).done(function(result){
        var json = JSON.parse(result.klineData);
        callback(json);
    });
}

function drawChart(klineData){
    var dps1 = [], dps2= [];
    var stockChart = new CanvasJS.StockChart("chart",{
        theme: "light2",
        exportEnabled: true,
        title:{
            text: $('#symbolOptions').val()
        },
        charts: [{
        axisX: {
            crosshair: {
            enabled: true,
            snapToDataPoint: true
            }
        },
        data: [{
            type: "candlestick",
            yValueFormatString: "$#,###.########",
            dataPoints : dps1
        }]
        }],
        navigator: {
        data: [{
            dataPoints: dps2
        }],
        slider: {
            minimum: new Date(2021, 01, 28),
            maximum: new Date(2021, 02, 28)
        }
        }
    });

    $.each(klineData, function (i, element) { 
        dps1.push({
            x: new Date(element[0]),
            y: [
                Number(element[1]),
                Number(element[2]),
                Number(element[3]),
                Number(element[4]),
            ],
        });
        dps2.push({
            x: new Date(element[0]),
            y: Number(element[4]),
        });
    });
    console.log(dps1);
    console.log(dps2);
    stockChart.render();
}
