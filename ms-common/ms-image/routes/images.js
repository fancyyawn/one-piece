var express = require('express');
var router = express.Router();
// const multer = require('multer');
const fs = require('fs');

var Image = require('../models/image');

router.get('/:id/data', function (req, res, next) {
    Image.findById(req.params.id, function(err, image) {
        // check for errors
        if(err) {
            // adds the http status code to the err object
            err.status = 422;

            // go to the error handler middleware
            return next(err);
        }

        res.contentType(image.image.contentType);
        res.send(image.image.data);

        // let data = image.image.data.toString();
        // res.contentType(image.image.contentType);
        // res.send(Buffer.from(data, "base64"));
    });
})

router.get('/create', function (req, res) {
    res.render('image-create');
});

router.get('/views', function (req, res, next) {
    // get all images
    Image.find({}, function(err, images) {
        // check for errors
        if(err) {
            // go to the error handler middleware
            return next(err);
        }

        // if no errors, go to the list page
        res.render('viewer', { images: images });
    }).sort({ _id: 1 });
});

router.get('/all', function (req, res, next) {
    // get all images
    Image.find({}, function(err, images) {
        // check for errors
        if(err) {
            // go to the error handler middleware
            return next(err);
        }

        // if no errors, go to the list page
        res.render('images', { images: images });
    }).sort({ _id: 1 });
});

router.get('/:id', function (req, res, next) {
    // get an image that its id be equals the value sent by url parameter
    Image.findById(req.params.id, function(err, image) {
        // check for errors
        if(err) {
            // adds the http status code to the err object
            err.status = 422;

            // go to the error handler middleware
            return next(err);
        }

        // if no errors, go to the image page
        image.image.data = Buffer.from(image.image.data).toString("base64");

        res.render('image', image);
    });
});

// router.post('/', multer({ dest: '../public/img' }).single('image'), function (req, res) {
router.post('/', function (req, res, next) {
        // creates an object of the image model
        var image = new Image();

        // adds the fields coming from the request in the image object
        image.title = req.fields.title;
        // image.image.data = fs.readFileSync(req.file.path);
        // image.image.contentType = req.file.mimetype;

        image.image.data = fs.readFileSync(req.files.image.path);
        image.image.contentType = req.files.image.type;

        // save image
        image.save(function(err) {
            // check for errors
            if(err) {
                // go to the error handler middleware
                return next(err);
            }

            // if no errors, log this message in the console
            console.log('image saved in mongodb!');

            // delete image from uploads directory
            fs.unlink(req.files.image.path, function(err) {
                // check for errors
                if(err) {
                    // go to the error handler middleware
                    return next(err);
                }

                // if no errors, log this message in the console
                console.log('image deleted from server!');

                // res.flash( { message: 'Saved image!' });
                // go to the home page
                res.redirect('/images/all');
            });
        });
    });


module.exports = router;