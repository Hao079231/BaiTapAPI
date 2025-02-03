package vn.itz.jpastudying.service;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import vn.itz.jpastudying.exceptions.ApiException;
import vn.itz.jpastudying.exceptions.ApiRequestException;
import vn.itz.jpastudying.model.Subject;
import vn.itz.jpastudying.repository.SubjectRepository;

@Service
public class SubjectDaoService {
  @Autowired
  private SubjectRepository subjectRepository;

  public ApiException<List<Subject>> getAllSubject() {
    List<Subject> subjects = subjectRepository.findAll();
    if (subjects.isEmpty()) {
      return new ApiException<>(
          false,
          "Danh sach khoa hoc trong",
          null,
          HttpStatus.NOT_FOUND
      );
    }

    return new ApiException<>(
        true,
        "Danh sach cac khoa hoc",
        subjects,
        HttpStatus.OK
    );
  }

  public ApiException<Subject> findSubjectById(int id) {
    Subject subject = subjectRepository.findById(id).orElseThrow(() ->
        new ApiRequestException("Khoa hoc khong ton tai", HttpStatus.NOT_FOUND));

    return new ApiException<>(
        true,
        "Tim thay khoa hoc",
        subject,
        HttpStatus.OK
    );
  }

  public ApiException<Subject> createSubject(@Valid Subject subject) {
    if (subject.getName() == null || subject.getName().isEmpty() || subject.getCode() == null || subject.getCode().isEmpty()) {
      return new ApiException<>(
          false,
          "Them du lieu khoa hoc that bai",
          subject,
          HttpStatus.BAD_REQUEST
      );
    }
    Subject savedSubject = subjectRepository.save(subject);
    return new ApiException<>(
        true,
        "Them du lieu khoa hoc thanh cong",
        savedSubject,
        HttpStatus.CREATED
    );
  }

  public ApiException<Subject> deleteSubject(int id) {
    Subject subject = subjectRepository.findById(id).orElseThrow(() ->
        new ApiRequestException("Khoa hoc khong ton tai", HttpStatus.NOT_FOUND));

    subjectRepository.delete(subject);
    return new ApiException<>(
        true,
        "Xoa khoa hoc thanh cong",
        subject,
        HttpStatus.OK
    );
  }

  public ApiException<Subject> updateSubject(int id, @Valid Subject subject) {
    if (subjectRepository.findById(id).isEmpty()){
      return new ApiException<>(
          false,
          "Khoa hoc khong ton tai",
          subject,
          HttpStatus.NOT_FOUND);
    }

    if (subject.getName().isEmpty() || subject.getName() == null || subject.getCode() == null || subject.getCode().isEmpty()) {
      return new ApiException<>(
          false,
          "Khong the cap nhat khoa hoc",
          subject,
          HttpStatus.BAD_REQUEST
      );
    }
    Subject updatedSubject = subjectRepository.save(subject);
    return new ApiException<>(
        true,
        "Cap nhat khoa hoc thanh cong",
        updatedSubject,
        HttpStatus.OK
    );
  }
}
