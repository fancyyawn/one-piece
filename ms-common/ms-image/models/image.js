// module dependencies
// ----------------------------------------

var mongoose = require('mongoose');



//  define the schema
// ----------------------------------------

// get the schema object
var Schema = mongoose.Schema;

// create the schema
var imageSchema = new Schema({
    title: String,
    image: { data: Buffer, contentType: String }
});

// convert the schema to model and export it
module.exports = mongoose.model('Image', imageSchema);