<HTML>
<head>
<style>
#div1 {
  border-radius: 5px;
  width: 360px;
  background-color: #f2f2f2;
  padding: 10px;
  padding-bottom: 60px;
  margin: auto;
  border:1px solid rgb(226,226,226);

}
button:focus {outline:0;}
button:hover{color:black;}
#div2{
  background-color: rgb(226,226,226);
  padding: 1px;
}
#div3{
  margin: auto;
  padding-top: 10px;
  padding-bottom: 10px;
}
#lable1_font{
  font-size: 25px;
  font-style: italic;
}
table,td {
  border: 2px solid rgb(200,200,200);
  border-collapse: collapse;
}
td:nth-child(odd)
{
  background-color: rgb(242,242,242);
  text-align: left;
}
td:nth-child(even)
{
  background-color: rgb(251,251,251);
  text-align: center;
}
a:hover
{
  color:black;
}
#hello1:hover{
  color:green;
}
</style>
<script>
function clearContent()
{
  document.getElementById('stocksname').value="";
  document.getElementById('div3').innerHTML="";
  document.getElementById('container').innerHTML="";
  document.getElementById('div4').innerHTML="";
}

</script>
<script>

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
function callforSMAgraph()
{
  if(document.getElementById('stocksname')!="")
  {
    console.log("in the callforgraph");
    var xmlhttp;
    if (window.XMLHttpRequest) {
      // code for IE7+, Firefox, Chrome, Opera, Safari
      xmlhttp=new XMLHttpRequest(); }
      else {
        // code for IE6, IE5
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP"); }
        var stockvalue= document.getElementById('stocksname').value;
        console.log(stockvalue);
        var filename = 'https://www.alphavantage.co/query?function=SMA&symbol='+stockvalue+'&interval=daily&time_period=10&series_type=close&apikey=TBYMG7F0TDJ73HP6';
        xmlhttp.open("GET", filename, true);
        xmlhttp.onreadystatechange = function() {
          console.log(this.readyState);
          if (this.readyState == 4 && this.status == 200) {
            var myArr = JSON.parse(this.responseText);
            //console.log(myArr);
            //console.log(myArr['Meta Data']['3. Last Refreshed']);
            console.log(myArr);
            gettheSMAvalue(myArr,stockvalue);
          }

        };
        xmlhttp.send();
      }
}

function gettheSMAvalue(arr,stockvalue)
{
  var value = arr['Technical Analysis: SMA'];
  //console.log(value);
  var finaldata = new Array();
  var finalvolume = new Array();
  //console.log("the 1st value"+new Date(value[0]));
  ////////////fetch todays date and then subtract
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
  /// now removing 6 months from todays date

  var today = yyyy+'-'+mm+'-'+dd;
  today = new Date(today);
  today.setMonth(today.getMonth() - 6);
  //console.log("date"+today);

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
  console.log("today's date"+today);
  var dateforcomparison=new Date(today);
  dateforcomparison.getTime();
  console.log("Time to Compare",+dateforcomparison);
  /*
  for(var plot in value)
  {
    console.log(plot);
    //if()
    var myDate = new Date(plot);
    if(myDate<dateforcomparison)
    {
      break;
    }
    //console.log(myDate.getTime());
    dd = myDate.getDate();
    mm = myDate.getMonth()+1; //January is 0!
    //yyyy = today.getFullYear();
    if(dd<10){
        dd='0'+dd;
    }
    if(mm<10){
        mm='0'+mm;
    }
    //today = mm+'/'+dd;
    finaldata.push(parseFloat(value[plot]['EMA']));
    finalvolume.push(String(mm+'/'+dd));
  }
  */
  for(var plot in value)
  {
    console.log(plot);
    //if()
    var myDate = new Date(plot);
    if(myDate<dateforcomparison)
    {
      break;
    }
    //console.log(myDate.getTime());
    var res=plot.split("-");
    var length1=res.length;
    var str1=res[length1-1].split(" ");
    var dd=str1[0];
    var mm=res[1];
    //console.log(myDate.getTime());
    /*dd = myDate.getDate()+1;
    mm = myDate.getMonth()+1; //January is 0!
    //yyyy = today.getFullYear();
    if(dd<10){
        dd='0'+dd;
    }
    if(mm<10){
        mm='0'+mm;
    }
    //today = mm+'/'+dd;*/
    finaldata.push(parseFloat(value[plot]['SMA']));
    finalvolume.push(String(mm+'/'+dd));
  }
  console.log(finaldata);
  printLinearchart(finaldata,finalvolume,stockvalue,"Simple Moving Average (SMA)","SMA");
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////EMA/////////////////////////////////////////////////////////////////////////////////////////////EMA////////////////EMA
function callforEMAgraph()
{
  if(document.getElementById('stocksname')!="")
  {
    console.log("in the callforgraph");
    var xmlhttp;
    if (window.XMLHttpRequest) {
      // code for IE7+, Firefox, Chrome, Opera, Safari
      xmlhttp=new XMLHttpRequest(); }
      else {
        // code for IE6, IE5
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP"); }
        var stockvalue= document.getElementById('stocksname').value;
        console.log(stockvalue);
        var filename = 'https://www.alphavantage.co/query?function=EMA&symbol='+stockvalue+'&interval=daily&time_period=10&series_type=close&apikey=TBYMG7F0TDJ73HP6';
        xmlhttp.open("GET", filename, true);
        xmlhttp.onreadystatechange = function() {
          console.log(this.readyState);
          if (this.readyState == 4 && this.status == 200) {
            var myArr = JSON.parse(this.responseText);
            //console.log(myArr);
            //console.log(myArr['Meta Data']['3. Last Refreshed']);
            console.log(myArr);
            gettheEMAvalue(myArr,stockvalue);
          }

        };
        xmlhttp.send();
      }
}

function gettheEMAvalue(arr,stockvalue)
{
  var value = arr['Technical Analysis: EMA'];
  //console.log(value);
  var finaldata = new Array();
  var finalvolume = new Array();
  //console.log("the 1st value"+new Date(value[0]));
  ////////////fetch todays date and then subtract
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
  /// now removing 6 months from todays date

  var today = yyyy+'-'+mm+'-'+dd;
  today = new Date(today);
  today.setMonth(today.getMonth() - 6);
  //console.log("date"+today);

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
  console.log("today's date"+today);
  var dateforcomparison=new Date(today);
  dateforcomparison.getTime();
  console.log("Time to Compare",+dateforcomparison);

  for(var plot in value)
  {
    console.log(plot);
    //if()
    var myDate = new Date(plot);
    console.log(myDate.toLocaleDateString());
    if(myDate<dateforcomparison)
    {
      break;
    }
    var res=plot.split("-");
    var length1=res.length;
    var str1=res[length1-1].split(" ");
    var dd=str1[0];
    var mm=res[1];
    //console.log(myDate.getTime());
    /*dd = myDate.getDate()+1;
    mm = myDate.getMonth()+1; //January is 0!
    //yyyy = today.getFullYear();
    if(dd<10){
        dd='0'+dd;
    }
    if(mm<10){
        mm='0'+mm;
    }
    //today = mm+'/'+dd;*/
    finaldata.push(parseFloat(value[plot]['EMA']));
    finalvolume.push(String(mm+'/'+dd));
  }
  console.log(finaldata);
  printLinearchart(finaldata,finalvolume,stockvalue,"Exponential Moving Average (EMA)","EMA");
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////RSI/////////////////////////////////////////////////////////////////////////////////////////////RSI////////////////RSI
function callforRSIgraph()
{
  if(document.getElementById('stocksname')!="")
  {
    console.log("in the callforgraph");
    var xmlhttp;
    if (window.XMLHttpRequest) {
      // code for IE7+, Firefox, Chrome, Opera, Safari
      xmlhttp=new XMLHttpRequest(); }
      else {
        // code for IE6, IE5
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP"); }
        var stockvalue= document.getElementById('stocksname').value;
        console.log(stockvalue);
        var filename = 'https://www.alphavantage.co/query?function=RSI&symbol='+stockvalue+'&interval=daily&time_period=10&series_type=close&apikey=TBYMG7F0TDJ73HP6';
        xmlhttp.open("GET", filename, true);
        xmlhttp.onreadystatechange = function() {
          console.log(this.readyState);
          if (this.readyState == 4 && this.status == 200) {
            var myArr = JSON.parse(this.responseText);
            //console.log(myArr);
            //console.log(myArr['Meta Data']['3. Last Refreshed']);
            console.log(myArr);
            gettheRSIvalue(myArr,stockvalue);
          }

        };
        xmlhttp.send();
      }
}

function gettheRSIvalue(arr,stockvalue)
{
  var value = arr['Technical Analysis: RSI'];
  //console.log(value);
  var finaldata = new Array();
  var finalvolume = new Array();
  //console.log("the 1st value"+new Date(value[0]));
  ////////////fetch todays date and then subtract
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
  /// now removing 6 months from todays date

  var today = yyyy+'-'+mm+'-'+dd;
  today = new Date(today);
  today.setMonth(today.getMonth() - 6);
  //console.log("date"+today);

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
  console.log("today's date"+today);
  var dateforcomparison=new Date(today);
  dateforcomparison.getTime();
  console.log("Time to Compare",+dateforcomparison);
  /*
  for(var plot in value)
  {
    console.log(plot);
    //if()
    var myDate = new Date(plot);
    if(myDate<dateforcomparison)
    {
      break;
    }
    //console.log(myDate.getTime());
    dd = myDate.getDate();
    mm = myDate.getMonth()+1; //January is 0!
    //yyyy = today.getFullYear();
    if(dd<10){
        dd='0'+dd;
    }
    if(mm<10){
        mm='0'+mm;
    }
    //today = mm+'/'+dd;
    finaldata.push(parseFloat(value[plot]['EMA']));
    finalvolume.push(String(mm+'/'+dd));
  }
  */
  for(var plot in value)
  {
    console.log(plot);
    //if()
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
    //console.log(myDate.getTime());
    /*dd = myDate.getDate()+1;
    mm = myDate.getMonth()+1; //January is 0!
    //yyyy = today.getFullYear();
    if(dd<10){
        dd='0'+dd;
    }
    if(mm<10){
        mm='0'+mm;
    }
    //today = mm+'/'+dd;*/
    finaldata.push(parseFloat(value[plot]['RSI']));
    finalvolume.push(String(mm+'/'+dd));
  }
  console.log(finaldata);
  printLinearchart(finaldata,finalvolume,stockvalue,"Relative Strength Index (RSI)","RSI");
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////ADX/////////////////////////////////////////////////////////////////////////////////////////////ADX////////////////ADX
function callforADXgraph()
{
  if(document.getElementById('stocksname')!="")
  {
    console.log("in the callforgraph");
    var xmlhttp;
    if (window.XMLHttpRequest) {
      // code for IE7+, Firefox, Chrome, Opera, Safari
      xmlhttp=new XMLHttpRequest(); }
      else {
        // code for IE6, IE5
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP"); }
        var stockvalue= document.getElementById('stocksname').value;
        console.log(stockvalue);
        var filename = 'https://www.alphavantage.co/query?function=ADX&symbol='+stockvalue+'&interval=daily&time_period=10&series_type=close&apikey=TBYMG7F0TDJ73HP6';
        xmlhttp.open("GET", filename, true);
        xmlhttp.onreadystatechange = function() {
          console.log(this.readyState);
          if (this.readyState == 4 && this.status == 200) {
            var myArr = JSON.parse(this.responseText);
            //console.log(myArr);
            //console.log(myArr['Meta Data']['3. Last Refreshed']);
            console.log(myArr);
            gettheADXvalue(myArr,stockvalue);
          }

        };
        xmlhttp.send();
      }
}

function gettheADXvalue(arr,stockvalue)
{
  var value = arr['Technical Analysis: ADX'];
  //console.log(value);
  var finaldata = new Array();
  var finalvolume = new Array();
  //console.log("the 1st value"+new Date(value[0]));
  ////////////fetch todays date and then subtract
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
  /// now removing 6 months from todays date

  var today = yyyy+'-'+mm+'-'+dd;
  today = new Date(today);
  today.setMonth(today.getMonth() - 6);
  //console.log("date"+today);

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
  console.log("today's date"+today);
  var dateforcomparison=new Date(today);
  dateforcomparison.getTime();
  console.log("Time to Compare",+dateforcomparison);
  /*
  for(var plot in value)
  {
    console.log(plot);
    //if()
    var myDate = new Date(plot);
    if(myDate<dateforcomparison)
    {
      break;
    }
    //console.log(myDate.getTime());
    dd = myDate.getDate();
    mm = myDate.getMonth()+1; //January is 0!
    //yyyy = today.getFullYear();
    if(dd<10){
        dd='0'+dd;
    }
    if(mm<10){
        mm='0'+mm;
    }
    //today = mm+'/'+dd;
    finaldata.push(parseFloat(value[plot]['EMA']));
    finalvolume.push(String(mm+'/'+dd));
  }
  */
  for(var plot in value)
  {
    console.log(plot);
    //if()
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
    //console.log(myDate.getTime());
    /*dd = myDate.getDate()+1;
    mm = myDate.getMonth()+1; //January is 0!
    //yyyy = today.getFullYear();
    if(dd<10){
        dd='0'+dd;
    }
    if(mm<10){
        mm='0'+mm;
    }
    //today = mm+'/'+dd;*/
    finaldata.push(parseFloat(value[plot]['ADX']));
    finalvolume.push(String(mm+'/'+dd));
  }
  console.log(finaldata);
  printLinearchart(finaldata,finalvolume,stockvalue,"Average Directional movement indeX (ADX)","ADX");
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////CCI/////////////////////////////////////////////////////////////////////////////////////////////CCI////////////////CCI
function callforCCIgraph()
{
  if(document.getElementById('stocksname')!="")
  {
    console.log("in the callforgraph");
    var xmlhttp;
    if (window.XMLHttpRequest) {
      // code for IE7+, Firefox, Chrome, Opera, Safari
      xmlhttp=new XMLHttpRequest(); }
      else {
        // code for IE6, IE5
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP"); }
        var stockvalue= document.getElementById('stocksname').value;
        console.log(stockvalue);
        var filename = 'https://www.alphavantage.co/query?function=CCI&symbol='+stockvalue+'&interval=daily&time_period=10&series_type=close&apikey=TBYMG7F0TDJ73HP6';
        xmlhttp.open("GET", filename, true);
        xmlhttp.onreadystatechange = function() {
          console.log(this.readyState);
          if (this.readyState == 4 && this.status == 200) {
            var myArr = JSON.parse(this.responseText);
            //console.log(myArr);
            //console.log(myArr['Meta Data']['3. Last Refreshed']);
            console.log(myArr);
            gettheCCIvalue(myArr,stockvalue);
          }

        };
        xmlhttp.send();
      }
}

function gettheCCIvalue(arr,stockvalue)
{
  var value = arr['Technical Analysis: CCI'];
  //console.log(value);
  var finaldata = new Array();
  var finalvolume = new Array();
  //console.log("the 1st value"+new Date(value[0]));
  ////////////fetch todays date and then subtract
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
  /// now removing 6 months from todays date

  var today = yyyy+'-'+mm+'-'+dd;
  today = new Date(today);
  today.setMonth(today.getMonth() - 6);
  //console.log("date"+today);

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
  console.log("today's date"+today);
  var dateforcomparison=new Date(today);
  dateforcomparison.getTime();
  console.log("Time to Compare",+dateforcomparison);
  /*
  for(var plot in value)
  {
    console.log(plot);
    //if()
    var myDate = new Date(plot);
    if(myDate<dateforcomparison)
    {
      break;
    }
    //console.log(myDate.getTime());
    dd = myDate.getDate();
    mm = myDate.getMonth()+1; //January is 0!
    //yyyy = today.getFullYear();
    if(dd<10){
        dd='0'+dd;
    }
    if(mm<10){
        mm='0'+mm;
    }
    //today = mm+'/'+dd;
    finaldata.push(parseFloat(value[plot]['EMA']));
    finalvolume.push(String(mm+'/'+dd));
  }
  */
  for(var plot in value)
  {
    console.log(plot);
    //if()
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
    //console.log(myDate.getTime());
    /*dd = myDate.getDate()+1;
    mm = myDate.getMonth()+1; //January is 0!
    //yyyy = today.getFullYear();
    if(dd<10){
        dd='0'+dd;
    }
    if(mm<10){
        mm='0'+mm;
    }
    //today = mm+'/'+dd;*/
    finaldata.push(parseFloat(value[plot]['CCI']));
    finalvolume.push(String(mm+'/'+dd));
  }
  console.log(finaldata);
  printLinearchart(finaldata,finalvolume,stockvalue,"Commodity Channel Index (CCI)","CCI");
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
function printLinearchart(finaldata,finalvolume,stockvalue,titlevalue,yaxisvalue)
{
  console.log("finnal data is"+finalvolume);
  Highcharts.chart('container', {
    chart: {
        type: 'line',
        borderColor: '#d2d2d2',
        borderWidth: 2
    },
    title: {
        text: titlevalue
    },
    subtitle: {
        text: '<a href="https://www.alphavantage.co/" style="text-decoration:none; color:blue" id="subtitlelinear" onmouseover="snext(this.id)" onmouseout="snext1(this.id)" target="_blank">Source:Alpha Vantage</a>',
        useHTML:true
    },
    xAxis: [{
            categories:finalvolume.reverse(),
            tickPositioner: function () {
                      var positions = [],
                          tick = Math.floor(this.dataMax);

                      while (tick > this.dataMin) {

                          positions.push(tick);

                          tick -= 5;
                      }

                      positions.push(this.dataMin);

                      return positions.reverse();
                  },
              labels:{
                //format: '{value:%m/%d}',
                enabled: true,
                align: 'center'
              }
           }],
    yAxis: [{
        title: {
            text: yaxisvalue
        }
    }],
    legend: {
    enabled: true,
    align: 'right',
    borderColor: 'black',
    borderWidth: 0,
    layout: 'vertical',
    verticalAlign: 'top',
    y: 100
},
tooltip: {
           xDateFormat:'%m/%d' //function(){return Highcharts.dateFormat('%m/%d',new Date(this.x));}
          //formatter: function() {
          //      return  Highcharts.dateFormat('%m/%d',new Date(this.x));
       },
    plotOptions: {
               line:{
                  color : '#c4392d',
                  marker: {
                     enabled: true,
                     radius:2
                  },
                     lineWidth:1.5

               }
            },
    series: [{
        type:'line',
        data: finaldata.reverse(),
        name:stockvalue,
        pointWidth: 20
        //pointStart:finalvolume[0]
    }]
});
}
////////////////////STOCH/////////////////////////////////////////////////////////////////////////////////////////////STOCH////////////////STOCH
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////MACD/////////////////////////////////////////////////////////////////////////////////////////////MACD////////////////MACD
function callforMACDgraph()
{
  if(document.getElementById('stocksname')!="")
  {
    console.log("in the callforgraph");
    var xmlhttp;
    if (window.XMLHttpRequest) {
      // code for IE7+, Firefox, Chrome, Opera, Safari
      xmlhttp=new XMLHttpRequest(); }
      else {
        // code for IE6, IE5
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP"); }
        var stockvalue= document.getElementById('stocksname').value;
        console.log(stockvalue);
        var filename = 'https://www.alphavantage.co/query?function=MACD&symbol='+stockvalue+'&interval=daily&time_period=10&series_type=close&apikey=TBYMG7F0TDJ73HP6';
        xmlhttp.open("GET", filename, true);
        xmlhttp.onreadystatechange = function() {
          console.log(this.readyState);
          if (this.readyState == 4 && this.status == 200) {
            var myArr = JSON.parse(this.responseText);
            //console.log(myArr);
            //console.log(myArr['Meta Data']['3. Last Refreshed']);
            console.log(myArr);
            gettheMACDvalue(myArr,stockvalue);
          }

        };
        xmlhttp.send();
      }
}

function gettheMACDvalue(arr,stockvalue)
{
  var value = arr['Technical Analysis: MACD'];
  //console.log(value);
  var finaldata = new Array();
  var finalsignal = new Array();
  var finalmacd = new Array();
  var finaldatevalue=new Array();
  //console.log("the 1st value"+new Date(value[0]));
  ////////////fetch todays date and then subtract
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
  /// now removing 6 months from todays date

  var today = yyyy+'-'+mm+'-'+dd;
  today = new Date(today);
  today.setMonth(today.getMonth() - 6);
  //console.log("date"+today);

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
  console.log("today's date"+today);
  var dateforcomparison=new Date(today);
  dateforcomparison.getTime();
  console.log("Time to Compare",+dateforcomparison);
  /*
  for(var plot in value)
  {
    console.log(plot);
    //if()
    var myDate = new Date(plot);
    if(myDate<dateforcomparison)
    {
      break;
    }
    //console.log(myDate.getTime());
    dd = myDate.getDate();
    mm = myDate.getMonth()+1; //January is 0!
    //yyyy = today.getFullYear();
    if(dd<10){
        dd='0'+dd;
    }
    if(mm<10){
        mm='0'+mm;
    }
    //today = mm+'/'+dd;
    finaldata.push(parseFloat(value[plot]['EMA']));
    finalvolume.push(String(mm+'/'+dd));
  }
  */
  for(var plot in value)
  {
    console.log(plot);
    //if()
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
    //console.log(myDate.getTime());
    /*dd = myDate.getDate()+1;
    mm = myDate.getMonth()+1; //January is 0!
    //yyyy = today.getFullYear();
    if(dd<10){
        dd='0'+dd;
    }
    if(mm<10){
        mm='0'+mm;
    }
    //today = mm+'/'+dd;*/
    finaldata.push(parseFloat(value[plot]['MACD_Hist']));
    finalsignal.push(parseFloat(value[plot]['MACD_Signal']));
    finalmacd.push(parseFloat(value[plot]['MACD']));
    finaldatevalue.push(String(mm+'/'+dd));
  }
  console.log(finalmacd);
  printTripleLinear1chart(finaldata,finalsignal,finalmacd,finaldatevalue,stockvalue,"Moving Average Convergence/Divergence (MACD)","MACD");
}

function printTripleLinear1chart(finaldata,finalsignal,finalmacd,finaldatevalue,stockvalue,titlevalue,yaxisvalue)
{
  Highcharts.chart('container', {
    chart: {
        type: 'line',
        borderColor: '#d2d2d2',
        borderWidth: 2
    },
    title: {
        text: titlevalue
    },
    subtitle: {
      text: '<a href="https://www.alphavantage.co/" style="text-decoration:none; color:blue" id="subtitletriple" onmouseover="snext(this.id)" onmouseout="snext1(this.id)" target="_blank">Source:Alpha Vantage</a>',
      useHTML:true
    },
    xAxis: {
      categories:finaldatevalue.reverse(),
      tickPositioner: function () {
                var positions = [],
                    tick = Math.floor(this.dataMax);

                while (tick > this.dataMin) {

                    positions.push(tick);

                    tick -= 5;
                }

                positions.push(this.dataMin);

                return positions.reverse();
            },
      labels:{
        //format: '{value:%d/%m}',
        enabled: true,
        align: 'center'
      }
      //ordinal: true
    },
    yAxis: {
        title: {
            text: yaxisvalue
        }
    },
    tooltip: {
               xDateFormat: '%m/%d'
             },
    legend: {
    enabled: true,
    align: 'right',
    borderColor: 'black',
    borderWidth: 0,
    layout: 'vertical',
    verticalAlign: 'top',
    y: 100
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
        data: finaldata.reverse(),
        name:stockvalue+' MACD_Hist'
    },{
      type:'line',
      data: finalsignal.reverse(),
      name:stockvalue+' MACD_Signal'
    },{
      type:'line',
      data: finalmacd.reverse(),
      name:stockvalue+' MACD'
    }]
});
}

function callforSTOCHgraph()
{
  if(document.getElementById('stocksname')!="")
  {
    console.log("in the callforgraph");
    var xmlhttp;
    if (window.XMLHttpRequest) {
      // code for IE7+, Firefox, Chrome, Opera, Safari
      xmlhttp=new XMLHttpRequest(); }
      else {
        // code for IE6, IE5
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP"); }
        var stockvalue= document.getElementById('stocksname').value;
        console.log(stockvalue);
        var filename = 'https://www.alphavantage.co/query?function=STOCH&symbol='+stockvalue+'&interval=daily&slowkmatype=1&slowdmatype=1&apikey=TBYMG7F0TDJ73HP6';
        xmlhttp.open("GET", filename, true);
        xmlhttp.onreadystatechange = function() {
          console.log(this.readyState);
          if (this.readyState == 4 && this.status == 200) {
            var myArr = JSON.parse(this.responseText);
            //console.log(myArr);
            //console.log(myArr['Meta Data']['3. Last Refreshed']);
            console.log(myArr);
            gettheSTOCHvalue(myArr,stockvalue);
          }

        };
        xmlhttp.send();
      }
}

function gettheSTOCHvalue(arr,stockvalue)
{
  var value = arr['Technical Analysis: STOCH'];
  //console.log(value);
  var finaldata = new Array();
  var finalvolume = new Array();
  var finaldatevalue=new Array();
  //console.log("the 1st value"+new Date(value[0]));
  ////////////fetch todays date and then subtract
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
  /// now removing 6 months from todays date

  var today = yyyy+'-'+mm+'-'+dd;
  today = new Date(today);
  today.setMonth(today.getMonth() - 6);
  //console.log("date"+today);

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
  console.log("today's date"+today);
  var dateforcomparison=new Date(today);
  dateforcomparison.getTime();
  console.log("Time to Compare",+dateforcomparison);
  /*
  for(var plot in value)
  {
    console.log(plot);
    //if()
    var myDate = new Date(plot);
    if(myDate<dateforcomparison)
    {
      break;
    }
    //console.log(myDate.getTime());
    dd = myDate.getDate()+1;
    mm = myDate.getMonth()+1; //January is 0!
    //yyyy = today.getFullYear();
    if(dd<10){
        dd='0'+dd;
    }
    if(mm<10){
        mm='0'+mm;
    }
    //today = mm+'/'+dd;
    finaldata.push(parseFloat(value[plot]['EMA']));
    finalvolume.push(String(mm+'/'+dd));
  }
  */
  for(var plot in value)
  {
    console.log(plot);
    //if()
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
    console.log(String(mm+'/'+dd));
    //console.log(myDate.getTime());
    /*dd = myDate.getDate()+1;
    mm = myDate.getMonth()+1; //January is 0!
    //yyyy = today.getFullYear();
    if(dd<10){
        dd='0'+dd;
    }
    if(mm<10){
        mm='0'+mm;
    }
    //today = mm+'/'+dd;*/
    finaldata.push(parseFloat(value[plot]['SlowK']));
    finalvolume.push(parseFloat(value[plot]['SlowD']));
    finaldatevalue.push(String(mm+'/'+dd));
  }
  console.log(finaldata);
  printDoubleLinearchart(finaldata,finalvolume,finaldatevalue,stockvalue,"Stochastic Ocillator (STOCH)","STOCH");
}
function printDoubleLinearchart(finaldata,finalvolume,finaldatevalue,stockvalue,titlevalue,yaxisvalue)
{
  Highcharts.chart('container', {
    chart: {
        type: 'line',
        borderColor: '#d2d2d2',
        borderWidth: 2
    },
    title: {
        text: titlevalue
    },
    subtitle: {
      text: '<a href="https://www.alphavantage.co/" style="text-decoration:none; color:blue" id="subtitledouble" onmouseover="snext(this.id)" onmouseout="snext1(this.id)" target="_blank">Source:Alpha Vantage</a>',
      useHTML:true
    },
    xAxis: [{
      categories:finaldatevalue.reverse(),
      tickPositioner: function () {
                var positions = [],
                    tick = Math.floor(this.dataMax);

                while (tick > this.dataMin) {

                    positions.push(tick);

                    tick -= 5;
                }

                positions.push(this.dataMin);

                return positions.reverse();
            },
      labels:{
        //format: '{value:%d/%m}',
        enabled:true,
        align: 'center'
      },
    }],
    yAxis: [{
        title: {
            text: yaxisvalue
        }
    }],
    tooltip: {
               xDateFormat: '%m/%d'
             },
    legend: {
    enabled: true,
    align: 'right',
    borderColor: 'black',
    borderWidth: 0,
    layout: 'vertical',
    verticalAlign: 'top',
    y: 100
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
                 radius: 2
             },
             lineWidth: 1
         }
    },
    series: [{
        type:'line',
        data: finaldata.reverse(),
        name: stockvalue+' SlowK'
    },{
      type:'line',
      data: finalvolume.reverse(),
      name: stockvalue+' SlowD'
    }]
});
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////BBANDS/////////////////////////////////////////////////////////////////////////////////////////////BBANDS////////////////BBANDS
function callforBBANDSgraph()
{
  if(document.getElementById('stocksname')!="")
  {
    console.log("in the callforgraph");
    var xmlhttp;
    if (window.XMLHttpRequest) {
      // code for IE7+, Firefox, Chrome, Opera, Safari
      xmlhttp=new XMLHttpRequest(); }
      else {
        // code for IE6, IE5
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP"); }
        var stockvalue= document.getElementById('stocksname').value;
        console.log(stockvalue);
        var filename = 'https://www.alphavantage.co/query?function=BBANDS&symbol='+stockvalue+'&interval=daily&time_period=5&series_type=close&nbdevup=3&nbdevdn=3&apikey=TBYMG7F0TDJ73HP6';
        xmlhttp.open("GET", filename, true);
        xmlhttp.onreadystatechange = function() {
          console.log(this.readyState);
          if (this.readyState == 4 && this.status == 200) {
            var myArr = JSON.parse(this.responseText);
            //console.log(myArr);
            //console.log(myArr['Meta Data']['3. Last Refreshed']);
            console.log(myArr);
            gettheBBANDSvalue(myArr,stockvalue);
          }

        };
        xmlhttp.send();
      }
}

function gettheBBANDSvalue(arr,stockvalue)
{
  var value = arr['Technical Analysis: BBANDS'];
  //console.log(value);
  var finaldata = new Array();
  var finalvolume = new Array();
  var finalband= new Array();
  var finaldatevalue=new Array();
  //console.log("the 1st value"+new Date(value[0]));
  ////////////fetch todays date and then subtract
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
  /// now removing 6 months from todays date

  today = yyyy+'-'+mm+'-'+dd;
  today = new Date(today);
  today.setMonth(today.getMonth() - 6);
  //console.log("date"+today);

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
  console.log("today's date"+today);
  var dateforcomparison=new Date(today);
  dateforcomparison.getTime();
  console.log("Time to Compare",+dateforcomparison);
  /*
  for(var plot in value)
  {
    console.log(plot);
    //if()
    var myDate = new Date(plot);
    if(myDate<dateforcomparison)
    {
      break;
    }
    //console.log(myDate.getTime());
    dd = myDate.getDate()+1;
    mm = myDate.getMonth()+1; //January is 0!
    //yyyy = today.getFullYear();
    if(dd<10){
        dd='0'+dd;
    }
    if(mm<10){
        mm='0'+mm;
    }
    //today = mm+'/'+dd;
    finaldata.push(parseFloat(value[plot]['EMA']));
    finalvolume.push(String(mm+'/'+dd));
  }
  */
  for(var plot in value)
  {
    console.log(plot);
    //if()

    console.log(String(mm+'/'+dd));
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
    //console.log(myDate.getTime());
    /*dd = myDate.getDate()+1;
    mm = myDate.getMonth()+1; //January is 0!
    //yyyy = today.getFullYear();
    if(dd<10){
        dd='0'+dd;
    }
    if(mm<10){
        mm='0'+mm;
    }
    //today = mm+'/'+dd;*/
    finaldata.push(parseFloat(value[plot]['Real Middle Band']));
    finalvolume.push(parseFloat(value[plot]['Real Lower Band']));
    finalband.push(parseFloat(value[plot]['Real Upper Band']));
    finaldatevalue.push(String(mm+'/'+dd));
  }
  console.log(finaldata);
  printTripleLinearchart(finaldata,finalvolume,finalband,finaldatevalue,stockvalue,"Bollinger Bands (BBANDS)","BBANDS");
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
function printTripleLinearchart(finaldata,finalvolume,finalband,finaldatevalue,stockvalue,titlevalue,yaxisvalue)
{
  Highcharts.chart('container', {
    chart: {
        type: 'line',
        borderColor: '#d2d2d2',
        borderWidth: 2
    },
    title: {
        text: titlevalue
    },
    subtitle: {
      text: '<a href="https://www.alphavantage.co/" style="text-decoration:none; color:blue" id="subtitletriple" onmouseover="snext(this.id)" onmouseout="snext1(this.id)" target="_blank">Source:Alpha Vantage</a>',
      useHTML:true
    },
    xAxis: {
      categories:finaldatevalue.reverse(),
      tickPositioner: function () {
                var positions = [],
                    tick = Math.floor(this.dataMax);

                while (tick > this.dataMin) {

                    positions.push(tick);

                    tick -= 5;
                }

                positions.push(this.dataMin);

                return positions.reverse();
            },
      labels:{
        //format: '{value:%d/%m}',
        align: 'center',
        enabled:true
      }
    },
    yAxis: {
        title: {
            text: yaxisvalue
        }
    },
    tooltip: {
               xDateFormat: '%m/%d'
             },
    legend: {
    enabled: true,
    align: 'right',
    borderColor: 'black',
    borderWidth: 0,
    layout: 'vertical',
    verticalAlign: 'top',
    y: 100
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
               radius: 2
           },
           lineWidth: 1
       }
    },
    series: [{
        type:'line',
        data: finaldata.reverse(),
        name:stockvalue+' Real Middle Band'
    },{
      type:'line',
      data: finalvolume.reverse(),
      name:stockvalue+' Real Lower Band'
    },{
      type:'line',
      data: finalband.reverse(),
      name:stockvalue+' Real Upper Band'
    }]
});
}






    </script>
  </head>
  <body>
    <?php
    error_reporting(0);
    date_default_timezone_set('America/New_York');
    //error_reporting(E_ERROR | E_WARNING | E_PARSE);
    $stock_name="";
    static $value_in_table="";?>
    <div id="div1" align="center">
      <form method="post" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>">
        <label id="lable1_font">Stock Search</label>
        <div id="div2"></div>
        <label>Enter Stock Ticket Symbol:* </label> <input type="text" name="stock_name" id="stocksname" style="margin-top: 10px;" value="<?php echo isset($_POST['stock_name'])? $_POST['stock_name'] : '';?>"/><br>
        <input type="submit" name="submit" value="Search" style="margin-left: 160px;"/><input type="button" name="reset" value="Clear" onClick="clearContent()" style="margin-left: 2px;margin-top: 4px;"/><br>
        <label style="font-style:italic; float:left">*- Mandatory fields</label>
      </form>
    </div>

    <?php
    $API="TBYMG7F0TDJ73HP6";
    if($_SERVER["REQUEST_METHOD"] == "POST")
    {
      if(empty($_POST["stock_name"]))
      {
        echo "<script>alert('stock name cannot be left empty');</script>";
      }
      else {
        $stock_name= $_POST["stock_name"];
        $json = file_get_contents('https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol='.$stock_name.'&apikey=TBYMG7F0TDJ73HP6');
        //echo($json);
        $data = json_decode($json, TRUE);
        //echo $data['Error Message'];
        if($data['Error Message']=="Invalid API call. Please retry or visit the documentation (https://www.alphavantage.co/documentation/) for TIME_SERIES_DAILY.")
        {
          echo '<div align="center" id="div3"><table><col width="300"><col width="600"><tr><td>Error</td><td>Error:NO record has been found, please neter a valid symbol</td></tr>';
          //echo "<script>alert('Enter the correct stock name');</script>";
          return 0;
        }
        $symbol = $data['Meta Data']['2. Symbol'];
        $current_date1=$data['Meta Data']['3. Last Refreshed'];
        $createDate = new DateTime($current_date1);
        //echo $createDate;
        $current_date = $createDate->format('Y-m-d');
        //echo $current_date;
        $close_current=$data['Time Series (Daily)'][$current_date]['4. close'];
        $open_current=$data['Time Series (Daily)'][$current_date]['1. open'];
        //echo $open_current;
        $date = new DateTime($current_date);
        ////////////////////////////////////////////////////////
        $finaldata1 = array();
        $finalvolume1 = array();
        $finaldatevalue=array();
        $value = $data['Time Series (Daily)'];
        //echo $value;
        foreach($value as $key=>$plot)
        {
          //$time= strtotime($key)*1000;
          $res=explode("-", $key);
          $length1=sizeof($res);
          $str1=explode(" ", $res[$length1-1]);
          //print_r ($res);
          $dd=$str1[0];
          $mm=$res[1];

          array_push($finaldata1,round(floatval($value[$key]['1. open']),2));
          array_push($finalvolume1,floatval($value[$key]['5. volume']));
          array_push($finaldatevalue,(string)$mm.'/'.$dd);
        }
        //print_r ($finaldatevalue);
        ///////////////////////////////////////////////////
          $close_previous="";
        while($close_previous=="")
        {
          $date->sub(new DateInterval('P01D'));
          $previous_date = $date->format('Y-m-d');
          //echo "The previous Date".$previous_date;
          $close_previous=$data['Time Series (Daily)'][$previous_date]['4. close'];
          //echo "The close".$close_previous;
        }
        $change=$close_current-$close_previous;
        $change=round($change,2);
        $change_percent=round(($change/$close_current)*100,2);
        //echo $change_percent;
        $day_range=$data['Time Series (Daily)'][$current_date]['3. low'].'-'.$data['Time Series (Daily)'][$current_date]['2. high'];
        $volume = $data['Time Series (Daily)'][$current_date]['5. volume'];
        $time_stamp=$current_date;
        $value_in_table.='<div align="center" id="div3"><table><col width="300"><col width="600"><tr><td>Stock Sticker Symbol</td><td>'.$symbol.'</td></tr>';
        $value_in_table.='<tr><td>Close</td><td>'.$close_current.'</td></tr>';
        $value_in_table.='<tr><td>Open</td><td>'.$open_current.'</td></tr>';
        $value_in_table.='<tr><td>Previous Close</td><td>'.$close_previous.'</td></tr>';
        if($change>=0)
        {
          $value_in_table.='<tr><td>Change</td><td>'.$change.'<img src="http://cs-server.usc.edu:45678/hw/hw6/images/Green_Arrow_Up.png" height="15" width="15"/></td></tr>';
          $value_in_table.='<tr><td>Change Percent</td><td>'.$change_percent.'%<img src="http://cs-server.usc.edu:45678/hw/hw6/images/Green_Arrow_Up.png" height="15" width="15"/></td></tr>';
        }
        else {
          $value_in_table.='<tr><td>Change</td><td>'.$change.'<img src="http://cs-server.usc.edu:45678/hw/hw6/images/Red_Arrow_Down.png" height="15" width="15"/></td></tr>';
          $value_in_table.='<tr><td>Change Percent</td><td>'.$change_percent.'%<img src="http://cs-server.usc.edu:45678/hw/hw6/images/Red_Arrow_Down.png" height="15" width="15"/></td></tr>';
        }
        //echo $current_date1;
        //$value_in_table.='<tr><td>Change</td><td>'.$change.'</td></tr>';
        //$value_in_table.='<tr><td>Change Percent</td><td>'.$change_percent.'%</td></tr>';
        $value_in_table.='<tr><td>Days Range</td><td>'.$day_range.'</td></tr>';
        $value_in_table.='<tr><td>Volume</td><td>'.$volume.'</td></tr>';
        $value_in_table.='<tr><td>Timestamp</td><td>'.$time_stamp.'</td></tr>';
        $value_in_table.='<tr><td>Indicators</td><td><button onclick="callforgraph()" style="color: blue;padding: 0;border: none;background: none; font-size: 16px;" onmouseover="snext(this.id)" onmouseout="snext1(this.id)" id="krish">Price</button>&nbsp;&nbsp;&nbsp;&nbsp;<button style="color: blue;padding: 0;border: none;background: none; font-size: 16px;" onmouseover="snext(this.id)" onmouseout="snext1(this.id)" id="krish1" onclick="callforSMAgraph()" >SMA </button>&nbsp;&nbsp;&nbsp;&nbsp;<button onclick="callforEMAgraph()" style="color: blue;padding: 0;border: none;background: none; font-size: 16px;" onmouseover="snext(this.id)" onmouseout="snext1(this.id)" id="krish2">EMA</button>&nbsp;&nbsp;&nbsp;&nbsp;<button onclick="callforSTOCHgraph()" style="color: blue;padding: 0;border: none;background: none; font-size: 16px;" onmouseover="snext(this.id)" onmouseout="snext1(this.id)" id="krish3">STOCH</button>&nbsp;&nbsp;&nbsp;&nbsp;<button onclick="callforRSIgraph()" style="color: blue;padding: 0;border: none;background: none; font-size: 16px;" onmouseover="snext(this.id)" onmouseout="snext1(this.id)" id="krish4">RSI</button>&nbsp;&nbsp;&nbsp;&nbsp;<button onclick="callforADXgraph()" style="color: blue;padding: 0;border: none;background: none; font-size: 16px;" onmouseover="snext(this.id)" onmouseout="snext1(this.id)" id="krish5">ADX</button>&nbsp;&nbsp;&nbsp;&nbsp;<button onclick="callforCCIgraph()" style="color: blue;padding: 0;border: none;background: none; font-size: 16px;" onmouseover="snext(this.id)" onmouseout="snext1(this.id)" id="krish6">CCI</button>&nbsp;&nbsp;&nbsp;&nbsp;<button onclick="callforBBANDSgraph()" style="color: blue;padding: 0;border: none;background: none; font-size: 16px;" onmouseover="snext(this.id)" onmouseout="snext1(this.id)" id="krish7">BBANDS</button>&nbsp;&nbsp;&nbsp;&nbsp;<button onclick="callforMACDgraph()" style="color: blue;padding: 0;border: none;background: none; font-size: 16px;" onmouseover="snext(this.id)" onmouseout="snext1(this.id)" id="krish8">MACD</a></td></tr></table></div>';
        echo $value_in_table;?>
        <script>
        function callforgraph()
        {

          if(document.getElementById('stocksname')!="")
          {
            //var arr=
            var finaldata2=<?php echo json_encode($finaldata1);?>;
            var finalvolume2=<?php echo json_encode($finalvolume1);?>;
            var finaldatevalue1=<?php echo json_encode($finaldatevalue);?>;
            //for(var i in )
            var stockvalue= document.getElementById('stocksname').value;
            var today = String("<?php echo $data['Meta Data']['3. Last Refreshed']?>");
            var res=today.split("-");
            var length1=res.length;
            var str1=res[length1-1].split(" ");
            dd=str1[0];
            mm=res[1];
            yyyy=res[0];
            today1 = mm+'/'+dd+'/'+yyyy;
            Highcharts.chart('container', {
              chart: {
                borderColor: '#d2d2d2',
                borderWidth: 2
              },
              title: {
                text: 'Stock Price ('+today1+')'
              },
              subtitle: {
                text: '<a href="https://www.alphavantage.co/" style="text-decoration:none; color:blue" id="subtitlechart" onmouseover="snext(this.id)" onmouseout="snext1(this.id)" target="_blank">Source:Alpha Vantage</a>',
                useHTML:true
              },
             xAxis: [{
                      categories:finaldatevalue1.reverse(),
                      //  ordinal: true,
                      tickPositioner: function () {
                                var positions = [],
                                    tick = Math.floor(this.dataMax);

                                while (tick > this.dataMin) {

                                    positions.push(tick);

                                    tick -= 5;
                                }

                                positions.push(this.dataMin);

                                return positions.reverse();
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
                }
              },{
                tickInterval: 50000000,
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
                  color: '#c4392d',
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
                  color:'#FFFFFF'
                }
              },
              tooltip: {
                shared: false,
                //pointFormat: '{series.name}: {point.y:.2f}',
                xDateFormat: '%m/%d'
              },
              legend: {
              enabled: true,
              align: 'right',
              borderColor: 'black',
              borderWidth: 0,
              layout: 'vertical',
              verticalAlign: 'top',
              y: 100
          },

              series: [{
                type: 'area',
                name: stockvalue,
                data: finaldata2.reverse(),
                tooltip:{
                  pointFormat: '{series.name}: {point.y:.2f}'
                }
              },{
                type:'column',
                name:stockvalue+' Volume',
                yAxis: 1,
                //xAxis: 1,
                maxPointWidth: 5,
                data:finalvolume2.reverse()
              }]
            });

            }
          }
        </script>


    <div id="container" style="height: 600px; margin-left: 260px; margin-right:260px; min-width: 600px;"></div>

    <?php
    //echo "hello";
    $xml = simplexml_load_file('https://seekingalpha.com/api/sa/combined/'.$_POST["stock_name"].'.xml');
    //$json = json_encode($xml);
    //$array = json_decode($json,TRUE);
    $i=0;
    foreach($xml->channel->children() as $state)
    {
    /*  if (strpos($a, 'are') !== false) {
    echo 'true';
    }*/
    if($i>=5)
    {
      break;
    }
      if((string)$state->link!="")
      {
        if(strpos((string)$state->link,'article')!==false)
        {
          //$widget_id = str_replace(array('[',']'), '',$widget_text);
          $states[]= array('title' =>(string)$state->title,'hyperlink'=>(string)$state->link,'published'=>(string)$state->pubDate);
          $i++;
        }
      }
    }
    $i=0;
    //echo $states;
     $json1=json_encode($states);
     //echo $json;
  }
}

?>
<script>
function snext(id) {
    //console.log("hello");
    document.getElementById(id).style.color='black';
}
function snext1(id) {

    document.getElementById(id).style.color='blue';
}
var click=true;
var book = <?php echo $json1?>;
if(document.getElementById('stocksname')!="")
{
  document.write('<div id="div4" align="center"><button id="label1" onclick="showthenews()" style="color: rgb(175,175,175); padding: 0;border: none;background: none;">Click to Show the news</button><br><button id="button1" onclick="showthenews()" style="padding: 0;border: none;background: none;"><img id="img1" src="http://cs-server.usc.edu:45678/hw/hw6/images/Gray_Arrow_Down.png " height="15" width="20"/></button>');
  document.write('<div id="div5" align="center"></div>');
}
function showthenews()
{
  if(click==true)
  {
    document.getElementById('label1').innerHTML="Click to hide the news";
    document.getElementById('img1').src="http://cs-server.usc.edu:45678/hw/hw6/images/Gray_Arrow_Up.png";
    document.getElementById('div5').style.display="block";
    //document.write('<div id="div4" align="center"><label id="label1">Click to hide the news</label><br><button onclick="showthenews()"><img src="http://cs-server.usc.edu:45678/hw/hw6/images/Gray_Arrow_Up.png " height="15" width="20"/></button>');
    //var myArr = JSON.parse(book);
    //console.log(myArr);
    //document.getElementById('table1').innerHTML=book[0]['title'];
    console.log(book.length);
    var valuefortable="<table><col width='900'>";
    for(var i=0;i<book.length;i++)
    { //$widget_id = str_replace(array('[',']'), '',$widget_text);
      valuefortable+='<tr><td><a href="'+book[i]['hyperlink']+'" target="_blank" style="text-decoration:none">'+book[i]['title']+'</a>&nbsp;&nbsp;&nbsp;&nbsp;Published Time:'+book[i]['published'].replace("-0400","")+'</td></tr>';
      console.log(book[i]['title']);
    }
    valuefortable+='</table>';
    document.getElementById('div5').innerHTML=valuefortable;
    click=false;
  }
  else {
    document.getElementById('label1').innerHTML="Click to show the news";
    document.getElementById('img1').src="http://cs-server.usc.edu:45678/hw/hw6/images/Gray_Arrow_Down.png";
    document.getElementById('div5').style.display="none";
    click=true;
  }
}
</script>



    <script src="https://code.highcharts.com/stock/highstock.js"></script>
    <script src="https://code.highcharts.com/stock/modules/exporting.js"></script>


    <?php
    echo "<script> callforgraph(); </script>"
    ?>

  </body>
  </html>
