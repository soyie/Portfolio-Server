package com.fullstackApp.fullStackApp.ManageClientUser;


import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

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
        JSONObject jsonObject = new JSONObject(user);
        messages.setSender(jsonObject.get("sender").toString());
        messages.setSenderEmail(jsonObject.get("senderEmail").toString());
        messages.setMessage(jsonObject.get("message").toString());
        messages.setDate(LocalDate.now());
        messages.setTime( LocalTime.now());
        db.insertMessage(messages);
        return new String[]{"Response", "Thank you for sending a message I promise to get back to you immediately."};
    }
}
