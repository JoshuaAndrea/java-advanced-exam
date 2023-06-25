package nl.inholland.exam.joshuaandrea.controllers;

import nl.inholland.exam.joshuaandrea.models.dtos.CourseRequestDto;
import nl.inholland.exam.joshuaandrea.services.CourseService;
import org.springframework.http.HttpStatus;
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
        return ResponseEntity.status(HttpStatus.OK).body(courseService.getAllCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCourseById(@PathVariable long id){
        return ResponseEntity.status(HttpStatus.OK).body(courseService.getCourseById(id));
    }

    @PostMapping()
    public ResponseEntity<Object> addCourse(@RequestBody CourseRequestDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.addCourse(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCourse(@PathVariable long id, @RequestBody CourseRequestDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(courseService.updateCourse(id, dto));
    }
}
