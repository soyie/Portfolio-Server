package com.fullstackApp.fullStackApp.ManageClientUser;


import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fullstackApp.fullStackApp.databases.BudgetBossDataBase;
import kong.unirest.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class ClientController {
    BudgetBossDataBase db = new BudgetBossDataBase();
    MessagesList messages = new MessagesList();
    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

    private final AtomicLong counter = new AtomicLong();
    @GetMapping("/allApps")
    public List<ProjectData> greeting() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return db.getAllImages();
    }

    @PostMapping("/SendMessage")
    public String[] greetingFromClient(@RequestBody String user) throws SQLException {
        System.out.println(user);
        JSONObject jsonObject = new JSONObject(user);
        System.out.println("Data from js "+jsonObject);
//        new MessagesList( ,);
//        System.out.println(jsonObject.get("sender").toString());
        messages.setSender(jsonObject.get("sender").toString());
        messages.setSenderEmail(jsonObject.get("senderEmail").toString());
        messages.setMessage(jsonObject.get("message").toString());
        messages.setDate(LocalDate.now());
        messages.setTime( LocalTime.now());
        db.insertMessage(messages);
        return new String[]{"hi", "Not Created"};
    }
}
