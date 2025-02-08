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
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date registeredAfter;

  public static Specification<Subject> getSubjectsByStudentCriteria(Integer studentId, Date registeredAfter) {
    return (Root<Subject> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
      Join<Subject, SubjectRegistration> registrationJoin = root.join("registrations");
      Join<SubjectRegistration, Student> studentJoin = registrationJoin.join("student");


      List<Predicate> predicates = new ArrayList<>();

      if (studentId != null) {
        predicates.add(cb.equal(studentJoin.get("id"), studentId));
      }

      if (registeredAfter != null) {
        predicates.add(cb.greaterThan(registrationJoin.get("dateRegister"), registeredAfter));
      }

      return cb.and(predicates.toArray(new Predicate[0]));
    };
  }

  public static Specification<Student> getStudentsBySubjectCriteria(Integer subjectId, Date registeredAfter) {
    return (Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
      Join<Student, SubjectRegistration> registrationJoin = root.join("registrations");
      Join<SubjectRegistration, Subject> subjectJoin = registrationJoin.join("subject");


      List<Predicate> predicates = new ArrayList<>();

      if (subjectId != null) {
        predicates.add(cb.equal(subjectJoin.get("id"), subjectId));
      }

      if (registeredAfter != null) {
        predicates.add(cb.greaterThan(registrationJoin.get("dateRegister"), registeredAfter));
      }

      return cb.and(predicates.toArray(new Predicate[0]));
    };
  }
}
