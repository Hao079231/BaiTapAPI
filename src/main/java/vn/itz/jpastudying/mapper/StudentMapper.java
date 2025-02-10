package vn.itz.jpastudying.mapper;

import java.util.List;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

import vn.itz.jpastudying.Dto.StudentDto;
import vn.itz.jpastudying.form.student.StudentCreateForm;
import vn.itz.jpastudying.form.student.StudentUpdateForm;
import vn.itz.jpastudying.model.Student;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface StudentMapper {
  @Mappings({
      @Mapping(source = "userNameValue", target = "username"),
      @Mapping(source = "fullNameValue", target = "fullname"),
      @Mapping(source = "birthDateValue", target = "birthday"),
      @Mapping(source = "passWordValue", target = "password"),
      @Mapping(source = "genderValue", target = "gender"),
      @Mapping(source = "roleValue", target = "role")
  })
  Student convertToStudent(StudentCreateForm requestDto);

  @Mappings({
      @Mapping(source = "id", target = "studentIdValue"),
      @Mapping(source = "username", target = "userNameValue"),
      @Mapping(source = "fullname", target = "fullNameValue"),
      @Mapping(source = "birthday", target = "birthDateValue"),
      @Mapping(source = "gender", target = "genderValue"),
      @Mapping(source = "role", target = "roleValue")

  })
  @Named("mapStudentDto")
  StudentDto convertToStudentResponse(Student student);

  @IterableMapping(elementTargetType = StudentDto.class, qualifiedByName = "mapStudentDto")
  List<StudentDto> convertToListStudentResponse(List<Student> students);

  @Mappings({
      @Mapping(source = "userNameValue", target = "username"),
      @Mapping(source = "fullNameValue", target = "fullname"),
      @Mapping(source = "birthDateValue", target = "birthday"),
      @Mapping(source = "passWordValue", target = "password"),
      @Mapping(source = "genderValue", target = "gender"),
      @Mapping(source = "roleValue", target = "role")
  })
  void updateStudent(@MappingTarget Student student, StudentUpdateForm studentDto);
}
