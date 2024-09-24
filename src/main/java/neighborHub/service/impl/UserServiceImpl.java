package neighborHub.service.impl;

import neighborHub.model.Entity.User;
import neighborHub.model.dto.UserDto;
import neighborHub.repository.UserRepository;
import neighborHub.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper)
    {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UserDto> viewListAllUser() {
        List<User> userList = userRepository.findAll();
        if (userList == null)
            return null;
        List<UserDto> users = userList.stream()
                .map(userList1 -> modelMapper.map(userList1, UserDto.class)).toList();
        return users;
    }

    @Override
    public UserDto updateUser(UserDto userDto)
    {
        User userById = userRepository.findById(userDto.getUserId()).orElse(null);

        if (userById == null)
            return null;

        userById.setPhone(userDto.getPhone());
        userById.setUsername(userDto.getUsername());
        userById.setEmail(userDto.getEmail());

        userRepository.save(userById);
        return modelMapper.map(userById, UserDto.class);
    }

    @Override
    public Boolean deleteUser(Long id)
    {
        User user = userRepository.findById(id).orElse(null);
        if (user == null)
            return false;

        user.setStatus(true);
        userRepository.save(user);
        return true;
    }
}
