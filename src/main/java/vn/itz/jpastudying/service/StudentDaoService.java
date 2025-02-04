package vn.itz.jpastudying.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import vn.itz.jpastudying.exceptions.DuplicateEntityException;
import vn.itz.jpastudying.exceptions.ResourceNotFound;
import vn.itz.jpastudying.model.Student;
import vn.itz.jpastudying.model.Subject;
import vn.itz.jpastudying.repository.StudentRepository;
import vn.itz.jpastudying.repository.SubjectRepository;

@Service
public class StudentDaoService {
  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private SubjectRepository subjectRepository;

  // Lay tat ca du lieu trong bang sinh vien
  public List<Student> findAllStudents() {
    return studentRepository.findAll();
  }

  // Lay mot sinh vien bat ky trong bang sinh vien
  public Student findStudentById(int id) {
    return studentRepository.findById(id).orElseThrow(() ->
        new ResourceNotFound("Sinh vien khong ton tai", HttpStatus.NOT_FOUND));
  }

  // Them du lieu trong bang sinh vien
  public Student createStudent(Student student) {
    if (studentRepository.existsByUsername(student.getUsername()))
      throw new DuplicateEntityException("Username nay da ton tai");
    return studentRepository.save(student);
  }

  // Xoa du lieu trong bang sinh vien
  public void deleteStudent(int id) {
    Student student = studentRepository.findById(id).orElseThrow(
        () -> new ResourceNotFound("Sinh vien nay khong ton tai", HttpStatus.NOT_FOUND));
    studentRepository.deleteById(id);
  }

  // Sua du lieu trong bang sinh vien
  public Student updateStudent(int id, Student newStudent) {
    Student oldStudent = studentRepository.findById(id).
        orElseThrow(() -> new ResourceNotFound("Sinh vien nay khong ton tai", HttpStatus.NOT_FOUND));
    if (studentRepository.existsByUsername(newStudent.getUsername()))
      throw new DuplicateEntityException("Username nay da ton tai");
    oldStudent.setUsername(newStudent.getUsername());
    oldStudent.setFullname(newStudent.getFullname());
    oldStudent.setBirthday(newStudent.getBirthday());
    oldStudent.setPassword(newStudent.getPassword());

    return studentRepository.save(oldStudent);
  }

  //Lay danh sach khoa hoc ma sinh vien dang ky
  public List<Subject> getEnrolledSubjects(int studentId) {
    Student student = studentRepository.findById(studentId).orElseThrow(() ->
        new ResourceNotFound("Khong tim thay sinh vien", HttpStatus.NOT_FOUND));
    return student.getSubjects();
  }

  // Dang ky mot khoa hoc cho mot sinh vien
  public Student enrollSubject(int studentId, int subjectId){
    Student student = studentRepository.findById(studentId)
        .orElseThrow(() -> new ResourceNotFound("Sinh vien khong ton tai", HttpStatus.NOT_FOUND));
    Subject subject = subjectRepository.findById(subjectId)
        .orElseThrow(() -> new ResourceNotFound("Khoa hoc khong ton tai", HttpStatus.NOT_FOUND));

    if (!student.getSubjects().contains(subject)) {
      student.getSubjects().add(subject);
      studentRepository.save(student);
    }
    return student;
  }

  // Xoa mot khoa hoc da dang ky
  public Student removeSubject(int studentId, int subjectId) {
    Student student = studentRepository.findById(studentId)
        .orElseThrow(() -> new ResourceNotFound("Sinh vien khong ton tai", HttpStatus.NOT_FOUND));
    Subject subject = subjectRepository.findById(subjectId)
        .orElseThrow(() -> new ResourceNotFound("Khoa hoc khong ton tai", HttpStatus.NOT_FOUND));

    if (student.getSubjects().contains(subject)) {
      student.getSubjects().remove(subject);
      studentRepository.save(student);
    }
    return student;
  }
}
