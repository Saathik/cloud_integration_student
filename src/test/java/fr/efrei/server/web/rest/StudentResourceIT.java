package fr.efrei.server.web.rest;

import fr.efrei.server.domain.Student;
import fr.efrei.server.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@TestPropertySource(
        locations = "classpath:application-test.properties")
public class StudentResourceIT {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    @Transactional
    void getStudent() throws Exception {
        // Given
        Student student = new Student();
        student.setName("Saathik");
        student.setAge(22);
        studentRepository.save(student);

        // When
        Student result = studentRepository.findById(student.getId()).orElse(null);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Saathik");
        assertThat(result.getAge()).isEqualTo(22);
    }

    @Test
    @Transactional
    void createStudent() throws Exception {
        // Given
        int databaseSizeBeforeCreate = studentRepository.findAll().size();
        assertThat(databaseSizeBeforeCreate).isEqualTo(0);

        Student student = new Student();
        student.setName("Saathik");
        student.setAge(22);
        studentRepository.save(student);

        // When
        List<Student> studentList = studentRepository.findAll();

        // Then
        assertThat(studentList.size()).isEqualTo(databaseSizeBeforeCreate + 1);
    }

    @Test
    @Transactional
    void updateStudent() throws Exception {
        // Given
        Student student = new Student();
        student.setName("Saathik");
        student.setAge(22);
        studentRepository.save(student);

        // When
        student.setName("Valentin");
        studentRepository.save(student);
        Student result = studentRepository.findById(student.getId()).orElse(null);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Valentin");
        assertThat(result.getAge()).isEqualTo(22);
    }

    @Test
    @Transactional
    void deleteStudent() throws Exception {
        // Given
        Student student = new Student();
        student.setName("Saathik");
        student.setAge(22);
        studentRepository.save(student);
        assertThat(studentRepository.findAll().size()).isEqualTo(1);

        // When
        studentRepository.deleteById(student.getId());

        // Then
        assertThat(studentRepository.findAll().size()).isEqualTo(0);
    }
}

