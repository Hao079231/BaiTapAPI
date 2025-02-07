package vn.itz.jpastudying.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

@Data
public class SubjectRegistrationCriteria {
  private Integer studentId;

  public Specification<SubjectRegistration> getCriteria() {
    return new Specification<SubjectRegistration>() {
      @Override
      public Predicate toPredicate(Root<SubjectRegistration> root,
          CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (studentId != null) {
          predicates.add(criteriaBuilder.equal(root.get("student").get("id"), studentId));
        }
        criteriaQuery.select(root.get("subject").get("name"));
        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
      }
    };
  }
}
