package nl.inholland.exam.joshuaandrea.models.dtos;


public record ReviewDTO(
        long courseId,
        int rating,
        int studentNumber,
        String comment
){
}
