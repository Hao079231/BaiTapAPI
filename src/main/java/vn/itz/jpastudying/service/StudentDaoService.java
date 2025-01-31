package vn.itz.jpastudying.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import vn.itz.jpastudying.exceptions.ApiException;
import vn.itz.jpastudying.exceptions.ApiRequestException;
import vn.itz.jpastudying.model.Student;
import vn.itz.jpastudying.repository.StudentRepository;

@Service
public class StudentDaoService {
  @Autowired
  private StudentRepository studentRepository;

  public ApiException<List<Student>> findAllStudents() {
    List<Student> students = studentRepository.findAll();
    if (students.isEmpty()) {
      return new ApiException<>(
          false,
          "Students are empty",
          null,
          HttpStatus.NOT_FOUND
      );
    }

    return new ApiException<>(
        true,
        "All students",
        students,
        HttpStatus.OK
    );
  }

  public ApiException<Student> findStudentById(int id) {
    Student student = studentRepository.findById(id).orElseThrow(() ->
        new ApiRequestException("Student doesn't exist", HttpStatus.NOT_FOUND));

    return new ApiException<>(
        true,
        "Found student",
        student,
        HttpStatus.OK
    );
  }

  public ApiException<Student> createStudent(Student student) {
    if (student.getUsername() == null || student.getUsername().isEmpty() || student.getFullname() == null
        || student.getFullname().isEmpty() || student.getBirthday() == null || student.getPassword() == null
        || student.getPassword().isEmpty()) {
      return new ApiException<>(
          false,
          "Student creation failed",
          student,
          HttpStatus.BAD_REQUEST
      );
    }
    Student savedStudent = studentRepository.save(student);
    return new ApiException<>(
        true,
        "Student created successfully",
        savedStudent,
        HttpStatus.CREATED
    );
  }

  public ApiException<Student> deleteStudent(int id) {
    Student student = studentRepository.findById(id).orElseThrow(() ->
        new ApiRequestException("Student doesn't exist", HttpStatus.NOT_FOUND));

    studentRepository.delete(student);
    return new ApiException<>(
        true,
        "Student deleted successfully",
        student,
        HttpStatus.OK
    );
  }

  public ApiException<Student> updateStudent(int id, Student student) {
    if (studentRepository.findById(id).isEmpty()){
      return new ApiException<>(
          false,
          "Student doesn't exist",
          student,
          HttpStatus.NOT_FOUND);
    }

    if (student.getUsername() == null || student.getUsername().isEmpty() || student.getFullname() == null || student.getFullname().isEmpty() || student.getBirthday() == null  || student.getPassword() == null || student.getPassword().isEmpty()) {
      return new ApiException<>(
          false,
          "Student update failed",
          student,
          HttpStatus.BAD_REQUEST
      );
    }
    Student updatedStudent = studentRepository.save(student);
    return new ApiException<>(
        true,
        "Student updated successfully",
        updatedStudent,
        HttpStatus.OK
    );
  }
}
