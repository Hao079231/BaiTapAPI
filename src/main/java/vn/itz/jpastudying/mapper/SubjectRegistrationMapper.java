package vn.itz.jpastudying.mapper;

import java.util.List;
import java.util.Set;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import vn.itz.jpastudying.Dto.response.SubjectRegistrationResponse;
import vn.itz.jpastudying.model.SubjectRegistration;

@Mapper(componentModel = "spring")
public interface SubjectRegistrationMapper {
  @Mapping(source = "student", target = "studentResponse")
  @Mapping(source = "subject", target = "subjectResponse")
  SubjectRegistrationResponse convertToSubjectRegistrationResponse(
      SubjectRegistration subjectRegistration);

  @IterableMapping(elementTargetType = SubjectRegistrationResponse.class)
  List<SubjectRegistrationResponse> convertToListSubjectRegistrationResponse(List<SubjectRegistration> subjectRegistrations);

  @Named("mapToSubjectName")
  default String mapToSubjectName(SubjectRegistration registration) {
    return registration.getSubject().getName();
  }

  @IterableMapping(qualifiedByName = "mapToSubjectName")
  List<String> convertToListSubjectNames(List<SubjectRegistration> registrations);
}
