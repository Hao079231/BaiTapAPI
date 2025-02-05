package vn.itz.jpastudying.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import vn.itz.jpastudying.Dto.request.SubjectCreateRequestDto;
import vn.itz.jpastudying.Dto.request.SubjectUpdateRequestDto;
import vn.itz.jpastudying.Dto.response.SubjectResponseDto;
import vn.itz.jpastudying.exceptions.DuplicateEntityException;
import vn.itz.jpastudying.exceptions.ResourceNotFound;
import vn.itz.jpastudying.mapper.SubjectMapper;
import vn.itz.jpastudying.model.Subject;
import vn.itz.jpastudying.repository.SubjectRepository;

@Service
public class SubjectDaoService {
  @Autowired
  private SubjectRepository subjectRepository;

  @Autowired
  private SubjectMapper subjectMapper;

  // Lay danh sach tat ca khoa hoc
  public List<SubjectResponseDto> getAllSubject() {
    return subjectMapper.convertToListStudentResponse(subjectRepository.findAll());
  }

  // Lay thong tin khoa hoc bang id
  public SubjectResponseDto findSubjectById(int id) {
    return subjectMapper.convertToSubjectResponse(subjectRepository.findById(id).orElseThrow(()->
        new ResourceNotFound("Khoa hoc nay khong ton tai", HttpStatus.NOT_FOUND)));
  }

  // Them du lieu khoa hoc
  public SubjectResponseDto createSubject(SubjectCreateRequestDto subject) {
    if (subjectRepository.existsByName(subject.getSubjectName()))
      throw new DuplicateEntityException("Ten khoa hoc da ton tai");
    if (subjectRepository.existsByCode(subject.getSubjectCode()))
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
  public SubjectResponseDto updateSubject(int id, SubjectUpdateRequestDto newSubject) {
    Subject oldSubject = subjectRepository.findById(id).orElseThrow(()
    -> new ResourceNotFound("Khong tim thay khoa hoc", HttpStatus.NOT_FOUND));
    if (subjectRepository.existsByName(newSubject.getSubjectName()))
      throw new DuplicateEntityException("Ten khoa hoc da ton tai");
    if (subjectRepository.existsByCode(newSubject.getSubjectCode()))
      throw new DuplicateEntityException("Ma khoa hoc da ton tai");
    subjectMapper.updateStudent(oldSubject, newSubject);
    return subjectMapper.convertToSubjectResponse(subjectRepository.save(oldSubject));
  }

}
