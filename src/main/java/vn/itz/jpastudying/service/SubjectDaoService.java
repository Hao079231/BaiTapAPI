package vn.itz.jpastudying.service;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import vn.itz.jpastudying.Dto.ApiMessageDto;
import vn.itz.jpastudying.exceptions.DuplicateEntityException;
import vn.itz.jpastudying.exceptions.ResourceNotFound;
import vn.itz.jpastudying.model.Student;
import vn.itz.jpastudying.model.Subject;
import vn.itz.jpastudying.repository.StudentRepository;
import vn.itz.jpastudying.repository.SubjectRepository;

@Service
public class SubjectDaoService {
  @Autowired
  private SubjectRepository subjectRepository;

  // Lay danh sach tat ca khoa hoc
  public List<Subject> getAllSubject() {
    return subjectRepository.findAll();
  }

  // Lay thong tin khoa hoc bang id
  public Subject findSubjectById(int id) {
    return subjectRepository.findById(id).orElseThrow(()->
        new ResourceNotFound("Khoa hoc nay khong ton tai", HttpStatus.NOT_FOUND));
  }

  // Them du lieu khoa hoc
  public Subject createSubject(Subject subject) {
    if (subjectRepository.existsByName(subject.getName()))
      throw new DuplicateEntityException("Ten khoa hoc da ton tai");
    if (subjectRepository.existsByCode(subject.getCode()))
      throw new DuplicateEntityException("Ma khoa hoc da ton tai");
    return subjectRepository.save(subject);
  }

  // Xoa thong tin khoa hoc
  public void deleteSubject(int id) {
    Subject subject = subjectRepository.findById(id).orElseThrow(() ->
        new ResourceNotFound("Khong tim thay khoa hoc", HttpStatus.NOT_FOUND));
    subjectRepository.deleteById(id);
  }

  // Cap nhat thong tin khoa hoc
  public Subject updateSubject(int id, Subject newSubject) {
    Subject oldSubject = subjectRepository.findById(id).orElseThrow(()
    -> new ResourceNotFound("Khong tim thay khoa hoc", HttpStatus.NOT_FOUND));
    if (subjectRepository.existsByName(newSubject.getName()))
      throw new DuplicateEntityException("Ten khoa hoc da ton tai");
    if (subjectRepository.existsByCode(newSubject.getCode()))
      throw new DuplicateEntityException("Ma khoa hoc da ton tai");
    oldSubject.setName(newSubject.getName());
    oldSubject.setCode(newSubject.getCode());
    return subjectRepository.save(oldSubject);
  }

}
