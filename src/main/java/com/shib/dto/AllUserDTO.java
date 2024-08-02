package com.shib.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class AllUserDTO {

    private List<UserDTO> userList;
}
