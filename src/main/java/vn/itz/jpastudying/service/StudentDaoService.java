package vn.itz.jpastudying.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
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

  @Autowired
  private EntityManager entityManager;

  // Lay tat ca du lieu trong bang sinh vien
  public ApiException<List<Student>> findAllStudents() {
    List<Student> students = studentRepository.findAll();
    if (students.isEmpty()) {
      return new ApiException<>(
          false,
          "Bang sinh vien dang trong",
          null,
          HttpStatus.NOT_FOUND
      );
    }

    return new ApiException<>(
        true,
        "Danh sach tat ca sinh vien",
        students,
        HttpStatus.OK
    );
  }

  // Lay mot sinh vien bat ky trong bang sinh vien
  public ApiException<Student> findStudentById(int id) {
    Student student = studentRepository.findById(id).orElseThrow(() ->
        new ApiRequestException("Sinh vien khong ton tai", HttpStatus.NOT_FOUND));

    return new ApiException<>(
        true,
        "Tim thay sinh vien",
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
          "Them du lieu sinh vien that bai",
          student,
          HttpStatus.BAD_REQUEST
      );
    }
    Student savedStudent = studentRepository.save(student);
    return new ApiException<>(
        true,
        "Them du lieu sinh vien thanh cong",
        savedStudent,
        HttpStatus.CREATED
    );
  }

  // Xoa du lieu trong bang sinh vien
  public ApiException<Student> deleteStudent(int id) {
    Student student = studentRepository.findById(id).orElseThrow(() ->
        new ApiRequestException("Sinh vien khong ton tai", HttpStatus.NOT_FOUND));

    studentRepository.delete(student);
    return new ApiException<>(
        true,
        "Sinh vien duoc xoa thanh cong",
        student,
        HttpStatus.OK
    );
  }

  // Sua du lieu trong bang sinh vien
  public ApiException<Student> updateStudent(int id, Student student) {
    if (studentRepository.findById(id).isEmpty()){
      return new ApiException<>(
          false,
          "Sinh vien khong ton tai",
          student,
          HttpStatus.NOT_FOUND);
    }

    if (student.getUsername() == null || student.getUsername().isEmpty() || student.getFullname() == null || student.getFullname().isEmpty() || student.getBirthday() == null  || student.getPassword() == null || student.getPassword().isEmpty()) {
      return new ApiException<>(
          false,
          "Du lieu sinh vien duoc sua that bai",
          student,
          HttpStatus.BAD_REQUEST
      );
    }
    Student updatedStudent = studentRepository.save(student);
    return new ApiException<>(
        true,
        "Du lieu sinh vien duoc sua thanh cong",
        updatedStudent,
        HttpStatus.OK
    );
  }

  // Lay toan bo du lieu trong bang dang ky
  public ApiException<List<StudentSubject>> getAllRegistration() {
    List<StudentSubject> res = studentSubjectRepository.findAll();
    if (res.isEmpty()) {
      return new ApiException<>(
          false,
          "Khong co du trong bang dang ky",
          null,
          HttpStatus.NOT_FOUND
      );
    }
    return new ApiException<>(
        true,
        "Danh sach tat ca cac dang ky",
        res,
        HttpStatus.OK
    );
  }

  public ApiException<List<Subject>> getSubjectsByStudenId(int studentId) {
    Optional<Student> students = studentRepository.findById(studentId);
    if (!students.isPresent()){
      return new ApiException<>(
        false,
        "Sinh vien da tim khong ton tai",
        null,
        HttpStatus.NOT_FOUND
      );
    }
    Student student = students.get();
    List<Subject> subjects = new ArrayList<>();
    for (StudentSubject ss : student.getStudentSubjects()){
      subjects.add(ss.getSubject());
    }
    return new ApiException<>(
        true,
        "Danh sach khoa hoc ma sinh vien da dang ky",
        subjects,
        HttpStatus.OK
    );
  }

  public ApiException<StudentSubject> registerForCourses(int studentId, int subjectId) {
    Optional<Student> students = studentRepository.findById(studentId);
    Optional<Subject> subjects = subjectRepository.findById(subjectId);

    if (students.isPresent() && subjects.isPresent()){
      Student student = students.get();
      Subject subject = subjects.get();

      for (StudentSubject ss : student.getStudentSubjects()){
        if (ss.getSubject().getSubject_id() == subjectId && ss.getStudent().getStudent_id() == studentId){
          return new ApiException<>(
              false,
              "Mon hoc nay da duoc dang ky",
              null,
              HttpStatus.CONFLICT
          );
        }
      }

      StudentSubject studentSubject = new StudentSubject(student, subject);
      studentSubjectRepository.save(studentSubject);
      return new ApiException<>(
          true,
          "Dang ky thanh cong",
          studentSubject,
          HttpStatus.OK
      );
    }
    return new ApiException<>(
        false,
        "Khong tim thay sinh vien hoac khoa hoc",
        null,
        HttpStatus.NOT_FOUND
    );
  }

  public ApiException<StudentSubject> deleteForCourses(int studentId, int subjectId) {
    Student student = studentRepository.findById(studentId).orElseThrow(() ->
        new ApiRequestException("Sinh vien khong ton tai", HttpStatus.NOT_FOUND));

    Subject subject = subjectRepository.findById(subjectId).orElseThrow(() ->
        new ApiRequestException("Khoa hoc khong ton tai", HttpStatus.NOT_FOUND));
//
//    boolean removed = student.getStudentSubjects()
//        .removeIf(ss -> ss.getSubject().getSubject_id() == subjectId);
//
//    if (!removed) {
//      return new ApiException<>(
//          false,
//          "Sinh vien chua dang ky mon hoc nay",
//          null,
//          HttpStatus.NOT_FOUND);
//    }
//
//    return new ApiException<>(
//        true,
//        "Xoa dang ky thanh cong",
//        null,
//        HttpStatus.OK);
    for (StudentSubject ss : student.getStudentSubjects()){
      if (ss.getSubject().getSubject_id() == subjectId){
        return new ApiException<>(
          true,
          "Xoa dang ky thanh cong",
          ss,
          HttpStatus.OK);
      }
    }
    return new ApiException<>(
          false,
          "Sinh vien chua dang ky mon hoc nay",
          null,
          HttpStatus.NOT_FOUND);
  }
}
