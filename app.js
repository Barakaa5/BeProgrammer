const express        = require("express");
      app            = express();
      blogPost       = require("./models/blogPost");
      Comment        = require("./models/comment");
      User           = require("./models/user"); 
      emails         = require("./models/emails");
      bodyParser     = require("body-parser");
      mongoose       = require("mongoose");
      flash          = require("connect-flash");
      passport       = require("passport");
      LocalStrategy  = require("passport-local");
      methodOverride = require("method-override");
      nodemailer     = require('nodemailer'); //Mailing function
      mailGun        = require('nodemailer-mailgun-transport');//Mailing function

      //requring routes
      commentRoutes  = require("./routes/comments");
      blogPostRoutes = require("./routes/blogPosts");
      indexRoutes    = require("./routes/index");


//Connecting to mongodb atlas
mongoose.connect('mongodb+srv://Barakaa5:barak2661995@barakamzaleg-dvlsk.mongodb.net/test?retryWrites=true&w=majority', {
	useNewUrlParser: true,
	useCreateIndex: true,
	useUnifiedTopology: true
}).then(() => {
	console.log('Connected to DB!');
}).catch(err => {
	console.log('ERROR:', err.message);
});


app.use(bodyParser.urlencoded({extended: true}));
app.set("view engine", "ejs");
app.use(express.static(__dirname + "/public"));
app.use(methodOverride("_method"));
app.use(flash());
app.locals.moment = require('moment');

// ====================
// PASSPORT CONFIGURATION
// ====================

app.use(require("express-session")({
    secret: "Once again Rusty wins cutest dog!",
    resave: false,
    saveUninitialized: false
}));
app.use(passport.initialize());
app.use(passport.session());
passport.use(new LocalStrategy(User.authenticate()));
passport.serializeUser(User.serializeUser());
passport.deserializeUser(User.deserializeUser());

app.use(function(req, res, next){
	res.locals.currentUser = req.user;
	res.locals.error = req.flash("error");
	res.locals.success = req.flash("success");
	res.locals.primary = req.flash("primary");
   next();
});

app.use(indexRoutes);
app.use(blogPostRoutes);
app.use(commentRoutes);


/*app.listen(5500, () => {
	console.log('server listening on port 5500');
});	*/

//Syntax Suitable for Heroku:
const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
    console.log(`app is running on port ${ PORT }`);
});