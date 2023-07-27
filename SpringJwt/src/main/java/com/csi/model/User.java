package com.csi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    private int userId;

    private String userName;

    private String userAddress;

    private double userSalary;

    private long userContactNumber;

    @JsonFormat(pattern = "dd-MM-yyyy",timezone = "Asia/Kolkata")
    private Date userDOB;

    private String userEmailId;

    private String userPassword;

    public User(int userId, String swara, String pcmc, double userSalary, long userContactNumber, String s, String mail, String userPassword) {
        this.userId=userId;
        this.userName=userName;
    }
}
