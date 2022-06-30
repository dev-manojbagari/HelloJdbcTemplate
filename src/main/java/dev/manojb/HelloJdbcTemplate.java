package dev.manojb;

import dev.manojb.dao.DAO;
import dev.manojb.model.Course;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class HelloJdbcTemplate {

    static DAO<Course> dao;

    public HelloJdbcTemplate(DAO<Course> dao) {
        this.dao = dao;
    }

    public static void main(String[] args) {
        SpringApplication.run(HelloJdbcTemplate.class, args);

        System.out.println("\nCreate Course -------------------------------------\n");
        Course springVue = new Course("Spring Boot + Vue","New Course","http://www.danvega.dev/courses");
        dao.create(springVue);


        System.out.println("\n All Courses --------------------------\n");
        List<Course> courses = dao.list();
        courses.forEach(System.out::println);


    }




}
