package fr.efrei.server.service;

import fr.efrei.server.domain.Student;
import fr.efrei.server.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    public final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findById(Integer id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student addStudent(Student student) {
        Student newStudent = new Student();
        student.setName(student.getName());
        student.setAge(student.getAge());
        return studentRepository.save(newStudent);
    }

    public Student updateStudent(Student student) {
        Student studentToUpdate = studentRepository.findById(student.getId()).orElse(null);
        if (studentToUpdate == null) {
            return null;
        }
        studentToUpdate.setName(student.getName());
        studentToUpdate.setAge(student.getAge());
        return studentRepository.save(studentToUpdate);
    }

    public void deleteStudent(Integer id) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student != null) {
            studentRepository.delete(student);
        }
    }
}
