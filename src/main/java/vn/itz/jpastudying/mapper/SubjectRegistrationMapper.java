package vn.itz.jpastudying.mapper;

import java.util.List;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import vn.itz.jpastudying.Dto.SubjectRegistrationDto;
import vn.itz.jpastudying.form.registration.SubjectRegistrationForm;
import vn.itz.jpastudying.model.SubjectRegistration;

@Mapper(componentModel = "spring", uses = {StudentMapper.class, SubjectMapper.class})
public interface SubjectRegistrationMapper {

  @Mappings({
      @Mapping(source = "studentResponse", target = "student"),
      @Mapping(source = "subjectResponse", target = "subject"),
      @Mapping(source = "dateRegisterResponse", target = "dateRegister"),
      @Mapping(source = "statusResponse", target = "status"),
      @Mapping(source = "studyResultResponse", target = "studyResult")
  })
  @Named("mapSubjectRegistration")
  SubjectRegistration convertToSubjectRegistration(SubjectRegistrationForm subjectRegistrationForm);

  @Mappings({
      @Mapping(source = "id", target = "idResponse"),
      @Mapping(source = "student", target = "studentResponse", qualifiedByName = "mapStudentDto"),
      @Mapping(source = "subject", target = "subjectResponse", qualifiedByName = "mapSubjectDto"),
      @Mapping(source = "dateRegister", target = "dateRegisterResponse"),
      @Mapping(source = "status", target = "statusResponse"),
      @Mapping(source = "studyResult", target = "studyResultResponse")
  })
  @Named("subjectRegistrationDto")
  SubjectRegistrationDto convertToSubjectRegistrationResponse(
      SubjectRegistration subjectRegistration);

  @IterableMapping(elementTargetType = SubjectRegistrationDto.class,  qualifiedByName = "subjectRegistrationDto")
  List<SubjectRegistrationDto> convertToListSubjectRegistrationResponse(List<SubjectRegistration> subjectRegistrations);

  @Named("mapToSubjectName")
  default String mapToSubjectName(SubjectRegistration registration) {
    return registration.getSubject().getName();
  }

  @IterableMapping(qualifiedByName = "mapToSubjectName")
  List<String> convertToListSubjectNames(List<SubjectRegistration> registrations);
}
