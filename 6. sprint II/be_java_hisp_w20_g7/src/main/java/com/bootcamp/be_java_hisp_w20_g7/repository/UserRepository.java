package com.bootcamp.be_java_hisp_w20_g7.repository;

import com.bootcamp.be_java_hisp_w20_g7.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository implements IUserRepository {

    List<User> users;

    public UserRepository() {
        users = new ArrayList<>();
        users.add(new User(1, "Sebastian"));
        users.add(new User(2, "Manuel"));
        users.add(new User(3, "Soraya"));
        users.add(new User(4, "Ronald"));
        users.add(new User(5, "Nathalia"));
        users.add(new User(6, "Tomas"));
        users.add(new User(7, "Stiven"));
        users.add(new User(8, "Juan"));
    }

    @Override
    public User findById(int id) {
        return users.stream().filter(e -> e.getUserId() == id).findFirst().orElse(null);
    }
}
