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
public class StudentCriteria {
  private Long id;
  private String username;
  private String fullname;
  private Integer sortId;

  public Specification<Student> getCriteria(){
    return new Specification<Student>() {
      @Override
      public Predicate toPredicate(Root<Student> root,
          CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (getId() != null){
          predicates.add(criteriaBuilder.equal(root.get("id"), getId()));
        }
        if (username != null && !username.isEmpty()) {
          predicates.add(criteriaBuilder.like(root.get("username"), "%" + username.toLowerCase() + "%"));
        }

        if (fullname != null && !fullname.isEmpty()) {
          predicates.add(criteriaBuilder.like(root.get("fullname"), "%" + fullname.toLowerCase() + "%"));
        }
        if (sortId != null) {
          if (sortId == 1) {
            criteriaQuery.orderBy(criteriaBuilder.asc(root.get("id")));
          } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));
          }
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
      }
    };
  }
}
