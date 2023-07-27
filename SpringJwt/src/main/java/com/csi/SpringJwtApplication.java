package com.csi;

import com.csi.model.User;
import com.csi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class SpringJwtApplication {
    @Autowired
    UserService userServiceImpl;

    public static void main(String[] args) {
        SpringApplication.run(SpringJwtApplication.class, args);
    }

    @PostConstruct
    public void saveUserInformation() {
        userServiceImpl.saveData(new User(101, "Rahul", "Pune", 75000, 9632587412L, String.valueOf(01 - 10 - 2022), "rahul@fintech.com", "rahul123"));

        /*SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");
        String userDBDOB= simpleDateFormat.format(userServiceImpl.getDataByDOB("01-10-1999"));
        */

       /* userServiceImpl.saveBulkOfData(Stream.of(new User(121, "Swara", "PCMC", 65000, 9865321452L, String.valueOf(01-02-1885), "SWARA@FINTECHCSI.COM", "SWARA@2023")).collect(Collectors.toList()));


    }
*/
    }
}
