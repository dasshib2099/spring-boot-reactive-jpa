package com.shib.mapper;

import com.shib.dto.AllUserDTO;
import com.shib.dto.UserDTO;
import com.shib.model.User;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public User mapToUser(UserDTO userDTO){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userDTO, User.class);
    }

    public UserDTO mapToUserDTO(User user){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(user, UserDTO.class);
    }

    public User maptoEntity(String[] args){
        User user = new User();
        user.setId(Integer.valueOf(args[0]));
        user.setName(args[1]);
        user.setEmailId(args[2]);
        user.setMobileNumber(args[3]);
        return user;
    }

    public AllUserDTO mapUserListToDTOList(List<User> userList){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userList, AllUserDTO.class);
    }

    public List<User> mapUserDTOListToUserList(AllUserDTO allUserRequest){
        ModelMapper modelMapper = new ModelMapper();
        List<User> userList = allUserRequest.getUserList()
                .stream()
                .map(user -> modelMapper.map(user, User.class))
                .collect(Collectors.toList());
        return userList;
    }
}
