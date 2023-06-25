package nl.inholland.exam.joshuaandrea.services;

import nl.inholland.exam.joshuaandrea.models.Course;
import nl.inholland.exam.joshuaandrea.models.Review;
import nl.inholland.exam.joshuaandrea.models.dtos.CourseRequestDto;
import nl.inholland.exam.joshuaandrea.models.dtos.ReviewRequestDto;
import nl.inholland.exam.joshuaandrea.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

import javax.naming.LimitExceededException;
import java.time.LocalDate;
import java.util.function.Function;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final CourseService courseService;

    private final Function<ReviewRequestDto, Review> mapReviewDtoToReview = dto -> {
        Review review = new Review();
        review.setRating(dto.rating());
        review.setComment(dto.comment());
        review.setStudentNumber(dto.studentNumber());
        review.setDate(LocalDate.now());
        return review;
    };

    public ReviewService(ReviewRepository reviewRepository, CourseService courseService) {
        this.reviewRepository = reviewRepository;
        this.courseService = courseService;
    }

    public Review addReview(ReviewRequestDto reviewRequestDto) throws LimitExceededException {
        if(StudentAlreadyReviewed(reviewRequestDto)){
            throw new LimitExceededException("Student already reviewed this course");
        }
        Review review = mapReviewDtoToReview.apply(reviewRequestDto);
        review.setCourse(courseService.getCourseById(reviewRequestDto.courseId()));

        return reviewRepository.save(review);
    }

    private boolean StudentAlreadyReviewed(ReviewRequestDto reviewRequestDto){
        return reviewRepository.existsByStudentNumberAndCourseId(reviewRequestDto.studentNumber(), reviewRequestDto.courseId());
    }
}
