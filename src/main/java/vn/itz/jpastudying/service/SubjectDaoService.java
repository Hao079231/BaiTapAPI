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
          "Subjects are empty",
          null,
          HttpStatus.NOT_FOUND
      );
    }

    return new ApiException<>(
        true,
        "All subjects",
        subjects,
        HttpStatus.OK
    );
  }

  public ApiException<Subject> findSubjectById(int id) {
    Subject subject = subjectRepository.findById(id).orElseThrow(() ->
        new ApiRequestException("Subject doesn't exist", HttpStatus.NOT_FOUND));

    return new ApiException<>(
        true,
        "Found subject",
        subject,
        HttpStatus.OK
    );
  }

  public ApiException<Subject> createSubject(@Valid Subject subject) {
    if (subject.getName() == null || subject.getName().isEmpty() || subject.getCode() == null || subject.getCode().isEmpty()) {
      return new ApiException<>(
          false,
          "Subject creation failed",
          subject,
          HttpStatus.BAD_REQUEST
      );
    }
    Subject savedSubject = subjectRepository.save(subject);
    return new ApiException<>(
        true,
        "Subject created successfully",
        savedSubject,
        HttpStatus.CREATED
    );
  }

  public ApiException<Subject> deleteSubject(int id) {
    Subject subject = subjectRepository.findById(id).orElseThrow(() ->
        new ApiRequestException("Subject doesn't exist", HttpStatus.NOT_FOUND));

    subjectRepository.delete(subject);
    return new ApiException<>(
        true,
        "Subject deleted successfully",
        subject,
        HttpStatus.OK
    );
  }

  public ApiException<Subject> updateSubject(int id, @Valid Subject subject) {
    if (subjectRepository.findById(id).isEmpty()){
      return new ApiException<>(
          false,
          "Subject doesn't exist",
          subject,
          HttpStatus.NOT_FOUND);
    }

    if (subject.getName().isEmpty() || subject.getName() == null || subject.getCode() == null || subject.getCode().isEmpty()) {
      return new ApiException<>(
          false,
          "Subject update failed",
          subject,
          HttpStatus.BAD_REQUEST
      );
    }
    Subject updatedSubject = subjectRepository.save(subject);
    return new ApiException<>(
        true,
        "Subject updated successfully",
        updatedSubject,
        HttpStatus.OK
    );
  }
}
