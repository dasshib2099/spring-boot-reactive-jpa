package com.shib.service;

import com.shib.dto.AllUserDTO;
import com.shib.dto.FileUploadRequest;
import com.shib.dto.FileUploadResponse;
import com.shib.dto.UserDTO;
import com.shib.mapper.UserMapper;
import com.shib.model.User;
import com.shib.repository.UserRepository;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    public UserDTO addUser(UserDTO userDTO){
        User user = userMapper.mapToUser(userDTO);
        userRepository.save(user);
        return userMapper.mapToUserDTO(user);
    }

    public UserDTO findUserById(Integer id){
       User user =  userRepository.getReferenceById(id);
       return userMapper.mapToUserDTO(user);
    }

    public UserDTO update(UserDTO userDTO) throws Exception {
        if(userRepository.existsById(userDTO.getId())){
            User user = userMapper.mapToUser(userDTO);
            userRepository.save(user);
            return userDTO;
        }else{
            throw new Exception("User does not exist with id:"+userDTO.getId());
        }
    }

    public String delete(Integer id) throws Exception {
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
            return "Success";
        }else{
            throw new Exception("User does not exist with id:"+id);
        }

    }

    public AllUserDTO getAllUser(){
        List<User> userList = userRepository.findAll();
        return userMapper.mapUserListToDTOList(userList);
    }

    public String saveAll(AllUserDTO allUserRequest){
      List<User> userList = userMapper.mapUserDTOListToUserList(allUserRequest);
      userRepository.saveAll(userList);
      return "Success";
    }

    public FileUploadResponse fileUpload(FileUploadRequest fileUploadRequest){
        List<User> userList = new ArrayList<User>();
        try{
            File file = new File(fileUploadRequest.getName());
            FileUtils.writeByteArrayToFile(file, fileUploadRequest.getFile().getBytes());
            Scanner scanner = new Scanner(file);
            while(scanner.hasNext()){
                String[] argsArray = scanner.next().split(",");
                userList.add(userMapper.maptoEntity(argsArray));
            }
            userRepository.saveAll(userList);
            scanner.close();;
        }catch(Exception e){
           throw  new RuntimeException("Internal Error");
        }
        return new FileUploadResponse(fileUploadRequest.getName(), userList.size(), "Success");
    }

}
