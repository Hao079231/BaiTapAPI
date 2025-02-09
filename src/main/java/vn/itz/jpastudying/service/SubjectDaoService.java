package vn.itz.jpastudying.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import vn.itz.jpastudying.Dto.ShowPagedResults;
import vn.itz.jpastudying.Dto.response.SubjectRegistrationResponse;
import vn.itz.jpastudying.Dto.response.SubjectResponseDto;
import vn.itz.jpastudying.exceptions.DuplicateEntityException;
import vn.itz.jpastudying.exceptions.ResourceNotFound;
import vn.itz.jpastudying.form.subject.SubjectCreateForm;
import vn.itz.jpastudying.form.subject.SubjectUpdateForm;
import vn.itz.jpastudying.mapper.SubjectMapper;
import vn.itz.jpastudying.mapper.SubjectRegistrationMapper;
import vn.itz.jpastudying.model.Subject;
import vn.itz.jpastudying.model.SubjectRegistration;
import vn.itz.jpastudying.model.SubjectRegistration.Status;
import vn.itz.jpastudying.model.criteria.SubjectCriteria;
import vn.itz.jpastudying.model.criteria.SubjectRegistrationCriteria;
import vn.itz.jpastudying.repository.SubjectRegistrationRepository;
import vn.itz.jpastudying.repository.SubjectRepository;

@Service
public class SubjectDaoService {
  @Autowired
  private SubjectRepository subjectRepository;

  @Autowired
  private SubjectMapper subjectMapper;

  @Autowired
  private SubjectRegistrationRepository subjectRegistrationRepository;

  @Autowired
  private SubjectRegistrationMapper subjectRegistrationMapper;

  // Lay danh sach tat ca khoa hoc
  public List<SubjectResponseDto> getAllSubject() {
    return subjectMapper.convertToListSubjectResponse(subjectRepository.findAll());
  }

  // Lay thong tin khoa hoc bang id
  public SubjectResponseDto findSubjectById(int id) {
    return subjectMapper.convertToSubjectResponse(subjectRepository.findById(id).orElseThrow(()->
        new ResourceNotFound("Khoa hoc nay khong ton tai", HttpStatus.NOT_FOUND)));
  }

  // Them du lieu khoa hoc
  public SubjectResponseDto createSubject(SubjectCreateForm subject) {
    if (subjectRepository.existsByName(subject.getSubjectNameValue()))
      throw new DuplicateEntityException("Ten khoa hoc da ton tai");
    if (subjectRepository.existsByCode(subject.getSubjectCodeValue()))
      throw new DuplicateEntityException("Ma khoa hoc da ton tai");
    Subject newSubject = subjectMapper.convertToSubject(subject);
    return subjectMapper.convertToSubjectResponse(subjectRepository.save(newSubject));
  }

  // Xoa thong tin khoa hoc
  public void deleteSubject(int id) {
    Subject subject = subjectRepository.findById(id).orElseThrow(() ->
        new ResourceNotFound("Khong tim thay khoa hoc", HttpStatus.NOT_FOUND));
    subjectRepository.deleteById(id);
  }

  // Cap nhat thong tin khoa hoc
  public SubjectResponseDto updateSubject(int id, SubjectUpdateForm newSubject) {
    Subject oldSubject = subjectRepository.findById(id).orElseThrow(()
    -> new ResourceNotFound("Khong tim thay khoa hoc", HttpStatus.NOT_FOUND));
    if (subjectRepository.existsByName(newSubject.getSubjectNameValue()))
      throw new DuplicateEntityException("Ten khoa hoc da ton tai");
    if (subjectRepository.existsByCode(newSubject.getSubjectCodeValue()))
      throw new DuplicateEntityException("Ma khoa hoc da ton tai");
    subjectMapper.updateSubject(oldSubject, newSubject);
    return subjectMapper.convertToSubjectResponse(subjectRepository.save(oldSubject));
  }

  public ShowPagedResults<SubjectResponseDto> getFilteredSubjects(SubjectCriteria subjectCriteria, Pageable pageable) {
    Page<Subject> subjects = subjectRepository.findAll(subjectCriteria.getCriteria(), pageable);

    List<SubjectResponseDto> subjectDtos = subjectMapper.convertToListSubjectResponse(subjects.getContent());

    return new ShowPagedResults<>(subjectDtos, subjects.getTotalElements(), subjects.getTotalPages());
  }

  // Lay danh sach cac khoa hoc dua id sinh vien va ngay nhap vao
  public ShowPagedResults<SubjectRegistrationResponse> getSubjectsByCriteria(SubjectRegistrationCriteria criteria, Pageable pageable) {

    Page<SubjectRegistration> subjectPage = subjectRegistrationRepository.findAll(
        criteria.getSubjectsByStudentCriteria(), pageable);

    List<SubjectRegistrationResponse> studentListDtos = subjectRegistrationMapper
        .convertToListSubjectRegistrationResponse(subjectPage.getContent());

    return new ShowPagedResults<>(studentListDtos, subjectPage.getTotalElements(), subjectPage.getTotalPages());
  }

  // Ham kiem tra sinh vien cac khoa hoc da hoan thanh khoa hoc hay chua, neu da hoan thanh thi chuyen done
  @Scheduled(cron = "0 0 0 * * ?")
//  @Scheduled(cron = "*/10 * * * * *")
  public void updateSubjectStatus() {
    List<Subject> subjects = subjectRepository.findAll();

    for (Subject subject : subjects) {
      List<SubjectRegistration> registrations = subjectRegistrationRepository.findBySubject(subject);

      // Kiem tat ca sinh vien cua mot khoa deu COMPLETE
      boolean allCompleted = registrations.stream()
          .allMatch(reg -> reg.getStatus() == Status.COMPLETE);

      if (allCompleted && !subject.isStatusSubject()) {
        subject.setStatusSubject(true);
        subjectRepository.save(subject);
        System.out.println("Cap nhat trang thai DONE cho khoa hoc: " + subject.getName());
      }
    }
  }
}
