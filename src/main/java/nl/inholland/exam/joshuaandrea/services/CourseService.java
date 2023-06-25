package nl.inholland.exam.joshuaandrea.services;

import nl.inholland.exam.joshuaandrea.models.Course;
import nl.inholland.exam.joshuaandrea.models.Review;
import nl.inholland.exam.joshuaandrea.models.dtos.CourseRequestDto;
import nl.inholland.exam.joshuaandrea.repositories.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    private final Function<CourseRequestDto, Course> mapCourseDtoToCourse = dto -> {
        Course course = new Course();
        course.setTitle(dto.title());
        course.setDescription(dto.description());
        return course;
    };

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

    public Course addCourse(CourseRequestDto dto){
        Course course = mapCourseDtoToCourse.apply(dto);
        return courseRepository.save(course);
    }

    public Course updateCourse(long id, CourseRequestDto dto){
        Course course = mapCourseDtoToCourse.apply(dto);
        return courseRepository.save(course);
    }

}
