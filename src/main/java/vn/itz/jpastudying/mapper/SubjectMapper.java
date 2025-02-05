package vn.itz.jpastudying.mapper;

import java.util.List;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import vn.itz.jpastudying.Dto.request.SubjectCreateRequestDto;
import vn.itz.jpastudying.Dto.request.SubjectUpdateRequestDto;
import vn.itz.jpastudying.Dto.response.SubjectResponseDto;
import vn.itz.jpastudying.model.Subject;

@Mapper(componentModel = "spring")
public interface SubjectMapper {
  @Mapping(source = "subjectName", target = "name")
  @Mapping(source = "subjectCode", target = "code")
  Subject convertToSubject(SubjectCreateRequestDto requestDto);

  @Mapping(source = "subject_id", target = "subjectId")
  @Mapping(source = "name", target = "subjectName")
  @Mapping(source = "code", target = "subjectCode")
  SubjectResponseDto convertToSubjectResponse(Subject subject);

  @IterableMapping(elementTargetType = SubjectResponseDto.class)
  List<SubjectResponseDto> convertToListStudentResponse(List<Subject> subjects);

  @Mapping(source = "subjectName", target = "name")
  @Mapping(source = "subjectCode", target = "code")
  void updateStudent(@MappingTarget Subject subject, SubjectUpdateRequestDto subjectDto);
}
