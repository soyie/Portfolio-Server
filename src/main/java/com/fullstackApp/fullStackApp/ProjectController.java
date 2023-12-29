package com.fullstackApp.fullStackApp;

import com.fullstackApp.fullStackApp.ManageClientUser.ImageUtil;
import com.fullstackApp.fullStackApp.ManageClientUser.ProjectData;
import com.fullstackApp.fullStackApp.databases.BudgetBossDataBase;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.*;

@Controller
public class ProjectController {
    BudgetBossDataBase db = new BudgetBossDataBase();
    ProjectData project = new ProjectData();

    //getting all the projects from the database to the Server IU for editing, adding and reading and deleting
    @GetMapping("/")
    public String listProjects(Model model) {
        model.addAttribute("projects", db.getAllImages());
        return "projectList";
    }

    //this is used when adding a new project these are the types of projects I can do
    @GetMapping("/add")
    public String showAddForm(Model model) {
        List<String> projectTypes = Arrays.asList("Web Development", "Mobile Development", "Machine Learning", "Systems Integration", "Command Line Interface", "Data Science", "Cloud");  // Replace with your actual project types
        model.addAttribute("projectTypes", projectTypes);
        model.addAttribute("project", new ProjectData());
        return "addProject";
    }
    //Posting a project to the database
    @PostMapping("/add")
    public String addProject(@RequestParam("image") MultipartFile file,
                             @RequestParam("name") String itemName,
                             @RequestParam("type") String type,
                             @RequestParam("description") String description,
                             @RequestParam("gitHub") String github,
                             @RequestParam("Link") String link,
                             @RequestParam("images") MultipartFile[] images,
                             Model model) throws IOException {

        if (!file.isEmpty()) {
            project.setImage(file.getBytes());
        }
        project.setName(itemName);
        project.setType(type);
        project.setLink(link);
        project.setGitHub(github);
        project.setDescription(description);
        project.setScreen1(images[0].getBytes());
        project.setScreen2(images[1].getBytes());
        project.setScreen3(images[2].getBytes());
        project.setProjectUID(UUID.randomUUID());

        db.insertImage(project);

        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        List<String> projectTypes = Arrays.asList("Web Development", "Mobile Development", "Machine Learning", "Systems Integration", "Command Line Interface");  // Replace with your actual project types
        model.addAttribute("projectTypes", projectTypes);
        ProjectData project = getProjectById(id);
        model.addAttribute("project", project);
        return "editProject";
    }

    //showing a project 1 by 1
    @GetMapping("/project/{projectId}")
    public String showProject(@PathVariable long projectId, Model model) {
        ProjectData projectData = db.getImageByName(projectId);

        if (projectData != null) {
            String base64Image = Base64.getEncoder().encodeToString(projectData.getImage());
            projectData.setBase64Image(base64Image);

            model.addAttribute("projectData", projectData);
            return "projectDetails";
        } else {
            // Handle case where project is not found
            return "error";
        }
    }
    //used for editing projects
    @PostMapping("/edit/{id}")
    public String editProject(@PathVariable int id,
                              @RequestPart("image") MultipartFile image,
                              @RequestPart("name") String itemName,
                              @RequestPart("type") String type,
                              @RequestPart("description") String description,
                              @RequestPart("gitHub") String github,
                              @RequestPart("testLink") String link) throws IOException {
        ProjectData project = getProjectById(id);
        if (project != null) {
            if (!image.isEmpty()) {
                project.setImage(image.getBytes());
            }
            project.setName(itemName);
            project.setType(type);
            project.setLink(link);
            project.setGitHub(github);
            project.setDescription(description);
            BudgetBossDataBase.updateImage(id, project);
        }
        return "redirect:/";
    }
    //delete project
    @GetMapping("/delete/{id}")
    public String deleteProject(@PathVariable int id) {
        ProjectData project = getProjectById(id);
        if (project != null) {
            BudgetBossDataBase.deleteImage(id);
        }
        return "redirect:/";
    }

    @GetMapping("/info/{id}")
    public String showProject(@PathVariable Long id, Model model) throws IOException {
        List<String> projectPics = new ArrayList<String>();
        ProjectData project = getProjectById(id);

        byte[] imageBytes = project.getImage();
        BufferedImage screen1 = convertBytesToImage(project.getScreen1());
        String base64Screen1 = ImageUtil.convertImageToBase64(screen1);
        projectPics.add(base64Screen1);

        BufferedImage screen2 = convertBytesToImage(project.getScreen2());
        String base64Screen2 = ImageUtil.convertImageToBase64(screen2);
        projectPics.add(base64Screen2);

        BufferedImage screen3 = convertBytesToImage(project.getScreen3());
        String base64Screen3 = ImageUtil.convertImageToBase64(screen3);
        projectPics.add(base64Screen3);


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(imageBytes.length);
        BufferedImage image = convertBytesToImage(imageBytes);
        String base64Image = ImageUtil.convertImageToBase64(image);

        model.addAttribute("image", base64Image);
        model.addAttribute("images", projectPics);
        model.addAttribute("project", project);
        return "project";
    }

    @GetMapping("/messages")
    public String showAddMessages(Model model) {
        model.addAttribute("messages", db.getAllMessages());
        return "messages";
    }

    private ProjectData getProjectById(long id) {
        return db.getAllImages().stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    private static BufferedImage convertBytesToImage(byte[] imageData) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
        return ImageIO.read(bis);
    }
}
