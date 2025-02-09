package vn.itz.jpastudying.model.criteria;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import vn.itz.jpastudying.model.Student;
import vn.itz.jpastudying.model.Subject;
import vn.itz.jpastudying.model.SubjectRegistration;

@Data
public class SubjectRegistrationCriteria {
  private Integer studentId;
  private Integer subjectId;
  @DateTimeFormat(pattern = "dd-MM-yyyy")
  private Date registeredAfter;

  public Specification<SubjectRegistration> getSubjectsByStudentCriteria() {
    return (Root<SubjectRegistration> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
      Join<SubjectRegistration, Student> studentJoin = root.join("student");
      Join<SubjectRegistration, Subject> subjectJoin = root.join("subject");

      List<Predicate> predicates = new ArrayList<>();

      if (studentId != null) {
        predicates.add(cb.equal(studentJoin.get("id"), studentId));
      }

      if (registeredAfter != null) {
        predicates.add(cb.greaterThan(root.get("dateRegister"), registeredAfter));
      }

      return cb.and(predicates.toArray(new Predicate[0]));
    };
  }

  public Specification<SubjectRegistration> getStudentsBySubjectCriteria() {
    return (Root<SubjectRegistration> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
      Join<SubjectRegistration, Student> studentJoin = root.join("student");
      Join<SubjectRegistration, Subject> subjectJoin = root.join("subject");

      List<Predicate> predicates = new ArrayList<>();

      if (subjectId != null) {
        predicates.add(cb.equal(subjectJoin.get("id"), subjectId));
      }

      if (registeredAfter != null) {
        predicates.add(cb.greaterThan(root.get("dateRegister"), registeredAfter));
      }

      return cb.and(predicates.toArray(new Predicate[0]));
    };
  }
}
