package nl.inholland.exam.joshuaandrea.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        private int rating;
        private int studentNumber;
        private String comment;
        LocalDate date;

        @ManyToOne
        @JsonBackReference
        Course course;

}
