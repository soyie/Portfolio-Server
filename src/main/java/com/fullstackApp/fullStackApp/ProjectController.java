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
    List<String> projectPics = new ArrayList<String>();
    List<byte[]> files = new ArrayList<byte[]>();
    ProjectData project = new ProjectData();


    @GetMapping("/")
    public String listProjects(Model model) {
        model.addAttribute("projects", db.getAllImages());
        return "projectList";
    }
    @GetMapping("/messages")
    public String listMessages(Model model) {
        model.addAttribute("projects", db.getAllMessages());
        return "projectList";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        List<String> projectTypes = Arrays.asList("Web Development", "Mobile Development", "Machine Learning", "Systems Integration", "Command Line Interface");  // Replace with your actual project types
        model.addAttribute("projectTypes", projectTypes);
        model.addAttribute("project", new ProjectData());
        return "addProject";
    }

//    @PostMapping("/add")
//    public String addProject(@RequestParam("image") MultipartFile file,
//                             @RequestParam("name") String itemName,
//                             @RequestParam("type") String type,
//                             @RequestParam("description") String description,
//                             @RequestParam("gitHub") String github,
//                             @RequestParam("testLink") String link,
//                             @RequestParam("images") MultipartFile[] images,
//                             Model model) throws IOException {
//        UUID proj = UUID.randomUUID();
//
//        if (!file.isEmpty()) {
//            project.setImage(file.getBytes());
//        }
//        for (int i = 0; i <= 2; i++) {
//            files.add(images[i].getBytes());
//        }
//        project.setName(itemName);
//        project.setType(type);
//        project.setTestLink(link);
//        project.setGitHub(github);
//        project.setDescription(description);
//
//        project.setUuid(proj);
//        project.setImageList(files);
//
//        BudgetBossDataBase.insertImage(project, files);
//        return "redirect:/";
//    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        List<String> projectTypes = Arrays.asList("Web Development", "Mobile Development", "Machine Learning", "Systems Integration", "Command Line Interface");  // Replace with your actual project types
        model.addAttribute("projectTypes", projectTypes);
        ProjectData project = getProjectById(id);
        model.addAttribute("project", project);
        return "editProject";
    }

//    @GetMapping("/project/{projectId}")
//    public String showProject(@PathVariable long projectId, Model model) {
//        ProjectData projectData = db.getImageByName(projectId);
//
//        if (projectData != null) {
//            // Convert byte[] to Base64-encoded string
//            String base64Image = Base64.getEncoder().encodeToString(projectData.getImage());
//            projectData.setBase64Image(base64Image);
//
//            model.addAttribute("projectData", projectData);
//            return "projectDetails";
//        } else {
//            // Handle case where project is not found
//            return "error";
//        }
//    }

//    @PostMapping("/edit/{id}")
//    public String editProject(@PathVariable int id,
//                              @RequestPart("image") MultipartFile image,
//                              @RequestPart("name") String itemName,
//                              @RequestPart("type") String type,
//                              @RequestPart("description") String description,
//                              @RequestPart("gitHub") String github,
//                              @RequestPart("testLink") String link) throws IOException {
//        ProjectData project = getProjectById(id);
//        if (project != null) {
//            if (!image.isEmpty()) {
//                project.setImage(image.getBytes());
//            }
//            System.out.println(project);
//            project.setName(itemName);
//            project.setType(type);
//            project.setTestLink(link);
//            project.setGitHub(github);
//            project.setDescription(description);
//            BudgetBossDataBase.updateImage(id, project);
//        }
//        return "redirect:/";
//    }

    @GetMapping("/delete/{id}")
    public String deleteProject(@PathVariable int id) {
        ProjectData project = getProjectById(id);
        if (project != null) {
            BudgetBossDataBase.deleteImage(id);
        }
        return "redirect:/";
    }

//    @GetMapping("/info/{id}")
//    public String showProject(@PathVariable Long id, Model model) throws IOException {
//        List<String> projectPics = new ArrayList<String>();
//        ProjectData project = getProjectById(id);
//        byte[] imageBytes = project.getImage();
//        for (int i = 0; i <= 2; i++) {
//            System.out.println(db.imagesList.get(i));
//            BufferedImage images = convertBytesToImage(db.imagesList.get(i));
//            String base64Images = ImageUtil.convertImageToBase64(images);
//            projectPics.add(base64Images);
//        }
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.IMAGE_JPEG);
//        headers.setContentLength(imageBytes.length);
//        BufferedImage image = convertBytesToImage(imageBytes);
//        String base64Image = ImageUtil.convertImageToBase64(image);
//
//        model.addAttribute("image", base64Image);
//        model.addAttribute("images", projectPics);
//        model.addAttribute("project", project);
//        return "project";
//    }

    private ProjectData getProjectById(long id) {
        return db.getAllImages().stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    private static BufferedImage convertBytesToImage(byte[] imageData) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
        return ImageIO.read(bis);
    }
}