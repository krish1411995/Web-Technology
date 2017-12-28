var express = require('express');
var app = express();
var bodyParser= require('body-parser');
var cors = require('cors');
Stock = require('./routes/stock');
app.use(express.static(__dirname));
app.use(bodyParser.json())
app.use(bodyParser.urlencoded({ extended: true }));
app.use(function(req, res, next) {
 res.header("Access-Control-Allow-Origin", "*");
 res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
 next();
});

app.get('/',function(req,res){
  res.send("hello");
});

app.get('/stock/table/:stockname',function(req,res){
  var stock_name = req.params.stockname;
  Stock.getStockTableData(stock_name,function(stock_data)
  {
    res.json(stock_data);
  });

});

app.get('/stock/pricevolume/:stockname',function(req,res){
  var stock_name = req.params.stockname;
  Stock.getStockPriceVolumeData(stock_name,function(stock_data)
  {
    res.json(stock_data);
  });

});

app.get('/stock/completename/:stockname',function(req,res){
  var stock_name = req.params.stockname;
  Stock.getCompleteName(stock_name,function(stock_data)
  {
    res.json(stock_data);
  });

});

app.get('/stock/EMA/:stockname',function(req,res){
  var stock_name = req.params.stockname;
  Stock.getStockEMAData(stock_name,function(stock_data)
  {
    res.json(stock_data);
  });
});

app.get('/stock/SMA/:stockname',function(req,res){
  var stock_name = req.params.stockname;
  Stock.getStockSMAData(stock_name,function(stock_data)
  {
    res.json(stock_data);
  });
});

app.get('/stock/RSI/:stockname',function(req,res){
  var stock_name = req.params.stockname;
  Stock.getStockRSIData(stock_name,function(stock_data)
  {
    res.json(stock_data);
  });
});

app.get('/stock/ADX/:stockname',function(req,res){
  var stock_name = req.params.stockname;
  Stock.getStockADXData(stock_name,function(stock_data)
  {
    res.json(stock_data);
  });
});

app.get('/stock/CCI/:stockname',function(req,res){
  var stock_name = req.params.stockname;
  Stock.getStockCCIData(stock_name,function(stock_data)
  {
    res.json(stock_data);
  });
});

app.get('/stock/STOCH/:stockname',function(req,res){
  var stock_name = req.params.stockname;
  Stock.getStockSTOCHData(stock_name,function(stock_data)
  {
    res.json(stock_data);
  });
});

app.get('/stock/BBANDS/:stockname',function(req,res){
  var stock_name = req.params.stockname;
  Stock.getStockBBANDSData(stock_name,function(stock_data)
  {
    res.json(stock_data);
  });
});

app.get('/stock/MACD/:stockname',function(req,res){
  var stock_name = req.params.stockname;
  Stock.getStockMACDData(stock_name,function(stock_data)
  {
    res.json(stock_data);
  });
});

app.get('/stock/NEWS/:stockname',function(req,res){
  var stock_name = req.params.stockname;
  Stock.getStockNewsData(stock_name,function(stock_data)
  {
    if(stock_data=="undefined")
    {
      res.json({"Error":"undefined"});
    }
    else {
        res.json(stock_data.rss.channel[0].item);
    }
  });
});
app.listen(8081);
