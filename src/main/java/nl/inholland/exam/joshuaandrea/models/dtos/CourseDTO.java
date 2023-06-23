package nl.inholland.exam.joshuaandrea.models.dtos;

import java.util.List;

import jakarta.annotation.Nullable;
import nl.inholland.exam.joshuaandrea.models.Review;

public record CourseDTO(
        String title,
        String description
) {
}
