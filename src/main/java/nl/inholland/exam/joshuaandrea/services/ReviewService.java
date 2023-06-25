package nl.inholland.exam.joshuaandrea.services;

import nl.inholland.exam.joshuaandrea.models.Review;
import nl.inholland.exam.joshuaandrea.models.dtos.ReviewRequestDto;
import nl.inholland.exam.joshuaandrea.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

import javax.naming.LimitExceededException;
import java.time.LocalDate;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final CourseService courseService;

    public ReviewService(ReviewRepository reviewRepository, CourseService courseService) {
        this.reviewRepository = reviewRepository;
        this.courseService = courseService;
    }

    public Review addReview(ReviewRequestDto reviewRequestDto) throws LimitExceededException {
        if(StudentAlreadyReviewed(reviewRequestDto)){
            throw new LimitExceededException("Student already reviewed this course");
        }
        Review review = new Review();
        review.setRating(reviewRequestDto.rating());
        review.setComment(reviewRequestDto.comment());
        review.setStudentNumber(reviewRequestDto.studentNumber());
        review.setDate(LocalDate.now());
        review.setCourse(courseService.getCourseById(reviewRequestDto.courseId()));

        return reviewRepository.save(review);
    }

    private boolean StudentAlreadyReviewed(ReviewRequestDto reviewRequestDto){
        return reviewRepository.existsByStudentNumberAndCourseId(reviewRequestDto.studentNumber(), reviewRequestDto.courseId());
    }
}
