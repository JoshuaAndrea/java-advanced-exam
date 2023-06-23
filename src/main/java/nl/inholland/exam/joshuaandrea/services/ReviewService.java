package nl.inholland.exam.joshuaandrea.services;

import nl.inholland.exam.joshuaandrea.models.Course;
import nl.inholland.exam.joshuaandrea.models.Review;
import nl.inholland.exam.joshuaandrea.models.dtos.ReviewDTO;
import nl.inholland.exam.joshuaandrea.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

import javax.naming.LimitExceededException;
import java.time.LocalDate;
import java.util.function.Function;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final CourseService courseService;

    public ReviewService(ReviewRepository reviewRepository, CourseService courseService) {
        this.reviewRepository = reviewRepository;
        this.courseService = courseService;
    }

    public Review addReview(ReviewDTO reviewDto) throws LimitExceededException {
        if(StudentAlreadyReviewed(reviewDto)){
            throw new LimitExceededException("Student already reviewed this course");
        }
        Review review = new Review();
        review.setRating(reviewDto.rating());
        review.setComment(reviewDto.comment());
        review.setStudentNumber(reviewDto.studentNumber());
        review.setDate(LocalDate.now());
        review.setCourse(courseService.getCourseById(reviewDto.courseId()));

        return reviewRepository.save(review);
    }

    private boolean StudentAlreadyReviewed(ReviewDTO reviewDto){
        return reviewRepository.existsByStudentNumberAndCourseId(reviewDto.studentNumber(), reviewDto.courseId());
    }
}
