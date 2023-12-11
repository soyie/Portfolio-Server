package com.fullstackApp.fullStackApp.ManageClientUser;

import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class ProjectData {

//    private static int nextId = 0;

    int id;
    private List<byte[]> images;
    byte[] Image;
    String Name;
    String Type;
    String GitHub;
    String TestLink;
    int Views;
    String Description;

    UUID uuid;

    List<byte[]> ImageList;


    private String base64Image;

    public ProjectData(byte[] image, String name, String type, String gitHub, String testLink, int views, String description) {
        Image = image;
        Name = name;
        Type = type;
        GitHub = gitHub;
        TestLink = testLink;
        Views = views;
        Description = description;
    }

    public ProjectData() {

    }



    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public byte[] getImage() {
        return Image;
    }

    public void setImage(byte[] image) {
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getGitHub() {
        return GitHub;
    }

    public void setGitHub(String gitHub) {
        GitHub = gitHub;
    }

    public String getTestLink() {
        return TestLink;
    }

    public void setTestLink(String testLink) {
        TestLink = testLink;
    }

    public int getViews() {
        return Views;
    }

    public void setViews(int views) {
        Views = views;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }


    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public List<byte[]> getImageList() {
        return ImageList;
    }

    public void setImageList(List<byte[]> imageList) {
        ImageList = imageList;
    }

//    public ProjectData() {
//        this.images = new ArrayList<>();
//    }

    public List<byte[]> getImages() {
        return images;
    }

    public void setImages(List<byte[]> images) {
        this.images = images;
    }

    public void addImage(byte[] image) {
        this.images.add(image);
    }

    @Override
    public String toString() {
        return "ProjectData{" +
                "id=" + id +
                ", images=" + images +
                ", Image=" + Image +
                ", Name='" + Name + '\'' +
                ", Type='" + Type + '\'' +
                ", GitHub='" + GitHub + '\'' +
                ", TestLink='" + TestLink + '\'' +
                ", Views=" + Views +
                ", Description='" + Description + '\'' +
                ", uuid=" + uuid +
                ", ImageList=" + ImageList +
                ", base64Image='" + base64Image + '\'' +
                '}';
    }
}
