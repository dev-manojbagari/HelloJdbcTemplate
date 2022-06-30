package dev.manojb.dao;

import dev.manojb.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CourseJdbcDAO implements DAO<Course> {

    JdbcTemplate jdbcTemplate;

   RowMapper<Course> rowMapper = (rs, rowNum) -> {
    Course course = new Course();
    course.setCourseId(rs.getInt("course_id"));
    course.setTitle(rs.getString("title"));
    course.setDescription(rs.getString("description"));
    return course;
   };

    public CourseJdbcDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Course> list() {
      String sql = "Select course_id,title, description,link from course";
        return jdbcTemplate.query(sql,rowMapper);
    }

    @Override
    public void create(Course course) {

    }

    @Override
    public Optional<Course> get(int id) {
        return Optional.empty();
    }

    @Override
    public void update(Course course, int id) {

    }

    @Override
    public void delete(int id) {

    }
}
