package vn.itz.jpastudying.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import vn.itz.jpastudying.exceptions.ApiException;
import vn.itz.jpastudying.exceptions.ApiRequestException;
import vn.itz.jpastudying.model.Student;
import vn.itz.jpastudying.model.StudentSubject;
import vn.itz.jpastudying.model.Subject;
import vn.itz.jpastudying.repository.StudentRepository;
import vn.itz.jpastudying.repository.StudentSubjectRepository;
import vn.itz.jpastudying.repository.SubjectRepository;

@Service
public class StudentDaoService {
  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private SubjectRepository subjectRepository;

  @Autowired
  private StudentSubjectRepository studentSubjectRepository;

  // Lay tat ca du lieu trong bang sinh vien
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

  // Lay mot sinh vien bat ky trong bang sinh vien
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

  // Them du lieu trong bang sinh vien
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

  // Xoa du lieu trong bang sinh vien
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

  // Sua du lieu trong bang sinh vien
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

  // Lay toan bo du lieu trong bang dang ky
  public ApiException<Optional<StudentSubject>> getAllRegistration(int id) {
    Optional<StudentSubject> res = studentSubjectRepository.findById(id);
    if (res.isPresent()) {
      return new ApiException<>(
          true,
          "All students",
          res,
          HttpStatus.OK
      );
    }
    return new ApiException<>(
        false,
        "No data available in the registration table",
        null,
        HttpStatus.NOT_FOUND
    );
  }

  public ApiException<StudentSubject> registerForCourses(int studentId, int subjectId) {
    Optional<Student> students = studentRepository.findById(studentId);
    Optional<Subject> subjects = subjectRepository.findById(subjectId);

    if (students.isPresent() && subjects.isPresent()){
      Student student = students.get();
      Subject subject = subjects.get();

      for (StudentSubject ss : student.getStudentSubjects()){
        if (ss.getSubject().getId() == subjectId && ss.getStudent().getId() == studentId){
          return new ApiException<>(
              false,
              "This subject already exists",
              null,
              HttpStatus.CONFLICT
          );
        }
      }

      StudentSubject studentSubject = new StudentSubject(student, subject);
      studentSubjectRepository.save(studentSubject);
      return new ApiException<>(
          true,
          "Successfully registered for the course",
          studentSubject,
          HttpStatus.OK
      );
    }
    return new ApiException<>(
        false,
        "Student or subject not found",
        null,
        HttpStatus.NOT_FOUND
    );
  }
}
