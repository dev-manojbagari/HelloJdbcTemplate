package dev.manojb;

import dev.manojb.dao.DAO;
import dev.manojb.model.Course;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class CoursePlatformApplication {

    static DAO<Course> dao;

    public CoursePlatformApplication(DAO<Course> dao) {
        this.dao = dao;
    }

    public static void main(String[] args) {
        SpringApplication.run(CoursePlatformApplication.class, args);
        System.out.println("\n All Courses --------------------------\n");
        List<Course> courses = dao.list();
        courses.forEach(System.out::println);


    }




}
