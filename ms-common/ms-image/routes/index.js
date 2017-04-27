var images = require('./images');

module.exports = function(app) {

    app.get('/', function (req, res) {
        res.redirect('/images/all');
    });
    app.use('/images', images);

}