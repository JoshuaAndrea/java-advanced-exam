package nl.inholland.exam.joshuaandrea.configuration;

import jakarta.transaction.Transactional;
import nl.inholland.exam.joshuaandrea.models.Course;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements ApplicationRunner {

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {

    }
}
