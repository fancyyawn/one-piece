var express = require('express');
var path = require('path');
var favicon = require('serve-favicon');
var logger = require('morgan');
var cookieParser = require('cookie-parser');
var bodyParser = require('body-parser');
var mongoose = require('mongoose');

mongoose.connect('mongodb://localhost:27017/images');

let routes = require('./routes');

var app = express();

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');

// uncomment after placing your favicon in /public
//app.use(favicon(path.join(__dirname, 'public', 'favicon.ico')));
app.use(logger('dev'));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));


// 处理表单及文件上传的中间件
app.use(require('express-formidable')({
    uploadDir: path.join(__dirname, 'public/images'),// 上传文件目录
    keepExtensions: true// 保留后缀
}));

routes(app);

app.use(function (err, req, res, next) {
    res.render('error', {
        error: err
    });
});
module.exports = app;
