package nl.inholland.exam.joshuaandrea.controllers;

import nl.inholland.exam.joshuaandrea.models.Course;
import nl.inholland.exam.joshuaandrea.models.dtos.CourseDTO;
import nl.inholland.exam.joshuaandrea.services.CourseService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllCourses(){
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCourseById(@PathVariable long id){
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addCourse(@RequestBody CourseDTO dto){
        return ResponseEntity.ok(courseService.addCourse(dto));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateCourse(@RequestBody CourseDTO dto){
        return ResponseEntity.ok(courseService.updateCourse(dto));
    }
}
