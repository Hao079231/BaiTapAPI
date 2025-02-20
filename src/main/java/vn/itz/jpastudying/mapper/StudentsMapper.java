package vn.itz.jpastudying.mapper;

import java.util.List;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import vn.itz.jpastudying.Dto.StudentsDto;
import vn.itz.jpastudying.form.students.StudentsCreateForm;
import vn.itz.jpastudying.model.Students;

@Mapper(componentModel = "spring")
public interface StudentsMapper {
  @Mappings({
      @Mapping(source = "userNameValue", target = "user.username"),
      @Mapping(source = "fullNameValue", target = "user.fullname"),
      @Mapping(source = "birthDateValue", target = "birthday"),
      @Mapping(source = "passWordValue", target = "user.password"),
      @Mapping(source = "genderValue", target = "user.gender"),
      @Mapping(source = "mssvValue", target = "mssv")
  })
  Students convertToStudent(StudentsCreateForm requestDto);

  @Mappings({
      @Mapping(source = "id", target = "studentIdValue"),
      @Mapping(source = "user.username", target = "userNameValue"),
      @Mapping(source = "user.fullname", target = "fullNameValue"),
      @Mapping(source = "birthday", target = "birthDateValue"),
      @Mapping(source = "user.gender", target = "genderValue"),
      @Mapping(source = "mssv", target = "mssvValue")

  })
  @Named("mapStudentsDto")
  StudentsDto convertToStudentResponse(Students student);

  @IterableMapping(elementTargetType = StudentsDto.class, qualifiedByName = "mapStudentsDto")
  List<StudentsDto> convertToListStudentResponse(List<Students> students);
}
