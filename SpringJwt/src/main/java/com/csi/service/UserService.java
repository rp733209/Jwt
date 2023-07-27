package com.csi.service;

import com.csi.model.User;
import com.csi.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepo userRepoImpl;

    public User saveData(User user) {
        return userRepoImpl.save(user);
    }

    public Optional<User> getDataById(int userId) {
        return userRepoImpl.findById(userId);
    }

    public List<User> getAllData() {
        return userRepoImpl.findAll();
    }

    public User getDataByEmailId(String userEmailId) {
        return getAllData().stream().filter(user -> user.getUserEmailId().equals(userEmailId)).collect(Collectors.toList()).get(0);
    }

    public List<User> getDataBySalary(double userSalary) {
        return userRepoImpl.getDataByUserSalary(userSalary);
    }

    public List<User> sortUserBySalary() {
        return getAllData().stream().sorted(Comparator.comparingDouble(User::getUserSalary)).collect(Collectors.toList());
    }

    public List<User> sortDataByName() {
        return getAllData().stream().sorted(Comparator.comparing(User::getUserName)).collect(Collectors.toList());
    }

    public List<User> sortByDOB() {
        return getAllData().stream().sorted(Comparator.comparing(User::getUserDOB)).collect(Collectors.toList());
    }

    public User updateDataById(User user) {
        return userRepoImpl.save(user);
    }
    public List<User> saveBulkOfData(List<User> users) {

        return userRepoImpl.saveAll(users);
    }

    public boolean checkLoanEligibility(int userId) {
        boolean flag = false;

        for (User user : getAllData()) {
            if (user.getUserId() == userId && user.getUserSalary() >= 30000.00) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public List<User> getDataByUsingAnyInput(String input) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        List<User> userList = new ArrayList<>();
        for (User user : getAllData()) {

            String userDOBDB = simpleDateFormat.format(user.getUserDOB());

            if (userDOBDB.equals(input)
                    || String.valueOf(user.getUserName()).equals(input)
                    || user.getUserAddress().equals(input)
                    || user.getUserEmailId().equals(input)
                    || String.valueOf(user.getUserContactNumber()).equals(input)
                    || user.getUserDOB().equals(input)) {
                userList.add(user);
            }


        }

        return userList;


    }

    public List<User> getDataByDOB(String userDOB) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        List<User> userList = new ArrayList<>();
        for (User user : getAllData()) {
            String userDBDOB = simpleDateFormat.format(user.getUserDOB());
            if (userDBDOB.equals(userDOB)) {
                userList.add(user);
            }
        }
        return userList;
    }

    public void deleteAllData() {
        userRepoImpl.deleteAll();
    }
    public void datadeletebyid(int userId){
        userRepoImpl.deleteById(userId);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = (User) userRepoImpl.findByUserName(username);
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getUserPassword(), new ArrayList<>());
    }


}