package nl.inholland.exam.joshuaandrea.controllers;

import nl.inholland.exam.joshuaandrea.models.dtos.ReviewRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> addReview(@RequestBody ReviewRequestDto reviewRequestDto) throws LimitExceededException {
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.addReview(reviewRequestDto));
    }
}
