package nl.inholland.exam.joshuaandrea.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue
    private long id;

    private String title;
    private String description;

    @OneToMany
    @JsonManagedReference
    @Nullable
    private List<Review> reviews;

    @Transient
    private double averageRating;
}
