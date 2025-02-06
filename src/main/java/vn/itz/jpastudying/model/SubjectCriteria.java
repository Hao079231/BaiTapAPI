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
public class SubjectCriteria {
  private Long id;
  private String code;
  private String name;
  private Integer sortId;

  public Specification<Subject> getCriteria(){
    return new Specification<Subject>() {
      @Override
      public Predicate toPredicate(Root<Subject> root, CriteriaQuery<?> criteriaQuery,
          CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (getId() != null){
          predicates.add(criteriaBuilder.equal(root.get("id"), getId()));
        }
        if (code != null && !code.isEmpty()) {
          predicates.add(criteriaBuilder.like(root.get("code"), "%" + code.toLowerCase() + "%"));
        }

        if (name != null && !name.isEmpty()) {
          predicates.add(criteriaBuilder.like(root.get("name"), "%" + name.toLowerCase() + "%"));
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
