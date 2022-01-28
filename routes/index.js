var express = require('express');
router = express.Router();
passport = require('passport');
User = require('../models/user');
middleware = require('../middleware');

//  ===========
// index ROUTES
//  ===========

router.post('/', function(req, res) {
	var newEmail = { email: req.body.newsLetterEmail1 };
	// Adding a new Email and save to DB
	emails.create(newEmail, function(err, newlyCreated) {
		if (err) {
			req.flash('error', err.message);
			console.log(err);
		} else {
			//redirect back to Index page
			req.flash('success', 'Succsesfully added your Email to BeProgrammer!');
			res.redirect('back');
		}
	});
});

router.get('/', function(req, res) {
	blogPost.find({}, function(err, allBlogPosts) {
		if (err) {
			req.flash('error', err.message);
			console.log(err);
		} else {
			res.render('index', { blogPost: allBlogPosts });
		}
	});
});
//  ===========
// index ROUTES
//  ===========

//  ===========
// AUTH ROUTES
//  ===========

// show register form
router.get('/register', function(req, res) {
	res.render('register');
});

//handle sign up logic
router.post('/register', function(req, res) {
	var newUser = new User({ username: req.body.username, email: req.body.email });
	User.register(newUser, req.body.password, function(err, user) {
		if (err) {
			console.log(err);
			req.flash('error', err.message);
			return res.render('register');
		}
		passport.authenticate('local')(req, res, function() {
			req.flash('success', 'Welcome to BeProgremmer ' + user.username);
			res.redirect('/');
		});
	});
});

// show login form
router.get('/login', function(req, res) {
	res.render('login');
});

// handling login logic
router.post('/login',passport.authenticate('local', {
		successRedirect: '/',
		failureRedirect: '/login'
	}),
	function(req, res) {}
);

// Log Out - logic route
router.get('/logout', function(req, res) {
	req.logout();
	req.flash('success', 'Successfully logged out');
	res.redirect('/');
});

//  =================================
// resume ROUTES

router.get('/resume', function(req, res) {
	res.render('Resume');
});

// resume ROUTES
//  =================================

//  =================================
// Services & Pricing ROUTES

router.get('/services', function(req, res) {
	res.render('services');
});

//  Services & Pricing ROUTES
//  =================================

//  =================================
//  contact ROUTES

router.get('/contact', function(req, res) {
	res.render('contact');
});

/*const auth = {
    auth: {
        api_key: 'c26b05f3230489cf4667db2a5d07d55b-a9919d1f-74b222ec', 
        domain:'sandbox5b03c21a31ad466a8d44473b68462e61.mailgun.org' 
    }
};

const transporter = nodemailer.createTransport(mailGun(auth));


const sendMail = (email, subject, text, cb) => {
    const mailOptions = {
        from: "barakaa5@walla.com",
        to: 'amzalegbarak@gmail.com', 
        subject:"Test Mailgun",
        text:"We Sucseed!"
    };

    transporter.sendMail(mailOptions, function (err, data) {
        if (err) {
            return cb(err, null);
        }
        return cb(null, data);
    });
}*/

//  contact ROUTES
//  =================================

module.exports = router;