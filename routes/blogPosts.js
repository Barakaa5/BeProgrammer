var express = require("express");
var router  = express.Router();
var blogPost = require("../models/blogPost");
var middleware = require("../middleware/index");



// ====================
// BlogPosts ROUTES
// ====================

//INDEX - show all blogPosts
router.get("/blogPosts", function(req, res){
    // Get all blogPost from DB
    blogPost.find({}, function(err, allBlogPosts){
       if(err){
		   req.flash("error", err.message);
           console.log(err);
       } else {
          res.render("blogPosts/blogPosts",{blogPost: allBlogPosts});
       }
    });
});

//Blog Post - show blogPosts Information
router.get("/blogPost/:id", function(req, res){
	blogPost.findById(req.params.id).populate("comments").exec(function(err, foundBlogPost){
		if (err){
			req.flash("error", err.message);
			res.redirect("/");
		} else{
			res.render("blogPosts/blogPost", {blogPost: foundBlogPost});
		}
	});
   
});

//New Blog Post - show new blogPosts form
router.get("/newBlogPost", middleware.isLoggedIn, function(req, res){
    res.render("blogPosts/newBlogPost");
});

//CREATE - add new blogPosts to DB
router.post("/blogPosts", middleware.isLoggedIn, function(req, res){
	var title = req.body.title;
    var image = req.body.image;
    var description = req.body.description;
    var author = {id: req.user._id, username: req.user.username}
	var newBlogPost = {title: title, image: image, description: description, author: author}
    // Create a new blogPost and save to DB
    blogPost.create(newBlogPost, function(err, newlyCreated){
        if(err){
		    req.flash("error", err.message);
            console.log(err);
        } else {
            //redirect back to blogPosts page
			req.flash("success", "Succsesfully created new blog post to BeProgrammer!");
            res.redirect("/blogPosts");
        }
    });
});


//Blog Post Page - Edit blogPosts Information 
router.get("/blogPost/:id/edit", middleware.checkBlogPostOwnership, function(req, res){
	blogPost.findById(req.params.id ,function(err, foundBlogPost){
		res.render("blogPosts/editBlogPost", {blogPost: foundBlogPost})
    });
});

//Blog Post - Editing blogPosts Information
router.put("/blogPost/:id", middleware.checkBlogPostOwnership, function(req, res){
    // find and update the correct blogPost
    blogPost.findByIdAndUpdate(req.params.id, req.body.blogPost, function(err, updatedBlogPost){
       if(err){
		   req.flash("error", err.message);
           res.redirect("/");
		   console.log(err);
       } else {
           //redirect show page
		   req.flash("success", "Succsesfully Updated blog post!");
           res.redirect("/blogPost/" + req.params.id);
       }
    });
});

// DESTROY Blog Post ROUTE
router.delete("/blogPost/:id", middleware.checkBlogPostOwnership, function(req, res){
   blogPost.findByIdAndRemove(req.params.id, function(err){
      if(err){
		  req.flash("error", err.message);
          res.redirect("/");
		  consloe.log(err);
      } else {
		  req.flash("success", "Succsesfully Deleted blog post!");
          res.redirect("/");
      }
   });
});

module.exports = router;
