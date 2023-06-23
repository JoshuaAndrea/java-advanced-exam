package nl.inholland.exam.joshuaandrea.repositories;

import nl.inholland.exam.joshuaandrea.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    boolean existsByStudentNumberAndCourseId(long studentNumber, long courseId);
}
