package nl.inholland.exam.joshuaandrea.controllers;

import nl.inholland.exam.joshuaandrea.models.dtos.ReviewDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import nl.inholland.exam.joshuaandrea.services.ReviewService;

import javax.naming.LimitExceededException;

@RestController
@RequestMapping("/reviews")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class ReviewController {

   private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
         this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<Object> addReview(@RequestBody ReviewDTO reviewDto) throws LimitExceededException {
        return ResponseEntity.ok(reviewService.addReview(reviewDto));
    }
}
