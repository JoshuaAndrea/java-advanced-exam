package nl.inholland.exam.joshuaandrea.models.dtos;


public record ReviewRequestDto(
        long courseId,
        int rating,
        int studentNumber,
        String comment
){
}
