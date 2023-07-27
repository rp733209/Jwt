package com.csi.repo;

import com.csi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    //Custom Methods Write Here
    public List<User> findByUserName(String userName);

    public List<User> getDataByUserSalary(double userSalary);
}
