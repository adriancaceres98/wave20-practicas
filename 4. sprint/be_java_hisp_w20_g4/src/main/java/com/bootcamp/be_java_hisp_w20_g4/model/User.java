package com.bootcamp.be_java_hisp_w20_g4.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class User {
    private int user_id;
    private String user_name;
    private HashMap<Integer, User> followed = new HashMap<>();


    /**
     * el propio usuario agrega en su lista de seguidos (followed) la persona a la que siguió
      * @param userToFollow
     * @return
     */
    public User addUserToMyFollowedList(User userToFollow){
        if(followed.containsKey(userToFollow.getUser_id())){
            return null; // lanza excepcion porque ya lo sigue
        }else{
            followed.put(userToFollow.getUser_id(), userToFollow);
            return userToFollow;
        }
    }



}
