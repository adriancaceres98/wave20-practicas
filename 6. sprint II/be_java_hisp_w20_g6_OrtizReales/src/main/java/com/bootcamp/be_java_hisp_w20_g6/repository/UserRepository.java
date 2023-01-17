package com.bootcamp.be_java_hisp_w20_g6.repository;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import com.bootcamp.be_java_hisp_w20_g6.model.UserModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository {
   private Map<Integer, UserModel> userList;

    public UserRepository(){

        UserModel user1 = UserModel.builder()
                .user_id(1)
                .user_name("pablito1")
                .followers(new ArrayList<>())
                .followed(new ArrayList<>())
                .build();

        UserModel user2 = UserModel.builder()
                .user_id(2)
                .user_name("pedrito1")
                .followers(new ArrayList<>())
                .followed(new ArrayList<>())
                .build();

        UserModel user3 = UserModel.builder()
                .user_id(3)
                .user_name("kardashianOficial")
                .followers(new ArrayList<>())
                .followed(new ArrayList<>())
                .build();

        userList = new HashMap<>();
        userList.put(user1.getUser_id(), user1);
        userList.put(user2.getUser_id(), user2);
        userList.put(user3.getUser_id(), user3);
    }

    public UserModel getUserById(int id){
        return userList.get(id);
    }
}
