package com.shib.mapper;

import com.shib.model.User;
import lombok.Data;

import java.util.List;

@Data
public class AllUser {
    List<User> userList;
}
