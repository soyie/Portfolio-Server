package com.fullstackApp.fullStackApp.ManageClientUser;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;


//Project data structure i use it to make sure that I get the correct data
public class ProjectData {
    int id;
    String base64Image;
    String Name;
    String Type;
    String Description;
    String link;
    String GitHub;
    int views;
    UUID ProjectUID;
    byte[] image;
    byte[] Screen1;
    byte[] Screen2;
    byte[] Screen3;

    public ProjectData(String name, String type, String description, String link, String gitHub, int views, byte[] image, byte[] screen1, byte[] screen2, byte[] screen3) {
        Name = name;
        Type = type;
        Description = description;
        this.link = link;
        GitHub = gitHub;
        this.views = views;
        this.image = image;
        Screen1 = screen1;
        Screen2 = screen2;
        Screen3 = screen3;
    }

    public UUID getProjectUID() {
        return ProjectUID;
    }

    public void setProjectUID(UUID projectUID) {
        ProjectUID = projectUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProjectData() {
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

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getGitHub() {
        return GitHub;
    }

    public void setGitHub(String gitHub) {
        GitHub = gitHub;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public byte[] getScreen1() {
        return Screen1;
    }

    public void setScreen1(byte[] screen1) {
        Screen1 = screen1;
    }

    public byte[] getScreen2() {
        return Screen2;
    }

    public void setScreen2(byte[] screen2) {
        Screen2 = screen2;
    }

    public byte[] getScreen3() {
        return Screen3;
    }

    public void setScreen3(byte[] screen3) {
        Screen3 = screen3;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    @Override
    public String toString() {
        return "ProjectData{" +
                "Name='" + Name + '\'' +
                ", Type='" + Type + '\'' +
                ", Description='" + Description + '\'' +
                ", link='" + link + '\'' +
                ", GitHub='" + GitHub + '\'' +
                ", views=" + views +
                ", image=" + Arrays.toString(image) +
                ", Screen1=" + Arrays.toString(Screen1) +
                ", Screen2=" + Arrays.toString(Screen2) +
                ", Screen3=" + Arrays.toString(Screen3) +
                '}';
    }
}
