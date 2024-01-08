package com.fullstackApp.fullStackApp.ManageClientUser;


import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

//import com.fullstackApp.fullStackApp.databases.BudgetBossDataBase;
import com.fullstackApp.fullStackApp.databases.MessagesCRUD;
import com.fullstackApp.fullStackApp.databases.ProjectsCRUD;
import kong.unirest.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class ClientController {
    ProjectsCRUD projDB = new ProjectsCRUD();
    MessagesCRUD msgDB = new MessagesCRUD();
    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

    private final AtomicLong counter = new AtomicLong();
    @GetMapping("/allApps")
    public List<Project> greeting() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return projDB.getAllProjects();
    }

    @PostMapping("/SendMessage")
    public String[] greetingFromClient(@RequestBody String user) throws SQLException {
        JSONObject jsonObject = new JSONObject(user);
        msgDB.insertMessage(jsonObject.get("senderEmail").toString(), jsonObject.get("sender").toString(), jsonObject.get("message").toString(), LocalDate.now(), LocalTime.now());
        return new String[]{"Response", "Thank you for sending a message I promise to get back to you immediately."};
    }
}
