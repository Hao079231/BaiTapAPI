package vn.itz.jpastudying.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import vn.itz.jpastudying.Dto.ShowPagedResults;
import vn.itz.jpastudying.Dto.request.StudentCreateRequestDto;
import vn.itz.jpastudying.Dto.request.StudentUpdateRequestDto;
import vn.itz.jpastudying.Dto.response.StudentResponseDto;
import vn.itz.jpastudying.Dto.response.SubjectRegistrationResponse;
import vn.itz.jpastudying.exceptions.DuplicateEntityException;
import vn.itz.jpastudying.exceptions.ResourceNotFound;
import vn.itz.jpastudying.mapper.StudentMapper;
import vn.itz.jpastudying.mapper.SubjectRegistrationMapper;
import vn.itz.jpastudying.model.Student;
import vn.itz.jpastudying.model.StudentCriteria;
import vn.itz.jpastudying.model.Subject;
import vn.itz.jpastudying.model.SubjectRegistration;
import vn.itz.jpastudying.model.SubjectRegistrationCriteria;
import vn.itz.jpastudying.repository.StudentRepository;
import vn.itz.jpastudying.repository.SubjectRegistrationRepository;
import vn.itz.jpastudying.repository.SubjectRepository;
@Service
public class StudentDaoService {
  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private SubjectRepository subjectRepository;

  @Autowired
  SubjectRegistrationRepository subjectRegistrationRepository;

  @Autowired
  private StudentMapper studentMapper;

  @Autowired
  private SubjectRegistrationMapper subjectRegistrationMapper;

  // Lay tat ca du lieu trong bang sinh vien
  public List<StudentResponseDto> findAllStudents() {
    return studentMapper.convertToListStudentResponse(studentRepository.findAll());
  }

  // Lay mot sinh vien bat ky trong bang sinh vien
  public StudentResponseDto findStudentById(int id) {
    return studentMapper.convertToStudentResponse(studentRepository.findById(id).orElseThrow(() ->
        new ResourceNotFound("Sinh vien khong ton tai", HttpStatus.NOT_FOUND)));
  }

  // Them du lieu trong bang sinh vien
  public StudentResponseDto createStudent(StudentCreateRequestDto student) {
    if (studentRepository.existsByUsername(student.getUserName()))
      throw new DuplicateEntityException("Username nay da ton tai");
    Student newStudent = studentMapper.convertToStudent(student);
    return studentMapper.convertToStudentResponse(studentRepository.save(newStudent));
  }

  // Xoa du lieu trong bang sinh vien
  public void deleteStudent(int id) {
    Student student = studentRepository.findById(id).orElseThrow(
        () -> new ResourceNotFound("Sinh vien nay khong ton tai", HttpStatus.NOT_FOUND));
    studentRepository.deleteById(id);
  }

  // Sua du lieu trong bang sinh vien
  public StudentResponseDto updateStudent(int id, StudentUpdateRequestDto newStudent) {
    Student oldStudent = studentRepository.findById(id).
        orElseThrow(() -> new ResourceNotFound("Sinh vien nay khong ton tai", HttpStatus.NOT_FOUND));
    if (studentRepository.existsByUsername(newStudent.getUserName()))
      throw new DuplicateEntityException("Username nay da ton tai");
    studentMapper.updateStudent(oldStudent, newStudent);


    return studentMapper.convertToStudentResponse(studentRepository.save(oldStudent));
  }

  //Lay danh sach khoa hoc ma sinh vien dang ky - ManyToOne, OneToMany
  public List<String> getEnrolledSubjects(int studentId) {
    Student student = studentRepository.findById(studentId).orElseThrow(() ->
        new ResourceNotFound("Khong tim thay sinh vien", HttpStatus.NOT_FOUND));

    return student.getRegistrations().stream()
        .map(registration -> registration.getSubject().getName())
        .collect(Collectors.toList());
  }

  // Dang ky mot khoa hoc cho mot sinh vien - ManyToOne, OneToMany
  public Student enrollSubject(int studentId, int subjectId){
    Student student = studentRepository.findById(studentId)
        .orElseThrow(() -> new ResourceNotFound("Sinh viên không tồn tại", HttpStatus.NOT_FOUND));

    Subject subject = subjectRepository.findById(subjectId)
        .orElseThrow(() -> new ResourceNotFound("Khóa học không tồn tại", HttpStatus.NOT_FOUND));

    if (subjectRegistrationRepository.existsByStudentIdAndSubjectId(studentId, subjectId)) {
      throw new DuplicateEntityException("Sinh viên đã đăng ký môn học này");
    }

    SubjectRegistration registration = new SubjectRegistration();
    registration.setStudent(student);
    registration.setSubject(subject);
    registration.setDateRegister(new Date());
    registration.setStatus(SubjectRegistration.Status.PENDING);
    subjectRegistrationRepository.save(registration);
    return student;
  }

  // Xoa mot khoa hoc da dang ky - ManyToOne, OneToMany
  @Transactional
  public Student removeSubject(int studentId, int subjectId) {

    subjectRegistrationRepository.deleteByStudentIdAndSubjectId(studentId, subjectId);
    return studentRepository.findById(studentId).orElse(null);
  }

  // Loc va phan trang cho sinh vien
  public ShowPagedResults<StudentResponseDto> getFilteredStudents(StudentCriteria studentCriteria, Pageable pageable) {
    Page<Student> students = studentRepository.findAll(studentCriteria.getCriteria(), pageable);

    List<StudentResponseDto> studentDtos = studentMapper.convertToListStudentResponse(students.getContent());

    return new ShowPagedResults<>(studentDtos, students.getTotalElements(), students.getTotalPages());
  }


  // Loc va phan trang danh sach mon hoc ma mot sinh vien da dang ky
  public ShowPagedResults<String> getFilteredSubjects(SubjectRegistrationCriteria criteria, Pageable pageable) {
    Page<SubjectRegistration> registrations = subjectRegistrationRepository.findAll(criteria.getCriteria(), pageable);

    List<String> subjectNames = subjectRegistrationMapper.convertToListSubjectNames(registrations.getContent());

    return new ShowPagedResults<>(subjectNames, registrations.getTotalElements(), registrations.getTotalPages());
  }
}
