var express = require("express");
var router  = express.Router({mergeParams: true});
var blogPost = require("../models/blogPost");
var comment = require("../models/comment");
var middleware = require("../middleware/index");


// ====================
// COMMENTS ROUTES
// ====================


// Show Adding comment Page
router.get("/blogPost/:id/comments/newComment", middleware.isLoggedIn, function(req, res){
    // find blogPost by id
    blogPost.findById(req.params.id, function(err, foundBlogPost){
        if(err){
			req.flash("error", err.message);
            console.log(err);
        } else {
             res.render("comments/newComment", {blogPost: foundBlogPost});
        }
    })
});

 //Adding comment to Blog Post Page
router.post("/blogPost/:id/comments", middleware.isLoggedIn, function(req, res){
   //lookup blogPost using ID
   blogPost.findById(req.params.id, function(err, blogPost){
       if(err){
		   req.flash("error", err.message);
           console.log(err);
           res.redirect("/");
       } else {
		//create new comment
        Comment.create(req.body.comment, function(err, comment){
           if(err){
			   req.flash("error", err.message);
               console.log(err);
			   res.redirect("/blogPost/" + req.params.id);
           } else {
			   //add username and id to comment
               comment.author.id = req.user._id;
               comment.author.username = req.user.username;
               //save comment
               comment.save();
			   //connect new comment to blogPost and Save it
               blogPost.comments.push(comment);
               blogPost.save();
			   //redirect blogPost show page
			   req.flash("success", "Successfully added comment");
               res.redirect("/blogPost/" + blogPost._id);
           }
        });
       }
   });
});

 // middleware.checkCommentOwnership,
// COMMENT EDIT ROUTE - Edit Form
router.get("/blogPost/:id/comments/:comment_id/edit", function(req, res){
   Comment.findById(req.params.comment_id, function(err, foundComment){
      if(err){
		  req.flash("error", err.message);
          res.redirect("back");
      } else {
        res.render("comments/editComment", {blogPost_id: req.params.id, comment: foundComment});
      }
   });
});


// COMMENT UPDATE
router.put("/blogPost/:id/comments/:comment_id", middleware.checkCommentOwnership, function(req, res){
   Comment.findByIdAndUpdate(req.params.comment_id, req.body.comment, function(err, updatedComment){
      if(err){
		  req.flash("error", err.message);
          res.redirect("back");
      } else {
		  req.flash("success", "Successfully updated comment");
          res.redirect("/blogPost/" + req.params.id );
      }
   });
});


// COMMENT DESTROY ROUTE
router.delete("/blogPost/:id/comments/:comment_id", middleware.checkCommentOwnership, function(req, res){
    //findByIdAndRemove
    Comment.findByIdAndRemove(req.params.comment_id, function(err){
       if(err){
		   req.flash("error", err.message);
           res.redirect("back");
       } else {
		   req.flash("success", "Successfully removed comment");
           res.redirect("/blogPost/" + req.params.id);
       }
    });
});


module.exports = router;