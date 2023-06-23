package nl.inholland.exam.joshuaandrea.services;

import nl.inholland.exam.joshuaandrea.models.Course;
import nl.inholland.exam.joshuaandrea.models.Review;
import nl.inholland.exam.joshuaandrea.repositories.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses(){
        List<Course> courses = courseRepository.findAll();

        courses.forEach(course -> {
            course.setAverageRating(
                    course
                        .getReviews()
                        .stream()
                        .mapToInt(Review::getRating)
                        .average()
                        .orElse(0));
        });

        return courses;
    }

    public Course getCourseById(long id){
        Course course = courseRepository.findById(id)
                                .orElse(null);
        if (course == null) throw new IllegalArgumentException("Course not found");

        course.setAverageRating(
                course
                    .getReviews()
                    .stream()
                    .mapToInt(Review::getRating)
                    .average()
                    .orElse(0));

        return course;
    }

    public Course addCourse(Course course){
        return courseRepository.save(course);
    }

    public Course updateCourse(Course course){
        return courseRepository.save(course);
    }

}
