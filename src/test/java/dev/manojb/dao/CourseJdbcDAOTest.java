package dev.manojb.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import dev.manojb.model.Course;

@DataJdbcTest
class CourseJdbcDAOTest {

	private JdbcTemplate jdbcTemplate;
	private CourseJdbcDAO dao;

	@Autowired
	public CourseJdbcDAOTest(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		dao = new CourseJdbcDAO(jdbcTemplate);
	}

	@Test
	public void listCourses_ShouldReturnAllCourses() {
		List<Course> courses = dao.list();
		assertEquals(5, courses.size());
	}

	@Test
	public void getCourseWithValidId_ShouldReturnCourse() {
		Optional<Course> course = dao.get(1);
		assertTrue(course.isPresent());
	}

	@Test
	public void getCourseWithInvalidId_ShouldReturnEmptyOptional() {
		Optional<Course> course = dao.get(99);
		assertFalse(course.isPresent());
	}

	@Test
	public void validCourse_ShouldBeCreated() {
		Course course = new Course("test_title", "test_desc", "test_link");
		dao.create(course);

		List<Course> courses = dao.list();
		assertEquals(6, courses.size());
		assertEquals("test_title", courses.get(5).getTitle());
		assertEquals("test_desc", courses.get(5).getDescription());
		assertEquals("test_link", courses.get(5).getLink());
	}

	@Test
	public void updateCourse_ShouldBeUpdated() {
		Course course = dao.get(2).get();
		course.setTitle("Spring Boot Testing Masterclass");
		dao.update(course, 1);

		// re-read from database
		Course updatedCourse = dao.get(1).get();
		assertEquals("Spring Boot Testing Masterclass", updatedCourse.getTitle());
	}

	@Test
	public void deleteCourse_ShouldRemoveCourse() {
		dao.delete(1);
		List<Course> courses = dao.list();
		assertEquals(4, courses.size());
	}

}
