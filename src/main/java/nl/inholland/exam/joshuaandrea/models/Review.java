package nl.inholland.exam.joshuaandrea.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
        @GeneratedValue
        private long id;

        private int rating;
        private int studentNumber;
        private String comment;
        LocalDate date;

        @ManyToOne
        @JsonManagedReference
        Course course;

}
