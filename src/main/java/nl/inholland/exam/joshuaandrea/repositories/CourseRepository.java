package nl.inholland.exam.joshuaandrea.repositories;

import nl.inholland.exam.joshuaandrea.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}
