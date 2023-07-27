package com.csi.controller;

import com.csi.exception.RecordNotFoundException;
import com.csi.model.AuthRequest;
import com.csi.model.User;
import com.csi.service.UserService;
import com.csi.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserService userServiceImpl;

    @PostMapping("/saveuserdata")
    public ResponseEntity<User> saveUserData(@RequestBody User user) {
        return ResponseEntity.ok(userServiceImpl.saveData(user));
    }

    @GetMapping("/getuserdatabyid/{userId}")
    public ResponseEntity<Optional<User>> getDataByID(@PathVariable int userId) {
        return ResponseEntity.ok(userServiceImpl.getDataById(userId));
    }

    @GetMapping("/getalldataofuser")
    public ResponseEntity<List<User>> getAllData() {
        return ResponseEntity.ok(userServiceImpl.getAllData());
    }

    @GetMapping("/getuserdatabyemailid/{userEmailId}")
    public ResponseEntity<User> getDataByEmailId(@PathVariable String userEmailId) {
        return ResponseEntity.ok(userServiceImpl.getDataByEmailId(userEmailId));
    }

    @GetMapping("/getdatabysalary/{userSalary}")
    public ResponseEntity<List<User>> getDataBySalary(@PathVariable double userSalary) {
        return ResponseEntity.ok(userServiceImpl.getDataBySalary(userSalary));
    }

    @GetMapping("/sortbyusersalary")
    public ResponseEntity<List<User>> sortUserDataBySalary() {
        return ResponseEntity.ok(userServiceImpl.sortUserBySalary());
    }

    @GetMapping("/sortuserdatabyname")
    public ResponseEntity<List<User>> sortDataByuserName() {
        return ResponseEntity.ok(userServiceImpl.sortDataByName());
    }

    @GetMapping("/sortdatabydob")
    public ResponseEntity<List<User>> sortDataByDOB() {
        return ResponseEntity.ok(userServiceImpl.sortByDOB());
    }

    @PutMapping("/updatedatabyid/{userId}")
    public ResponseEntity<User> updateDataById(@PathVariable int userId, User user) {
        User user1 = userServiceImpl.getDataById(userId).orElseThrow(() -> new RecordNotFoundException("User Id Does not Exist"));
        user1.setUserName(user.getUserName());
        user1.setUserAddress(user.getUserAddress());
        user1.setUserSalary(user.getUserSalary());
        user1.setUserEmailId(user.getUserEmailId());
        user1.setUserContactNumber(user.getUserContactNumber());
        user1.setUserPassword(user.getUserPassword());
        return ResponseEntity.ok(userServiceImpl.updateDataById(user1));
    }

    @PostMapping("/savebulkofdata")
    public ResponseEntity<List<User>> saveBulkOfData(@RequestBody List<User> users) {
        return ResponseEntity.ok(userServiceImpl.saveBulkOfData(users));
    }

    @GetMapping("/checkloaneligibility/{userId}")
    public ResponseEntity<Boolean> checkLoanEligibility(@PathVariable int userId) {
        return ResponseEntity.ok(userServiceImpl.checkLoanEligibility(userId));
    }

    @GetMapping("/getdatabyusinganyinput/{input}")
    public ResponseEntity<List<User>> getDataByUsingAnyInput(@PathVariable String input) {
        return ResponseEntity.ok(userServiceImpl.getDataByUsingAnyInput(input));
    }

    @GetMapping("/getdatabydob/{userDOB}")
    public ResponseEntity<List<User>> getDataByDOB(@PathVariable String userDOB) {
        return ResponseEntity.ok(userServiceImpl.getDataByDOB(userDOB));
    }

    @DeleteMapping("/deletealldata")
    public ResponseEntity<String> deleteAllData() {
        userServiceImpl.deleteAllData();
        return ResponseEntity.ok("Data Deleted SuccesFully");
    }

    @DeleteMapping("/datadeletebyid/{userId}")
    public ResponseEntity<String> deleteDataById(@PathVariable int userId) {
        userServiceImpl.datadeletebyid(userId);
        return ResponseEntity.ok("Data Deleted Succeszfully");
    }
    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getUserPassword()));
        } catch (Exception ex) {
            throw new Exception("Incorrect un/pwd");
        }
        return jwtUtil.generateToken(authRequest.getUserName());
    }

}
