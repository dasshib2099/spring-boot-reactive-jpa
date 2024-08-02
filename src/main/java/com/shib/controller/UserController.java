package com.shib.controller;

import com.shib.dto.AllUserDTO;
import com.shib.dto.FileUploadRequest;
import com.shib.dto.FileUploadResponse;
import com.shib.dto.UserDTO;
import com.shib.model.User;
import com.shib.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.server.PathParam;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("get/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer id){
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) throws Exception {
        return ResponseEntity.ok(userService.update(userDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok(userService.delete(id));
    }

    @GetMapping("/getall")
    public ResponseEntity<AllUserDTO> getAllUser(@PathVariable Integer id){
        return ResponseEntity.ok(userService.getAllUser());
    }

    @PostMapping("/addAll")
    public ResponseEntity<String> addAllUser(@RequestBody AllUserDTO addAllUserRequest){
        return ResponseEntity.ok(userService.saveAll(addAllUserRequest));
    }

//    @GetMapping("/fallback/get/{id}")
//    @CircuitBreaker
//    public ResponseEntity<AllUserDTO> getAllUser(@PathVariable Integer id){
//        return ResponseEntity.ok(userService.getAllUser());
//    }

    @PostMapping("/fileupload")
    public ResponseEntity<FileUploadResponse> fileUpload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name){
        FileUploadRequest fileUploadRequest = new FileUploadRequest(name, file);
            return ResponseEntity.ok(userService.fileUpload(fileUploadRequest));
    }

    @GetMapping("/filedownload/{filename}")
    public void downloadFile(@PathVariable("filename") String filename, HttpServletResponse response){
        try{
            InputStream is = UserController.class.getClassLoader().getResourceAsStream(filename);
            IOUtils.copy(is, response.getOutputStream());
            response.setContentType("application/CSV");
            response.flushBuffer();
        }catch(Exception e){
           throw new RuntimeException("IOError while output stream");
        }
    }



}
