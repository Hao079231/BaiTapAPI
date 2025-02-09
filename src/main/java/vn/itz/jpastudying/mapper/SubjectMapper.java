package vn.itz.jpastudying.mapper;

import java.util.List;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import vn.itz.jpastudying.Dto.response.SubjectResponseDto;
import vn.itz.jpastudying.form.subject.SubjectCreateForm;
import vn.itz.jpastudying.form.subject.SubjectUpdateForm;
import vn.itz.jpastudying.model.Subject;

@Mapper(componentModel = "spring")
public interface SubjectMapper {
  @Mappings({
      @Mapping(source = "subjectNameValue", target = "name"),
      @Mapping(source = "subjectCodeValue", target = "code"),
      @Mapping(source = "subjectStatusValue", target = "statusSubject")
  })
  Subject convertToSubject(SubjectCreateForm requestDto);

  @Mappings({
      @Mapping(source = "id", target = "subjectIdValue"),
      @Mapping(source = "name", target = "subjectNameValue"),
      @Mapping(source = "code", target = "subjectCodeValue"),
      @Mapping(source = "statusSubject", target = "subjectStatusValue")

  })
  @Named("mapSubjectDto")
  SubjectResponseDto convertToSubjectResponse(Subject subject);

  @IterableMapping(elementTargetType = SubjectResponseDto.class, qualifiedByName = "mapSubjectDto")
  List<SubjectResponseDto> convertToListSubjectResponse(List<Subject> subjects);

  @Mappings({
      @Mapping(source = "subjectNameValue", target = "name"),
      @Mapping(source = "subjectCodeValue", target = "code"),
      @Mapping(source = "subjectStatusValue", target = "statusSubject")

  })
  void updateSubject(@MappingTarget Subject subject, SubjectUpdateForm subjectDto);
}
