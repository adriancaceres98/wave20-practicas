package com.bootcamp.be_java_hisp_w20_g7.repository;

import com.bootcamp.be_java_hisp_w20_g7.entity.Follow;
import lombok.Getter;
import org.springframework.beans.PropertyValues;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface IFollowRepository {

    List<Follow> findAll();

    boolean save(Follow follow);

    void deleteByIds(int idFollower, int idFollowed);

}
