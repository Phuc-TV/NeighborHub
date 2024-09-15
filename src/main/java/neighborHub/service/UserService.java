package neighborHub.service;

import neighborHub.model.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> viewListAllUser();

    UserDto updateUser(UserDto userDto);

    Boolean deleteUser(Long id);
}
