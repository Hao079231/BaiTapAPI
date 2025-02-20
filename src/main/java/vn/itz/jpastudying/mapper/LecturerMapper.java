package vn.itz.jpastudying.mapper;

import java.util.List;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import vn.itz.jpastudying.Dto.LecturerDto;
import vn.itz.jpastudying.form.lecturer.LecturerCreateForm;
import vn.itz.jpastudying.model.Lecturer;

@Mapper(componentModel = "spring")
public interface LecturerMapper {
  @Mappings({
      @Mapping(source = "userNameValue", target = "user.username"),
      @Mapping(source = "fullNameValue", target = "user.fullname"),
      @Mapping(source = "passWordValue", target = "user.password"),
      @Mapping(source = "genderValue", target = "user.gender"),
      @Mapping(source = "careerValue", target = "career"),
  })
  Lecturer convertToLecturer(LecturerCreateForm requestDto);

  @Mappings({
      @Mapping(source = "id", target = "lecturerId"),
      @Mapping(source = "user.username", target = "lecturerUserName"),
      @Mapping(source = "user.fullname", target = "lecturerFullName"),
      @Mapping(source = "user.gender", target = "lecturerGender"),
      @Mapping(source = "career", target = "lecturerCareer")
  })
  @Named("mapLecturerDto")
  LecturerDto convertToLecturerResponse(Lecturer lecturer);

  @IterableMapping(elementTargetType = LecturerDto.class, qualifiedByName = "mapLecturerDto")
  List<LecturerDto> convertToListStudentResponse(List<Lecturer> lecturers);
}
