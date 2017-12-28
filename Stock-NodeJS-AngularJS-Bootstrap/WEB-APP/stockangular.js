var Pricechart;
var BBANDSchart;
var SMAchart;
var EMAchart;
var STOCHchart;
var RSIchart;
var ADXchart;
var CCIchart;
var MACDchart;
var stockCHART;
var dataPrice=new Array();
var dataVolume=new Array();
var finaldateValue=new Array();
var dataSMA=new Array();
var dataEMA=new Array();
var dataRSI=new Array();
var dataADX=new Array();
var dataCCI=new Array();
var dataSTOCH1=new Array();
var dataSTOCH2=new Array();
var dataBBANDS1=new Array();
var dataBBANDS2=new Array();
var dataBBANDS3=new Array();
var dataMACD1=new Array();
var dataMACD2=new Array();
var dataMACD3=new Array();
var dataHistory= new Array();
var newsfeeddata=new Array();
var stockvalue;
var fronttable;
var valuetoprinterror=0;

function facebookshare(){
  var valueofactivetab=$('.nav-tabs .active').text();
  var finalcontenttoshare=valueofactivetab+"chart";
  //console.log(finalcontenttoshare);
  var object;
  if(finalcontenttoshare=="Pricechart")
  {
    object = {
      options: JSON.stringify(Pricechart),
      type: 'image/png',
      async: true
    };
    passingobject(object,Pricechart);
  }else if(finalcontenttoshare=="BBANDSchart")
  {
    object = {
      options: JSON.stringify(BBANDSchart),
      type: 'image/png',
      async: true
    };
    passingobject(object,BBANDSchart);
  }
  else if(finalcontenttoshare=="SMAchart")
  {
    object = {
      options: JSON.stringify(SMAchart),
      type: 'image/png',
      async: true
    };
    passingobject(object,SMAchart);
  }
  else if(finalcontenttoshare=="EMAchart")
  {
    object = {
      options: JSON.stringify(EMAchart),
      type: 'image/png',
      async: true
    };
    passingobject(object,EMAchart);
  }else if(finalcontenttoshare=="STOCHchart")
  {
    object = {
      options: JSON.stringify(STOCHchart),
      type: 'image/png',
      async: true
    };
    passingobject(object,STOCHchart);
  }else if(finalcontenttoshare=="RSIchart")
  {
    object = {
      options: JSON.stringify(RSIchart),
      type: 'image/png',
      async: true
    };
    passingobject(object,RSIchart);
  }else if(finalcontenttoshare=="ADXchart")
  {
    object = {
      options: JSON.stringify(ADXchart),
      type: 'image/png',
      async: true
    };
    passingobject(object,ADXchart);
  }else if(finalcontenttoshare=="CCIchart")
  {
    object = {
      options: JSON.stringify(CCIchart),
      type: 'image/png',
      async: true
    };
    passingobject(object,CCIchart);
  }else{
    object = {
      options: JSON.stringify(MACDchart),
      type: 'image/png',
      async: true
    };
    passingobject(object,MACDchart);
  }


  var urlforfb;
  function passingobject(object,chart){
    var exportUrl = 'http://export.highcharts.com/';
    $.ajax({
      type: 'post',
      url: exportUrl,
      data: object,
      success: function (data) {
        // Update "src" attribute with received image URL
        //$('#chart').attr('src', exportUrl + data);
        //console.log(exportUrl+data);
        urlforfb=exportUrl+data;
        //console.log(urlforfb);
        postToFacebook(urlforfb,chart);
      }
    });
  }

  //$.getScript('https://connect.facebook.net/en_US/sdk.js', function(){
  /*window.fbAsyncInit = function() {
    FB.init({
      appId      : '295363070868339',
      cookie     : true,
      xfbml      : true,
      version    : '2.11'
    });

    FB.AppEvents.logPageView();

  };

  (function(d, s, id){
     var js, fjs = d.getElementsByTagName(s)[0];
     if (d.getElementById(id)) {return;}
     js = d.createElement(s); js.id = id;
     js.src = "https://connect.facebook.net/en_US/sdk.js";
     fjs.parentNode.insertBefore(js, fjs);
   }(document, 'script', 'facebook-jssdk'));
   FB.getLoginStatus(function(response) {
    statusChangeCallback(response);
});
FB.getLoginStatus(function(response) {
  if (response.status === 'connected') {
    FB.ui({
      app_id:'295363070868339',
      method: 'feed',
      display: 'popup',
      picture: urlforfb
    }, (response) => {
      if (response && !response.error_message) {
      }
    });
  } else if (response.status === 'not_authorized') {
    FB.login();

  } else {
    // the user isn't logged in to Facebook.
  }
});*/
function postToFacebook(url,chart) {
    	//console.log(chart.options.exporting.url + url);
        //var url = chart.options.exporting.url + url,
			title = "Hello";
        var popup=window.open('http://www.facebook.com/sharer.php?u=' + encodeURIComponent(url) + '&t=' +
        	encodeURIComponent(title), 'sharer', 'toolbar=0,status=0,width=626,height=436');
          if(window.focus)
          popup.focus();
    }


//  });


};
function dividingthedate()
{
  var today = new Date();
  var dd = today.getDate();
  var mm = today.getMonth()+1; //January is 0!

  var yyyy = today.getFullYear();
  if(dd<10){
    dd='0'+dd;
  }
  if(mm<10){
    mm='0'+mm;
  }
  today = yyyy+'-'+mm+'-'+dd;
  today = new Date(today);
  today.setMonth(today.getMonth() - 6);
  dd = today.getDate();
  mm = today.getMonth()+1; //January is 0!
  yyyy = today.getFullYear();
  if(dd<10){
    dd='0'+dd;
  }
  if(mm<10){
    mm='0'+mm;
  }
  today = yyyy+'-'+mm+'-'+dd;
  var dateforcomparison=new Date(today);
  dateforcomparison.getTime();
  return dateforcomparison;
}
function mySMAchart()
{
  var finaldateValue1=[];
  var dataSMA1=[];
  for(var i=0;i<finaldateValue.length;i++)
  {
    //finaldateValue1[i]=finaldateValue[i];
    dataSMA1[i]=dataSMA[i];
  }
  SMAchart={
    chart: {
        zoomType: 'x',
        type:'line'
    },
    title: {
      text: 'Simple Moving Average (SMA)'
    },
    subtitle: {
      text: '<a href="https://www.alphavantage.co/" style="text-decoration:none; color:blue" id="subtitlelinear"  target="_blank">Source:Alpha Vantage</a>',
      useHTML:true
    },
    xAxis: [{
      //categories:finaldateValue1.reverse(),
      /*tickPositioner: function () {
        var positions = [],
        tick = Math.floor(this.dataMax);
        while (tick > this.dataMin) {
          positions.push(tick);
          tick -= 5;
        }
        //positions.push(this.dataMin);
        return positions.reverse();
      },*/
      /*tickPositioner: function() {
      this.tickPositions.pop();
      this.tickPositions.push(this.dataMax);
    },*/
    //startOnTick: true,
    //endOnTick: true,
    //showLastLabel: true,
      type: 'datetime',
      tickInterval: 1*24*3600*1000,
      labels:{
        enabled: true,
        align: 'center',
        format:'{value:%m/%d}',
      },
    }],
    yAxis: [{
      title: {
        text: 'SMA'
      }
    }],
    tooltip: {
      xDateFormat:'%m/%d'
    },
    plotOptions: {
      line:{
        color : '#c4392d',
        marker: {
          enabled: true,
          radius:1.5
        },
        lineWidth:1.5

      }
    },
    series: [{
      type:'line',
      data: dataSMA1.reverse(),
      name:stockvalue,
      pointWidth: 20
    }]
  };
  Highcharts.chart('2b',SMAchart);
}

function myEMAchart()
{
  var finaldateValue1=[];
  var dataEMA1=[];
  for(var i=0;i<finaldateValue.length;i++)
  {
    //finaldateValue1[i]=finaldateValue[i];
    dataEMA1[i]=dataEMA[i];
  }
  EMAchart={
    chart: {
      type: 'line',
        zoomType: 'x'
    },
    title: {
      text: 'Exponential Moving Average (EMA)'
    },
    subtitle: {
      text: '<a href="https://www.alphavantage.co/" style="text-decoration:none; color:blue" id="subtitlelinear"  target="_blank">Source:Alpha Vantage</a>',
      useHTML:true
    },
    xAxis: [{
      /*categories:finaldateValue1.reverse(),
      tickPositioner: function () {
        var positions = [],
        tick = Math.floor(this.dataMax);
        while (tick > this.dataMin) {
          positions.push(tick);
          tick -= 5;
        }
        //positions.push(this.dataMin);
        return positions.reverse();
      },*/
      type: 'datetime',
      tickInterval: 1*24*3600*1000,
      labels:{
        enabled: true,
        align: 'center',
        format:'{value:%m/%d}',
      },
    }],
    yAxis: [{
      title: {
        text: 'EMA'
      }
    }],
    tooltip: {
      xDateFormat:'%m/%d'
    },
    plotOptions: {
      line:{
        color : '#c4392d',
        marker: {
          enabled: true,
          radius:1.5
        },
        lineWidth:1.5

      }
    },
    series: [{
      type:'line',
      data: dataEMA1,
      name:stockvalue,
      pointWidth: 20
    }]
  };
  Highcharts.chart('3b',EMAchart);
}

function myRSIchart()
{
  var finaldateValue1=[];
  var dataRSI1=[];
  for(var i=0;i<finaldateValue.length;i++)
  {
    //finaldateValue1[i]=finaldateValue[i];
    dataRSI1[i]=dataRSI[i];
  }
  RSIchart={
    chart: {
      type: 'line',
        zoomType: 'x'
    },
    title: {
      text: 'Relative Strength Index (RSI)'
    },
    subtitle: {
      text: '<a href="https://www.alphavantage.co/" style="text-decoration:none; color:blue" id="subtitlelinear"  target="_blank">Source:Alpha Vantage</a>',
      useHTML:true
    },
    xAxis: [{
      /*categories:finaldateValue1.reverse(),
      tickPositioner: function () {
        var positions = [],
        tick = Math.floor(this.dataMax);
        while (tick > this.dataMin) {
          positions.push(tick);
          tick -= 5;
        }
        //positions.push(this.dataMin);
        return positions.reverse();
      },*/
      type: 'datetime',
      tickInterval: 1*24*3600*1000,
      labels:{
        enabled: true,
        align: 'center',
        format:'{value:%m/%d}',
      },
    }],
    yAxis: [{
      title: {
        text: 'RSI'
      }
    }],
    tooltip: {
      xDateFormat:'%m/%d'
    },
    plotOptions: {
      line:{
        color : '#c4392d',
        marker: {
          enabled: true,
          radius:1.5
        },
        lineWidth:1.5

      }
    },
    series: [{
      type:'line',
      data: dataRSI1.reverse(),
      name:stockvalue,
      pointWidth: 20
    }]
  };
  Highcharts.chart('5b',RSIchart);
}

function myADXchart()
{
  var finaldateValue1=[];
  var dataADX1=[];
  for(var i=0;i<finaldateValue.length;i++)
  {
    finaldateValue1[i]=finaldateValue[i];
    dataADX1[i]=dataADX[i];
  }
  ADXchart={
    chart: {
      type: 'line',
        zoomType: 'x'
    },
    title: {
      text: 'Relative Strength Index (RSI)'
    },
    subtitle: {
      text: '<a href="https://www.alphavantage.co/" style="text-decoration:none; color:blue" id="subtitlelinear"  target="_blank">Source:Alpha Vantage</a>',
      useHTML:true
    },
    xAxis: [{
      /*categories:finaldateValue1.reverse(),
      tickPositioner: function () {
        var positions = [],
        tick = Math.floor(this.dataMax);
        while (tick > this.dataMin) {
          positions.push(tick);
          tick -= 5;
        }
        //positions.push(this.dataMin);
        return positions.reverse();
      },*/
      type: 'datetime',
      tickInterval: 1*24*3600*1000,
      labels:{
        enabled: true,
        align: 'center',
        format:'{value:%m/%d}',
      },
    }],
    yAxis: [{
      title: {
        text: 'ADX'
      }
    }],
    tooltip: {
      xDateFormat:'%m/%d'
    },
    plotOptions: {
      line:{
        color : '#c4392d',
        marker: {
          enabled: true,
          radius:1.5
        },
        lineWidth:1.5

      }
    },
    series: [{
      type:'line',
      data: dataADX1,
      name:stockvalue,
      pointWidth: 20
    }]
  };
  Highcharts.chart('6b',ADXchart);
}

function myCCIchart()
{
  var finaldateValue1=[];
  var dataCCI1=[];
  for(var i=0;i<finaldateValue.length;i++)
  {
    //finaldateValue1[i]=finaldateValue[i];
    dataCCI1[i]=dataCCI[i];
  }
  CCIchart={
    chart: {
      type: 'line',
        zoomType: 'x'
    },
    title: {
      text: 'Commodity Channel Index (CCI)'
    },
    subtitle: {
      text: '<a href="https://www.alphavantage.co/" style="text-decoration:none; color:blue" id="subtitlelinear"  target="_blank">Source:Alpha Vantage</a>',
      useHTML:true
    },
    xAxis: [{
      /*categories:finaldateValue1.reverse(),
      tickPositioner: function () {
        var positions = [],
        tick = Math.floor(this.dataMax);
        while (tick > this.dataMin) {
          positions.push(tick);
          tick -= 5;
        }
        //positions.push(this.dataMin);
        return positions.reverse();
      },*/
      type: 'datetime',
      tickInterval: 1*24*3600*1000,
      labels:{
        enabled: true,
        align: 'center',
        format:'{value:%m/%d}',
      }
    }],
    yAxis: [{
      title: {
        text: 'CCI'
      }
    }],
    tooltip: {
      xDateFormat:'%m/%d'
    },
    plotOptions: {
      line:{
        color : '#c4392d',
        marker: {
          enabled: true,
          radius:1.5
        },
        lineWidth:1.5

      }
    },
    series: [{
      type:'line',
      data: dataCCI1,
      name:stockvalue,
      pointWidth: 20
    }]
  };
  Highcharts.chart('7b',CCIchart);
}

function mySTOCHchart()
{
  var finaldateValue1=[];
  var dataSTOCH11=[];
  var dataSTOCH21=[];
  for(var i=0;i<finaldateValue.length;i++)
  {
    //finaldateValue1[i]=finaldateValue[i];
    dataSTOCH11[i]=dataSTOCH1[i];
    dataSTOCH21[i]=dataSTOCH2[i];
  }
  STOCHchart={
    chart: {
        zoomType: 'x'
    },
    title: {
        text: 'Stochstic Ocillator (STOCH)'
    },
    subtitle: {
      text: '<a href="https://www.alphavantage.co/" style="text-decoration:none; color:blue" id="subtitledouble"  target="_blank">Source:Alpha Vantage</a>',
      useHTML:true
    },
    xAxis: [{
      /*categories:finaldateValue1.reverse(),
      tickPositioner: function () {
                var positions = [],
                    tick = Math.floor(this.dataMax);

                while (tick > this.dataMin) {

                    positions.push(tick);

                    tick -= 5;
                }

                //positions.push(this.dataMin);

                return positions.reverse();
            },*/
            type: 'datetime',
            tickInterval: 1*24*3600*1000,
            labels:{
              enabled: true,
              align: 'center',
              format:'{value:%m/%d}',
            },
    }],
    yAxis: [{
        title: {
            text: 'STOCH'
        }
    }],
    tooltip: {
               xDateFormat: '%m/%d'
             },
    plotOptions: {
        line: {
            dataLabels: {
                enabled: false
            },
           // enableMouseTracking: false
        },
          series: {
             marker: {
                 radius: 1.5
             },
             lineWidth: 1
         }
    },
    series: [{
        type:'line',
        data: dataSTOCH11,
        name: stockvalue+' SlowK'
    },{
      type:'line',
      data: dataSTOCH21,
      name: stockvalue+' SlowD'
    }]
};
Highcharts.chart('4b',STOCHchart);
}

function myBBANDSchart()
{
  var finaldateValue1=[];
  var dataBBANDS11=[];
  var dataBBANDS21=[];
  var dataBBANDS31=[];
  for(var i=0;i<finaldateValue.length;i++)
  {
    //finaldateValue1[i]=finaldateValue[i];
    dataBBANDS11[i]=dataBBANDS1[i];
    dataBBANDS21[i]=dataBBANDS2[i];
    dataBBANDS31[i]=dataBBANDS3[i];
  }
  var BBANDSchart={
    chart: {
        type: 'line',
          zoomType: 'x'
    },
    title: {
        text: 'Bollinger Bands (BBANDS)'
    },
    subtitle: {
      text: '<a href="https://www.alphavantage.co/" style="text-decoration:none; color:blue" id="subtitletriple"  target="_blank">Source:Alpha Vantage</a>',
      useHTML:true
    },
    xAxis: {
      /*categories:finaldateValue1.reverse(),
      tickPositioner: function () {
                var positions = [],
                    tick = Math.floor(this.dataMax);

                while (tick > this.dataMin) {

                    positions.push(tick);

                    tick -= 5;
                }

                //positions.push(this.dataMin);

                return positions.reverse();
            },*/
            tickInterval: 7*24*3600*1000,
            type: 'datetime',
            labels:{
              enabled: true,
              align: 'center',
              format:'{value:%m/%d}',
            },
    },
    yAxis: {
        title: {
            text: 'BBANDS'
        }
    },
    tooltip: {
               xDateFormat: '%m/%d'
             },
    plotOptions: {
        line: {
            dataLabels: {
                enabled: false
            },
           // enableMouseTracking: false
        },
        series: {
           marker: {
               radius: 1.5
           },
           lineWidth: 1
       }
    },
    series: [{
        type:'line',
        data: dataBBANDS11,
        name:stockvalue+' Real Middle Band'
    },{
      type:'line',
      data: dataBBANDS21,
      name:stockvalue+' Real Upper Band'
    },{
      type:'line',
      data: dataBBANDS31,
      name:stockvalue+' Real Lower Band'
    }]
};
  Highcharts.chart('8b',BBANDSchart);
}

function myMACDchart()
{
  var finaldateValue1=[];
  var dataMACD11=[];
  var dataMACD21=[];
  var dataMACD31=[];
  for(var i=0;i<finaldateValue.length;i++)
  {
    //finaldateValue1[i]=finaldateValue[i];
    dataMACD11[i]=dataMACD1[i];
    dataMACD21[i]=dataMACD2[i];
    dataMACD31[i]=dataMACD3[i];
  }
  MACDchart={
    chart: {
        type: 'line',
          zoomType: 'x'
    },
    title: {
        text: 'Moving Average Convergence/Divergence (MACD)'
    },
    subtitle: {
      text: '<a href="https://www.alphavantage.co/" style="text-decoration:none; color:blue" id="subtitletriple"  target="_blank">Source:Alpha Vantage</a>',
      useHTML:true
    },
    xAxis: {
      /*categories:finaldateValue1.reverse(),
      tickPositioner: function () {
                var positions = [],
                    tick = Math.floor(this.dataMax);

                while (tick > this.dataMin) {

                    positions.push(tick);

                    tick -= 5;
                }

                //positions.push(this.dataMin);

                return positions.reverse();
            },*/
            type: 'datetime',
            tickInterval: 1*24*3600*1000,
            labels:{
              enabled: true,
              align: 'center',
              format:'{value:%m/%d}',
            },
    },
    yAxis: {
        title: {
            text: 'MACD'
        }
    },
    tooltip: {
               xDateFormat: '%m/%d'
             },
    plotOptions: {
        line: {
            dataLabels: {
                enabled: false
            },
           // enableMouseTracking: false
        },
        series: {
           marker: {
               radius: 1.5
           },
           lineWidth: 1,
           gapSize:0
       }
    },
    series: [{
        type:'line',
        data: dataMACD11,
        name:stockvalue+' MACD_Signal'
    },{
      type:'line',
      data: dataMACD21,
      name:stockvalue+' MACD'
    },{
      type:'line',
      data: dataMACD31,
      name:stockvalue+' MACD_Hist'
    }]
};
Highcharts.chart('9b',MACDchart);
}
function myHistorychart(stockvalue){
  //var dataHistory1=[];
  //console.log("this is history data"+dataHistory);
  if ($(window).width()<768)
  {
    stockCHART={

          chart: {
              height: 400
          },

          title: {
              text: stockvalue+' Stock Value'
          },

          subtitle: {
            text: '<a href="https://www.alphavantage.co/" style="text-decoration:none; color:blue" id="subtitlechart"  target="_blank">Source:Alpha Vantage</a>',
            useHTML:true
          },
          rangeSelector: {
            inputEnabled:false,
        buttons: [
          {
          type: 'month',
          count: 1,
          text: '1m'
        }, {
          type: 'month',
          count: 3,
          text: '3m'
        }, {
          type: 'month',
          count: 6,
          text: '6m'
        },
        {
          type: 'year',
          count: 1,
          text: '1y'
        }, {
          type: 'all',
          text: 'All'
        }],
          selected: 1
  },
  tooltip: {
        useHTML: true,
        formatter: function() {
          return '<small>' + Highcharts.dateFormat('%A,%b %d,%Y',new Date(this.x)) + '</small><br> '+"<span style='color:" + this.points[0].series.color+"'>\u25CF</span> "+this.points[0].series.name+': <b>'+this.y+'</b>';
        }
      },

          series: [{
              name: stockvalue+' Stock Value',
              data: dataHistory.reverse(),
              type: 'area',
              threshold: null,
              tooltip: {
                  valueDecimals: 2,
                  shared: true
              }
          }],

          responsive: {
              rules: [{
                  condition: {
                      maxWidth: 500
                  },
                  chartOptions: {
                      chart: {
                          height: 300
                      }
                  }
              }]
          }
      };
  }
  else {
    stockCHART={

          chart: {
              height: 400
          },

          title: {
              text: stockvalue+' Stock Value'
          },

          subtitle: {
            text: '<a href="https://www.alphavantage.co/" style="text-decoration:none; color:blue" id="subtitlechart"  target="_blank">Source:Alpha Vantage</a>',
            useHTML:true
          },
          rangeSelector: {
  			buttons: [
          {
  				type: 'day',
  				count: 7,
  				text: '1w',

  			},
          {
  				type: 'month',
  				count: 1,
  				text: '1m'
  			}, {
  				type: 'month',
  				count: 3,
  				text: '3m'
  			}, {
  				type: 'month',
  				count: 6,
  				text: '6m'
  			}, {
  				type: 'ytd',
  				text: 'YTD'
  			}, {
  				type: 'year',
  				count: 1,
  				text: '1y'
  			}, {
  				type: 'all',
  				text: 'All'
  			}],
          selected: 1
  },
  tooltip: {
        useHTML: true,
        formatter: function() {
          return '<small>' + Highcharts.dateFormat('%A,%b %d,%Y',new Date(this.x)) + '</small><br> '+"<span style='color:" + this.points[0].series.color+"'>\u25CF</span> "+this.points[0].series.name+': <b>'+this.y+'</b>';
        }
      },

          series: [{
              name: stockvalue+' Stock Value',
              data: dataHistory.reverse(),
              type: 'area',
              threshold: null,
              tooltip: {
                  valueDecimals: 2,
                  shared: true
              }
          }],

          responsive: {
              rules: [{
                  condition: {
                      maxWidth: 500
                  },
                  chartOptions: {
                      chart: {
                          height: 300
                      }
                  }
              }]
          }
      };

  }

    Highcharts.stockChart('div10', stockCHART);
    document.getElementById('div9').style.display="none";
    document.getElementById('div10').style.display="block";

}
function mypricevolumechart(){
  var finaldateValue1=[];
  var dataPrice1=[];
  var dataVolume1=[];
  for(var i=0;i<finaldateValue.length;i++)
  {
    finaldateValue1[i]=finaldateValue[i];
    dataPrice1[i]=dataPrice[i];
    dataVolume1[i]=dataVolume[i];
  }
   Pricechart={
    chart: {
        zoomType: 'x'
    },
    title: {
      text: stockvalue+' Stock Price and Volume'
    },
    subtitle: {
      text: '<a href="https://www.alphavantage.co/" style="text-decoration:none; color:blue" id="subtitlechart"  target="_blank">Source:Alpha Vantage</a>',
      useHTML:true
    },
    xAxis: [{
      categories:finaldateValue1.reverse(),
      //  ordinal: true,
      tickPositioner: function () {
        if ($(window).width()<768)
        {
          var positions = [],
          tick = Math.floor(this.dataMax);

          while (tick > this.dataMin) {

            positions.push(tick);

            tick -= 10;
          }

          //positions.push(this.dataMin);

          return positions.reverse();
        }else {
          var positions = [],
          tick = Math.floor(this.dataMax);

          while (tick > this.dataMin) {

            positions.push(tick);

            tick -= 5;
          }

          //positions.push(this.dataMin);

          return positions.reverse();
        }

      },
      labels:{
        //format: '{value:%m/%d}',
        enabled: true,
        align: 'center'
      }
    }],
    yAxis: [{
      title: {
        text: 'Stock Price'
      },
      legend: {
        enabled: false
      },
       min: 0
    },{
      title: {
        text: 'volume'
      },
      legend: {
        enabled: false
      },
      opposite: true
    }],
    plotOptions: {
      area: {
        color: 'rgb(22,120,184)',
        fillOpacity: 0.5,
        marker: {
          radius: 0
        },
        lineWidth: 1,
        states: {
          hover: {
            lineWidth: 2
          }
        },
        threshold: null
      },
      column:{
        color:'red'
      }
    },
    tooltip: {
      shared: false,
      xDateFormat: '%m/%d'
    },
    series: [{
      type: 'area',
      name: stockvalue,
      data: dataPrice1.reverse(),
      tooltip:{
        pointFormat: '{series.name}: {point.y:.2f}'
      }
    },{
      type:'column',
      name:stockvalue+' Volume',
      yAxis: 1,
      //xAxis: 1,
      maxPointWidth: 5,
      data:dataVolume1.reverse()
    }]
  };
  Highcharts.chart('1b', Pricechart);
  //console.log("data after reverse");
  //console.log(JSON.parse(localStorage.getItem("stocknames")));
  var storedNames = JSON.parse(localStorage.getItem("stocknames"));
  if(storedNames.length!=0)
  {
    var j;
    var flag1="true";
    for(j in storedNames)
    {
      if(storedNames[j]==stockvalue)
      {
        flag1="false";
        break;
      }
      else {
        flag1="true";
      }
    }
    if(flag1=="false")
    {
      starcoloryellow();
    }
    else {
      starcolorwhite();
    }
  }

}
function starcoloryellow()
{
  //console.log($('.nav-tabs .active').text());
  //console.log($('.nav-tabs .active > a').attr('href'));
  //console.log("colorchangeyellow");
  document.getElementById('forstar').style.color='yellow';
}

function starcolorwhite()
{
  //console.log("colorchangewhite");
  document.getElementById('forstar').style.color='white';
}
(function(angular) {
  'use strict';
var myApp=angular.module('myApp', ["ngStorage","ngAnimate","ui.toggle"]);
myApp.controller('myCtrl', function($scope, $http, $localStorage, $interval) {
  var numberofdata;
  var flag="true";
  var storedNames=new Array();
  /*$scope.autorefresh=function(){
    console.log($scope.checkboxModel);
    if($scope.checkedornot)
    {
      console.log("it is checked");
      $scope.load1();
    }
  }
  $interval( function(){ $scope.autorefresh(); }, 3000);*/
  $scope.showanim='front';
  $scope.toogleAuto =function(checkStatus){
      //console.log("checkStatus");
      //console.log(checkStatus);
      //console.log($scope.checkboxModel);
      var interval;
      if($scope.checkboxModel)
      {
          interval = $interval(function() {
          $scope.load1();
          if(!$scope.checkboxModel)
            $interval.cancel(interval);
        }, 10000);
      }
  }
  $scope.disablerightbutton=function(){
  //console.log("right button is disabled");
  $scope.myInput="";
  $scope.right_button=true;
}
$scope.enablerightbutton=function(){
  $scope.right_button=false;
}
  $scope.save1 = function() {
    var storedNames = JSON.parse(localStorage.getItem("stocknames"));
    if(storedNames==null || storedNames.length==0)
    {
      storedNames=[];
      storedNames.push(stockvalue);
      localStorage.setItem("stocknames",JSON.stringify(storedNames));
      starcoloryellow();
    }
    else {
      var j;
      for(j in storedNames)
      {
        if(storedNames[j]==stockvalue)
        {
          flag="false";
          break;
        }
        else {
          flag="true";
        }
      }
      if(flag!="false")
      {
        storedNames.push(stockvalue);
        localStorage.setItem("stocknames",JSON.stringify(storedNames));
        starcoloryellow();
      }
      else {
        //console.log(j);
        storedNames.splice(j,1);
        //console.log(storedNames);
        localStorage.setItem("stocknames",JSON.stringify(storedNames));
        starcolorwhite();
      }
    }
  }
  $scope.againtheanswer=function(name,showanim){
    //console.log(name);
    $scope.myInput=name;
    $scope.myFunc(); $scope.myFunc1(); $scope.myFunc2(); $scope.myFunc4();
  }
  $scope.delete=function(name){
    //console.log(name);
    var flag12="true";
    var storedNames = JSON.parse(localStorage.getItem("stocknames"));
    //console.log(fronttable);
    var x;
    for(x in fronttable)
    {
      //console.log(x);
      if(fronttable[x]['stockTicker']==name)
      {
        //console.log("HELLO"+x);
        break;
      }
    }
    fronttable.splice(x,1);
    $scope.fronttabledata=fronttable;
    var z;
    for(z in storedNames)
    {
      if(storedNames[z]==name)
      {
        flag12="false";
        break;
      }
      else {
        flag12="true";
      }
    }
    if(flag12=="false")
    {

        //console.log(z);
        storedNames.splice(z,1);
        //console.log(storedNames);
        localStorage.setItem("stocknames",JSON.stringify(storedNames));
    }
}
$scope.load1 = function() {
  fronttable=new Array();
  var stockTicker1;
  var lastPrice1;
  var change_percent1;
  var change_percent;
  var color;
  var href;
  var volume;
  var change;
  var storedNames = JSON.parse(localStorage.getItem("stocknames"));
  var length=storedNames.length;
  //console.log(storedNames[0].toString());
  var storedNames1=[];
  for(var k=0;k<length;k++)
  {
    //console.log(storedNames[k]);
    storedNames1[k]=storedNames[k];
  }
  var stringof=storedNames[0];
  for(var k=0;k<length;k++)
  {
    $http.get("/stock/table/"+storedNames[k]).then(function(response) {
      try{
        var z=k;
        //console.log(k);
        stockTicker1=response.data['Meta Data']['2. Symbol'];
        var current_date=response.data['Meta Data']['3. Last Refreshed'];
        var createDate=moment(current_date).format('YYYY-MM-DD');
        lastPrice1=parseFloat(response.data['Time Series (Daily)'][createDate]['4. close']).toFixed(2);
        var open1=parseFloat(response.data['Time Series (Daily)'][createDate]['1. open']).toFixed(2);
        var someday="";
        var createDate1=createDate;
        while(someday=="")
        {
          var date= new Date(moment(createDate1));
          var createDate1= moment(new Date(date.setDate(date.getDate() - 1))).format('YYYY-MM-DD');
          try{
            someday=parseFloat(response.data['Time Series (Daily)'][createDate1]['4. close']).toFixed(2);
          }catch(e)
          {
            continue;
          }

          //console.log(createDate1);
        }
        change=parseFloat(lastPrice1-someday).toFixed(2);
        change_percent=parseFloat((change/lastPrice1)*100).toFixed(2);
        if(change<0)
        {
          //change_percent1=change+'('+change_percent+'%)';
          color="red";
          href="http://cs-server.usc.edu:45678/hw/hw6/images/Red_Arrow_Down.png";
        }
        else {
          //change_percent1=change+'('+change_percent+'%)';
          color="green";
          href="http://cs-server.usc.edu:45678/hw/hw6/images/Green_Arrow_Up.png";
        }
       volume=response.data['Time Series (Daily)'][createDate]['5. volume'];
       fronttable.push({"stockTicker":stockTicker1,"lastPrice1":parseFloat(lastPrice1),"change":change,"changePercentage":change_percent,"color":color,"hyperlink":href,"volume":parseInt(volume)});

      }catch(e){
        fronttable.push({"stockTicker":"STOCKNOTAVAILABLE","lastPrice1":"N/A","change":"N/A","changePercentage":"NA","color":"black","hyperlink":"NA","volume":0});
      }

    });
  }
    $scope.fronttabledata=fronttable;
    //console.log($scope.fronttabledata);

  }
  $scope.selectedItemChanged=function(){
    $scope.unit=$scope.sortbyitem;
    $scope.unit1=$scope.unit1;
  }
  $scope.selectedItemChanged1=function(){
    $scope.unit=$scope.sortbyitem;
    $scope.unit1=!$scope.unit1;
  }
    $scope.myFunc = function() {
      $scope.facebook_button=true;
      $scope.star_button=true;
      starcolorwhite();
      document.getElementById('div4').style.display="none";
      document.getElementById('div5').style.display="block";
      document.getElementById('div13').style.display="none";

      //console.log("hello");
    $http.get("/stock/table/"+$scope.myInput).then(function(response) {
        //console.log(response.data['Time Series (Daily)']);
        if(response.data['Error Message']!=null)
        {
            //console.log("hello123");
            document.getElementById('div5').style.display="none";
            document.getElementById('div4').style.display="none";
            document.getElementById('div13').style.display="block";
            valuetoprinterror=1;
            return 0;

        }
        else if(response.data=='{"Error Message":"error in content"}')
        {
          document.getElementById('div5').style.display="none";
          document.getElementById('div4').style.display="none";
          document.getElementById('div13').style.display="block";
          valuetoprinterror=1;
          return 0;
        }
        //console.log(response.data);
        stockvalue=$scope.myInput;
        try{
          $scope.stockTicker=response.data['Meta Data']['2. Symbol'];
        }catch(e){
          document.getElementById('div4').style.display="none";
          document.getElementById('div5').style.display="none";
          document.getElementById('div13').style.display="block";
        }
        var current_date=response.data['Meta Data']['3. Last Refreshed'];
        var stringtocomaretime=moment.tz('2017-11-08',"America/New_York").format('HH:mm:ss').toString();
        //console.log("is the value of the time"+stringtocomaretime);
        var stringtocomaretime1=moment.tz(current_date,"America/New_York").format('HH:mm:ss').toString();
        //console.log("this is the string to compare"+stringtocomaretime1);
        var monthyearday=moment.tz(current_date,"America/New_York").format('YYYY-MM-DD').toString();
        var valueofthetimezone=moment.tz(current_date,"America/New_York").format('z').toString();
        if(stringtocomaretime1==stringtocomaretime)
        {
          //console.log("the time is same and string to compare shows");
          //console.log(valueofthetimezone+"This is the value of the time zone");
          //console.log(monthyearday+"this the month year day");
          $scope.timestamp=monthyearday+" 14:00:00 "+valueofthetimezone;

        }
        else {
          $scope.timestamp=monthyearday+" "+stringtocomaretime1+" "+valueofthetimezone;
        }
        var createDate=moment(current_date).format('YYYY-MM-DD');
        //console.log(createDate);
        var lastPrice1=parseFloat(response.data['Time Series (Daily)'][createDate]['4. close']).toFixed(2);
        //console.log(lastPrice1);
        $scope.lastPrice=lastPrice1;
        var open1=parseFloat(response.data['Time Series (Daily)'][createDate]['1. open']).toFixed(2);
        $scope.open=open1;
        var someday="";
        var createDate1=createDate;
        while(someday=="")
        {
          var date= new Date(moment(createDate1));
          var createDate1= moment(new Date(date.setDate(date.getDate() - 1))).format('YYYY-MM-DD');
          try{
            someday=parseFloat(response.data['Time Series (Daily)'][createDate1]['4. close']).toFixed(2);
          }catch(e)
          {
            continue;
          }

          //console.log(createDate1);
        }


        var change=parseFloat(lastPrice1-someday).toFixed(2);
        var change_percent=parseFloat((change/lastPrice1)*100).toFixed(2);
        //console.log(change+"("+change_percent+"%)");

        if(change<0)
        {
          $scope.changePercent1=change+'('+change_percent+'%)';
          $scope.stockColor="red";
          $scope.stockImg="http://cs-server.usc.edu:45678/hw/hw6/images/Red_Arrow_Down.png";
        }
        else {
          $scope.changePercent1=change+"("+change_percent+"%)";
          $scope.stockColor="green";
          $scope.stockImg="http://cs-server.usc.edu:45678/hw/hw6/images/Green_Arrow_Up.png";
        }
        if(stringtocomaretime1==stringtocomaretime)
        {
          $scope.previousClose=lastPrice1;
        }
        else {
          $scope.previousClose=someday;
        }
        $scope.daysRange=parseFloat(response.data['Time Series (Daily)'][createDate]['3. low']).toFixed(2)+'-'+parseFloat(response.data['Time Series (Daily)'][createDate]['2. high']).toFixed(2);
        $scope.volume=response.data['Time Series (Daily)'][createDate]['5. volume'];
        document.getElementById('div4').style.display="block";
        document.getElementById('div5').style.display="none";
    });
    };
    $scope.myFunc2 = function(){
      newsfeeddata=[];
      document.getElementById('div11').style.display="block";
      document.getElementById('div12').style.display="none";
      document.getElementById('div16').style.display="none";
      $http.get("/stock/NEWS/"+$scope.myInput).then(function(response){
        var value = response.data;
        if(response.data['Error']!=null)
        {
          document.getElementById('div11').style.display="none";
          document.getElementById('div12').style.display="none";
          document.getElementById('div16').style.display="block";
        }
        //console.log("value is:"+value);
        var i=0;
        for(var plot in value)
        {
          if(i>=5)
          {
            break;
          }
          var newstitle=String(value[plot]['title']);
          var hyperlink=String(value[plot]['link']);
          var publication=String(value[plot]['pubDate']).replace(/-0400|-0500/,"");
          var valueofthetimezone=moment.tz(publication,"America/New_York").format('z').toString();
          //console.log(valueofthetimezone+"news time zone");
          publication=publication+" "+valueofthetimezone;
          var author=String(value[plot]['sa:author_name']);
          //console.log(newstitle+"   "+hyperlink+"  "+publication+"  "+author);
          //console.log(hyperlink.search("article"));
          if(hyperlink.search('article')!=-1)
          {
            newsfeeddata.push({"newstitle":newstitle,"hyperlink":hyperlink,"publication":publication,"author":author});
            i++;
          }
          //newsfeeddata.push(["{newstitle:"+value[plot].title+"},{hyperlink:"+value[plot].link+"},{publication:"+res2+"},{author:"+value[plot]['sa:author_name']+"}"]);
        }
        document.getElementById('div11').style.display="none";
        document.getElementById('div12').style.display="block";
        $scope.newsfeeddata1=newsfeeddata;
        //console.log(newsfeeddata);
      });
    };
    $scope.myFunc4 = function(){
      dataHistory=[];
      document.getElementById('div15').style.display="none";
      document.getElementById('div9').style.display="block";
      document.getElementById('div10').style.display="none";
      //console.log("7");
      $http.get("/stock/pricevolume/"+$scope.myInput).then(function(response){
        //console.log("response of History"+response);
        if(response.data=='{"Error Message":"error in content"}')
        {
          document.getElementById('div15').style.display="block";
          document.getElementById('div9').style.display="none";
          document.getElementById('div10').style.display="none";
          //console.log("error in format return 0");
          return 0;
        }
        else if(response.data['Error Message']!=undefined)
        {
          document.getElementById('div15').style.display="block";
          document.getElementById('div9').style.display="none";
          document.getElementById('div10').style.display="none";
          //console.log("error in format return 0");
          return 0;
        }
        var value = response.data['Time Series (Daily)'];
        for(var plot in value)
        {
          //console.log(String(mm+'/'+dd));
          var myDate = new Date(plot);
          dataHistory.push([myDate.getTime(),parseFloat(value[plot]['4. close'])]);
        }
        //console.log(dataHistory);
        //pricevolume();
        //newsfeed1();
        myHistorychart($scope.myInput);
      });


    }
    $scope.myFunc1 = function(){
      dataSMA=[];
      dataEMA=[];
      dataRSI=[];
      dataADX=[];
      dataCCI=[];
      dataPrice=[];
      dataVolume=[];
      dataSTOCH1=[];
      dataSTOCH2=[];
      dataBBANDS1=[];
      dataBBANDS2=[];
      dataBBANDS3=[];
      dataMACD1=[];
      dataMACD2=[];
      dataMACD3=[];
      finaldateValue=[];
      //console.log("hello1");
      document.getElementById('div7').style.display="none";
      document.getElementById('div6').style.display="block";


      document.getElementById('div14').style.display="none";

      $scope.facebook_button=true;
      $scope.star_button=true;
      $http.get("/stock/SMA/"+$scope.myInput).then(function(response){
        //console.log(response.data);
        if(response.data=='{"Error Message":"error in content"}')
        {
          document.getElementById('div14').style.display="block";
          document.getElementById('div6').style.display="none";
          document.getElementById('div7').style.display="none";
          $scope.facebook_button=true;
          $scope.star_button=true;
          //console.log("error in format return 0");
          return 0;
        }
        var value = response.data['Technical Analysis: SMA'];
        //console.log("value of the SMA data"+response);
        var dateforcomparison=dividingthedate();
        //console.log("date for SMA"+dividingthedate());
        var i=0;
        //console.log("value of lentgh"+value.length);
        for(var plot in value)
        {
          //console.log(i++);
          //console.log(String(mm+'/'+dd));
          var myDate = new Date(plot);
          if(myDate<dateforcomparison)
          {
            break;
          }
          dataSMA.push([myDate.getTime(),parseFloat(value[plot]['SMA'])]);
          //if(i==value.length)
        }
        //console.log("finish");
        EMAcall();
      });
      function EMAcall(){
        //console.log("1");
        $http.get("/stock/EMA/"+$scope.myInput).then(function(response){
          if(response.data=='{"Error Message":"error in content"}')
          {
            document.getElementById('div14').style.display="block";
            document.getElementById('div6').style.display="none";
            document.getElementById('div7').style.display="none";
            $scope.facebook_button=true;
            $scope.star_button=true;
            //console.log("error in format return 0");
            return 0;
          }
          var value = response.data['Technical Analysis: EMA'];
          var dateforcomparison=dividingthedate();
          for(var plot in value)
          {
            //console.log(String(mm+'/'+dd));
            var myDate = new Date(plot);
            if(myDate<dateforcomparison)
            {
              break;
            }
            dataEMA.push([myDate.getTime(),parseFloat(value[plot]['EMA'])]);
          }
          RSIcall();
          //pricevolume();
        });
      };
      function RSIcall(){
        //console.log("2");
        $http.get("/stock/RSI/"+$scope.myInput).then(function(response){
          if(response.data=='{"Error Message":"error in content"}')
          {
            document.getElementById('div14').style.display="block";
            document.getElementById('div6').style.display="none";
            document.getElementById('div7').style.display="none";
            $scope.facebook_button=true;
            $scope.star_button=true;
            //console.log("error in format return 0");
            return 0;
          }
          var value = response.data['Technical Analysis: RSI'];
          var dateforcomparison=dividingthedate();
          for(var plot in value)
          {
            //console.log(String(mm+'/'+dd));
            var myDate = new Date(plot);
            if(myDate<dateforcomparison)
            {
              break;
            }
            dataRSI.push([myDate.getTime(),parseFloat(value[plot]['RSI'])]);
          }
          ADXcall();
        });
      };
      function ADXcall(){
        //console.log("3");
        $http.get("/stock/ADX/"+$scope.myInput).then(function(response){
          if(response.data=='{"Error Message":"error in content"}')
          {
            document.getElementById('div14').style.display="block";
            document.getElementById('div6').style.display="none";
            document.getElementById('div7').style.display="none";
            $scope.facebook_button=true;
            $scope.star_button=true;
            //console.log("error in format return 0");
            return 0;
          }
          var value = response.data['Technical Analysis: ADX'];
          var dateforcomparison=dividingthedate();
          for(var plot in value)
          {
            //console.log(String(mm+'/'+dd));
            var myDate = new Date(plot);
            if(myDate<dateforcomparison)
            {
              break;
            }
            dataADX.push([myDate.getTime(),parseFloat(value[plot]['ADX'])]);
          }
          CCIcall();
        });
      };
      function CCIcall(){
        //console.log("4");
        $http.get("/stock/CCI/"+$scope.myInput).then(function(response){
          if(response.data=='{"Error Message":"error in content"}')
          {
            document.getElementById('div14').style.display="block";
            document.getElementById('div6').style.display="none";
            document.getElementById('div7').style.display="none";
            $scope.facebook_button=true;
            $scope.star_button=true;
            //console.log("error in format return 0");
            return 0;
          }
          var value = response.data['Technical Analysis: CCI'];
          var dateforcomparison=dividingthedate();
          for(var plot in value)
          {
            //console.log(String(mm+'/'+dd));
            var myDate = new Date(plot);
            if(myDate<dateforcomparison)
            {
              break;
            }
            dataCCI.push([myDate.getTime(),parseFloat(value[plot]['CCI'])]);
          }
          STOCHcall();
        });
      };
      function STOCHcall(){
        //console.log("5");
        $http.get("/stock/STOCH/"+$scope.myInput).then(function(response){
          if(response.data=='{"Error Message":"error in content"}')
          {
            document.getElementById('div14').style.display="block";
            document.getElementById('div6').style.display="none";
            document.getElementById('div7').style.display="none";
            $scope.facebook_button=true;
            $scope.star_button=true;
            //console.log("error in format return 0");
            return 0;
          }
          var value = response.data['Technical Analysis: STOCH'];
          var dateforcomparison=dividingthedate();
          for(var plot in value)
          {
            //console.log(String(mm+'/'+dd));
            var myDate = new Date(plot);
            if(myDate<dateforcomparison)
            {
              break;
            }
            dataSTOCH1.push([myDate.getTime(),parseFloat(value[plot]['SlowK'])]);
            dataSTOCH2.push([myDate.getTime(),parseFloat(value[plot]['SlowD'])]);
          }
          BBANDScall();
        });
      };
      function BBANDScall(){
        //console.log("6");
        $http.get("/stock/BBANDS/"+$scope.myInput).then(function(response){
          if(response.data=='{"Error Message":"error in content"}')
          {
            document.getElementById('div14').style.display="block";
            document.getElementById('div6').style.display="none";
            document.getElementById('div7').style.display="none";
            $scope.facebook_button=true;
            $scope.star_button=true;
            //console.log("error in format return 0");
            return 0;
          }
          var value = response.data['Technical Analysis: BBANDS'];
          var dateforcomparison=dividingthedate();
          for(var plot in value)
          {
            //console.log(String(mm+'/'+dd));
            var myDate = new Date(plot);
            if(myDate<dateforcomparison)
            {
              break;
            }
            dataBBANDS1.push([myDate.getTime(),parseFloat(value[plot]['Real Middle Band'])]);
            dataBBANDS2.push([myDate.getTime(),parseFloat(value[plot]['Real Upper Band'])]);
            dataBBANDS3.push([myDate.getTime(),parseFloat(value[plot]['Real Lower Band'])]);
          }
          MACDcall();
        });
      };
      function MACDcall(){
        //console.log("7");
        $http.get("/stock/MACD/"+$scope.myInput).then(function(response){
          if(response.data=='{"Error Message":"error in content"}')
          {
            document.getElementById('div14').style.display="block";
            document.getElementById('div6').style.display="none";
            document.getElementById('div7').style.display="none";
            $scope.facebook_button=true;
            $scope.star_button=true;
            //console.log("error in format return 0");
            return 0;
          }
          var value = response.data['Technical Analysis: MACD'];
          var dateforcomparison=dividingthedate();
          for(var plot in value)
          {
            //console.log(String(mm+'/'+dd));
            var myDate = new Date(plot);
            if(myDate<dateforcomparison)
            {
              break;
            }
            dataMACD1.push([myDate.getTime(),parseFloat(value[plot]['MACD_Signal'])]);
            dataMACD2.push([myDate.getTime(),parseFloat(value[plot]['MACD'])]);
            dataMACD3.push([myDate.getTime(),parseFloat(value[plot]['MACD_Hist'])]);
          }
          //HistoryChart();
          pricevolume();
        });
      };
      function HistoryChart(){
        //console.log("7");
        $http.get("/stock/pricevolume/"+$scope.myInput).then(function(response){
          if(response.data=='{"Error Message":"error in content"}')
          {
            document.getElementById('div14').style.display="block";
            document.getElementById('div6').style.display="none";
            document.getElementById('div7').style.display="none";
            $scope.facebook_button=true;
            $scope.star_button=true;
            //console.log("error in format return 0");
            return 0;
          }
          var value = response.data['Time Series (Daily)'];
          for(var plot in value)
          {
            //console.log(String(mm+'/'+dd));
            var myDate = new Date(plot);
            dataHistory.push([myDate.getTime(),parseFloat(value[plot]['4. close'])]);
          }
          //console.log(dataHistory);
          //pricevolume();
          //newsfeed1();
        });
      };

      function pricevolume(){
        //console.log("8");
        $http.get("/stock/pricevolume/"+$scope.myInput).then(function(response) {
          if(response.data['Error Message']!=null)
          {
            document.getElementById('div14').style.display="block";
            document.getElementById('div6').style.display="none";
            document.getElementById('div7').style.display="none";

            $scope.facebook_button=true;
            $scope.star_button=true;
            return 0;
          }
          var value = response.data['Time Series (Daily)'];
          var dateforcomparison=dividingthedate();
          for(var plot in value)
          {
            //console.log(String(mm+'/'+dd));
            var myDate = new Date(plot);
            if(myDate<dateforcomparison)
            {
              break;
            }
            var res=plot.split("-");
            var length1=res.length;
            var str1=res[length1-1].split(" ");
            var dd=str1[0];
            var mm=res[1];
        dataPrice.push(parseFloat(value[plot]['4. close']));
        dataVolume.push(parseFloat(value[plot]['5. volume']));
        finaldateValue.push(String(mm+'/'+dd));
      }
      //console.log("before enterning");
      $scope.facebook_button=false;
      $scope.star_button=false;
      document.getElementById('div7').style.display="block";
      document.getElementById('div6').style.display="none";
      mypricevolumechart();
      myEMAchart();
      mySMAchart();
      myRSIchart();
      myADXchart();
      myCCIchart();
      myMACDchart();
      myBBANDSchart();
      mySTOCHchart();
        });
      };
    };
});
})(window.angular);
