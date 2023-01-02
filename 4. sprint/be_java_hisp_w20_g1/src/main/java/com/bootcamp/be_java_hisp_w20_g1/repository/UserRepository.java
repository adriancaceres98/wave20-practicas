package com.bootcamp.be_java_hisp_w20_g1.repository;

import com.bootcamp.be_java_hisp_w20_g1.model.User;
import com.bootcamp.be_java_hisp_w20_g1.repository.interfaces.IUserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Repository
public class UserRepository implements IUserRepository {

    private List<User> users;

    public UserRepository() {
        this.users = buildUserRepository();
    }

    @Override
    public boolean isValidId(int id){
        return getUserById(id) != null;
    }

    @Override
    public boolean isSeller(int id){
        return getUserById(id).isSeller();
    }
    @Override
    public User getUserById(int id){
        return users.stream().filter(u -> id == u.getId()).findFirst().orElse(null);
    }

    @Override
    public void addFollower(int userIdToModify, int userIdOfFollower){
        for (User user : users){
            if (user.getId() == userIdToModify){
                user.getFollowers().add(userIdOfFollower);
            }
        }
    }

    @Override
    public void removeFollower(int userIdToModify, int userIdOfFollower) {
        for (User user : users) {
            if (user.getId() == userIdToModify) {
                 user.getFollowers().remove(userIdOfFollower);
            }
        }
    }

    @Override
    public void addFollowed(int userIdToModify, int userIdToFollow){
        for (User user : users){
            if (user.getId() == userIdToModify){
                user.getFollowed().add(userIdToFollow);
            }
        }
    }

    @Override
    public void removeFollowed(int userIdToModify, int userIdToFollow) {
        for (User user : users) {
            if (user.getId() == userIdToModify) {
                user.getFollowed().remove(userIdToFollow);
            }
        }
    }

    List<User> buildUserRepository() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:users.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<User>> typeRef = new TypeReference<>() {
        };
        List<User> users = null;
        try {

            users = objectMapper.readValue(file, typeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }
}
