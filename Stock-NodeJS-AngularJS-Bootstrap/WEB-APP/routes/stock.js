var https = require('https');
var http = require('http');
var parser1 = require('xml2js').parseString;
/*var mongoose = require('mongoose');

//get gateways
module.exports.getUser = function(callback){

        return (callback);
}*/
module.exports.getStockTableData = function(stock_name,callback){
  var options={
     host: 'www.alphavantage.co',
     path: '/query?function=TIME_SERIES_DAILY&symbol='+stock_name+'&apikey=###########',
     method: 'GET'
  };
  https.request(options, function(res){
      var value_of_data = '';
      res.on('data',function(chunk){
          value_of_data+= chunk;
      });
      res.on('end', function(){
        try{
          var result = JSON.parse(value_of_data);
          //console.log("value in the SMA"+result);
          return callback(result);
        }catch(e){
          var result = '{"Error Message":"error in content"}';
          console.log("the data not in correct format");
          return callback(result);

        }
      });
      res.on('error',callback)
  }).on('error',callback).end();
}



module.exports.getStockPriceVolumeData = function(stock_name,callback){
  var options={
     host: 'www.alphavantage.co',
     path: '/query?function=TIME_SERIES_DAILY&symbol='+stock_name+'&outputsize=full&apikey=TBYMG7F0TDJ73HP6',
     method: 'GET'
  };
  https.request(options, function(res){
      var value_of_data = '';
      res.on('data',function(chunk){
          value_of_data+= chunk;
      });
      res.on('end', function(){
        try{
          var result = JSON.parse(value_of_data);
          console.log("value in the price"+result);
          return callback(result);
        }catch(e){
          var result = '{"Error Message":"error in content"}';
          console.log("the data not in correct format in price and volume");
          return callback(result);
        }
      });
      res.on('error',callback)
  }).on('error',callback).end();
}


module.exports.getStockSMAData = function(stock_name,callback){
  var options={
     host: 'www.alphavantage.co',
     path: '/query?function=SMA&symbol='+stock_name+'&interval=daily&time_period=10&series_type=close&apikey=TBYMG7F0TDJ73HP6',
     method: 'GET'
  };
  https.request(options, function(res){
      var value_of_data = '';
      res.on('data',function(chunk){
          value_of_data+= chunk;
      });
      res.on('end', function(){
        try{
          var result = JSON.parse(value_of_data);
          //console.log("value in the SMA"+result);
          return callback(result);
        }catch(e){
          var result = '{"Error Message":"error in content"}';
          console.log("the data not in correct format");
          return callback(result);

        }

      });
      res.on('error',callback)
  }).on('error',callback).end();
}

module.exports.getCompleteName = function(stock_name,callback){
  var options={
    host: 'dev.markitondemand.com',
    path:'/MODApis/Api/v2/Lookup/json?input='+stock_name,
    mehtod: 'GET'
  };
  http.request(options, function(res){
      var value_of_data = '';
      res.on('data',function(chunk){
          value_of_data+= chunk;
      });
      res.on('end', function(){
        try{
          var result = JSON.parse(value_of_data);
          //console.log("value in the SMA"+result);
          return callback(result);
        }catch(e){
          var result = '{"Error Message":"error in content"}';
          console.log("the data not in correct format");
          return callback(result);
        }
      });
      res.on('error',callback)
  }).on('error',callback).end();
}

module.exports.getStockEMAData = function(stock_name,callback){
  var options={
     host: 'www.alphavantage.co',
     path: '/query?function=EMA&symbol='+stock_name+'&interval=daily&time_period=10&series_type=close&apikey=TBYMG7F0TDJ73HP6',
     method: 'GET'
  };
  https.request(options, function(res){
      var value_of_data = '';
      res.on('data',function(chunk){
          value_of_data+= chunk;
      });
      res.on('end', function(){
        try{
          var result = JSON.parse(value_of_data);
          //console.log("value in the SMA"+result);
          return callback(result);
        }catch(e){
          var result = '{"Error Message":"error in content"}';
          console.log("the data not in correct format");
          return callback(result);
        }
      });
      res.on('error',callback)
  }).on('error',callback).end();
}

module.exports.getStockRSIData = function(stock_name,callback){
  var options={
     host: 'www.alphavantage.co',
     path: '/query?function=RSI&symbol='+stock_name+'&interval=daily&time_period=10&series_type=close&apikey=TBYMG7F0TDJ73HP6',
     method: 'GET'
  };
  https.request(options, function(res){
      var value_of_data = '';
      res.on('data',function(chunk){
          value_of_data+= chunk;
      });
      res.on('end', function(){
        try{
          var result = JSON.parse(value_of_data);
          //console.log("value in the SMA"+result);
          return callback(result);
        }catch(e){
          var result = '{"Error Message":"error in content"}';
          console.log("the data not in correct format");
          return callback(result);
        }
      });
      res.on('error',callback)
  }).on('error',callback).end();
}

module.exports.getStockADXData = function(stock_name,callback){
  var options={
     host: 'www.alphavantage.co',
     path: '/query?function=ADX&symbol='+stock_name+'&interval=daily&time_period=10&series_type=close&apikey=TBYMG7F0TDJ73HP6',
     method: 'GET'
  };
  https.request(options, function(res){
      var value_of_data = '';
      res.on('data',function(chunk){
          value_of_data+= chunk;
      });
      res.on('end', function(){
        try{
          var result = JSON.parse(value_of_data);
          //console.log("value in the SMA"+result);
          return callback(result);
        }catch(e){
          var result = '{"Error Message":"error in content"}';
          console.log("the data not in correct format");
          return callback(result);
        }
      });
      res.on('error',callback)
  }).on('error',callback).end();
}


module.exports.getStockCCIData = function(stock_name,callback){
  var options={
     host: 'www.alphavantage.co',
     path: '/query?function=CCI&symbol='+stock_name+'&interval=daily&time_period=10&series_type=close&apikey=TBYMG7F0TDJ73HP6',
     method: 'GET'
  };
  https.request(options, function(res){
      var value_of_data = '';
      res.on('data',function(chunk){
          value_of_data+= chunk;
      });
      res.on('end', function(){
        try{
          var result = JSON.parse(value_of_data);
          //console.log("value in the SMA"+result);
          return callback(result);
        }catch(e){
          var result = '{"Error Message":"error in content"}';
          console.log("the data not in correct format");
          return callback(result);
        }
      });
      res.on('error',callback)
  }).on('error',callback).end();
}

module.exports.getStockSTOCHData = function(stock_name,callback){
  var options={
     host: 'www.alphavantage.co',
     path: '/query?function=STOCH&symbol='+stock_name+'&interval=daily&slowkmatype=1&slowdmatype=1&apikey=TBYMG7F0TDJ73HP6',
     method: 'GET'
  };
  https.request(options, function(res){
      var value_of_data = '';
      res.on('data',function(chunk){
          value_of_data+= chunk;
      });
      res.on('end', function(){
        try{
          var result = JSON.parse(value_of_data);
          //console.log("value in the SMA"+result);
          return callback(result);
        }catch(e){
          var result = '{"Error Message":"error in content"}';
          console.log("the data not in correct format");
          return callback(result);
        }
      });
      res.on('error',callback)
  }).on('error',callback).end();
}


module.exports.getStockBBANDSData = function(stock_name,callback){
  var options={
     host: 'www.alphavantage.co',
     path: '/query?function=BBANDS&symbol='+stock_name+'&interval=daily&time_period=5&series_type=close&nbdevup=3&nbdevdn=3&apikey=TBYMG7F0TDJ73HP6',
     method: 'GET'
  };
  https.request(options, function(res){
      var value_of_data = '';
      res.on('data',function(chunk){
          value_of_data+= chunk;
      });
      res.on('end', function(){
        try{
          var result = JSON.parse(value_of_data);
          //console.log("value in the SMA"+result);
          return callback(result);
        }catch(e){
          var result = '{"Error Message":"error in content"}';
          console.log("the data not in correct format");
          return callback(result);
        }
      });
      res.on('error',callback)
  }).on('error',callback).end();
}


module.exports.getStockMACDData = function(stock_name,callback){
  var options={
     host: 'www.alphavantage.co',
     path: '/query?function=MACD&symbol='+stock_name+'&interval=daily&time_period=10&series_type=close&apikey=TBYMG7F0TDJ73HP6',
     method: 'GET'
  };
  https.request(options, function(res){
      var value_of_data = '';
      res.on('data',function(chunk){
          value_of_data+= chunk;
      });
      res.on('end', function(){
        try{
          var result = JSON.parse(value_of_data);
          //console.log("value in the SMA"+result);
          return callback(result);
        }catch(e){
          var result = '{"Error Message":"error in content"}';
          console.log("the data not in correct format");
          return callback(result);
        }
      });
      res.on('error',callback)
  }).on('error',callback).end();
}

//$xml = simplexml_load_file('https://seekingalpha.com/api/sa/combined/'.$_POST["stock_name"].'.xml');

module.exports.getStockNewsData = function(stock_name,callback){
  var options={
     host: 'seekingalpha.com',
     path: '/api/sa/combined/'+stock_name+'.xml',
     method: 'GET'
  };
  https.request(options, function(res){
      var value_of_data = '';
      res.on('data',function(chunk){
          value_of_data+= chunk;
      });
      res.on('end', function(){
        parser1(value_of_data, function (err, result) {
            //console.log(result);
            if(result==undefined)
            {
              //console.log("hello");
              return callback("undefined");
            }
            var something=JSON.parse(JSON.stringify(result));
            //console.log(something['rss']['channel'][0]['item'][0]['title']);

            return callback(something);
            });
          //var result = JSON.parse(value_of_data);
          //console.log(result['Time Series (Daily)']['2017-10-24']['1. open']);
          //return callback(result);
      });
      res.on('error',callback)
  }).on('error',callback).end();
}
