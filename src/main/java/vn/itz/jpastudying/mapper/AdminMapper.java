package vn.itz.jpastudying.mapper;

import java.util.List;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import vn.itz.jpastudying.Dto.AdminDto;
import vn.itz.jpastudying.form.admin.AdminCreateForm;
import vn.itz.jpastudying.model.Admin;

@Mapper(componentModel = "spring")
public interface AdminMapper {
  @Mappings({
      @Mapping(source = "userNameValue", target = "user.username"),
      @Mapping(source = "fullNameValue", target = "user.fullname"),
      @Mapping(source = "passWordValue", target = "user.password"),
      @Mapping(source = "genderValue", target = "user.gender"),
      @Mapping(source = "levelValue", target = "level"),
  })
  Admin convertToAdmin(AdminCreateForm requestDto);

  @Mappings({
      @Mapping(source = "id", target = "adminId"),
      @Mapping(source = "user.username", target = "adminUserName"),
      @Mapping(source = "user.fullname", target = "adminFullName"),
      @Mapping(source = "user.gender", target = "adminGender"),
      @Mapping(source = "level", target = "adminLevel"),
      @Mapping(source = "superAdmin", target = "adminIsSuperAdmin")
  })
  @Named("mapAdminDto")
  AdminDto convertToAdminResponse(Admin admin);

  @IterableMapping(elementTargetType = AdminDto.class, qualifiedByName = "mapAdminDto")
  List<AdminDto> convertToListStudentResponse(List<Admin> admins);
}
