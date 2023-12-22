package com.fullstackApp.fullStackApp.ManageClientUser;


import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import com.fullstackApp.fullStackApp.Controllers.CreatingAccounts;
import com.fullstackApp.fullStackApp.databases.BudgetBossDataBase;
import kong.unirest.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class ClientController {
    BudgetBossDataBase db = new BudgetBossDataBase();

    private final AtomicLong counter = new AtomicLong();
    @GetMapping("/allApps")
    public List<ProjectData> greeting() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return db.getAllImages();
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/SendMessage")
    public String greetingFromClient(@RequestBody String user) throws SQLException {
//        System.out.println(user);
//        NewAccount acc = new NewAccount();
////        System.out.println("User data: "+user);
//        if (user.contains("%22") && user.contains("+")) {
//            try {
//                String decodedString = URLDecoder.decode(user, "UTF-8");
//                if (decodedString.endsWith("=")) {
//                    decodedString = decodedString.substring(0, decodedString.length() - 1);
//                    JSONObject jsonObject = new JSONObject(decodedString);
//                    System.out.println("Data from curl "+jsonObject);
//                    acc.setName(jsonObject.get("name").toString());
//                    acc.setSurname(jsonObject.get("surname").toString());
//                    acc.setEmail(jsonObject.get("email").toString());
//                    acc.setGender(jsonObject.get("gender").toString());
//                    acc.setPhone(jsonObject.get("phone").toString());
//                    acc.setMaritalStatus(jsonObject.get("status").toString());
//                    acc.setOccupation(jsonObject.get("occupation").toString());
//                    acc.setPassword(jsonObject.get("password").toString());
//                    acc.setUserName(jsonObject.get("userName").toString());
//                    acc.setUuid(UUID.randomUUID());
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        else{
//            JSONObject jsonObject = new JSONObject(user);
//            System.out.println("Data from js "+jsonObject);
//            acc.setName(jsonObject.get("name").toString());
//            acc.setSurname(jsonObject.get("surname").toString());
//            acc.setEmail(jsonObject.get("email").toString());
//            acc.setGender(jsonObject.get("gender").toString());
//            acc.setPhone(jsonObject.get("phone").toString());
//            acc.setMaritalStatus(jsonObject.get("status").toString());
//            acc.setOccupation(jsonObject.get("occupation").toString());
//            acc.setPassword(jsonObject.get("password").toString());
//            acc.setUserName(jsonObject.get("userName").toString());
//            acc.setUuid(UUID.randomUUID());
//            if (acc.CreateNewAccounts() == CreatingAccounts.CREATED){
//                return "Created";
//            }
//        }
        return "Not Created";
    }

}
